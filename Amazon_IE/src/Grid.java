
public class Grid {

	public String[][] table;//tb initialized
	public int xdimen;
	public int ydimen;
	
	//units and cb_size tbd
	public Grid(int xdim, int ydim, int zdim, int cb_size) {
		this.xdimen = xdim+2;
		this.ydimen = ydim+2;
		table = new String[xdim+2][ydim+2];
		//populate(table, xdim, ydim);
		populate(table,xdim+2,ydim+2);
		
	}
	
	
	public void populate(String[][] mat, int x, int y) {
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				mat[i][j] = "-1";
			}
		}
		
		for(int i = 1; i < x-1; i++) {
			for(int j = 1; j < y-1; j++) {
				mat[i][j] = "00";
			}
		}

		
	}
	
	
	
	
	
}


