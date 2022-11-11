public class BSTree {

    Node root;

    BSTree() {
        this.root = null;
    }

    public String getMaxVal(Node root) {
        if (root == null) return "";
        if (root.rightChild != null) {
            return getMaxVal(root.rightChild);
        } else {
            return root.value;
        }
    }

    public String getMinVal(Node root) {
        // Base Case
        if (root == null) return "";

        // Go Left Always
        if (root.leftChild != null) {
            return getMinVal(root.leftChild);
        } else {
            return root.value;
        }
    }


    Node addTo(String value, Node root) {

        // Base Case
        if (root == null) {
            root = new Node(value);
        }

        // Go Left
        if (value.compareTo(root.value) < 0) {
            root.leftChild = addTo(value, root.leftChild);
        }

        // Go Right
        if (value.compareTo(root.value) > 0) {
            root.rightChild = addTo(value, root.rightChild);
        }

        return root;

    }

    public void add(String value) {

        root = addTo(value, root);

    }

    Node deleteFrom(String value, Node root) {

        //Base Case
        if (root == null) {
            return null;
        }

        // Go left
        if (value.compareTo(root.value) < 0) {
            root.leftChild = deleteFrom(value, root.leftChild);
        }

        // Go right
        if (value.compareTo(root.value) > 0) {
            root.rightChild = deleteFrom(value, root.rightChild);
        }

        // We came to the Node to delete
        if (value.compareTo(root.value) == 0) {


            // No Child

            if (root.rightChild == null && root.leftChild == null) {
                root = null;
            }

            // Only One Child

            else if(root.leftChild == null) {
                root = root.rightChild;
            } else if (root.rightChild == null) {
                root = root.leftChild;
            }

            // Two Children
            else {
                root.value = getMinVal(root.rightChild);
                root.rightChild = deleteFrom(getMinVal(root.rightChild), root.rightChild);
            }
        }

        return root;

    }

    public void delete(String value) {
        deleteFrom(value, this.root);
    }


    public void send() {
    }
}


