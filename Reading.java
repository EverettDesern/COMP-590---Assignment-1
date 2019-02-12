package stuff;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

import java.io.File;

public class Reading extends HuffmanTree {


	public Reading(InputStreamBitSource input) {
		int[] list = new int[256];
		int[] numbers = new int[256];

		for (int i = 0; i < 256; i++) {
			try {
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
					int temp2 = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp2;
				}
			}
		}
		int total = 0;
		try {
			total = input.next(32);

		} catch (InsufficientBitsLeftException | IOException e) {
			e.printStackTrace();
		}

		Node root = new Node();

		for (int i = 0; i < 256; i++) {
			Node node = new Node((char) numbers[i], list[i]);
			buildTree(root, node);
		}
		int bit = 2;
		for(int i = 0; i < total; i++) {
			Node current = root;
			while(findSymbol(root, bit, current) == false) {
			try {
				if(bit == 1) {
					current = current.right;
				}
				if(bit == 0) {
					current = current.left;
				}
				bit = input.next(1);
			} catch (InsufficientBitsLeftException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}

	}

}
