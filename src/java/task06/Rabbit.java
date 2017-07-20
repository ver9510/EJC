package task06;

import java.io.IOException;

public class Rabbit {
    public volatile static boolean isEating=true;

    public static void main(String[] args) {
        new Rabbit.Eating().start();
        new Rabbit.Input().start();
    }

    public static class Input extends Thread{
        @Override
        public void run() {
            try {
                int input=System.in.read();

            } catch (IOException e) {
                e.printStackTrace();
            }
            isEating=false;
        }
    }

    public static class Eating extends Thread{
        @Override
        public void run() {
            while(isEating){
                System.err.println("eat carrot");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("I stopped eating. What do you want? ");
        }
    }
}
