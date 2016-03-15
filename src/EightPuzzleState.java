import java.util.ArrayList; //to return items in
/**
 * 
 * @author brad
 * For A* project
 */

public class EightPuzzleState {
<<<<<<< HEAD
=======
	private goalString = "123804765";
>>>>>>> 838dc379e142d0c737d9a8794667bd71e8c9071c
	private EightPuzzleState parent; //points to parent
	private String stateString; //acts as actual state
	public EightPuzzleState(String state) {
		parent = null;
		stateString = state;
	}
	public EightPuzzleState(String state, EightPuzzleState parent) {
		this.parent = parent; //assign parent to class
		this.stateString = state;
	}
	
	public EightPuzzleState getParent() {
		return parent;
	}
	
	public String getString() {
		return stateString;
	}
	public ArrayList<String> getChildrenStrings() {
		ArrayList<String> returnstates = new ArrayList<String>();
		if (!((getSpace() == 0) || (getSpace() == 1) || (getSpace() == 2))) {  //UP
			returnstates.add(moveUp());
		}
		if(!((getSpace() == 6) || (getSpace() == 7) || (getSpace() == 8))) { //DOWN
			returnstates.add(moveDown());
		}
		if(!((getSpace() == 0) || (getSpace() == 3) || (getSpace() == 6))) {//LEFT
			returnstates.add(moveLeft());
		}
		if(!((getSpace() == 2) || (getSpace() == 5) || (getSpace() == 8))) {//RIGHT
			returnstates.add(moveRight());
		}
		return returnstates; //list of strings
	}
	
	public ArrayList<EightPuzzleState> getChildrenStates() {
		ArrayList<EightPuzzleState> returnstates = new ArrayList<EightPuzzleState>();
		
		if (!((getSpace() == 0) || (getSpace() == 1) || (getSpace() == 2))) {  //UP
			EightPuzzleState childstate = new EightPuzzleState((String)moveUp(),this);
			returnstates.add(childstate);
		}
		if(!((getSpace() == 6) || (getSpace() == 7) || (getSpace() == 8))) { //DOWN
			EightPuzzleState childstate = new EightPuzzleState((String)moveDown(), this);
			returnstates.add(childstate);
		}
		if(!((getSpace() == 0) || (getSpace() == 3) || (getSpace() == 6))) {//LEFT
			EightPuzzleState childstate = new EightPuzzleState((String)moveLeft(), this);
			returnstates.add(childstate);
		}
		if(!((getSpace() == 2) || (getSpace() == 5) || (getSpace() == 8))) {//RIGHT
			EightPuzzleState childstate = new EightPuzzleState((String)moveRight(), this);
			returnstates.add(childstate);
		}
		return returnstates; //list of strings
	}
<<<<<<< HEAD
	
=======
	/**Get how many spots in current state, are out of palce*/
	public int getOutOfPlace() { 
		int ret = 0;
		for (Char x : stateString.toCharArray()) { //DEBUG
			if (x != goalString) {
				ret++; //increment number out of bounds
			}
		}
		return ret; 
	}
	/** Utility method to print out the state*/
	public void printStatus() {
		System.out.println("stateString: " + stateString);
		if (parent == null) System.out.println("parent: null");
		else System.out.println("parent: [" + parent.getString()+ "] ");
	}
>>>>>>> 838dc379e142d0c737d9a8794667bd71e8c9071c
	private int getSpace() { //returns position of 0 in string
		return stateString.indexOf("0");
	}
	private String moveUp() {
		return swap((int) getSpace()-3, getSpace(), stateString);
	}
	private String moveDown() {
		return swap((int) getSpace()+3, getSpace(), stateString);
	}
	private String moveLeft() {
		return swap((int) getSpace()-1, getSpace(), stateString);
	}
	private String moveRight() {
		return swap((int) getSpace()+1, getSpace(), stateString);
<<<<<<< HEAD
	}
	/** Utility method to print out the state*/
	public void printStatus() {
		System.out.println("stateString: " + stateString);
		if (parent == null) System.out.println("parent: null");
		else System.out.println("parent: [" + parent.getString()+ "] ");
	}
	public static String swap(int index1, int index2, String string) {
=======
	} 
	private static String swap(int index1, int index2, String string) {
>>>>>>> 838dc379e142d0c737d9a8794667bd71e8c9071c
		char[] c = string.toCharArray(); //trun to array
		char temp = c[index1]; //move a into temp
		c[index1] = c[index2]; //move b into a
		c[index2] = temp; //move temp into b
		String newstring = new String(c); //create new string
		return newstring;
	}
<<<<<<< HEAD
=======
	/** DONT USE THIS IF POSSIBLE */
>>>>>>> 838dc379e142d0c737d9a8794667bd71e8c9071c
	public static String swapOLD(int index1, int index2, String string) {
		StringBuilder sb = new StringBuilder(string);
		char ch1 = string.charAt(index1);
		char ch2 = string.charAt(index2);
		sb.setCharAt(index1, ch1);
		sb.setCharAt(index2, ch2);
		String restring = sb.toString(); //cast
		return restring;
	}
	/** Static utility method to check if two states are equal
	 * Usage: EightPuzzleState.equals(STATE1, STATE2)
	 * */
	public boolean equals(EightPuzzleState State) {
		return (stateString.equals(State.getString()));
	}
	
}
