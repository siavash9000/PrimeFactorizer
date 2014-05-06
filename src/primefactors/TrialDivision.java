package primefactors;

import java.util.ArrayList;
import java.util.List;

public class TrialDivision implements IntegerFactorizer {
	/* (non-Javadoc)
	 * @see primefactors.IntegerFactorizer#factorize(java.lang.Integer)
	 */
	@Override
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
		//toBeFactorized must be prime now or 1
		if(toBeFactorized>1){
			primeFactors.add(toBeFactorized);
		}
		return primeFactors;
	}
}
