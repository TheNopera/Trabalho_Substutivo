/*This is the class responsible to execute all the API calls with one thread only*/


import java.io.IOException;

public class WeatherFetcher1 {
    //Instantiates the networking class
    NetworkingService networkingService = new NetworkingService();

    public void ProcessData(City[] cities){
        //Star time
        long startTime = System.currentTimeMillis();

        // iterate over cities
        for (City capital : cities) {
            try {
                for (int i = 0; i < 10; i++) {
                    networkingService.GetWeatherData(capital);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //End time
        long endTime = System.currentTimeMillis();

        System.out.println("EXPERIMENT 1: " + (endTime-startTime) + "ms"); // OVERALL TIME
    }
}
