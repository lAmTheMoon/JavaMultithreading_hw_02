package hw_2_1;

import java.util.ArrayList;
import java.util.List;

public class CarShop {
    private final long TIME_SELLING_CAR = 500L;
    private final long TIME_CAR_PRODUCTION = 100L;
    private final int CAR_COUNT = 10;
    private final List<Car> carsList = new ArrayList<>(CAR_COUNT);
    private CarSeller carSeller = new CarSeller(this);

    public CarShop() {
    }

    public void receiveCar() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(TIME_CAR_PRODUCTION);
                if (carSeller.getCarNumber() > CAR_COUNT) {
                    break;
                }
                carSeller.receiveCar();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void sellCar() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(TIME_SELLING_CAR);
                if (carSeller.getCarNumber() > CAR_COUNT) {
                    break;
                }
                carSeller.sellCar();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public List<Car> getCarsList() {
        return carsList;
    }
}
