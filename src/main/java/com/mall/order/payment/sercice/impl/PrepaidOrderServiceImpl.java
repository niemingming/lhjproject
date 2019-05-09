package com.mall.order.payment.sercice.impl;
/**
 * @author Lihaj
 */
import com.mall.order.payment.mapper.PrepaidOrderMapper;
import com.mall.order.payment.pojo.BaseResp;
import com.mall.order.payment.pojo.PropertyPayment;
import com.mall.order.payment.pojo.PropertyPaymentOption;
import com.mall.order.payment.sercice.PrepaidOrderService;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PrepaidOrderServiceImpl implements PrepaidOrderService {

    @Resource
    private PrepaidOrderMapper prepaidOrderMapper;

    /**
     * 保存预缴费订单信息
     * @param
     * @return 订单主键
     * @throws Exception 实现操作数据库失败时事物回滚
     * @author Lihj
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResp addPrepaidOrder(JSONObject jsonObject) throws Exception {
        String resultCode = "200";
        String msg = "请求成功";
        String userCode = (String) jsonObject.get("userCode");
        String roomCode = (String) jsonObject.get("roomCode");
        String strDate = (String) jsonObject.get("srDate");
        String endDate = (String) jsonObject.get("endDate");
        String totalMoney = (String) jsonObject.get("totalMoney");
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        listJson = (List<JSONObject>) jsonObject.get("billDatailList");

        //获取订单信息并保存
        PropertyPayment p = new PropertyPayment();
        String pkBill = getPk();
        p.setPkBill(pkBill);
        p.setUserCode(userCode);
        p.setRoomCode(roomCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        p.setBeginDate(sdf.parse(strDate));
        p.setEndDate(sdf.parse(endDate));
        p.setTotalMoney(Double.parseDouble(totalMoney));
        Date now = new Date();
        p.setCreatedTime(now);
        //根据房间号获取对应小区的编号和小区名称
        Map areaInfo = getAreaByHouse(roomCode);
        String areaCode = (String) areaInfo.get("area_code");
        p.setAreaCode(areaCode);
        String areaName = (String) areaInfo.get("area_name");
        p.setAreaName(areaName);
        //二次校验
        Integer ordersByUser = getCheckUnpaidOrderUser(userCode);
        if (ordersByUser>0){
            resultCode = "500";
            msg = "请求失败";
        }else {
            Integer ordersByRomm = getCheckUnpaidOrderRoom(roomCode);
            if(ordersByRomm>0){
                resultCode = "500";
                msg = "请求失败";
            }else {
                List<Double> moneyList = new ArrayList<Double>();
                moneyList.add(Double.parseDouble(totalMoney));
                for (int i=0;i<listJson.size();i++){
                    Double money = Double.parseDouble(listJson.get(i).get("chargeMoney").toString()) ;
                    moneyList.add(money);
                }
                Integer result = getCheckTotalMoney(moneyList);
                if (result == 0) {
                    resultCode = "500";
                    msg = "请求失败";
                }else {
                    savePropertyPayment(p);
                    //获取收费项目信息并保存
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
                        ppo.setDiscount(ppoJson.getAsString("discount"));
                        ppo.setChargeMoney(ppoJson.getAsString("chargeMoney"));
                        ppo.setNcPkStd(ppoJson.getAsString("ncPkStd"));
                        ppo.setNcPkDiscount(ppoJson.getAsString("ncPkDiscount"));
                        ppo.setDiscountName(ppoJson.getAsString("discountName"));
                        savePropertyPaymentOption(ppo);
                    }
                }
            }
        }
        return new BaseResp(resultCode,msg,null);
    }

    /**
     * 查询预缴费订单列表
     * @param userCode 业主代码
     * @param orderStatus 状态筛选
     * @param orderTime 时间筛选
     * @return 订单列表信息
     * @author Lihj
     */
    @Transactional(rollbackFor = Exception.class)
    public List<PropertyPayment> getPrepaidOrder(String userCode, String orderStatus, String orderTime){
        List<PropertyPayment> propertyPaymentsList = prepaidOrderMapper.getPropertyPaymentList(userCode,orderStatus,orderTime);
        return propertyPaymentsList;
    }

    /**
     * 保存订单记录
     * @param
     * @author Lihj
     */
    public void savePropertyPayment(PropertyPayment propertyPayment){
        prepaidOrderMapper.savePropertyPayment(propertyPayment);
    }

    /**
     * 保存收费项目记录
     * @param
     * @author Lihj
     */
    public void savePropertyPaymentOption(PropertyPaymentOption propertyPaymentOption){
        prepaidOrderMapper.savePropertyPaymentOption(propertyPaymentOption);
    }

    /**
     * 根据房间号获取小区名称和小区编码
     * @param
     * @return MAP
     * @author Lihj
     */
    public Map getAreaByHouse(String strHouse){
        Map areaMap = prepaidOrderMapper.getAreaByHouse(strHouse);
        return areaMap;
    }

    /**
     * 按照规则生成订单表主键
     * @return 主键
     * @author Lihj
     */
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

    /**
     * 校验该用户是否存在未支付订单
     * @param userCode
     * @author Lihj
     */
    public Integer getCheckUnpaidOrderUser(String userCode){
       Map<String,String> orderMap =  prepaidOrderMapper.getCheckUnpaidOrderUser(userCode);
       if (orderMap == null || orderMap.equals(null)) {
           return 0;
       }else {
           return orderMap.size();
       }
    }
    /**
     * 校验该用户是否存在未支付订单
     * @param roomCode
     * @author Lihj
     */
    public Integer  getCheckUnpaidOrderRoom(String roomCode){
        Map<String,String> orderMap =  prepaidOrderMapper.getCheckUnpaidOrderRoom(roomCode);
        if (orderMap == null ||orderMap.equals(null)){
            return 0;
        }else {
            return orderMap.size();
        }
    }

    /**
     * 校验总金额和和明细金额是否一致
     * @param doubles 金额
     * @return 1：一致，0：不一致
     */
    public Integer  getCheckTotalMoney(List<Double> doubles){
        double total = doubles.get(0);
        double itemMoney = 0;
        for(int i=1;i<doubles.size();i++){
            itemMoney += doubles.get(i);
        }
        if(total == itemMoney){
            return 1;
        }else {
            return 0;
        }
    }

}
