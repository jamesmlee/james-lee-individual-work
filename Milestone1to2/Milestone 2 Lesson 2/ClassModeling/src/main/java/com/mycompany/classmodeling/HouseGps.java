/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classmodeling;

/**
 *
 * @author James
 */
public class HouseGps {
    private String address;
    private float latitude;
    private float longitude;
    private float altitude;
    private String country;

// get rid of setters? all the properties are read-only?    
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


// behaviors    
    public void hideItself() {
        
    }

    public void highlightOnClick() {
        
    }
    
    public void navigateTo() {
        
    }
    
    public void navigateFrom() {
        
    }
    
    public void saveToAddressBook(String address, String nickname) {
        
    }
    
}
