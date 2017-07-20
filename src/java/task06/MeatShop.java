package task06;

public class MeatShop {
    private int cash = 1000;

    public static void main(String[] args) {
        MeatShop shop = new MeatShop();
        shop.buyMeat(15);
    }

    private synchronized void buyMeat(int amount) {
        for (int i = 0; i < amount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (cash >= 100) {
                        cash -= 100;
                        System.err.println("Done. Money left: " + cash);
                    } else {
                        System.err.println("Not enough money");
                    }
                }
            }).start();
        }
    }
}
