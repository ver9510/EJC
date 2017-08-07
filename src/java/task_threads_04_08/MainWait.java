package task_threads_04_08;

public class MainWait {
    /**
     * Example of using threads with sinchronized key, methods notify and wait
     * */
    private static final Object key = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 11; i++) {
                    System.err.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                synchronized (key) {
                    key.notifyAll();
                }
            }
        });

        System.err.println("start...");
        thread.start();
        synchronized (key) {
            key.wait();
        }
        //thread.join();
        System.err.println("finish");
    }
}
