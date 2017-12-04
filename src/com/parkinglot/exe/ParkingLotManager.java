package com.parkinglot.exe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.parking.ParkingLot;
import com.parking.Vehicle;
import com.parking.VehicleTypes;

public class ParkingLotManager {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }
        File file = new File(args[0]);
        BufferedReader buf = new BufferedReader(new FileReader(file));
        String line;
        ParkingLot parkingLot = null;
        while ((line = buf.readLine()) != null) {

            String[] stArr = line.split("\t");
            if (stArr.length >= 1) {
                if (stArr[0].equalsIgnoreCase(Constants.CREATE_PARKING_LOT)) {
                    parkingLot = new ParkingLot(Integer.valueOf(stArr[1]));
                } else if (stArr[0].equals(Constants.PARK)) {
                    Vehicle vehicle = new Vehicle(VehicleTypes.Car.name(), stArr[1], stArr[2]);
                    parkingLot.parkVehicle(vehicle);
                } else if (stArr[0].equals(Constants.LEAVE)) {
                    parkingLot.unparkVehicle(Integer.valueOf(stArr[1]));
                } else if (stArr[0].equals(Constants.STATUS)) {
                    parkingLot.status();
                } else if (stArr[0].equalsIgnoreCase(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR)) {
                    parkingLot.registrationNumOfCarsByColor(stArr[1]);
                } else if (stArr[0].equalsIgnoreCase(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR)) {
                    parkingLot.slotNumsByColor(stArr[1]);
                } else if (stArr[0].equalsIgnoreCase(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER)) {
                    parkingLot.slotNumForRegistrationNum(stArr[1]);
                }

            }
        }
        buf.close();

    }
}
