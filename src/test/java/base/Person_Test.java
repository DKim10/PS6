package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {
	
	private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date date = null;
		
		try{
			date = new SimpleDateFormat("yyyy-MM-dd").parse("1995-10-30");
		} catch (ParseException e) {
				e.printStackTrace();
		}
	
		per1 = new PersonDomainModel();
		per1.setFirstName("Daniel");
		per1.setLastName("Kim");
		per1.setBirthday(date);
		per1.setCity("Newark");
		per1.setPostalCode(19702);
		per1.setStreet("Cedar Day");
		
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("It didn't work", per);
	}
	
	@Test
	public void AddPersonTest() {
		PersonDomainModel per;
		
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This is wrong", per );
		PersonDAL.addPerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + "found it!!");
		assertNotNull("This is wrong", per);
	}
	
	@Test
	public void UpdatePersonTest(){ 
		PersonDomainModel per;
		final String nlastname = "Jin";
		
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This is wrong", per );

		PersonDAL.addPerson(per1);
		
		per1.setLastName(nlastname);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());
		
		assertTrue("This is wrong", per1.getLastName() == nlastname);
	}
	
	@Test
	public void DeletePersonTest(){
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This is wrong", per );

		PersonDAL.addPerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + "found it!!");
		assertNotNull("This is wrong", per);

		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNotNull("This is wrong", per);

	}

}
