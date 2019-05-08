package com.mall.order.payment;

import com.mall.order.payment.pojo.BaseMockTest;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentApplicationTests extends BaseMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void CreatePrepaidOrder()throws Exception {
        //构造参数体
        JSONObject data = new JSONObject();
        data.put("userCode","2019022516485543296");
        data.put("roomCode","201902212339");
        data.put("srDate","2019-04-01");
        data.put("endDate","2019-06-30");
        data.put("totalMoney","3060");

        List<JSONObject> list = new ArrayList<JSONObject>();
        JSONObject billJsonOne = new JSONObject();
        billJsonOne.put("pkChargeItem","10011A100000Z001W001");
        billJsonOne.put("chargeItem","物业服务费");
        billJsonOne.put("feeType","0");
        billJsonOne.put("nprice","200.00");
        billJsonOne.put("advanceMoney","3060.00");
        billJsonOne.put("discountMoney","200");
        billJsonOne.put("discount","0.8");
        billJsonOne.put("chargeMoney","2860");
        billJsonOne.put("ncPkStd","10011A100000Z001W001");
        billJsonOne.put("ncPkDiscount","10011A100000000J2FF7");
        billJsonOne.put("discountName","空置房折扣");
        list.add(billJsonOne);

        JSONObject billJsonTwo = new JSONObject();
        billJsonTwo.put("pkChargeItem","10011A100000Z001W002");
        billJsonTwo.put("chargeItem","水费");
        billJsonTwo.put("feeType","1");
        billJsonTwo.put("nprice",null);
        billJsonTwo.put("advanceMoney",null);
        billJsonTwo.put("discountMoney",null);
        billJsonTwo.put("discount",null);
        billJsonTwo.put("chargeMoney","200");
        billJsonTwo.put("ncPkStd",null);
        billJsonTwo.put("ncPkDiscount",null);
        billJsonTwo.put("discountName",null);
        list.add(billJsonTwo);
        data.put("billDatailList",list);
        mockMvc.perform(MockMvcRequestBuilders.post("/chargeItem/createOrder")
                .param("data",data.toJSONString())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
