package comp1110.lectures.C05;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class XYPlot extends Stage {
	static final int CANVAS_WIDTH = 400;
	static final int CANVAS_HEIGHT = 400;
	static final int MARGIN = 10;
	
	HashMap<Color, ArrayList<Point>> lines;
	Group group;
	double minx = Double.MAX_VALUE;
	double miny = Double.MAX_VALUE;
	double maxx = Double.MIN_VALUE;
	double maxy = Double.MIN_VALUE;
	
	XYPlot(String name) {
	  group = new Group();
		this.setScene(new Scene(group, CANVAS_WIDTH + 2 * MARGIN, CANVAS_HEIGHT + 2 * MARGIN));
		this.setTitle(name);
		lines = new HashMap<Color, ArrayList<Point>>();
	}
	
	void addPoint(double x, double y, Color color) {
	  Point point = new Point(x, y, color);
	  ArrayList<Point> line = lines.get(color);
	  if (line == null) {
	    line = new ArrayList<Point>();
	    lines.put(color, line);
	  }
	  line.add(point);
	  group.getChildren().add(point.circle);
	  boolean change = false;
	  if (x < minx) {
	    minx = x;
	    change = true;
	  }
    if (y < miny) {
      miny = y;
      change = true;    
    }
    if (x > maxx) {
      maxx = x;
      change = true;    
    }
    if (y > maxy) {
      maxy = y;
      change = true;    
    }
    if (change)
      updatePoints();
    else
      point.update(minx, miny, maxx, maxy);
	}
	
	void updatePoints() {
	//  System.out.println("Updating all points: "+minx+" - "+maxx+", "+miny+" - "+maxy);
	  for (ArrayList<Point> line: lines.values()) {
	    for (Point point : line) {
	      point.update(minx, miny, maxx, maxy);
	    }
	  }
	}
	
	class Point {
	  static final int RADIUS = 4;
	  double x;
	  double y;
	  Circle circle;
	  
	  Point(double x, double y, Color color) {
	    this.x = x;
	    this.y = y;
	    this.circle = new Circle(RADIUS, color);
	  }
	  
	  void update(double minx, double miny, double maxx, double maxy) {
	    int x, y;
	    if (maxx == minx)
	      x = MARGIN + CANVAS_WIDTH/2;
	    else
	      x = MARGIN + (int) ((CANVAS_WIDTH*(this.x - minx)/(maxx-minx)));
	    if (maxy == miny)
	      y = MARGIN + CANVAS_HEIGHT/2;
	    else
	      y = MARGIN + CANVAS_HEIGHT - (int) ((CANVAS_HEIGHT*(this.y - miny)/(maxy-miny)));
	    
	 //   System.out.println("Setting ("+this.x+", "+this.y+") to ["+x+","+y+"]");
      circle.setCenterX(x);
      circle.setCenterY(y);
	  }
	}

}
