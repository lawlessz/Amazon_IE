import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */
public class Node {

	Grid g;
	Grid gradient;
	private static Item[] arr;
	private int[][] placement;
	int currIndex = 0;
	List<Node> Nodes = new ArrayList<Node>();
	boolean isComplete;
	Node parentNode;
	
	
	int whitespace;
	int AmazonBoxVolumeCM3;
	int smallestVolume;
	int startingVol;
	int ogGridVol;
	
	int closex;
	int closey;
	int closez;
	
	//root node constructor
	public Node(Grid gx, Item[] arr, int currIndex){
		this.g = gx;
		this.gradient = gx;
		this.setArr(arr);
		this.currIndex = currIndex;
		setPlacement(new int[arr.length][9]);//second to last spot for whitespace bool // last spot for is stacked
		this.ogGridVol = ogGridVol;
		this.startingVol = startingVol; // startingvolA is final vol
	}
	
	private Item[] copyArr(Item[] arr2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node(Grid g, Item[] arr, int currIndex, Node parent, int[][] placementHold, Grid gradient){
		this.g = g;
		this.gradient = gradient;
		this.setArr(arr);
		this.currIndex = currIndex;
		//this.Nodes = nodes;
		this.parentNode = parent;
		placement = cloneArray(placementHold); 
		//setPlacement(placementHold);//5 item run
		this.ogGridVol = ogGridVol;
		this.startingVol = parent.startingVol;
	}
	
	
	public String toString(){
		
		printGrid(g.table);
	    return Integer.toString(whitespace);
	}
	
	public static void printGrid(String matrix[][]) {

	    for (int row = 0; row < matrix.length; row++) {
	        for (int column = 0; column < matrix[row].length; column++) {
	            System.out.print(matrix[row][column] + " ");
	        }
	        System.out.println();
	    }

	}

	public Item[] getArr() {
		return arr;
	}

	public void setArr(Item[] arr) {
		this.arr = arr;
	}

	public int[][] getPlacement() {
		return placement;
	}

	public void setPlacement(int[][] placement) {
		this.placement = placement;
	}
	
	
	public static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	
	//xysort probably closer to solution
	//XY sort, currently, can be modified with item comparator to support more xyz comparisons
	public static void customSort() {
		Item[] copyOf = deepCloneArr(arr);
		List<Item> hold = Arrays.asList(copyOf);
		Collections.sort(hold,  new itemComparator());
		Item[] arrNew = hold.toArray(new Item[hold.size()]);
		//Item[] array = copyOf.toArray(hold);
		//List<Item> hold = new ArrayList<Item>();
		for (int i = 0; i < copyOf.length; i++){
			//int x = arr[i].xdim*arr[i].ydim;
			//sorted[temp.indexOf(x)] = arr[i];
			arr[i].xdim = arrNew[i].xdim;
			arr[i].ydim = arrNew[i].ydim;
			arr[i].zdim = arrNew[i].zdim;  
			arr[i].xdim2 = arrNew[i].xdim2;
			arr[i].ydim2 = arrNew[i].ydim2;
			arr[i].zdim2 = arrNew[i].zdim2;
			arr[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
		}

		
		
		
		
		
		}
		
	//xy only
	public void defaultAreaSortInNode() {
		Item[] copyOf = deepCloneArr(arr);
		Item[] sorted = new Item[copyOf.length];
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < copyOf.length; i++){
			temp.add(copyOf[i].xdim*copyOf[i].ydim);
		}
		Collections.sort(temp);
		Collections.reverse(temp);
		for (int i = 0; i < copyOf.length; i++){
			int x = copyOf[i].xdim*copyOf[i].ydim;
			sorted[temp.indexOf(x)] = copyOf[i];
			copyOf[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
		}
		for (int i = 0; i < copyOf.length; i++){
			//int x = arr[i].xdim*arr[i].ydim;
			//sorted[temp.indexOf(x)] = arr[i];
			arr[i].xdim = sorted[i].xdim;
			arr[i].ydim = sorted[i].ydim;
			arr[i].zdim = sorted[i].zdim;  
			arr[i].xdim2 = sorted[i].xdim2;
			arr[i].ydim2 = sorted[i].ydim2;
			arr[i].zdim2 = sorted[i].zdim2;
			arr[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
		}
		
		//for(int i = 0; i < sorted.length; i++){
		//	sorted[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
			//System.out.println(I[i].numS + " <This is the item number list");
		//}
			arr = sorted;
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
