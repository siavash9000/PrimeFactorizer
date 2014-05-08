package primefactors;

import java.util.List;

public class PerformanceComparator {
	private IntegerFactorizer factorizer;
	
	public PerformanceComparator(IntegerFactorizer factorizer) {
		this.factorizer = factorizer;
	}
	public long getComputationLengthInMilliseconds(long toBeFactorized) {
		long start = System.currentTimeMillis();
		factorizer.factorize(toBeFactorized);
		long length = System.currentTimeMillis() - start;
		return length;
	}
	
	public Double lengthOfHeavyComputation(long toBeFactorized) {
        Double computationLength =  new Double(getComputationLengthInMilliseconds(toBeFactorized))/1000;
		return computationLength;
	}

    public double getMeanOfRuns(int numberOfRuns, long toBeFactorized) {
        double mean=0;
        for (int i=0;i<numberOfRuns;i++){
            mean += lengthOfHeavyComputation(toBeFactorized);
        }
        mean /= numberOfRuns;
        return mean;
    }
	
	public static void main(String[] args) {
		PerformanceComparator performanceComparator = new PerformanceComparator(new TrialDivision());
        Double computationLength = performanceComparator.getMeanOfRuns(10, 82233720368554498L);
		System.out.println(String.format("Mean of %d runs: %f seconds.",10,computationLength));
	}
}
