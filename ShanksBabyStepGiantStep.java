import java.util.ArrayList;
import java.math.BigInteger;

public class ShanksBabyStepGiantStep {
	
	private ExtendedEuclidianAlg euclid;

	public ShanksBabyStepGiantStep() {
		euclid = new ExtendedEuclidianAlg();
	}

	public ExtendedEuclidianAlg getEuclidObj() {
		return euclid;
	}

	// TODO break up into smaller methods
	public int findMatch(int number, int y, int p) {
		BigInteger valueToAdd, numInverse, currentY;
		boolean matchFound = false;
		int firstMatch = -1, secondMatch = -1,
			u = euclid.extEuclid(number, p)[1],
			n = 1 + (int)(Math.sqrt(p));
		ArrayList<BigInteger> numberExpons = new ArrayList<BigInteger>(n);

		numInverse = BigInteger.valueOf(u);
		numInverse = numInverse.pow(n);
		numInverse = numInverse.mod(BigInteger.valueOf(p));		

		for (int i = 0; i <= n; i++) {
			valueToAdd = BigInteger.valueOf(number);
			valueToAdd = valueToAdd.pow(i);
			valueToAdd = valueToAdd.mod(BigInteger.valueOf(p));
			numberExpons.add(valueToAdd);
		}

		for (int i = 0; (i <= n) && (!matchFound); i++) {
			currentY = numInverse.pow(i);
			currentY = currentY.mod(BigInteger.valueOf(p));
			currentY = currentY.multiply(BigInteger.valueOf(y));
			currentY = currentY.mod(BigInteger.valueOf(p));
			if (numberExpons.indexOf(currentY) != -1) {
				firstMatch = numberExpons.indexOf(currentY);
				secondMatch = i;
				matchFound = true;
			}
		}

		return firstMatch + (secondMatch * n);
	}

}