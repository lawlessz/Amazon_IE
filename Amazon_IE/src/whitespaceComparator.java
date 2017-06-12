/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//NOTE:COMPARES VOLUME RIGHT NOW
public class whitespaceComparator implements Comparator<Node> { 

	public int compare(Node o1, Node o2) {
		//if(o1.whitespace < o2.whitespace){
		//	return 0;
		//}
		//return o1.whitespace - o2.whitespace; // for comparing whitespace
		return o1.smallestVolume - o2.smallestVolume;
		//return o1.AmazonBoxVolumeCM3 - o2.AmazonBoxVolumeCM3;
	}
	/*

*/
	//public int sort(ArrayList<Node> listN) {
		
		//Collections.sort(listN);
	//}






}
