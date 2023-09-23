package org.example;

public class Main {

    private static class Counter {

        private int value;

        Counter() {}

        void increment() {
            value++;
        }

        int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable target = () -> {
            for(int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(target), thread2 = new Thread(target);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }

        catch(InterruptedException ie) {}

        //Конечное значение счетчика может отличаться из-за того, что один поток может считать значение перед тем, как другой его увеличит.

        System.out.println(counter.getValue());
    }
}