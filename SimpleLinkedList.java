import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {
    private SimpleNode<T> head;
    private SimpleNode<T> tail;
    private int size;

    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T element) {
        SimpleNode<T> newNode = new SimpleNode<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        SimpleNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return head.getValue();
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        T value = head.getValue();
        head = head.getNext();
        size--;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public boolean contains(T element) {
        SimpleNode<T> current = head;
        while (current != null) {
            if (current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleLinkedListIterator();
    }

    private class SimpleLinkedListIterator implements Iterator<T> {
        private SimpleNode<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            T element = currentNode.getValue();
            currentNode = currentNode.getNext();
            return element;
        }
    }

    private class SimpleNode<V> {
        private V value;
        private SimpleNode<V> next;

        public SimpleNode(V value) {
            this.value = value;
            this.next = null;
        }

        public V getValue() {
            return value;
        }

        public SimpleNode<V> getNext() {
            return next;
        }

        public void setNext(SimpleNode<V> next) {
            this.next = next;
        }
    }
}
