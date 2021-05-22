package classes;

import java.awt.Rectangle;

public class Square extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	int x;
	int y;
	
	int i;
	int j;
	
	static final int LENGTH = 20;

	public Square(int x, int y) {
		
		setX(x);
		setY(y);
	}
	

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public double getI() {
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public double getJ() {
		return j;
	}
	
	public void setJ(int j) {
		this.j = j;
	}
	
}