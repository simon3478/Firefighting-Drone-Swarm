package Drone;

import Scheduler.scheduler;
import Scheduler.Task;

/**
 * Drones will make calls to the Scheduler which will then reply when there is work to
 * be done. The Drone will then send the data back to the Scheduler who will then send
 * it back to the Fire Incident subsystem
 */

public class droneSubsystem {
    private int battery;
    private int WaterLevel;
    private int xCord;
    private int yCord;
    statusLight statusLight;

    /**
     * Constructor for the droneSubsystem class
     *
     * @param battery the charge of the drone's battery
     * @param WaterLevel the amount of water it's currently holding
     * @param xCord the x coordinates for the drone
     * @param yCord the y coordinates for the drone
     * @param statusLight the status of the drone's current actions
     */
    public droneSubsystem(int battery, int WaterLevel, int xCord, int yCord, statusLight statusLight) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.battery = battery;
        this.WaterLevel = WaterLevel;
        this.statusLight = statusLight;
        Baydoor baydoor = new Baydoor(false, false);
    }

    /**
     * Receives a task for the drone.
     * It will set the status light for that drone accordingly.
     * It will set the xCord and yCord
     *
     * @param task the task that the drone is required to do
     */
    public synchronized void receiveMessage(Task task, int xCord, int yCord, Baydoor baydoor) {
        switch (task) {
            case TRAVEL_TO_FIRE:
                flyToLocation(xCord, yCord);
                break;
            case DROP_AGENT:
                baydoor.setBaydoor(true);
                baydoor.setSpraying(true);
                break;
            case RETURN_TO_BASE:
                baydoor.setSpraying(false);
                baydoor.setBaydoor(false);
                flyToLocation(xCord, yCord);
                break;
        }
        notifyAll();
    }

    /**
     * The message to be sent to the scheduler
     *
     * @param task that the drone finished
     */
    public synchronized void sendMessage(Task task) {
        scheduler.receiveMessage(task);
        notifyAll();
    }

    public void flyToLocation(int xCord, int yCord) {
        setxCord(xCord);
        setyCord(yCord);
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getWaterLevel() {
        return WaterLevel;
    }

    public void setWaterLevel(int WaterLevel) {
        this.WaterLevel = WaterLevel;
    }

    public statusLight getStatusLight() {
        return statusLight;
    }

    public void setStatusLight(statusLight statusLight) {
        this.statusLight = statusLight;
    }
}
