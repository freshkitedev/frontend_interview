import java.util.LinkedList;
import java.util.Queue;

class Treenode {
    int data;
    Treenode left;
    Treenode right;

    Treenode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    void printnode() {
        System.out.println(data);
    }
}

class BinaryTree {
    Treenode root;

    BinaryTree() {
        root = null;
    }

    void insertNode(int val) {
        Treenode node = new Treenode(val);
        Treenode temp = root;
        if (temp == null) {
            root = node;
            return;
        }

        while (temp != null) {
            if (temp.data > val) {
                if (temp.left == null) {
                    temp.left = node;
                    break;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = node;
                    break;
                }
                temp = temp.right;
            }
        }
    }

    void inorderTraversal(Treenode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }

    void preorderTraversal(Treenode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    void postorderTraversal(Treenode node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    void bfs() {
        Queue<Treenode> Queues = new LinkedList<>();

        Queues.offer(root);

        while (!Queues.isEmpty()) {
            Treenode node = Queues.poll();
            System.out.println(node.data);

            if (node.left != null) {
                Queues.offer(node.left);
            }

            if (node.right != null) {
                Queues.offer(node.right);
            }
        }
    }

    Treenode deleteNode(Treenode root, int key) {
        Treenode temp;
        if (root == null) {
            return root;
        }
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        }

        else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        }

        else {
            if (root.left == null) {
                temp = root.right;
                root = null;
                return temp;
            }

            if (root.right == null) {
                temp = root.left;
                root = null;
                return temp;
            }

            // two childrens
            temp = getMin(root.right);

            root.data = temp.data;

            root.right = deleteNode(root.right, temp.data);

        }

        return root;

    }

    Treenode getMin(Treenode root) {
        Treenode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;

    }
}

public class tree_traversal {
    public static void main(String[] args) {
        BinaryTree btree = new BinaryTree();
        btree.insertNode(6);
        btree.insertNode(2);
        btree.insertNode(1);
        btree.insertNode(4);
        btree.insertNode(3);
        btree.insertNode(5);
        btree.deleteNode(btree.root, 2);
        System.out.print("Inorder: ");
        btree.inorderTraversal(btree.root);
        System.out.println();
        System.out.print("Preorder:");
        btree.preorderTraversal(btree.root);
        System.out.print("\nPostorder:");
        btree.postorderTraversal(btree.root);

    }
}
