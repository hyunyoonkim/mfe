package mvc.concert.db;


public class ConcertBeanJoin extends ConcertBean{
	
	/*
	 * private int concert_id; 
	 * private String concert_name; 
	 * private Date concert_day; 
	 * private String concert_musician; 
	 * private String concert_open;
	 * private String concert_close; 
	 * private String concert_image; 
	 * private String genre_id; 
	 * private String local_id; 
	 * private String concert_price;
	 */
	String book_eticket;
	int book_amount;
	String card_id;
	String book_date;
	int total;
	int book_payment;
	
	
	public int getBook_payment() {
		return book_payment;
	}
	public void setBook_payment(int book_payment) {
		this.book_payment = book_payment;
	}
	public int getTotal() {
		return total;
	}
	public String getBook_eticket() {
		return book_eticket;
	}
	public void setBook_eticket(String book_eticket) {
		this.book_eticket = book_eticket;
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
	public String getBook_date() {
		return book_date;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public void setTotal(int total2) {
		this.total = total2;
		
	}
	
	
}
