package hw_2_1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CarShop {
    private final long TIME_SELLING_CAR = 500L;
    private final int CAR_COUNT = 10;
    private final Queue<Car> carsList = new ConcurrentLinkedQueue<>();
    private volatile CarSeller carSeller = new CarSeller(this);

    public void receiveCar() {
        while (carSeller.getCarNumber() != CAR_COUNT) {
            carSeller.receiveCar();
        }
    }

    public void sellCar() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(TIME_SELLING_CAR);
                if (carSeller.getCarNumber() == CAR_COUNT) {
                    break;
                }
                carSeller.sellCar();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public Queue<Car> getCarsList() {
        return carsList;
    }
}
