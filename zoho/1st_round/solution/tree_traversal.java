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
        Queue <Treenode> Queues = new LinkedList<>();

        Queues.offer(root);

        while(!Queues.isEmpty()){
            Treenode node = Queues.poll();
            System.out.println(node.data);

            if(node.left != null){
                Queues.offer(node.left);
            }

            if(node.right != null){
                Queues.offer(node.right);
            }
        }
    }
}

public class tree_traversal {
    public static void main(String[] args) {
        BinaryTree btree = new BinaryTree();
        btree.insertNode(3);
        btree.insertNode(7);
        btree.insertNode(2);
        btree.insertNode(5);
        // System.out.print("Inorder: ");
        // btree.inorderTraversal(btree.root);
        // System.out.println();
        // System.out.print("Preorder:");
        // btree.preorderTraversal(btree.root);
        // System.out.print("\nPostorder:");
        // btree.postorderTraversal(btree.root);

        btree.bfs();
        
    }
}
