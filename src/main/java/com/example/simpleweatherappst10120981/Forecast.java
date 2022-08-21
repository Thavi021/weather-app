package com.example.simpleweatherappst10120981;

public class Forecast {

    private String fMaxTemp;
    private String fMinTemp;
    private String date;

    public String getfMaxTemp(){
        return fMaxTemp;
    }
    public void setfMaxTemp(String fMaxTemp){
        this.fMaxTemp = fMaxTemp;
    }

    public String getfMinTemp(){
        return fMinTemp;
    }
    public void setfMinTemp(String fMinTemp){
        this.fMinTemp = fMinTemp;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
}
