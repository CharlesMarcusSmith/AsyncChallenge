import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        RunnableClass rc = new RunnableClass();
        Thread t = new Thread(rc);

        t.start();
        System.out.println("The thread has been started.");

        t.join();
        System.out.println("The thread has been completed.");
    }
}
