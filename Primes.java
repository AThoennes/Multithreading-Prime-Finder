import java.util.ArrayList;

/**
 * This class extends the Java Thread class and 
 * finds the prime numbers between the bounds 
 * you set
 * 
 * @author Alex
 * 
 */
public class Primes extends Thread
{
	// lower bound specified by the user
	private final int LOWER_BOUND;
	
	// range of numbers between the upper and lower bounds
	private int difference;

	// thread names used to help identify which thread you are running on
	private final String THREAD_A = "threadA";
	private final String THREAD_B = "threadB";
	private final String THREAD_C = "threadC";
	private final String THREAD_D = "threadD";
	
	// array lists that will eventually hold the prime numbers
	// you find related to the respectable thread so aList contains
	// threadA's primes
	public ArrayList<Integer> aList = new ArrayList<Integer>();
	public ArrayList<Integer> bList = new ArrayList<Integer>();
	public ArrayList<Integer> cList = new ArrayList<Integer>();
	public ArrayList<Integer> dList = new ArrayList<Integer>();

	/**
	 * constructor that takes in a lower bound and 
	 * an upper bound and then finds the range to divide
	 * evely among the threads
	 * 
	 * @param lower
	 * @param upper
	 */
	public Primes(int lower, int upper)
	{
		super();
		
		// lower bound
		LOWER_BOUND = lower;
		
		// range to divide evenly among threads
		difference = upper - lower;
	}

	/**
	 * Overridden run method that assigns each thread to it's proper
	 * subset of the overall range and then finds the primes within
	 * that subset
	 */
	@Override
	public void run()
	{
		super.run();

		// synchronized method
		assignment(difference);
	}

	/**
	 * Must be synchronized otherwise the threads will
	 * be calling this method in four directions and cause
	 * the program wont work properly. Other than that, this
	 * method assigns the proper subset of the overall range
	 * to the proper thread based on the name of the thread
	 * calling the method
	 * 
	 * @param difference
	 */
	private synchronized void assignment(int difference)
	{
		// assign the first quarter of the range to threadA
		if (getName().equals(THREAD_A))
		{
			 primeCount(LOWER_BOUND, LOWER_BOUND + (difference/4)*1);
		}// assign the second quarter of the range to threadB
		else if (getName().equals(THREAD_B))
		{
			 primeCount(LOWER_BOUND + (difference/4)*1, LOWER_BOUND + (difference/4)*2);
		} // assign the third quarter of the range to threadC
		else if (getName().equals(THREAD_C))
		{
			primeCount(LOWER_BOUND + (difference/4)*2, LOWER_BOUND + (difference/4)*3);
		} // assign the fourth quarter of the range to threadD
		else if (getName().equals(THREAD_D))
		{
			primeCount(LOWER_BOUND + (difference/4)*3, LOWER_BOUND + (difference/4)*4);
		}
	}

	/**
	 * This method is synchronized to keep the threads
	 * from calling this method at the same time. When
	 * a thread enters this method, it goes through it's
	 * subset of numbers and finds all the primes in that
	 * subset. It then puts all those numbers into the respective
	 * array list to be moved over to the main method.
	 * 
	 * @param lower
	 * @param upper
	 */
	private synchronized void primeCount(int lower, int upper)
	{
		// temporary array list
		ArrayList<Integer> temp = new ArrayList<Integer>();

		// go through the entire subset of numbers
		for (int i = lower; i < upper; i ++)
		{
			// count is used to see how many times the
			// number is divisible when going through
			// this subset
			int count = 0;
			
			// start at 2 because every number is divisible by one
			//
			// only go up to the square root of the number because
			// after that you're just comparing reverse pairs
			for (int j = 2; j <= Math.sqrt(i); j ++)
			{
				// if it is divisible
				if (i % j == 0)
				{
					// increment count
					count ++;
				}

				// if at any point you find one number that divides
				// your bound then it isn't prime so exit immediately
				if (count > 0)
				{
					break;
				}
			}

			// if the number is prime then add it to the temp array list
			if (count == 0)
			{
				// save prime
				temp.add(i);
			}
		}
		
		// save the primes for threadA
		if (getName().equals(THREAD_A))
		{
			aList = temp;
		} // save the primes for threadB
		else if (getName().equals(THREAD_B))
		{
			bList = temp;
		} // save the primes for threadC
		else if (getName().equals(THREAD_C))
		{
			cList = temp;
		} // save the primes for threadD
		else if (getName().equals(THREAD_D))
		{
			dList = temp;
		}
	}
	
	/* THE FOLLOWING METHODS ARE USED TO TRANSFER THE PRIMES THAT ARE FOUND OVER TO THE MAIN CLASS */
	
	/**
	 * getter for the aList of primes for
	 * threadA
	 * 
	 * @return aList
	 */
	public  ArrayList<Integer> getAList()
	{
		return aList;
	}
	
	/**
	 * getter for the bList of primes for
	 * threadB
	 * 
	 * @return bList
	 */
	public  ArrayList<Integer> getBList()
	{
		return bList;
	}
	
	/**
	 * getter for the cList of primes for 
	 * threadC
	 * 
	 * @return cList
	 */
	public  ArrayList<Integer> getCList()
	{
		return cList;
	}
	
	/**
	 * getter for the dList of primes for
	 * threadD
	 * 
	 * @return dList
	 */
	public  ArrayList<Integer> getDList()
	{
		return dList;
	}
}
