import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Item methods need to eventually be moved to the item class...
public class test_client implements java.io.Serializable {
public static int numberOUT = 0;
	
	public static void main(String [] args) {
		
	    Item i1 = new Item(10,5,4);
	    Item i2 = new Item(5,5,6);
	    Item i3 = new Item(8,5,4);
	    Item i4 = new Item(4,5,3);
	    Item i5 = new Item(2,8,2);
	    Item[] arr = new Item[5];
	    
	    arr[0] = i1;arr[1] = i2;arr[2] = i3;arr[3] = i4;arr[4] = i5;
	    
		//Grid x = new Grid(48,33,10,1);
		Grid x = new Grid(20,20,10,1);
		Algorithm A = new Algorithm(x.table);
		
		//Grid sandbox = new Grid(10,10,10,1);
		//Algorithm b = new Algorithm(sandbox.table);
		//A.placeItem(sandbox.table,arr[3],12,4,2);
		//A.placeItem(sandbox.table,i5,12,9,2);
		//int space2 = b.countWhitespace(sandbox, 4, 2, arr[3]);
		//System.out.println(space2);
		//printArray(sandbox.table);
		
		arr = defaultAreaSort(arr);//sort by area
		updateItemNumbers(arr);//update numbers
		//printItemsArea(arr);
		
		//A.find_Empty(x, i1);
		
		Node rootx = new Node(x, arr, 1);  //new node that is a root
		A.placeItem(x,i1,32,1,1,rootx,rootx.currIndex);
		i1.placedx = 1;
		i1.placedy = 1;
		

		rootx.parentNode = rootx;  //root's parent node is itself
		rootx.getPlacement()[rootx.currIndex-1][0] = 1;
		rootx.getPlacement()[rootx.currIndex-1][1] = 1;
		rootx.getPlacement()[rootx.currIndex-1][2] = 10;
		rootx.getPlacement()[rootx.currIndex-1][3] = 5;
		rootx.getPlacement()[rootx.currIndex-1][4] = 4;
		//A.placeItem(x.table,i3,12,15,4);
		//A.placeItem(x.table,i5,12,28,4);
		//int space1 = A.countWhitespace(x, 5, 5, i1);
		//System.out.println(space1);
		
		//System.out.println(A.checkPlace(x.table,i1,23,1,11));
		
		printArray(x.table);
		int [][] corners = A.findCorners(x.table);
		System.out.println(A.checkPlace(x,arr[2], 23, 1, 1));
		//checkPlace(String[][] mat, Item I, int orient, int x, int y) {
		System.out.println(A.checkPlace(x,i1,43,11,1) + "DDD");
		testRun(rootx.getArr(), rootx.g, rootx);
		
		Result R = new Result();
		
		//updateResult(rootx, R);
		
		
		updateAll(rootx, R);
		
		//printAllFinished(rootx);
		
		Collections.sort(R.listN, new whitespaceComparator());

		//printList(R.listN);
		ReadExcelFileV2 XFILE = new ReadExcelFileV2();
		XFILE.readExcel();
	}
	
	
	public static void orderOnAllBox(){
		
	}
	
	




