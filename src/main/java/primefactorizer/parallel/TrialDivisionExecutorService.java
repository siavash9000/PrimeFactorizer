package primefactorizer.parallel;

import primefactorizer.IntegerFactorizerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrialDivisionExecutorService implements IntegerFactorizerInterface {

    private int numberOfCores = Runtime.getRuntime().availableProcessors();

    private class SubFactorizer implements Runnable {
        long toBeFactorized,start,end;
        boolean computationFinished;
        List<List<Long>> resultsContainer;
        public SubFactorizer(long toBeFactorized,long start,long end, List<List<Long>> resultsContainer){
            this.toBeFactorized=toBeFactorized;this.start=start;this.end=end;this.resultsContainer=resultsContainer;
            this.computationFinished=false;
        }

        @Override
        public void run() {
            resultsContainer.add(findFactorsInRange(toBeFactorized,start,end));
            computationFinished=true;
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
    }

    @Override
    public List<Long> factorize(long toBeFactorized) {
        if (toBeFactorized <= 0) {
            throw new IllegalArgumentException("Argument must be greater 0.");
        }
        if (toBeFactorized<1000) {
            ArrayList<List<Long>> partialResults = new ArrayList<List<Long>>();
            SubFactorizer solver = new SubFactorizer(toBeFactorized,2,toBeFactorized,partialResults);
            solver.run();
            return partialResults.get(0);
        }
        List<List<Long>> partialResults = solveSubProblems(toBeFactorized);
        return merge(partialResults,toBeFactorized);
    }

    private List<List<Long>> solveSubProblems(long toBeFactorized) {
        long upperbound = (long) Math.ceil(Math.sqrt(toBeFactorized));
        long intervalLength = upperbound / numberOfCores;
        List<List<Long>> partialResults = new ArrayList<List<Long>>();
        List<SubFactorizer> subProblems = new ArrayList<SubFactorizer>();
        subProblems.add(new SubFactorizer(toBeFactorized,2,intervalLength,partialResults));
        for(int i=1;i<numberOfCores-1;i++){
            subProblems.add(new SubFactorizer(toBeFactorized,intervalLength*i+1,intervalLength*(i+1),partialResults));
        }
        subProblems.add(new SubFactorizer(toBeFactorized,intervalLength*numberOfCores,upperbound,partialResults));
        ExecutorService executor = Executors.newCachedThreadPool();
        for (SubFactorizer subProblem: subProblems){
            executor.execute(subProblem);
        }
        while(!allSubproblemsSolved(subProblems));
        executor.shutdown();
        return partialResults;
    }

    private boolean allSubproblemsSolved(List<SubFactorizer> subProblems) {
        boolean finished = true;
        for(SubFactorizer subProblem: subProblems){
            finished=finished&&subProblem.computationFinished;
        }
        return finished;
    }
    private List<Long> merge(List<List<Long>> partialResults,long toBeFactorized) {
        ArrayList<Long> resultCandidates = new ArrayList<Long>();
        for (List<Long> partial: partialResults){
            for(Long value:partial) {
                resultCandidates.add(value);
            }
        }
        Collections.sort(resultCandidates);
        ArrayList<Long> result = new ArrayList<Long>();
        for(int i=0;i<resultCandidates.size();i++){
            boolean candidateRedundant = false;
            long productOfValue = resultCandidates.get(i);
            for (int j = 0; j < i; j++) {
                if (resultCandidates.get(i) % resultCandidates.get(j) == 0) {
                    if(resultCandidates.get(i)==resultCandidates.get(j)) {
                        productOfValue=productOfValue*resultCandidates.get(i);
                        if (toBeFactorized%productOfValue!=0){
                            candidateRedundant = true;
                        }
                    }
                    else {
                        candidateRedundant = true;
                    }
                }

            }
            if (!candidateRedundant) {
                result.add(resultCandidates.get(i));
            }
        }
        return result;
    }
}