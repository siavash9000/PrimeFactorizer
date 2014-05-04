package primefactors;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizer {
	public List<Integer> factorize(Integer toBeFactorized) {
		if (toBeFactorized <= 0){
			throw new IllegalArgumentException("Argument must be greater 0.");
		}
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		primeFactors.add(toBeFactorized);
		return primeFactors;
	}
}
