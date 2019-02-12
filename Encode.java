package stuff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.OutputStreamBitSink;

public class Encode {

	public static void main(String[] args) throws IOException {
		String input_file_name = "dataUncompressed.txt";
		String output_file_name = "dataRecompressed.dat";
		
		FileInputStream fis = new FileInputStream(input_file_name);
		
		Encoder encoder = new Encoder(fis);
		
		FileOutputStream fos = new FileOutputStream(output_file_name);
		OutputStreamBitSink bit_sink = new OutputStreamBitSink(fos);
		
		for (int i = 0; i < 256; i++) {
			bit_sink.write(encoder.finalMap.get(i).length(), 8);
		}
		bit_sink.write((int) encoder.count, 32);
		
		fis = new FileInputStream(input_file_name);
		
		for (int i = 0; i < encoder.count; i++) {
			int character = fis.read();
			bit_sink.write(encoder.finalMap.get(character));
		}
		bit_sink.padToWord();
		fis.close();
		fos.close();

	}

}
