//ROWS THEN COLUMNS might have messed this up
public class Grid {

	public String[][] table;//tb initialized
	public int xdimen;
	public int ydimen;
	public int zdimen;
	public int cb_size;
	//public grid g;
	public int index = 0;
	
	//units and cb_size tbd
	public Grid(int xdim, int ydim, int zdim, int cb_size) {
		this.xdimen = xdim+2;
		this.ydimen = ydim+2;
		this.zdimen = zdim;
		this.cb_size = cb_size;
		table = new String[ydim+2][xdim+2];
		populate(table,ydim+2,xdim+2);
		//index = 0;
		
		
	}
	
	public Grid(Grid g) {
		this.xdimen = g.xdimen;
		this.ydimen = g.ydimen;
		this.zdimen = g.zdimen;
		this.cb_size = g.cb_size;
		
		this.table = arrayCopy(g.table);
		this.index = g.index;
		//this.g = g;
	}
	
	
	
	
	
	
	
	
	
	private String[][] arrayCopy(String[][] table2) {
		//System.out.println(xdimen);
		String[][] tableNew = new String[ydimen][xdimen];
		for(int i = 0; i < xdimen; i++) {
			for(int j = 0; j < ydimen; j++) {
				//System.out.println(i + " " + j);
				tableNew[j][i] = table2[j][i];

			}
		}
		return tableNew;
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


