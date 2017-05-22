
import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFileV2 {

   
   public static List<Object> readExcel(){
	   try {
		   
	         FileInputStream file = new FileInputStream(new File("Test.xlsx"));
	         XSSFWorkbook workbook = new XSSFWorkbook(file);
	         XSSFSheet sheet = workbook.getSheetAt(0);
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
	         double[][] itemdata = new double[100000][3];
	         double[][] rawboxdata = new double[1][3];
             List<Order> o = new ArrayList<Order>();
	         while (sheet.getRow(index) != null) {
	        	numofitems = 0;
             	Order x = new Order();
	            while (sheet.getRow(index).getCell(1).getStringCellValue() == sheet.getRow(count).getCell(1).getStringCellValue()) {

	               for (int i = 0; i < sheet.getRow(count).getCell(12).getNumericCellValue(); i++) {
	            	  
	            	   Item holdI = new Item();
	            	   
	                  itemdata[i + numofitems][0] = sheet.getRow(count).getCell(5).getNumericCellValue(); System.out.print(itemdata[i + numofitems][0] + " ");
	                  itemdata[i + numofitems][1] = sheet.getRow(count).getCell(6).getNumericCellValue(); System.out.print(itemdata[i + numofitems][1] + " ");
	                  itemdata[i + numofitems][2] = sheet.getRow(count).getCell(7).getNumericCellValue(); System.out.println(itemdata[i + numofitems][2]);
	                  
	                  x.arr[x.index].xdim = (int) ((itemdata[i + numofitems][0])*2.5);
	                  x.arr[x.index].xdim = (int) ((itemdata[i + numofitems][1])*2.5);
	                  x.arr[x.index].xdim = (int) ((itemdata[i + numofitems][2])*2.5);
	                  x.index++;
	               }
	               numofitems = numofitems + (int)sheet.getRow(count).getCell(12).getNumericCellValue();
	               count++;
	               if (count > sheet.getLastRowNum()) {
	            	   
	                  break;
	               }
	            }
	            System.out.println(numofitems);
	            rawboxdata[0][0] = sheet.getRow(index).getCell(9).getNumericCellValue();  System.out.print(rawboxdata[0][0] + " ");
	            rawboxdata[0][1] = sheet.getRow(index).getCell(10).getNumericCellValue(); System.out.print(rawboxdata[0][1] + " ");
	            rawboxdata[0][2] = sheet.getRow(index).getCell(11).getNumericCellValue(); System.out.println(rawboxdata[0][2]);
                
	            System.out.println();
	            index = count;
	            o.add(x);
	         }
	         
	         file.close();
	         FileOutputStream out = new FileOutputStream(new File("Test.xlsx"));
	         workbook.write(out);
	         out.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	return null;
	   
	   }
   //x = 1, y = 2, z = 3 stored in cm rounded up
   public static int[][] fetchBoxes() {
	int[][] arr = new int[21][4];
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
	
	/*
	arr[14][1] = ;
	arr[14][2] = ;
	arr[14][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	
	arr[][1] = ;
	arr[][2] = ;
	arr[][3] = ;
	*/
	return arr;
	   
   }

   
   
   
   
   
   
   
   
   
   }