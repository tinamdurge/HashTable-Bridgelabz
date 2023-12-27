// UC1: hashtable class using linkedlist
public class HashTable<K extends Comparable<K>, V> {
    int number_of_nodes;
    int currently_filled_nodes;
    LinkedList<K, V>[] hashtable;

    public HashTable() {
        this.number_of_nodes = 100;
        this.hashtable = new LinkedList[number_of_nodes];
        this.currently_filled_nodes = 0;
        this.generate(number_of_nodes);
    }

    // method to initialize empty l inkedlist of given size
    private void generate(int size) {
        for (int i = 0; i < size; i++) {
            this.hashtable[i] = new LinkedList<K, V>();
        }
    }

    // UC2: method to get index for key to be inserted using hashCode
    private int getIndexforKey(K key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % number_of_nodes;
    }

    // similar to put method of hashmap
    public void put(K key, V value) {
        int index = this.getIndexforKey(key);
        MyMapNode<K, V> node = hashtable[index].head;
        while (node != null) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
            node = node.next;
        }
        hashtable[index].add(key, value);
        currently_filled_nodes++;
    }

    // similar to get method of hashmap
    public V get(K key) {
        int index = this.getIndexforKey(key);
        MyMapNode<K, V> node = hashtable[index].head;
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            node = node.next;
        }
        return null;
    }

    // UC3: remove method similar to hashmap
    public void remove(K key) {
        int index = this.getIndexforKey(key);
        LinkedList<K, V> list = hashtable[index];
        MyMapNode<K, V> node = list.head;
        while (node != null) {
            if (node.getKey().equals(key)) {
                list.deleteNode(key);
                return;
            }
            node = node.next;
        }
    }

    // similar to getordefault method of hashmap
    public V getOrDefault(K key, V default_value) {
        V value = this.get(key);
        return value == null ? default_value : this.get(key);
    }

    // similar to containskey method of hashmap
    public boolean containsKey(K key) {
        return this.get(key) != null;
    }

    @Override
    public String toString() {
        String tabledata = "[ ";
        for (int i = 0; i < hashtable.length; i++) {
            MyMapNode<K, V> node = hashtable[i].head;
            while (node != null) {
                tabledata += "(" + node.getKey() + ", " + node.getValue() + ") ";
                node = node.next;
            }
        }
        return tabledata + "]";
    }
}
