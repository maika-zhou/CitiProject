package com.dto;

import com.enums.ResponseStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;

public class OrderRespDTO
{
    private ResponseStatus status = ResponseStatus.SUCCESS;
    private String errorCode;
    private String errorMsg;
    private TransactionSendResult transactionSendResult;

    public OrderRespDTO()
    {
    }

    public OrderRespDTO(ResponseStatus status, String errorCode, String errorMsg)
    {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public ResponseStatus getStatus()
    {
        return status;
    }

    public void setStatus(ResponseStatus status)
    {
        this.status = status;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public TransactionSendResult getTransactionSendResult()
    {
        return transactionSendResult;
    }

    public void setTransactionSendResult(TransactionSendResult transactionSendResult)
    {
        this.transactionSendResult = transactionSendResult;
    }
}
