import java.util.ArrayList;
import java.util.Collections;
/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */
//Item should process an item within the class and result with 
//an item object that can be added to an order
public class Item implements java.io.Serializable {

	public int orderNum;//to be determined later
	String[] w_info = new String[10];
	int n_info[]=new int[10];
	int xdim;//these may need to be parsed
	int ydim;
	int zdim;
	String b;
	public int xdim2;//these may need to be parsed
	public int ydim2;
	public int zdim2;
	public String numS = "-2";
	
	public boolean placed;
	public int placedx;
	public int placedy;
	public int placedz;
	
	public Item (int x, int y, int z) {
		this.xdim = x;
		this.ydim = y;
		this.zdim = z;
		this.xdim2 = x;
		this.ydim2 = y;
		this.zdim2 = z;
		this.placed = false;
	}
	
	public Item (int x, int y, int z, String b1) {
		this.xdim = x;
		this.ydim = y;
		this.zdim = z;
		this.xdim2 = x;
		this.ydim2 = y;
		this.zdim2 = z;
		this.placed = false;
		this.b = b1;
	}
	
	public Item(Item I) {
		this.xdim = I.xdim;
		this.ydim = I.ydim;
		this.zdim = I.zdim;
		//this.placedx = placedx;
		
	}
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public void setX(int n){
		this.xdim = n;
	}
	public void setY(int n){
		this.ydim = n;
	}
	public void setZ(int n){
		this.zdim = n;
	}

	
}
	

