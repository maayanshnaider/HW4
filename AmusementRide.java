public class AmusementRide {
    private String name;
    private IsraeliQueue<Person> queue;
    private final int maxCapacity;

    public AmusementRide(String name, int maxCapacity) {
        this.name = name;
        this.queue = new IsraeliQueue<>();
        this.maxCapacity = maxCapacity;
    }

    /**
     * this method starts the rides
     * first check if queue is empty; if so print massage
     * check which one is shorter- queue size or max capacity of the ride
     * remove that number of people from queue - these are the people going on the ride
     * @throws EmptyQueueException
     */
    public void startRide() throws EmptyQueueException {
        if (queue.isEmpty()) {
            System.out.println("Ride is empty.");
            return;
        }

        System.out.println("Currently using the ride:");
        int counter = Math.min(queue.size(), maxCapacity);
        for (int i = 0; i < counter; i++) {
            Person person = queue.remove();
            System.out.println(person.getName());
        }
    }

    public IsraeliQueue<Person> getQueue() {
        return queue;
    }
}


