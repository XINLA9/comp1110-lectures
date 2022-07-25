package comp1110.lectures.C04;

import java.io.*;

public class Buffering {
	public static void main(String[] args) {
		for (int j = 0; j < 20; j++) {
			InputStream in = null;
			OutputStream out = null;
			var bufferSize = 1 << j;
			long start = 0;
			try {
				try {
					if (false) {
						in = new FileInputStream("assets/dictionary.txt");
						out = new FileOutputStream("output.txt");
					} else {
						in = new BufferedInputStream(
								new FileInputStream("assets/dictionary.txt"),
								bufferSize);
						out = new BufferedOutputStream(
								new FileOutputStream("output.txt"),
								bufferSize);
					}

					start = System.nanoTime();
					int i;
					while ((i = in.read()) != -1) {
						out.write(i);
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					in.close();
					out.close();
					System.out.println("Size " + bufferSize + " took " + ((System.nanoTime() - start) / 1000000) + "ms");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
