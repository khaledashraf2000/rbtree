package com.company;

public class RedBlackTree {
    private Node root;
    private int size;

    public RedBlackTree() {
        root = null;
        size = 0;
    }

    public void insert(String value) {
        insertRecursive(null, this.root, value);
    }

    private void insertRecursive(Node parent, Node node, String value) {
        //root
        if(node == null) {
            root = new Node(null, value, false);
            size = size + 1;
            return;
        }
        if(value.compareTo(node.value) < 0) {
            if(node.left != null) {
                insertRecursive(node, node.left, value);
            } else {
                node.left = new Node(node, value, true);
                size = size + 1;
                node.fixup(node.left);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right != null) {
                insertRecursive(node, node.right, value);
            } else {
                node.right = new Node(node, value, true);
                size = size + 1;
                node.fixup(node.right);
            }
        } else {
            System.out.println("Value already in tree!");
        }
    }

    public boolean contains(String value) {
        return containsRecursive(this.root, value);
    }

    private boolean containsRecursive(Node node, String value) {
        if(node == null) {
            return false;
        }

        if(value.compareTo(node.value) == 0) {
            return true;
        } else if (value.compareTo(node.value) < 0) {
            return containsRecursive(node.left, value);
        } else {
            return containsRecursive(node.right, value);
        }
    }

    public void printInOrder() {
        printInOrderRecursive(root);
    }

    private void printInOrderRecursive(Node node) {
        if(node.left != null) {
            printInOrderRecursive(node.left);
        }
        System.out.println(node.value + " ");
        if(node.right != null) {
            printInOrderRecursive(node.right);
        }
    }

    public void printPreOrder() {
        printPreOrderRecursive(root);
    }

    private void printPreOrderRecursive(Node node) {
        System.out.println(node.value + " ");
        if(node.left != null) {
            printInOrderRecursive(node.left);
        }
        if(node.right != null) {
            printInOrderRecursive(node.right);
        }
    }

    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node node) {
        if(node == null) return 0;
        return 1 + Math.max(heightRecursive(node.right), heightRecursive(node.left));
    }

    public int getSize() {
        return size;
    }


    private class Node {
        String value;
        Node left, right, parent;
        boolean red;

        Node(Node parent, String value, boolean red) {
            this.value = value;
            this.red = red;
            this.parent = parent;
            this.right = null;
            this.left = null;
        }

        void fixup(Node node) {
            if(node.red) {
                // case 1: if node is root then change its color to black
                if(node.parent == null) {
                    node.red = false;
                }
                // case 2: if node has red parent
                else if (node.parent.red && node.parent.parent != null) {
                    Node uncle = getUncle(node);

                    // case 2a: if node has red uncle
                    if(uncle != null && uncle.red) {
                        uncle.red = false;
                        node.parent.red = false;
                        node.parent.parent.red = true;
                        fixup(node.parent.parent);
                    }

                    // case 2b: if node has black uncle
                    else {
                        //              B
                        //          /       \
                        //        R         Uncle
                        //      /   \
                        //           Node
                        if(node.parent.isLeftChild()) {
                            if(node.isRightChild()) {
                                node.parent.leftRotate();
                                fixup(node.left);
                                return;
                            }
                            // case 3
                            node.parent.red = false;
                            node.parent.parent.red = true;
                            node.parent.parent.rightRotate();
                        }

                        //              B
                        //          /       \
                        //      Uncle         R
                        //                  /   \
                        //                Node
                        else {
                            if(node.isLeftChild()) {
                                node.parent.rightRotate();
                                fixup(node.right);
                                return;
                            }
                            // case 3
                            node.parent.red = false;
                            node.parent.parent.red = true;
                            node.parent.parent.leftRotate();
                        }
                    }
                }
            }
        }

        boolean isRightChild() {
            return parent.right == this;
        }

        boolean isLeftChild() {
            return parent.left == this;
        }

        Node getUncle(Node node) {
            Node uncle;
            if (node.parent.isLeftChild()) {
                uncle = node.parent.parent.right;
            } else {
                uncle = node.parent.parent.left;
            }
            return uncle;
        }

        void rightRotate() {
            Node y = this.left;
            this.left = y.right;
            if(y.right != null) {
                y.right.parent = this;
            }
            y.parent = this.parent;
            if(this.parent == null) {
                root = y;
            } else if (this.isLeftChild()) {
                this.parent.left = y;
            } else {
                this.parent.right = y;
            }
            y.right = this;
            this.parent = y;
        }

        void leftRotate() {
            Node y = this.right; // y is right child of x (current node)
            this.right = y.left; // turn y's left subtree into x's right subtree
            if (y.left != null) {
                y.left.parent = this;
            }
            y.parent = this.parent;
            if(this.parent == null) {
                root = y;
            } else if(this.isLeftChild()) {
                this.parent.left = y;
            } else {
                this.parent.right = y;
            }
            y.left = this;
            this.parent = y;
        }
    }

}
