public class List {
    private Node first;
    private Node last;
    private int count;

    public List() {
        this.first = null;
        this.last = null;
        this.count = 0;    
    }

    public int size() {
        return count;
    }

    public void add(Pair toAdd) {
        Node newNode = new Node(toAdd);
        if (first == null) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
        }
        count++;    
    }

    public Node find(String key) {
        Node current = first;
        while (current != null) {
            if (current.pair.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;    
    }

    public void remove(String key) {
        Node current = first;
        while (current != null) {
            if (current.pair.key.equals(key)) {
                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    first = current.next;
                }

                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    last = current.previous;
                }

                count--;
                return;
            }
            current = current.next;
        }
    }
}
