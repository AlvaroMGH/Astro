package astrolib.coordinates;


/**
 * @author alvaro.gonzalez
 * @version 1.0
 * @created 05-sep-2018 10:22:41
 */
public class CircularCoordinate {
	final Float f360 = new Float( 360 ); /** Used in several operations */
	final Float f180 = new Float( 180 ); /** Used in several operations */

	private Float degrees360; /** Degrees in interval [0-360[ */
	public Float getDegrees360(){ return degrees360; }

	private Float degrees180; /** Degrees in interval [0-180[ */
	public Float getDegrees180() { return degrees180;  } 


	public void finalize() throws Throwable {

	}


	// Sexagesimal System
	private int sexagesimalDegrees;
	private int sexagesimalMinutes;
	private Float sexagesimalSeconds;
	public int getSexagesimalDegrees()   { return sexagesimalDegrees; }
	public int getSexagesimalMinutes()   { return sexagesimalMinutes; }
	public Float getSexagesimalSeconds() { return sexagesimalSeconds; }
	
	/**
	 * Default constructor 
	 * Set to 0º
	 */
	public CircularCoordinate() {
		setDegrees360( (float)0 );
	}
	
	/**
	 * Copy constructor
	 * @param c
	 */
	public CircularCoordinate( CircularCoordinate c)
	{
		setDegrees360( c.degrees360 );
	}


	/**
	 * @brief Convert into interval [0-360[
	 * 
	 * Example: 365º is convert to 5º
	 * @param degrees
	 */	
	public void setDegrees360(Float degrees )
	{
		degrees360 = calculateDegrees360( degrees );
		calculateDegrees180();
		calculateSexagesimal(); // Save in format XXº YY' SS.SS''
	}

	

	@Override
	public String toString() {
		return "degrees360=" + degrees360 + ", degrees180=" + degrees180 + ", sexagesimalDegrees="
				+ sexagesimalDegrees + ", sexagesimalMinutes=" + sexagesimalMinutes + ", sexagesimalSeconds="
				+ sexagesimalSeconds;
	}


	/**
	 * Calculte the difference between current coordinate and the parameter coordinate
	 * @param cc subtrahend
	 * @return difference
	 */
	protected CircularCoordinate subtract( CircularCoordinate cc )
	{
		CircularCoordinate result = new CircularCoordinate( this );
		Float fDegrees360Aux = degrees360 - cc.getDegrees360();
		result.setDegrees360(fDegrees360Aux);
		return result;
	}
	
	
	/**
	 * Calculte the sum of current coordinate and the parameter coordinate
	 * @param cc summand
	 * @return summ
	 */
	protected CircularCoordinate add( CircularCoordinate cc )
	{
		CircularCoordinate result = new CircularCoordinate( this );
		Float fDegrees360Aux = degrees360 + cc.getDegrees360();
		result.setDegrees360(fDegrees360Aux);
		return result;
	}

	/**
	 * Normalizes the total of degrees to the interval [0-360[
	 * @param degrees
	 */
	protected Float calculateDegrees360( Float degrees )
	{
		Float auxDegrees = degrees;

		if ( Math.abs(degrees) >= 360 )
		{
			auxDegrees = degrees % 360;
		}
		
		auxDegrees += (float)0.0; // Avoid -0.0
		
		if ( auxDegrees.compareTo( (float)0 ) < 0 )
		{
			auxDegrees += 360;
		}
		return auxDegrees;
	}
	
	
	/**
	 * Once set this.degrees360 sets degrees180 to the interval ]-180 - 180]
	 */
	protected void calculateDegrees180() {
		if ( degrees360.compareTo( f180) > 0)
		{
			degrees180 = degrees360*10 - f360*10;
			degrees180 /= 10; // Avoid problems with decimal point
		}
		else if ( degrees360.compareTo( f180) == 0)
		{
			degrees180 = (float)0;
		}
		else
		{
			degrees180 =  degrees360;
		}
	}
	
	
	/**
	 * @brief  Saves in format XXº YY' SS.SS''
	 */
	private void calculateSexagesimal()
	{
		sexagesimalDegrees = (int) (degrees360.floatValue());
		
		// Multiply by 10 the factors and then divide by 10 the result avoid some problems in decimal point in java
		Float remainingDegrees = degrees360*10 - sexagesimalDegrees*10;
		remainingDegrees /= 10;
		
		sexagesimalMinutes = (int) (remainingDegrees * 60);
		
		// Multiply by 10 the factors and then divide by 10 the result avoid some problems in decimal point in java
		sexagesimalSeconds = ( 10*degrees360 - 10*sexagesimalDegrees -10* sexagesimalMinutes/(float)60)*3600;
		sexagesimalSeconds /= 10;
	}	
}