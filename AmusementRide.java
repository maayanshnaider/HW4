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
            System.out.println(person);
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IsraeliQueue<Person> getQueue() {
        return queue;
    }

    public void setQueue(IsraeliQueue<Person> queue) {
        this.queue = queue;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}