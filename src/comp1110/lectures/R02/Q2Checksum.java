package comp1110.lectures.R02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * COMP1110 Final Exam, Question 2
 *
 * 5 Marks
 */
public class Q2Checksum {

  /**
   * Q2 Part I (2 marks)
   *
   * Open the specified input file.  If the checksum boolean is false, simply
   * copy all bytes of the input file to the specified output file.  Part II
   * covers the case where the checksum boolean is true.
   *
   *
   * Q2 Part II (3 marks)
   *
   * Open the specified input file.  If the checksum boolean is true and there
   * is at least 1 byte read in then copy all bytes of the input file to the
   * output file, inserting checksum letters before every ten (10) bytes, or
   * less if the end of file is reached. Otherwise simply copy all bytes of the
   * input file to the specified output file.
   *
   * The checksum is a letter from 'a' to 'z' which represents the integer
   * remainder of the sum of the next ten bytes (or all remaining bytes if
   * there are less than ten).
   *
   * So, if the file contained one byte, which had the value 78 ('N'), then
   * the checksum would 78 % 26 = 0, so the checksum character would be 'a',
   * and the output file would contain two bytes: 'a' followed by 'N'.
   *
   * If the input file contained two bytes which had the values 78 and 103
   * ('Ng'), then the checksum would be (78 + 103) % 26 = 25, so the checksum
   * character would be 'z', and the output file would contain three bytes: 'z'
   * followed by 'Ng'.
   *
   *
   * @param input The name of the input file
   * @param output The name of the output file
   * @param checksum if true, include checksums in the output file
   */
  public static void checksum(String input, String output, boolean checksum) {
    // note: here we have written two completely different pieces of code for the
    // two parts (checksum false and checksum true); clearly there is some overlap,
    // so a neater implementation would factor out the common parts.

    // part 1: if checksum is false, we simply copy the contents of the input file
    // to the output file.
    if (!checksum) {
      try {
        FileInputStream in = new FileInputStream(input);
        FileOutputStream out = new FileOutputStream(output);
        // keep in mind: in.read() returns an int, but it actually contains a
        // byte, except if we have reached end-of-file, in which case it is -1.
        int byte_read;
        while ((byte_read = in.read()) != -1) {
          byte b = (byte) byte_read;
          out.write(b);
        }
        out.close();
        in.close();
      // java forces us to catch these exceptions: the error handlers are not
      // doing anything useful.
      } catch (FileNotFoundException e) {
        System.out.println("file not found!");
      } catch (IOException e) {
        System.out.println("I/O error!");
      }
    }
    // part 2: if checksum is true, copy contents from input file to output file,
    // but also compute and write a checksum byte for every 10 bytes of input.
    else {
      try {
        FileInputStream in = new FileInputStream(input);
        FileOutputStream out = new FileOutputStream(output);
        // instead of reading one byte at a time, we will use methods from the
        // input/output stream classes that read up to a specified number of bytes
        // into an array:
        byte[] bytes = new byte[10];
        // in.read(bytes[]) returns the number of bytes read (up to max the size of
        // the array); it is zero if we are at end of file.
        int n_bytes_read = in.read(bytes);
        while (n_bytes_read > 0) {
          // compute the checksum on the bytes read:
          int sum = 0;
          for (int i = 0; i < n_bytes_read; i++)
            sum += bytes[i];
          // 97 is a lowercase 'a':
          int chk = 97 + (sum % 26);
          // write first the checksum byte, then the bytes from the array
          out.write((byte)chk);
          out.write(bytes, 0, n_bytes_read);
          // read next 10 (at most) bytes
          n_bytes_read = in.read(bytes);
        }
        out.close();
        in.close();
      }
      catch (FileNotFoundException e) {
        System.out.println("file not found!");
      }
      catch (IOException e) {
        System.out.println("I/O error!");
      }
    }
  }

}
