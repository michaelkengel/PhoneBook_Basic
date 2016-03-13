
public class Node { // Simple node class with 3 text fields

	private String name;
	private String email;
	private String phone;
	private Node next = null;
	
	public void setName(String nameIn){
		name = nameIn;	
	}
	public void setEmail(String emailIn){
		email = emailIn;	
	}

	public void setPhone(String phoneIn){
		phone = phoneIn;	
	}

	public String getName(){
		return name;
	}

	public String getEmial(){
		return email;
	}

	public String getPhone(){
		return phone;
	}

	public Node getNextNodeInLine() {
		return next;
	}

	public void setNextNodeInLine(Node next) {	
		this.next = next;
	}
}
