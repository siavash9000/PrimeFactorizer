package primefactors;

import java.util.ArrayList;
import java.util.List;

public class PerformanceComparator {
	private List<IntegerFactorizer> factorizers;
	
	public PerformanceComparator() {
		this.factorizers = new ArrayList<IntegerFactorizer>();
	}

    public void addFactorizerToComparison(IntegerFactorizer factorizer){
        this.factorizers.add(factorizer);
    }

    public long getComputationLengthInMilliseconds(long toBeFactorized, IntegerFactorizer factorizer) {
		long start = System.currentTimeMillis();
		List<Long> result = factorizer.factorize(toBeFactorized);
		long length = System.currentTimeMillis() - start;
        System.out.println(result);
        return length;
	}
	
	public void compareFactorizersForInput(long toBeFactorized) {
        for (IntegerFactorizer current: factorizers) {
            Double computationLength = new Double(getComputationLengthInMilliseconds(toBeFactorized,current)) / 1000;
            System.out.println(String.format("Time for %s of run: %f seconds.", current.getClass().getName(), computationLength));
        }
	}

	
	public static void main(String[] args) {
		PerformanceComparator performanceComparator = new PerformanceComparator();
        performanceComparator.addFactorizerToComparison(new TrialDivision());
        performanceComparator.addFactorizerToComparison(new TrialDivisionExecutorService());
        performanceComparator.compareFactorizersForInput(8223372036855449890L);


    }
}
