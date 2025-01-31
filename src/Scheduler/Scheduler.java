package Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.time.LocalTime;
import Drone.DroneSubsystem;
import FireIncident.*;
import Config.*;


public class Scheduler {
    private List<DroneSubsystem> listOfDrones;
    private Queue<FireEvent> eventQueue;

    public Scheduler() {
        this.listOfDrones = new ArrayList<>();
        this.eventQueue = new LinkedList<>();
    }

    public LocalTime trackTime() {
        return LocalTime.now();
    }

    public void assignTask(Task task, Zone zone) {
        for (DroneSubsystem drone : listOfDrones) {
            if (drone.isAvailable() && ((Integer) drone.getBatteryLevel()) > Config.MIN_BATTERY_REQUIRED) {
                drone.receiveTask(task, zone.getCoordinates());
            }

        }
        System.out.println("No available drones for zone " + zone.getZoneID());
    }

    public FireEvent findUrgentFireEvent() {
        if (eventQueue.isEmpty()) {
            return null;
        }
        FireEvent nextEvent = eventQueue.peek(); // Next in line
        for (FireEvent event : eventQueue) {
            if (event.getSeverity() > nextEvent.getSeverity()) {
                nextEvent = event;
            }
        }
        return nextEvent;
    }

    public void receiveCommand(Task task, DroneSubsystem drone) {
        System.out.println("Received command " + task + " from drone " + drone.getId());
        if (task == Task.HANDLE_EMERGENCY) {
            System.out.println("Handling emergency for drone " + drone.getId());
            assignTask(Task.RETURN_TO_BASE, new Zone(0, 0, 0)); // Sending drone to base
        }
    }

    public void receiveFireEvent(FireEvent event) {
        eventQueue.offer(event);
        assignTask(Task.TRAVEL_TO_FIRE, event);
    }
}
