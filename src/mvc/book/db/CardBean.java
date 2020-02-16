package mvc.book.db;

public class CardBean {
	String card_id, member_id;
	int card_mmyy, card_cvv;
	
	
	
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getCard_mmyy() {
		return card_mmyy;
	}
	public void setCard_mmyy(int card_mmyy) {
		this.card_mmyy = card_mmyy;
	}
	public int getCard_cvv() {
		return card_cvv;
	}
	public void setCard_cvv(int card_cvv) {
		this.card_cvv = card_cvv;
	}
	
	
}
