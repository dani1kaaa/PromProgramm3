package BDV1_LAB3;

public class Weather {
    int id;           
    String nameCity;  
    String Temperature;   

    public Weather() {
        this.id = 0;
        this.nameCity = "";
        this.Temperature = "";
    }

    public Weather(String nameCity, String Temperature) {
        this.id = 0;
        this.nameCity = nameCity;
        this.Temperature = Temperature;
    }

    public Weather(String nameCity) {
        this.id = 0;
        this.nameCity = nameCity;
        
    }

    public int getId() {
        return id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public String getTemperature() {
        return Temperature;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public void setTemperature(String Temperature) {
        this.Temperature = Temperature;
    }


    @Override
    public String toString() {
        return String.format("Название города - %s, Температура - %s", nameCity, Temperature);
    }
}
