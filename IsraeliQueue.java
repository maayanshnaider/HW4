import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IsraeliQueue<E extends Cloneable> implements Iterable<E> {
    private Node<List<E>> head;
    private Node<List<E>> tail;
    private int size;

    public IsraeliQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(E newPerson, E friend) {
        if (newPerson == null || friend == null) {
            throw new InvalidInputException();
        }

        Node<List<E>> current = head;
        boolean friendFound = false;

        while (current != null) {
            List<E> group = current.getValue();
            if (group.contains(friend)) {
                group.add(newPerson);
                friendFound = true;
                break;
            }
            current = current.getNext();
        }

        if (!friendFound) {
            // Friend not found, create a new group at the end
            List<E> newGroup = new ArrayList<>();
            newGroup.add(newPerson);
            Node<List<E>> newNode = new Node<>(newGroup);
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.setNext(newNode);
                tail = newNode;
            }
        }
        size++;
    }

    public void add(E newPerson) {
        if (newPerson == null) {
            throw new InvalidInputException();
        }

        List<E> newGroup = new ArrayList<>();
        newGroup.add(newPerson);
        Node<List<E>> newNode = new Node<>(newGroup);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public E remove() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }

        List<E> group = head.getValue();
        E removedPerson = group.remove(0);
        size--;

        if (group.isEmpty()) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        }

        return removedPerson;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return head.getValue().get(0);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public IsraeliQueue<E> clone() {
        IsraeliQueue<E> clonedQueue = new IsraeliQueue<>();

        Node<List<E>> current = this.head;
        while (current != null) {
            List<E> group = current.getValue();
            List<E> newGroup = new ArrayList<>();
            for (E person : group) {
                E clonedPerson = cloneElement(person);
                newGroup.add(clonedPerson);
            }
            Node<List<E>> newNode = new Node<>(newGroup);
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

    private E cloneElement(E element) {
        if (element == null) {
            return null;
        }
        try {
            Method cloneMethod = element.getClass().getMethod("clone");
            return (E) cloneMethod.invoke(element);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // If cloning fails, return the original element
            // You might want to log this exception
            System.err.println("Failed to clone element: " + e.getMessage());
            return element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new IsraeliQueueIterator();
    }

    private class IsraeliQueueIterator implements Iterator<E> {
        private Node<List<E>> currentNode = head;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentNode != null && currentIndex >= currentNode.getValue().size()) {
                currentNode = currentNode.getNext();
                currentIndex = 0;
            }
            return currentNode != null;
        }

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
