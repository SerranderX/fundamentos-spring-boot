package com.fundamentos.springboot.fundamentos.bean;

public class MyOperationImpl implements MyOperation {
    @Override
    public int suma(int num1, int num2) {
        return num1 + num2;
    }
}
