package astrolib.coordinates;


/**
 * @author alvaro.gonzalez
 * @version 1.0
 * @created 05-sep-2018 10:22:41
 */
public class CircularCoordinate {
	final Double CORRECTOR = 10.0; /** Factor for multipliying fators and the divide the result: avoid problems with Doubleing point in java ^*/
	final int PRECISION = 3; /** Number of decimals in the results */ 
	
	final Double f360 = new Double( 360 ); /** Used in several operations */
	final Double f180 = new Double( 180 ); /** Used in several operations */

	private Double degrees360; /** Degrees in interval [0-360[ */
	public Double getDegrees360(){ return degrees360; }

	private Double degrees180; /** Degrees in interval [0-180[ */
	public Double getDegrees180() { return degrees180;  } 


	public void finalize() throws Throwable {

	}


	// Sexagesimal System
	private int sexagesimalDegrees;
	private int sexagesimalMinutes;
	private Double sexagesimalSeconds;
	public int getSexagesimalDegrees()   { return sexagesimalDegrees; }
	public int getSexagesimalMinutes()   { return sexagesimalMinutes; }
	public Double getSexagesimalSeconds() { return sexagesimalSeconds; }
	
	/**
	 * Default constructor 
	 * Set to 0º
	 */
	public CircularCoordinate() {
		setDegrees360( 0.0 );
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
	public void setDegrees360(Double degrees )
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
		Double fDegrees360Aux = degrees360 - cc.getDegrees360();
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
		Double fDegrees360Aux = degrees360 + cc.getDegrees360();
		result.setDegrees360(fDegrees360Aux);
		return result;
	}

	/**
	 * Normalizes the total of degrees to the interval [0-360[
	 * @param degrees
	 */
	protected Double calculateDegrees360( Double degrees )
	{
		Double auxDegrees = degrees;

		if ( Math.abs(degrees) >= 360 )
		{
			auxDegrees = degrees % 360;
		}
		
		auxDegrees += (Double)0.0; // Avoid -0.0
		
		if ( auxDegrees.compareTo( 0.0 ) < 0 )
		{
			auxDegrees += 360;
		}
		
		int precision = (int) Math.pow(10, PRECISION);
		auxDegrees = Math.round(auxDegrees*precision)/(double)precision;
		
		return auxDegrees;
	}
	
	
	/**
	 * Once set this.degrees360 sets degrees180 to the interval ]-180 - 180]
	 */
	protected void calculateDegrees180() {
		if ( degrees360.compareTo( f180 ) > 0)
		{
			degrees180 = degrees360*CORRECTOR - f360*CORRECTOR;
			degrees180 /= CORRECTOR; // Avoid problems with decimal point
		}
		else
		{
			degrees180 =  degrees360;
		}
		
		int precision = (int) Math.pow(10, PRECISION);
		degrees180 = Math.round(degrees180*precision)/(double)precision;
	}
	
	
	/**
	 * @brief  Saves in format XXº YY' SS.SS''
	 */
	private void calculateSexagesimal()
	{
		sexagesimalDegrees = degrees360.intValue();
		
		// Multiply by a factors and then divide by 10 the result avoid some problems in decimal point in java
		Double remainingDegrees = degrees360*CORRECTOR - sexagesimalDegrees*CORRECTOR;
		remainingDegrees /= CORRECTOR;
		
		sexagesimalMinutes = (int) (remainingDegrees * 60);
		
		// Multiply by a factor the factors and then divide by the factor the result avoid some problems in decimal point in java	
		sexagesimalSeconds = ( CORRECTOR*degrees360 - CORRECTOR*sexagesimalDegrees - CORRECTOR*sexagesimalMinutes/60.0 )*3600.0;
		sexagesimalSeconds /= CORRECTOR;
		
		sexagesimalSeconds = Math.round(sexagesimalSeconds*100) / 100.0; 
		
		sexagesimalSeconds = sexagesimalSeconds % 60.0;
	}	
}