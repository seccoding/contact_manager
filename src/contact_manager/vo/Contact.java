package contact_manager.vo;

import java.util.List;

public class Contact {

	private String name;
	private List<String> phoneNumber;
	private String birthDay;
	private String relation;
	private boolean isBlock;
	private String anniversary;
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public boolean getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public String getAnniversary() {
		return anniversary;
	}

	public void setAnniversary(String anniversary) {
		this.anniversary = anniversary;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("Contact [");
		sb.append("이름(별명): " + this.name + ", ");
		String contactNumber = null;
		for (int i = 0; i < this.phoneNumber.size(); i++) {
			contactNumber = this.phoneNumber.get(i);
			sb.append("연락처 목록 번호: " + i + ", ");
			sb.append("연락처 번호: " + contactNumber + ", ");
		}
		sb.append("생일: " + this.birthDay + ", ");
		sb.append("관계: " + this.relation + ", ");
		sb.append("차단여부: " + this.isBlock + ", ");
		sb.append("기념일: " + this.anniversary + ", ");
		sb.append("메모: " + this.memo);
		sb.append("]");

		return sb.toString();
	}

}
