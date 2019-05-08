package com.mall.order.payment.pojo;

import com.mall.order.payment.controller.PrepaidOrderController;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class BaseMockTest {
    //启用web上下文
    @Autowired
    private WebApplicationContext context;
    //使用上下文伪造mvc环境
    @Autowired
    private PrepaidOrderController prepaidOrderController;

    @Before
    public void setUp(){
        MockMvcBuilders.webAppContextSetup(context).build();
    }

}
