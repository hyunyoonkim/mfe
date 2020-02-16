package mvc.likey.db;

public class likeyVO {
	private int LIKEY_ID;
	private int CONCERT_ID;
	private String MEMBER_ID;
	
	public int getLIKEY_ID() {
		return LIKEY_ID;
	}
	public void setLIKEY_ID(int lIKEY_ID) {
		LIKEY_ID = lIKEY_ID;
	}
	public int getCONCERT_ID() {
		return CONCERT_ID;
	}
	public void setCONCERT_ID(int cONCERT_ID) {
		CONCERT_ID = cONCERT_ID;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
}
