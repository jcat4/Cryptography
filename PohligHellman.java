import java.util.ArrayList;

public class PohligHellman {

	private int number, x, p, y;
	private ArrayList <Integer> primeFactorsAL, congruencesAL;
	private ShanksBabyStepGiantStep shanks;
	ChineseRemainderThm chinRemainThmObj;

	public PohligHellman(int number, int y, int p) {
		chinRemainThmObj = new ChineseRemainderThm(number, y, p);
		shanks = new ShanksBabyStepGiantStep();
		primeFactorsAL = new ArrayList <Integer>();
		congruencesAL = new ArrayList <Integer>();
		this.number = number;
		this.y = y;
		this.p = p;
	}

	public void findCongruences() {
		int currCongruence, currFactor, firstInput, secondInput;
		for (int i = 0; i < primeFactorsAL.size(); i++) {
			currFactor = primeFactorsAL.get(i);
			firstInput = chinRemainThmObj.doBigExponentiation(number, ((p - 1) / currFactor));
			secondInput = chinRemainThmObj.doBigExponentiation(y, ((p - 1) / currFactor));
			currCongruence = shanks.findMatch(firstInput, secondInput, p);
			congruencesAL.add(currCongruence);
		}
	}

	public int solveAllCongruences() {
		int num1 = -1, mod1 = -1, num2 = -1, mod2 = -1;
		boolean isFirstPair = true;
		for (int i = 0; i < (primeFactorsAL.size() - 1); i++) {
			if (isFirstPair) {
				num1 = congruencesAL.get(i);
				mod1 = primeFactorsAL.get(i);
				isFirstPair = false;
			}
			num2 = congruencesAL.get(i + 1);
			mod2 = primeFactorsAL.get(i + 1);
			num1 = chinRemainThmObj.chinRemainThm(num1, mod1, num2, mod2);
			mod1 *= mod2;
		}
		return num1;
	}

	// Calculate needed prime numbers of the modulo p
	public void findPrimeFactors(int number) {
		int n = number;
		int currentNum = 0;
		int temp;

		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				if (i == currentNum) {
					temp = primeFactorsAL.get(primeFactorsAL.size() - 1) * i;
					primeFactorsAL.set((primeFactorsAL.size() - 1), temp);
				} else {
					currentNum = i;
					primeFactorsAL.add(i);
				}
				n /= i;
			}
		}

		if (n > 1) {
			primeFactorsAL.add(n);
		}
	}

}