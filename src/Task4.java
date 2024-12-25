import java.util.concurrent.atomic.AtomicInteger;

public class Task4 {
    static class BankAccount {
        private AtomicInteger balance = new AtomicInteger(1000);

        public void deposit(int amount) {
            balance.addAndGet(amount);
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", Balance: " + balance.get());
        }

        public void withdraw(int amount) {
            if (balance.get() >= amount) {
                balance.addAndGet(-amount);
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", Balance: " + balance.get());
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient balance for withdrawal of: " + amount);
            }
        }

        public int getBalance() {
            return balance.get();
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                int amount = (int) (Math.random() * 200);
                if (Math.random() > 0.5) {
                    account.deposit(amount);
                } else {
                    account.withdraw(amount);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread client1 = new Thread(task, "Client1");
        Thread client2 = new Thread(task, "Client2");
        Thread client3 = new Thread(task, "Client3");

        client1.start();
        client2.start();
        client3.start();

        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
