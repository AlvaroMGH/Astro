package astrolib.coordinates;


/**
 * @author alvaro.gonzalez
 * @version 1.0
 * @created 05-sep-2018 10:22:41
 */
public class CircularCoordinate {

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
	 * Set to 0�
	 */
	public CircularCoordinate() {
		setDegrees360( new Float("0") );
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
	 * Example: 365� is convert to 5�
	 * @param degrees
	 */	
	public void setDegrees360(Float degrees )
	{
		this.degrees360 = calculateDegrees360( degrees );
		calculateDegrees180();
		calculateSexagesimal(); // Save in format XX� YY' SS.SS''
	}
	
	

	/**
	 * Calculte the difference between current coordinate and the parameter coordinate
	 * @param cc subtrahend
	 * @return difference
	 */
	protected CircularCoordinate subtract( CircularCoordinate cc )
	{
		CircularCoordinate result = new CircularCoordinate( this );
		Float fDegrees360Aux = this.degrees360 - cc.getDegrees360();
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
		Float fDegrees360Aux = this.degrees360 + cc.getDegrees360();
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
			auxDegrees = (degrees*10) % 360;
			auxDegrees /= 10;
		}
		
		if ( auxDegrees < 0) 
		{
			auxDegrees += 360;
		}
		return auxDegrees;
	}
	
	
	/**
	 * Once set this.degrees360 sets degrees180 to the interval ]-180 - 180]
	 */
	protected void calculateDegrees180() {
		if ( this.degrees360 > 180)
		{
			this.degrees180 = this.degrees360 - 360;
		}
		else
		{
			this.degrees180 =  this.degrees360;
		}
	}
	
	
	/**
	 * @brief  Saves in format XX� YY' SS.SS''
	 */
	private void calculateSexagesimal()
	{
		this.sexagesimalDegrees = (int) (this.degrees360.floatValue());
		Float remainingDegrees = this.degrees360 - this.sexagesimalDegrees;
		this.sexagesimalMinutes = (int) (remainingDegrees * 60);
		this.sexagesimalSeconds = this.degrees360 - this.sexagesimalDegrees - this.sexagesimalMinutes/60;		
	}	
	
	



}