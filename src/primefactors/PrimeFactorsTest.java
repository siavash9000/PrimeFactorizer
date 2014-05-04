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
		Integer one = 1;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(one);
		Assert.assertEquals(expected,factorizer.factorize(one));
	}
	
	@Test
	public void two() {
		Integer two = 2;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(two);
		Assert.assertEquals(expected,factorizer.factorize(two));
	}
	
	@Test
	public void three() {
		Integer three = 3;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(three);
		Assert.assertEquals(expected,factorizer.factorize(three));
	}
	
	@Test
	public void four() {
		Integer four = 4;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(2);
		expected.add(2);
		Assert.assertEquals(expected,factorizer.factorize(four));
	}

}
