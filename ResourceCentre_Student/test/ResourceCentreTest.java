import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//final update
public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;
	
	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;
	
	public ResourceCentreTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList= new ArrayList<Camcorder>();
		chromebookList= new ArrayList<Chromebook>();
	}

	
	@Test
	public void addCamcorderTest() {
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);		
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());
		
		//The item just added is as same as the first item of the list
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));
		
		//Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
	}
	@Test
	public void addChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);
				
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addChromebook(chromebookList, cb1);		
		assertEquals("Test if that chromebookList arraylist size is 1?", 1, chromebookList.size());
				
		//The item just added is as same as the first item of the list
		assertSame("Test that chromebook is added same as 1st item of the list?", cb1, chromebookList.get(0));
				
		//Add another item. test The size of the list is 2?
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that chromebook arraylist size is 2?", 2, chromebookList.size());
	}
	
	@Test
	public void retrieveAllCamcorderTest() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//test if the list of camcorders retrieved from the SourceCentre is empty
				String allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);
				String testOutput = "";
				assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
				
		//Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());
		
		//test if the expected output string same as the list of camcorders retrieved from the SourceCentre
		allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0012", "Sony DSC-RX100M7", "Yes", "", 20);
	
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
		
	}
	@Test
	public void retrieveAllChromebookTest() {
		//fail("Not yet implemented");
		assertNotNull("Test if there is valid chromebook arraylist to add to", chromebookList);
		// write your code here
		String allChromebook= ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = "";
		assertEquals("Check that ViewAllchromebookList", testOutput, allChromebook);
		
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, chromebookList.size());
		
		allChromebook= ResourceCentre.retrieveAllChromebook(chromebookList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n","CB0011", "My Google Chromebook 1st","Yes","", "Mac OS");
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n","CB0012", "SAMSUNG Chromebook 4+","Yes","", "Win 10");
	
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allChromebook);
		 
	}

	@Test
	public void doLoanCamcorderTest() {
		assertNotNull("Test if there is valid camcorder arraylist to loan from", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		
		Boolean ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		
		assertTrue("Test if an available item is ok to loan?", ok);
		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		
		assertFalse("Test if an same item is NOT ok to loan again?", ok);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "8-8-2020");
		
		assertFalse("Test that un-available item is NOT ok to loan?", ok);
		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0013", "8-8-2020");
		
		assertFalse("Test that non-existing item is NOT ok to loan?", ok);


		
	}
	
	@Test
	public void doLoanChromebookTest() {
				// boundary:test for if arraylist is in reach , can be used
				assertNotNull("test if arrayList is legit to use for loan",chromebookList);
				// to add a chromebook into chromebookList
				ResourceCentre.addChromebook(chromebookList, cb1);
		        // normal condition:test if user can Loan an available item
				Boolean loan=ResourceCentre.doLoanChromebook(chromebookList, "CB0011","23/7");
				assertTrue("Test if an available item is available to loan?",loan);
				// normal condition:test if item can be not available
				loan=ResourceCentre.doLoanChromebook(chromebookList, "CB0011","23/7");
				assertFalse("Test if item can be not available to loan?",loan);
				
				// to add a chromebook into chromebookList
				ResourceCentre.addChromebook(chromebookList, cb2);
				
				//error condition: test if user cannot Loan an unavailable item
				cb2.setIsAvailable(false);  // to make chromebook 2 unavailable 
				loan=ResourceCentre.doLoanChromebook(chromebookList, "CB0012","23/7");
				assertFalse("Test if  user can lend an chromebook that is not available ?",loan);
				
				//error condition : test if user can loan an non-existing chromebook with invalid asset tag
				loan=ResourceCentre.doLoanChromebook(chromebookList, "HI","23/7");
				assertFalse("test if user can loan an non-existing chromebook with invalid asset tag ?",loan);
		
		
		
		
	}
	
	@Test
	public void doReturnCamcorderTest() {
		assertNotNull("Test if there is valid camcorder arraylist to return to",camcorderList);
		
		ResourceCentre.addCamcorder(camcorderList, cc1);
		
		cc1.setIsAvailable(false);
		boolean ok = ResourceCentre.doReturnCamcorder(camcorderList, camcorderList.get(0).getAssetTag());
		assertTrue("Test if an available item is ok to return? ", ok);
		
		ok = ResourceCentre.doReturnCamcorder(camcorderList, camcorderList.get(0).getAssetTag());
		assertFalse("Test if the same item is Not ok to return again? ", ok);
		
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		ok = ResourceCentre.doReturnCamcorder(camcorderList, camcorderList.get(1).getAssetTag());
		assertTrue("Test if nu-available item is ok to return? ", ok);
		
		ok = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Test if the non-esiting item is ok return? ", ok);
		
		
	}
	@Test
	public void doReturnChromebookTest() {
		//List Not NULL - to add item
		assertNotNull("Test for Valid Chromebook List", chromebookList);
		
		//Add item 
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb1.setIsAvailable(false);
		cb2.setIsAvailable(false);
		
		//Can Return An Item On Loan (NORMAL)
		assertTrue("Test if an item on loan can be return", 
				    ResourceCentre.doReturnChromebook(chromebookList, "CB0011"));
		assertTrue("Test if an item on loan can be return", 
			        ResourceCentre.doReturnChromebook(chromebookList, "CB0012"));
		
		//Can't Return An Item That Is Available (ERROR)
		assertFalse("Test if an available item cannot be return",
				    ResourceCentre.doReturnChromebook(chromebookList, "CB0011"));
		assertFalse("Test if an available item cannot be return",
			        ResourceCentre.doReturnChromebook(chromebookList, "CB0012"));
		
		//Can't Return An Item That Is Non-existent (ERROR)
		assertFalse("Test if a non-existent item cannot be return",
			        ResourceCentre.doReturnChromebook(chromebookList, "Chromebook 11"));
		assertFalse("Test if a non-existent item cannot be return",
			        ResourceCentre.doReturnChromebook(chromebookList, "Chromebook 12"));

	}
	
	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
