package Btrees;

class Node {
    static int MAX_COUNT = 2; // 노드의 최대 크기
    static int MIN_COUNT = MAX_COUNT / 2; // 노드의 최소 크기
    int count; // 노드에 들어있는 데이터의 수
    int[] data; // 데이터 목록
    Node parent; // 부모 노드에 대한 참조
    Node[] child; // 자식 노드에 대한 참조

    Node() {
        data = new int[MAX_COUNT + 1]; // 오버플로우를 임시로 허용할 수 있도록 +1
        child = new Node[MAX_COUNT + 2];
    }

    boolean isLeaf() {
        return this.child[0] == null;
    }

    boolean isNotFull() {
        return this.count < MAX_COUNT;
    }

    boolean isOverHalfFull() {
        return this.count > MIN_COUNT;
    }

    boolean overflow() {
        return this.count == MAX_COUNT + 1;
    }

    int findChildIndex(int value) {
        for (int i = 0; i < this.count; ++i)
            if (value < this.data[i])
                return i;
        return this.count;
    }

    int findChildIndex(Node childNode) {
        for (int i = 0; i < this.count + 1; ++i)
            if (childNode == this.child[i])
                return i;
        return -1;
    }

    void remove(int value) {
        int index = findChildIndex(value);
        ArrayUtils.removeAt(data, index);
        ArrayUtils.removeAt(child, index + 1);
        --this.count;
    }

    void removeAt(int dataIndex, int childIndex) {
        ArrayUtils.removeAt(data, dataIndex);
        ArrayUtils.removeAt(child, childIndex);
        --this.count;
    }

    void prepand(int value, Node leftChild) {
        ArrayUtils.insertAt(data, 0, value);
        ArrayUtils.insertAt(child, 0, leftChild);
        if (leftChild != null)
            leftChild.parent = this;
        ++this.count;
    }

    void insert(int value, Node rightChild) {
        int index = this.findChildIndex(value);
        ArrayUtils.insertAt(data, index, value);
        ArrayUtils.insertAt(child, index + 1, rightChild);

        if (rightChild != null)
            rightChild.parent = this;
        ++this.count;
    }

    void setParentOfChildren() {
        for (int i = 0; i < this.count + 1; ++i)
            child[i].parent = this;
    }
}