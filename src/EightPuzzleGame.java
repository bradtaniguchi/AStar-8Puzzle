import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack; 
import java.util.Queue; //for depth search
import java.util.PriorityQueue; //for AStar Search

public class EightPuzzleGame {
	private String goalString = "123804765";
	private ArrayList<String> exploredStates = new ArrayList<String>();
	private Queue<EightPuzzleState> statesToSearch = new LinkedList<EightPuzzleState>();
	private boolean foundWinner;
	
	public EightPuzzleGame(String startString, boolean debug) { //autostart game
		breadthSearch(startString, debug);
		foundWinner = false;
	}
	public EightPuzzleGame(){
		foundWinner = false;
		//default
	}
	/**BreadthSearch implementation*/
	public void breadthSearch(String startString, boolean debug) {
		ArrayList<String> exploredStates = new ArrayList<String>(); // Create list of explored states
		Queue<EightPuzzleState> statesToSearch = new LinkedList<EightPuzzleState>(); // creates list of states to explore, expand as needed
		
		EightPuzzleState parentState = new EightPuzzleState(startString); // no parent
		statesToSearch.add(parentState);// add parent state to be checked
		int loopcounter = 0; //keep track of how many while loops gone through, for debuggin purposes
		if(debug) System.out.println(String.format("%-20s %-20s %-6s","statesToSearch", "exploredStates", "loopcounter" )); //printout statetopline
		while(!(statesToSearch.isEmpty())) {
			loopcounter++;
			if(debug) System.out.println(String.format("%-20s %-20s %-6s", ("?: "+statesToSearch.size()),("X: " + exploredStates.size()), 
					("S: "+loopcounter))); //printout states while in loop
			EightPuzzleState state = statesToSearch.poll(); //pop state to check
			if(state.getString().equals(goalString)) { // if the state equals goal, exit 
				if(debug)System.out.println(String.format("%-20s %-20s %-6s","statesToSearch", "exploredStates", "loopcounter" )); //end print
				if(debug) {
					System.out.println("Winner!");
					printsolution(state); //go on to print the solution 
				} else {
					foundWinner=true; //otherwise just return that we found the winner in GLOBAL variable
				}
				return; //exit function loop
			}
			exploredStates.add(state.getString()); // add state is not a win
			ArrayList<EightPuzzleState> childrenStatesToCheck = new ArrayList<EightPuzzleState>();  
			//childrenStatesToCheck = state.getChildrenStates();
			for(EightPuzzleState mystate : state.getChildrenStates()) {
				if(!exploredStates.contains(mystate.getString())) {
					childrenStatesToCheck.add(mystate);
				}
			}
			//statesToSearch.addAll(state.getChildrenStates()); // does this do what I want
			if(!childrenStatesToCheck.isEmpty()) {
				statesToSearch.addAll(childrenStatesToCheck); //add all states to be checked
			}
		}
		if(debug) System.out.println("WINNER Not found?");
	} //end BreadthSearch
	/** A* search algorithm with manhattan flag, defaults to outofplace*/
	public void AStarSearch(String startString, boolean debug, boolean manhattan) { //work here
		foundWinner = false; //reset flag
		if(debug) System.out.println("\n\nStarting AStarSearch with startString: " + startString); //update later
		MyComparator comparator = new MyComparator(); //EightPuzzle implementation
		PriorityQueue<EightPuzzleState> statesToSearch = new PriorityQueue<EightPuzzleState>(comparator); //DEBUG  
		ArrayList<String> exploredStates = new ArrayList<String>();
		
		EightPuzzleState parentState = new EightPuzzleState(startString, null, 0, manhattan); //no parent, and no score as its the start
		statesToSearch.add(parentState); 
		int loopcounter = 0;
		int fc=9;
		if(debug) System.out.println(String.format("%-20s %-20s %-6s","statesToSearch", "exploredStates", "loopcounter", "fCost")); //printout statetopline
		while(!(statesToSearch.isEmpty())) {
			loopcounter++;
			if(debug) System.out.println(String.format("%-20s %-20s %-20s %-6s", ("?: "+statesToSearch.size()),("X: " + exploredStates.size()), 
					("S: "+loopcounter),("fC: "+fc))); //printout states while in loop
			EightPuzzleState state = statesToSearch.poll(); //pop off spot\
			if (state.getString().equals(goalString)) {
				fc=0;//found winner
				if(debug) System.out.println(String.format("%-20s %-20s %-6s","statesToSearch", "exploredStates", "loopcounter", "fCost" )); //printout statetopline
				if(debug) {
					System.out.println("Winner!");
					printsolution(state); //go on to print the solution
				} else {
					foundWinner=true; //otherwise just return that we found the winner in GLOBAL variable
				}
				return;
			} //else
			exploredStates.add(state.getString()); //add state that isn't a win
			fc = state.getfCost();
			ArrayList<EightPuzzleState> childrenStatesToCheck = new ArrayList<EightPuzzleState>();
			for(EightPuzzleState mystate : state.getChildrenStates(manhattan)) {
				if(!exploredStates.contains(mystate.getString())) {
					//childrenStatesToCheck.add(mystate);
					childrenStatesToCheck.add(mystate);
				}
			} //WORK BELOW
			if(!childrenStatesToCheck.isEmpty()) { //get children states
				statesToSearch.addAll(childrenStatesToCheck); //SHOULD add them in order 
			}
		}
	}
	/*private Queue<EightPuzzleState> fSort(Queue<EightPuzzleState> unsortedlist) {
		if(unsortedlist.size() == 1) return unsortedlist; //return 1 size list
		Queue<EightPuzzleState> retQueue = new LinkedList<EightPuzzleState>();
		for (EightPuzzleState item : unsortedlist) { //what to use that will run easiest?
			
		}
		return null; //REMOVE LATER
	}*/
	/**Print out solution to problem*/
	public void printsolution(EightPuzzleState winner) {
		Stack<EightPuzzleState> solutionPath = new Stack<EightPuzzleState>();
		EightPuzzleState tempNode = winner;
		solutionPath.push(tempNode);
		tempNode = tempNode.getParent(); //get parent
		while(tempNode.getParent() != null) {
			solutionPath.push(tempNode);
			tempNode = tempNode.getParent();
		}
		solutionPath.push(tempNode);
		int loopSize = solutionPath.size();
		for(int i=0; i < loopSize; i++) {
			tempNode = solutionPath.pop();
			System.out.println("["+tempNode.getString()+"]");
		}
		System.out.println("Number Of Moves:" + loopSize);
	}
	public boolean getWinner() {
		return foundWinner;
	}
}
