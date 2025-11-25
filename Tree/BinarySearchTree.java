import java.util.Scanner;

class BinarySearchTree {

    // 1. The Node Class
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of the BST
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // 2. Wrapper method for insertion (What the user calls)
    void insertRecursive(int key) {
        root = insertRecursive(root, key);
    }
    void insertIterative(int key) {
        Node newNode = new Node(key);

        // Case 1: If tree is empty, this new node becomes the root
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current; // Keep track of the parent before moving down

            // Case 2: Go Left
            if (key < current.key) {
                current = current.left;
                // If we hit the bottom (null), attach the node here
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            }
            // Case 3: Go Right
            else {
                current = current.right;
                // If we hit the bottom (null), attach the node here
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    // 3. The Recursive Logic
    Node insertRecursive(Node current, int key) {
        // Base Case: If the tree/subtree is empty, return a new node
        if (current == null) {
            return new Node(key);
        }

        // Recursive Case: Traverse down the tree
        if (key < current.key) {
            // If key is smaller, go LEFT
            current.left = insertRecursive(current.left, key);
        } else if (key > current.key) {
            // If key is larger, go RIGHT
            current.right = insertRecursive(current.right, key);
        }

        // return the (unchanged) node pointer
        return current;
    }

    // Helper: Inorder traversal to print the tree (Sorted order)
    void preOrder() {
        preOrderRecursive(root);
    }

    void preOrderRecursive(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderRecursive(root.left);
            preOrderRecursive(root.right);
        }
    }
    void postOrder() {
        postOrderRecursive(root);
    }

    void postOrderRecursive(Node root) {
        if (root != null) {
            postOrderRecursive(root.left);
            postOrderRecursive(root.right);
            System.out.print(root.key + " ");
        }
    }

    void inorder() {
        inorderRecursive(root);
    }

    void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.key + " ");
            inorderRecursive(root.right);
        }
    }

    // 4. Main Method
    static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Start entering numbers to insert into BST.");
        System.out.println("Type -1 to stop and see the result.");

        while (true) {
            System.out.print("Enter number: ");
            int num = scanner.nextInt();

            if (num == -1) break; // Exit condition

//            bst.insertRecursive(num);
            bst.insertIterative(num);
            System.out.println("Inserted " + num);
        }

        System.out.println("\nFinal Sorted Tree (Inorder Traversal):");
        bst.inorder();
        System.out.println("\n(Pre-order Traversal):");
        bst.preOrder();
        System.out.println("\n(Post-order Traversal):");
        bst.postOrder();
        scanner.close();
    }
}
