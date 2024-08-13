import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

        E removedPerson = head.group.removeFirst();
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
        return head.group.getFirst();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public IsraeliQueue<E> clone() {
        try {
            IsraeliQueue<E> clonedQueue = (IsraeliQueue<E>) super.clone();
            clonedQueue.head = null;
            clonedQueue.tail = null;
            clonedQueue.size = 0;

            Node<E> current = this.head;
            while (current != null) {
                for (E person : current.group) {
                    E clonedPerson = cloneElement(person);
                    clonedQueue.add(clonedPerson);
                }
                current = current.next;
            }

            return clonedQueue;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    private E cloneElement(E element) {
        try {
            Method cloneMethod = element.getClass().getMethod("clone");
            return (E) cloneMethod.invoke(element);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<E> {
        private Node<E> currentNode = head;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentNode != null &&
                    (currentIndex < currentNode.group.size() || currentNode.next != null);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            if (currentIndex >= currentNode.group.size()) {
                currentNode = currentNode.next;
                currentIndex = 0;
            }

            return currentNode.group.get(currentIndex++);
        }
    }
}