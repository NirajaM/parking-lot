package com.parking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot(3);
        Vehicle vehicle1 = new Vehicle(VehicleTypes.Car.name(), "res1", "white");
        Vehicle vehicle2 = new Vehicle(VehicleTypes.Car.name(), "res2", "black");
        Vehicle vehicle3 = new Vehicle(VehicleTypes.Car.name(), "res3", "black");
        parkingLot.parkVehicle(vehicle1);
        parkingLot.parkVehicle(vehicle2);
        parkingLot.parkVehicle(vehicle3);

    }

    @Test
    public void testRegistrationNumOfCarsByColorPos() {
        assertTrue(parkingLot.registrationNumOfCarsByColor("black").size() == 2);
    }

    @Test
    public void testRegistrationNumOfCarsByColorNeg() {
        assertNull(parkingLot.registrationNumOfCarsByColor("green"));
    }

    @Test
    public void testUnparkVehiclePos() {
        parkingLot.unparkVehicle(2);
        //assertTrue(parkingLot.().size() == 2);
   }

    //@Test
   public void testUnparkVehicleNeg() {
        parkingLot.unparkVehicle(2);
        //assertTrue(parkingLot.get.size() == 3);
    }

    @Test
    public void testSlotNumForRegistrationNumPos() {
        assertTrue(parkingLot.slotNumForRegistrationNum("res1") == 0);
    }

    @Test
    public void testSlotNumForRegistrationNumNeg() {
        assertTrue(parkingLot.slotNumForRegistrationNum("res") == -1);
    }

    @Test
    public void testSlotNumByColorPos() {
        assertTrue(parkingLot.slotNumsByColor("black").size() == 2);
    }

    @Test
    public void testSlotNumByColorNeg() {
        assertTrue(parkingLot.slotNumsByColor("red").size() == 0);
    }

    @Test
    public void testFreeSlotAfterUnparking() {
        assertTrue(parkingLot.unparkVehicle(2));
        assertTrue(parkingLot.getFirstFreeSlot() == 1);
    }
    
    @Test
    public void unparkingNeg() {
        assertFalse(parkingLot.unparkVehicle(5));
    }
}
