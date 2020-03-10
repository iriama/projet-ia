public class Node<T> {

    private T value;
    private Node<T> parent;

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getValue() {
        return value;
    }
}
