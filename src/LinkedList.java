// modified linkedlist class to have MyMapNode objects

public class LinkedList<K extends Comparable<K>, V> {
    MyMapNode<K, V> head;
    MyMapNode<K, V> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    // delete given node from linkedlist
    public void deleteNode(K key) {
        // if linkedlist is empty
        if (head == null) {
            return;
        }

        // if node is head or tail
        if (head.getKey().equals(key)) {
            this.deleteFirst();
            return;
        }

        if (tail.getKey().equals(key)) {
            this.deleteLast();
            return;
        }

        // if the node is between head and tail
        MyMapNode<K, V> temp = head;
        while (temp.next != null && !temp.next.getKey().equals(key)) {
            temp = temp.next;
        }

        temp.next = temp.next.next;
    }

    // method to delete last node in linkedlist
    public void deleteLast() {
        // if linkedlist is empty
        if (head == null) {
            return;
        }

        MyMapNode<K, V> temp = head;

        // check if there is only one node
        if (temp.next == null) {
            head = null;
            tail = null;
            return;
        }

        while (temp.next != tail) {
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
    }

    // method to delete first node in linkedlist
    public void deleteFirst() {
        // if linkedlist is empty
        if (head == null) {
            return;
        }

        MyMapNode<K, V> temp = head.next;
        head.next = null;
        head = temp;
    }

    // method to search node in linkedlist
    // modified for hashtable
    public MyMapNode<K, V> search(K key) {
        // if linkedlist is empty
        if (head == null) {
            return null;
        }

        MyMapNode<K, V> temp = head;
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // method to add new node at given index in linkedlist
    public void insertAt(int index, K key, V value) {
        // check if index is valid
        if (index < 0 || index > this.size()) {
            System.out.println("Invalid index.");
            return;
        }

        MyMapNode<K, V> node = new MyMapNode<>(key, value);
        if (index == 0) {
            this.addFirst(key, value);
        } else {
            MyMapNode<K, V> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

    // method to add new node at last of linkedlist
    public void add(K key, V value) {
        MyMapNode<K, V> node = new MyMapNode<>(key, value);
        // if linkedlist is empty
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    // method to add new node at head of linkedlist
    public void addFirst(K key, V value) {
        MyMapNode<K, V> node = new MyMapNode<>(key, value);
        // if linkedlist is empty
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    // method to get size of linkedlist
    public int size() {
        MyMapNode<K, V> node = head;
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    @Override
    public String toString() {
        String listdata = "[ ";
        MyMapNode<K, V> node = head;
        while (node != null) {
            if (node.next == null) {
                listdata += "(" + node.getKey() + ": " + node.getValue() + ") ]";
            } else {
                listdata += "(" + node.getKey() + ": " + node.getValue() + "), ";
            }
            node = node.next;
        }
        return listdata;
    }
}