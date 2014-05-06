package primefactors;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizer {
	public List<Integer> factorize(Integer toBeFactorized) {
		if (toBeFactorized <= 0) {
			throw new IllegalArgumentException("Argument must be greater 0.");
		}
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		int factorCandidate=2;
		int upperBound = (int) Math.ceil(Math.sqrt(toBeFactorized));
		while(factorCandidate <=  upperBound){
			while(toBeFactorized % factorCandidate == 0) {
				primeFactors.add(factorCandidate);
				toBeFactorized /= factorCandidate;
			}
			factorCandidate++;
		}
		if(toBeFactorized>1){
			primeFactors.add(toBeFactorized);
		}
		return primeFactors;
	}
}
