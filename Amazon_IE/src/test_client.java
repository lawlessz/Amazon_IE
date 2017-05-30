import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//Item methods need to eventually be moved to the item class...
public class test_client implements java.io.Serializable {
public static int numberOUT = 0;
	
	public static void main(String [] args) throws IOException {
		/*
	    Item i1 = new Item(10,5,4);
	    Item i2 = new Item(5,5,6);
	    Item i3 = new Item(8,5,4);
	    Item i4 = new Item(4,5,3);
	    Item i5 = new Item(2,8,2);
	    */
	    Item i1 = new Item(5,5,5);
	    Item i2 = new Item(6,5,5);
	    Item i3 = new Item(7,5,5);
	    Item i4 = new Item(8,5,5);
	    //Item i5 = new Item(9,5,5);
	    
	    
	    Item[] arr = new Item[4];
	    
	    arr[0] = i1;arr[1] = i2;arr[2] = i3;arr[3] = i4;//arr[4] = i5;
	    
		//Grid x = new Grid(48,33,10,1);
		Grid x = new Grid(20,20,10,1, 20*20*10);
		//Grid xgrade = new Grid(20,20,10,1);
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
		A.placeItem(x,i4,32,1,1,rootx,rootx.currIndex);
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
		//System.out.println(A.checkPlace(x,arr[2], 23, 1, 1));
		//checkPlace(String[][] mat, Item I, int orient, int x, int y) {
		//System.out.println(A.checkPlace(x,i1,43,11,1) + "DDD");
		
		
		testRun(rootx.getArr(), rootx.g, rootx);
		
		Result R = new Result();
		updateAll(rootx, R);
		
		//printAllFinished(rootx);
		
		Collections.sort(R.listN, new whitespaceComparator());

		printList(R.listN);
		ReadExcelFileV2 XFILE = new ReadExcelFileV2();
		
		List<Order> orders = XFILE.readExcel();
		int[][] boxes = XFILE.fetchBoxes();
		
		int[][] sBoxes = new int [3][6];
		sBoxes[0][1] = boxes[0][1];
		sBoxes[0][2] = boxes[0][2];
		sBoxes[0][3] = boxes[0][3];
		sBoxes[0][4] = boxes[0][4];
		sBoxes[0][5] = boxes[0][5];
		sBoxes[1][1] = boxes[10][1];
		sBoxes[1][2] = boxes[10][2];
		sBoxes[1][3] = boxes[10][3];
		sBoxes[1][4] = boxes[10][4];
		sBoxes[1][5] = boxes[10][5];
		sBoxes[2][1] = boxes[20][1];
		sBoxes[2][2] = boxes[20][2];
		sBoxes[2][3] = boxes[20][3];
		sBoxes[2][4] = boxes[20][4];
		sBoxes[2][5] = boxes[20][5];
		
		int[][] s2Boxes = new int [1][6];
		sBoxes[0][1] = boxes[20][1];
		sBoxes[0][2] = boxes[20][2];
		sBoxes[0][3] = boxes[20][3];
		sBoxes[0][4] = boxes[20][4];
		sBoxes[0][5] = boxes[20][5];
		
		//System.out.println(orders.get(2).arr[1]);
		
		//for (Order element : orders) {
		//	for(int i = 0; i < element.arr.length; i++){
				
		//		System.out.println(element.arr[i].xdim + "   -" + i);
		//	}
			
		//}
		//orderOnAllBox(orders, boxes); send all boxes;
		orderOnAllBox(orders, boxes);
	}
	
	
public static void orderOnAllBox(List<Order> orders, int[][] boxes) throws IOException{
	
		List<Order> newO = new ArrayList<Order>();
		newO.addAll(orders);
		
		for (Order element : newO) {
			//for(int i = 0; i < element.arr.length; i++){
				boolean packed1 = false;
				boolean packed2 = false;
				boolean packed3 = false;

				Item[] arr1 = deepCloneArr(element.arr);
				Item[] arrx2 = deepCloneArr(element.arr);
				Item[] arrx3 = deepCloneArr(element.arr);
				for(int j = 0; j < boxes.length; j++) {
					//System.out.println("HERE");
					
				
					Grid x = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4]);
					Item[] arr = deepCloneArr(arr1);
					Algorithm A = new Algorithm(x.table);
					Node rootx = new Node(x, arr, 1);  //new node that is a root
					defaultAreaSort(rootx.getArr());
					updateItemNumbers(rootx.getArr());//update numbers

					//rootx.g.index++;
					
					//System.out.println(rootx.getArr()[0].numS + " BAA  " + rootx.g.index);
					//System.out.println(rootx.getArr()[0].xdim + "<XDIM");
					//System.out.println(rootx.getArr()[0].ydim + "<YDIM");
					//System.out.println(rootx.getArr()[0].zdim + "<ZDIM");
				
					if(A.checkPlace(rootx.g, rootx.getArr()[0], 32, 1, 1, rootx.getArr()) && packed1 == false) {
						//System.out.println("THERE");
						A.placeItem(rootx.g,rootx.getArr()[0],32,1,1,rootx,rootx.currIndex);
						//rootx.currIndex++;
			        	 rootx.getPlacement()[rootx.currIndex-1][0] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][1] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][2] = rootx.getArr()[0].xdim;
			        	 rootx.getPlacement()[rootx.currIndex-1][3] = rootx.getArr()[0].ydim;
			        	 rootx.getPlacement()[rootx.currIndex-1][4] = rootx.getArr()[0].zdim;
			        	 rootx.getPlacement()[rootx.currIndex-1][5] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][5] = 0;
						testRun(rootx.getArr(), rootx.g, rootx);
						//System.out.println(rootx.getPlacement()[1][0] + "<X");
						//System.out.println(rootx.getPlacement()[1][1] + "<Y");
						Result R = new Result();
						updateAll(rootx, R);
						Collections.sort(R.listN, new whitespaceComparator());
						if(!R.listN.isEmpty()){
							element.AmazonVol = R.listN.get(0).AmazonBoxVolumeCM3;//set values for the order back to excel
							element.foundVol = R.listN.get(0).smallestVolume;
							//System.out.println(element.foundVol + "FOUNDVOL");
							
							printList(R.listN);
							System.out.println("-------------------Order out-------------------");
							System.out.println("------------------------------------------------------------------");
							System.out.println("------------------------------------------------------------------");
							packed1 = true;
							
				            for(int i = 0; i <R.listN.get(0).getPlacement().length; i++){
				            	//System.out.println(R.listN.get(0).getPlacement()[i][2] + "<X");
				            	//System.out.println(R.listN.get(0).getPlacement()[i][3] + "<Y");
				            	//System.out.println(R.listN.get(0).getPlacement()[i][4] + "<Z");
				            	//System.out.println(R.listN.get(0).getPlacement()[i][4]*R.listN.get(0).getPlacement()[i][4]*R.listN.get(0).getPlacement()[i][2] + "VOL"  );
				            	
				            }
							//i = 100;//back out of loop to update later
							
						}
					
					}
					


					
					
					
					
				}
				
				
			}
				//updateAll(rootx, R);
			//ReadExcelFileV2 write = new ReadExcelFileV2();
			//List<Order> newOO = newO;
			//write.writeExcel(newO);
			printDiff(newO);
		}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





	//Recursive method to create tree structure, simple in theory but complex in practice
	public static void testRun(Item[] arr, Grid g, Node n) {
		//System.out.println("GOING IN TEST RUN");
		Algorithm a = new Algorithm(g.table);
		for(int i = n.currIndex; i < arr.length; i++){
			int[][] cornersCurr = a.findCorners(n.g.table);
			
		      for (int j = 1; j <= cornersCurr[0][0]; j++) {
		    	     int y = cornersCurr[j][0];
		    	     int x = cornersCurr[j][1];
			         String s = String.valueOf(cornersCurr[j][2]) + String.valueOf(cornersCurr[j][3]);
			         int c = Integer.parseInt(s);
			        // n.arr[i].setX(n.arr[i].xdim2);
			        // n.arr[i].setY(n.arr[i].ydim2);
			        // n.arr[i].setZ(n.arr[i].zdim2);
			         int[][] cornersForStack = findStackingOptions(n);
			         if(a.checkPlace(g, n.getArr()[i], c, x, y,n.getArr())){//xy
			        	 Grid gnew = new Grid(g);
			        	 Grid gradientNew = new Grid(n.gradient);
			        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement(), gradientNew);
			        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, x, y, newNode, newNode.currIndex-1);
			        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
			        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
			        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
			        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
			        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
			        	 newNode.getPlacement()[n.currIndex][5] = 1;
			        	 newNode.getPlacement()[n.currIndex][6] = 0;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         //
			         

			         
			         
			         int xh = n.getArr()[i].xdim;
			         n.getArr()[i].setX(n.getArr()[i].zdim);
			         n.getArr()[i].setZ(xh);
			         if(a.checkPlace(g, n.getArr()[i], c, x, y, n.getArr())){//zy
			        	 Grid gnew = new Grid(g);
			        	 Grid gradientNew = new Grid(n.gradient);
			        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement(), gradientNew);
			        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, x, y, newNode, newNode.currIndex-1);
			        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
			        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
			        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
			        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
			        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
			        	 newNode.getPlacement()[n.currIndex][5] = 1;
			        	 newNode.getPlacement()[n.currIndex][6] = 0;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         //	
			         
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         int hold = n.getArr()[i].zdim;
			         n.getArr()[i].setZ(n.getArr()[i].ydim);
			         n.getArr()[i].setY(hold);
			         if(a.checkPlace(g, n.getArr()[i], c, x, y, n.getArr())){//zx
			        	 Grid gnew = new Grid(g);
			        	 Grid gradientNew = new Grid(n.gradient);
			        	 Node newNode = new Node(gnew, n.getArr().clone(), n.currIndex+1, n, n.getPlacement(), gradientNew);
			        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, x, y, newNode, newNode.currIndex-1);
			        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
			        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
			        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
			        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
			        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
			        	 newNode.getPlacement()[n.currIndex][5] = 1;
			        	 newNode.getPlacement()[n.currIndex][6] = 0;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         //
			         
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);

			      }
		      
		      int c = 32;
		        int[][] cornersForStack = findStackingOptions(n);
		        cornersForStack = findStackingOptions(n);
	         	if(cornersForStack != null){
	         		for(int q = 0; q<cornersForStack.length; q++) {
	         			//System.out.println(q);
		         		if(a.checkStackPlace(g, n.getArr()[i], c, cornersForStack[q][0], cornersForStack[q][1],n.getArr(), n)) {
				        	 Grid gnew = new Grid(g);
				        	 Grid gradientNew = new Grid(n.gradient);
				        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement(), gradientNew);
				        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, cornersForStack[q][0], cornersForStack[q][1], newNode, newNode.currIndex-1);
				        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
				        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
				        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
				        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
				        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
				        	 newNode.getPlacement()[n.currIndex][5] = 0;
				        	 newNode.getPlacement()[n.currIndex][6] = 0;
				        	 newNode.parentNode = n;
				        	 n.Nodes.add(newNode);
	                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
		         		}
	         		}
	         	}
	         	
		         
		         int xh = n.getArr()[i].xdim;
		         n.getArr()[i].setX(n.getArr()[i].zdim);
		         n.getArr()[i].setZ(xh);
	         	
	         	
			        cornersForStack = findStackingOptions(n);
		         	if(cornersForStack != null){
		         		for(int q = 0; q<cornersForStack.length; q++) {
		         			//System.out.println(q);
			         		if(a.checkStackPlace(g, n.getArr()[i], c, cornersForStack[q][0], cornersForStack[q][1],n.getArr(), n)) {
					        	 Grid gnew = new Grid(g);
					        	 Grid gradientNew = new Grid(n.gradient);
					        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement(), gradientNew);
					        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, cornersForStack[q][0], cornersForStack[q][1], newNode, newNode.currIndex-1);
					        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
					        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
					        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
					        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
					        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
					        	 newNode.getPlacement()[n.currIndex][5] = 0;
					        	 newNode.getPlacement()[n.currIndex][6] = 0;
					        	 newNode.parentNode = n;
					        	 n.Nodes.add(newNode);
		                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         		}
		         		}
		         	}
		         	
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
		      
			         
				        cornersForStack = findStackingOptions(n);
			         	if(cornersForStack != null){
			         		for(int q = 0; q<cornersForStack.length; q++) {
			         			//System.out.println(q);
				         		if(a.checkStackPlace(g, n.getArr()[i], c, cornersForStack[q][0], cornersForStack[q][1],n.getArr(), n)) {
						        	 Grid gnew = new Grid(g);
						        	 Grid gradientNew = new Grid(n.gradient);
						        	 Node newNode = new Node(gnew, deepCloneArr(n.getArr()), n.currIndex+1, n, n.getPlacement(), gradientNew);
						        	 a.placeItem(newNode.g, newNode.getArr()[newNode.currIndex-1], c, cornersForStack[q][0], cornersForStack[q][1], newNode, newNode.currIndex-1);
						        	 newNode.getPlacement()[n.currIndex][0] = newNode.getArr()[newNode.currIndex-1].placedx;
						        	 newNode.getPlacement()[n.currIndex][1] = newNode.getArr()[newNode.currIndex-1].placedy;
						        	 newNode.getPlacement()[n.currIndex][2] = n.getArr()[i].xdim;
						        	 newNode.getPlacement()[n.currIndex][3] = n.getArr()[i].ydim;
						        	 newNode.getPlacement()[n.currIndex][4] = n.getArr()[i].zdim;
						        	 newNode.getPlacement()[n.currIndex][5] = 0;
						        	 newNode.getPlacement()[n.currIndex][6] = 0;
						        	 newNode.parentNode = n;
						        	 n.Nodes.add(newNode);
			                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
				         		}
			         		}
			         	}
			         	
				         n.getArr()[i].setX(n.getArr()[i].xdim2);
				         n.getArr()[i].setY(n.getArr()[i].ydim2);
				         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			      
		      n.currIndex++;
			}
		
	}
	
	//should be located in Algorithm
	//any top location but top left only on a lone item
	private static int[][] findStackingOptions(Node n) {
		int c = 0;
		int[][] options = new int[100][2];  //100 default
		
		for(int i = 0; i < n.currIndex; i++){
			/*
			System.out.println(n.getArr()[n.currIndex].numS);
			System.out.println(n.getArr()[n.currIndex].xdim);
			System.out.println(n.getArr()[n.currIndex].ydim);
			System.out.println(n.getArr()[n.currIndex].zdim);
			System.out.println(n.getPlacement()[i][0]);
			System.out.println(n.getPlacement()[i][1]);
			System.out.println("-");
			*/
			
			
			//if(n.getPlacement()[i][0] == 0){//if 
				//if(n.g.table[n.getPlacement()[i][1]][n.getPlacement()[i][0]+n.getArr()[i].xdim] != "00"){
				//	if(n.g.table[n.getPlacement()[i][1]][n.getPlacement()[i][0]+n.getArr()[i].xdim] != "00"){
						/*
						options[c][0] = n.getPlacement()[i][0]+n.getArr()[i].xdim;
						options[c][1] = n.getPlacement()[i][1];
						System.out.println(i + "<I");
						System.out.println(i + "<ci");
						System.out.println(n.getArr()[i].numS + " <THE ITEM");
						System.out.println(n.getArr().length);

						System.out.println(n.getPlacement()[i][0]);


						System.out.println(n.getPlacement()[i][0]+n.getArr()[i].xdim);
						System.out.println(n.getPlacement()[i][1]);
						System.out.println("d");
						*/
				//	}
				//}
				
			//}
			
			if(n.getPlacement()[i][0] != 0 && n.getPlacement()[i][1] != 0) {
			options[i][0] = n.getPlacement()[i][0];
			options[i][1] = n.getPlacement()[i][1];
			//System.out.println("THERE");
			//System.out.println(n.getPlacement()[i][0]+" item " + n.getArr()[i].numS);
			//System.out.println(n.getPlacement()[i][1]+" item " + n.getArr()[i].numS);
			//System.out.println("x-y");
			}
		}
			for(int j = 0; j < options.length; j++) {
				if(options[j][0] == 0){
					c = j;
					j = 100+options.length;
					
				}
			}
			int[][] arr2 = new int[c][2];
			for(int q = 0; q < c; q++){
			    arr2[q] = options[q].clone();
			}
	
		return arr2;
	}


	//recursive method to be made to print all finished
	public static void printAllFinished(Node root){
		Algorithm a = new Algorithm(null);


		if(root.Nodes.size() == 0){
			printArray(root.g.table);
			//root.whitespace = a.totalWhitespace(root);
			System.out.println(a.totalWhitespace(root)+"<Whitespace of ^");
			
		}
		for(int i = 0; i < root.Nodes.size(); i++){
			printAllFinished(root.Nodes.get(i));
		}
	}
	
	public static void updateAll(Node root, Result R){
		Algorithm a = new Algorithm(null);
		if(root.Nodes.size() == 0 &&  root.getPlacement()[root.getArr().length-1][0] != 0){//has to be completed
			//printArray(root.g.table);
			root.whitespace = a.totalWhitespace(root);
			root.AmazonBoxVolumeCM3 = root.g.vol;
			a.smallestVolume(root);
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
		for (int i = 0; i < items.length; i++){
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
	
	public static void printGradient(int matrix[][]) {

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
			//System.out.println(I[i].numS + " <This is the item number list");
		}

	}
	public static void printList(ArrayList<Node> listN){
		Algorithm A = new Algorithm(null);
		numberOUT = 0;
		int c = 1;
		for(Node n : listN){
			c++;
			if(c<=2){//limiter
			printArray(n.g.table);
			printGradient(n.gradient.gtable);
			A.smallestVolume(n);
	        int[][] cornersForStack = findStackingOptions(n);
	        System.out.println(n.g.xdimen + " X-DIM OF BOX");
	        System.out.println(n.g.ydimen + " Y-DIM OF BOX");
	        System.out.println(n.g.zdimen + " Z-DIM OF BOX");
	        
	        System.out.println(n.closex + " <  closex");
	        System.out.println(n.closey + " <  closey");
	        System.out.println(n.closez + " <  closez");
			for(int i = 0; i < n.getArr().length; i++) {
				System.out.println(n.getArr()[i].zdim + " zdim of " + n.getArr()[i].numS);
				System.out.println(cornersForStack[i][0] + " xplace of " + n.getArr()[i].numS);
				System.out.println(cornersForStack[i][1] + " xplace of " + n.getArr()[i].numS);
				
			}
			
		    //System.out.println(n.arr[]);
			System.out.println(A.totalWhitespace(n)+"<Whitespace of ^");
			System.out.println(n.smallestVolume+" <Smallest volume of  ^");
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
	  
	  
	   
	   
	   
	   
	   
		private static void printDiff(List<Order> newO) {
		      for (int counter = 0; counter < newO.size(); counter++) { 
		    	  Order o = newO.get(counter);
		    	  System.out.println(((o.foundVol)*0.0610237 - o.testVol));
		          //System.out.println(arrlist.get(counter)); 		
		      }  
		      
		
	}
		}




//System.out.println(cornersCurr[0][0]);
//System.out.println(x);
//System.out.println(y);
//System.out.println(a.checkPlace(g, arr[i], c, x, y));
/*
 * 
 * 					 Grid x2 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1);
					 Algorithm A2 = new Algorithm(x2.table);
					 Item[] arr2 = deepCloneArr(arrx2);
					 Node rootx2 = new Node(x2, arr2, 1);  //new node that is a root
					 defaultAreaSort(rootx2.getArr());
					 updateItemNumbers(rootx2.getArr());//update numbers
			         int xh = rootx2.getArr()[0].xdim;
			         rootx2.getArr()[i].setX(rootx2.getArr()[0].zdim);
			         rootx2.getArr()[i].setZ(rootx2.getArr()[0].xdim);
			         //System.out.println(rootx2.getArr()[0].zdim + "<Zdim");
			        // System.out.println(rootx2.getArr()[0].xdim + "<Xdim");

			         
						if(A2.checkPlace(rootx2.g, rootx2.getArr()[0], 32, 1, 1, rootx2.getArr()) && packed2 == false) {
							//System.out.println("THERE");
							A2.placeItem(rootx2.g,rootx2.getArr()[0],32,1,1,rootx2,rootx2.currIndex);
							//rootx.currIndex++;
							 // System.out.println(rootx2.getArr()[0].zdim + "<ZdimA");
						     // System.out.println(rootx2.getArr()[0].xdim + "<XdimA");
				        	 rootx2.getPlacement()[rootx2.currIndex-1][0] = 1;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][1] = 1;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][2] = rootx2.getArr()[0].xdim;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][3] = rootx2.getArr()[0].ydim;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][4] = rootx2.getArr()[0].zdim;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][5] = 1;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][5] = 0;
							testRun(rootx2.getArr(), rootx2.g, rootx2);
							//System.out.println(rootx.getPlacement()[1][0] + "<X");
							//System.out.println(rootx.getPlacement()[1][1] + "<Y");
							
							Result R2 = new Result();
							updateAll(rootx2, R2);
							Collections.sort(R2.listN, new whitespaceComparator());
							if(!R2.listN.isEmpty()){
								printList(R2.listN);
								System.out.println("-------------------Order out-------------------");
								System.out.println("------------------------------------------------------------------");
								System.out.println("------------------------------------------------------------------");

								//i = 100;//back out of loop to update later
								packed2 = true;
							}
						
						}
						 Grid x3 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1);
						 Algorithm A3 = new Algorithm(x3.table);
						 Item[] arr3 = deepCloneArr(arrx3);
						 Node rootx3 = new Node(x3, arr3, 1);  //new node that is a root
						 defaultAreaSort(rootx3.getArr());
						 updateItemNumbers(rootx3.getArr());//update numbers
				         rootx3.getArr()[i].setX(rootx3.getArr()[i].xdim2);
				         rootx3.getArr()[i].setY(rootx3.getArr()[i].ydim2);
				         rootx3.getArr()[i].setZ(rootx3.getArr()[i].zdim2);
							if(A3.checkPlace(rootx3.g, rootx3.getArr()[0], 32, 1, 1, rootx3.getArr()) && packed3 == false) {
								//System.out.println("THERE");
								A3.placeItem(rootx3.g,rootx3.getArr()[0],32,1,1,rootx3,rootx3.currIndex);
								//rootx.currIndex++;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][0] = 1;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][1] = 1;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][2] = rootx3.getArr()[0].xdim;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][3] = rootx3.getArr()[0].ydim;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][4] = rootx3.getArr()[0].zdim;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][5] = 1;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][5] = 0;
								testRun(rootx3.getArr(), rootx3.g, rootx3);
								//System.out.println(rootx.getPlacement()[1][0] + "<X");
								//System.out.println(rootx.getPlacement()[1][1] + "<Y");
								
								Result R3 = new Result();
								updateAll(rootx3, R3);
								Collections.sort(R3.listN, new whitespaceComparator());
								if(!R3.listN.isEmpty()){
									printList(R3.listN);
									System.out.println("-------------------Order out-------------------");
									System.out.println("------------------------------------------------------------------");
									System.out.println("------------------------------------------------------------------");

									//i = 100;//back out of loop to update later
									packed3 = true;
								}
							
							}
 * 
 * 
 * 
 * 		
		
		for (Order element : orders) {
			for(int i = 0; i < element.arr.length; i++){
				boolean packed1 = false;
				boolean packed2 = false;
				boolean packed3 = false;

				Item[] arr1 = deepCloneArr(element.arr);
				Item[] arrx2 = deepCloneArr(element.arr);
				Item[] arrx3 = deepCloneArr(element.arr);
				for(int j = 0; j < boxes.length; j++) {
					//System.out.println("HERE");
					Result R = new Result();
					Result R2 = new Result();
					Result R3 = new Result();
					Grid x = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1);
					Item[] arr = deepCloneArr(arr1);
					Algorithm A = new Algorithm(x.table);
					Node rootx = new Node(x, arr, 1);  //new node that is a root
					defaultAreaSort(rootx.getArr());
					updateItemNumbers(rootx.getArr());//update numbers

					//rootx.g.index++;
					
					//System.out.println(rootx.getArr()[0].numS + " BAA  " + rootx.g.index);
					//System.out.println(rootx.getArr()[0].xdim + "<XDIM");
					//System.out.println(rootx.getArr()[0].ydim + "<YDIM");
					//System.out.println(rootx.getArr()[0].zdim + "<ZDIM");
					
					if(A.checkPlace(rootx.g, rootx.getArr()[0], 32, 1, 1, rootx.getArr()) && packed1 == false) {
						//System.out.println("THERE");
						A.placeItem(rootx.g,rootx.getArr()[0],32,1,1,rootx,rootx.currIndex);
						//rootx.currIndex++;
					
			        	 rootx.getPlacement()[rootx.currIndex-1][0] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][1] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][2] = rootx.getArr()[0].xdim;
			        	 rootx.getPlacement()[rootx.currIndex-1][3] = rootx.getArr()[0].ydim;
			        	 rootx.getPlacement()[rootx.currIndex-1][4] = rootx.getArr()[0].zdim;
			        	 rootx.getPlacement()[rootx.currIndex-1][5] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][6] = 0;
						 testRun(rootx.getArr(), rootx.g, rootx);
					}
					
					
					 Grid x2 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1);
					 Algorithm A2 = new Algorithm(x2.table);
					 Item[] arr2 = deepCloneArr(arrx2);
					 Node rootx2 = new Node(x2, arr2, 1);  //new node that is a root
					 defaultAreaSort(rootx2.getArr());
					 updateItemNumbers(rootx2.getArr());//update numbers
			         int xh = rootx2.getArr()[0].xdim;
			         rootx2.getArr()[i].setX(rootx2.getArr()[0].zdim);
			         rootx2.getArr()[i].setZ(rootx2.getArr()[0].xdim);
						if(A2.checkPlace(rootx2.g, rootx2.getArr()[0], 32, 1, 1, rootx2.getArr()) && packed2 == false) {
							 A2.placeItem(rootx2.g,rootx2.getArr()[0],32,1,1,rootx2,rootx2.currIndex);
				        	 rootx2.getPlacement()[rootx2.currIndex-1][0] = 1;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][1] = 1;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][2] = rootx2.getArr()[0].xdim;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][3] = rootx2.getArr()[0].ydim;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][4] = rootx2.getArr()[0].zdim;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][5] = 1;
				        	 rootx2.getPlacement()[rootx2.currIndex-1][5] = 0;
							 testRun(rootx2.getArr(), rootx2.g, rootx2);
							
							 updateAll(rootx2, R2);
							 if(!R2.listN.isEmpty()){
						         packed2 = true;
							}
						
						}
						
						 Grid x3 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1);
						 Algorithm A3 = new Algorithm(x3.table);
						 Item[] arr3 = deepCloneArr(arrx3);
						 Node rootx3 = new Node(x3, arr3, 1);  //new node that is a root
						 defaultAreaSort(rootx3.getArr());
						 updateItemNumbers(rootx3.getArr());//update numbers
				         rootx3.getArr()[i].setX(rootx3.getArr()[i].xdim2);
				         rootx3.getArr()[i].setY(rootx3.getArr()[i].ydim2);
				         rootx3.getArr()[i].setZ(rootx3.getArr()[i].zdim2);
							if(A3.checkPlace(rootx3.g, rootx3.getArr()[0], 32, 1, 1, rootx3.getArr()) && packed3 == false) {
								A3.placeItem(rootx3.g,rootx3.getArr()[0],32,1,1,rootx3,rootx3.currIndex);
					        	 rootx3.getPlacement()[rootx3.currIndex-1][0] = 1;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][1] = 1;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][2] = rootx3.getArr()[0].xdim;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][3] = rootx3.getArr()[0].ydim;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][4] = rootx3.getArr()[0].zdim;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][5] = 1;
					        	 rootx3.getPlacement()[rootx3.currIndex-1][5] = 0;
								testRun(rootx3.getArr(), rootx3.g, rootx3);								
								
								updateAll(rootx3, R3);
								if(!R3.listN.isEmpty()){
									packed3 = true;
								}
							
							}
						
						
					
					System.out.println("GOT THERE2");
					updateAll(rootx, R);
					if((packed1 == true && packed2 == true && packed3 == true) || i == boxes.length-2){
						R.listN.addAll(R2.listN);
						R.listN.addAll(R3.listN);
						System.out.println("GOT THERE");
						if(!R.listN.isEmpty()){
							Collections.sort(R.listN, new whitespaceComparator());
							printList(R.listN);
							System.out.println("-------------------Order out-------------------");
							System.out.println("------------------------------------------------------------------");
							System.out.println("------------------------------------------------------------------");
							packed1 = true;
							
							i = boxes.length;//back out of loop to update later
							
						}
					}


					
					
					
					
				}
				
				
			}
			
		}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * public static void orderOnAllBox(List<Order> orders, int[][] boxes){
			
			
			for (Order element : orders) {
				Result R = new Result();
				//Result R3 = (Result) getOther3(element, boxes);
				//System.out.println("Back");
				//Result R2 = (Result) getOther2(element, boxes);
				
				//R.listN.addAll(R2.listN);
				//R.listN.addAll(R3.listN);
				//for(int i = 0; i < element.size; i++){
					boolean packed1 = false;

					Item[] arr1 = deepCloneArr(element.arr);
					//System.out.println("made it out");
					
					for(int j = 0; j < boxes.length; j++) {
						Result Rx = new Result();
						Grid x = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4]);
						Item[] arr = deepCloneArr(arr1);
						Algorithm A = new Algorithm(x.table);
						Node rootx = new Node(x, arr, 1);  //new node that is a root
						defaultAreaSort(rootx.getArr());
						updateItemNumbers(rootx.getArr());//update numbers
						//int a = rootx.getArr()[0].zdim;
						//rootx.getArr()[0].zdim = rootx.getArr()[0].ydim;
						//rootx.getArr()[0].ydim = a;
						
						if(A.checkPlace(rootx.g, rootx.getArr()[0], 32, 1, 1, rootx.getArr()) && packed1 == false) {
						    A.placeItem(rootx.g,rootx.getArr()[0],32,1,1,rootx,rootx.currIndex);
				        	rootx.getPlacement()[rootx.currIndex-1][0] = 1;
				        	rootx.getPlacement()[rootx.currIndex-1][1] = 1;
				        	rootx.getPlacement()[rootx.currIndex-1][2] = rootx.getArr()[0].xdim;
				        	rootx.getPlacement()[rootx.currIndex-1][3] = rootx.getArr()[0].ydim;
				            rootx.getPlacement()[rootx.currIndex-1][4] = rootx.getArr()[0].zdim;
				        	rootx.getPlacement()[rootx.currIndex-1][5] = 1;
				        	rootx.getPlacement()[rootx.currIndex-1][6] = 0;
				        	System.out.println(j+ "-9");
							testRun(rootx.getArr(), rootx.g, rootx);
							System.out.println(j);
							updateAll(rootx, Rx);
							System.out.println(j+ "o1");
							R.listN.addAll(Rx.listN);
							System.out.println(j+ "o2");
						}
					}
					
						//updateAll(rootx, R);
						//R.addAll(Rx);
						Collections.sort(R.listN, new whitespaceComparator());
						if(!R.listN.isEmpty()){
							//packed1 = true;
							printList(R.listN);
							System.out.println("----------------------------Order out-----------------------------");
							System.out.println("------------------------------------------------------------------");
							System.out.println("------------------------------------------------------------------");
							//packed1 = true;
							//j = 1000;//back out of loop to update later
							break;
						}
						
					
					
					
				//}
				
			}
			
			
		}
		
		
	
	
	



	private static Result getOther2(Order element, int[][] boxes) {
		boolean packed1 = false;
		Result R = new Result();
		Item[] arr1 = deepCloneArr(element.arr);
		//System.out.println("made it out");
		

			
		
		System.out.println(arr1[0].xdim + " <Xdim");
		System.out.println(arr1[0].ydim+ " <Ydim");
		System.out.println(arr1[0].zdim+ " <Zdim");

		for(int j = 0; j < boxes.length; j++) {
			Grid x = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4]);
			Item[] arr = deepCloneArr(arr1);
			Algorithm A = new Algorithm(x.table);
			Node rootx = new Node(x, arr, 1);  //new node that is a root
			defaultAreaSort(rootx.getArr());
			updateItemNumbers(rootx.getArr());//update numbers
			
			int a = rootx.getArr()[0].zdim;
			rootx.getArr()[0].zdim = rootx.getArr()[0].ydim;
			rootx.getArr()[0].ydim = a;
			
			if(A.checkPlace(rootx.g, rootx.getArr()[0], 32, 1, 1, rootx.getArr()) && packed1 == false) {
				System.out.println(j);
			    A.placeItem(rootx.g,rootx.getArr()[0],32,1,1,rootx,rootx.currIndex);
	        	rootx.getPlacement()[rootx.currIndex-1][0] = 1;
	        	rootx.getPlacement()[rootx.currIndex-1][1] = 1;
	        	rootx.getPlacement()[rootx.currIndex-1][2] = rootx.getArr()[0].xdim;
	        	rootx.getPlacement()[rootx.currIndex-1][3] = rootx.getArr()[0].ydim;
	            rootx.getPlacement()[rootx.currIndex-1][4] = rootx.getArr()[0].zdim;
	        	rootx.getPlacement()[rootx.currIndex-1][5] = 1;
	        	rootx.getPlacement()[rootx.currIndex-1][6] = 0;
				testRun(rootx.getArr(), rootx.g, rootx);
				System.out.println(j + "OUT");
			}
			
			
			updateAll(rootx, R);
			Collections.sort(R.listN, new whitespaceComparator());
			if(!R.listN.isEmpty()){
				
				//packed1 = true;
				printList(R.listN);
				//System.out.println("----------------------------Order out-----------------------------");
				//System.out.println("------------------------------------------------------------------");
				//System.out.println("------------------------------------------------------------------");
				//packed1 = true;
				//j = 1000;//back out of loop to update later
				//break;
				System.out.println(j + "OUT");
				return R;
			}
			
		}
		System.out.println("GOT OUT");
			return R;
		}
	
	
	private static Result getOther3(Order element, int[][] boxes) {
		boolean pack2More = false;
		boolean packed1 = false;
		Result R = new Result();
		Item[] arr1 = deepCloneArr(element.arr);

		int len = boxes.length;
		
		System.out.println(arr1[0].xdim + " <Xdim");
		System.out.println(arr1[0].ydim+ " <Ydim");
		System.out.println(arr1[0].zdim+ " <Zdim");
		//System.out.println("made it out");
		for(int j = 0; j < len; j++) {
			Grid x = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4]);
			Item[] arr = deepCloneArr(arr1);
			Algorithm A = new Algorithm(x.table);
			Node rootx = new Node(x, arr, 1);  //new node that is a root
			defaultAreaSort(rootx.getArr());
			updateItemNumbers(rootx.getArr());//update numbers
			int a = rootx.getArr()[0].ydim;
			rootx.getArr()[0].ydim = rootx.getArr()[0].xdim;
			rootx.getArr()[0].xdim = a;
			if(A.checkPlace(rootx.g, rootx.getArr()[0], 32, 1, 1, rootx.getArr()) && packed1 == false) {
			    A.placeItem(rootx.g,rootx.getArr()[0],32,1,1,rootx,rootx.currIndex);
	        	rootx.getPlacement()[rootx.currIndex-1][0] = 1;
	        	rootx.getPlacement()[rootx.currIndex-1][1] = 1;
	        	rootx.getPlacement()[rootx.currIndex-1][2] = rootx.getArr()[0].xdim;
	        	rootx.getPlacement()[rootx.currIndex-1][3] = rootx.getArr()[0].ydim;
	            rootx.getPlacement()[rootx.currIndex-1][4] = rootx.getArr()[0].zdim;
	        	rootx.getPlacement()[rootx.currIndex-1][5] = 1;
	        	rootx.getPlacement()[rootx.currIndex-1][6] = 0;
	        	//System.out.println(j);
				testRun(rootx.getArr(), rootx.g, rootx);
				//System.out.println(j + "OUT");
				updateAll(rootx, R);
			}
		}
			//updateAll(rootx, R);
			Collections.sort(R.listN, new whitespaceComparator());
			if(!R.listN.isEmpty() ){
				
				
				//Collections.sort(R.listN, new whitespaceComparator());
				//packed1 = true;
				//printList(R.listN);
				//System.out.println("----------------------------Order out-----------------------------");
				//System.out.println("------------------------------------------------------------------");
				//System.out.println("------------------------------------------------------------------");
				//packed1 = true;
				//j = 1000;//back out of loop to update later
				//break;
				//System.out.println(j);
				
			}

		
		
			return R;
		}


 * 
 */

