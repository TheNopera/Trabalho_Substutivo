/*This is the class responsible to execute all the API calls with one thread only*/


import java.io.IOException;

public class WeatherFetcher {
    //Instantiates the networking class
    NetworkingService networkingService = new NetworkingService();

    public WeatherFetcher(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    int numberOfThreads;

    public void ProcessData(City[] cities) throws InterruptedException {
        long startTime = System.currentTimeMillis();  // Record the start time for performance measurement

        // Create an array to hold the threads
        Thread[] threads = new Thread[numberOfThreads];
        final int citiesPerThread = cities.length / threads.length;  // Calculate the number of cities each thread will handle

        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    // Each thread processes its assigned subset of cities
                    for (int j = index * citiesPerThread; j < (index + 1) * citiesPerThread; j++) {
                        try {
                            for(int i = 0; i < 10; i++)networkingService.GetWeatherData(CityData.getCities()[j]);  // Get weather data for the city 10 times
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threads[i].start();  // Start the thread
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();  // Record the end time for performance measurement
        long duration = endTime - startTime;  // Calculate the duration
        System.out.println("Execution time for 3 threads: " + duration + " ms");  // Print the execution time
    }
}
