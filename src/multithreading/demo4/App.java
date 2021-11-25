package multithreading.demo4;

import java.util.Scanner;

//Sharing variables in Thread is dangerous
//variable running is read from Processor thread and write from Main thread, can happen that processor thread caches
//values and then the change on the variable could be not read from processor thread.
//Adding 'volatile' keyword prevents thread to cache variables

class Processor extends Thread{

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running){
            System.out.println("Hello, world!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}

public class App {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();


    }
}
