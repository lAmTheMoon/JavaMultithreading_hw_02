package hw_2_1;

import java.util.concurrent.atomic.AtomicInteger;

public class CarSeller {
    private CarShop carShop;
    private final long TIME_CAR_PRODUCTION = 800L;
    private AtomicInteger carNumber = new AtomicInteger(1);

    public CarSeller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void sellCar() {
        System.out.println("Покупатель " + Thread.currentThread().getName() + " пришла за машиной.");
        try {
            while (carShop.getCarsList().isEmpty()) {
                System.out.println("Машин нет!");
                wait();
            }
        } catch (InterruptedException e) {
            return;
        }
        Car car = carShop.getCarsList().poll();
        System.out.println("Поступила в магазин " + car);
        System.out.println(car + " продана! Счастливый владелец - " + Thread.currentThread().getName() + ".");
    }

    public void receiveCar() {
        try {
            Thread.sleep(TIME_CAR_PRODUCTION);
        } catch (InterruptedException ignored) {
        }
        synchronized (this) {
            Car car = new Car("MASHINA" + carNumber.getAndIncrement());
            carShop.getCarsList().add(car);
            notify();
        }
    }

    public synchronized int getCarNumber() {
        return carNumber.get() - 1;
    }
}
