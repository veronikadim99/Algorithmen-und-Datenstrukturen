import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases=0;
	
	void initialize() {
		n1=4;
		n2=6;
		Cases c= new Cases();
		p1= c.switchforTesting(cases, n1);
		p2= c.switchforTesting(cases, n2);
	}
	

	@Test
	void testPermutation() {
		initialize();
		// TODO;
		//check if there is a number that appears twice in the permutation



		//test Permutation

			int j = 0;
			try {
				assertEquals(n1, p1.original.length);  //length original must be equal to 4


			} catch (NullPointerException exc) {
				System.out.println("They are not equal.");
			}


			while(j < p1.original.length - 1) {
				for(int i = j+1; i < p1.original.length; i++){
					if(p1.original[j] == p1.original[i]){      //if it appears twice, then fail for p1
						fail("Number appears twice");
					//fail
					}
				}
				j++;
			}

			try{
				assertTrue(p1.allDerangements.isEmpty()); //empty list
			}catch (NullPointerException exc) {
				fail("The list is not empyty");
			}

		//test Permutation1
			int k = 0;

			try {
				assertEquals(n2, p2.original.length);  //length original must be equal to 6
			} catch (NullPointerException exc) {
				System.out.println("They are not equal.");
			}

			while(k < p2.original.length -1) {
				for(int l = k+1; l < p2.original.length; l++) {
					if (p2.original[k] == p2.original[l]) {
						fail("Number appears twice"); //if it appears twice, then fail for p2
					}
				}
				k++;
			}

			try{
				assertTrue(p2.allDerangements.isEmpty());
			}catch (NullPointerException exc) {
				fail("The list is not empty");
			}  //nullpointer

		}


	@Test
	void testDerangements() {
        initialize(); //in case there is something wrong with the constructor

		fixConstructor();

		p1.derangements();
		assertEquals(derNum(n1), p1.allDerangements.size());


		for(int i = 0; i < p1.original.length; i++) {
			for (int j = 0; j < p1.allDerangements.size(); j++) {
				int[] first = p1.allDerangements.get(j);

				if (first[i] == p1.original[i]) {
					fail("The condition is not met");
				}


			}
		}

		p2.derangements();
		assertEquals(derNum(n2), p2.allDerangements.size());

		for(int k = 0; k < p2.original.length; k++) {
			for (int l = 0; l < p2.allDerangements.size(); l++) {
				int[] first = p2.allDerangements.get(l);

				if (first[k] == p2.original[k]) {
					fail("The condition is not met");
				}


			}
		}


	}

	private int derNum(int n1) {
		int derangementsNum;

		if (n1 == 0 || n1 == 2) { return 1; }

		if(n1 == 1) { return 0; }


		derangementsNum = (n1 - 1) * (derNum(n1 - 1) + derNum(n1 - 2));
		return derangementsNum;

	}



	@Test
	void testsameElements() {
		initialize();
		fixConstructor();
		p1.derangements();


			for(int j = 0; j < p1.allDerangements.size(); j++) {
				assertEquals(p1.allDerangements.get(j).length,p1.original.length);

				for(int l = 0; l < p1.original.length; l++){
					boolean checkIf = false;
					for(int i = 0; i< p1.original.length; i++) {
						if (p1.allDerangements.get(j)[i] == p1.original[l]) {

							checkIf = true;
						}
					}
					assertTrue(checkIf);
				}
			}



		try{
			assertFalse(p1.allDerangements.isEmpty());   //if there are no permutations,fail
		}catch(NullPointerException exc){
			fail("No permutations found");
		}


		p2.derangements();  //calling the function
		for(int j = 0; j < p2.allDerangements.size(); j++) {
			assertEquals(p2.allDerangements.get(j).length,p2.original.length);

			for(int l = 0; l < p2.original.length; l++){
				boolean checkIf = false;
				for(int i = 0; i< p2.original.length; i++) {
					if (p2.allDerangements.get(j)[i] == p2.original[l]) {

						checkIf = true;
					}
				}
				assertTrue(checkIf);
			}
		}

		try{
			assertFalse(p2.allDerangements.isEmpty());
		}catch(NullPointerException exc){
			fail("No permutations found");
		}

	}



	
	void setCases(int c) {
		this.cases=c;
	}
	
	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=2*i+1;
		
		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}
}


