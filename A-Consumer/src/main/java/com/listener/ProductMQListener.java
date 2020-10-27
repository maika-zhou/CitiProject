package com.listener;


import com.alibaba.fastjson.JSON;
import com.msg.OrderMsg;
import com.pojo.Product;
import com.service.ProductService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component 
@RocketMQMessageListener(consumerGroup="consumer_tx_group",topic = "msg_receiver_destination")
public class ProductMQListener implements RocketMQListener<String>{

	@Autowired
	private ProductService productService;
	
	public void onMessage(String message) {
		System.out.println("ConsumerTransferListener.onMessage()");
		//message 就是json字符串.
		OrderMsg msg = JSON.parseObject(message,OrderMsg.class);


		System.out.println("msg.toString() = " + msg.toString());

		//Decrease Inventory
		Product product = productService.findById(msg.getPid());
		product.setQuantity(product.getQuantity() - msg.getQuantity());
		productService.updateProduct(product);


	}

}
