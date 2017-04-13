//several parts to a whole, brute force and basic attempts
//
public class Algorithm {


	public int coordPass = 0;
	public String[][] g;
	public Algorithm(String[][] T){
		this.g = T;
	}
	//find empty spots and enact procedure
	public void find_Empty(Grid g, Item rect) {
		for (int i = 0; i<g.xdimen; i++) {
			for (int j = 0; j<g.ydimen; j++){
				if(g.table[i][j] == "00"){//if empty then we look for complete space
				System.out.println("true");	
				}
			}
		}
	}
	
	public static void space_Item(int coordX, int coordY, Grid g, Item rect){
		boolean t = true;
		int count = 0;
		while (t == true){

		}
	}
	
	//return true if item fits in grid
	public static boolean place(int x, int y, Grid g, Item rect){
		for (int i = x; i<rect.xdim; i++) {
			for (int j = y; j<rect.ydim; j++) {
				if(g.table[i][j] == "00"){
					
				} else {
					return false;
				}
			}
		}
		
		return true;
	}
	//update if the item will fit place it
	public static void sweep(int x, int y, int i, int j, Grid g){
		
	}

}
