/**
 * 
 */
package com.parking;

/**
 * @author nirmishra
 *
 */
public class ParkedVehicle {

    private Vehicle vehicle;
    private int slot;
    public ParkedVehicle(Vehicle vehicle, int slot) {
        super();
        this.vehicle = vehicle;
        this.slot = slot;
    }
    /**
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }
    /**
     * @param slot the slot to set
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }
    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }
    /**
     * @param vehicle the vehicle to set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    
}
