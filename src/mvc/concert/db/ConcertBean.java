package mvc.concert.db;

import java.sql.Date;

public class ConcertBean {

	private int concert_id;
	private String concert_name;
	private Date concert_day;
	private String concert_musician;
	private String concert_open;
	private String concert_close;
	private String concert_image;
	private String genre_id;
	private String local_id;
	private String concert_price;
	
	private int likey_id;
	private String member_id;
	
	public int getConcert_id() {
		return concert_id;
	}
	public void setConcert_id(int concert_id) {
		this.concert_id = concert_id;
	}
	public String getConcert_name() {
		return concert_name;
	}
	public void setConcert_name(String concert_name) {
		this.concert_name = concert_name;
	}
	public Date getConcert_day() {
		return concert_day;
	}
	public void setConcert_day(Date concert_day) {
		this.concert_day = concert_day;
	}
	public String getConcert_musician() {
		return concert_musician;
	}
	public void setConcert_musician(String concert_musician) {
		this.concert_musician = concert_musician;
	}
	public String getConcert_open() {
		return concert_open;
	}
	public void setConcert_open(String concert_open) {
		this.concert_open = concert_open;
	}
	public String getConcert_close() {
		return concert_close;
	}
	public void setConcert_close(String concert_close) {
		this.concert_close = concert_close;
	}
	public String getConcert_image() {
		return concert_image;
	}
	public void setConcert_image(String concert_image) {
		this.concert_image = concert_image;
	}
	public String getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(String genre_id) {
		this.genre_id = genre_id;
	}
	public String getLocal_id() {
		return local_id;
	}
	public void setLocal_id(String local_id) {
		this.local_id = local_id;
	}
	public String getConcert_price() {
		return concert_price;
	}
	public void setConcert_price(String concert_price) {
		this.concert_price = concert_price;
	}
	public int getLikey_id() {
		return likey_id;
	}
	public void setLikey_id(int likey_id) {
		this.likey_id = likey_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
	

}