public class Task1 {
    public static void main(String[] args) {
        // Thread 1: Print numbers 1 to 10
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Number: " + i);
                try {
                    Thread.sleep(100); // Simulate processing delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2: Print squares of numbers 1 to 10
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Square: " + (i * i));
                try {
                    Thread.sleep(100); // Simulate processing delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();
    }
}
