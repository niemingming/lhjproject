package com.mall.order.payment.pojo;
/*************************************************************
 * 类：返回实体类
 * @author Lihj
 *************************************************************
 */
public class BaseResp
{
    private String resultCode;
    private String msg;
    private Object data;

    public void BaseResp(){

    }

    public BaseResp(String resultCode,String msg,Object data){
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = data;
    }

    public String getCode()
    {
        return resultCode;
    }

    public void setCode(String code)
    {
        this.resultCode = resultCode;
    }

    public String getMessage()
    {
        return msg;
    }

    public void setMessage(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
