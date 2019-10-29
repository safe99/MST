package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import structures.*;

public class SearchEngine {
	static Scanner sc1, sc2;
	
	public static void main(String[] args) throws IOException {
		sc1 = new Scanner(System.in);
		System.out.print("Enter word:");
		String word = sc1.nextLine();
		Graph g = new Graph("graph1.txt");
		//MST.initialize(g);
		
		MST.execute(MST.initialize(g));
		
		
		
		
				
	}
}
