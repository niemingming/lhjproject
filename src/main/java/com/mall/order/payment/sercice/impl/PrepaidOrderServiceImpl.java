package com.mall.order.payment.sercice.impl;

import com.mall.order.payment.mapper.PrepaidOrderMapper;
import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.pojo.PropertyPaymentOption;
import com.mall.order.payment.sercice.PrepaidOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrepaidOrderServiceImpl implements PrepaidOrderService {

    @Resource
    private PrepaidOrderMapper prepaidOrderMapper;

    public void savePropertyPayment(PropertyPayment propertyPayment){
        System.out.println("----新增订单信息---");
        prepaidOrderMapper.savePropertyPayment();
        System.out.println("----完成新增功能---");
    }

    public void savePropertyPaymentOption(PropertyPaymentOption propertyPaymentOption){
        System.out.println("----新增收费项目信息---");
        prepaidOrderMapper.savePropertyPaymentOption();
        System.out.println("----完成新增功能---");
    }

    public List<PropertyPayment> getPrepaidOrder(String userCode, String orderStatus, String orderTime){

        return  null;
    }


}
