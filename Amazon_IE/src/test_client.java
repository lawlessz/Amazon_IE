

//NEW CHANGE
public class test_client {

	
	public static void main(String [] args) {
	
		
		//System.out.println("here");
		Grid x = new Grid(15,15,10,1);
		printArray(x.table);
		
	}
	
	public static void printArray(int matrix[][]) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int column = 0; column < matrix[row].length; column++) {
	            System.out.print(matrix[row][column] + " ");
	        }
	        System.out.println();
	    }
	}
	
}
