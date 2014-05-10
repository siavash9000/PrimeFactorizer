package primefactorizer.parallel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import primefactorizer.IntegerFactorizerInterface;
import primefactorizer.sequential.TrialDivision;

import java.util.ArrayList;

public class TrialDivisionExecutorServiceTest {
	private IntegerFactorizerInterface executorServiceFactorizer;
    private IntegerFactorizerInterface trialDivisionFactorizer;

	@Before
	public void setup() {

        executorServiceFactorizer = new TrialDivisionExecutorService();
        trialDivisionFactorizer = new TrialDivision();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidDataZeroTest() {
		Long zero = 0L;
		executorServiceFactorizer.factorize(zero);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidDataNegativeTest() {
		Long randomNegativeNumber = -100000L;
		executorServiceFactorizer.factorize(randomNegativeNumber);
	}

	@Test
	public void one() {
		Assert.assertEquals(generateListWithElements(), executorServiceFactorizer.factorize(1L));
	}
	
	@Test
	public void two() {
		Assert.assertEquals(generateListWithElements(2), executorServiceFactorizer.factorize(2L));
	}
	
	@Test
	public void three() {
		Assert.assertEquals(generateListWithElements(3), executorServiceFactorizer.factorize(3L));
	}
	
	@Test
	public void four() {
		Assert.assertEquals(generateListWithElements(2, 2), executorServiceFactorizer.factorize(4L));
	}
	
	@Test
	public void six() {
		Assert.assertEquals(generateListWithElements(2, 3), executorServiceFactorizer.factorize(6L));
	}
	
	@Test
	public void eight() {
		Assert.assertEquals(generateListWithElements(2, 2, 2), executorServiceFactorizer.factorize(8L));
	}
	
	@Test
	public void nine() {
		Assert.assertEquals(generateListWithElements(3, 3), executorServiceFactorizer.factorize(9L));
	}
	
	@Test
	public void ninetynine() {
		Assert.assertEquals(generateListWithElements(3, 3, 11), executorServiceFactorizer.factorize(99L));
	}
	
	@Test
	public void seventyone() {
		Assert.assertEquals(generateListWithElements(71), executorServiceFactorizer.factorize(71L));
	}

    @Test
    public void checkAgainstBruteforceOne(){
        long testValue = Long.MAX_VALUE/1000;
        Assert.assertEquals(trialDivisionFactorizer.factorize(testValue),executorServiceFactorizer.factorize(testValue));
    }

    @Test
    public void checkAgainstBruteforceTwo(){
        long testValue = Long.MAX_VALUE/8297;
        Assert.assertEquals(trialDivisionFactorizer.factorize(testValue),executorServiceFactorizer.factorize(testValue));
    }

    @Test
    public void checkAgainstBruteforceThree(){
        long testValue = Long.MAX_VALUE/56897;
        Assert.assertEquals(trialDivisionFactorizer.factorize(testValue),executorServiceFactorizer.factorize(testValue));
    }

    @Test
    public void checkAgainstBruteforceFour(){
        for (int i=0;i<1000000;i+=1000) {
            long testValue = new Long(Integer.MAX_VALUE) * (23*1);
            Assert.assertEquals(trialDivisionFactorizer.factorize(testValue), executorServiceFactorizer.factorize(testValue));
        }
    }


	private ArrayList<Long> generateListWithElements(long... elements) {
		ArrayList<Long> list = new ArrayList<Long>();
		for (long current: elements){
			list.add(current);
		}
		return list;
	}
	
	
}
