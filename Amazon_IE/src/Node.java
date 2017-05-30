import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {

	Grid g;
	Grid gradient;
	private Item[] arr;
	private int[][] placement;
	int currIndex = 0;
	List<Node> Nodes = new ArrayList<Node>();
	boolean isComplete;
	Node parentNode;
	
	
	int whitespace;
	int AmazonBoxVolumeCM3;
	int smallestVolume;
	
	int closex;
	int closey;
	int closez;
	
	//root node constructor
	public Node(Grid gx, Item[] arr, int currIndex){
		this.g = gx;
		this.gradient = gx;
		this.setArr(arr);
		this.currIndex = currIndex;
		setPlacement(new int[arr.length][7]);//second to last spot for whitespace bool // last spot for is stacked

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
	
	

	
}
