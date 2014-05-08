package primefactors;

import java.util.List;
import java.util.Random;

public class PerformanceComparator {
	private IntegerFactorizer factorizer;
	
	public PerformanceComparator(IntegerFactorizer factorizer) {
		this.factorizer = factorizer;
	}
	public long getComputationLengthInMilliseconds(long toBeFactorized) {
		long start = System.currentTimeMillis();
		List<Long> result = factorizer.factorize(toBeFactorized);
		System.out.println(result);
		long length = System.currentTimeMillis() - start;
		System.out.println(length);
		return length;
	}
	
	public Double lengthOfHeavyComputation() {
		PerformanceComparator comparator = new PerformanceComparator(new TrialDivision());
		Random random = new Random();
		long toBeFactorized = 8223372036855449890L;
		System.out.println(toBeFactorized);
        Double computationLength =  new Double(comparator.getComputationLengthInMilliseconds(toBeFactorized))/1000;
		return computationLength;
	}
	
	public static void main(String[] args) {
		PerformanceComparator performanceComparator = new PerformanceComparator(new TrialDivision());
        Double computationLength = performanceComparator.lengthOfHeavyComputation();
		System.out.println(String.format("Factorization of took %f seconds.",computationLength));
	}
}
