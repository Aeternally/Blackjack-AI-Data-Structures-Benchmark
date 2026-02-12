public class HashTable {
    private List[] table;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new List();
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, Object value) {
        int index = hash(key);
        Node existing = table[index].find(key);
        if (existing != null) {
            existing.pair.value = value;
        } else {
            table[index].add(new Pair(key, value));
        }
    }

    public Object get(String key) {
        int index = hash(key);
        Node node = table[index].find(key);
        return node == null ? null : node.pair.value;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public void remove(String key) {
        int index = hash(key);
        table[index].remove(key);
    }
}
