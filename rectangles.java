package AreaRectangleUnion;
import AreaRectangleUnion.Rectangle;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;
/*
 My algorithm has an events, active and track variable. Events is all the X values so a given rectangle will 
 have 2 events (start and end). Track starts at the leftmost event and slowly moves right (the vertical line). 
 The active will be the events that are active so the events that track has passed by and they will be 
 inserted based on their Y coordinates, ordered mainly by y1. 
 I used the vertical line sweep.
 */
public class rectangles {
  public static void main(String args[]) throws Exception {
		java.io.BufferedReader br = new java.io.BufferedReader(
		        new java.io.InputStreamReader( System.in ) ) ;
//		BufferedReader buffer = new BufferedReader(new FileReader("input.txt"));
		   ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
		   //while loop is to read in inputs
		   while(true){
			   String test = br.readLine();
			   //String test = buffer.readLine();
			   if (test==null || test.equals("")) 
					break;
				StringTokenizer a = new StringTokenizer(test);
				rectangle.add(new Rectangle(Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken()),
						Integer.parseInt(a.nextToken()), Integer.parseInt(a.nextToken())));
			}
			long area = 0;
			//initializing the events 
			for(int i = 0; i<rectangle.size(); i++){
				events.add(new IntInt(rectangle.get(i).x1, i));
				events.add(new IntInt(rectangle.get(i).x2, i));
			}
			track = events.first().x;
			
			//finding the area and updating the events that are active 
			while (!events.isEmpty()){
				updateActive(rectangle);
				if (!events.isEmpty())
					area += instanceArea();
				}
			System.out.println(area);
	}
	//active will be the y range of the active rectangles
	static TreeSet<IntInt> active = new TreeSet<IntInt>();
	static int track;
	static TreeSet<IntInt> events = new TreeSet<IntInt>();
	
	static void updateActive(ArrayList<Rectangle> rectangle) {
		//accounts for more than one rectangle start at that x position
		while(!events.isEmpty() && events.first().x <= track) {
			IntInt decide = events.pollFirst();
			Rectangle temp = rectangle.get(decide.index);
			if (decide.x == rectangle.get(decide.index).x2) { 
				//if it's an ending event, need to find in active n delete
				active.remove(new IntInt (temp.y1, temp.y2));
				if (active.isEmpty() && !events.isEmpty())
					//b/c deleting from active, need to update track if no events in active
					track = events.first().x; 
			} else //it's a starting event
				active.add(new IntInt(temp.y1, temp.y2));
		}
	}
	static long instanceArea() {
		int width = events.first().x-track;
		track = events.first().x;
		int len = lengthVertical();
		return width*len;
	}
	static int lengthVertical() {
		//Horizontal line Sweep to find vertical length
		java.util.Iterator<IntInt> it = active.iterator();
		int begin = active.first().x;
		int ending = active.first().index;
		int length = 0;
		//looked at first alrdy so
		it.next();
		while (it.hasNext()) { //3 cases:
			IntInt next = it.next();
			//1. next rectangle is fully in the other
			if (next.index <= ending) //if next ends before ending
				continue;
			//2. next starts inside but ends outside
			else if (next.x < ending && next.index > ending)
				ending = next.index;
			//3. disconnected rectangle, save the length and re-adjust beg +end
			else {
				length += ending - begin;
				begin = next.x;
				ending = next.index;
			}
		} //add the last length
		length += ending - begin;
		return length;
	}
	static class IntInt implements Comparable<IntInt>{
		public int x, index;
		public IntInt (int x1, int i) { x = x1;	index = i; }
		public int compareTo(IntInt b) {
			if (this.x != b.x)
				return this.x-b.x;
			else
				return  this.index-b.index; }
	}
}
