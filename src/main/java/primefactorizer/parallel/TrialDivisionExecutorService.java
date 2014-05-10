package primefactorizer.parallel;

import primefactorizer.IntegerFactorizerInterface;

import java.util.ArrayList;
import java.util.List;

public class TrialDivisionExecutorService implements IntegerFactorizerInterface {

    private int numberOfCores = Runtime.getRuntime().availableProcessors();

    @Override
    public List<Long> factorize(long toBeFactorized) {
        if (toBeFactorized <= 0) {
            throw new IllegalArgumentException("Argument must be greater 0.");
        }
        if (toBeFactorized<1000) {
            return findFactorsInRange(toBeFactorized,2,toBeFactorized);
        }
        ArrayList<List<Long>> partialResults = solveSubProblems(toBeFactorized);
        return merge(partialResults);
    }

    private ArrayList<List<Long>> solveSubProblems(long toBeFactorized) {
        long upperbound = (long) Math.ceil(Math.sqrt(toBeFactorized));
        long intervalLength = upperbound / numberOfCores;
        ArrayList<List<Long>> partialResults = new ArrayList<List<Long>>();
        partialResults.add(findFactorsInRange(toBeFactorized,2,intervalLength));
        for(int i=1;i<numberOfCores-1;i++){
            partialResults.add(findFactorsInRange(toBeFactorized,intervalLength*i+1,intervalLength*(i+1)));
        }
        partialResults.add(findFactorsInRange(toBeFactorized,intervalLength*numberOfCores,upperbound));
        return partialResults;
    }

    private List<Long> findFactorsInRange(long toBeFactorized, long start, long end) {
        ArrayList<Long> primeFactors = new ArrayList<Long>();
        long factorCandidate=start;
        while(factorCandidate <=  end){
            while(toBeFactorized % factorCandidate == 0) {
                primeFactors.add(factorCandidate);
                toBeFactorized /= factorCandidate;
            }
            factorCandidate++;
        }
        //toBeFactorized must be prime now or 1
        if(toBeFactorized>1){
            primeFactors.add(toBeFactorized);
        }
        return primeFactors;
    }

    private List<Long> merge(List<List<Long>> partialResults) {
        ArrayList<Long> result = new ArrayList<Long>();
        for(int i=0;i<partialResults.size();i++){
            for (Long candidate: partialResults.get(i)) {
                boolean candidateRedundant = false;
                for (int j = 0; j < i; j++) {
                    for (Long alreadyVerifiedElement: partialResults.get(j)) {
                        if (candidate % alreadyVerifiedElement == 0) {
                            candidateRedundant = true;
                        }
                    }
                }
                if (!candidateRedundant) {
                    result.add(candidate);
                }
            }
        }
        return result;
    }
}