import java.util.Iterator;

public class IsraeliQueue<E extends Cloneable> implements Iterable<E> {
    private Node<MyLinkedList<E>> head;
    private Node<MyLinkedList<E>> tail;
    private int size;

    /**
     * a constructor to give israeliQueue its attributes: head, tail and size
     */
    public IsraeliQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * the add method adds a person to the queue. if the new person has a friend in the queue, it calls the second add method.
     * if he doesn't have a friend, he will be added to the end of the queue and form a new group of his own.
     * when a new person is added ot the list the size of the IsraeliQueue is increased by one.
     * if a null person is being added to the list, an exception is thrown.
     * @param newPerson
     */
    public void add(E newPerson) {
        if (newPerson == null) {
            throw new InvalidInputException();
        }

        if (newPerson instanceof Person && ((Person) newPerson).hasFriend()) {
            Person friend = ((Person) newPerson).getFriend();
            add(newPerson, (E) friend);
        } else {
            MyLinkedList<E> newGroup = new MyLinkedList<>();
            newGroup.add(newPerson);
            Node<MyLinkedList<E>> newNode = new Node<>(newGroup);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
            size++;
        }
    }

    /**
     * if the first add method calls this second add method, it gets the new person and his friend and adds the new person to the end
     * of his friends group. if his friend is not found in the group, the new person will be added to the end of the queue.
     * if a null person is being added to the list, an exception is thrown.
     * when a new person is added ot the list the size of the IsraeliQueue is increased by one.
     * @param newPerson
     * @param friend
     */
    public void add(E newPerson, E friend) {
        if (newPerson == null || friend == null) {
            throw new InvalidInputException();
        }

        Node<MyLinkedList<E>> current = head;
        boolean friendFound = false;

        while (current != null) {
            MyLinkedList<E> group = current.getValue();
            if (group.contains(friend)) {
                group.add(newPerson);
                friendFound = true;
                break;
            }
            current = current.getNext();
        }

        if (!friendFound) {
            // Friend not found, create a new group at the end
            MyLinkedList<E> newGroup = new MyLinkedList<>();
            newGroup.add(newPerson);
            Node<MyLinkedList<E>> newNode = new Node<>(newGroup);
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
        }
        size++;
    }

    /**
     *this method removes a person from the queue if the queue is not empty, and decreases the size of the list by one
     * @return
     */
    public E remove() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        MyLinkedList<E> group = head.getValue();
        E removedPerson = group.remove();
        size--;

        if (group.isEmpty()) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        }

        return removedPerson;
    }

    /**
     *this method returns the first person currently in the IsraeliQueue if the queue is not empty
     * @return
     */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return head.getValue().get(0);
    }

    /**
     *check if the queue is empty
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *creating a deep copy of the queue
     * @return
     */
    public IsraeliQueue<E> clone() {
        IsraeliQueue<E> clonedQueue = new IsraeliQueue<>();
        Node<MyLinkedList<E>> current = head;
        while (current != null) {
            MyLinkedList<E> group = current.getValue();
            MyLinkedList<E> newGroup = group.clone();
            Node<MyLinkedList<E>> newNode = new Node<>(newGroup);
            if (clonedQueue.tail == null) {
                clonedQueue.head = clonedQueue.tail = newNode;
            } else {
                clonedQueue.tail.setNext(newNode);
                clonedQueue.tail = newNode;
            }
            current = current.getNext();
        }
        return clonedQueue;
    }

    /**
     *an iterator fo IsraeliQueue
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new IsraeliQueueIterator();
    }


    private class IsraeliQueueIterator implements Iterator<E> {
        private Node<MyLinkedList<E>> currentNode = head;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentNode != null && currentIndex >= currentNode.getValue().size()) {
                currentNode = currentNode.getNext();
                currentIndex = 0;
            }
            return currentNode != null;
        }

        /**
         * goes through the queue and check if there are more elements in the next place in the list
         * @return
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }

            E element = currentNode.getValue().get(currentIndex);
            currentIndex++;
            return element;
        }
    }
}
