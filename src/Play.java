import java.util.ArrayList;
import java.util.HashMap;


public class Play {

	/**
	 * @param args
	 */
	private int players;
	
	public Play(int players){
		Deck deck = new Deck();
		ArrayList<Hand> hands = new ArrayList<Hand>();
		for(int i = 0; i<players; i++){
			Hand playerHand = new Hand();
			for(int j = 0; j < 5; j++){
				playerHand.addCard(deck.draw());
			}
			hands.add(playerHand);
		}
		Hand winner = Hand.evaluate(hands);
		ArrayList<Integer> winnerCircle = new ArrayList<Integer>();
		for(int i = 0; i<players; i++){
			if(Hand.evaluate(winner).equals(Hand.evaluate(hands.get(i)))){
				winnerCircle.add(i);
			}
		}
		for(Integer winners: winnerCircle){
			System.out.println(winners);
		}
	}
	public static void main(String[] args) {
//		Card test1 = new Card(Suit.CLUBS, Rank.ACE);
//		Card test2 = new Card(Suit.CLUBS, Rank.TWO);
//		Card test3 = new Card(Suit.CLUBS, Rank.THREE);
//		Card test4 = new Card(Suit.CLUBS, Rank.FOUR);
//		Card test5 = new Card(Suit.CLUBS, Rank.FIVE);
//		
//		Hand test = new Hand();
//		test.addCard(test1);
//		test.addCard(test2);
//		test.addCard(test3);
//		test.addCard(test4);
//		test.addCard(test5);
//		
//		Score score = test.evaluate(test);
//		System.out.println(score);
		
		
		

	}

}