	public static void testRun(Item[] arr, Grid g, Node n) {
		
		Algorithm a = new Algorithm(g.table);
		for(int i = n.currIndex; i < arr.length; i++){
			int[][] cornersCurr = a.findCorners(n.g.table);
		      for (int j = 1; j <= cornersCurr[0][0]; j++) {
		    	     int y = cornersCurr[j][0];
		    	     System.out.println(y + "<-THIS IS Y");
		    	     int x = cornersCurr[j][1];
			         String s = String.valueOf(cornersCurr[j][2]) + String.valueOf(cornersCurr[j][3]);
			         int c = Integer.parseInt(s);
			         
			        // n.arr[i].setX(n.arr[i].xdim2);
			        // n.arr[i].setY(n.arr[i].ydim2);
			        // n.arr[i].setZ(n.arr[i].zdim2);
			         
			         if(a.checkPlace(g, n.getArr()[i], c, x, y)){//xy
			        	 Grid gnew = new Grid(g);
			        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement());
			        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, x, y, newNode, newNode.currIndex-1);
			        	 System.out.println(newNode.getArr()[newNode.currIndex-1].placedy + "Placedy");
			        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
			        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
			        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
			        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
			        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         int xh = n.getArr()[i].xdim;
			         n.getArr()[i].setX(n.getArr()[i].zdim);
			         n.getArr()[i].setZ(n.getArr()[i].xdim);
			         if(a.checkPlace(g, n.getArr()[i], c, x, y)){//zy
			        	 Grid gnew = new Grid(g);
			        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement());
			        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, x, y, newNode, newNode.currIndex-1);
			        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
			        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
			        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
			        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
			        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
			        	 //System.out.println(newNode.getArr()[newNode.currIndex-1].placedy + "Placedy");
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         int hold = n.getArr()[i].zdim;
			         n.getArr()[i].setZ(n.getArr()[i].ydim);
			         n.getArr()[i].setY(hold);
			         if(a.checkPlace(g, n.getArr()[i], c, x, y)){//zx
			        	 Grid gnew = new Grid(g);
			        	 Node newNode = new Node(gnew, n.getArr().clone(), n.currIndex+1, n, n.getPlacement());
			        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, x, y, newNode, newNode.currIndex-1);
			        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
			        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
			        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
			        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
			        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
			        	 System.out.println(newNode.getArr()[newNode.currIndex-1].placedy + "Placedy");
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);

			      }
		      n.currIndex++;
			}
	}
	
	//recursive method to be made to print all finished
	public static void printAllFinished(Node root){
		Algorithm a = new Algorithm(null);
		if(root.Nodes.size() == 0){
			printArray(root.g.table);
			//root.whitespace = a.totalWhitespace(root);
			//System.out.println(a.totalWhitespace(root)+"<Whitespace of ^");

		}
		for(int i = 0; i < root.Nodes.size(); i++){
			printAllFinished(root.Nodes.get(i));
		}
	}
	
	public static void updateAll(Node root, Result R){
		Algorithm a = new Algorithm(null);
		System.out.println(root.currIndex + "DSFFDSSFASDFSDSFSD");
		System.out.println(root.getArr().length);
		if(root.Nodes.size() == 0 &&  root.getPlacement()[root.getArr().length-1][0] != 0){//has to be completed
			//printArray(root.g.table);
			root.whitespace = a.totalWhitespace(root);
		//System.out.println(a.totalWhitespace(root)+"<Whitespace of ^");
			R.listN.add(root);
		}
		for(int i = 0; i < root.Nodes.size(); i++){
			updateAll(root.Nodes.get(i), R);
		}
	}
	
	public static void assignWhiteSpace(Node root){
		
		
	}
	public static void place(){
		
	}
	
	//sorts items puts biggest area first - to be moved
	public static Item[] defaultAreaSort(Item[] items) {
		Item[] sorted = new Item[items.length];
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < items.length; i++){
			temp.add(items[i].xdim*items[i].ydim);
		}
		Collections.sort(temp);
		Collections.reverse(temp);
		for(int i = 0; i < items.length; i++){
			int x = items[i].xdim*items[i].ydim;
			sorted[temp.indexOf(x)] = items[i];
		}
		return sorted;
	}
	
	//to be moved to item class
	public static void printItemsArea(Item[] I){
		for(int i = 0; i < I.length; i++){
			System.out.println(I[i].xdim*I[i].ydim);
			
		}
	}
	
	//to be moved to grid class?
	public static void printArray(String matrix[][]) {

	    for (int row = 0; row < matrix.length; row++) {
	        for (int column = 0; column < matrix[row].length; column++) {
	            System.out.print(matrix[row][column] + " ");
	        }
	        System.out.println();
	    }
		numberOUT++;
		System.out.println("NumberOUT = " + numberOUT);
	}
	
	//to be moved to item class
	public static void updateItemNumbers(Item[] I){
		for(int i = 0; i < I.length; i++){
			I[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
			//System.out.println(I[i].numS);
		}

	}
	public static void printList(ArrayList<Node> listN){
		Algorithm A = new Algorithm(null);
		int c = 1;
		for(Node n : listN){
			c++;
			if(c<=1000){//limiter
			printArray(n.g.table);
		    //System.out.println(n.arr[]);
			System.out.println(A.totalWhitespace(n)+"<Whitespace of ^");
			} else{
				break;
			}
			
			
		}
	}
	
	public static Item[] deepCloneArr(Item[] arr){
		Item [] arrNew = new Item[arr.length];
		for(int i = 0; i < arr.length; i++){
			arrNew[i] = (Item) copy(arr[i]);
		}
		return arrNew;
	}
	
	/**
	 * This method makes a "deep clone" of any Java object it is given.
	 */
	   public static Object copy(Object orig) {
	        Object obj = null;
	        try {
	            // Write the object out to a byte array
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            ObjectOutputStream out = new ObjectOutputStream(bos);
	            out.writeObject(orig);
	            out.flush();
	            out.close();

	            // Make an input stream from the byte array and read
	            // a copy of the object back in.
	            ObjectInputStream in = new ObjectInputStream(
	                new ByteArrayInputStream(bos.toByteArray()));
	            obj = in.readObject();
	        }
	        catch(IOException e) {
	            e.printStackTrace();
	        }
	        catch(ClassNotFoundException cnfe) {
	            cnfe.printStackTrace();
	        }
	        return obj;
	    }
	  
	  
		}



//System.out.println(cornersCurr[0][0]);
//System.out.println(x);
//System.out.println(y);
//System.out.println(a.checkPlace(g, arr[i], c, x, y));
