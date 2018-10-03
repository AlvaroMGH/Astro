package astrolib.coordinates;

public class CircularCoordinateResults {

	private Double degrees;
	private Double degrees360;
	private Double degrees180;
	private Integer sexagesimalDegrees;
	private Integer sexagesimalMinutes;
	private Double sexagesimalSeconds;
	
	public Double getDegrees() {
		return degrees;
	}

	public Double getDegrees360() {
		return degrees360;
	}

	public Double getDegrees180() {
		return degrees180;
	}

	public Integer getSexagesimalDegrees() {
		return sexagesimalDegrees;
	}

	public Integer getSexagesimalMinutes() {
		return sexagesimalMinutes;
	}

	public Double getSexagesimalSeconds() {
		return sexagesimalSeconds;
	}


	
	public CircularCoordinateResults(
			Double degrees, 
			Double degrees360, 
			Double degrees180, 
			Integer sexagesimalDegrees,
			Integer sexagesimalMinutes, 
			Double   sexagesimalSeconds) {

		this.degrees = degrees;
		this.degrees360 = degrees360;
		this.degrees180 = degrees180;
		this.sexagesimalDegrees = sexagesimalDegrees;
		this.sexagesimalMinutes = sexagesimalMinutes;
		this.sexagesimalSeconds = sexagesimalSeconds;
	}

	@Override
	public String toString() {
		return "degrees360=" + degrees360 + ", degrees180="
				+ degrees180 + ", sexagesimalDegrees=" + sexagesimalDegrees + ", sexagesimalMinutes="
				+ sexagesimalMinutes + ", sexagesimalSeconds=" + sexagesimalSeconds;
	}
	
	public boolean isEquals(CircularCoordinate cc)
	{
		boolean equals = 
				(
						( degrees360.compareTo( cc.getDegrees360() ) == 0 )  &&
						( degrees180.compareTo( cc.getDegrees180() ) == 0 )  &&
						( sexagesimalDegrees == cc.getSexagesimalDegrees() ) &&
						( sexagesimalMinutes == cc.getSexagesimalMinutes() ) &&
						( sexagesimalSeconds.compareTo( cc.getSexagesimalSeconds() ) == 0 )
				);		
		
		return equals;
	}
	

}
