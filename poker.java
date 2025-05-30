import arc.*;

public class poker{
	public static void main(String[] args){
		Console con = new Console("Poker", 1280, 720);
		int intCard[][];
		int intRand;
		int intCount;
		int intCount2;
		String strOption;
		String strName;
		
		intCard = new int[52][3];
		
		con.println("Play Game (type '1')");
		con.println("View Leaderboard (type '2')");
		con.println("Quit Option (type '3')");
		strOption = con.readLine();
		
		if(strOption.equals("1")){
			con.println("Enter your name:");
			strName = con.readLine();
			int intMoney;
			int intBet;
			intMoney = 1000;
			
			con.println("Current Money: $"+intMoney);
			con.println("What is your bet?");
			intBet = con.readInt();
			intMoney = intMoney - intBet;
			
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
			// for(intCount = 0; intCount < 10; intCount++){
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
		
			String strSwitch;
			int intNextCard;
			intNextCard = 0;
			
			for(intCount = 0; intCount < 5;intCount++){
				con.println("Do you want to switch Card "+(intCount+1)+"? (Type 'y' for yes and 'n' for no)");
				strSwitch = con.readLine();
			
				if(strSwitch.equalsIgnoreCase("y")){
					intHand[intCount][0] = intCard[intNextCard+5][0];
					intHand[intCount][1] = intCard[intNextCard+5][1];
					intNextCard = intNextCard + 1;
				}
			}
		
			con.println("New Hand:");
			for(intCount = 0; intCount < 5; intCount++){
				con.println("Card "+(intCount+1)+": "+intHand[intCount][0]+"-"+intHand[intCount][1]);
			} 
		}
	}
}
