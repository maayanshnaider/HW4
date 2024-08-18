public class Park {
    private final String name;
    private Node<AmusementRide> head;
    private Node<AmusementRide> tail;
    private int NumOfRides;
    private static final int MaxNumRides = 5;

    /**
     * constructor for the park object that has attributes of name, head, tail and number of rides currently in the park
     * @param name
     */
    public Park(String name) {
        this.name = name;
        this.head = null;
        this.tail = null;
        this.NumOfRides = 0;
    }

    /**
     * a method that adds a ride to the park if the current number of rides is less
     * than the max number of rides allowed in the park which is 5, also it adds one to the current number of rides in the park
     * @param ride
     */
    public void addRide(AmusementRide ride) {
        if (NumOfRides < MaxNumRides) {
            Node<AmusementRide> newNode = new Node<>(ride);
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
            NumOfRides++;
        }
    }

    /**
     * a method that removes a ride from the park if the park is not empty of rides and subtracts the number of rides by one
     * @param ride
     */
    public void removeRide(AmusementRide ride) {
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
                NumOfRides--;
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }

    /**
     *the startRides method goes over all the rides that are currently in the park and starts them.
     *  if the park is empty it throws an exception.
     * @throws EmptyQueueException
     */
    public void startRides() throws EmptyQueueException {
        Node<AmusementRide> current = head;
        while (current != null) {
            current.getValue().startRide();
            current = current.getNext();
        }
    }

    /**
     *this method adds a specific person to a ride. the method goes through the rides in the park and checks if the ride that person wants to go to is in the park by checking if the rides are equal,
     * if the ride is in the park it adds the person to the queue using add method in IsraeliQueue class.
     * if the ride is not in the park it throws an exception.
     *
     * @param ride
     * @param person
     * @throws InvalidInputException
     */
    public void addPersonToRide(AmusementRide ride, Person person) throws InvalidInputException {
        Node<AmusementRide> current = head;
        while (current != null) {
            if (current.getValue().equals(ride)) {
                current.getValue().getQueue().add(person);
                return;
            }
            current = current.getNext();
        }
    }
}
