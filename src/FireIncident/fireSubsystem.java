package FireIncident;

// Imports 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Fire Incident subsystem is to read in events using the format shown above:
 * Time, Zone ID, Event Type and Severity. Each line of input is to be sent to the Scheduler
 */
public class fireSubsystem {

    // List of lines read from the zone file. 
    private ArrayList<String> zoneData;

    // List of lines read from the event file. 
    private ArrayList<String> eventData;

    /**
     * Constructor to initialize the subsystem with empty data.
     */
    public fireSubsystem() {
        this.zoneData = new ArrayList<>();
        this.eventData = new ArrayList<>();
    }

    /**
     * Reads the zone file into an ArrayList.
     *
     * @param filePath Path to the zone file.
     */
    public void readZoneFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                zoneData.add(line); // Add each line to the zoneData list
            }
            System.out.println("Zone File Contents:");
            for (String line : zoneData) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Zone file not found: " + e.getMessage());
        }
    }

    /**
     * Reads the event file into an ArrayList.
     *
     * @param filePath Path to the event file.
     */
    public void readEventFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                eventData.add(line); // Add each line to the eventData list
            }
            System.out.println("Event File Contents:");
            for (String line : eventData) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Event file not found: " + e.getMessage());
        }
    }

    // Getters

    /**
     * Gets zone data as an ArrayList.
     *
     * @return ArrayList containing lines from the zone file.
     */
    public ArrayList<String> getZoneData() {
        return new ArrayList<>(zoneData); // Returns a copy to maintain encapsulation
    }

    /**
     * Gets event data as an ArrayList.
     *
     * @return ArrayList containing lines from the event file.
     */
    public ArrayList<String> getEventData() {
        return new ArrayList<>(eventData); 
    }

    // Clear data

    /**
     * Clears zone data.
     */
    public void clearZoneData() {
        zoneData.clear();
    }

    /**
     * Clears event data.
     */
    public void clearEventData() {
        eventData.clear();
    }

    // SEND EVENTS TO SCHEDULER FUNCTION
    // CODE HERE ABDUALLAH


    /**
     * Main method to test IF THE CLASS WORKS.
     */
    public static void main(String[] args) {
        fireSubsystem subsystem = new fireSubsystem();

        // File paths
        String zoneFilePath = "src/Config/sample_zone_file.csv";
        String eventFilePath = "src/Config/Sample_event_file.csv";

        // Read files
        subsystem.readZoneFile(zoneFilePath);
        subsystem.readEventFile(eventFilePath);

        // Access the data using getters
        ArrayList<String> zoneData = subsystem.getZoneData();
        ArrayList<String> eventData = subsystem.getEventData();

        System.out.println("Retrieved Zone Data: " + zoneData);
        System.out.println("Retrieved Event Data: " + eventData);
    }
}

