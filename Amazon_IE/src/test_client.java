import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Item methods need to eventually be moved to the item class...
public class test_client {
public static int numberOUT = 0;
	
	public static void main(String [] args) {
		
	    Item i1 = new Item(10,5,4);
	    Item i2 = new Item(5,5,6);
	    Item i3 = new Item(8,5,4);
	    Item i4 = new Item(4,5,3);
	    Item i5 = new Item(2,8,2);
	    Item[] arr = new Item[5];
	    
	    arr[0] = i1;arr[1] = i2;arr[2] = i3;arr[3] = i4;arr[4] = i5;
	    
		Grid x = new Grid(48,33,10,1);
		Algorithm A = new Algorithm(x.table);
		
		Grid sandbox = new Grid(10,10,10,1);
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
		A.placeItem(x,i1,32,1,1,rootx);


		rootx.parentNode = rootx;  //root's parent node is itself
		rootx.placement[rootx.currIndex-1][0] = 1;
		rootx.placement[rootx.currIndex-1][1] = 1;
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
		testRun(rootx.arr, rootx.g, rootx);
		
		Result R = new Result();
		//updateResult(rootx, R);
		updateAll(rootx, R);
		//printAllFinished(rootx);
		Collections.sort(R.listN, new whitespaceComparator());
		//System.out.println(R.listN.get(0).toString());
		//Collections.sort(ArrayList<Node> R.listN);
		printList(R.listN);
	}
	
	
	
	



	private static void printList(ArrayList<Node> listN) {
		for(Node n : listN){
			System.out.println(n.toString());
		}
		
	}







	public static void testRun(Item[] arr, Grid g, Node n) {
		
		//if(n.currIndex != arr.length-1){
		Algorithm a = new Algorithm(g.table);
		for(int i = n.currIndex; i < arr.length; i++){
			int[][] cornersCurr = a.findCorners(n.g.table);
		      for (int j = 1; j <= cornersCurr[0][0]; j++) {
		  		//if(n.currIndex || > i){
				//	break;
				//}
		    	     int y = cornersCurr[j][0];
		    	     //System.out.println(x + "<x_");
		    	     int x = cornersCurr[j][1];
			         String s = String.valueOf(cornersCurr[j][2]) + String.valueOf(cornersCurr[j][3]);
			         int c = Integer.parseInt(s);
			         //System.out.println(cornersCurr[0][0]);
			         System.out.println(x);
		        	 System.out.println(y);
		        	 System.out.println(a.checkPlace(g, arr[i], c, x, y));
			         if(a.checkPlace(g, arr[i], c, x, y)){
			        	 
			        	 System.out.println("madeIT");
			        	 System.out.println(arr[i].orderNum);
			        	 //public Node(Grid g, Item[] arr, int currIndex, List<Node> nodes, Node parent){
			        	 Grid gnew = new Grid(g);
			        	 
			        	 
			        	 
			        	 
			        	 Node newNode = new Node(gnew, arr, n.currIndex+1, n, n.placement);

			        	 a.placeItem(newNode.g, arr[i], c, x, y, newNode);
			        	
			        	 newNode.placement[n.currIndex][0] = y;
			        	 newNode.placement[n.currIndex][1] = x;
			        	 //System.out.println(x);
			        	// System.out.println(y);
			        	// printArray(newNode.g.table);
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
			        	 //printArray(newNode.g.table);
                         //System.out.println(cornersCurr[i+1][0]);

                         testRun(newNode.arr, newNode.g, newNode);
	         		  	 //if(n.currIndex == newNode.arr.length-1 || newNode.currIndex == newNode.arr.length-1){
	         			//	break;
	         			//}

			         }
			      }
		      n.currIndex++;
			}
	//}
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
		if(root.Nodes.size() == 0){
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

	

	
}
