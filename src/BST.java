import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName BST
 * @Description Binary Search Tree
 * @Author zhangzx
 * @Date 2019/4/16 10:32
 * Version 1.0
 **/
public class BST<E extends Comparable> {

    private class Node {
        public E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if(root == null) {
//            root = new Node(e);
//            size ++;
//        }
//        else {
//            add(root, e);
//        }
        root = add(root, e);
    }

    private Node add(Node node, E e) {
//        if(e.equals(node.e)){
//            return;
//        }
//        if(e.compareTo(root.e) < 0 && root.left == null) {
//            root.left = new Node(e);
//            size ++;
//            return;
//        }else if(e.compareTo(root.e) > 0 && root.right == null) {
//            root.right = new Node(e);
//            size ++;
//            return;
//        }
        if(node == null) {
            size ++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }else{
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contain(E e) {
        return contain(root, e);
    }

    private boolean contain(Node node, E e) {
        if(node == null) {
            return false;
        }
        if(e.compareTo(node.e) == 0) {
            return true;
        }else if(e.compareTo(node.e) < 0){
            return contain(node.left, e);
        }else{
            return contain(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void postOrder() {
        inOrder(root);
    }

    private void preOrder(Node node) {

        if(node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(Node node) {

        if(node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    private void postOrder(Node node) {

        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void preOrderNR() {

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    public E miniMum() {
        if (size == 0) {
           throw new IllegalArgumentException("BST is empty");
        }
        return miniMum(root).e;
    }

    private Node miniMum(Node node) {
        if (node.left == null) {
            return node;
        }
        return miniMum(node.left);
    }

    public E maxMum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty !");
        }
        return maxMum(root).e;
    }

    private Node maxMum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxMum(node.right);
    }

    public E removeMin() {
        E ret = miniMum();
        root = removeMin(root);
        return ret;
    }

    public E removeMax() {
        E ret = maxMum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node retNode = node.right;
            node.right = null;
            size --;
            return retNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.left == null) {
            Node retNode = node.right;
            node.right = null;
            size --;
            return retNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void remove(E e) {
        //为什么返回值变为root了
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {

        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }

        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
//            e = node.e
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = miniMum(node.right);
            successor.right = removeMin(node.right);
            // size++
            successor.left = node.left;

            node.left = node.right = null;
            //size --
            return successor;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if(node == null) {
            res.append(generateDepthString(depth) +  "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);

    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }
}
