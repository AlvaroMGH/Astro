package astrolib.coordinates;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircularCoordinateTest {


	@Test
	public void test1()
	{
		testSetDegrees( 
				(float)120, // Degrees to set  
				(float)120, // [0-360[ expected degrees 
				(float)120, // [0-180[ expected degrees
				120,        // Expected sexagesimal degrees
				0,          // Expected sexagesimal minutes
				(float)0    // Expected sexagesimal seconds
				);
	}
	
	@Test
	public void test2()
	{
		testSetDegrees( 
				(float)400.1, // Degrees to set  
				(float)40.1, // [0-360[ expected degrees 
				(float)40.1, // [0-180[ expected degrees
				40,        // Expected sexagesimal degrees
				6,          // Expected sexagesimal minutes
				(float)0    // Expected sexagesimal seconds
				);
	}
	
	private void testSetDegrees( 
			float degrees360, 
			float expectedDegrees360,
			float expectedDegrees180,
			int expectedSexagesimalDegrees,
			int expectedSexagesimalMinutes, 
			float expectedSexagesimalSeconds )
	{
		CircularCoordinate circularCoordinate = new CircularCoordinate();
		circularCoordinate.setDegrees360(degrees360);
		
		System.out.printf("%f %f",  circularCoordinate.getDegrees360(), expectedDegrees360 );
		
		// assertTrue( circularCoordinate.getDegrees360() == expectedDegrees360 );
		assertTrue( circularCoordinate.getDegrees360().compareTo( expectedDegrees360 ) == 0 );
		assertTrue( circularCoordinate.getDegrees180() == expectedDegrees180 );
		assertTrue( circularCoordinate.getSexagesimalDegrees() == expectedSexagesimalDegrees );
		assertTrue( circularCoordinate.getSexagesimalMinutes() == expectedSexagesimalMinutes );
		assertTrue( circularCoordinate.getSexagesimalSeconds() == expectedSexagesimalSeconds );
		
		
	}

}
