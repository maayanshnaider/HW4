public class Park {
    private String name;
    private AmusementRide[] rides;
    private int rideCount;
    private static final int MAX_RIDES = 5;

    public Park(String name) {
        this.name = name;
        this.rides = new AmusementRide[MAX_RIDES];
        this.rideCount = 0;
    }

    public void add(AmusementRide ride) {
        if (rideCount < MAX_RIDES) {
            rides[rideCount++] = ride;
        }
    }

    public void remove(AmusementRide ride) {
        for (int i = 0; i < rideCount; i++) {
            if (rides[i] == ride) {
                System.arraycopy(rides, i + 1, rides, i, rideCount - i - 1);
                rides[--rideCount] = null;
                return;
            }
        }
    }

    public void startRides() throws EmptyQueueException {
        for (int i = 0; i < rideCount; i++) {
            rides[i].startRide();
        }
    }

    public void addPerson(AmusementRide ride, Person person) throws InvalidInputException {
        for (int i = 0; i < rideCount; i++) {
            if (rides[i] == ride) {
                rides[i].getQueue().add(person);
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