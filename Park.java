public class Park {
    private final String name;
    private Node head;
    private Node tail;
    private int rideCount;
    private static final int MAX_RIDES = 5;

    // Private inner Node class
    private static class Node {
        AmusementRide ride;
        Node next;

        Node(AmusementRide ride) {
            this.ride = ride;
            this.next = null;
        }
    }

    public Park(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
        this.rideCount = 0;
    }

    public void add(AmusementRide ride) {
        if (rideCount < MAX_RIDES) {
            Node newNode = new Node(ride);
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            rideCount++;
        }
    }

    public void remove(AmusementRide ride) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.ride == ride) {
                if (previous == null) {
                    head = current.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                }
                rideCount--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public void startRides() throws EmptyQueueException {
        Node current = head;
        while (current != null) {
            current.ride.startRide();
            current = current.next;
        }
    }

    public void addPerson(AmusementRide ride, Person person) throws InvalidInputException {
        Node current = head;
        while (current != null) {
            if (current.ride == ride) {
                current.ride.getQueue().add(person);
                return;
            }
            current = current.next;
        }
    }
}
