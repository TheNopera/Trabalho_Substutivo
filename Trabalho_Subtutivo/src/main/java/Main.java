//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //CREATE EXPERIMENTS
        WeatherFetcher experiment1 = new WeatherFetcher(1);
        WeatherFetcher experiment2 = new WeatherFetcher(3);
        WeatherFetcher experiment3 = new WeatherFetcher(9);
        WeatherFetcher experiment4 = new WeatherFetcher(27);

        /* Uncomment to run different experiments, running all at once will make API
            return 429 (too many requests)*/

        experiment4.ProcessData(CityData.getCities());
//        experiment2.ProcessData(CityData.getCities());
//        experiment3.ProcessData(CityData.getCities());
//        experiment4.ProcessData(CityData.getCities());
    }
}