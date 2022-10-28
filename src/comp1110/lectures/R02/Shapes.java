/**
 * Example to show the difference between static and dynamic method
 * dispatch (and a few other things), largely inspired by a similar
 * example in Eckel's book "Thinking in Java".
 */
package comp1110.lectures.R02;

import static comp1110.lectures.R02.PrintUtil.*;
// import comp1110.lectures.R02.PrintUtil;

class Shape {

    // static (class) variable counting the number of shapes created
    static int counter = 0;

    // instance variable with this shape's unique id
    int id;

    Shape() {
        // assign id current value of counter, increment counter
        id = counter++;
    }

    // static factory method: returns a new Shape object
    static Shape makeNew() {
        return new Shape();
    }
    public String toString() {
        return "unknown shape #" + id + " of " + counter;
    }
}

// Triangle is a well-behaved shape subclass
class Triangle extends Shape {
    Triangle() {
        // implicit super()
    }
    static Shape makeNew() {
        return new Triangle();
    }
    public String toString() {
        return "triangle #" + id + " of " + counter;
    }
}

class Square extends Shape {
    // Square defines its own counter
    static int counter = 0;
    Square() {
        // implicit call to super()
    }
    static Shape makeNew() {
        return new Square();
    }
    public String toString() {
        return "square #" + id + " of " + counter;
    }
}

class Hexagon extends Shape {
    Hexagon() {
        System.out.println("my id is " + id + " and counter is " + counter);
        // Hexagon increments the counter by 6; note that implicit call to
        // super() has already happened
        counter += 5;
        //printArray({});
    }
    static Shape makeNew() {
        return new Hexagon();
    }
    public String toString() {
        return "hexagon #" + id + " of " + counter;
    }
}

public class Shapes {

    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Triangle();
        shapes[1] = new Square();
        shapes[2] = new Hexagon();
        shapes[3] = new Square();
        printArray(shapes);

        // create a new array with the same shapes by calling each
        // shape's factory method
        Shape[] new_shapes = new Shape[4];
        for (int i = 0; i < 4; i++) {
            try {
                Triangle tri = (Triangle) shapes[i];
                new_shapes[i] = tri.makeNew();
            }
            catch (ClassCastException e) {
                System.out.println(i + " is not a Triangle");
            }
            if (new_shapes[i] == null)
                new_shapes[i] = shapes[i].makeNew();
        }

        printArray(new_shapes);

        // cast the second Square to a Square reference
        Square s = (Square) shapes[3];
        System.out.println("second square: " + (s == shapes[3])
                + ", " + shapes[3].counter + ", " + s.counter);
    }
}
