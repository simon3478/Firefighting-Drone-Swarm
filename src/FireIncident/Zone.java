package FireIncident;

import java.util.AbstractMap.SimpleEntry;

public class Zone {
    private int zoneID;
    private SimpleEntry<Integer, Integer> location; // Tuple (x, y)

    // Constructor
    public Zone(int zoneID, int x, int y) {
        this.zoneID = zoneID;
        this.location = new SimpleEntry<>(x, y);
    }

    // Getters
    public int getZoneID() {
        return zoneID;
    }

    public SimpleEntry<Integer, Integer> getLocation() {
        return location;
    }

    public SimpleEntry getCoordinates() {
        return location;
    }
}

