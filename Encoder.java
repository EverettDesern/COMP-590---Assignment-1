package stuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class Encoder extends HuffmanTree {
	private int length = 0;
	private String code = "";
	private String finalCode = "";
	int codelength = 0;
	double count;
	int[] list = new int[256];
	int[] numbers = new int[256];
	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	HashMap<Integer, String> finalMap = new HashMap<Integer, String>();

	public Encoder(FileInputStream file) throws IOException {
		int[] frequencies = new int[256];
		int[] sorted = new int[256];
		int[] lengthss = new int[256];
		count = 0.0000000000000000000000;

		while (true) {

			int character = file.read();

			if (character == -1) {
				break;
			}
			count++;
			for (int i = 0; i < 256; i++) {
				sorted[i] = i;
				if ((char) character == (char) i) {
					frequencies[i]++;
				}
			}

		}
		file.close();

		for (int i = 0; i < 255; i++) {
			for (int j = 0; j < 255 - i; j++) {
				if (frequencies[j] < frequencies[j + 1]) {
					int temp = frequencies[j];
					frequencies[j] = frequencies[j + 1];
					frequencies[j + 1] = temp;
					int temp2 = sorted[j];
					sorted[j] = sorted[j + 1];
					sorted[j + 1] = temp2;
				}
			}
		}

		PriorityQueue<ProbabilityNode> queue = new PriorityQueue<ProbabilityNode>();
		for (int i = 0; i < 256; i++) {
			double probability = frequencies[i];
			ProbabilityNode node = new ProbabilityNode((char) sorted[i], probability);
			queue.add(node);
		}

		while (queue.size() != 1) {

			ProbabilityNode leftChild = queue.poll();
			ProbabilityNode rightChild = queue.poll();

			ProbabilityNode parent = new ProbabilityNode(leftChild, rightChild);
			queue.add(parent);

		}
		generateCode(queue.peek(), code);

		List<SymbolWithCodeLength> sym_with_length = new ArrayList<SymbolWithCodeLength>();

		for (int i = 0; i < 256; i++) {
			SymbolWithCodeLength newLength = new SymbolWithCodeLength(i, map.get((char) i));
			sym_with_length.add(newLength);
		}

		sym_with_length.sort(null);

		Node root = new Node();
		for (int i = 0; i < 256; i++) {
			Node insertNode = new Node((char) sym_with_length.get(i).sym, sym_with_length.get(i).codelength);
			buildTree(root, insertNode);
		}
		generateFinalCode(root, finalCode);

		
		// for part 3:
		
		
		String compressedFile = "/Users/edesern/Downloads/compressed.dat";
		InputStreamBitSource input = new InputStreamBitSource(new FileInputStream(new File(compressedFile)));

		for (int i = 0; i < 256; i++) {						// this is for providing the original
			try {											// code lengths
				list[i] = input.next(8);
				numbers[i] = i;
			} catch (InsufficientBitsLeftException e) {
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		for (int i = 0; i < 255; i++) {
			for (int j = 0; j < 255 - i; j++) {
				if (list[j] > list[j + 1]) {
					int temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}

		double[] probabilities = new double[256];
		for (int i = 0; i < 256; i++) {
			probabilities[i] = frequencies[i] / count;
		}


		double theoreticalEntropy = 0;
		for (int i = 0; i < 256; i++) {
			if (probabilities[i] != 0) {
				theoreticalEntropy += (probabilities[i] * (Math.log(probabilities[i]) / Math.log(2)));
			}
		}
		theoreticalEntropy *= -1;
														// theoretical entropy

		double entropy = 0;	
		for (int i = 0; i < 256; i++) {
			entropy += probabilities[i] * list[i];
		}
														// entropy provided from TA

		double compressedEntropy = 0;
		for (int i = 0; i < 256; i++) {
			compressedEntropy += probabilities[i] * sym_with_length.get(i).codelength;
		}
														// compressed entropy of my encoder

	}

	public boolean isLeaf(ProbabilityNode node) {
		if (node == null) {
			return false;
		}
		if (node.left == null && node.right == null) {
			return true;
		}
		return false;
	}

	public void generateCode(ProbabilityNode node, String code) {
		if (node.left == null && node.right == null) {
			map.put(node.sym, code.length());
		}
		if (node.left != null) {
			generateCode(node.left, code + "0");
		}
		if (node.right != null) {
			generateCode(node.right, code + "1");
		}
	}

	public void generateFinalCode(Node node, String finalCode) {
		if (node.left == null && node.right == null) {
			finalMap.put((int) node.symbol, finalCode);
		}
		if (node.left != null) {
			generateFinalCode(node.left, finalCode + "0");
		}
		if (node.right != null) {
			generateFinalCode(node.right, finalCode + "1");
		}
	}

}
