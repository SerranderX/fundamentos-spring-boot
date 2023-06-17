package com.fundamentos.springboot.fundamentos.bean;

public class BeanWithDependencyImpl implements BeanWithDependency {

    private MyOperation myOperation;

    public BeanWithDependencyImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void saludo() {
        System.out.println("Operacion: "+this.myOperation.suma(5, 15));
        System.out.println("Saludando luego de la operacion realizada en BeanWithDependencyImpl");
    }
}
