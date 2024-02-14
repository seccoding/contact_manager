package contact_manager;

import contact_manager.manager.ContactManager;
import contact_manager.manager.ReflectableContactManager;

public class Main {

	public static void main(String[] args) {
		
		ContactManager contactManager = new ReflectableContactManager();
		contactManager.printAllContacts();
		
		contactManager.addOneContact();
		
		contactManager.printOneConctact(0);
		contactManager.printOneConctact(1);
		
		contactManager.printAndUpdateDetailContact(0);
		contactManager.printAndUpdateDetailContact(1);
		
		contactManager.printAllContacts();
		contactManager.printOneConctact(0);
		
		contactManager.deleteOneContact(0);
		contactManager.printAllContacts();
		
		contactManager.printBirthDayContact();
		contactManager.printAnniversaryContact();
		
	}
	
}
