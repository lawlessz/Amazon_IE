import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Item methods need to eventually be moved to the item class...
public class test_client {

	
	public static void main(String [] args) {
		
	    Item i1 = new Item(10,5,0);
	    Item i2 = new Item(5,5,0);
	    Item i3 = new Item(8,5,0);
	    Item i4 = new Item(4,5,0);
	    Item i5 = new Item(2,8,0);
	    Item[] arr = new Item[5];
	    
	    arr[0] = i1;arr[1] = i2;arr[2] = i3;arr[3] = i4;arr[4] = i5;
	    
		Grid x = new Grid(33,48,10,1);
		Algorithm A = new Algorithm(x.table);
		
		

		arr = defaultAreaSort(arr);//sort by area
		updateItemNumbers(arr);//update numbers
		//printItemsArea(arr);
		
		A.find_Empty(x, i1);
		A.placeItem(x.table,i1,12,1,1);
		int space1 = A.countWhitespace(x, 1, 1, i1);
		System.out.println(space1);
		
		
		printArray(x.table);
		int [][] corners = A.findCorners(x.table);
		
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
	}
	
	//to be moved to item class
	public static void updateItemNumbers(Item[] I){
		for(int i = 0; i < I.length; i++){
			I[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
			//System.out.println(I[i].numS);
		}
	}
}
