public class NetflixAVL {

    static class AVLNode {
        int key, height;
        AVLNode left, right;

        AVLNode(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static AVLNode root;

    // Height
    static int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Balance Factor
    static int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Right Rotation
    static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert
    static AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Minimum Value Node
    static AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Delete
    static AVLNode delete(AVLNode root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete(root.left, key);

        else if (key > root.key)
            root.right = delete(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {

                AVLNode temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }

            } else {

                AVLNode temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = delete(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);

        // RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    // Search
    static boolean search(AVLNode root, int key) {

        while (root != null) {

            if (key == root.key)
                return true;

            else if (key < root.key)
                root = root.left;

            else
                root = root.right;
        }

        return false;
    }

    // Inorder Traversal
    static void inorder(AVLNode root) {

        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        int scores[] = {50, 30, 70, 20, 40, 60, 80};

        for (int score : scores)
            root = insert(root, score);

        System.out.println("AVL Tree (Inorder):");
        inorder(root);

        System.out.println("\n\nSearch 60 : " + search(root, 60));
        System.out.println("Search 25 : " + search(root, 25));

        root = delete(root, 30);

        System.out.println("\nAVL After Deleting 30:");
        inorder(root);

        System.out.println("\n\nHeight = " + height(root));
    }
}