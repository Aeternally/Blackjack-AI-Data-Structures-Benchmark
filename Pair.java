class Pair {
    String key;
    Object value;

    public Pair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return key + ": " + value.toString();
    }
}
