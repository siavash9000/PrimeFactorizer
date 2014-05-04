package primefactors;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizer {
	public List<Integer> factorize(Integer toBeFactorized) {
		if (toBeFactorized <= 0){
			throw new IllegalArgumentException("Argument must be greater 0.");
		}
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		if (toBeFactorized%2 == 0 && toBeFactorized/2>1) {
			primeFactors.add(2);
			primeFactors.add(toBeFactorized/2);
		}
		else 
			primeFactors.add(toBeFactorized);
		return primeFactors;
	}
}
