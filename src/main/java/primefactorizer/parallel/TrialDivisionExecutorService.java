package primefactorizer.parallel;

import primefactorizer.IntegerFactorizerInterface;

import java.util.*;
import java.util.concurrent.*;

class SubFactorizer implements Callable<List<Long>> {
    long toBeFactorized,start,end;
    public SubFactorizer(long toBeFactorized,long start,long end){
        this.toBeFactorized=toBeFactorized;
        this.start=start;
        this.end=end;
    }

    @Override
    public List<Long> call() {
        return findFactorsInRange(toBeFactorized,start,end);
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

public class TrialDivisionExecutorService implements IntegerFactorizerInterface {

    private int numberOfCores = Runtime.getRuntime().availableProcessors();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private List<SubFactorizer> subProblems = new ArrayList<SubFactorizer>();

    @Override
    public List<Long> factorize(long toBeFactorized) {
        if (toBeFactorized <= 0) {
            throw new IllegalArgumentException("Argument must be greater 0.");
        }
        if (toBeFactorized<1000) {
            SubFactorizer solver = new SubFactorizer(toBeFactorized,2,toBeFactorized);
            return solver.call();
        }
        List<List<Long>> partialResults = solveSubProblems(toBeFactorized);
        return merge(partialResults,toBeFactorized);
    }

    private List<List<Long>> solveSubProblems(long toBeFactorized) {
        long upperbound = (long) Math.ceil(Math.sqrt(toBeFactorized));
        long intervalLength = upperbound / numberOfCores;
        long start = 2;
        ArrayList<Future> solutions = new ArrayList<Future>();
        solutions.add(executorService.submit(new SubFactorizer(toBeFactorized, start, intervalLength)));
        for(int i=1;i<numberOfCores-1;i++){
            solutions.add(executorService.submit(new SubFactorizer(toBeFactorized, intervalLength*i+1, intervalLength*(i+1))));
        }
        solutions.add(executorService.submit(new SubFactorizer(toBeFactorized, intervalLength*numberOfCores, upperbound)));
        for (SubFactorizer subProblem: subProblems){
            solutions.add(executorService.submit(subProblem));
        }
        List<List<Long>> partialResults = Collections.synchronizedList(new ArrayList<List<Long>>());
        for (Future<List<Long>> solution: solutions){
            try {
                partialResults.add(solution.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        return partialResults;
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