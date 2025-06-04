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
		String strSuit;
		String strValue;
		String strContinue;
		strContinue = "y";
		
		intCard = new int[52][3];
		
		con.println("Play Game (type '1')");
		con.println("View Leaderboard (type '2')");
		con.println("Quit Option (type '3')");
		strOption = con.readLine();
		
		if(strOption.equals("1")){
			con.println("Enter your name:");
			strName = con.readLine();
			double dblMoney;
			double dblBet;
			
			// Provides a starting amount of cash at $1000 but double the amount if the name is "statitan"
			if(strName.equals("statitan")){
				con.println("You entered the special name, start with $2000 instead of $1000.");
				dblMoney = 2000;
			}else{
				dblMoney = 1000;
			}
			
			while(strContinue.equalsIgnoreCase("y")){
				con.println("Current Money: $"+dblMoney);
				con.println("What is your bet?");
				dblBet = con.readDouble();
				while(dblBet > dblMoney){
					con.println("Ineligible Funds, Bet Again:");
					dblBet = con.readDouble();
				}
				dblMoney = dblMoney - dblBet;
			
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
		
			
				int intHand[][];
				intHand = new int[5][2];
		
				// Picks the first 5 cards to add to the hand
				for(intCount = 0; intCount < 5; intCount++){
					intHand[intCount][0] = intCard[intCount][0];
					intHand[intCount][1] = intCard[intCount][1];
				}
		
				// Prints out the hand 
				// Uses the number of this suits and value to print the corresponding suits and values
				con.println("Hand:");
				for(intCount = 0; intCount < 5; intCount++){
					// 1 = Ace, 11 = Jack, 12 = Queen, 13 = King
					if(intHand[intCount][0] == 1){
						strValue = "Ace";
					}else if(intHand[intCount][0] == 11){
						strValue = "Jack";
					}else if(intHand[intCount][0] == 12){
						strValue = "Queen";
					}else if(intHand[intCount][0] == 13){
						strValue = "King";
					}else{
						strValue = intHand[intCount][0] + "";
					}
					// 1 = Diamonds, 2 = Clubs, 3 = Hearts, 4 = Spades
					if(intHand[intCount][1] == 1){
						strSuit = "Diamonds";
					}else if(intHand[intCount][1] == 2){
						strSuit = "Clubs";
					}else if(intHand[intCount][1] == 3){
						strSuit = "Hearts";
					}else{
						strSuit = "Spades";
					}
					con.println("Card "+(intCount+1)+": "+strValue+"-"+strSuit);
				}
		
				String strSwitch;
				int intNextCard;
				intNextCard = 0;
				
				// Asks the user if they want to switch any of the 5 cards the hav
				for(intCount = 0; intCount < 5;intCount++){
					con.println("Do you want to switch Card "+(intCount+1)+"? (Type 'y' for yes and 'n' for no)");
					strSwitch = con.readLine();
					
					// Switches the chosen card with the next card at the top of the deck
					// In other words, the card with the next smallest random number
					if(strSwitch.equalsIgnoreCase("y")){
						intHand[intCount][0] = intCard[intNextCard+5][0];
						intHand[intCount][1] = intCard[intNextCard+5][1];
						intNextCard = intNextCard + 1;
					}
				}
				// Prints the new hand with the switched cards using the same rules as the previous hand
				con.println("New Hand:");
				for(intCount = 0; intCount < 5; intCount++){
					if(intHand[intCount][0] == 1){
						strValue = "Ace";
					}else if(intHand[intCount][0] == 11){
						strValue = "Jack";
					}else if(intHand[intCount][0] == 12){
						strValue = "Queen";
					}else if(intHand[intCount][0] == 13){
						strValue = "King";
					}else{
						strValue = intHand[intCount][0] + "";
					}
					if(intHand[intCount][1] == 1){
						strSuit = "Diamonds";
					}else if(intHand[intCount][1] == 2){
						strSuit = "Clubs";
					}else if(intHand[intCount][1] == 3){
						strSuit = "Hearts";
					}else{
						strSuit = "Spades";
					}
					con.println("Card "+(intCount+1)+": "+strValue+"-"+strSuit);
				}
				
				// Rearranges cards in order of value to make identifying matching cards and straights easier for the computer
				for(intCount2 = 0; intCount2 < 5-1; intCount2++){
					for(intCount = 0; intCount < 5-1; intCount++){
						if(intHand[intCount][0] > intHand[intCount+1][0]){
							intValueTemp = intHand[intCount][0];
							intHand[intCount][0] = intHand[intCount+1][0];
							intHand[intCount+1][0] = intValueTemp;
				
							intSuitTemp = intHand[intCount][1];
							intHand[intCount][1] = intHand[intCount+1][1];
							intHand[intCount+1][1] = intSuitTemp;
						}
					}
				}
			
				int intNextValue;
				intNextValue = intHand[0][0];
				
				int intSuit;
				intSuit = 0;
				int intStraight;
				intStraight = 1;
				int intPairs;
				intPairs = 0;
				int intThrees;
				intThrees = 0;
				int intFours;
				intFours = 0;
					
				// Identifies if all cards have the same suit or not
				for(intCount = 0; intCount < 5; intCount++){
					if(intHand[intCount][1] == intHand[0][1]){
						intSuit = intSuit + 1;
					}
				}
				
				// Identifies if all the cards are consecutive by value
				for(intCount = 1; intCount < 5; intCount++){
					intNextValue = intNextValue + 1;
					if(intHand[intCount][0] == intNextValue){
						intStraight = intStraight + 1;
					}
				}
				// Identifies adjacent pairs by value
				for(intCount = 0; intCount < 5-1; intCount++){
					if(intHand[intCount][0] == intHand[intCount+1][0]){
						intPairs = intPairs + 1;
					}
				}
				// Identifies adjacent trios by value
				for(intCount = 0; intCount < 5-2; intCount++){
					if(intHand[intCount][0] == intHand[intCount+2][0]){
						intThrees = intThrees + 1;
					}
				}
				// Identifies groups of four by value
				for(intCount = 0; intCount < 5-3; intCount++){
					if(intHand[intCount][0] == intHand[intCount+3][0]){
						intFours = intFours + 1;
					}
				}
				
				// Checks if all cards have the same suit
				if(intSuit == 5){
					// Checks whether the cards match a royal flush, straight flush, or flush 
					// Provides a multiplier on the bet and rewards the according amount
					if(intHand[0][0] == 1 && intHand[1][0] == 10 && intHand[2][0] == 11 && intHand[3][0] == 12 && intHand[4][0] == 13){
						con.println("Royal Flush");
						dblBet = dblBet * 800;
					}else if(intStraight == 5){
						con.println("Straight Flush");
						dblBet = dblBet * 50;
					}else{
						con.println("Flush");
						dblBet = dblBet * 6;
					}
				}else{
					// Determines a four of a kind, full house, three of a kind, two pair, and straight
					// Provides a multiplier on the bet and rewards the according amount
					if(intFours == 1){
						con.println("Four of a Kind");
						dblBet = dblBet * 25;
					}else if(intThrees == 1){
						if(intPairs == 3){
							con.println("Full House");
							dblBet = dblBet * 9;
						}else{
							con.println("Three of a Kind");
							dblBet = dblBet * 3;
						}
					}else if(intPairs == 2){
						con.println("Two Pair");
						dblBet = dblBet * 2;
					}else if(intStraight == 5){
						con.println("Straight");
						dblBet = dblBet * 4;
					}else{
						con.println("Loss");
						dblBet = 0;
					}
				}
				// Determines the amount of money left after the game
				dblMoney = dblMoney + dblBet;
				con.println("Remaining Cash: $"+dblMoney);
				
				// Determines if there is any cash left and if the user wants to continue
				// If the user continues, the code loops to the top
				if(dblMoney > 0){
					con.println("Do you wish to continue? ('y' for yes, 'n' for no)");
					strContinue = con.readLine();
				}else{
					con.println("No more money remaining.");
					strContinue = "n";
				}
			}
			
			// Prints the profile of the user to leaderboard.txt if they're done
			TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
			leaderboard.println(strName);
			leaderboard.println(dblMoney);
			leaderboard.close();
		}else if(strOption.equals("2")){
			TextInputFile scores = new TextInputFile("leaderboard.txt");
			double dblScore[];
			double dblScoreTemp;
			int intScoreCount;
			String strProfile[];
			String strProfileTemp;
			
			intScoreCount = 0;
			
			// Reads the amount of profiles in the file
			while(scores.eof() != true){
				strProfileTemp = scores.readLine();
				dblScoreTemp = scores.readDouble();
				intScoreCount++;
			}
			scores.close();
			
			// Creates arrays according to the amount of profiles
			strProfile = new String[intScoreCount];
			dblScore = new double[intScoreCount];
			scores = new TextInputFile("leaderboard.txt");
			
			// Reads the specific files
			for(intCount = 0; intCount < intScoreCount; intCount++){
				strProfile[intCount] = scores.readLine();
				dblScore[intCount] = scores.readDouble();
			}
			
			// Sorts the profiles in order from greatest to least amount of money
			for(intCount2 = 0; intCount2 < intScoreCount - 1; intCount2++){
				for(intCount = 0; intCount < intScoreCount - 1; intCount++){
					if(dblScore[intCount] < dblScore[intCount+1]){
						strProfileTemp = strProfile[intCount];
						strProfile[intCount] = strProfile[intCount+1];
						strProfile[intCount+1] = strProfileTemp;
						
						dblScoreTemp = dblScore[intCount];
						dblScore[intCount] = dblScore[intCount+1];
						dblScore[intCount+1] = dblScoreTemp;
					}
				}
			}
			
			// Prints the leaderboard
			for(intCount = 0; intCount < intScoreCount; intCount++){
				con.println((intCount+1)+": "+strProfile[intCount]+" - $"+dblScore[intCount]);
			}
		}else if(strOption.equals("3")){
			// Quits the program
			System.exit(0);
		}else if(strOption.equals("4")){
			// Hidden option that prints a joke
			con.println("When a poker player shares their secrets,");
			con.sleep(1500);
			con.println("it's a full-house of revelations.");
		}
	}
}
