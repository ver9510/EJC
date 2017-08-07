package task_threads_04_08;

public class Yield {
    /**
     * Example of using threads and method yield
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.err.println(Thread.currentThread().getName() + " " + i);
                    Thread.yield();
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
