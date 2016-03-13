import java.util.Scanner;
import java.io.*;

public class TheList {

	///////////////////////////// Global Objects
	private Node head;
	private Node tail; 
	private int NumberOfNodes=0; // Node counter
	Scanner kb = new Scanner(System.in); // Keyboard
	FileReader FileIn = null; // File reader
	FileWriter FileOut = null; // Write to file out
	String workingDirectory = System.getProperty("user.dir"); // Get the path of the user
	//////////////////////////////End Global Objects

	public void getList(){
		try {	
			System.out.println("Please wait, attempting to load your address book...");
			FileReader stream = new FileReader(workingDirectory + "/NodeSaver"); //create a stream to NodeSaver
			BufferedReader read = new BufferedReader(stream);
			String line;

			while ((line = read.readLine()) != null){ /// Will read line by line and build a node
				Node outNode = new Node();
				outNode.setName(line); // Set the data in order that it came in
				line = read.readLine();
				outNode.setPhone(line);
				line = read.readLine();
				outNode.setEmail(line);	
				addNumber(outNode);		 // Call overloaded addNumber, pass in new node
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("No list found. Save to create new file");
		} catch (IOException e) {
			System.out.println("No list found. Save to create new file");
		}
	}

	public void setList(){ // Save list to file
		String fileName = "NodeSaver"; /// Set the file name here where we want to write the list to
		Node current = new Node();
		current = head;
		try {
			System.out.println("Saving...");
			PrintWriter outputStream = new PrintWriter(fileName); // Open stream to NodeSaver
			for (int i = 0; i < NumberOfNodes;i++){ // Print 3 lines into the text file
				// They will be read in this order then creating a node
				outputStream.println(current.getName().toUpperCase().substring(0, 1) + current.getName().substring(1));
				outputStream.println(current.getPhone());
				outputStream.println(current.getEmial());
				current = current.getNextNodeInLine();
			}
			outputStream.close();
		} catch (FileNotFoundException e){
			System.out.println("Sorry, there was an error locating the write file");
		}
	}

	private void addNumber(Node fromFile){ // Overloaded addNumber for nodes passed in from file

		if (head == null){ /// If no nodes in list, set it as head
			head = fromFile;
			NumberOfNodes = 1;
		}
		else{ // Else, add it on to the back end and label it the tail
			sortAlpha(fromFile);
		}
	}

	public void addNumber(){ // Manual entry addNumber
		Node n = new Node();
		System.out.println("First name:");
		String first = kb.nextLine();
		System.out.println("Last name:");
		String last = kb.nextLine();
		String nameout = last + ", " + first;
		n.setName(nameout);
		System.out.println("Phone:");
		n.setPhone(kb.nextLine());
		System.out.println("Email:");
		n.setEmail(kb.nextLine());	

		if (head == null){ /// If no nodes in list, set it as head
			head = n;
			NumberOfNodes = 1;
		}
		else{ // Run sorting algo
			sortAlpha(n);
		}	
	}

	public void searchForNode(String toLookFor, int typeOfSearchCode){
		Node current = new Node();
		current = head;
		boolean found = false;
		int count = 1;

		while (count <= NumberOfNodes){
			/// Look for matching name based on name search
			if (typeOfSearchCode == 1){ // 1 = name search
				if (current.getName().toLowerCase().contains(toLookFor)){
					System.out.println("Name:" + current.getName());
					System.out.println("Email:" +current.getEmial());
					System.out.println("Number:" +current.getPhone());
					System.out.println("Entry ID: " + count + "\n");
					found=true;
				}
			}
			/// Look for matching email for email search
			if (typeOfSearchCode == 2){ // 2 = email search
				if (current.getEmial().toLowerCase().contains(toLookFor)){
					found = true;
					System.out.println("Name:" + current.getName());
					System.out.println("Email:" +current.getEmial());
					System.out.println("Number:" +current.getPhone());
					System.out.println("Entry ID: " + count + "\n");
					found=true;
				}
			}/// Check if at the last node and break
			if (current.getNextNodeInLine() == null)
				break;
			else //Update current node and loop again
				current = current.getNextNodeInLine();
			count++;
		}
		if (!found) System.out.println("No matching entry found.");
	}

	public void viewList(){ // Displays list, if have time make alpha order
		Node temp = new Node();
		temp = head;
		if (NumberOfNodes == 0) System.out.println("No Nodes! Try loading a saved list (L)");
		else{
			int counter = 1;
			while (temp != null) { // While we have nodes in the list that we haven't gone over
				System.out.println("Name: " + temp.getName());
				System.out.println("Email: " +temp.getEmial());
				System.out.println("Number: " +temp.getPhone());
				System.out.println("Entry ID: " + counter + "\n");
				temp=temp.getNextNodeInLine();
				counter++;
			}
		}	
	}

	public void DeleteNode(int indexToDelete){ // Pass in index in list to be deleted
		Node current = new Node();
		Node prev = new Node();
		boolean hitlast = false; // Hit last indicator
		current = head;
		int nodeAt = 1;
		// Check if its the first node
		if (indexToDelete > NumberOfNodes){
			System.out.println("Invalid index");
		}
		else if (indexToDelete == 1){
			//Is there only 1 node?
			if (head.getNextNodeInLine()== null){
				System.out.println("Sorry, only one address in the list");
			}
			else // Cut the ties
				head = head.getNextNodeInLine();
			NumberOfNodes--;
		}
		// Or check if nodeIndex is the last node
		else if (indexToDelete == NumberOfNodes){
			while (current.getNextNodeInLine()!= null){
				prev=current;
				current = current.getNextNodeInLine();
				nodeAt++;
			}
			tail = prev;
			tail.setNextNodeInLine(null);
			NumberOfNodes--;
		}
		// Else remove from middle
		else {
			while (nodeAt != indexToDelete){
				prev=current;
				current = current.getNextNodeInLine();
				nodeAt++;
			}
			/// Heres the actual cut and re-order once input node hit
			if (hitlast == false){
				prev.setNextNodeInLine(current.getNextNodeInLine());
				NumberOfNodes--;
			}		
		}
		System.out.println("Complete.\n");
	}

	private void sortAlpha(Node toAdd){ // Sort nodes alphabetically by last name, All IO through here
		Node current = new Node();
		Node previous = new Node();
		boolean swap = false;
		current = head; // shows how nodes act like pointers- point to same object- interesting

		// BLOCK FOR ONLY ONE NODE IN LIST OR SWAP HEAD 
		if (current.getNextNodeInLine() == null || head.getName().toLowerCase().compareTo(toAdd.getName().toLowerCase())>0){
			if (current.getName().toLowerCase().compareTo(toAdd.getName().toLowerCase())>0){
				toAdd.setNextNodeInLine(head); // Set as head and set head as next
				head = toAdd;
				swap = true;
				NumberOfNodes++;
			}
			else { // Tack on behind head
				head.setNextNodeInLine(toAdd);
				swap = true;
				NumberOfNodes++;
			}
		} 
		else // END OF ONE NODE BLOCK, BEGIN MULTIPLE NODES ITERATION BLOCK
		{  
			if (swap == false) {	
				while (swap == false && current.getNextNodeInLine()!= null){
					previous = current;
					current = current.getNextNodeInLine(); 
					if (current.getName().toLowerCase().compareTo(toAdd.getName().toLowerCase())>0){
						toAdd.setNextNodeInLine(current); // Here is the linking
						previous.setNextNodeInLine(toAdd);
						swap = true;
						NumberOfNodes++;
					}
					else{
						previous = current; // Set previous
					}
				}
				if (swap == false){ // Add to tail
					previous.setNextNodeInLine(toAdd);
					tail = toAdd;
					NumberOfNodes++;
				}
			}
		}
	}
}
