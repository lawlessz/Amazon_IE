/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */


//excel input format
//fc	fsi	pi	barcode	ASIN	asin_l_in	asin_w_in	asin_h_in	asin_wt_lb	box_l_in	box_w_in	box_h_in	units
import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFileV2 {

   
   public  List<Order> readExcel() throws IOException{
	   try {
		   
		     //FileInputStream file = new FileInputStream(new File("fulltest.xlsx"));//biggest test @ sheet 0
	        //FileInputStream file = new FileInputStream(new File("testM.xlsx"));//big test @ sheet 0
	      
		    //FileInputStream file = new FileInputStream(new File("2-5ItemOrders.xlsx"));
	         FileInputStream file = new FileInputStream(new File("10TestCases.xlsx"));

	         XSSFWorkbook workbook = new XSSFWorkbook(file);
	         //System.out.println("out");
	         //XSSFSheet sheet = workbook.getSheetAt(4);
	         XSSFSheet sheet = workbook.getSheetAt(6); //pick the sheet that you want to reference starting at 0


	         Iterator<Row> rowIterator = sheet.iterator();
	         int rownumber = 0;
	         
	         while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	           //System.out.println(row);
	            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
	            rownumber++;
	         }
	         int index = 0;
	         int count = 0;
	         int numofitems;
	         double[][] itemdata = new double[10][3];
	         double[][] rawboxdata = new double[1][4];
             List<Order> oList = new ArrayList<Order>();
	         while (sheet.getRow(index) != null) {
	        	numofitems = 0;
	        	
	            while (sheet.getRow(index).getCell(1).getStringCellValue() == sheet.getRow(count).getCell(1).getStringCellValue()) {

	               for (int i = 0; i < sheet.getRow(count).getCell(12).getNumericCellValue(); i++) {
	            	  
	            	  Item holdI = new Item();
	            	  //x.arr[x.index] = holdI;
	            	  //ATTN: THIS IS WHERE YOU CHANGE CUBIT SIZE
	                  itemdata[i + numofitems][0] = Math.ceil(sheet.getRow(count).getCell(5).getNumericCellValue()*2.5); System.out.print(itemdata[i + numofitems][0] + " ");
	                  itemdata[i + numofitems][1] = Math.ceil(sheet.getRow(count).getCell(6).getNumericCellValue()*2.5); System.out.print(itemdata[i + numofitems][1] + " ");
	                  itemdata[i + numofitems][2] = Math.ceil(sheet.getRow(count).getCell(7).getNumericCellValue()*2.5); System.out.println(itemdata[i + numofitems][2]);
	                  
	                  //x.arr[x.index].xdim = (int) ((itemdata[i + numofitems][0])*2.5);
	                  //x.arr[x.index].xdim = (int) ((itemdata[i + numofitems][1])*2.5);
	                  //x.arr[x.index].xdim = (int) ((itemdata[i + numofitems][2])*2.5);
	                  //x.index++;
	               }
	               numofitems = numofitems + (int)sheet.getRow(count).getCell(12).getNumericCellValue();
	               count++;
	               if (count > sheet.getLastRowNum()) {
	            	   
	                  break;
	               }
	            }
	            System.out.println(numofitems);
	            String s = sheet.getRow(index).getCell(1).getStringCellValue();  //order number
	            rawboxdata[0][0] = sheet.getRow(index).getCell(9).getNumericCellValue();  //System.out.print(rawboxdata[0][0] + " ");
	            rawboxdata[0][1] = sheet.getRow(index).getCell(10).getNumericCellValue(); //System.out.print(rawboxdata[0][1] + " ");
	            rawboxdata[0][2] = sheet.getRow(index).getCell(11).getNumericCellValue(); //System.out.println(rawboxdata[0][2]);
                //make item here
             	Order x = new Order();
             	x.arr = new Item[numofitems];
	            for(int i = 0; i < numofitems; i++){
	            	//Change item constructor here
	            	Item ihold = new Item((int)itemdata[i][0],(int)itemdata[i][1],(int)itemdata[i][2], s);
	            	x.arr[i] = ihold;
		            itemdata[i][0] = 0;
		            itemdata[i][1] = 0;
		            itemdata[i][2] = 0;

	            }

	            System.out.println();
	            index = count;
	            x.testVol = (int) (rawboxdata[0][0]*rawboxdata[0][1]*rawboxdata[0][2]);
	            oList.add(x);
	         } 
	         return oList;
	   			} finally {
	         
	         
	   		}
	   
	   }
   //x = 1, y = 2, z = 3 stored in cm rounded up
   public static int[][] fetchBoxes() throws IOException {
	int [][] b2 = new int [21][7];
	//b2 = getBoxes2();
	
	
	for(int i = 0; i < b2.length; i++){//area
		b2[i][6] = b2[i][3];
	}
	
	
	int[][] arr = new int[21][7];//add one
	arr[0][1] = 25;
	arr[0][2] = 18;
	arr[0][3] = 8;
	arr[0][6] = 210;
	
	arr[1][1] = 25;
	arr[1][2] = 18;
	arr[1][3] = 13;
	arr[1][6] = 350;
	
	arr[2][1] = 33;
	arr[2][2] = 21;
	arr[2][3] = 20;
	arr[2][6] = 1092;
	
	arr[3][1] = 34;
	arr[3][2] = 24;
	arr[3][3] = 8;
	arr[3][6] = 385;
	
	arr[4][1] = 34;
	arr[4][2] = 27;
	arr[4][3] = 12;
	arr[4][6] = 638;
	
	arr[5][1] = 35;
	arr[5][2] = 32;
	arr[5][3] = 23;
	arr[5][6] = 1575;
	
	arr[6][1] = 37;
	arr[6][2] = 20;
	arr[6][3] = 18;
	arr[6][6] = 812;
	
	arr[7][1] = 39;
	arr[7][2] = 32;
	arr[7][3] = 8;
	arr[7][6] = 581;
	
	arr[8][1] = 40;
	arr[8][2] = 30;
	arr[8][3] = 13;
	arr[8][6] = 960;
	
	arr[9][1] = 44;
	arr[9][2] = 35;
	arr[9][3] = 20;
	arr[9][6] = 1960;
	
	arr[10][1] = 45;
	arr[10][2] = 23;
	arr[10][3] = 22;
	arr[10][6] = 1377;
	
	arr[11][1] = 48;
	arr[11][2] = 33;
	arr[11][3] = 15;
	arr[11][6] = 1482;
	
	arr[12][1] = 48;
	arr[12][2] = 37;
	arr[12][3] = 30;
	arr[12][6] = 3306;
	
	arr[13][1] = 48;
	arr[13][2] = 32;
	arr[13][3] = 28;
	arr[13][6] = 2613;
	
	arr[14][1] = 49;
	arr[14][2] = 35;
	arr[14][3] = 8;
	arr[14][6] = 819;
	
	arr[15][1] = 55;
	arr[15][2] = 45;
	arr[15][3] = 13;
	arr[15][6] = 1980;
	
	arr[16][1] = 55;
	arr[16][2] = 45;
	arr[16][3] = 30;
	arr[16][6] = 4752;
	
	arr[17][1] = 24;
	arr[17][2] = 17;
	arr[17][3] = 5;
	arr[17][6] = 124;
	
	arr[18][1] = 30;
	arr[18][2] = 23;
	arr[18][3] = 5;
	arr[18][6] = 216;
	
	
	arr[19][1] = 25;
	arr[19][2] = 18;
	arr[19][3] = 5;
	arr[19][6] = 140;
	
	arr[20][1] = 35;
	arr[20][2] = 23;
	arr[20][3] = 5;
	arr[20][6] = 252;
	
	//big box to save
	//arr[21][1] = 70;
	//arr[21][2] = 60;
	//arr[21][3] = 50;
	
	//for(int i = 0; i < arr.length; i++) {
	//	arr[i][1] = (int) Math.ceil(arr[i][1]*1.5748);
	//	arr[i][2] = (int) Math.ceil(arr[i][2]*1.5748);
	//	arr[i][3] = (int) Math.ceil(arr[i][3]*1.5748);
	//}
	for(int i = 0; i < arr.length; i++){//vol
		//ATTN: ROUND DOWN a cubit
		//need to round down here if you want to or up
		//Comment these out most likely
		arr[i][1]--;
		arr[i][2]--;
		arr[i][3]--;
		arr[i][4] = arr[i][1]*arr[i][2]*arr[i][3];
	}
	for(int i = 0; i < arr.length; i++){//area
		arr[i][5] = arr[i][1]*arr[i][2];
	}
	
	java.util.Arrays.sort(arr, new java.util.Comparator<int[]>() {
	    public int compare(int[] a, int[] b) {
	        return Double.compare(a[6], b[6]);
	    }
	});
	
	Arrays.sort(arr, (a, b) -> Double.compare(a[6], b[6]));
	for(int i = 0; i < arr.length; i++){//vol
		System.out.println(arr[i][6]);
	}
	//Arrays.sort(arr);
	System.out.println(arr[0][1] + "code7");
	System.out.println(arr[0][5] + "code7");
	System.out.println(arr[20][5] + "code8");
	return arr;
	   
   }
   
   //x = 1, y = 2, z = 3 stored in cm rounded up



   
   //not being used
   public static int[][] getBoxes2() throws IOException{
	   try {
		   
		     //Iterator<Order> iterator = O.iterator();
		   
	         FileInputStream file = new FileInputStream(new File("10TestCases.xlsx"));
	         XSSFWorkbook workbook = new XSSFWorkbook(file);
	         XSSFSheet sheet = workbook.getSheetAt(2);
	         Iterator<Row> rowIterator = sheet.iterator();
	         int rownumber = 0;
	         while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
	            rownumber++;
	         }
	         int[][] boxlist = new int[21][7];
	         for (int i = 0; i < 21; i++) {
	            boxlist[i][0] = (int)(sheet.getRow(i).getCell(2).getNumericCellValue()*4);
	            boxlist[i][1] = (int)(sheet.getRow(i).getCell(3).getNumericCellValue()*4);
	            boxlist[i][2] = (int)(sheet.getRow(i).getCell(4).getNumericCellValue()*4);
	            boxlist[i][3] = (int)(sheet.getRow(i).getCell(5).getNumericCellValue()*4);
	         }
	         return boxlist;
	   			} finally {
	         
	         
	   		}
	   
	   
	   }
   
   
   
   
   
   
   
   }