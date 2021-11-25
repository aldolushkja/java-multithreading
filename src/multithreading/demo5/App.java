package multithreading.demo5;

//Intrinsic Looking
//Synchronized lock execution to current thread and does not cache variables, no need to volatile variables inside synchronized blocks.
public class App {

    private int count = 0;

    public synchronized void increment(){
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }
}
