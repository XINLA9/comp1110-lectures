package comp1110.lectures.C04;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HexFormat;
import java.util.StringJoiner;

public class FileReadingDemo {

    public static void showBytes(InputStream in, int len) throws IOException {
	byte[] bytes = new byte[len];
	for (int i = 0; i < len; i++)
	    bytes[i] = (byte) in.read();  // ignore EOF (because we're lazy)
	HexFormat fmt = HexFormat.ofDelimiter(" ");
	String rb = fmt.formatHex(bytes);
	char[] chars = new char[len];
	for (int i = 0; i < len; i++)
	    if (32 <= bytes[i] && bytes[i] <= 126)
			chars[i] = (char)bytes[i];
	    else
			chars[i] = '.';
	System.out.println(rb + "  " + new String(chars));
    }

    public static void showChars(Reader in, int len) throws IOException {
    	char[] chars = new char[len];
    	for (int i = 0; i < len; i++)
    	    chars[i] = (char) in.read();  // ignore EOF (because we're lazy)
		StringJoiner codes = new StringJoiner(" ");
    	for (int i = 0; i < len; i++)
	    	codes.add(Integer.toString(Character.codePointAt(chars, i)));
    	System.out.println(new String(chars) + " (" + codes.toString() + ")");
    }

    public static void main(String[] args) {
	FileInputStream in = null;
	try {
	    try {
			// in = new FileInputStream("resources/words/hamlet.txt");
			// open "Mjallhvit" (Icelandic translation of the tale of
			// Snow white), from project Gutenberg
			// (https://www.gutenberg.org/), skip some preamble (in
			// English) so we start reading at the start of the story
			in = new FileInputStream("words/mjallhvit.txt");
			in.skip(959);
			var reader = new InputStreamReader(in);
			for (int i = 0; i < 20; i++) {
		    	//showBytes(in, 16);
		    	showChars(reader, 16);
			}
			System.out.flush();
	    } catch (FileNotFoundException e) {
			// auto-generated catch block
			e.printStackTrace();
	    } finally {
			in.close();
	    }
	} catch (IOException e) {
	    // auto-generated catch block
	    e.printStackTrace();
	}
    }

}
