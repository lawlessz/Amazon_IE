import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	public int compare(Node o1, Node o2) {
		//if(o1.whitespace < o2.whitespace){
		//	return 0;
		//}
		//return o1.whitespace - o2.whitespace; // for comparing whitespace
		//return o1.smallestVolume - o2.smallestVolume;
		return o1.AmazonBoxVolumeCM3 - o2.AmazonBoxVolumeCM3;
	}
*/
	//public int sort(ArrayList<Node> listN) {
		
		//Collections.sort(listN);
	//}






}
