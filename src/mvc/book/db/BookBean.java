package mvc.book.db;

import java.util.Date;

public class BookBean {

	private int book_id, concert_id, book_amount, book_payment;
	private String book_eticket, member_id, card_id;
	private Date book_date;
	private Date concert_day;
	
	public int getBook_payment() {
		return book_payment;
	}
	public void setBook_payment(int book_payment) {
		this.book_payment = book_payment;
	}
	public Date getConcert_day() {
		return concert_day;
	}
	public void setConcert_day(Date concert_day) {
		this.concert_day = concert_day;
	}
	public String getConcert_name() {
		return concert_name;
	}
	public void setConcert_name(String concert_name) {
		this.concert_name = concert_name;
	}
	private String concert_name;
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getConcert_id() {
		return concert_id;
	}
	public void setConcert_id(int concert_id) {
		this.concert_id = concert_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getBook_amount() {
		return book_amount;
	}
	public void setBook_amount(int book_amount) {
		this.book_amount = book_amount;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getBook_eticket() {
		return book_eticket;
	}
	public void setBook_eticket(String book_eticket) {
		this.book_eticket = book_eticket;
	}
	public Date getBook_date() {
		return book_date;
	}
	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}
	
}
