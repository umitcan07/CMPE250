public class Node {

    public int height;
    public String value;
    public Node rightChild;
    public Node leftChild;
    public Node(String value) {

        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 1;

    }

    @Override
    public String toString() {
        return "<" + toString(this) + ">";
    }

    public static String toString(Node n) {
        if(n == null) {
            return " ";
        }
        return toString(n.leftChild) + " " + n.value + " " + toString(n.rightChild);
    }
}
