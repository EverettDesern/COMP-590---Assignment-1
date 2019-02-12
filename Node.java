package stuff;

public class Node {
	Node left, right;
	char symbol;
	int length;

	public Node(char sym, int length) {
		this.symbol = sym;
		this.length = length;
		this.left = null;
		this.right = null;
	}

	public Node() {
		this.left = null;
		this.right = null;
	}

	public boolean isLeaf(Node node) {
		if(node == null) {
			return false;
		}
		if (node.left == null && node.right == null) {
			return true;
		}
		return false;
	}

	public boolean isFull(Node node) {
		if (isLeaf(node) == true) {
			return true;
		}
		if(node.left == null || node.right == null) {
			return false;
		}
		if(isFull(node.left) == true && isFull(node.right) == true) {
			return true;
		}
		return false;
	}
}
