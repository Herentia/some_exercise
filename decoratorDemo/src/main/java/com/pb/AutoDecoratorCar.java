package com.pb;

/**
 * @author haohan
 * @date 2021/1/4 14:56
 * 自动驾驶汽车类
 */
public class AutoDecoratorCar extends CarDecorator {

    public AutoDecoratorCar(Car decoratorCar) {
        super(decoratorCar);
    }

    @Override
    public void run() {
        super.run();
        aotoRun();
    }

    /** 自动驾驶功能 */
    private void aotoRun() {
        System.out.println("开启自动驾驶功能");
    }
}
