public class BSTree {
    private BSTNode root;

    public BSTree() {
        this.root = null;
    }

    public Stat get(String key) {
        BSTNode current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current.value;
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    public void put(String key, Stat value) {
        root = putRec(root, key, value);
    }

    private BSTNode putRec(BSTNode node, String key, Stat value) {
        if (node == null) return new BSTNode(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = putRec(node.left, key, value);
        else if (cmp > 0) node.right = putRec(node.right, key, value);
        else node.value = value;
        return node;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }
} 
