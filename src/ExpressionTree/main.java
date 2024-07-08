package ExpressionTree;

public class main {

	public static void main(String[] args) {

		Node n1 = new Node("*",null,null);
		Node n2 = new Node("+",null,null);
		Node n3 = new Node("-",null,null);
		Node n4 = new Node("1",null,null);
		Node n5 = new Node("2",null,null);
		Node n6 = new Node("4",null,null);
		Node n7 = new Node("3",null,null);

		n1.setLeft(n2); n1.setRight(n3);  // n1의 왼쪽 자식-> n2, n1의 오른쪽 자식-> n3
		n2.setLeft(n4); n2.setRight(n5);
		n3.setLeft(n6); n3.setRight(n7);
	
		
		BinaryTree t = new BinaryTree();  // 이진 트리 객체 t 생성 
		t.setRoot(n1); 					  // t의 루트 노드를 n1으로
		
		System.out.print("트리 노드 수  = "+ t.size(t.getRoot())+"\n트리 높이     = "+ t.height(t.getRoot()));
		System.out.printf("\n전위 순회:  ");
		t.preorder(t.getRoot());
		System.out.printf("\n중위 순회:  ");
		t.inorder(t.getRoot());
		System.out.printf("\n후위 순회:  ");
		t.postorder(t.getRoot());
		System.out.printf("\n레벨 순회:  ");
		t.levelorder(t.getRoot());
		System.out.println();

		System.out.printf("결과 : "+ t.evaluate(t.getRoot()));
	}
}


