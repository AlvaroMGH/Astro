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
			Float degrees = expectedResult.getDegrees();
			CircularCoordinate circularCoordinate = new CircularCoordinate();
			circularCoordinate.setDegrees360(degrees);
			
			boolean correct = expectedResult.isEquals(circularCoordinate);
			if ( !correct )
			{
				System.out.printf("Error. Degrees= %f \n", expectedResult.getDegrees().floatValue() );
				System.out.printf( "Result expected: %s\n", expectedResult.toString() );
				System.out.printf( "Result obtained: %s\n", circularCoordinate.toString() );
				System.out.println();
			}
			assertTrue(correct);
		}
	}


	
	private void fillListOfExpectedResults()
	{
		//                                                     Degrees, Degrees360,  Degrees180, SexaDegrees, SexaMins, SexaSeconds
		expectedResults.add(new CircularCoordinateResults( (float)   0, (float)  0,  (float)  0,           0,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)  90, (float) 90,  (float) 90,          90,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float) -90, (float)270,  (float)-90,         270,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float) 180, (float)180,  (float)  0,         180,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)-180, (float)180,  (float)  0,         180,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float) 270, (float)270,  (float)-90,         270,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)-270, (float) 90,  (float) 90,          90,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float) 360, (float)  0,  (float)  0,           0,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)-360, (float)  0,  (float)  0,           0,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)   1, (float)  1,  (float)  1,           1,        0,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)  -1, (float)359,  (float) -1,         359,        0,    (float)0 ) );
		
		// Tests with decimal point
//      //                                                     Degrees,   Degrees360,   Degrees180, SexaDegrees, SexaMins, SexaSeconds
		expectedResults.add(new CircularCoordinateResults( (float) 0.1, (float)  0.1,  (float) 0.1,           0,        6,    (float)0 ) );
		expectedResults.add(new CircularCoordinateResults( (float)-0.1, (float)359.9,  (float)-0.1,         359,       54,    (float)0 ) );
	}

}
