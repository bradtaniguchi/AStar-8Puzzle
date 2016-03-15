/**
 * 
 * @author Bradley Taniguchi
 *
 */

public class Runner {
	public static void main(String arg[]) {
		test2();
		
		System.out.println("Starting Runner Method...");
		//String TestString1 = "012345678"; //impossible to win?
		String TestString1 = "103824765"; //one move from win
		String TestString2 = "123456780"; //need to test these two
		//String TestString3 = "087654321";
		//test();
		EightPuzzleGame mygame = new EightPuzzleGame();
		mygame.breadthSearch(TestString1, true); //false to keep prints off
		System.out.println("NEXT");
		mygame.breadthSearch(TestString2, true);
		
	}
	public static void test2() { //quick test to test outofplace
		EightPuzzleState state = new EightPuzzleState("103824765");
		
	}
	public static void test() { //quick test to test swap
		EightPuzzleState tester = new EightPuzzleState("103824765");
		for(EightPuzzleState state : tester.getChildrenStates()) {
			//System.out.println("STATE: " + state.getString());
			state.printStatus();
		}
	}
}
