package org.academiadecodigo.calculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Calculator {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Calculator calculator = context.getBean("calculator", Calculator.class);
        System.out.println(calculator.add(10,10));
        System.out.println(calculator.multiply(5,5));
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}
