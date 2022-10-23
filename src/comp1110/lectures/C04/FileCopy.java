package comp1110.lectures.C04;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class FileCopy {

    static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
	InputStream in = null;
	OutputStream out = null;
	long total_time = 0;
	long count = 0;
	try {
	    try {
		in = new FileInputStream("words/hamlet.txt");
		out = new FileOutputStream("output.txt");

		// use buffering
		in = new BufferedInputStream(in, BUFFER_SIZE);
		out = new BufferedOutputStream(out, BUFFER_SIZE);
		
		int i;
		long t0 = System.nanoTime();
		while ((i = in.read()) != -1) {
		    out.write(i);
		    count += 1;
		}
		long t1 = System.nanoTime();
		total_time = t1 - t0;
	    } catch (FileNotFoundException e) {
		// auto-generated catch block
		e.printStackTrace();
	    } finally {
		in.close();
		out.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println(count + " bytes copied in " + (total_time * 1e-9) + " seconds");
    }

}
