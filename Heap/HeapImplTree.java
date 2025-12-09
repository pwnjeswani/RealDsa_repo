import java.util.LinkedList;
import java.util.Queue;

class HeapImplTree {

    // The Node Class now needs a 'parent' pointer
    static class Node {
        int val;
        Node left, right, parent;

        public Node(int val) {
            this.val = val;
        }
    }

    Node root;

    public void insert(int val) {
        Node newNode = new Node(val);

        if (root == null) {
            root = newNode;
            return;
        }

        // Step 1: Find the first open spot using BFS (Level Order)
        // We look for a node that has a left or right child missing.
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node parent = null;

        while (!queue.isEmpty()) {
            parent = queue.poll();

            // Check Left side
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                break;
            } else {
                queue.add(parent.left);
            }

            // Check Right side
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                break;
            } else {
                queue.add(parent.right);
            }
        }

        // Step 2: Bubble Up (Fix the Order)
        bubbleUp(newNode);
    }

    private void bubbleUp(Node node) {
        // While parent exists and Parent > Child (Violation)
        while (node.parent != null && node.parent.val > node.val) {
            // Swap VALUES (Not pointers, it's easier!)
            int temp = node.val;
            node.val = node.parent.val;
            node.parent.val = temp;

            // Move up
            node = node.parent;
        }
    }

    public int extractMin() {
        if (root == null) throw new RuntimeException("Empty Heap");

        int min = root.val;

        // Special Case: Only one node
        if (root.left == null && root.right == null) {
            root = null;
            return min;
        }

        // Step 1: Find the "Last Node" in the tree (Bottom-Right-most)
        Node lastNode = getLastNode();

        // Step 2: Move Last Node's value to Root
        root.val = lastNode.val;

        // Step 3: Delete the Last Node
        deleteDeepestNode(lastNode);

        // Step 4: Heapify Down (Fix the Order)
        heapifyDown(root);

        return min;
    }

    // Helper: Compare Root with Children and swap down
    private void heapifyDown(Node node) {
        Node smallest = node;

        // Compare with Left
        if (node.left != null && node.left.val < smallest.val) {
            smallest = node.left;
        }

        // Compare with Right
        if (node.right != null && node.right.val < smallest.val) {
            smallest = node.right;
        }

        // If swap needed
        if (smallest != node) {
            // Swap Values
            int temp = node.val;
            node.val = smallest.val;
            smallest.val = temp;

            // Recurse down
            heapifyDown(smallest);
        }
    }


    // Finds the bottom-right-most node (The one to swap with root)
    private Node getLastNode() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node temp = null;

        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        return temp; // The last node processed is the last node in the tree
    }

    // Deletes the specific node (Cleanup)
    private void deleteDeepestNode(Node deleteNode) {
        Node parent = deleteNode.parent;
        if (parent != null) {
            if (parent.right == deleteNode) parent.right = null;
            else parent.left = null;
        }
        deleteNode.parent = null; // Help GC
    }

    // Testing
    public void printInorder(Node node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.val + " ");
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        HeapImplTree heapImplTree = new HeapImplTree();

        System.out.println("Inserting 10, 5, 20, 2...");
        heapImplTree.insert(10);
        heapImplTree.insert(5);
        heapImplTree.insert(20);
        heapImplTree.insert(2);

        System.out.println("Root is: " + heapImplTree.root.val); // Should be 2

        System.out.println("Extract Min: " + heapImplTree.extractMin()); // 2
        System.out.println("New Root is: " + heapImplTree.root.val); // Should be 5
    }
}