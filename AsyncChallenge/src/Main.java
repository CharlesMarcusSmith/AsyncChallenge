import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Part One - Threads exercises:
        RunnableClass rc = new RunnableClass();
        Thread t = new Thread(rc);

        t.start();
        System.out.println("The thread has been started.");

        t.join();
        System.out.println("The thread has been completed.");

        // Part Two - Executors exercises:
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for(int i=0; i<5;i++){
            int finalI = i;
            executor.submit(() -> {
                System.out.println("Task " + finalI + ":");
                System.out.println(getCurrentTemperature());
            });
        }

        executor.shutdown();
    }

    public static String getForecast(){
        HttpClient client = HttpClient.newHttpClient();
        String jsonResponse = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.open-meteo.com/v1/"+"forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m"))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonResponse = response.body();
        } catch(Exception e) {
            // System.out.println(e);
        }
        return jsonResponse;
    }

    public static String getCurrentTemperature(){
        String forecast = getForecast();
        String current = forecast.substring(338,342);
        return current;
    }
}
