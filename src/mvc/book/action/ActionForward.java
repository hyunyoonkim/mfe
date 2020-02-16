package mvc.book.action;
//ActionForward 클래스는 Action 인터페이스에서 명령을 수행하고 결과 값을
// 가지고 포워딩 할 때 사용되는 클래스입니다.
// 이 클래스는 Redirect 여부 값과 포워딩할 페이지의 위치를 가지고 있습니다.
//이 값들은 FrontController에서 ActionForward 클래스 타입으로 반환값을
//가져오면 그 값을 확인하여 해당하는 요청 페이지로 포워딩을 처리합니다.


public class ActionForward {
	private boolean redirect=false;
	private String path=null;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	
	
	//property Redirect 의 is 메소드
	public boolean isRedirect() {
		//프로퍼티 타입이 boolean일 경우 get 대신 is를 앞에 붙일 수 있다.
		return redirect;
	}
	
	
}
