import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */
public class itemComparator implements Comparator<Item> {

	public int compare(Item i1, Item i2) {
				return i2.xdim*i2.ydim - i1.xdim*i1.ydim;


	}




}