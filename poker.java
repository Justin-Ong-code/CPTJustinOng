import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class poker{
	public static void main(String[] args){
		Console con = new Console("Poker", 1280, 720);
		BufferedImage imgLogo = con.loadImage("pokerlogo.jpg");
		int intCard[][];
		int intRand;
		int intCount;
		int intCount2;
		int intGamesPlayed;
		int intY;
		int intOption;
		String strName;
		String strContinue;
		
		intGamesPlayed = 0;
		intY = 0;
		strContinue = "y";
		
		// Array for deck
		intCard = new int[52][3];
		
		// Animation where logo slides down
		while(intY < 720){
			con.setDrawColor(Color.BLACK);
			con.fillRect(0, 0, 1280, 720);
			con.drawImage(imgLogo, 320, intY);
			intY = intY + 2;
			
			con.repaint();
			con.sleep(10);
		}

		// Provides the user with options
		con.println("(p)lay Game");
		con.println("(v)iew Leaderboard");
		con.println("(q)uit Option");
		intOption = con.getKey();
		con.clear();
		
		if(intOption == 80){
			// Asks for name
			con.println("Enter your name:");
			strName = con.readLine();
			double dblMoney;
			double dblBet;
			
			// Provides a starting amount of cash at $1000 but double the amount if the name is "statitan"
			if(strName.equals("statitan")){
				con.println("You entered the special name, start with $2000 instead of $1000.");
				con.sleep(2500);
				dblMoney = 2000;
			}else{
				dblMoney = 1000;
			}
			con.clear();
			
			while(strContinue.equalsIgnoreCase("y")){
				// Asks for bet, making sure it isn't greater than the current amount
				con.println("Current Money: $"+dblMoney);
				con.sleep(500);
				con.println("What is your bet?");
				dblBet = con.readDouble();
				while(dblBet > dblMoney){
					con.clear();
					con.println("Current Money: $"+dblMoney);
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
				con.clear();
	
				// After Sorting
				int intValueTemp;
				int intSuitTemp;
				int intRandTemp;
		
				// Shuffles the Cards by arranging them by value of the random number
				con.println("Current Money: $"+dblMoney);
				con.println("Bet: $"+dblBet);
				con.println("Shuffling Hand...");
				con.sleep(4000);
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
				
				// Array for player's hand
				int intHand[][];
				intHand = new int[5][2];
		
				// Picks the first 5 cards to add to the hand
				for(intCount = 0; intCount < 5; intCount++){
					intHand[intCount][0] = intCard[intCount][0];
					intHand[intCount][1] = intCard[intCount][1];
				}
		
				// Prints out the hand 
				// Uses the number of this suits and value to print the corresponding suits and values
				con.clear();
				con.println("Current Money: $"+dblMoney);
				con.println("Bet: $"+dblBet);
				con.sleep(1000);
				con.println("Hand:");
				con.sleep(1000);
				pokermethods.handprint(intHand, con);
		
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
				con.clear();
				// Prints the new hand with the switched cards using the same rules as the previous hand
				con.println("Current Money: $"+dblMoney);
				con.println("Bet: $"+dblBet);
				con.sleep(1000);
				con.println("New Hand:");
				con.sleep(1000);
				pokermethods.handprint(intHand, con);
				
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
				
				// Variables for determining various poker hands
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
				
				// Determines the type of poker hand the user has with a returning bet
				con.sleep(1500);
				dblBet = pokermethods.pokerhands(intHand, intSuit, intStraight, intPairs, intThrees, intFours, dblBet, con);
				con.println("+$"+dblBet);
				con.sleep(3000);
				
				// Determines the amount of money left after the game
				con.clear();
				dblMoney = dblMoney + dblBet;
				con.println("Current Money: $"+dblMoney);
				
				// Keeps track of the amount of games played
				intGamesPlayed = intGamesPlayed + 1;
				
				// Determines if there is any cash left and if the user wants to continue
				// If the user continues, the code loops to the top
				if(dblMoney > 0){
					con.println("Do you wish to continue? ('y' for yes, 'n' for no)");
					strContinue = con.readLine();
				}else{
					con.println("No more money remaining.");
					strContinue = "n";
				}
				con.clear();
			}
			
			// Shows the final results
			con.println("Final Results:");
			con.sleep(750);
			con.println("Name: "+strName);
			con.sleep(750);
			con.print("Games Played: ");
			con.sleep(750);
			con.println(intGamesPlayed);
			con.sleep(750);
			con.print("Final Score: ");
			con.sleep(750);
			con.println("$"+dblMoney);
			
			// Prints the profile of the user to leaderboard.txt if they're done
			TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
			leaderboard.println(strName);
			leaderboard.println(dblMoney);
			leaderboard.close();
		}else if(intOption == 86){
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
			con.println("Leaderboard:");
			con.sleep(1000);
			for(intCount = 0; intCount < intScoreCount; intCount++){
				con.println((intCount+1)+": "+strProfile[intCount]+" - $"+dblScore[intCount]);
				con.sleep(500);
			}
		}else if(intOption == 81){
			// Quits the program
			con.closeConsole();
		}else if(intOption == 83){
			// Secret option that prints a joke
			con.println("When a poker player shares their secrets,");
			con.sleep(1500);
			con.println("it's a full-house of revelations.");
		}
	}	
}
