/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */

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
		//System.out.println("here");
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
		Grid x = new Grid(20,20,10,1, 20*20*10, 20*20*10);
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
		
		int [][] corners = A.findCorners(x.table);
		
		//testRun(rootx.getArr(), rootx.g, rootx);
		//Result R = new Result();
		//updateAll(rootx, R);
		//printAllFinished(rootx);
		//Collections.sort(R.listN, new whitespaceComparator());
		//printList(R.listN); //to uncomment
		
		ReadExcelFileV2 XFILE = new ReadExcelFileV2();
		
		List<Order> orders = XFILE.readExcel();
		int[][] boxes = XFILE.fetchBoxes();
		System.out.println("here");
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
		

		orderOnAllBox(orders, boxes);
	}
	
	
public static void orderOnAllBox(List<Order> orders, int[][] boxes) throws IOException{
	System.out.println("here");
		List<Order> newO = new ArrayList<Order>();
		newO.addAll(orders);
		int count = 1;
		for (Order element : newO) {
			//for(int i = 0; i < element.arr.length; i++){
				boolean packed1 = false;
				boolean packed2 = false;
				boolean packed3 = false;
				boolean packed4 = false;
				boolean packed5 = false;
				boolean packed6 = false;
				//System.out.println(element.arr[1].zdim + "THATSZ");//debug tool for bug
				Item[] arr1 = deepCloneArr(element.arr);
				Item[] arrx2 = deepCloneArr(element.arr);
				Item[] arrx3 = deepCloneArr(element.arr);
				Item[] arrx4 = deepCloneArr(element.arr);
				Item[] arrx5 = deepCloneArr(element.arr);
				Item[] arrx6 = deepCloneArr(element.arr);
				for(int j = 0; j < boxes.length; j++) {
					//System.out.println("HERE");
					Grid x = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4], boxes[j][6]);
					Item[] arr = deepCloneArr(arr1);
					
					
					Grid x2 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4], boxes[j][6]);
					Item[] arrswitch = deepCloneArr(arrx2);
					Algorithm A2 = new Algorithm(x2.table);
					Node rootx2 = new Node(x2, arrswitch, 1);  //new node that is a root new node
					element.realAmazonVol = boxes[j][6];
					rootx2.startingVol = boxes[j][6];
					rootx2.ogGridVol = boxes[j][4];
					rootx2.customSort();  //works
					
					rootx2.getArr()[0].xdim = rootx2.getArr()[0].ydim2;
					rootx2.getArr()[0].ydim = rootx2.getArr()[0].xdim2;
					
					
					Grid x3 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4], boxes[j][6]);
					Item[] arrswitch3 = deepCloneArr(arrx3);
					Algorithm A3 = new Algorithm(x3.table);
					Node rootx3 = new Node(x3, arrswitch3, 1);  //new node that is a root new node
					element.realAmazonVol = boxes[j][6];
					rootx3.startingVol = boxes[j][6];
					rootx3.ogGridVol = boxes[j][4];
					rootx3.customSort();  //works

					rootx2.getArr()[0].ydim = rootx2.getArr()[0].zdim2;
					rootx2.getArr()[0].zdim = rootx2.getArr()[0].ydim2;
					
					Grid x4 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4], boxes[j][6]);
					Item[] arrswitch4 = deepCloneArr(arrx4);
					Algorithm A4 = new Algorithm(x3.table);
					Node rootx4 = new Node(x4, arrswitch4, 1);  //new node that is a root new node
					element.realAmazonVol = boxes[j][6];
					rootx4.startingVol = boxes[j][6];
					rootx4.ogGridVol = boxes[j][4];
					rootx4.customSort();  //works

					rootx2.getArr()[0].xdim = rootx2.getArr()[0].zdim2;
					rootx2.getArr()[0].ydim = rootx2.getArr()[0].xdim2;
					rootx2.getArr()[0].zdim = rootx2.getArr()[0].ydim2;
					
					Grid x5 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4], boxes[j][6]);
					Item[] arrswitch5 = deepCloneArr(arrx5);
					Algorithm A5 = new Algorithm(x5.table);
					Node rootx5 = new Node(x5, arrswitch5, 1);  //new node that is a root new node
					element.realAmazonVol = boxes[j][6];
					rootx5.startingVol = boxes[j][6];
					rootx5.ogGridVol = boxes[j][4];
					rootx5.customSort();  //works

					rootx2.getArr()[0].xdim = rootx2.getArr()[0].zdim2;
					rootx2.getArr()[0].zdim = rootx2.getArr()[0].xdim2;

					Grid x6 = new Grid(boxes[j][1],boxes[j][2],boxes[j][3],1, boxes[j][4], boxes[j][6]);
					Item[] arrswitch6 = deepCloneArr(arrx6);
					Algorithm A6 = new Algorithm(x6.table);
					Node rootx6 = new Node(x6, arrswitch6, 1);  //new node that is a root new node
					element.realAmazonVol = boxes[j][6];
					rootx6.startingVol = boxes[j][6];
					rootx6.ogGridVol = boxes[j][4];
					rootx6.customSort();  //works


					rootx2.getArr()[0].xdim = rootx2.getArr()[0].ydim2;
					rootx2.getArr()[0].ydim = rootx2.getArr()[0].zdim2;
					rootx2.getArr()[0].zdim = rootx2.getArr()[0].xdim2;
					
					
					//Item[] arr4 = defaultAreaSort(arr1);
					//Item[] arrN = deepCloneArr(defaultAreaSort(arr));
					//System.out.println(arrN[0].xdim);
					//Item[] arrN2 = deepCloneArr(arrN);
					//updateItemNumbers(arrN);//update numbers
					//Item[] arrN = deepCloneArr(arr);
					Algorithm A = new Algorithm(x.table);
					Node rootx = new Node(x, arr, 1);  //new node that is a root
					element.realAmazonVol = boxes[j][6];
					rootx.startingVol = boxes[j][6];
					rootx.ogGridVol = boxes[j][4];
					//rootx.defaultAreaSortInNode();
					//rootx.getArr() = defaultAreaSort(rootx.getArr());
					rootx.customSort();  //works

					Result R = new Result();
					Result R2 = new Result();
					Result R3 = new Result();
					Result R4 = new Result();
					Result R5 = new Result();
					Result R6 = new Result();
					
					//ATTN:
					//important note here
					//get rid of the packed booleans when you want to see all of the solutions
					//Do this for all 6
					if(A.checkPlace(rootx6.g, rootx6.getArr()[0], 32, 1, 1, rootx6.getArr()) && (packed6 == false)) {
						A.placeItem(rootx6.g,rootx6.getArr()[0],32,1,1,rootx6,rootx6.currIndex);
			        	 rootx6.getPlacement()[rootx6.currIndex-1][0] = 1;
			        	 rootx6.getPlacement()[rootx6.currIndex-1][1] = 1;
			        	 rootx6.getPlacement()[rootx6.currIndex-1][2] = rootx6.getArr()[0].xdim;
			        	 rootx6.getPlacement()[rootx6.currIndex-1][3] = rootx6.getArr()[0].ydim;
			        	 rootx6.getPlacement()[rootx6.currIndex-1][4] = rootx6.getArr()[0].zdim;
			        	 rootx6.getPlacement()[rootx6.currIndex-1][5] = 1;
			        	 rootx6.getPlacement()[rootx6.currIndex-1][5] = 0;
			        	 rootx6.getPlacement()[rootx2.currIndex-1][8] = rootx.getArr()[0].zdim;
						testRun(rootx6.getArr(), rootx6.g, rootx6);
						updateAll(rootx6, R6);
						Collections.sort(R6.listN, new whitespaceComparator());
						if(packed6 == false){
						for(Node t : R6.listN){
							if(t.getArr()[1].zdim != 0){
								packed6 = true;
							}
							}
						}
					}
					
					if(A.checkPlace(rootx5.g, rootx5.getArr()[0], 32, 1, 1, rootx5.getArr()) && (packed5 == false && packed6 == false)) {
						A.placeItem(rootx5.g,rootx5.getArr()[0],32,1,1,rootx5,rootx5.currIndex);
			        	 rootx5.getPlacement()[rootx5.currIndex-1][0] = 1;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][1] = 1;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][2] = rootx5.getArr()[0].xdim;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][3] = rootx5.getArr()[0].ydim;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][4] = rootx5.getArr()[0].zdim;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][5] = 1;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][5] = 0;
			        	 rootx5.getPlacement()[rootx5.currIndex-1][7] = 0;
			        	 rootx5.getPlacement()[rootx2.currIndex-1][8] = rootx.getArr()[0].zdim;
						testRun(rootx5.getArr(), rootx5.g, rootx5);
						updateAll(rootx5, R5);
						Collections.sort(R5.listN, new whitespaceComparator());
						if(packed5 == false){
						for(Node t : R5.listN){
							if(t.getArr()[1].zdim != 0){
								packed5 = true;
							}
							}
						}
					}
					
					if(A.checkPlace(rootx4.g, rootx4.getArr()[0], 32, 1, 1, rootx4.getArr()) && (packed4 == false &&packed5 == false && packed6 == false)) {
						A.placeItem(rootx4.g,rootx4.getArr()[0],32,1,1,rootx4,rootx4.currIndex);
			        	 rootx4.getPlacement()[rootx4.currIndex-1][0] = 1;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][1] = 1;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][2] = rootx4.getArr()[0].xdim;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][3] = rootx4.getArr()[0].ydim;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][4] = rootx4.getArr()[0].zdim;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][5] = 1;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][5] = 0;
			        	 rootx4.getPlacement()[rootx4.currIndex-1][7] = 0;
			        	 rootx4.getPlacement()[rootx2.currIndex-1][8] = rootx.getArr()[0].zdim;
						testRun(rootx4.getArr(), rootx4.g, rootx4);
						updateAll(rootx4, R4);
						Collections.sort(R4.listN, new whitespaceComparator());
						if(packed4 == false){
						for(Node t : R4.listN){
							if(t.getArr()[1].zdim != 0){
								packed4 = true;
							}
							}
						}
					}
					
					if(A.checkPlace(rootx3.g, rootx3.getArr()[0], 32, 1, 1, rootx3.getArr()) && (packed3 == false && packed4 == false && packed5 == false && packed6 == false)) {
					
						A.placeItem(rootx3.g,rootx3.getArr()[0],32,1,1,rootx3,rootx3.currIndex);
			        	 rootx3.getPlacement()[rootx3.currIndex-1][0] = 1;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][1] = 1;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][2] = rootx3.getArr()[0].xdim;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][3] = rootx3.getArr()[0].ydim;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][4] = rootx3.getArr()[0].zdim;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][5] = 1;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][5] = 0;
			        	 rootx3.getPlacement()[rootx3.currIndex-1][7] = 0;
			        	 rootx3.getPlacement()[rootx2.currIndex-1][8] = rootx.getArr()[0].zdim;
						testRun(rootx3.getArr(), rootx3.g, rootx3);
						updateAll(rootx3, R3);
						Collections.sort(R3.listN, new whitespaceComparator());
						if(packed3 == false){
						for(Node t : R3.listN){
							if(t.getArr()[1].zdim != 0){
								packed3 = true;
							}
							}
						}
					}
					
					
					
					
					
					
					if(A.checkPlace(rootx2.g, rootx2.getArr()[0], 32, 1, 1, rootx2.getArr()) && (packed2 == false && packed3 == false && packed4 == false && packed5 == false && packed6 == false)) {
						A.placeItem(rootx2.g,rootx2.getArr()[0],32,1,1,rootx2,rootx2.currIndex);
			        	 rootx2.getPlacement()[rootx2.currIndex-1][0] = 1;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][1] = 1;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][2] = rootx2.getArr()[0].xdim;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][3] = rootx2.getArr()[0].ydim;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][4] = rootx2.getArr()[0].zdim;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][5] = 1;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][5] = 0;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][7] = 0;
			        	 rootx2.getPlacement()[rootx2.currIndex-1][8] = rootx.getArr()[0].zdim;
						testRun(rootx2.getArr(), rootx2.g, rootx2);
						updateAll(rootx2, R2);
						Collections.sort(R2.listN, new whitespaceComparator());
						if(packed2 == false){
						for(Node t : R2.listN){
							if(t.getArr()[1].zdim != 0){
								packed2 = true;
							}
							}
						}
					}
					
					
					
					
					//here is other
					
					
					
					if(A.checkPlace(rootx.g, rootx.getArr()[0], 32, 1, 1, rootx.getArr()) && (packed1 == false && packed2 == false && packed3 == false && packed4 == false && packed5 == false && packed6 == false)) {
						A.placeItem(rootx.g,rootx.getArr()[0],32,1,1,rootx,rootx.currIndex);
			        	 rootx.getPlacement()[rootx.currIndex-1][0] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][1] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][2] = rootx.getArr()[0].xdim;
			        	 rootx.getPlacement()[rootx.currIndex-1][3] = rootx.getArr()[0].ydim;
			        	 rootx.getPlacement()[rootx.currIndex-1][4] = rootx.getArr()[0].zdim;
			        	 rootx.getPlacement()[rootx.currIndex-1][5] = 1;
			        	 rootx.getPlacement()[rootx.currIndex-1][5] = 0;
			        	 rootx.getPlacement()[rootx2.currIndex-1][7] = 0;
			        	 rootx.getPlacement()[rootx2.currIndex-1][8] = rootx.getArr()[0].zdim;
						testRun(rootx.getArr(), rootx.g, rootx);
						updateAll(rootx, R);
						Collections.sort(R.listN, new whitespaceComparator());
						if(packed1 == false){
						for(Node t : R.listN){
							if(t.getArr()[1].zdim != 0){
								packed1 = true;
							}
							}
						}
						//HOLD 2

						
					}
					
					
					Result Rt = new Result();
					Rt.listN.addAll(R.listN);
					Rt.listN.addAll(R2.listN);
					Rt.listN.addAll(R3.listN);
					Rt.listN.addAll(R4.listN);
					Rt.listN.addAll(R5.listN);
					Rt.listN.addAll(R6.listN);
					Collections.sort(Rt.listN, new whitespaceComparator());
					if(!Rt.listN.isEmpty() && (packed1 == true || packed2 == true || packed3 == true || packed4 == true || packed5 == true || packed6 == true )){

						element.AmazonVol = Rt.listN.get(0).AmazonBoxVolumeCM3;//set values for the order back to excel
						element.outputAmazon = Rt.listN.get(0).g.Amazonvol;//set values for the order back to excel
						element.foundVol = Rt.listN.get(0).smallestVolume;
						element.startingVolA = Rt.listN.get(0).startingVol;
						int c = (Rt.listN.get(0).g.xdimen-2)*(Rt.listN.get(0).g.ydimen-2)*(Rt.listN.get(0).g.zdimen);  //vol of pack box cm3
						element.finalGrid = c;
						element.boxP = Rt.listN.get(0).ogGridVol;
						//System.out.println(element.foundVol + "FOUNDVOL");
						
						printOrderOfItem(Rt.listN.get(0));
						
						printList(Rt.listN);
						System.out.println("Order Number: " + count);
						count++;
						System.out.println("-------------------Order out-------------------");
						System.out.println("------------------------------------------------------------------");
						System.out.println("------------------------------------------------------------------");
						packed1 = true;
						packed2 = true;
						packed3 = true;
						packed4 = true;
						packed5 = true;
						packed6 = true;
						
						
					}
					
					
					
				}
				
				
			}
		
				//updateAll(rootx, R);
			//ReadExcelFileV2 write = new ReadExcelFileV2();
			//List<Order> newOO = newO;
			//write.writeExcel(newO);
			printDiff(newO);
			printBoxDiff(newO);
		}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





	//Recursive method to create tree structure, simple in theory but complex in practice
	public static void testRun(Item[] arr, Grid g, Node n) {
		if(n.currIndex < arr.length) {
		//System.out.println(n.currIndex);
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
			        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.getPlacement()[n.currIndex][7] = 0;
			        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         //
			         
			         n.getArr()[i].setY(n.getArr()[i].xdim2);
			         n.getArr()[i].setX(n.getArr()[i].ydim2);
			         
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
			        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.getPlacement()[n.currIndex][7] = 0;
			        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         
			         
			         //n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].zdim2);
			         n.getArr()[i].setZ(n.getArr()[i].ydim2);
			         
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
			        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.getPlacement()[n.currIndex][7] = 0;
			        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         //	
			         
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         n.getArr()[i].setX(n.getArr()[i].zdim2);
			         n.getArr()[i].setY(n.getArr()[i].xdim2);
			         n.getArr()[i].setZ(n.getArr()[i].ydim2);
			         
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
			        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.getPlacement()[n.currIndex][7] = 0;
			        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         
			         
			         
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         n.getArr()[i].setX(n.getArr()[i].zdim2);
			         //n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].xdim2);
			         
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
			        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.getPlacement()[n.currIndex][7] = 0;
			        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         //
			         
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         n.getArr()[i].setX(n.getArr()[i].ydim2);
			         n.getArr()[i].setY(n.getArr()[i].zdim2);
			         n.getArr()[i].setZ(n.getArr()[i].xdim2);
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
			        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.getPlacement()[n.currIndex][7] = 0;
			        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
			        	 newNode.parentNode = n;
			        	 n.Nodes.add(newNode);
                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         }
			         
			         
			         
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
						//System.out.println(n.getArr()[1].zdim + "zRUN");

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
				        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
				        	 newNode.getPlacement()[n.currIndex][7] = 1;//stack
				        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
				        	 newNode.parentNode = n;
				        	 n.Nodes.add(newNode);
	                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
		         		}
	         		}
	         	}
	         	
		         n.getArr()[i].setY(n.getArr()[i].xdim2);
		         n.getArr()[i].setX(n.getArr()[i].ydim2);
		         
		         	if(cornersForStack != null){
		         		for(int q = 0; q<cornersForStack.length; q++) {
		         			//System.out.println(q);
							//System.out.println(n.getArr()[1].zdim + "zRUN");

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
					        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
					        	 newNode.getPlacement()[n.currIndex][7] = 1;//stack
					        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
					        	 newNode.parentNode = n;
					        	 n.Nodes.add(newNode);
		                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         		}
		         		}
		         	}
		         	
		         n.getArr()[i].setX(n.getArr()[i].xdim2);
		         n.getArr()[i].setY(n.getArr()[i].ydim2);
		         n.getArr()[i].setZ(n.getArr()[i].zdim2);
		         
		         //n.getArr()[i].setX(n.getArr()[i].xdim2);
		         n.getArr()[i].setY(n.getArr()[i].zdim2);
		         n.getArr()[i].setZ(n.getArr()[i].ydim2);
	         	
	         	
			        cornersForStack = findStackingOptions(n);
		         	if(cornersForStack != null){
		         		for(int q = 0; q<cornersForStack.length; q++) {
		         			//System.out.println(q);
			         		if(a.checkStackPlace(g, n.getArr()[i], c, cornersForStack[q][0], cornersForStack[q][1],n.getArr(), n)) {
								//System.out.println(n.getArr()[1].zdim + "zRUN");

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
					        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
					        	 newNode.getPlacement()[n.currIndex][7] = 1;
					        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
					        	 newNode.parentNode = n;
					        	 n.Nodes.add(newNode);
		                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         		}
		         		}
		         	}
		         	
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
			         
			         n.getArr()[i].setX(n.getArr()[i].zdim2);
			         n.getArr()[i].setY(n.getArr()[i].xdim2);
			         n.getArr()[i].setZ(n.getArr()[i].ydim2);
		         	
			        cornersForStack = findStackingOptions(n);
		         	if(cornersForStack != null){
		         		for(int q = 0; q<cornersForStack.length; q++) {
		         			//System.out.println(q);
			         		if(a.checkStackPlace(g, n.getArr()[i], c, cornersForStack[q][0], cornersForStack[q][1],n.getArr(), n)) {
								//System.out.println(n.getArr()[1].zdim + "zRUN");

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
					        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
					        	 newNode.getPlacement()[n.currIndex][7] = 1;
					        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
					        	 newNode.parentNode = n;
					        	 n.Nodes.add(newNode);
		                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
			         		}
		         		}
		         	}
		         	
			         n.getArr()[i].setX(n.getArr()[i].xdim2);
			         n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].zdim2);
		      
			         n.getArr()[i].setX(n.getArr()[i].zdim2);
			         //n.getArr()[i].setY(n.getArr()[i].ydim2);
			         n.getArr()[i].setZ(n.getArr()[i].xdim2);
			         
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
						        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
						        	 newNode.getPlacement()[n.currIndex][7] = 1;
						        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
						        	 newNode.parentNode = n;
						        	 n.Nodes.add(newNode);
			                         testRun(deepCloneArr(newNode.getArr()), newNode.g, newNode);
				         		}
			         		}
			         	}
			         	
				         n.getArr()[i].setX(n.getArr()[i].xdim2);
				         n.getArr()[i].setY(n.getArr()[i].ydim2);
				         n.getArr()[i].setZ(n.getArr()[i].zdim2);
				         
				         n.getArr()[i].setX(n.getArr()[i].ydim2);
				         n.getArr()[i].setY(n.getArr()[i].zdim2);
				         n.getArr()[i].setZ(n.getArr()[i].xdim2);
				         
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
							        	 newNode.getPlacement()[n.currIndex][6] = newNode.getArr()[newNode.currIndex-1].placedz;
							        	 newNode.getPlacement()[n.currIndex][7] = 1;
							        	 newNode.getPlacement()[n.currIndex][8] = newNode.getArr()[newNode.currIndex-1].placedz;
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
			options[c][0] = n.getPlacement()[i][0];
			options[c][1] = n.getPlacement()[i][1];
			//System.out.println("THERE");
			//System.out.println(n.getPlacement()[i][0]+" item " + n.getArr()[i].numS);
			//System.out.println(n.getPlacement()[i][1]+" item " + n.getArr()[i].numS);
			//System.out.println("x-y");
			c++;
			}
			if(n.getPlacement()[i][7] == 1) {
				options[c][0] = n.getPlacement()[i][2]+n.getPlacement()[i][0];
				options[c][1] = n.getPlacement()[i][1];
				c++;
				options[c][0] = n.getPlacement()[i][0];
				options[c][1] = n.getPlacement()[i][3]+n.getPlacement()[i][1];
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
			items[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
		}
		
		//for(int i = 0; i < sorted.length; i++){
		//	sorted[i].numS = Integer.toString(i+1) + Integer.toString(i+1);
			//System.out.println(I[i].numS + " <This is the item number list");
		//}
		
		return sorted;
	}
	
	
	//debugging tool
	public static void printOrderOfItem(Node n){
		for (int i = 0; i < n.getArr().length; i++){
			//int x = items[i].xdim*items[i].ydim;
			//sorted[temp.indexOf(x)] = items[i];
			System.out.println(n.getArr()[i].xdim + " xdim " + i);
			System.out.println(n.getArr()[i].ydim + " ydim " + i);
			System.out.println(n.getArr()[i].zdim + " zdim " + i);
		}
		for (int i = 0; i < n.getPlacement().length; i++){
			//int x = items[i].xdim*items[i].ydim;
			//sorted[temp.indexOf(x)] = items[i];
			System.out.println(n.getPlacement()[i][2] + " xdim P " + i);
			System.out.println(n.getPlacement()[i][2] + " ydim P " + i);
			System.out.println(n.getPlacement()[i][2] + " zdim P " + i);
		}
		
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
	
	//ATTN:
	//Modify the output here
	//Prints the 
	public static void printList(ArrayList<Node> listN){
		Algorithm A = new Algorithm(null);
		numberOUT = 0;
		int c = 0;
		for(Node n : listN){
			c++;
			if(c<=100){//limiter
			printArray(n.g.table);
			printGradient(n.gradient.gtable);
			A.smallestVolume(n);
	        int[][] cornersForStack = findStackingOptions(n);
	        System.out.println(n.g.xdimen-2 + " <-cm X-DIM OF BOX in->" + (n.g.xdimen-2)/2.5);
	        System.out.println(n.g.ydimen-2 + " <-cm Y-DIM OF BOX in->"  + (n.g.ydimen-2)/2.5);
	        System.out.println(n.g.zdimen + " <-cm Z-DIM OF BOX in->"  + (n.g.zdimen)/2.5);
	        
	        System.out.println(n.closex + " <  smallest x bounding box in> " + n.closex/2.5);
	        System.out.println(n.closey + " <  smallest y bounding box in> " + n.closey/2.5);
	        System.out.println(n.closez + " <  smallest z bounding box in> " + n.closez/2.5);
			for(int i = 0; i < n.getArr().length; i++) {
				//System.out.println(n.getArr()[i].zdim + " zdim of " + n.getArr()[i].numS);
				//System.out.println(cornersForStack[i][0] + " xplace of " + n.getArr()[i].numS);
				//System.out.println(cornersForStack[i][1] + " xplace of " + n.getArr()[i].numS);
				System.out.println(n.getPlacement()[i][0] + " xplace of " + n.getArr()[i].numS);
				System.out.println(n.getPlacement()[i][1] + " yplace of " + n.getArr()[i].numS);
				
				System.out.println(n.getPlacement()[i][4] + " zdim NEW " + n.getArr()[i].numS);
			}
			for(int i = 0; i < n.getPlacement().length; i++) {
				System.out.println();
				System.out.println(n.getArr()[i].numS + " -<Item");
				System.out.println((double)((n.getPlacement()[i][0]-1)*.4) + (double)(n.getPlacement()[i][2]*.4*.5) + " xplace of " + n.getArr()[i].numS);
				System.out.println((double)((n.getPlacement()[i][1]-1)*.4) + (double)(n.getPlacement()[i][3]*.4*.5) + " yplace of " + n.getArr()[i].numS);
				System.out.println((double)(n.getPlacement()[i][8]*.4) + " zplace of " + n.getArr()[i].numS);
				
				System.out.println((double)(n.getPlacement()[i][2]/2.5) + "< Xdim Placed");
				System.out.println((double)(n.getPlacement()[i][3]/2.5) + "< Ydim Placed");
				System.out.println((double)(n.getPlacement()[i][4]/2.5) + "< Zdim Placed");
				System.out.println((double)(n.getArr()[i].xdim2/2.5) + "< Xdim Original");
				System.out.println((double)(n.getArr()[i].ydim2/2.5) + "< Ydim Original");
				System.out.println((double)(n.getArr()[i].zdim2/2.5) + "< Zdim Original");
				//System.out.println(cornersForStack[i][0] + " xplace of " + n.getArr()[i].numS);
				//System.out.println(cornersForStack[i][1] + " xplace of " + n.getArr()[i].numS);
				
			}
			
		    //System.out.println(n.arr[]);
			System.out.println(A.totalWhitespace(n)+"<Whitespace of ^");
			System.out.println(n.smallestVolume+" <Smallest volume of  ^");
			System.out.println(n.getArr()[0].b + " order no. " );
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
	  
	  
	   
	   
	   
	   
	   //Print the difference between smallest possible and the volume sent in
		private static void printDiff(List<Order> newO) {
		      for (int counter = 0; counter < newO.size(); counter++) { 
		    	  Order o = newO.get(counter);
		    	 // System.out.println((o.AmazonVol)*0.0610237);
		    	 // System.out.println((o.boxP)*0.0610237);
		    	  //System.out.println((o.realAmazonVol)*0.0610237);
		    	 // System.out.println((o.testVol) + "<THIS");
		    	  //System.out.println((o.realAmazonVol)*0.0610237);
		    	  //System.out.println((o.foundVol)*0.0610237);
		    	  System.out.println(((o.foundVol)*0.0610237 - (o.testVol)));  //This one
		          //System.out.println(counter); 		
		      }  
		      System.out.println("");
		      System.out.println("");
		      System.out.println("splitcode");
		      System.out.println("");
		      System.out.println("");
	}
		//print the difference between the box chosen and the one sent in
		private static void printBoxDiff(List<Order> newO) {
		      for (int counter = 0; counter < newO.size(); counter++) { 
		    	  Order o = newO.get(counter);
		    	  //System.out.println(o.testVol);
		    	  //System.out.println(o.startingVolA);
		    	  //System.out.println(o.realAmazonVol);
		    	  //System.out.println((o.AmazonVol)*0.0610237 - (o.testVol));  //This one
		    	  //System.out.println((o.AmazonVol)*0.0610237 - (o.realAmazonVol));  //This one
		    	  System.out.println((o.startingVolA) - (o.testVol));  //This one startingvolA is the final actually
		    	  //System.out.println((o.AmazonVol)*0.015625 - (o.testVol));  //This one test quarter
		          //System.out.println(arrlist.get(counter)); 		
		      }  
		      
		
		}
		}
		
 


