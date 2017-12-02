package com.parking;

public class Vehicle {
    private String registrationId;
    private String vehicleType;
    private String color;

    public Vehicle(String vehicleType, String registrationId, String color) {
        super();
        this.vehicleType = vehicleType;
        this.registrationId = registrationId;
        this.color = color;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
