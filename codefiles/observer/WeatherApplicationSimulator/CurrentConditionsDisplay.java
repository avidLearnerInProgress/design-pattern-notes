package WeatherApplicationSimulator;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;
    
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    public void update(float temp, float hum, float pres) {
        this.temperature = temp;
        this.humidity = hum;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature 
                + "F degrees and " + humidity + "% humidity"); 
    }
    
}
