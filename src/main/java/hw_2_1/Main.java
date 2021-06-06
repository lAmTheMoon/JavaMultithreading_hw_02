package hw_2_1;

public class Main {
    public static void main(String[] args) {
        CarShop carShop = new CarShop();
        Thread supplier = new Thread(null, carShop::receiveCar, "Поставщик авто");
        supplier.start();
        ThreadGroup threadGroup = new ThreadGroup("Покупатели");
        new Thread(threadGroup, carShop::sellCar, "Татьяна").start();
        new Thread(threadGroup, carShop::sellCar, "Ольга").start();
        new Thread(threadGroup, carShop::sellCar, "Марина").start();
        try {
            supplier.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadGroup.interrupt();
    }
}
