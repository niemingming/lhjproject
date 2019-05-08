package com.mall.order.payment.sercice;

import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.pojo.PropertyPaymentOption;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrepaidOrderService {

    void savePropertyPayment(PropertyPayment propertyPayment);

    void savePropertyPaymentOption(PropertyPaymentOption propertyPaymentOption);

    List<PropertyPayment> getPrepaidOrder(String userCode, String orderStatus, String orderTime);
}
