import java.util.Comparator;

public class MyComparator implements Comparator<EightPuzzleState> {
	public int compare(EightPuzzleState a, EightPuzzleState b) { //for priorityqueue?
		//a negative integer, zero or a positive, if the first argument is less than, equal to or 
		//greater than the second
		if(a.getfCost() < b.getfCost()) return -1; //a is less than
		if(a.getfCost() > b.getfCost()) return 1;
		return 0;
	}
}
