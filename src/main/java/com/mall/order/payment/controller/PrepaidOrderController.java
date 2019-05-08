package com.mall.order.payment.controller;

import com.mall.order.payment.pojo.BaseResp;
import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.pojo.PropertyPaymentOption;
import com.mall.order.payment.sercice.impl.PrepaidOrderServiceImpl;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chargeItem")
public class PrepaidOrderController {

    @Autowired
    private PrepaidOrderServiceImpl prepaidOrderServiceImpl;


    @RequestMapping(value = "/createOrder/{data}",method = RequestMethod.POST)

    public BaseResp CreatePrepaidOrder(@RequestBody JSONObject data ) throws ParseException {
        String userCode = (String) data.get("userCode");
        String roomCode = (String) data.get("roomCode");
        String strDate = (String) data.get("srDate");
        String endDate = (String) data.get("endDate");
        String totalMoney = (String) data.get("totalMoney");
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        listJson = (List<JSONObject>) data.get("billDatailList");

        //保存订单信息
        PropertyPayment p = new PropertyPayment();
        String pkBill = getPk();
        p.setPkBill(pkBill);
        p.setUserCode(userCode);
        p.setRoomCode(roomCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        p.setBeginDate(sdf.parse(strDate));
        p.setEndDate(sdf.parse(endDate));
        p.setTotalMoney(Float.parseFloat(totalMoney));
        Date now = new Date();
        System.out.println("------当前时间:"+now);
        p.setCteatedTime(now);
        prepaidOrderServiceImpl.savePropertyPayment(p);

        //保存收费项目信息
        for(int i = 0;i<listJson.size(); i++ ){
            PropertyPaymentOption ppo = new PropertyPaymentOption();
            String ppoPk = getPk();
            ppo.setPkChargeItem(ppoPk);
            JSONObject ppoJson = listJson.get(i);
            ppo.setChargeItem(ppoJson.getAsString("chargeItem"));
            ppo.setFeeType(ppoJson.getAsString("feeType"));
            ppo.setnPrice(ppoJson.getAsString("nPrice"));
            ppo.setAdvanceMoney(ppoJson.getAsString("advanceMoney"));
            ppo.setDiscountMoney(ppoJson.getAsString("dicountMoney"));
            ppo.setDicount(ppoJson.getAsString("dicount"));
            ppo.setChargeMoney(ppoJson.getAsString("chargeMoney"));
            ppo.setNcPkStd(ppoJson.getAsString("ncPkStd"));
            ppo.setNvPkDiscount(ppoJson.getAsString("ncPkDiscount"));
            ppo.setDiscountName(ppoJson.getAsString("discountName"));
            prepaidOrderServiceImpl.savePropertyPaymentOption(ppo);
        }
        return  new BaseResp(200,"请求成功",null);
    }

    @Ignore
    @GetMapping("/getOrder")
    public BaseResp getPrepaidOrder(String userCode,String orderStatus,String orderTime){


        return  new BaseResp(200,"请求成功",null);
    }

    /**
     * 按照规则生成订单表主键
     * @return 主键
     * @author Lihj
     */
    @Ignore
    public String getPk(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(year);
        sBuffer.append(month);
        sBuffer.append(day);
        sBuffer.append(hour);
        sBuffer.append(minute);
        sBuffer.append(second);
        int i = (int)(Math.random()*900 + 100);
        sBuffer.append(i);
        sBuffer.append("A1");
        System.out.print(sBuffer);
        return sBuffer.toString();
    }
}
