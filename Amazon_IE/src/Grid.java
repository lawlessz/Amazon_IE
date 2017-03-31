
public class Grid {

	public int[][] table;//tb initialized
	public int xdimen;
	public int ydimen;
	
	//units and cb_size tbd
	public Grid(int xdim, int ydim, int zdim, int cb_size) {
		this.xdimen = xdim;
		this.ydimen = ydim;
		table = new int[xdim][ydim];
		populate(table, xdim, ydim);
		
	}
	
	
	public void populate(int[][] mat, int x, int y) {
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				mat[i][j] = 0;
			}
		}
	}
	
	
	
	
	
}


