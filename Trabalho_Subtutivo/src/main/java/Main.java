import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create a city object with latitude and longitude
        City city = new City("Brasilia", "-15.782073", "-47.88678");

        // Create an instance of NetworkingService
        NetworkingService networkingService = new NetworkingService();

        try {
            // Call GetWeatherData method to fetch and process weather data
            networkingService.GetWeatherData(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}