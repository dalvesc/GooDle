package goodle.util;

public class LinkedList implements Ilist {

    private Node head;
    private int totalDeElementos;

    @Override
    public void addFirst(Object data) {
        Node nova = head;
        head = new Node(data);
        head.setNext(nova);
        this.totalDeElementos++;
    }

    @Override
    public void addLast(Object data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            Node n = getNode(size() - 1);
            n.setNext(new Node(data));
            totalDeElementos++;
        }
    }

    @Override
    public Object removeFirst() {
        Object p = this.head.getData();
        this.head = this.head.getNext();
        this.totalDeElementos--;

        return p;
    }

    @Override
    public Object removeLast() {
        Node n = this.head;
        Node ant = this.head;
        while (n.getNext() != null) {
            ant = n;
            n = n.getNext();
        }
        Object p = n.getData();
        ant.setNext(null);
        this.totalDeElementos--;
        return p;
    }

    @Override
    public int size() {
        return this.totalDeElementos;
    }

    @Override
    public boolean isEmpty() {
        return this.totalDeElementos == 0;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator(head);
    }

    private Node getNode(int index) {
        if (index >= 0 && index < size()) {
            Node n = this.head;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            return n;
        }
        return null;
    }

    @Override
    public Object get(int index) {
        Node n = getNode(index);
        if (n != null) {
            return n.getData();
        }
        return null;
    }

    @Override
    public void set(int index, Object data) {
        Node n = getNode(index);
        if (n != null) {
            n.setData(data);
        }
    }
}