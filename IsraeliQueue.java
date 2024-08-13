import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IsraeliQueue<E extends Cloneable> implements Iterable<E> {
    private static class Node<E> {
        List<E> group;
        Node<E> next;

        Node() {
            this.group = new ArrayList<>();
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public IsraeliQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(E newPerson, E friend) {
        if (newPerson == null || friend == null) {
            throw new InvalidInputException("Input cannot be null");
        }

        if (head == null) {
            Node<E> newNode = new Node<>();
            newNode.group.add(newPerson);
            head = tail = newNode;
        } else {
            Node<E> current = head;
            while (current != null) {
                if (current.group.contains(friend)) {
                    current.group.add(newPerson);
                    break;
                }
                current = current.next;
            }

            if (current == null) {
                // Friend not found, create a new group at the end
                Node<E> newNode = new Node<>();
                newNode.group.add(newPerson);
                tail.next = newNode;
                tail = newNode;
            }
        }
        size++;
    }

    public void add(E newPerson) {
        if (newPerson == null) {
            throw new InvalidInputException("Input cannot be null");
        }

        Node<E> newNode = new Node<>();
        newNode.group.add(newPerson);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E remove() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }

        E removedPerson = head.group.remove(0); // Updated method call
        size--;

        if (head.group.isEmpty()) {
            head = head.next;
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
        return head.group.get(0); // Updated method call
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public IsraeliQueue<E> clone() {
        IsraeliQueue<E> clonedQueue = new IsraeliQueue<>();

        Node<E> current = this.head;
        while (current != null) {
            Node<E> newNode = new Node<>();
            for (E person : current.group) {
                E clonedPerson = cloneElement(person);
                if (clonedPerson != null) {
                    newNode.group.add(clonedPerson);
                    clonedQueue.size++;
                }
            }
            if (!newNode.group.isEmpty()) {
                if (clonedQueue.tail == null) {
                    clonedQueue.head = clonedQueue.tail = newNode;
                } else {
                    clonedQueue.tail.next = newNode;
                    clonedQueue.tail = newNode;
                }
            }
            current = current.next;
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
        private Node<E> current = head;
        private int groupIndex = 0;

        @Override
        public boolean hasNext() {
            return current != null && (groupIndex < current.group.size() || current.next != null);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }

            E element = current.group.get(groupIndex);
            groupIndex++;

            if (groupIndex >= current.group.size()) {
                current = current.next;
                groupIndex = 0;
            }

            return element;
        }
    }
}
