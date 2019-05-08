package com.mall.order.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrepaidOrderMapper {
    void  savePropertyPayment();
    void savePropertyPaymentOption();
}
