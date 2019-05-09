package com.mall.order.payment.mapper;
/**
 * @author Lihaj
 */

import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.pojo.PropertyPaymentOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PrepaidOrderMapper {
    void  savePropertyPayment(PropertyPayment propertyPayment);
    void savePropertyPaymentOption(PropertyPaymentOption propertyPaymentOption);
    Map getAreaByHouse(String strHouse);
    List<PropertyPayment> getPropertyPaymentList(@Param("userCode") String userCode,
                                                 @Param("orderStatus") String orderStatus,
                                                 @Param("orderTime") String orderTime);
    Map getCheckUnpaidOrderUser(String userCode);
    Map getCheckUnpaidOrderRoom(String roomCode);
}
