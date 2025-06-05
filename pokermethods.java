import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class pokermethods{
	// Prints out the user's hand and substitutes certain numbers for a specific value or suit
	// Value: 1 = Ace, 11 = Jack, 12 = Queen, 13 = King
	// Suit: 1 = Diamonds, 2 = Clubs, 3 = Hearts, 4 = Spades
	public static void handprint(int intHand[][], Console con){
		int intCount;
		String strValue;
		String strSuit;
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
			con.sleep(500);				
		}
	}
	
	// Determines the type of poker hand the user has and provides a reward based on the bet
	public static double pokerhands(int intHand[][], int intSuits, int intStraight, int intPairs, int intThrees, int intFours, double dblBet, Console con){
		// Checks if all cards have the same suit
		if(intSuits == 5){
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
			}else if(intHand[0][0] >= 11 || intHand[1][0] >= 11 || intHand[2][0] >= 11 || intHand[3][0] >= 11 || intHand[4][0] >= 11){
				con.println("Jacks or Better");
			}else{
				con.println("Loss");
				dblBet = 0;
			}
		}
		return dblBet;
	}
}
