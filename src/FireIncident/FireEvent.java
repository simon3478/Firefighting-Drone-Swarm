package FireIncident;

import java.util.AbstractMap;


public class FireEvent extends Zone {
    private int severity;

    public FireEvent(int zoneID, int x, int y, int severity) {
        super(zoneID, x, y);
        this.severity = severity;
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getCoordinates() {
        return getLocation();
    }

    @Override
    public int getZoneID() {
        return super.getZoneID();
    }

    public int getSeverity() {
        return severity;
    }
}
