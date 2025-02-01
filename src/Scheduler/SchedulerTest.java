package Scheduler;

import Drone.DroneSubsystem;
import FireIncident.FireEvent;
import org.junit.Before;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {
    //init
    private List<DroneSubsystem> drones;
    private Queue<FireEvent> fireEvents;

    @Before
    void SchedulerTest(){
        drones = new ArrayList<DroneSubsystem>();
        fireEvents = new LinkedList<FireEvent>();
    }


    @org.junit.jupiter.api.Test
    void trackTime() {

    }

    @org.junit.jupiter.api.Test
    void assignTask() {
    }

    @org.junit.jupiter.api.Test
    void findUrgentFireEvent() {
        //before adding
        assertNull(fireEvents, "Our fire events queue is not null.");
        //adding new


    }

    @org.junit.jupiter.api.Test
    void receiveCommand() {

    }

    @org.junit.jupiter.api.Test
    void receiveFireEvent() {
    }
}