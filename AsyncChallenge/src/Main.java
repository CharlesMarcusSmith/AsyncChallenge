import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RunnableClass rc = new RunnableClass();
        Thread t = new Thread(rc);
        t.start();
        System.out.println("The thread has been started.");
    }
}
