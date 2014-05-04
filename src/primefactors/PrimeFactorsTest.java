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

	@Test
	public void smallestPrimeNumberTest() {
		Integer smallestPrimeNUmber = 2;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(smallestPrimeNUmber);
		Assert.assertEquals(expected,factorizer.factorize(smallestPrimeNUmber));
	}
	
	@Test
	public void oneTest() {
		Integer one = 1;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(one);
		Assert.assertEquals(expected,factorizer.factorize(one));
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
}
