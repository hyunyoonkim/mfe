package mvc.concert.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.concert.db.ConcertBeanN;
import mvc.concert.db.ConcertDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class SearchfilterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		ConcertDAO cdao = new ConcertDAO();

		String date = request.getParameter("search_date");
		String local = request.getParameter("search_local");
		String genre = request.getParameter("search_genre");
		System.out.println("date = " + date + "local = " + local + "genre = " + genre);

		Date search_date = null;
		String[] search_local = null;
		String[] search_genre = null;

		List<Integer> list = new ArrayList<Integer>();
		List<ConcertBeanN> list1 = new ArrayList<ConcertBeanN>();
		List<ConcertBeanN> list2 = new ArrayList<ConcertBeanN>();
		List<ConcertBeanN> list3 = new ArrayList<ConcertBeanN>();
		List<ConcertBeanN> flist = new ArrayList<ConcertBeanN>();

		// date, local, genre 모두 선택했을 경우
		if (!(date.equals(null) || date.equals("")) && !local.equals("전국,") && !genre.equals("모든 장르,")) {
			System.out.println("모두 선택");

			search_date = Date.valueOf(date);
			search_local = local.split(",");
			search_genre = genre.split(",");
			list1 = cdao.getDateList(search_date);
			list2 = cdao.getLocalList(search_local);
			list3 = cdao.getGenreList(search_genre);

			System.out.println("list1.size() = " + list1.size());
			System.out.println("list2.size() = " + list2.size());
			System.out.println("list3.size() = " + list3.size());

			List<Integer> temp = new ArrayList<Integer>();
			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list1.get(i).getConcert_id() == list2.get(j).getConcert_id()) {
						temp.add(list1.get(i).getConcert_id());
					}
				}
			}

			for (int i = 0; i < list3.size(); i++) {
				for (int j = 0; j < temp.size(); j++) {
					if (list3.get(i).getConcert_id() == temp.get(j)) {
						list.add(list3.get(i).getConcert_id());
					}
				}
			}
			if (list.size() != 0)
				flist = cdao.getFilterList(list);
		}
		// date = null일 때
		else if ((date.equals(null) || date.equals("")) && !local.equals("전국,") && !genre.equals("모든 장르,")) {
			System.out.println("date == null");
			search_local = local.split(",");
			search_genre = genre.split(",");
			list1 = cdao.getLocalList(search_local);
			list2 = cdao.getGenreList(search_genre);

			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list1.get(i).getConcert_id() == list2.get(j).getConcert_id()) {
						list.add(list1.get(i).getConcert_id());
					}
				}
			}

			if (list.size() != 0)
				flist = cdao.getFilterList(list);
		}

		// local = null일 때
		else if (local.equals("전국,") && !(date.equals(null) || date.equals("")) && !genre.equals("모든 장르,")) {
			System.out.println("local == null");

			search_date = Date.valueOf(date);
			search_local = local.split(",");
			search_genre = genre.split(",");

			list1 = cdao.getDateList(search_date);
			list2 = cdao.getGenreList(search_genre);

			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list1.get(i).getConcert_id() == list2.get(j).getConcert_id()) {
						list.add(list1.get(i).getConcert_id());
					}
				}
			}

			if (list.size() != 0)
				flist = cdao.getFilterList(list);
		}
		// genre = null일 때
		else if (genre.equals("모든 장르,") && !(date.equals(null) || date.equals("")) && !local.equals("전국,")) {
			System.out.println("genre == null");

			search_date = Date.valueOf(date);
			search_local = local.split(",");
			search_genre = genre.split(",");

			list1 = cdao.getLocalList(search_local);
			list2 = cdao.getDateList(search_date);

			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list1.get(i).getConcert_id() == list2.get(j).getConcert_id()) {
						list.add(list1.get(i).getConcert_id());
					}
				}
			}

			if (list.size() != 0)
				flist = cdao.getFilterList(list);
		}

// local = null, genre = null일 때(date만 선택했을 때)
		if (local.equals("전국,") && genre.equals("모든 장르,") && !(date.equals(null) || date.equals(""))) {
			System.out.println("local == null && genre == null");

			search_date = Date.valueOf(date);
			search_local = local.split(",");
			search_genre = genre.split(",");
			flist = cdao.getDateList(search_date);
		}
// date = null, genre = null일 때(local만 선택했을 때)
		else if ((date.equals(null) || date.equals("")) && genre.equals("모든 장르,") && !local.equals("전국,")) {
			System.out.println("date == null && genre == null");

			search_local = local.split(",");
			search_genre = genre.split(",");
			flist = cdao.getLocalList(search_local);
		}
// date = null, local = null일 때(genre만 선택했을 때)
		else if ((date.equals(null) || date.equals("")) && local.equals("전국,") && !genre.equals("모든 장르,")) {
			System.out.println("date == null && local == null");

			search_local = local.split(",");
			search_genre = genre.split(",");
			flist = cdao.getGenreList(search_genre);
		}
// date = null, local = 전국, genre = 모든장르일 때
		else if ((date.equals(null) || date.equals("")) && local.equals("전국,") && genre.equals("모든 장르,")) {
			System.out.println("date == null && local == null && genre == null");

			search_local = local.split(",");
			search_genre = genre.split(",");
			flist = cdao.getAllList();
		}

		System.out.println("flist.size() = " + flist.size());
		if (flist.size() != 0) {
			request.setAttribute("flist", flist);
			request.setAttribute("flistsize", flist.size());
			request.setAttribute("search_date", date);
			request.setAttribute("search_local", search_local);
			request.setAttribute("search_genre", search_genre);
			forward.setRedirect(false);
			forward.setPath("search/filter_result_form.jsp");
			return forward;
		} else {
			forward.setRedirect(false);
			request.setAttribute("none", "검색 결과가 존재하지 않습니다.");
			forward.setPath("search/filter_result_form.jsp");
			return forward;
		}
	}
}