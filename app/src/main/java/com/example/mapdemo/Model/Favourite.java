package com.example.mapdemo.Model;


import com.google.api.client.util.Key;

public class Favourite {
private String latitude;
private String longitude;
private String city;
private String state;
private String pincode;
private String id;

    public Favourite() {
    }



    public Favourite(String lat, String lng, String city, String state, String pincode) {
        this.latitude=lat;
        this.longitude=lng;
        this.city=city;
        this.state=state;
        this.pincode=pincode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
