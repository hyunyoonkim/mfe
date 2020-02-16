package mvc.chat.db;

import java.util.Date;

public class ChatBean {
	private String  member_id, chat_log_content;
	private Date chat_log_date;
	private int chat_log_id, chat_log_id2;
	private int chat_max, chat_min;
	private int chat_log_id2_count;

	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getChat_log_content() {
		return chat_log_content;
	}
	public void setChat_log_content(String chat_log_content) {
		this.chat_log_content = chat_log_content;
	}
	public Date getChat_log_date() {
		return chat_log_date;
	}
	public void setChat_log_date(Date chat_log_date) {
		this.chat_log_date = chat_log_date;
	}
	public int getChat_log_id() {
		return chat_log_id;
	}
	public void setChat_log_id(int chat_log_id) {
		this.chat_log_id = chat_log_id;
	}
	public int getChat_log_id2() {
		return chat_log_id2;
	}
	public void setChat_log_id2(int chat_log_id2) {
		this.chat_log_id2 = chat_log_id2;
	}
	public int getChat_max() {
		return chat_max;
	}
	public void setChat_max(int chat_max) {
		this.chat_max = chat_max;
	}
	public int getChat_min() {
		return chat_min;
	}
	public void setChat_min(int chat_min) {
		this.chat_min = chat_min;
	}
	public int getChat_log_id2_count() {
		return chat_log_id2_count;
	}
	public void setChat_log_id2_count(int chat_log_id2_count) {
		this.chat_log_id2_count = chat_log_id2_count;
	}


}
