package WeatherApplicationSimulatorV2;

import java.utils.ArrayList;
import java.utils.Observable; // builtin api
import java.utils.Observer; // builtin api

public class WeatherData extends Observable {
    // private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherData() {
        // observers = new ArrayList();
    }
    
    public void measurementsChanged() {
        setChanged(); // to ensure that the state has changed
        notifyObservers(); //pull method
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }
    
    public float getHumidity() {
        return humidity;
    }
    
    public float getPressure() {
        return pressure;
    }
}
