import java.util.Scanner;

public class Driver {

	public static void main(String args[]){

		int[] coo = new int[4];
		int k = coo.length;
		
		TheList list = new TheList();
		Scanner kb = new Scanner(System.in);
		boolean runList = true;
		list.getList();
		System.out.println("Done.");
		while (runList == true){
			System.out.println(
							"~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
							+"Add phone (A)\n"
							+ "Save list (S)\n"
							+ "View the list (V)\n"
							+ "Search name (F)\n"
							+ "Search email (E)\n"
							+ "Delete entry (D)\n"
							+ "Quit (Q)\n"
							+ "~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

			switch (kb.next().toLowerCase())
			{
			case "a":
				list.addNumber();
				break;
			case "v":
				list.viewList();
				break;
			case "f":
				System.out.println("Please enter the name you would like to search for");
				String winggedHippo = kb.next();
				list.searchForNode(winggedHippo, 1);
				break;
			case "e":
				System.out.println("Please enter the name you would like to search for");
				String winggedOctopus = kb.next();
				list.searchForNode(winggedOctopus, 2);
				break;
			case "s":
				list.setList();
				System.out.println("Done.\n");
				break;
			case "q":
				System.out.println("Goodbye");
				kb.close();
				System.exit(0);
			case "d":
				boolean run = true;
				int nodeByeBye=0;
				while (run == true){
					try {
						System.out.println("What entry (by entry ID) would you like to delete?");
						String nodeGoBye = kb.next();
						nodeByeBye = Integer.parseInt(nodeGoBye);
						run = false;
					}
					catch (Exception e){
						System.out.println("Invalid input");
						run = true;
					}
				}
				list.DeleteNode(nodeByeBye);
				break;
			}
		}
	}
}	
