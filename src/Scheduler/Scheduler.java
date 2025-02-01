package Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.time.LocalTime;
import Drone.*;
import FireIncident.*;
import Config.Config;


public class Scheduler {
    private final List<DroneSubsystem> listOfDrones;
    private final Queue<FireEvent> eventQueue;
    private DroneSubsystem droneToBeRecalled; //  a drone that needs to be recalled


    public Scheduler() {
        this.listOfDrones = new ArrayList<>();
        this.eventQueue = new LinkedList<>();
        droneToBeRecalled=null;
    }

    /**
     * Returns the current system time.
     */
    public LocalTime trackTime() {
        return LocalTime.now();
    }

    /**
     * Assigns a task if  drone is available.
     * If not it waits.
     */
    public synchronized void assignTask(Task task, Zone zone) {
        // If it's not recalling action, find an available drone
        if (task != Task.RETURN_TO_BASE && task != Task.HANDLE_EMERGENCY) {
            //drone unavaiable
            while (listOfDrones.isEmpty() || !isAnyDroneAvailable()) {
                try {
                    System.out.println("[" + trackTime() + "] No available drones. Waiting...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("[" + trackTime() + "] assignTask interrupted while waiting.");
                    return;
                }
            }

            // if drone is available
            for (DroneSubsystem drone : listOfDrones) {
                if (drone.isAvailable() && (Integer) drone.getBatteryLevel() > Config.MIN_BATTERY_REQUIRED) {
                    drone.receiveTask(task, zone.getCoordinates());
                    System.out.println("[" + trackTime() + "] Assigned task: " + task + " to drone " + drone.getId());
                    notifyAll();
                    return;
                }
            }
        }

        else {
            if (droneToBeRecalled != null) {
                droneToBeRecalled.receiveTask(task, zone.getCoordinates());
                System.out.println("[" + trackTime() + "] Recalling drone " + droneToBeRecalled.getId() + " to base.");
                droneToBeRecalled = null;
            } else {
                System.out.println("[" + trackTime() + "] Error: No drone to recall.");
            }
        }
    }



    /**
     * Receives a fire event and call asignTask to the severe one.
     */
    public synchronized void receiveFireEvent(FireEvent event) {
        eventQueue.add(event);
        System.out.println("[" + trackTime() + "] Received fire event in zone " + event.getZoneID() +
                " with severity " + event.getSeverity());
        FireEvent urgentEvent = findUrgentFireEvent();
        if (urgentEvent != null) {
            assignTask(Task.TRAVEL_TO_FIRE, urgentEvent);
        }

    }


    /**
     * Finds and removes the most urgent fire event from the queue.
     */
    public synchronized FireEvent findUrgentFireEvent() {
        if (eventQueue.isEmpty()) {
            return null;
        }

        FireEvent mostUrgentEvent = null;
        FireEvent toRemove = null;

        for (FireEvent event : eventQueue) {
            if (mostUrgentEvent == null || event.getSeverity() > mostUrgentEvent.getSeverity()) {
                mostUrgentEvent = event;
                toRemove = event;
            }
        }

        if (toRemove != null) {
            eventQueue.remove(toRemove);
        }

        return mostUrgentEvent;
    }


    /**
     * Receives a command from a drone like malfunctioning.
     */
    public synchronized void receiveCommand(Task task, DroneSubsystem drone) {
        System.out.println("[" + trackTime() + "] Received command " + task + " from drone " + drone.getId());

        if (task == Task.HANDLE_EMERGENCY) {
            System.out.println("[" + trackTime() + "] Handling emergency for drone " + drone.getId());
            droneToBeRecalled = drone;
            assignTask(Task.RETURN_TO_BASE, new Zone(0, 0, 0));
        }
    }


    /**
     * Adds a new drone to the scheduler and notifies waiting tasks.
     */
    public synchronized void addDrone(DroneSubsystem drone) {
        listOfDrones.add(drone);
        System.out.println("[" + trackTime() + "] New drone added: " + drone.getId());
        notifyAll();
    }

    /**
     * Checks if there is any available drone.
     */
    private boolean isAnyDroneAvailable() {
        for (DroneSubsystem drone : listOfDrones) {
            if (drone.isAvailable() && (int) drone.getBatteryLevel() > Config.MIN_BATTERY_REQUIRED) {
                return true;
            }
        }
        return false;
    }
}
