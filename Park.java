public class Park {
    private final String name;
    private Node<AmusementRide> head;
    private Node<AmusementRide> tail;
    private int rideCount;
    private static final int MAX_RIDES = 5;

    // Constructor
    public Park(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
        this.rideCount = 0;
    }

    // Add a ride to the park
    public void add(AmusementRide ride) {
        if (rideCount < MAX_RIDES) {
            Node<AmusementRide> newNode = new Node<>(ride);
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
            rideCount++;
        }
    }

    // Remove a ride from the park
    public void remove(AmusementRide ride) {
        Node<AmusementRide> current = head;
        Node<AmusementRide> previous = null;

        while (current != null) {
            if (current.getValue().equals(ride)) { // Uses overridden equals method
                if (previous == null) {
                    head = current.getNext();
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    previous.setNext(current.getNext());
                    if (current == tail) {
                        tail = previous;
                    }
                }
                rideCount--;
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }

    // Start all rides in the park
    public void startRides() throws EmptyQueueException {
        Node<AmusementRide> current = head;
        while (current != null) {
            current.getValue().startRide();
            current = current.getNext();
        }
    }

    // Add a person to a specific ride's queue
    public void addPerson(AmusementRide ride, Person person) throws InvalidInputException {
        Node<AmusementRide> current = head;
        while (current != null) {
            if (current.getValue().equals(ride)) { // Uses overridden equals method
                current.getValue().getQueue().add(person);
                return;
            }
            current = current.getNext();
        }
    }
}
