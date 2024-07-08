package Btrees;

public class BTree {
    public Node root = new Node();

    public void add(int value) {
        add(root, value, null);
    }

    private void add(Node node, int value, Node rightChild) {
        if (node.isLeaf())
            addToNode(node, value, rightChild);
        else {
            int index = node.findChildIndex(value);
            add(node.child[index], value, rightChild);
        }
    }

    private void addToNode(Node node, int value, Node rightChild) {
        node.insert(value, rightChild);
        if (node.overflow()) {
            if (node == root) {
                node.parent = root = new Node();
                root.child[0] = node;
                splitNode(node);
            } else {
                //회전연산 삭제
                splitNode(node);
            }
        }
    }

    private void splitNode(Node node) {
        Node rightNode = new Node();
        rightNode.parent = node.parent;
        int middle = (node.count % 2 == 0) ? node.count / 2 - 1 : node.count / 2;  //선행자 올리도록 수정
        for (int i = middle + 1; i < node.count; ++i)
            rightNode.insert(node.data[i], node.child[i + 1]);
        node.count = middle;
        int value = node.data[middle];
        rightNode.child[0] = node.child[middle + 1];
        if (!node.isLeaf())
            rightNode.setParentOfChildren();
        addToNode(node.parent, value, rightNode);
    }

    private static boolean rotateRight1(Node leftNode) {
        Node parent = leftNode.parent;
        int index = parent.findChildIndex(leftNode);
        if (index == parent.count)
            return false;
        Node rightNode = parent.child[index + 1];
        if (rightNode.isNotFull() == false)
            return false;
        int lastData = leftNode.data[leftNode.count - 1];
        rightNode.prepand(parent.data[index], leftNode.child[leftNode.count]);
        parent.data[index] = lastData;
        leftNode.removeAt(leftNode.count - 1, leftNode.count);
        return true;
    }

    private static boolean rotateRight2(Node rightNode) {
        Node parent = rightNode.parent;
        int index = parent.findChildIndex(rightNode);
        if (index == 0)
            return false;
        Node leftNode = parent.child[index - 1];
        if (leftNode.isOverHalfFull() == false)
            return false;
        int lastData = leftNode.data[leftNode.count - 1];
        rightNode.prepand(parent.data[index - 1], leftNode.child[leftNode.count]);
        parent.data[index - 1] = lastData;
        leftNode.removeAt(leftNode.count - 1, leftNode.count);
        return true;
    }

    private static boolean rotateLeft1(Node rightNode) {
        Node parent = rightNode.parent;
        int index = parent.findChildIndex(rightNode);
        if (index == 0)
            return false;
        Node leftNode = parent.child[index - 1];
        if (leftNode.isNotFull() == false)
            return false;
        int firstData = rightNode.data[0];
        leftNode.insert(parent.data[index - 1], rightNode.child[0]);
        parent.data[index - 1] = firstData;
        rightNode.removeAt(0, 0);
        return true;
    }

    private static boolean rotateLeft2(Node leftNode) {
        Node parent = leftNode.parent;
        int index = parent.findChildIndex(leftNode);
        if (index == parent.count)
            return false;
        Node rightNode = parent.child[index + 1];
        if (rightNode.isOverHalfFull() == false)
            return false;
        int firstData = rightNode.data[0];
        leftNode.insert(parent.data[index], rightNode.child[0]);
        parent.data[index] = firstData;
        rightNode.removeAt(0, 0);
        return true;
    }

    public void remove(int value) {
        remove(root, value);
        if (root.isLeaf() == false && root.count == 0)
            root = root.child[0];
    }

    private void remove(Node node, int value) {
        int index = node.findChildIndex(value);
        if (0 < index && node.data[index - 1] == value) {
            if (node.isLeaf())
                removeFromNode(node, index - 1);
            else {
                int value1 = getLeftMostValue(node.child[index - 1]); //수정
                node.data[index - 1] = value1;    //수정
                remove(node.child[index - 1], value1);    //수정
            }
        } else {
            Node childNode = node.child[index];
            if (childNode != null)
                remove(childNode, value);
        }
    }

    private static int getLeftMostValue(Node node) {
        if (node.child[0] == null)
            return node.data[0];
        return getLeftMostValue(node.child[0]);
    }

    private void removeFromNode(Node node, int index) {
        node.removeAt(index, index + 1);
        if (node.count < Node.MIN_COUNT) {
            if (node == root)
                return;
            if (rotateRight2(node))
                return;
            if (rotateLeft2(node))
                return;
            merge(node);
        }
    }

    private void merge(Node node) {
        Node parent, leftNode, rightNode;
        int index;
        parent = node.parent;
        index = parent.findChildIndex(node);
        if (index > 0) {
            rightNode = node;
            leftNode = parent.child[--index];
        } else {
            leftNode = node;
            rightNode = parent.child[index + 1];
        }
        leftNode.insert(parent.data[index], rightNode.child[0]);
        for (int i = 0; i < rightNode.count; ++i)
            leftNode.insert(rightNode.data[i], rightNode.child[i + 1]);
        removeFromNode(parent, index);
    }
}