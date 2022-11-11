public class AVLTree extends BSTree {

    Node root;

    AVLTree() {
        this.root = null;
    }

    public int getHeight(Node n) {

        if (n == null) {
            return 0;
        }

        return n.height;
    }

    public void updateHeight(Node n) {
        int l = getHeight(n.leftChild);
        int r = getHeight(n.rightChild);
        n.height = Math.max(l, r) + 1;
    }

    public int getBalance(Node n) {
        return getHeight(n.rightChild) - getHeight(n.leftChild);
    }

    public Node balance(Node n) {

        int bf = getBalance(n);

        if (bf < -1) {  // Left Heavy

            if (getBalance(n.leftChild) > 0) {
                n.leftChild = rotLeft(n.leftChild);
            }
            n = rotRight(n);

        }

        if (bf > 1) {

            if (getBalance(n.rightChild) < 0) {
                n.rightChild = rotRight(n.rightChild);
            }
            n = rotLeft(n);
        }

        return n;
    }

    public Node rotRight(Node n) {

        Node prevLeft = n.leftChild;

        prevLeft.rightChild = n;
        n.leftChild = prevLeft.rightChild;

        updateHeight(prevLeft);
        updateHeight(n);

        return prevLeft;

    }

    public Node rotLeft(Node n) {
        Node prevRight = n.rightChild;

        prevRight.leftChild = n;
        n.rightChild = prevRight.leftChild;

        updateHeight(prevRight);
        updateHeight(n);

        return prevRight;
    }

    @Override
    Node addTo(String value, Node root) {
        root = super.addTo(value, root);

        updateHeight(root);
        return balance(root);
    }

    @Override
    Node deleteFrom(String value, Node root) {
        root = super.deleteFrom(value, root);

        updateHeight(root);
        return balance(root);
    }

}
