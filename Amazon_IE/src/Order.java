/*
 * Developed By Zach Lawless for Amazon capstone project UW 2017
 * Will produce possible boxes and packaging locations for a range of Amazon Boxes.
 * 
 * 
 * Contact z.law@comcast.net for questions, I can modify and clarify things swiftly
 * 
 */
public class Order {

	public Item[] arr;
	public int numI;
	public int index = 0;
	
	private int x;

	int AmazonVol; //Volume of box used - verified
	int foundVol; //volume of pack that it could fit in - verified and in cm
	int realAmazonVol;//realvol in inch^3
	int testVol; //VOLUME OF BOX SENT IN inches
	int startingVolA; // Actually the volume that we output of the box that worked
	int boxP;
	int finalGrid;
	int outputAmazon;
	

	
	
	
	
	
	
}
