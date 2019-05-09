package com.mall.order.payment.controller;
/**
 * @author Lihaj
 */

import com.mall.order.payment.pojo.BaseResp;
import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.sercice.impl.PrepaidOrderServiceImpl;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chargeItem")
public class PrepaidOrderController {

    @Autowired
    private PrepaidOrderServiceImpl prepaidOrderServiceImpl;

    /**
     * 生成预缴费订单信息，包括订单记录和收费项目记录
     * @param data
     * @return BaseResp
     * @author Lihj
     */
    @PostMapping(value = "/createOrder")
    public BaseResp CreatePrepaidOrder(@RequestParam String data)  {
        String resultCode = "200";
        String msg = "请求成功";
        String pkBill = "";
        JSONObject jsonObject = (JSONObject) JSONValue.parse(data);
        String userCode = (String) jsonObject.get("userCode");
        String roomCode = (String) jsonObject.get("roomCode");
        try{
            //校验
            Integer ordersByUser = prepaidOrderServiceImpl.getCheckUnpaidOrderUser(userCode);
            if (ordersByUser>0){
                resultCode = "500";
                msg = "请求失败";
            }else {
                Integer ordersByRomm = prepaidOrderServiceImpl.getCheckUnpaidOrderRoom(roomCode);
                if(ordersByRomm>0){
                    resultCode = "500";
                    msg = "请求失败";
                }else {
                    List<Double> moneyList = new ArrayList<Double>();
                    Double totalMoney = Double.parseDouble(jsonObject.get("totalMoney").toString()) ;
                    moneyList.add(totalMoney);
                    List<JSONObject> listJson = (List<JSONObject>) jsonObject.get("billDatailList");
                    for (int i=0;i<listJson.size();i++){
                        Double money = Double.parseDouble(listJson.get(i).get("chargeMoney").toString()) ;
                        moneyList.add(money);
                    }
                    Integer result = prepaidOrderServiceImpl.getCheckTotalMoney(moneyList);
                    if (result == 0) {
                        resultCode = "500";
                        msg = "请求失败";
                    }else {
                        BaseResp baseResp = prepaidOrderServiceImpl.addPrepaidOrder(jsonObject);
                        resultCode = baseResp.getCode();
                        msg = baseResp.getMessage();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            resultCode = "500";
            msg = "请求失败";
        }
        return new BaseResp(resultCode,msg,null);
    }

    @GetMapping(value = "/getOrder")
    //传路径参数用@PathVariable，传表单参数用@RequestParam
    public BaseResp getPrepaidOrder( String userCode, String orderStatus, String orderTime ){
        String resultCode = "200";
        String msg = "请求成功";
        List<PropertyPayment> propertyPayments = new ArrayList<PropertyPayment>();
        try{
            propertyPayments = prepaidOrderServiceImpl.getPrepaidOrder(userCode,orderStatus,orderTime);
        }catch (Exception e){
            e.printStackTrace();;
            resultCode = "500";
            msg="请求失败";
        }
        return  new BaseResp(resultCode,msg,propertyPayments);
    }


}
