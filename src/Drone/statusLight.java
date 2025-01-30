package Drone;

/**
 * Enum representing each type of status a drone can have.
 *
 * Flying - Indicates drone flying towards fire incident.
 * Approach - Indicates drone nearing its target area.
 * Dropping agent - Indicates drone taking out fire.
 * Returning - Indicates drone returning to base for refill.
 * Refilling - Indicates drone is actively refilling its resources.
 * Fault - Indicates the drone has a fault and returning to base.
 *
 */

public enum statusLight {
    FLYING, APPROACH, DROPPING_AGENT, RETURNING, REFILLING, FAULT
}
