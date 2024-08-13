import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IsraeliQueue<E extends Cloneable> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public IsraeliQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(E newPerson, E friend) throws InvalidInputException {
        if (newPerson == null || friend == null) {
            throw new InvalidInputException("Input cannot be null");
        }
        //the new person is a new node
        Node<E> newNode = new Node<>(newPerson);
        //if queue is empty, the new person is the head and the tail of the queue
        if (head == null) {
            head = tail = newNode;
        }
        else {
            Node<E> current = head;
            // as long as current is a person existing in line
            while (current != null) {
                //if the current person is the friend we look for
                if (current.isContained(friend)) {
                    while (current.getNext() != null && current.getNext().getValue().equals(friend)) {
                        current = current.getNext();
                    }
                    newNode.setNext(current.getNext());
                    current.setNext(newNode);
                    if (current == tail) {
                        tail = newNode;
                    }
                    break;
                }
                current = current.getNext();
            }

            if (current == null) {
                // Friend not found, add to the end
                tail.setNext(newNode);
                tail = newNode;
            }
        }
        size++;
    }

    public void add(E newPerson) throws InvalidInputException {
        if (newPerson == null) {
            throw new InvalidInputException("Input cannot be null");
        }

        Node<E> newNode = new Node<>(newPerson);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public E remove() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }

        E removedValue = head.getValue();
        head = head.getNext();
        size--;

        if (head == null) {
            tail = null;
        }

        return removedValue;
    }

    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return head.getValue();
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
                E clonedValue = cloneElement(current.getValue());
                clonedQueue.add(clonedValue);
                current = current.getNext();
            }

            return clonedQueue;
        } catch (CloneNotSupportedException e) {
            return null;
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
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
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E value = current.getValue();
            current = current.getNext();
            return value;
        }
    }
}