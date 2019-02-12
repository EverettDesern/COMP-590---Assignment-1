package stuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.InputStreamBitSource;

public class Decode {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream("dataUncompressed.txt"));
		System.setOut(out);

		String compressedFile = "/Users/edesern/Downloads/compressed.dat";
		InputStreamBitSource input = new InputStreamBitSource(new FileInputStream(new File(compressedFile)));

		Reading read = new Reading(input);
		HuffmanTree tree = new HuffmanTree();
		

	}

}
