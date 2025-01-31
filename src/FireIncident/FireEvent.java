package FireIncident;

import java.util.AbstractMap;
import java.util.AbstractMap;

public class FireEvent extends Zone {
    private int severity; // Add a severity field

    public FireEvent(int zoneID, int x, int y, int severity) {
        super(zoneID, x, y);
        this.severity = severity;
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getCoordinates() {
        return getLocation(); // Get from superclass (Zone)
    }

    @Override
    public int getZoneID() {
        return super.getZoneID(); // Get from superclass
    }

    public int getSeverity() {
        return severity; // Return actual severity value
    }
}
