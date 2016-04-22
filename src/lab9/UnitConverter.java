package lab9;

public class UnitConverter {
	
	/**
	 * method convert is action to convert value to unit choose.
	 * @param amount of number from JTextField
	 * @param fromUnit : first Unit from JComboBox
	 * @param toUnit : second Unit choose form JComboBox
	 * @return
	 */
	public double convert( double amount , Unit fromUnit , Unit toUnit ){
		return (amount*fromUnit.getValue())/toUnit.getValue();
	}
	
	/**
	 * Return Unit
	 * @return return Array of Unit
	 */
	public Unit[] getUnits(){
		return Length.values();
	}
}
