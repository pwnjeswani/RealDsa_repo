import java.util.Scanner;
import java.util.Stack;

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

    // Iterative Traversals
    void inorderIterative() {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current must be null at this point, so pop from stack
            current = stack.pop();
            System.out.print(current.key + " ");

            // Visit the right subtree
            current = current.right;
        }
    }

    void preorderIterative() {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.key + " ");

            // Push right child first, then left child
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    void postorderIterative() {
        if (root == null) return;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty()) {
            Node current = s1.pop();
            s2.push(current);

            if (current.left != null) {
                s1.push(current.left);
            }
            if (current.right != null) {
                s1.push(current.right);
            }
        }

        // Print the post-order traversal
        while (!s2.isEmpty()) {
            Node node = s2.pop();
            System.out.print(node.key + " ");
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

        System.out.println("\n--- Recursive Traversals ---");
        System.out.println("\nFinal Sorted Tree (Inorder Traversal):");
        bst.inorder();
        System.out.println("\n(Pre-order Traversal):");
        bst.preOrder();
        System.out.println("\n(Post-order Traversal):");
        bst.postOrder();

        System.out.println("\n\n--- Iterative Traversals ---");
        System.out.println("\nIterative Inorder Traversal:");
        bst.inorderIterative();
        System.out.println("\nIterative Pre-order Traversal:");
        bst.preorderIterative();
        System.out.println("\nIterative Post-order Traversal:");
        bst.postorderIterative();

        scanner.close();
    }
}
