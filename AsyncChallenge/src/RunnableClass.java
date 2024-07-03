import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RunnableClass implements Runnable {
    public RunnableClass() throws IOException {
    }

//    WeatherApiRequest weatherApiRequest = new WeatherApiRequest();

    public void run(){
        System.out.println(Thread.currentThread().getName()
                + ", executing run() method!");
        System.out.println("Executing webpage api request.");
        System.out.println(getCurrentTemperature());
    }

    public static String getWeatherCurrentAndForecast(){
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
        String forecast = getWeatherCurrentAndForecast();
        String current = forecast.substring(338,342);
        return current;
    }
}
