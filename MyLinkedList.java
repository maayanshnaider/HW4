import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyLinkedList<E extends Cloneable> implements Iterable<E>  {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Constructor to initialize an empty list
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add a new element to the end of the list
    public void add(E e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<E> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<>(e));
        }
        size++;
    }
    // Get an element at a specified index
    public E get(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    // Remove an element at a specified index
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            E value = head.getValue();
            head = head.getNext();
            size--;
            return value;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node<E> nodeToRemove = current.getNext();
            current.setNext(nodeToRemove.getNext());
            size--;
            return nodeToRemove.getValue();
        }
    }

    // Get the current size of the list
    public int size() {
        return size;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the list contains a certain element
    public boolean contains(E e) {
        if (head == null) {
            return false;
        }
        return head.isContained(e);
    }

    // Clear all elements from the list
    public void clear() {
        head = null;
        size = 0;
    }

    // Check if the index is within bounds
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Convert list to string for display
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getValue());
            current = current.getNext();
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
