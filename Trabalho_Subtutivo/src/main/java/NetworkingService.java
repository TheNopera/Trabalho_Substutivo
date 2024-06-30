import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingService {

    // Get weather data
    public void GetWeatherData(City city) throws IOException {
        try {
            // Creates a new URL based on the city
            HttpURLConnection con = getConnection(city);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                String[] tempStrings = getStrings(con);
                double[] temperatureArray = new double[tempStrings.length];
                double sumTemp = 0;

                for (int i = 0; i < tempStrings.length; i++) {
                    temperatureArray[i] = Double.parseDouble(tempStrings[i]);
                    sumTemp += temperatureArray[i];
                }

                // Calculate min, max, and mean temperature
                double minTemp = Double.MAX_VALUE;
                double maxTemp = Double.MIN_VALUE;

                for (double temp : temperatureArray) {
                    if (temp < minTemp) {
                        minTemp = temp;
                    }
                    if (temp > maxTemp) {
                        maxTemp = temp;
                    }
                }

                double meanTemp = sumTemp / temperatureArray.length;

                // Print the results
                System.out.println("City: " + city.getName());
                System.out.println("Minimum Temperature: " + minTemp + "°C");
                System.out.println("Maximum Temperature: " + maxTemp + "°C");
                System.out.println("Mean Temperature: " + meanTemp + "°C");
            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection getConnection(City city) throws IOException {
        String baseUrl = "https://archive-api.open-meteo.com/v1/archive?";
        URL url = new URL(baseUrl + "latitude=" + city.getLatitude() + "&longitude=" + city.getLongitude() + "&start_date=2024-01-01&end_date=2024-01-31&hourly=temperature_2m");

        // Creates new connection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Sets the method of the request to GET
        con.setRequestMethod("GET");
        return con;
    }

    private static String[] getStrings(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the JSON response manually
        String jsonResponse = response.toString();
        int startIndex = jsonResponse.indexOf("\"temperature_2m\":[") + "\"temperature_2m\":[".length();
        int endIndex = jsonResponse.indexOf("]", startIndex);
        String temperatures = jsonResponse.substring(startIndex, endIndex);

        // Split temperatures into an array of doubles
        return temperatures.split(",");
    }
}
