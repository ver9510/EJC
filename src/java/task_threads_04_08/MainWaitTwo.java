package task_threads_04_08;

public class MainWaitTwo {
    /**
     * Example of using threads with sinchronized key, methods notify and wait, with notify in if-clause
     */
    private static final Object key = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 11; i++) {
                    System.err.println(i);
                    if (i == 3) {
                        synchronized (key) {
                            key.notifyAll();
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

