package org.arsonal.accounting;

import org.arsonal.accounting.controller.HelloController;
import org.arsonal.accounting.model.service.Greeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

class ApplicationTest {
    private HelloController helloController = new HelloController();

    @BeforeAll
    public static void setUpAll() {
        System.out.println("在所有测试用例之前运行且只运行一次！方法需要声明为static");
    }

    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException {
        System.out.println("初始化测试类的实例！");
        // 如果HelloController类依赖Greeting类
        // AutoWired注解直接放在field字段上，
        // 通过反射的方式将，greeting变量注入HelloController
        // 如果AutoWired注解放在构造器上，则明显简单多，
        // helloController = new HelloController(mockGreeting)；
        // 所以spring建议通过构造器方式注入依赖
        Greeting mockGreeting = Mockito.mock(Greeting.class);
        Field greeting = HelloController.class.getField("greeting");
        greeting.setAccessible(true);
        greeting.set(helloController, mockGreeting);
    }

    @Test
    void addEquals() {
        Assertions.assertEquals(3, Application.add(1, 1, 1));
    }

    @Test
    void addFail() {
        Assertions.assertNotEquals(4, Application.add(1, 1, 1));
    }
}