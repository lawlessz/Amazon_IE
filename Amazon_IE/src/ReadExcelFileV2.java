
import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFileV2 {

   
   public  List<Order> readExcel() throws IOException{
	   try {
		   
	         FileInputStream file = new FileInputStream(new File("TestNew.xlsx"));
	         XSSFWorkbook workbook = new XSSFWorkbook(file);
	         XSSFSheet sheet = workbook.getSheetAt(9);
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
	         double[][] rawboxdata = new double[1][3];
             List<Order> oList = new ArrayList<Order>();
	         while (sheet.getRow(index) != null) {
	        	numofitems = 0;

	            while (sheet.getRow(index).getCell(1).getStringCellValue() == sheet.getRow(count).getCell(1).getStringCellValue()) {

	               for (int i = 0; i < sheet.getRow(count).getCell(12).getNumericCellValue(); i++) {
	            	  
	            	  Item holdI = new Item();
	            	  //x.arr[x.index] = holdI;
	            	   
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
	            rawboxdata[0][0] = sheet.getRow(index).getCell(9).getNumericCellValue();  //System.out.print(rawboxdata[0][0] + " ");
	            rawboxdata[0][1] = sheet.getRow(index).getCell(10).getNumericCellValue(); //System.out.print(rawboxdata[0][1] + " ");
	            rawboxdata[0][2] = sheet.getRow(index).getCell(11).getNumericCellValue(); //System.out.println(rawboxdata[0][2]);
                //make item here
             	Order x = new Order();
             	x.arr = new Item[numofitems];
	            for(int i = 0; i < numofitems; i++){
	            	Item ihold = new Item((int)itemdata[i][0],(int)itemdata[i][1],(int)itemdata[i][2]);
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
   public static int[][] fetchBoxes() {
	int[][] arr = new int[22][6];//add one
	arr[0][1] = 25;
	arr[0][2] = 18;
	arr[0][3] = 8;
	
	arr[1][1] = 25;
	arr[1][2] = 18;
	arr[1][3] = 13;
	
	arr[2][1] = 33;
	arr[2][2] = 21;
	arr[2][3] = 20;
	
	arr[3][1] = 34;
	arr[3][2] = 24;
	arr[3][3] = 8;
	
	arr[4][1] = 34;
	arr[4][2] = 27;
	arr[4][3] = 12;
	
	arr[5][1] = 35;
	arr[5][2] = 32;
	arr[5][3] = 23;
	
	arr[6][1] = 37;
	arr[6][2] = 20;
	arr[6][3] = 18;
	
	arr[7][1] = 39;
	arr[7][2] = 32;
	arr[7][3] = 8;
	
	arr[8][1] = 40;
	arr[8][2] = 30;
	arr[8][3] = 13;
	
	arr[9][1] = 44;
	arr[9][2] = 35;
	arr[9][3] = 20;
	
	arr[10][1] = 45;
	arr[10][2] = 23;
	arr[10][3] = 22;
	
	arr[11][1] = 48;
	arr[11][2] = 33;
	arr[11][3] = 15;
	
	arr[12][1] = 48;
	arr[12][2] = 37;
	arr[12][3] = 30;
	
	arr[13][1] = 48;
	arr[13][2] = 32;
	arr[13][3] = 28;
	
	arr[14][1] = 49;
	arr[14][2] = 35;
	arr[14][3] = 8;
	
	arr[15][1] = 55;
	arr[15][2] = 45;
	arr[15][3] = 13;
	
	arr[16][1] = 55;
	arr[16][2] = 45;
	arr[16][3] = 30;
	
	arr[17][1] = 24;
	arr[17][2] = 17;
	arr[17][3] = 5;
	
	arr[18][1] = 30;
	arr[18][2] = 23;
	arr[18][3] = 5;
	
	
	arr[19][1] = 25;
	arr[19][2] = 18;
	arr[19][3] = 5;
	
	arr[20][1] = 35;
	arr[20][2] = 23;
	arr[20][3] = 5;
	
	//big box to save
	arr[21][1] = 70;
	arr[21][2] = 60;
	arr[21][3] = 50;
	
	for(int i = 0; i < arr.length; i++){//vol
		arr[i][4] = arr[i][1]*arr[i][2]*arr[i][3];
	}
	for(int i = 0; i < arr.length; i++){//area
		arr[i][5] = arr[i][1]*arr[i][2];
	}
	
	java.util.Arrays.sort(arr, new java.util.Comparator<int[]>() {
	    public int compare(int[] a, int[] b) {
	        return Double.compare(a[4], b[4]);
	    }
	});
	
	Arrays.sort(arr, (a, b) -> Double.compare(a[4], b[4]));
	for(int i = 0; i < arr.length; i++){//vol
		System.out.println(arr[i][4]);
	}
	//Arrays.sort(arr);
	System.out.println(arr[0][1] + "code7");
	System.out.println(arr[0][5] + "code7");
	System.out.println(arr[20][5] + "code8");
	return arr;
	   
   }
   
   //x = 1, y = 2, z = 3 stored in cm rounded up



   
   //not being used
   public void writeExcel(List<Order> O) throws IOException{
	   try {
		   
		     Iterator<Order> iterator = O.iterator();
		   
	         FileInputStream file = new FileInputStream(new File("TestNew.xlsx"));
	         XSSFWorkbook workbook = new XSSFWorkbook(file);
	         XSSFSheet sheet = workbook.getSheetAt(4);
	         Iterator<Row> rowIterator = sheet.iterator();
	         int rownumber = 0;
	         while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
	            rownumber++;
	         }
	         int index = 0;
	         int count = 0;
	         int numofitems;
	         double[][] itemdata = new double[10][3];
	         double[][] rawboxdata = new double[1][3];
             List<Order> oList = new ArrayList<Order>();
	         while (sheet.getRow(index) != null) {
	        	numofitems = 0;
	            while (sheet.getRow(index).getCell(1).getStringCellValue() == sheet.getRow(count).getCell(1).getStringCellValue() && count != 26) {
	              // System.out.println("here" + count);
	               for (int i = 0; i < sheet.getRow(count).getCell(12).getNumericCellValue(); i++) {
	            	  
	            	  Item holdI = new Item();
	            	  //x.arr[x.index] = holdI;
	            	   
	                  itemdata[i + numofitems][0] = Math.ceil(sheet.getRow(count).getCell(5).getNumericCellValue()*2.54); //System.out.print(itemdata[i + numofitems][0] + " ");
	                  itemdata[i + numofitems][1] = Math.ceil(sheet.getRow(count).getCell(6).getNumericCellValue()*2.54); //System.out.print(itemdata[i + numofitems][1] + " ");
	                  itemdata[i + numofitems][2] = Math.ceil(sheet.getRow(count).getCell(7).getNumericCellValue()*2.54); //System.out.println(itemdata[i + numofitems][2]);
	                  
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
	            Row r1 = sheet.getRow(count);
                if (r1 == null) {
                   r1 = sheet.createRow(count);
                }
                Cell c1 = r1.getCell(26);//col16
                if (c1 == null) {
                   c1 = r1.createCell(26, Cell.CELL_TYPE_NUMERIC);
                }
                Order ord = iterator.next();
                //c1.setCellValue((ord.AmazonVol - ord.foundVol)); //*0.0610237
                //System.out.println(ord.AmazonVol);
                //System.out.println(ord.foundVol);
               // System.out.println(ord.foundVol*0.0610237);
                //System.out.println(ord.testVol);
                
                System.out.println(((ord.foundVol)*0.0610237 - ord.testVol));//*0.0610237
	            //System.out.println(numofitems);
	            

	            //rawboxdata[0][0] = sheet.getRow(index).getCell(9).getNumericCellValue();  //System.out.print(rawboxdata[0][0] + " ");
	            //rawboxdata[0][1] = sheet.getRow(index).getCell(10).getNumericCellValue(); //System.out.print(rawboxdata[0][1] + " ");
	            //rawboxdata[0][2] = sheet.getRow(index).getCell(11).getNumericCellValue(); //System.out.println(rawboxdata[0][2]);
                //make item here
             	//Order x = new Order();
             	//getClass()..x.arr = new Item[numofitems];
	            /*
	            for(int i = 0; i < numofitems; i++){
	            	Item ihold = new Item((int)itemdata[i][0],(int)itemdata[i][1],(int)itemdata[i][2]);
	            	x.arr[i] = ihold;
		            itemdata[i][0] = 0;
		            itemdata[i][1] = 0;
		            itemdata[i][2] = 0;

	            }
*/	
	            //System.out.println();
	            index = count;
	            //oList.add(x);
	         } 
	         //return oList;
	   			} finally {
	         
	         
	   		}
	   
	   }
   
   
   
   
   
   
   
   }