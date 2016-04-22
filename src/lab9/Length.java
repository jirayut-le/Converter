package lab9;
/**
 * A definition of common units of length.
 */
public enum Length implements Unit{
	
	/**
	 * Define the members of the enumeration
	 * The attributes are :
	 * name = a string name for this unit,
	 * value = multiplier to convert to meters.
	 */
	METER( "Meter" , 1.0 ),
	CENTIMETER( "Centimeter" , 0.01),
	KILOMETER("Kilometer" , 1000),
	FOOT( "Foot" , 0.3048),
	MILE ("Mile" , 1609.344),
	INCH ("Inch" , 0.0254),
	YARD ("Yard" , 0.9144),
	MICRON ("Micron", 1 * Math.pow(10, -6)),
	WA ("Wa", 2);
	
	/** name of this unit */
	public final String name;
	
	/** multiplier to convert this unit to std unit */
	public final double value;
	
	/**
	 * constructor for enum members
	 * @param name
	 * @param value
	 */
	Length (String name , double value){
		this.name = name;
		this.value = value;
	}
	
	/**
	 * public properties of the enum members
	 */
	public double getValue () { return value; }
	public String toString (){ return name;}
}
