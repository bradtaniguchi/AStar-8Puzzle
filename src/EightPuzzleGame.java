import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack; 
import java.util.Queue; //for depth search

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
		EightPuzzleState parentState = new EightPuzzleState(startString); // no parent
		statesToSearch.add(parentState);// add parent state to be checked
		int loopcounter = 0;
		if(debug) System.out.println(String.format("%-20s %-20s %-6s","statesToSearch", "exploredStates", "loopcounter" ));
		while(!(statesToSearch.isEmpty())) {
			loopcounter++;
			if(debug) System.out.println(String.format("%-20s %-20s %-6s", ("?: "+statesToSearch.size()),("X: " + exploredStates.size()), 
					("S: "+loopcounter)));
			EightPuzzleState state = statesToSearch.poll(); //pop state
			if(state.getString().equals(goalString)) {
				if(debug)System.out.println(String.format("%-20s %-20s %-6s","statesToSearch", "exploredStates", "loopcounter" ));
				if(debug) {
					System.out.println("Winner!");
					printsolution(state);
				} else {
					foundWinner=true;
				}
				return;
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
<<<<<<< HEAD
	/** A* search algorithm*/
	public static void AStarSearch(String startString, boolean debug) { //work here
		
=======
	public static void AStarSearch(String startString, boolean debug) { //work here
		EightPuzzleState parentState = new EightPuzzleState(startString); //no parent
		states.toSearch.add(parentState);
		int loopcounter = 0;
		if(debug) System.out.println("Starting AStarSearch..."); //update later
		while(!(statesToSearch.isEmpty())) {
			
		}
>>>>>>> 838dc379e142d0c737d9a8794667bd71e8c9071c
	}
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
