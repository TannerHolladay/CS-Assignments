package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Tanner Holladay & Erin Parker
 * @version March 16, 2020
 * @param <Type> - The object type of the binary search tree
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private Node root;

    private class Node {
        Type data;
        Node left, right;
        private Node(Type data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if (this.root == null) {
            this.root = new Node(item);
            return true;
        }
        Node current = this.root;
        while (!item.equals(current.data)) {
            int compare = item.compareTo(current.data);
            if (compare < 0 && current.left == null) {
                current.left = new Node(item);
                return true;
            } else if (compare > 0 && current.right == null) {
                current.right = new Node(item);
                return true;
            }
            current = compare < 0 ? current.left : current.right;
        }
        return false;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean added = false;
        for (Type item : items) {
            if (add(item)) added = true;
        }
        return added;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = null;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        if (item.equals(this.root)) return true;
        Node current = this.root;
        while (current != null) {
            if (item.equals(current.data)) return true;

            int compare = item.compareTo(current.data);
            current = compare < 0 ? current.left : current.right;
        }

        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type item : items) {
            if (!contains(item)) return false;
        }
        return items.size() > 0;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (this.root == null) throw new NoSuchElementException();

        Node current = this.root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (this.root == null) throw new NoSuchElementException();

        Node current = this.root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        if (this.root == null) return false;
        Node current = this.root;
        if (item.equals(current.data)){
            this.root = delNode(this.root);
            return true;
        }
        while (current != null) {
            int compare = item.compareTo(current.data);

            if (current.left != null && item.equals(current.left.data)) {
                current.left = delNode(current.left);
                return true;
            } else if (current.right != null && item.equals(current.right.data)) {
                current.right = delNode(current.right);
                return true;
            }
            current = compare < 0 ? current.left : current.right;
        }

        return false;
    }

    private Node delNode(Node node) {
        if (node.right != null && node.left != null) {
            Node parent = node.right;
            if (parent.left != null){
                while (parent.left.left != null) {
                    parent = parent.left;
                }
                node.data = parent.left.data;
                parent.left = parent.left.right;
                return node;
            }
            parent.left = node.left;
            return parent;
        } else {
            return node.right != null ? node.right : node.left;
        }
    }


    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean removed = false;
        for (Type item : items) {
            if (remove(item)) removed = true;
        }
        return removed;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size(this.root);
    }

    private int size (Node root) {
        return root == null ? 0 : 1 + size(root.left) + size(root.right);
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> list = new ArrayList<>();
        addToList(this.root, list);
        return list;
    }

    private void addToList(Node root, ArrayList<Type> list) {
        if (root != null) {
            if (root.left != null) {
                addToList(root.left, list);
            }
            list.add(root.data);
            if (root.right != null) {
                addToList(root.right, list);
            }
        }
    }

    public void print() {
        System.out.println("digraph G{");
        System.out.println(this.root.data);
        print(this.root);
        System.out.println("}");
    }

    private void print(Node root) {
        if (root != null) {
            if (root.left != null) {
                System.out.println(root.data + "->" + root.left.data);
                print(root.left);
            }
            if (root.right != null) {
                System.out.println(root.data + "->" + root.right.data);
                print(root.right);
            }
        }
    }
}
