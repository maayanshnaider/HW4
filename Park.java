import java.util.ArrayList;

public class Park {
    private String name;
    private ArrayList<AmusementRide> rides;
    private int rideCount;
    private static final int MAX_RIDES = 5;

    public Park(String name) {
        this.name = name;
        this.rides = new ArrayList<AmusementRide>();
        this.rideCount = 0;
    }

    public void add(AmusementRide ride) {
        if (rideCount < MAX_RIDES) {
            rides.set(rideCount++, ride);
        }
    }

    public void remove(AmusementRide ride) {
        for (int i = 0; i < rideCount; i++) {
            if (rides.get(i) == ride) {
                rides.remove(i);
                --rideCount;
                return;
            }
        }
    }

    public void startRides() throws EmptyQueueException {
        for (int i = 0; i < rideCount; i++) {
            rides.get(i).startRide();
        }
    }

    public void addPerson(AmusementRide ride, Person person) throws InvalidInputException {
        for (int i = 0; i < rideCount; i++) {
            if (rides.get(i) == ride) {
                rides.get(i).getQueue().add(person);
                return;
            }
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}