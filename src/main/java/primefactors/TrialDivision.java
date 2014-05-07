package primefactors;

import java.util.ArrayList;
import java.util.List;

public class TrialDivision implements IntegerFactorizer {
	@Override
	public List<Long> factorize(Long toBeFactorized) {
		if (toBeFactorized <= 0) {
			throw new IllegalArgumentException("Argument must be greater 0.");
		}
		ArrayList<Long> primeFactors = new ArrayList<Long>();
		long factorCandidate=2;
		long upperBound = (long) Math.ceil(Math.sqrt(toBeFactorized));
		while(factorCandidate <=  upperBound){
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
