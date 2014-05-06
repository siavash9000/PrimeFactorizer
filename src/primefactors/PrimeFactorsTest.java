package primefactors;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrimeFactorsTest {
	private PrimeFactorizer factorizer;
	@Before
	public void setup() {
		factorizer = new PrimeFactorizer();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidDataZeroTest() {
		Integer zero = 0;
		factorizer.factorize(zero);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidDataNegativeTest() {
		Random randomNumberGenerator = new Random();
		Integer randomNegativeNumber = -randomNumberGenerator.nextInt(100000);
		factorizer.factorize(randomNegativeNumber);
	}

	@Test
	public void one() {
		Assert.assertEquals(generateListWithElements(),factorizer.factorize(1));
	}
	
	@Test
	public void two() {
		Assert.assertEquals(generateListWithElements(2),factorizer.factorize(2));
	}
	
	@Test
	public void three() {
		Assert.assertEquals(generateListWithElements(3),factorizer.factorize(3));
	}
	
	@Test
	public void four() {
		Assert.assertEquals(generateListWithElements(2,2),factorizer.factorize(4));
	}
	
	@Test
	public void six() {
		Assert.assertEquals(generateListWithElements(2,3),factorizer.factorize(6));
	}
	
	@Test
	public void eight() {
		Assert.assertEquals(generateListWithElements(2,2,2),factorizer.factorize(8));
	}
	
	@Test
	public void nine() {
		Assert.assertEquals(generateListWithElements(3,3),factorizer.factorize(9));
	}
	
	@Test
	public void ninetynine() {
		Assert.assertEquals(generateListWithElements(3,3,11),factorizer.factorize(99));
	}
	
	@Test
	public void seventyone() {
		Assert.assertEquals(generateListWithElements(71),factorizer.factorize(71));
	}

	private ArrayList<Integer> generateListWithElements(int... elements) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int current: elements){
			list.add(current);
		}
		return list;
	}
	
	
}
