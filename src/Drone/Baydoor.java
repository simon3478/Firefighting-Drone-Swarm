package Drone;

/**
 * Handles nozzle/bay door operations
 */

public class Baydoor {
    private boolean baydoor;
    private boolean spraying;

    public Baydoor(boolean baydoor, boolean spraying) {
        this.baydoor = baydoor;
        this.spraying = spraying;
    }

    public void setBaydoor(boolean baydoor) {
        this.baydoor = baydoor;
    }

    public void setSpraying(boolean spraying) {
        this.spraying = spraying;
    }
}
