package primefactors;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrialDivisionTest {
	private IntegerFactorizer factorizer;
	@Before
	public void setup() {
		factorizer = new TrialDivision();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidDataZeroTest() {
		Long zero = 0L;
		factorizer.factorize(zero);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidDataNegativeTest() {
		Long randomNegativeNumber = -100000L;
		factorizer.factorize(randomNegativeNumber);
	}

	@Test
	public void one() {
		Assert.assertEquals(generateListWithElements(),factorizer.factorize(1L));
	}
	
	@Test
	public void two() {
		Assert.assertEquals(generateListWithElements(2),factorizer.factorize(2L));
	}
	
	@Test
	public void three() {
		Assert.assertEquals(generateListWithElements(3),factorizer.factorize(3L));
	}
	
	@Test
	public void four() {
		Assert.assertEquals(generateListWithElements(2,2),factorizer.factorize(4L));
	}
	
	@Test
	public void six() {
		Assert.assertEquals(generateListWithElements(2,3),factorizer.factorize(6L));
	}
	
	@Test
	public void eight() {
		Assert.assertEquals(generateListWithElements(2,2,2),factorizer.factorize(8L));
	}
	
	@Test
	public void nine() {
		Assert.assertEquals(generateListWithElements(3,3),factorizer.factorize(9L));
	}
	
	@Test
	public void ninetynine() {
		Assert.assertEquals(generateListWithElements(3,3,11),factorizer.factorize(99L));
	}
	
	@Test
	public void seventyone() {
		Assert.assertEquals(generateListWithElements(71),factorizer.factorize(71L));
	}

	private ArrayList<Long> generateListWithElements(long... elements) {
		ArrayList<Long> list = new ArrayList<Long>();
		for (long current: elements){
			list.add(current);
		}
		return list;
	}
	
	
}
