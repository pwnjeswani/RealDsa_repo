import java.util.*;

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

    boolean isNodePresent(int key) {
        Node node = searchNodeIterative(root,key);
        return node != null;
    }

    private Node searchNodeIterative(Node root,int key) {
        Node current = root;
        while(current != null) {
            if(current.key == key) return current;
            else if(current.key > key) current = current.left;
            else current = current.right;
        }
        return null;
    }

    boolean isNodePresentRecursive(int key) {
        Node node = searchNodeRecursive(root,key);
        return node != null;
    }

    private Node searchNodeRecursive(Node root,int key) {
        if (root == null) return null;

        if (root.key == key) return root;

        if (root.key > key) return searchNodeRecursive(root.left, key);

        return searchNodeRecursive(root.right, key);
    }


    private int heightOfTree(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    private int heightOfTreeIterative(Node root) {
        if (root == null) return 0;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int height = 0;
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            height++;
            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }
        return height;
    }

    private int heightOfTreeIterativeQueue(Node root){
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            height++;
        }
        return height;
    }

    // Print tree sideways
    void printTree(Node root, int space) {
        if (root == null) return;

        space += 5; // distance between levels

        printTree(root.right, space);
        System.out.println();
        for (int i = 5; i < space; i++) System.out.print(" ");
        System.out.println(root.key);
        printTree(root.left, space);
    }

    // Public wrapper method for deletion
    void delete(int key) {
        root = deleteNode(root, key);
    }

    /* A recursive function to delete a key in the BST */
    private Node deleteNode(Node root, int key) {
        // Base Case: If the tree is empty
        if (root == null) return root;

        // Recur down the tree
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.key = findMinValue(root.right);

            // Delete the inorder successor
            root.right = deleteNode(root.right, root.key);
        }

        return root;
    }

    private int findMinValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
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

        System.out.println("*** isNodePresent iterative, 5 = " + bst.isNodePresent(5));
        System.out.println("#### isNodePresent recursive, 5 = " + bst.isNodePresentRecursive(5));

        System.out.println("printTree visualize");
        bst.printTree(bst.root,5);
        System.out.println("Height of tree = " + bst.heightOfTree(bst.root));
        System.out.println("Height of tree iterative Q = " + bst.heightOfTreeIterativeQueue(bst.root));
        scanner.close();
    }
}
