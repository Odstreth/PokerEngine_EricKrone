import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Hand implements Comparable<Hand>{

	/**
	 * @param args
	 */

	private ArrayList<Card> hand;


	public Hand() {
		this.hand = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public static Score evaluate(Hand eval) {
		ArrayList<Score> score = new ArrayList<Score>();
		HashMap<Rank, Integer> cardMap = numberOfPairs(eval);
		Collections.sort(eval.getHand());
		if (isFlush(eval)) {
			if (isStraight(eval)) {
				//Handle Royal Flush
				Rank rank = eval.getHand().get(eval.getHand().size() - 2).getRank();
				if (rank == Rank.KING) {
					Score royalFlush = new Score(HandStrength.ROYALFLUSH);
					score.add(royalFlush);
					
				} 
				//Handle Straight Flush
				else {

					Score straightFlush = new Score(HandStrength.STRAIGHTFLUSH,rank, null);
					score.add(straightFlush);
				}
			}

			else {
				//Handle Regular Flush
				Score flush = new Score(HandStrength.FLUSH, eval.getHand().get(eval.getHand().size() - 1).getRank(), null);
				score.add(flush);
			}
		}
		if (isStraight(eval)) {

			if (eval.getHand().get(0).getRank() == Rank.TWO
					&& eval.getHand().get(4).getRank() == Rank.ACE) {
				Score straight = new Score(HandStrength.STRAIGHT, eval.getHand().get(3).getRank(), null);
				score.add(straight);
			} else {
				Score straight = new Score(HandStrength.STRAIGHT, eval.getHand().get(eval.getHand().size() - 1).getRank(), null);
				score.add(straight);

			}
		}
		if (cardMap.containsValue(4)) {
			//Handle Four of a Kind
			Rank fours = getKey(4, cardMap);
			Rank kicker = getKey(1, cardMap);
			Score fourOfAKind = new Score(HandStrength.FOUROFAKIND, fours, kicker);
			score.add(fourOfAKind);

		}
		if (cardMap.containsValue(3) && cardMap.containsValue(2)) {
			//Handle Full House
			Rank threes = getKey(3, cardMap);
			Rank twos = getKey(2, cardMap);
			Score fullHouse = new Score(HandStrength.FULLHOUSE, threes, twos);
			score.add(fullHouse);

		}
		if (cardMap.containsValue(3)) {
			//Handle Three of a King
			Rank threes = getKey(3, cardMap);
			Rank kicker = null;
			if (threes.value() != eval.getHand().get(eval.getHand().size() - 1).getRank().value()) {
				kicker = eval.getHand().get(eval.getHand().size() - 1).getRank();
			} 
			else {
				kicker = eval.getHand().get(1).getRank();
			}
			Score threeOfAKind = new Score(HandStrength.THREEOFAKIND, threes, kicker);
			score.add(threeOfAKind);
		}
		if (cardMap.containsValue(2)) {
			//Handle Pairs
			//Look for two Pair first. If failed, do one pair
			HashMap<Rank, Integer> clone = new HashMap<Rank, Integer>();
			clone = (HashMap<Rank, Integer>) cardMap.clone();
			//Remove the first pair and look for a set pair
			Rank store = getKey(2, cardMap);
			clone.remove(store);
			if (clone.containsValue(2)) {

				Rank store2 = getKey(2, clone);
				if (store.value() > store2.value()) {
					Score twoPair = new Score(HandStrength.TWOPAIR, store, store2);
					score.add(twoPair);
				} 
				else {
					Score twoPair = new Score(HandStrength.TWOPAIR, store2, store);
					score.add(twoPair);
				}
			} 
			else {

				Score onePair = new Score(HandStrength.ONEPAIR, store,null);
				score.add(onePair);
			}
		} 
		else {
			//Handle High Card
			Score highCard = new Score(HandStrength.HIGHCARD, eval.getHand().get(eval.getHand().size() - 1).getRank(), eval.getHand().get(eval.getHand().size() - 2).getRank());
			score.add(highCard);

		}
		Collections.sort(score, Collections.reverseOrder());
		return score.get(0);

	}
	

	public static Hand evaluate(ArrayList<Hand> hands){
		//Evaluate multiple hands by using our overridden compareTo and Collections
		Collections.sort(hands, Collections.reverseOrder());
		return hands.get(0);
	}
	
	@Override
	//Override compareTo so that we can easily use Collections
	public int compareTo(Hand otherHand){
		Score thisScore = Hand.evaluate(this);
		Score otherScore = Hand.evaluate(otherHand);
		return thisScore.compareTo(otherScore);
		
	}
	

	public static boolean isFlush(Hand hand) {
		//Check all suits for flush
		boolean flush = true;
		Suit suit = hand.getHand().get(0).getSuit();
		for (Card i : hand.getHand()) {
			if (i.getSuit() != suit) {
				flush = false;
				return flush;
			}
		}
		return flush;
	}

	public static boolean isStraight(Hand hand) {
		//Check for straight and account for straights where Ace is low
		boolean straight = true;
		for (int i = 0; i < hand.getHand().size() - 1; i++) {
			if (hand.getHand().get(i).getRank().value() + 1 != hand.getHand().get(i + 1).getRank().value()) {
				if (!(hand.getHand().get(i).getRank() == Rank.FIVE && hand.getHand().get(i + 1).getRank() == Rank.ACE)) {
					straight = false;
					return straight;
				}
			}
		}
		return straight;
	}

	public static HashMap<Rank, Integer> numberOfPairs(Hand hand) {
		//Build a map that shows how many pairs we have and what rank they are
		System.out.println("CHECKING PAIRS");
		HashMap<Rank, Integer> cardMap = new HashMap<Rank, Integer>();
		for (Card i : hand.getHand()) {
			if (cardMap.containsKey(i.getRank())) {
				cardMap.put(i.getRank(), cardMap.get(i.getRank()) + 1);
			} else {
				cardMap.put(i.getRank(), 1);
			}
		}
		return cardMap;
	}

	public static Rank getKey(int value, HashMap<Rank, Integer> map) {
		//Get the rank from the map based on how many times the rank appears
		for (Entry<Rank, Integer> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
