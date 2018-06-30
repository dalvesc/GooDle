package goodle.util;

public class MyIterator implements Iterator {

    private Node temp;

    public MyIterator(Node head) {
        this.temp = head;
    }

    @Override
    public boolean hasNext() {
        return temp != null;
    }

    @Override
    public Object next() {
        Object aux = temp.getData();
        temp = temp.getNext();
        return aux;
    }
}