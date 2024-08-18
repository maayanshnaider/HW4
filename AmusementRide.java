public class AmusementRide {
    private String name;
    private IsraeliQueue<Person> queue;
    private int maxCapacity;

    public AmusementRide(String name, int maxCapacity) {
        this.name = name;
        this.queue = new IsraeliQueue<>();
        this.maxCapacity = maxCapacity;
    }

    public void startRide() throws EmptyQueueException {
        if (queue.isEmpty()) {
            System.out.println("Ride is empty.");
            return;
        }

        System.out.println("Currently using the ride:");
        int rideCount = Math.min(queue.size(), maxCapacity);
        for (int i = 0; i < rideCount; i++) {
            Person person = queue.remove();
            System.out.println(person.getName());
        }
    }

    // Getters and setters

    public IsraeliQueue<Person> getQueue() {
        return queue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same memory location
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this == obj; // Check if it's the same instance
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this); // Use identity hash code
    }
}
