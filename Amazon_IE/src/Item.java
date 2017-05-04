//Item should process an item within the class and result with 
//an item object that can be added to an order
public class Item {

	public int orderNum;//to be determined later
	String[] w_info = new String[10];
	int n_info[]=new int[10];
	public int xdim;//these may need to be parsed
	public int ydim;
	public int zdim;
	public String numS = "-2";
	
	public boolean placed;
	public int placedx;
	public int placedy;
	public int placedz;
	
	public Item (int x, int y, int z) {
		this.xdim = x;
		this.ydim = y;
		this.zdim = z;
		
		this.placed = false;
	}
	
	public Item(Item I) {
		this.xdim = I.xdim;
		this.ydim = I.ydim;
		this.zdim = I.zdim;
		//this.placedx = placedx;
		
	}
	}
	

