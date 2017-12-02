package com.parking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 
 * @author nirmishra
 *
 */
public class ParkingLot {
    private static final Logger logger = Logger.getLogger(ParkingLot.class.getSimpleName());

    // Map to store color as key and vehicle details with slot num as value
    private static Map<String, Set<ParkedVehicle>> parkedVehiclesByColor = new HashMap<>();

    private ParkedVehicle[] slots;

    public ParkingLot(int numberOfSlots) {
        this.slots = new ParkedVehicle[numberOfSlots];
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
            logger.info("no slots free");
            return false;
        }
        ParkedVehicle parkedVehicle = new ParkedVehicle(vehicle, freeSlot);
        slots[freeSlot] = parkedVehicle;
        String color = vehicle.getColor();

        if (parkedVehiclesByColor.containsKey(color)) {
            parkedVehiclesByColor.get(color).add(parkedVehicle);

        } else {
            Set<ParkedVehicle> parkedVehicleSet = new HashSet<>();
            parkedVehicleSet.add(parkedVehicle);
            parkedVehiclesByColor.put(color, parkedVehicleSet);
        }
        logger.info("Vehicle parked");
        return true;
    }

    /**
     * UnPark vehicle for the particular slot
     * 
     * @param registrationId
     */
    public boolean unparkVehicle(int slotId) {
        if (slotId >= slots.length) {
            logger.info("No vehicle present with the slotId : " + slotId);
            return false;
        }
        ParkedVehicle vehicleToRemove = slots[slotId - 1];
        slots[slotId - 1] = null;
        parkedVehiclesByColor.get(vehicleToRemove.getVehicle().getColor()).remove(vehicleToRemove);
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
        Set<ParkedVehicle> parkedVehiclesSet = parkedVehiclesByColor.get(color);
        if (parkedVehiclesSet != null && parkedVehiclesSet.size() > 0) {
            regNums = new HashSet<>();
            for (ParkedVehicle parkedVeh : parkedVehiclesSet)
                regNums.add(parkedVeh.getVehicle().getRegistrationId());
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
        Set<ParkedVehicle> parkedVehiclesSet = parkedVehiclesByColor.get(color);
        if (parkedVehiclesSet != null && parkedVehiclesSet.size() > 0) {
            for (ParkedVehicle parkedVehicleDetails : parkedVehiclesSet)
                slotNums.add(parkedVehicleDetails.getSlot());
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
            if (parkedVehicle.getVehicle().getRegistrationId().equals(resNum))
                return parkedVehicle.getSlot();
        }
        return -1;

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