package com.pb;

/**
 * @author haohan
 * @date 2021/1/4 14:59
 * 带有飞行功能的汽车，包含了自动驾驶功能
 */
public class FlyDecoratorCar extends CarDecorator {

    public FlyDecoratorCar(Car decoratorCar) {
        super(decoratorCar);
    }

    @Override
    public void run() {
        super.run();
        flyRun();
    }

    private void flyRun() {
        System.out.println("开启汽车飞行功能");
    }
}
