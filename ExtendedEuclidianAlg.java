public class ExtendedEuclidianAlg {
	
	public int[] extEuclid(int firstNum, int secondNum) {
		if (secondNum == 0) {
			return new int[] { firstNum, 1, 0 };
		}

		int[] vals = extEuclid(secondNum, firstNum % secondNum);
		int g = vals[0];
		int u = vals[2];
		int v = vals[1] - (firstNum / secondNum) * vals[2];

		while (u < 0) {
			u += secondNum;
			v -= firstNum;
		}

		return new int[] { g, u, v };
	}

}