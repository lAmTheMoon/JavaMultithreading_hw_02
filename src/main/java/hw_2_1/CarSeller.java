package hw_2_1;

import java.util.concurrent.atomic.AtomicInteger;

public class CarSeller {
    private CarShop carShop;
    private final long TIME_ACCEPTING_CAR = 500L;
    private final long TIME_CAR_PRODUCTION = 800L;
    private AtomicInteger carNumber = new AtomicInteger(1);

    public CarSeller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void sellCar() {
        System.out.println("Покупатель " + Thread.currentThread().getName() + " пришла за машиной.");
        try {
            while (carShop.getCarsList().size() == 0) {
                System.out.println("Машин нет!");
                wait();
            }
            Thread.sleep(TIME_ACCEPTING_CAR);
        } catch (InterruptedException ignored) {
        }
        Car car = carShop.getCarsList().remove(0);
        System.out.println("Поступила в магазин " + car.getName());
        System.out.println(car.getName() + " продана! Счастливый владелец - " + Thread.currentThread().getName() + ".");
    }

    public synchronized void receiveCar() {
        try {
            Thread.sleep(TIME_CAR_PRODUCTION);
        } catch (InterruptedException ignored) {
        }
        Car car = new Car("MASHINA" + carNumber.getAndIncrement());
        carShop.getCarsList().add(car);
        notify();
    }

    public synchronized int getCarNumber() {
        return carNumber.get();
    }
}
