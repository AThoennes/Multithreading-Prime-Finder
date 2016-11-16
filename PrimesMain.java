import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main class for asking the user to 
 * specify an upper and lower bound. It then creates
 * four threads that take that range and divide it by
 * four. This effectively allows each thread to work
 * simultaneously and then converge at the end to share
 * results and put all the primes found into the overall
 * allPrimes array list.
 * 
 * @author Alex Thoennes
 *
 */
public class PrimesMain
{
	/**
	 * Main method for running the process of using four
	 * threads to find the prime numbers within a user 
	 * specified range
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String [] args) throws InterruptedException
	{
		// array list that will (hopefully) hold all the primes 
		// that are found within the specified range
		ArrayList<Integer> allPrimes = new ArrayList<Integer>();
		
		// new scanner object to read user input
		Scanner keyboard = new Scanner(System.in);
		
		// prompt for a lower bound and then set
		// lower equal to the typed bound
		System.out.print("Enter a lower bound: " );
		int lower = keyboard.nextInt();
		
		// prompt the user for an upper bound then
		// set upper equal to the typed bound
		System.out.print("\nNow enter an upper bound: ");
		int upper = keyboard.nextInt();

		// you create a new object of thread and initialize it 
		// to Primes because primes class extends the thread class
		//
		// then set the thread name to threadA, threadB, threadC, or
		// threadD to help differentiate between which thread you
		// are working with
		//
		// then finally call the start method on each thread to
		// start finding prime numbers
		Thread threadA = new Primes(lower, upper);
		threadA.setName("threadA");
		threadA.start();
		
		Thread threadB = new Primes(lower, upper);
		threadB.setName("threadB");
		threadB.start();
		
		Thread threadC = new Primes(lower, upper);
		threadC.setName("threadC");
		threadC.start();
		
		Thread threadD = new Primes(lower, upper);
		threadD.setName("threadD");
		threadD.start();
		
		// call the join method on each and wait until you are allowed to move on
		// when you can move on, put the primes that you found into the overall list
		threadA.join();
		allPrimes.addAll(((Primes) threadA).getAList());
		
		threadB.join();
		allPrimes.addAll(((Primes) threadB).getBList());
		
		threadC.join();
		allPrimes.addAll(((Primes) threadC).getCList());
		
		threadD.join();
		allPrimes.addAll(((Primes) threadD).getDList());
		
		// print out the number of primes found
		System.out.println("\nNumber of Primes Found: " + allPrimes.size());
		
		// then go through the overall array and print the first ten primes
		for (int i = 0; i < 10; i ++)
		{
			System.out.println("\nPrime " + i + ": " + allPrimes.get(i));
		}
		
		// close the resource leak
		keyboard.close();
	}
}
