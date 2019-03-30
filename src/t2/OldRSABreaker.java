package t2;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.xml.crypto.dsig.XMLSignature.SignatureValue;

public class OldRSABreaker {

	private static int n;
	private static BigInteger e;
	private int encryptedMessage;
	static Pair PrimeNumbs;
	static int Phi;

	public void setN(int n) {
		this.n = n;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

	public static BigInteger getPhi() {
		
		Pair pair = sieve();
		Phi = ((pair.getP())-1)*((pair.getQ())-1);
		return BigInteger.valueOf(Phi);
	}

	public void setEncryptedMessage(int encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	public static BigInteger getD() {
		
		 int bTemp2 = getPhi().intValue();
		 int x = 0 ,v = 0;
		 int y = 1, u = 1;
		 int q = 0 ,m = 0 ,n = 0;
		 int r = 0;
		 int l = e.intValue();
		  while(l!=0) {
			  q  = (int) (bTemp2/l);
			  r = (int) (bTemp2%l);
			  m = x-u*q;
			  n = y-v*q;
			  bTemp2 = (int) l;
			  l = r;
			  x = u;
			  y = v;
			  u = m;
			  v = n;  
			  
		  }

		  return (BigInteger.valueOf(x));
	}

	public BigInteger decryptMessage() {
		
		BigInteger N = BigInteger.valueOf(n);
		BigInteger decryptedMessage = (BigInteger.valueOf(encryptedMessage)).modPow(getD(), N);
		return decryptedMessage;
	}

	public static Pair sieve() {

		int upperBoundSquareRoot = (int) Math.sqrt(n);

		boolean[] isComposite = new boolean[n + 1];

		for (int m = 2; m <= upperBoundSquareRoot; m++) {

			if (!isComposite[m]) {

				for (int k = m * m; k < n; k += m)

					isComposite[k] = true;

			}

		}

		Pair pair = null;
		for (int m = 2; m < n; m++) {

			if (!isComposite[m] && n % m == 0 && !isComposite[n / m]) {

				pair = new Pair(m, n / m);
			}
		}

		return pair;

	} 
	
}