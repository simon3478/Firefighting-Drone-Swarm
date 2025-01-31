package Scheduler;

/**
 * Represents the different tasks a drone can perform.
 */

public enum Task {
    TRAVEL_TO_FIRE,    // Move toward the fire zone
    DROP_AGENT,        // Release water/foam
    RETURN_TO_BASE,    // Go back to base after extinguishing
    REFILL_WATER,       // Refill firefighting agent
    HANDLE_EMERGENCY

}
