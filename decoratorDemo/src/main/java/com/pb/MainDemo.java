package com.pb;

/**
 * @author haohan
 * @date 2021/1/4 15:01
 */
public class MainDemo {

    public static void main(String[] args) {
        Car aodiCar = new AodiCar();
        Car baomaCar = new BaomaCar();
//        Optional.ofNullable(aodiCar).orElse(null);

        AutoDecoratorCar autoDecoratorCar = new AutoDecoratorCar(aodiCar);
        autoDecoratorCar.run();

        FlyDecoratorCar flyDecoratorCar = new FlyDecoratorCar(new AutoDecoratorCar(baomaCar));
        flyDecoratorCar.run();
    }

}
