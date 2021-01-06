package com.pb;

/**
 * @author haohan
 * @date 2021/1/4 14:53
 * 汽车装饰器类
 */
public class CarDecorator implements Car {

    protected Car decoratorCar;

    public CarDecorator(Car decoratorCar) {
        this.decoratorCar = decoratorCar;
    }

    public void run() {
        decoratorCar.run();
    }
}
