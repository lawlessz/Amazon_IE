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
	
	
	public boolean checkPlace(Grid g, Item I, int orient, int x, int y) {
		if(g.index != (Character.getNumericValue(I.numS.charAt(0))-1) ){  //dont place an item that shouldnt be placed
			System.out.println("DFSJFFKSDFJFSIFDFSFD");
			return false;
		}
	    if (orient == 12) { // north then east but east first
	        for (int i = x; i < x + I.xdim; i++) {
	        	System.out.println(y);
	        	System.out.println(I.ydim);
	        	System.out.println(y-I.ydim);
	        	if(y-I.ydim < 0){
	        		return false;
	        		
	        	}
	            for (int j = y; j > y - I.ydim; j--) {
	                if(g.table[j][i] != "00") {
	                	return false;
	                }
	             }
	         }
	      } else if (orient == 14) { // north then west but west first
	        	if(x-I.xdim < 0){
	        		return false;
	        		
	        	}
	            for (int i = x; i > x - I.xdim; i--) {
	                for (int j = y; j < y + I.ydim; j++) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
	                }
	            }
	      } else if (orient == 21) { // northeast east first
		        for (int i = x; i < x + I.xdim; i++) {
		        	if(y-I.ydim < 0){
		        		return false;
		        		
		        	}
		            for (int j = y; j > y - I.ydim; j--) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
		             }
		         }
	      } else if (orient == 23) { // southeast east first
	    	  System.out.println(x + " " + y + "x y" + I.xdim + " " + I.ydim );
	    	  System.out.println();

	           for (int i = x; i < x + I.xdim; i++) {
		           for (int j = y; j < y + I.ydim; j++) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
		              }
		       }
		  } else if (orient == 32) { // south east
		        for (int i = x; i < x + I.xdim; i++) {
			        for (int j = y; j < y + I.ydim; j++) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
			        }
			    }
		  } else if (orient == 34) { // south west
	        	if(x-I.xdim < 0){
	        		return false;
	        		
	        	}
		        for (int i = x; i > x - I.xdim; i--) {
			        for (int j = y; j < y + I.ydim; j++) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
			        }
			    }
		  } else if (orient == 41) { // west north
	        	if(x-I.xdim < 0 || y-I.ydim < 0){
	        		return false;
	        		
	        	}
		        for (int i = x; i > x - I.xdim; i--) {
			        for (int j = y; j > y - I.ydim; j--) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
			        }
			    }
		  } else if (orient == 43) { // South then west but west first
	        	if(x-I.xdim < 0){
	        		return false;
	        		
	        	}
		        for (int i = x; i > x - I.xdim; i--) {
			        for (int j = y; j < y + I.ydim; j++) {
		                if(g.table[j][i] != "00") {
		                	return false;
		                }
			        }
			    }
		  }
	    return true;
    }
	
	//places item //need to rethink for [y][x]
	public boolean placeItem(Grid g, Item I, int orient, int x, int y, Node n, int ind) {
		g.index++;
		int tempx = x;
		int tempy = y;
		I.placedy = y;
		n.getArr()[ind].placedy = tempy;
		n.getPlacement()[ind][1] = tempy;
	    if (orient == 12) { // north then east but east first
	        for (int i = x; i < x + I.xdim; i++) {
	            for (int j = y; j > y - I.ydim; j--) {
	            	g.table[j][i] = I.numS;
	             }
	         }
	       tempy = y-I.ydim+1;
	      } else if (orient == 14) { // north then west but west first
	            for (int i = x; i > x - I.xdim; i--) {
	                for (int j = y; j < y + I.ydim; j++) {
	                	g.table[j][i] = I.numS;
	                }   
	            }
	            tempx = x-I.xdim+1;
	            tempy = y-I.ydim+1;
	      } else if (orient == 21) { // northeast east first
		        for (int i = x; i < x + I.xdim; i++) {
		            for (int j = y; j > y - I.ydim; j--) {
		            	g.table[j][i] = I.numS;
		             }
		         }
		        tempy = y-I.ydim+1;
	      } else if (orient == 23) { // southeast east first
	           for (int i = x; i < x + I.xdim; i++) {
		           for (int j = y; j < y + I.ydim; j++) {
		        	   g.table[j][i] = I.numS;
		              }
		       }
		  } else if (orient == 32) { // south east
			  System.out.println(x + "  "  + y);
		        for (int i = x; i < x + I.xdim; i++) {
			        for (int j = y; j < y + I.ydim; j++) {
			        	g.table[j][i] = I.numS;
			        }
			    }
		  } else if (orient == 34) { // south west
		        for (int i = x; i > x - I.xdim; i--) {
			        for (int j = y; j < y + I.ydim; j++) {
			        	g.table[j][i] = I.numS;
			        }
			    }
		        tempx = x-I.xdim+1;
		  } else if (orient == 41) { // west north
		        for (int i = x; i > x - I.xdim; i--) {
			        for (int j = y; j > y - I.ydim; j--) {
			        	g.table[j][i] = I.numS;
			        }
			    }
		        tempx = x-I.xdim+1;
		        tempy = y-I.ydim+1;
		  } else if (orient == 43) { // South then west but west first
		        for (int i = x; i > x - I.xdim; i--) {
			        for (int j = y; j < y + I.ydim; j++) {
			        	g.table[j][i] = I.numS;
			        }
			    }
		        tempx = x-I.xdim+1;
		  }
	    I.placedx = tempx;
	    n.getArr()[ind].placedx = tempx;
	    n.getArr()[ind].placedy = tempy;
	    n.getPlacement()[ind][0] = tempx;
	    n.getPlacement()[ind][1] = tempy;
	    return true;
    }
	
	
	
	
	
	
    public void placeItem2(String[][] mat, Item I, int orient, int y, int x) {
    	//System.out.println("placing");
    	//System.out.println(orient);
	    if (orient == 12) { // north then east but east first
	        for (int i = x; i < x + I.xdim; i++) {
	            for (int j = y; j < y + I.ydim; j++) {
	                mat[j][i] = I.numS;
	             }
	         }
	      } else if (orient == 14) { // north then west but west first
	            for (int i = x; i < x - I.xdim; i++) {
	                for (int j = y; j < y + I.ydim; j++) {
	                    mat[j][i] = I.numS;
	                }
	            }
	      } else if (orient == 21) { // northeast east first
	           for (int i = x; i < x + I.xdim; i++) {
	               for (int j = y; j < y + I.ydim; j++) {
	                  mat[j][i] = I.numS;
	              }
	           }
	      } else if (orient == 23) { // southeast but west first
	           for (int i = x; i < x + I.xdim; i++) {
		           for (int j = y; j < y - I.ydim; j++) {
		               mat[j][i] = I.numS;
		              }
		       }
		  } else if (orient == 32) { // south east
		        for (int i = x; i < x + I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
			            mat[j][i] = I.numS;
			        }
			    }
		  } else if (orient == 34) { // south west
			  System.out.println("placing123");
			  System.out.println(x + "  <- that is x");
			  System.out.println(y + "  <- that is y");
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
			        	System.out.println("placing");
			            mat[j][i] = I.numS;
			        }
			    }
		  } else if (orient == 41) { // west north
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y + I.ydim; j++) {
			            mat[j][i] = I.numS;
			        }
			    }
		  } else if (orient == 43) { // north then west but west first
		        for (int i = x; i < x - I.xdim; i++) {
			        for (int j = y; j < y - I.ydim; j++) {
			            mat[j][i] = I.numS;
			        }
			    }
		  }
    }
   
	//update if the item will fit place it
	public static void sweep(int x, int y, int i, int j, Grid g){
		
	}

	
	// (Matt) - finds the corners of the space remaining in the box
	   public int[][] findCorners(String matrix[][]) {
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
	                  cornerdata[cornerdata[0][0]][2] = 3; // 2 means east x west
	                  cornerdata[cornerdata[0][0]][3] = 4; // 3 means south is one possible orientation
	                  //Corner corner3 = new Corner(row, column);
	               } else if (matrix[row - 1][column - 1] != "00" && matrix[row - 1][column] != "00" && matrix[row][column - 1] != "00") {
	                  cornerdata[0][0]++;
	                  cornerdata[cornerdata[0][0]][0] = row;
	                  cornerdata[cornerdata[0][0]][1] = column;
	                  cornerdata[cornerdata[0][0]][2] = 3; // 3 means south
	                  cornerdata[cornerdata[0][0]][3] = 2; // 4 means west x east
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
		   //System.out.println(I.xdim);
		   System.out.println(I.numS + "-THE ITEM");
		   //printArray(g.table);
		   for(int i = xloc; i < xloc+I.xdim; i++){
			   while(g.table[yloc-h][i] == "00"){
				   //System.out.println("went in");
				   total++;
				   h++;

			   }
			   h = 1;
			   //System.out.println(total + "<what is this1");
		   }
		   for(int j = xloc; j < xloc+I.xdim; j++){
			   while(g.table[yloc+I.ydim+h-1][j] == "00"){
				   total++;
				   h++;

			   }
			   h = 1;
			   //System.out.println(total + "<what is this2");
		   }
		   for(int k = yloc; k < yloc+I.ydim; k++){
			   while(g.table[k][xloc-h] == "00"){
				   total++;
				   h++;
			   }
			   h = 1;
			   //System.out.println(total + "<what is this3");
		   }
		   for(int l = yloc; l < yloc+I.ydim; l++){
			   while(g.table[l][xloc+I.xdim+h-1] == "00"){
				   total++;
				   h++;
				   //System.out.println(l);
			   }
			   h = 1;
			   //System.out.println(total + "<what is this4");
		   }
		   
		   //return total+I.xdim+I.ydim;//wow hacked to work be careful and figure out what happened
		   return total;
	   }
	   
	   public int totalWhitespace(Node n){
		  // System.out.println("HWEREWWEJREWRR");
		   //printArray(n.placement);
		   int total = 0;
		   for(int i = 0; i < n.getArr().length; i++){
	        	 n.getArr()[i].xdim = n.getPlacement()[i][2];
	        	 n.getArr()[i].ydim = n.getPlacement()[i][3];
	        	 n.getArr()[i].zdim = n.getPlacement()[i][4];
	        	 System.out.println(n.getPlacement()[1][2] + "HEREE999999");
			   total += countWhitespace(n.g, n.getPlacement()[i][0],n.getPlacement()[i][1], n.getArr()[i]);
			   System.out.println(n.currIndex+"<<<<INDEX");
			   //System.out.println(n.getPlacement()[2][1]);
			   //System.out.println(total + "<TOTALPARTIAL");
		   }
		   return total;

	   }
	   
	   
	   public static void printArray(int matrix[][]) {
		    for (int row = 0; row < matrix.length; row++) {
		        for (int column = 0; column < matrix[row].length; column++) {
		            System.out.print(matrix[row][column] + " ");
		        }
		        System.out.println();
		    }
		}
		public static void printArray(String matrix[][]) {

		    for (int row = 0; row < matrix.length; row++) {
		        for (int column = 0; column < matrix[row].length; column++) {
		            System.out.print(matrix[row][column] + " ");
		        }
		        System.out.println();
		    }
		}
}
