
public class Score implements Comparable<Score>{

	/**
	 * @param args
	 */
	private HandStrength handStrength;
	private Rank strength;
	private Rank kicker;

	public Score(HandStrength hand, Rank strength, Rank kicker){
		this.handStrength = hand;
		this.strength = strength;
		this.kicker = kicker;
	}
	
	public Score(HandStrength hand){
		this.handStrength = hand;
		this.strength = null;
		this.kicker = null;
	}
	
	public HandStrength getHandStrength(){
		return this.handStrength;
	}
	
	public Rank getStrength(){
		return this.strength;
	}
	
	public Rank getKicker(){
		return this.kicker;
	}
	
	@Override
	public int compareTo(Score otherScore){
		int compare = this.handStrength.value() - otherScore.handStrength.value();
		if (compare == 0){
			int strengthCompare = this.strength.value() - otherScore.strength.value();
			if (strengthCompare == 0){
				int kickerCompare = this.kicker.value() - otherScore.kicker.value();
				return kickerCompare;
			}
			else{
				return strengthCompare;
			}
		}
		else{
			return compare;
		}
	}
	
	@Override
	public String toString(){
		String string = this.handStrength + ", " + this.strength + ", "+ this.kicker;
		return string;
	}
	
	public boolean equals(Score otherScore){
		int compare = this.handStrength.value() - otherScore.handStrength.value();
		if (compare == 0){
			int strengthCompare = this.strength.value() - otherScore.strength.value();
			if (strengthCompare == 0){
				if( this.kicker.value() - otherScore.kicker.value() == 0){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
	}
}
