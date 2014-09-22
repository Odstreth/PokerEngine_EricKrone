import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	/**
	 * @param args
	 */
	private ArrayList<Card> deck;
	
	public Deck(){
		for ( int i = 0; i<Card.suitList.size(); i++){
			for (int j = 0; j<Card.rankList.size(); j++){
				Card card = new Card(Card.suitList.get(i), Card.rankList.get(i));
				this.deck.add(card);
			}
		}
		Collections.shuffle(deck);
			
	}
	
	public Card draw(){
		Card drawnCard = this.deck.get(0);
		this.deck.remove(0);
		return drawnCard;
	}
	
	public int remaining(){
		return this.deck.size();
	}
}
