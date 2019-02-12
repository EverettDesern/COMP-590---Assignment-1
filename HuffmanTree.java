package stuff;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import io.InputStreamBitSource;

public class HuffmanTree extends Node {

	public HuffmanTree() {
		Node root = new Node();
	}

	public void buildTree(Node root, Node node) {
		int length = node.length;
		Node current = root;
		char sym = node.symbol;
		for (int i = 0; i < length; i++) {
			if (current.left == null) {
				if (i + 1 == length) {
					current.left = node;
				} else {
					current.left = new Node();
					current = current.left;
				}
			} else {
				if (isFull(current.left) == true) {
					if (i + 1 == length) {
						current.right = node;
					} else {
						if (current.right == null) {
							current.right = new Node();
							current = current.right;
						} else {
							current = current.right;
						}
					}
				} else {
					current = current.left;
				}
			}
		}
	}
	public boolean findSymbol(Node root, int bit, Node current) {
		//Node current = root;
		if(bit == 0) {
			//current = current.left;
			if(isLeaf(current) == true) {
				System.out.print(current.symbol);
				return true;
			}
			return false;
		}
		if(bit == 1) {
			//current = current.right;
			if(isLeaf(current) == true) {
				System.out.print(current.symbol);
				return true;
			}
			return false;
		}
		return false;
	}
}
