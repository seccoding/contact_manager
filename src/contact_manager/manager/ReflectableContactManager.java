package contact_manager.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import contact_manager.util.ReflectUtil;
import contact_manager.vo.Contact;

public class ReflectableContactManager extends ContactManager {

	private Map<String, String> variablesName;
	private Scanner keyboard;
	
	public ReflectableContactManager() {
		super();
		this.keyboard = new Scanner(System.in);
		
		this.variablesName = new HashMap<>();
		this.variablesName.put("이름", "name");
		this.variablesName.put("연락처", "phoneNumber");
		this.variablesName.put("생일", "birthDay");
		this.variablesName.put("관계", "relation");
		this.variablesName.put("차단여부", "isBlock");
		this.variablesName.put("기념일", "anniversary");
		this.variablesName.put("메모", "memo");
	}

	@Override
	public void printAndUpdateDetailContact(int index) {
		if (super.contactList.size() > index) {
			Contact contact = super.contactList.get(index);
			System.out.println(contact);
			
			System.out.println("연락처를 편집하시겠습니까? (yes / no)");
			String yesOrNo = this.keyboard.nextLine();
			
			if (yesOrNo.equalsIgnoreCase("yes")) {
				while (true) {
					System.out.println("편집할 항목의 이름을 입력하세요.");
					String itemName = this.keyboard.nextLine();
					
					if (this.variablesName.containsKey(itemName)) {
						Contact item = super.contactList.get(index);
						if (itemName.equals("연락처")) {
							if (changePhoneNumbers(item)) {
								break;
							}
						}
						else {
							String fieldName = this.variablesName.get(itemName);
							System.out.println("변경할 내용(" + fieldName + ")을 입력하세요.");
							String newValue = this.keyboard.nextLine();
							if (newValue.trim().length() > 0) {
								Object object = ReflectUtil.invokeGetter(item, fieldName);
								ReflectUtil.invokeSetter(object, fieldName, newValue);
								break;
							}
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
	
	private boolean changePhoneNumbers(Contact item) {
		List<String> phoneNumberList = item.getPhoneNumber();
		for (int i = 0; i < phoneNumberList.size(); i++) {
			System.out.println("전화번호 목록 번호: " + i);
			System.out.println("전화번호: " + phoneNumberList.get(i));
		}
		
		System.out.println("편집할 전화번호 목록의 번호를 입력하세요.");
		int indexNumber = this.keyboard.nextInt();
		this.keyboard.nextLine();
		
		if (phoneNumberList.size() >= indexNumber) {
			System.out.println("변경할 내용을 입력하세요.");
			String newPhoneNumber = this.keyboard.nextLine();
			if (newPhoneNumber.trim().length() > 0) {
				phoneNumberList.set(indexNumber, newPhoneNumber.trim());
				return true;
			}
			else {
				System.out.println("편집할 수 없습니다.");
			}
		}
		else {
			System.out.println("편집할 수 없습니다.");
		}
		
		return false;
	}
	
	@Override
	public void addOneContact() {
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
				phoneNumber = this.keyboard.nextLine();
			}
			
			phoneNumberList.add(phoneNumber);
			newContact.setPhoneNumber(phoneNumberList);
			
			System.out.println("연락처를 더 추가하시겠습니까? (yes / no)");
			addOtherNumber = this.keyboard.nextLine().equalsIgnoreCase("yes");
		}
		
		this.variablesName.forEach((key, value) -> {
			if (!key.equals("이름") && !key.equals("연락처")) {
				System.out.println(key + "을 입력하세요.");
				String itemValue = this.keyboard.nextLine().trim();
				if (key.equals("차단여부")) {
					ReflectUtil.invokeSetter(newContact, value, Boolean.parseBoolean(itemValue));
				}
				else {
					ReflectUtil.invokeSetter(newContact, value, itemValue);
				}
			}
		});
		
		System.out.println(newContact);
		
		System.out.println("이 정보로 등록하시겠습니까? (yes / no)");
		String yesOrNo = keyboard.nextLine();
		if (yesOrNo.equalsIgnoreCase("yes")) {
			super.contactList.add(newContact);
		}
		else {
			System.out.println("등록을 취소합니다.");
		}
	}
	
}
