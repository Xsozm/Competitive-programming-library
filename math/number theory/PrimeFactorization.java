package math.number_theory;

import java.util.ArrayList;

public class PrimeFactorization {

	static ArrayList<Integer> primes;					// generated by sieve

	/*
	 * 1. Generating a list of prime factors of N
	 */	
	static ArrayList<Integer> primeFactors(int N)		// O(sqrt(N) / ln sqrt(N))
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();		//take abs(N) in case of -ve integers
		int idx = 0, p = primes.get(idx);

		while(p * p <= N)
		{
			while(N % p == 0) { factors.add(p); N /= p; }
			p = primes.get(++idx);
		}

		if(N != 1)						// last prime factor may be > sqrt(N)
			factors.add(N);				// for integers whose largest prime factor has a power of 1
		return factors;
	}

	/*
	 * 2. Sum of divisors of N
	 */
	static int sumDiv(int N)
	{
		int ans = 1, idx = 0, p = primes.get(0);
		while(p * p <= N)
		{
			int e = 0;
			while(N % p == 0) { N /= p; ++e; }
			ans *= (pow(p, e + 1) - 1) / (p - 1);
			p = primes.get(++idx);
		}
		if(N != 1)
			ans *= (pow(N, 2) - 1) / (N - 1);
		return ans;
	}
	
	/*
	 * 3. Euler's Totient Function (Number of positive integers < N relatively prime to N)
	 */
	static int phi(int N)
	{
		int ans = N, idx = 0, p = primes.get(0);
		while(p * p <= N)
		{
			if(N % p == 0)
				ans -= ans / p;
			while(N % p == 0)
				N /= p;
			p = primes.get(++idx);
		}
			
		if(N != 1) 
			ans -= ans / N;
		return ans;
	}

	static int pow(int a, int n)
	{
		int res = 1;
		while(n != 0)
		{
			if((n & 1) == 1)
				res *= a;
			a *= a;
			n >>= 1;			
		}
		return res;
	}
}
