import arc.*;

public class poker{
	public static void main(String[] args){
		Console con = new Console("Poker", 600, 600);
		int intCard[][];
		int intRand;
		int intCount;
		int intCount2;
		
		intCard = new int[52][3];
		
		// Before Sorting
		// Generates the 52 card each with a random number used for bubble sorting
		for(intCount = 0; intCount < 52; intCount++){
			intRand = (int)(Math.random() * 100 + 1);
			
			intCard[intCount][0] = (intCount+1)%13;
			if(intCard[intCount][0] == 0){
				intCard[intCount][0] = 13;
			}
			
			intCard[intCount][1] = (intCount+1)%4;
			if(intCard[intCount][1] == 0){
				intCard[intCount][1] = 4;
			}
			intCard[intCount][2] = intRand;
		}
		
		// con.println("Before Sorting");
		// for(intCount = 0; intCount < 52; intCount++){
			// con.println(intCard[intCount][0]+"-"+intCard[intCount][1]+"-"+intCard[intCount][2]);
		// }
		
		// After Sorting

		int intValueTemp;
		int intSuitTemp;
		int intRandTemp;
		
		// Shuffles the Cards by arranging them by value of the random number
		for(intCount2 = 0; intCount2 < 52-1; intCount2++){
			for(intCount = 0; intCount < 52-1; intCount++){
				if(intCard[intCount][2] > intCard[intCount+1][2]){
					intValueTemp = intCard[intCount][0];
					intCard[intCount][0] = intCard[intCount+1][0];
					intCard[intCount+1][0] = intValueTemp;
				
					intSuitTemp = intCard[intCount][1];
					intCard[intCount][1] = intCard[intCount+1][1];
					intCard[intCount+1][1] = intSuitTemp;
				
					intRandTemp = intCard[intCount][2];
					intCard[intCount][2] = intCard[intCount+1][2];
					intCard[intCount+1][2] = intRandTemp;
				}
			}
		}
		
		// con.println("\n\nAfter Sorting");
		// for(intCount = 0; intCount < 52; intCount++){
			// con.println(intCard[intCount][0]+"-"+intCard[intCount][1]+"-"+intCard[intCount][2]);
		// }
		
		int intHand[][];
		intHand = new int[5][2];
		
		// Picks the first 5 cards to add to the hand
		for(intCount = 0; intCount < 5; intCount++){
			intHand[intCount][0] = intCard[intCount][0];
			intHand[intCount][1] = intCard[intCount][1];
		}
		
		// Prints out the hand 
		con.println("Hand:");
		for(intCount = 0; intCount < 5; intCount++){
			con.println("Card "+(intCount+1)+": "+intHand[intCount][0]+"-"+intHand[intCount][1]);
		}
	}
}
