package com.parking;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author nirmishra
 *
 */
public class ParkingLot {

    // Map to store color as key and vehicle details with slot num as value
    private static Map<String, Set<ParkedVehicle>> parkedVehiclesByColor = new HashMap<>();
    private PrintStream out = null;

    private ParkedVehicle[] slots;

    public ParkingLot(int numberOfSlots) throws FileNotFoundException {
        out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        this.slots = new ParkedVehicle[numberOfSlots];
        System.out.println("Created   a   parking   lot   with " + numberOfSlots);

    }

    /**
     * Park vehicle if slots are available
     * 
     * @param vehicle
     * @return
     */
    public boolean parkVehicle(Vehicle vehicle) {
        int freeSlot = getFirstFreeSlot();
        if (freeSlot == -1) {
            System.out.println("Sorry,   parking   lot   is   full ");
            return false;
        }
        freeSlot = getFirstFreeSlot() + 1;
        ParkedVehicle parkedVehicle = new ParkedVehicle(vehicle, freeSlot);
        slots[freeSlot - 1] = parkedVehicle;
        String color = vehicle.getColor();

        if (parkedVehiclesByColor.containsKey(color)) {
            parkedVehiclesByColor.get(color).add(parkedVehicle);

        } else {
            Set<ParkedVehicle> parkedVehicleSet = new HashSet<>();
            parkedVehicleSet.add(parkedVehicle);
            parkedVehiclesByColor.put(color, parkedVehicleSet);
        }
        System.out.println("Allocated   slot   number:  " + freeSlot);
        return true;
    }

    /**
     * UnPark vehicle for the particular slot
     * 
     * @param registrationId
     */
    public boolean unparkVehicle(int slotId) {
        if (slotId >= slots.length) {
            System.out.println("No vehicle present with the slotId : " + slotId);
            return false;
        }
        ParkedVehicle vehicleToRemove = slots[slotId - 1];
        slots[slotId - 1] = null;
        parkedVehiclesByColor.get(vehicleToRemove.getVehicle().getColor()).remove(vehicleToRemove);
        System.out.println("Slot   number " + slotId + "  is   free");
        return true;
    }

    /**
     * returns set of registration numbers of the cars for the specific color
     * 
     * @param color
     * @return
     */
    public Set<String> registrationNumOfCarsByColor(String color) {
        Set<String> regNums = null;
        int index = 0;
        Set<ParkedVehicle> parkedVehiclesSet = parkedVehiclesByColor.get(color);
        if (parkedVehiclesSet != null && parkedVehiclesSet.size() > 0) {
            regNums = new HashSet<>();
            for (ParkedVehicle parkedVeh : parkedVehiclesSet) {
                Vehicle vehicle = parkedVeh.getVehicle();
                regNums.add(vehicle.getRegistrationId());
                System.out.print(vehicle.getRegistrationId());
                if (index++ < parkedVehiclesSet.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        return regNums;
    }

    /**
     * return set of slot numbers of the vehicle for the specific color
     * 
     * @param color
     * @return
     */
    public Set<Integer> slotNumsByColor(String color) {
        Set<Integer> slotNums = new HashSet<>();
        int index = 0;
        Set<ParkedVehicle> parkedVehiclesSet = parkedVehiclesByColor.get(color);
        if (parkedVehiclesSet != null && parkedVehiclesSet.size() > 0) {
            for (ParkedVehicle parkedVehicleDetails : parkedVehiclesSet) {
                slotNums.add(parkedVehicleDetails.getSlot());
                System.out.print(parkedVehicleDetails.getSlot());
                if (index++ < parkedVehiclesSet.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        return slotNums;

    }

    /**
     * get the slot num for the registration num
     * 
     * @param resNum
     * @return
     */
    public int slotNumForRegistrationNum(String resNum) {
        for (ParkedVehicle parkedVehicle : slots) {
            if (parkedVehicle.getVehicle().getRegistrationId().equals(resNum)) {
                System.out.println(parkedVehicle.getSlot());
                return parkedVehicle.getSlot();
            }
        }
        System.out.println("Not found");
        return -1;

    }

    /**
     * to get to know the status of the parking lot
     * 
     * @param resNum
     * @return
     */
    public void status() {
        System.out.println("Slot No.   Registration No    Color");
        for (ParkedVehicle parkedVehicle : slots) {
            if (parkedVehicle == null)
                continue;
            Vehicle vehicle = parkedVehicle.getVehicle();

            System.out
                    .println(parkedVehicle.getSlot() + "\t" + vehicle.getRegistrationId() + "\t" + vehicle.getColor());
        }
    }

    /**
     * 
     * @return
     */
    public int getFirstFreeSlot() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null)
                return i;
        }
        return -1;
    }

}