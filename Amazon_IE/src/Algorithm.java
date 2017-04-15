//several parts to a whole, brute force and basic/(not clever) attempts
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
				//System.out.println("true");	
				}
			}
		}
	}
	
	//todelete?
	public static void space_Item(int coordX, int coordY, Grid g, Item rect){
		boolean t = true;
		int count = 0;
		while (t == true){

		}
	}
	
	//return true if item fits in grid (OUTDATED? need to figure out structure)
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
	
	
	public boolean checkPlace(String[][] mat, Item I, int orient, int x, int y) {
	    if (orient == 12) { // north then east but east first
	        for (int i = x; i < x + I.xdim; i++) {
	            for (int j = y; j < y + I.ydim; j++) {
	                if(mat[i][j] != "00") {
	                	return false;
	                }
	             }
	         }
	      } else if (orient == 14) { // north then west but west first
	            for (int i = x; i < x - I.xdim; i++) {
	                for (int j = y; j < y + I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
	                }
	            }
	      } else if (orient == 21) { // northeast east first
	           for (int i = x; i < x + I.xdim; i++) {
	               for (int j = y; j < y + I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
	              }
	           }
	      } else if (orient == 23) { // southeast but west first
	           for (int i = x; i < x + I.xdim; i++) {
		           for (int j = y; j < y - I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
		              }
		       }
		  } else if (orient == 32) { // south east
		        for (int i = x; i < x + I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
			        }
			    }
		  } else if (orient == 34) { // south west
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
			        }
			    }
		  } else if (orient == 41) { // west north
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y + I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
			        }
			    }
		  } else if (orient == 43) { // north then west but west first
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
		                if(mat[i][j] != "00") {
		                	return false;
		                }
			        }
			    }
		  }
	    return true;
    }
	
	//places item //need to rethink for [y][x]
    public void placeItem(String[][] mat, Item I, int orient, int x, int y) {

	    if (orient == 12) { // north then east but east first
	        for (int i = x; i < x + I.xdim; i++) {
	            for (int j = y; j < y + I.ydim; j++) {
	                mat[i][j] = I.numS;
	             }
	         }
	      } else if (orient == 14) { // north then west but west first
	            for (int i = x; i < x - I.xdim; i++) {
	                for (int j = y; j < y + I.ydim; j++) {
	                    mat[i][j] = I.numS;
	                }
	            }
	      } else if (orient == 21) { // northeast east first
	           for (int i = x; i < x + I.xdim; i++) {
	               for (int j = y; j < y + I.ydim; j++) {
	                  mat[i][j] = I.numS;
	              }
	           }
	      } else if (orient == 23) { // southeast but west first
	           for (int i = x; i < x + I.xdim; i++) {
		           for (int j = y; j < y - I.ydim; j++) {
		               mat[i][j] = I.numS;
		              }
		       }
		  } else if (orient == 32) { // south east
		        for (int i = x; i < x + I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
			            mat[i][j] = I.numS;
			        }
			    }
		  } else if (orient == 34) { // south west
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
			            mat[i][j] = I.numS;
			        }
			    }
		  } else if (orient == 41) { // west north
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y + I.ydim; j++) {
			            mat[i][j] = I.numS;
			        }
			    }
		  } else if (orient == 43) { // north then west but west first
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
			            mat[i][j] = I.numS;
			        }
			    }
		  }
    }
   
	//update if the item will fit place it
	public static void sweep(int x, int y, int i, int j, Grid g){
		
	}

	
	// (Matt) - finds the corners of the space remaining in the box
	   public static int[][] findCorners(String matrix[][]) {
	      int[][] cornerdata = new int[20][4];
	      cornerdata[0][0] = 0; // counts number of corners found
	      for (int row = 1; row < matrix.length - 1; row++) {
	         for (int column = 1; column < matrix[row].length - 1; column++) {
	            if (matrix[row][column] == "00") {
	               if (matrix[row + 1][column + 1] != "00" && matrix[row + 1][column] != "00" && matrix[row][column + 1] != "00") {
	                  cornerdata[0][0]++;
	                  cornerdata[cornerdata[0][0]][0] = row;
	                  cornerdata[cornerdata[0][0]][1] = column;
	                  cornerdata[cornerdata[0][0]][2] = 1; // 1 means north is one possible orientation
	                  cornerdata[cornerdata[0][0]][3] = 4; // 1 means west is one possible orientation
	                  //Corner corner1 = new Corner(row, column);
	               } else if (matrix[row + 1][column - 1] != "00" && matrix[row + 1][column] != "00" && matrix[row][column - 1] != "00") {
	                  cornerdata[0][0]++;
	                  cornerdata[cornerdata[0][0]][0] = row;
	                  cornerdata[cornerdata[0][0]][1] = column;
	                  cornerdata[cornerdata[0][0]][2] = 1; // 1 means north
	                  cornerdata[cornerdata[0][0]][3] = 2; // 2 means east is one possible orientation
	                  //Corner corner2 = new Corner(row, column);
	               } else if (matrix[row - 1][column + 1] != "00" && matrix[row - 1][column] != "00" && matrix[row][column + 1] != "00") {
	                  cornerdata[0][0]++;
	                  cornerdata[cornerdata[0][0]][0] = row;
	                  cornerdata[cornerdata[0][0]][1] = column;
	                  cornerdata[cornerdata[0][0]][2] = 2; // 2 means east
	                  cornerdata[cornerdata[0][0]][3] = 3; // 3 means south is one possible orientation
	                  //Corner corner3 = new Corner(row, column);
	               } else if (matrix[row - 1][column - 1] != "00" && matrix[row - 1][column] != "00" && matrix[row][column - 1] != "00") {
	                  cornerdata[0][0]++;
	                  cornerdata[cornerdata[0][0]][0] = row;
	                  cornerdata[cornerdata[0][0]][1] = column;
	                  cornerdata[cornerdata[0][0]][2] = 3; // 3 means south
	                  cornerdata[cornerdata[0][0]][3] = 4; // 4 means west
	                  //Corner corner4 = new Corner(row, column);
	               }
	            }
	         }
	      }
	      System.out.println("Number of Corners: " + cornerdata[0][0]);
	      for (int i = 1; i <= cornerdata[0][0]; i++) {
	         System.out.print("Corner " + i + ": [" + cornerdata[i][0] + "][" + cornerdata[i][1] + "]");
	         System.out.println("   Possible Item Orientations: " + cornerdata[i][2] + ", " + cornerdata[i][3]);
	      }
	      System.out.println();
	      return cornerdata;
	   }
	   
	   //returns int of all whitespace cubits to borders
	   //update all backwards from the xy-yx thing but works
	   //needs to be tested further
	   public int countWhitespace(Grid g, int xloc, int yloc, Item I){
		   int total = 0;
		   int h = 1;
		   for(int i = xloc; i < xloc+I.xdim; i++){
			   while(g.table[i][yloc-h] == "00"){
				   total++;
				   h++;
			   }
			   h = 1;
		   }
		   for(int j = xloc; j < xloc+I.xdim; j++){
			   while(g.table[j][yloc+I.ydim+h] == "00"){
				   total++;
				   h++;
			   }
			   h = 1;
		   }
		   for(int k = yloc; k < yloc+I.ydim; k++){
			   while(g.table[xloc-h][k] == "00"){
				   total++;
				   h++;
			   }
			   h = 1;
		   }
		   for(int l = yloc; l < yloc+I.ydim; l++){
			   while(g.table[xloc+I.xdim+h][l] == "00"){
				   total++;
				   h++;
			   }
			   h = 1;
		   }
		   
		   return total+I.xdim+I.ydim;//wow hacked to work be careful and figure out what happened
	   }
	   
}
