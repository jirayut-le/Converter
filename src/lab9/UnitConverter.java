package lab9;

public class UnitConverter {
	public double convert( double amount , Unit fromUnit , Unit toUnit ){
		return (amount*fromUnit.getValue())/toUnit.getValue();
	}
	
	public Unit[] getUnits(){
		return Length.values();
	}
}
