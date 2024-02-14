package contact_manager.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contact_manager.vo.Contact;

public class ContactManager {

	protected List<Contact> contactList;
	
	public ContactManager() {
		this.contactList = new ArrayList<>();
	}

	public void printAllContacts() {
		System.out.println("연락처 목록");
		System.out.println("=".repeat(30));
		for (int i = 0; i < this.contactList.size(); i++) {
			System.out.println("번호: " + i + ", " 
		                     + "이름: " + this.contactList.get(i).getName());
		}
	}
	
	public void printOneConctact(int index) {
		if (this.contactList.size() > index) {
			Contact contact = this.contactList.get(index);
			
			System.out.println("번호: " + index);
			System.out.println("이름: " + contact.getName());
			List<String> phoneNumberList = this.contactList.get(index).getPhoneNumber();
			for (int i = 0; i < phoneNumberList.size(); i++) {
				System.out.println("전화번호 목록 번호: " + i);
				System.out.println("전화번호: " + phoneNumberList.get(i));
			}
		}
		else {
			System.out.println("존재하지 않는 연락처입니다.");
		}
		
	}
	
	public void printAndUpdateDetailContact(int index) {
		if (this.contactList.size() > index) {
			Contact contact = this.contactList.get(index);
			System.out.println(contact);
			
			Scanner keyboard = new Scanner(System.in);
			System.out.println("연락처를 편집하시겠습니까? (yes / no)");
			String yesOrNo = keyboard.nextLine();
			
			if (yesOrNo.equalsIgnoreCase("yes")) {
				while (true) {
					System.out.println("편집할 항목의 이름을 입력하세요.");
					String itemName = keyboard.nextLine();
					if (itemName.equals("이름")) {
						System.out.println("변경할 내용을 입력하세요.");
						String newName = keyboard.nextLine();
						if (newName.trim().length() > 0) {
							this.contactList.get(index).setName(newName);
							break;
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("연락처")) {
						List<String> phoneNumberList = this.contactList.get(index).getPhoneNumber();
						for (int i = 0; i < phoneNumberList.size(); i++) {
							System.out.println("전화번호 목록 번호: " + i);
							System.out.println("전화번호: " + phoneNumberList.get(i));
						}
						System.out.println("편집할 전화번호 목록의 번호를 입력하세요.");
						int indexNumber = keyboard.nextInt();
						keyboard.nextLine();
						
						if (phoneNumberList.size() >= indexNumber) {
							System.out.println("변경할 내용을 입력하세요.");
							String newPhoneNumber = keyboard.nextLine();
							if (newPhoneNumber.trim().length() > 0) {
								// add 아이템을 추가
								// remove 아이템을 제거
								// set 아이템 수정.
								phoneNumberList.set(indexNumber, newPhoneNumber.trim());
								break;
							}
							else {
								System.out.println("편집할 수 없습니다.");
							}
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("생일")) {
						System.out.println("변경할 내용을 입력하세요.");
						String newBirthDay = keyboard.nextLine();
						if (newBirthDay.trim().length() > 0) {
							this.contactList.get(index).setBirthDay(newBirthDay);
							break;
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("관계")) {
						System.out.println("변경할 내용을 입력하세요.");
						String newRelation = keyboard.nextLine();
						if (newRelation.trim().length() > 0) {
							this.contactList.get(index).setRelation(newRelation);
							break;
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("차단여부")) {
						System.out.println("변경할 내용을 입력하세요.");
						String newIsBlock = keyboard.nextLine();
						if (newIsBlock.trim().length() > 0) {
							this.contactList.get(index).setIsBlock(Boolean.parseBoolean(newIsBlock));
							break;
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("기념일")) {
						System.out.println("변경할 내용을 입력하세요.");
						String newAnniversary = keyboard.nextLine();
						if (newAnniversary.trim().length() > 0) {
							this.contactList.get(index).setAnniversary(newAnniversary);
							break;
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("메모")) {
						System.out.println("변경할 내용을 입력하세요.");
						String newMemo = keyboard.nextLine();
						if (newMemo.trim().length() > 0) {
							this.contactList.get(index).setMemo(newMemo);
							break;
						}
						else {
							System.out.println("편집할 수 없습니다.");
						}
					}
					else if (itemName.equals("취소")) {
						System.out.println("편집이 취소되었습니다.");
						break;
					}
					else {
						System.out.println("편집할 수 없습니다.");
					}
				}
			}
			else {
				System.out.println("편집이 취소되었습니다.");
			}
		}
		else {
			System.out.println("존재하지 않는 연락처입니다.");
		}
	}
	
	public void deleteOneContact(int index) {
		if (this.contactList.size() > index) {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("정말 삭제하시겠습니까? (yes / no)");
			
			String yesOrNo = keyboard.nextLine();
			if (yesOrNo.equalsIgnoreCase("yes")) {
				this.contactList.remove(index);
			}
			else {
				System.out.println("삭제가 취소되었습니다.");
			}
		}
		else {
			System.out.println("존재하지 않는 연락처입니다.");
		}
	}
	
	public void addOneContact() {
		Scanner keyboard = new Scanner(System.in);
		Contact newContact = new Contact();
		newContact.setIsBlock(false);
		
		String name = null;
		while (name == null || name.trim().length() == 0) {
			System.out.println("이름을 입력하세요.");
			name = keyboard.nextLine();
		}
		newContact.setName(name);
		
		List<String> phoneNumberList = new ArrayList<>();
		String phoneNumber = null;
		boolean addOtherNumber = true;
		while (addOtherNumber) {
			phoneNumber = null;
			while(phoneNumber == null || phoneNumber.trim().length() == 0) {
				System.out.println("연락처를 입력하세요.");
				phoneNumber = keyboard.nextLine();
			}
			
			phoneNumberList.add(phoneNumber);
			newContact.setPhoneNumber(phoneNumberList);
			
			System.out.println("연락처를 더 추가하시겠습니까? (yes / no)");
			addOtherNumber = keyboard.nextLine().equalsIgnoreCase("yes");
		}
		
		System.out.println("생일을 입력하세요.");
		String birthDay = keyboard.nextLine().trim();
		newContact.setBirthDay(birthDay);
		
		System.out.println("관계를 입력하세요.");
		String relation = keyboard.nextLine().trim();
		newContact.setRelation(relation);
		
		System.out.println("기념일을 입력하세요.");
		String anniversary = keyboard.nextLine().trim();
		newContact.setAnniversary(anniversary);
		
		System.out.println("메모를 입력하세요.");
		String memo = keyboard.nextLine().trim();
		newContact.setMemo(memo);
		
		System.out.println(newContact);
		
		System.out.println("이 정보로 등록하시겠습니까? (yes / no)");
		String yesOrNo = keyboard.nextLine();
		if (yesOrNo.equalsIgnoreCase("yes")) {
			this.contactList.add(newContact);
		}
		else {
			System.out.println("등록을 취소합니다.");
		}
	}
	
	public void printBirthDayContact() {
		System.out.println("생일이 다가오는 연락처들");
		LocalDate now = LocalDate.now();
		LocalDate after15Day = now.plusDays(15);
		
		Contact contact = null;
		LocalDate birthDate = null;
		for (int i = 0; i < this.contactList.size(); i++) {
			contact = this.contactList.get(i);
			String birthDay = contact.getBirthDay();
			
			if (birthDay == null || birthDay.trim().length() == 0) {
				continue; // 현재 반복은 멈추고 다음 인덱스로 진행함.
			}
			
			if (birthDay.contains("-")) {
				String[] splittedBirthDay = birthDay.split("-");
				birthDate = LocalDate.of(now.getYear(), 
						Integer.parseInt(splittedBirthDay[1]), 
						Integer.parseInt(splittedBirthDay[2]));
			}
			else if (birthDay.contains("\\.")) {
				String[] splittedBirthDay = birthDay.split("\\.");
				birthDate = LocalDate.of(now.getYear(), 
						Integer.parseInt(splittedBirthDay[1]), 
						Integer.parseInt(splittedBirthDay[2]));
			}
			else if (birthDay.length() == "20240101".length()) {
				int month = Integer.parseInt(birthDay.substring(4, 6));
				int date = Integer.parseInt(birthDay.substring(6, 8));
				birthDate = LocalDate.of(now.getYear(), month, date);
			}
			
			if (birthDate.isBefore(after15Day) && birthDate.isAfter(now)) {
				System.out.println("이름: " + contact.getName());
				System.out.println("생일: " + contact.getBirthDay());
			}
		}
	}
	
	public void printAnniversaryContact() {
		System.out.println("기념일이 다가오는 연락처들");
		
		LocalDate now = LocalDate.now();
		LocalDate after15Day = now.plusDays(15);
		
		Contact contact = null;
		LocalDate anniversaryDate = null;
		for (int i = 0; i < this.contactList.size(); i++) {
			contact = this.contactList.get(i);
			String anniversaryDay = contact.getAnniversary();
			if (anniversaryDay == null || anniversaryDay.trim().length() == 0) {
				continue; // 현재 반복은 멈추고 다음 인덱스로 진행함.
			}
			
			if (anniversaryDay.contains("-")) {
				String[] splittedAnniversary = anniversaryDay.split("-");
				anniversaryDate = LocalDate.of(now.getYear(), 
						Integer.parseInt(splittedAnniversary[1]), 
						Integer.parseInt(splittedAnniversary[2]));
			}
			else if (anniversaryDay.contains("\\.")) {
				String[] splittedAnniversary = anniversaryDay.split("\\.");
				anniversaryDate = LocalDate.of(now.getYear(), 
						Integer.parseInt(splittedAnniversary[1]), 
						Integer.parseInt(splittedAnniversary[2]));
			}
			else if (anniversaryDay.length() == "20240101".length()) {
				int month = Integer.parseInt(anniversaryDay.substring(4, 6));
				int date = Integer.parseInt(anniversaryDay.substring(6, 8));
				anniversaryDate = LocalDate.of(now.getYear(), month, date);
			}
			
			if (anniversaryDate.isBefore(after15Day) && anniversaryDate.isAfter(now)) {
				System.out.println("이름: " + contact.getName());
				System.out.println("기념일: " + contact.getAnniversary());
			}
		}
	}

}
