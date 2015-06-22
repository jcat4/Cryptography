import java.util.ArrayList;
import java.math.BigInteger;

public class ChineseRemainderThm {
	
	private ExtendedEuclidianAlg euclid; 
	private ArrayList <Integer> congruencesAL;
	private ArrayList <Integer> primeFactorsAL;
	private int number, y, p;

	public ChineseRemainderThm(int number, int y, int p) {
		congruencesAL = new ArrayList <Integer>();
		euclid = new ExtendedEuclidianAlg();
		this.number = number;
		this.y = y;
		this.p = p;
	}

	public int chinRemainThm(int num1, int mod1, int num2, int mod2) {
		int mulInverse, y;
		if (num2 >= num1) {
			mulInverse = euclid.extEuclid(mod1, mod2)[1];
			y = (mulInverse * (num2 - num1)) % mod2;
			return (num1 + mod1 * y);
		} else {
			mulInverse = euclid.extEuclid(mod2, mod1)[1];
			y = (mulInverse * (num1 - num2)) % mod1;
			return (num2 + mod2 * y);
		}
	}

	public int doBigExponentiation(int number, int exponent) {
		BigInteger bigNumber = BigInteger.valueOf(number);
		BigInteger bigExp = BigInteger.valueOf(exponent);
		BigInteger bigP = BigInteger.valueOf(p);
		bigNumber = bigNumber.modPow(bigExp, bigP);
		return Integer.parseInt(bigNumber.toString());
	}

}