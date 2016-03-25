import java.util.PriorityQueue;
/**
 * 
 * @author Bradley Taniguchi
 *
 */

public class Runner {
	public static void main(String arg[]) {
		System.out.println("Starting Runner Method...");
		a_starrun();
		//breadthrun();
	}
	public static void a_starrun() { //note goal string "123804765"
		String TestString1 = "103824765"; //one from win
		//String TestString20 = "013824765"; // //3353???
		String TestString2 = "023184765"; // a few from win 
		String TestString3 = "813724650"; //difficult string
		EightPuzzleGame mygame = new EightPuzzleGame();
		/**Debug Print, and Manhattan usage*/
		boolean manhattan = true; 
		boolean outprint = true;
		mygame.AStarSearch(TestString1, outprint, manhattan);
		if (mygame.getWinner() && !outprint) {
			System.out.println("Found A Winner for 1");
		}
		mygame.AStarSearch(TestString2, outprint, manhattan);
		if (mygame.getWinner() && !outprint) {
			System.out.println("Found A Winner for 2");
		}
		mygame.AStarSearch(TestString3, outprint, manhattan);
		if (mygame.getWinner() && !outprint) {
			System.out.println("Found A Winner for 3");
		}
		/**Onto manhattan version*/
		manhattan = false;
		
	}
	public static void breadthrun() { //note goal string: "123804765"		
		System.out.println("Starting Runner Method...");
		//String TestString1 = "012345678"; //impossible to win?
		String TestString1 = "103824765"; //one move from win
		//String TestString2 = "123456780"; //need to test these two
		//String TestString3 = "087654321";
		EightPuzzleGame mygame = new EightPuzzleGame();
		mygame.breadthSearch(TestString1, true); //false to keep prints off
		System.out.println("END");
		//mygame.breadthSearch(TestString2, true);
	}
	public static void test7() { //test priorityqueue orders correctly
		String TestString1 = "123840765"; //one move from win
		EightPuzzleState testState = new EightPuzzleState(TestString1); //act as parent
		
		MyComparator comparator = new MyComparator(); //test this
		PriorityQueue<EightPuzzleState> statesToSearch = 
				new PriorityQueue<EightPuzzleState>(comparator);// and this
		for(EightPuzzleState state : testState.getChildrenStates()) {
			System.out.println("S:" + state.getString() + " fc: " + state.getfCost()); //print string
			statesToSearch.add(state); 
		}
		System.out.println("NEXT");
		for(EightPuzzleState state : statesToSearch) { //SHOULD be in different order
			System.out.println("S:" + state.getString() + " fc: " + state.getfCost());
		}
	}//SUCCESS it does sort according to fCost
	public static void test6() { //test of EightPuzzleState.getManhattan() 
		String TestString1 = "123804765";
		EightPuzzleState testState = new EightPuzzleState(TestString1);
		System.out.println(testState.getManhattan()); //should be 0
		//part two
		System.out.println("Part Two");
		
		String TestString2 = "103824765";
		EightPuzzleState testState2 = new EightPuzzleState(TestString2);
		System.out.println(testState2.getManhattan()); //should be 2, but it SHOULD take 1 swap
	}
	public static void test5() { //test EightPuzzleState.tileDist, on one character
		String TestString1 = "103824765"; //1 move from win
		EightPuzzleState testState = new EightPuzzleState(TestString1);
		//testState.getTileDist('0'); //check to see how far out 0 is from final pos, should be 1
		//part two
		System.out.println("PART 2");
		String TestString2 = "523804761";
		EightPuzzleState testState2 = new EightPuzzleState(TestString2);
		//testState2.getTileDist('1'); //should be 4, moves from corner
		//part three
		System.out.println("PART 3");
		String TestString3 = "123804765";
		EightPuzzleState testState3 = new EightPuzzleState(TestString3);
		//testState3.getTileDist('0'); //should be 0
	}//SUCCESS
	public static void test4() { //test EightPuzzleState.toArray Feature
		String TestString1 = "123804765"; //0 move from win
		EightPuzzleState testState = new EightPuzzleState(TestString1); 
		//testState.toArray(true); //print, dont accept output for test
	}//SUCCESS!
	public static void test3() { //test to see if MyComparator works as expected.
		//talk about lack of loops!
		String TestString1 = "123804765";//none off
		String TestString2 = "103824765";//two off?
		String TestString3 = "013824765";//four off?
		String TestString4 = "413820765";//six off?
		
		EightPuzzleState state1 = new EightPuzzleState(TestString1);
		EightPuzzleState state2 = new EightPuzzleState(TestString2);
		EightPuzzleState state3 = new EightPuzzleState(TestString3);
		EightPuzzleState state4 = new EightPuzzleState(TestString4);
		
		System.out.println(state1.getOutOfPlace() +" "+state1.getString());
		System.out.println(state2.getOutOfPlace() +" "+state2.getString());
		System.out.println(state3.getOutOfPlace() +" "+state3.getString());
		System.out.println(state4.getOutOfPlace() +" "+state4.getString());
		
		MyComparator comparator = new MyComparator(); //EightPuzzle implementation
		PriorityQueue<EightPuzzleState> statesToSearch = new PriorityQueue<EightPuzzleState>(comparator); //DEBUG
		statesToSearch.add(state4);
		statesToSearch.add(state3);
		statesToSearch.add(state2);
		statesToSearch.add(state1);
		
		while(true) {
			EightPuzzleState state = statesToSearch.poll(); //pop
			if(state == null) {
				break;
			}
			System.out.print(state.getString() + "<---");
		}
	}//SUCCESS
	public static void test2() { //quick test to test out of place, SUCCESS		
		EightPuzzleState state = new EightPuzzleState("103824765");
		System.out.println(state.getOutOfPlace());
	}//SUCCESS
	public static void test() { //quick test to test swap
		EightPuzzleState tester = new EightPuzzleState("103824765");
		for(EightPuzzleState state : tester.getChildrenStates()) {
			//System.out.println("STATE: " + state.getString());
			state.printStatus();
		}
	}
}
