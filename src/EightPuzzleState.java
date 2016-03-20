import java.util.ArrayList; //to return items in
/**
 * 
 * @author brad
 * For A* project
 */

public class EightPuzzleState {
	private String goalString = "123804765";
	private EightPuzzleState parent; //points to parent
	private String stateString; //acts as actual state
	private int hScore; //Heuristic cost
	private int gScore; //Distance score
	private int fCost; //total Score
	
	public EightPuzzleState(String state) { //parent basic constructor
		parent = null;
		stateString = state;
		gScore = 0;
		hScore = getOutOfPlace(); //is this ok? 
		fCost = gScore + hScore;
	}
	/*public EightPuzzleState(String state, EightPuzzleState parent) { //REMOVE LATER
		this.parent = parent; //assign parent to class
		this.stateString = state;
		gScore = 0;
	}*/
	public EightPuzzleState(String state, EightPuzzleState parent, int gScore) { //main oncstructor
		this.parent = parent; //assign parent to class
		this.stateString = state;
		this.gScore = gScore; //define the gScore upon creation of object
		this.hScore = getOutOfPlace();
		this.fCost = this.gScore + this.hScore;  
	}
	public EightPuzzleState getParent() {
		return parent;
	}
	public String getString() {
		return stateString;
	}
	public int getGScore() {
		return gScore;
	}
	public int getHScore() {
		return hScore;
	}
	public int getfCost() {
		return fCost;
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
			EightPuzzleState childstate = new EightPuzzleState((String)moveUp(),this, gScore + 1);
			returnstates.add(childstate);
		}
		if(!((getSpace() == 6) || (getSpace() == 7) || (getSpace() == 8))) { //DOWN
			EightPuzzleState childstate = new EightPuzzleState((String)moveDown(), this, gScore + 1);
			returnstates.add(childstate);
		}
		if(!((getSpace() == 0) || (getSpace() == 3) || (getSpace() == 6))) {//LEFT
			EightPuzzleState childstate = new EightPuzzleState((String)moveLeft(), this, gScore + 1);
			returnstates.add(childstate);
		}
		if(!((getSpace() == 2) || (getSpace() == 5) || (getSpace() == 8))) {//RIGHT
			EightPuzzleState childstate = new EightPuzzleState((String)moveRight(), this, gScore + 1);
			returnstates.add(childstate);
		}
		return returnstates; //list of strings
	}
	/**Get how many spots in current state, are out of place*/
	public int getOutOfPlace() { 
		int ret = 9; //9 not in place
		char[] mygoalString = goalString.toCharArray();
		char[] mystateString = stateString.toCharArray();
		for (int i=0; i < 9; i++) { //8 or 9?
			if(mygoalString[i] == mystateString[i]) {
				ret--; //decrement those out of place
			}
		}
		return ret; //return number out of place
	}
	/**Gets ManHattan distance, or distance from every tile to its goal position
	 * NOTE if there needs 1 swap between numbers, you will get two. IDK if thise works correctly
	 * BUT it is a good indication of how "far" tiles are out of place*/
	public int getTotalOutOfPlace() { //DEBUG
		int totalcount = 0; //0 are out of place, we assume
		char[] stateArray = goalString.toCharArray(); //test?
		for(char c : stateArray){ //for each character in stateArray
			totalcount += getTileDist(c);
		}
		return totalcount;
	}
	/** Utility method to print out the state*/
	public void printStatus() {
		System.out.println("stateString: " + stateString);
		if (parent == null) System.out.println("parent: null");
		else System.out.println("parent: [" + parent.getString()+ "] ");
	}
	/**Gets the Distance of character, to its goal state*/
	private int getTileDist(char tileCharacter) {
		char[][] goalArray = toArray(goalString, false); 
		char[][] stateArray = toArray(stateString, false);
		int x_value = getXofArray(tileCharacter, stateArray); //pos of current tilecharacter
		int y_value = getYofArray(tileCharacter, stateArray); 
		int x_goal = getXofArray(tileCharacter, goalArray);//pos of what we want current tilecharacter
		int y_goal = getYofArray(tileCharacter, goalArray);
		int distance = Math.abs(x_value - x_goal) + Math.abs(y_value - y_goal); //get abs distance
		//System.out.println("DISTANCE:" + distance); //to hard test in runner
		return distance;
	}
	/**Finds the X cord in the Array Version of State*/
	public int getXofArray(char tilecharacter, char[][] array) {
		for(int y=0; y<3; y++) {
			for(int x=0;x<3;x++) {
				if(array[x][y] == tilecharacter) {
					return x;
				}
			}
		}
		System.out.println("ERROR in getXOfArray");
		return 0; //errorState
	}
	/**Finds the Y cord in the Array Version of State*/
	public int getYofArray(char tilecharacter, char[][] array) {
		for(int y=0; y<3; y++) {
			for(int x=0;x<3;x++) {
				if(array[x][y] == tilecharacter) { //better way to do this??
					return y;
				}
			}
		}
		System.out.println("ERROR in getYOfArray");
		return 0;
	}
	/**Changes the 1d given String to a 2d array*/
	public char[][] toArray(String state, boolean print) {
		if (state.length() != 9) {
			System.out.println("ERROR toArray is length:" + state.length());
			return null;
		}
		char[] stateArray = state.toCharArray(); //cast to 1d array
		int cnt = 0; //counter for stateArray
		char[][] retarray = new char[3][3];
		for(int y=0; y< 3; y++) {
			for(int x=0; x<3; x++) {
				retarray[x][y] = stateArray[cnt]; //assign
				if(print) System.out.print(retarray[x][y]);
				cnt++;
			}
			if(print) System.out.println("");
		}
		return retarray;
	}
	/**Changes the 1d stateString to a 2d array*/
	public char[][] toArray(boolean print) {//DEBUG
		char[] stateArray = stateString.toCharArray(); //cast to 1d array
		int cnt = 0; //counter for stateArray
		char[][] retarray = new char[3][3];
		for(int y=0; y< 3; y++) {
			for(int x=0; x<3; x++) {
				retarray[x][y] = stateArray[cnt]; //assign
				if(print) System.out.print(retarray[x][y]);
				cnt++;
			}
			if(print) System.out.println("");
		}
		return retarray;
	}
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
	}
	private static String swap(int index1, int index2, String string) {
		char[] c = string.toCharArray(); //trun to array
		char temp = c[index1]; //move a into temp
		c[index1] = c[index2]; //move b into a
		c[index2] = temp; //move temp into b
		String newstring = new String(c); //create new string
		return newstring;
	}
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
