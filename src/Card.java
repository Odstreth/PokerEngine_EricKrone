import java.util.ArrayList;
import java.util.Arrays;


public class Card implements Comparable<Card> {

	/**
	 * @param args
	 */
	
	private Rank rank;
	private Suit suit;
	private boolean isWild;
	
	public final static ArrayList<Suit> suitList =  new ArrayList<Suit>(Arrays.asList(Suit.HEARTS, Suit.SPADES, Suit.CLUBS, Suit.DIAMONDS));
	
	public final static ArrayList<Rank> rankList = new ArrayList<Rank>(Arrays.asList(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE));
	
	public Card(Suit suit, Rank rank){
		//accounting for multiple value/suit cards such as aces and wilds
		this.suit = suit;
		this.rank = rank;
		this.isWild = false;
	}
	
	public Rank getRank(){
			return this.rank;
	}
	
	public Suit getSuit(){
			return this.suit;
	}
	
	public void setWild(){
		this.isWild = true;
	}
	
	public boolean isWild(){
		return this.isWild;
	}
	@Override
	public int compareTo(Card otherCard) {
		return this.rank.value() - otherCard.rank.value();
	}
	

}
