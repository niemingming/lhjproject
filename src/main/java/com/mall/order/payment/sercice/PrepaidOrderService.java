package com.mall.order.payment.sercice;
/**
 * @author Lihaj
 */

import com.mall.order.payment.pojo.BaseResp;
import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.pojo.PropertyPaymentOption;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PrepaidOrderService {

    void savePropertyPayment(PropertyPayment propertyPayment);

    void savePropertyPaymentOption(PropertyPaymentOption propertyPaymentOption);

    List<PropertyPayment> getPrepaidOrder(String userCode, String orderStatus, String orderTime);

    Map getAreaByHouse(String strHouse);

    BaseResp addPrepaidOrder(JSONObject jsonObject) throws Exception;
}
