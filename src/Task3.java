import java.util.concurrent.CopyOnWriteArrayList;

public class Task3 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> sharedList = new CopyOnWriteArrayList<>();

        Runnable writerTask = () -> {
            for (int i = 0; i < 5; i++) {
                sharedList.add(Thread.currentThread().getName() + " - " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable readerTask = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " reads: " + sharedList);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread writerThread1 = new Thread(writerTask, "Writer1");
        Thread writerThread2 = new Thread(writerTask, "Writer2");
        Thread readerThread = new Thread(readerTask, "Reader");

        writerThread1.start();
        writerThread2.start();
        readerThread.start();
    }
}
