package AreaRectangleUnion;

public class Rectangle implements Comparable<Rectangle> {
  public int x1, x2, y1, y2;
	public Rectangle (int a, int b, int c, int d) {
		x2= (a)*-1;
		y2= (b)*-1;
		x1= (c)*-1;
		y1= (d)*-1;
	}
	public int compareTo(Rectangle b) {	
		if (this.x1 != b.x1)
			return this.x1-b.x1;
		else
			return this.y1-b.y1;
	}
}


