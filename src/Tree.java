import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        name = label;
    }

    /**
     * Create BST from ArrayList
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Return a string containing the tree contents as a tree with one node per line
     */
    public String toString() {

        String treeString = name + "\n";

        treeString = treeAsString(root, 0, treeString);
        return treeString;
    }

    private String treeAsString(BinaryTreeNode t, int depth, String treeString) {
        if (t == null) {
            return treeString;
        }

        treeString = treeAsString(t.right, depth + 2, treeString);
        for (int i = 0; i < depth; i++){
            treeString = treeString + " ";
        }
        if (t.parent == null){
            treeString = treeString + t.key + " [no parent]\n";
        }
        else{
            treeString = treeString + t.key + " [" + t.parent.key + "]\n";
        }
        treeString = treeAsString(t.left, depth+ 2, treeString);
        return treeString;
    }

    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {

        ArrayList<E> nodesInOrder = new ArrayList<>();
        nodesInOrder = arrayInOrder(root, nodesInOrder);

        String stringOfNodesInOrder = name + ":";
        for (E node : nodesInOrder)
            stringOfNodesInOrder = stringOfNodesInOrder + " " + node;

        return stringOfNodesInOrder;
    }

    private ArrayList<E> arrayInOrder(BinaryTreeNode t, ArrayList<E> nodesInOrder) {

        if (t == null) {
            return nodesInOrder;
        }

        arrayInOrder(t.left, nodesInOrder);
        nodesInOrder.add(t.key);
        arrayInOrder(t.right, nodesInOrder);

        return nodesInOrder;
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        flipTree(root);
    }

    private void flipTree(BinaryTreeNode t) {
        BinaryTreeNode leftNode = new BinaryTreeNode(t.left.key, t.left.left, t.left.right, null);
        t.left = t.right;
        t.right = leftNode;
        if (t.left != null) {
            flipTree(t.left.left);
        }
        if (t.right != null) {
            flipTree(t.right.right);
        }
    }

    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
        // TODO:
        return null;
    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        // TODO:
        return 0;
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        // TODO:
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        // TODO:
        return 0;
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root, null);
    }

    public BinaryTreeNode getByKey(E key) {
        // TODO:
        return null;
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        ArrayList<E> orderedNodes = new ArrayList<E>();
        orderedNodes = arrayInOrder(root, orderedNodes);
        root = null;

        makeBalancedTree(orderedNodes);
    }

    private void makeBalancedTree(ArrayList<E> orderedNodes){
        if (orderedNodes.size() == 1) {
            insert(orderedNodes.get(0));
        }

        else{
            int middleIndex = orderedNodes.size()/2;

            insert(orderedNodes.get(middleIndex));
            ArrayList<E> firstHalf = new ArrayList<E>(orderedNodes.subList(0, middleIndex));
            ArrayList<E> secondHalf = new ArrayList<E>(orderedNodes.subList(middleIndex + 1, orderedNodes.size()));
            makeBalancedTree(firstHalf);
            makeBalancedTree(secondHalf);
        }

    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) return new BinaryTreeNode(x, null, null, parent);

        int compareResult = x.compareTo(t.key);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryTreeNode t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.key);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E key;            // The data/key for the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            key = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(key);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.key);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}

