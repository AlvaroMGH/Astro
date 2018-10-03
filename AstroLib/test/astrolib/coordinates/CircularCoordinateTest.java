package astrolib.coordinates;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class CircularCoordinateTest {
	ArrayList<CircularCoordinateResults> expectedResults = new ArrayList<CircularCoordinateResults>();

	@Test
	public void test()
	{
		fillListOfExpectedResults();

		
		for (int i=0; i<expectedResults.size(); i++ )
		{
			CircularCoordinateResults expectedResult = (CircularCoordinateResults) expectedResults.get(i);
			Double degrees = expectedResult.getDegrees();
			CircularCoordinate circularCoordinate = new CircularCoordinate();
				
			
			circularCoordinate.setDegrees360(degrees);
			

			boolean correct = expectedResult.isEquals(circularCoordinate);
			if ( !correct )
			{
				System.out.printf("Error. Prueba:%d.  Degrees= %f \n", i+1, expectedResult.getDegrees() );
				System.out.printf( "Result expected: %s\n", expectedResult.toString() );
				System.out.printf( "Result obtained: %s\n", circularCoordinate.toString() );
				System.out.println();
			}
			assertTrue(correct);
		}
	}


	
	/**
	 * Populate the array with result expected according to Tests.ods
	 */
	private void fillListOfExpectedResults()
	{
		//                                                Degrees, Degrees360,  Degrees180, SexaDegrees, SexaMins, SexaSeconds
		expectedResults.add(new CircularCoordinateResults(    0.0,   0.0,   0.0,   0,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(   90.0,  90.0,  90.0,  90,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(  -90.0, 270.0, -90.0, 270,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(  180.0, 180.0,  180.0, 180,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults( -180.0, 180.0,  180.0, 180,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(  270.0, 270.0, -90.0, 270,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults( -270.0,  90.0,  90.0,  90,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(  360.0,   0.0,   0.0,   0,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults( -360.0,   0.0,   0.0,   0,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(    1.0,   1.0,   1.0,   1,  0, 0.0 ) );
		expectedResults.add(new CircularCoordinateResults(   -1.0, 359.0,  -1.0, 359,  0, 0.0 ) );
		
		// Tests with decimal point                     Degrees, Degrees360,   Degrees180, SexaDegrees, SexaMins, SexaSeconds
		expectedResults.add(new CircularCoordinateResults(    0.1,     0.1,    0.1,    0,  6,  0.0 ) );
		expectedResults.add(new CircularCoordinateResults(   -0.1,   359.9,   -0.1,  359, 54,  0.0 ) );
		expectedResults.add(new CircularCoordinateResults(    0.01,    0.01,   0.01,   0,  0, 36.0 ) );
		expectedResults.add(new CircularCoordinateResults(   -0.01,  359.99,  -0.01, 359, 59, 24.0 ) );
		expectedResults.add(new CircularCoordinateResults(    0.001,   0.001,  0.001,  0,  0,  3.6 ) );
		expectedResults.add(new CircularCoordinateResults(    0.001,   0.001,  0.001,  0,  0,  3.6 ) );
		expectedResults.add(new CircularCoordinateResults(   -0.001, 359.999, -0.001,  359,  59,  56.4 ) );		
		expectedResults.add(new CircularCoordinateResults( 1000.0,    280.0, -80.0,  280,  0,  0.00 ) );
		expectedResults.add(new CircularCoordinateResults(-1000.0,     80.0,  80.0,   80,  0,  0.00 ) );
		expectedResults.add(new CircularCoordinateResults(  400.1,     40.1,  40.1,   40,  6,  0.00 ) );
	}

}
