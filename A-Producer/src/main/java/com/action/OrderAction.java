package com.action;


import com.dao.TxNoDAO;
import com.dto.OrderReqDTO;
import com.dto.OrderRespDTO;
import com.enums.ResponseStatus;
import com.msg.OrderMsg;
import com.pojo.Acct;
import com.pojo.Product;
import com.service.AcctService;
import com.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "order")
public class OrderAction
{
    @Autowired
    private ProductService productService;
    @Autowired
    private AcctService acctService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @ApiOperation(value = "下单",notes = "下单")
    @RequestMapping(value = "/placeOrder",method = RequestMethod.POST)
    public OrderRespDTO placeOrder(@ModelAttribute OrderReqDTO orderDTO)
    {
        OrderRespDTO respDTO = new OrderRespDTO();
        try
        {
            /**
             *  Init Check
             *
             */
            //Chk if inventory is enough
            Product product = productService.findById(orderDTO.getPid());
            if(product.getQuantity()<orderDTO.getQuantity())
            {
                throw new RuntimeException("Inventory is not enough");
            }
            //Chk if Card Number is valid
            Acct acct = acctService.findById(orderDTO.getId());
            if(acct.getCardNumber().indexOf("8888")!=-1)
            {
                throw new RuntimeException("Card Number is not valid");
            }
            //Chk if Card's balance is valid
            if( acct.getBalance()< orderDTO.getQuantity()*product.getPrice() )
            {
                throw new RuntimeException("Balance is not enough");
            }

            /*
             * txProducerGroup: 事务组ID,  让程序知道调用哪个类的executeLocalTransaction
             * destination : topic,
             * message: 事务消息
             * arg: 扩展参数
             */
            OrderMsg orderMsg = new OrderMsg(orderDTO.getId(),orderDTO.getPid(),orderDTO.getQuantity(),orderDTO.getQuantity()*product.getPrice());
            Message<OrderMsg> message = MessageBuilder.withPayload(orderMsg).build();
            TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("producer_tx_group", "msg_receiver_destination", message, null);
            respDTO.setTransactionSendResult(result);



            System.out.println("result = " + result);
        }catch (Exception e)
        {
            respDTO.setStatus(ResponseStatus.FAIL);
            respDTO.setErrorMsg(e.getMessage());
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        // Order Save


        return respDTO;

    }
}
