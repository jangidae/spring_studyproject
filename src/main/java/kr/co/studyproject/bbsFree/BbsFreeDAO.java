package kr.co.studyproject.bbsFree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

import kr.co.studyproject.bbsLang.BbsLangDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class BbsFreeDAO {

	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	public BbsFreeDAO() {
		dbopen = new DBOpen();
	}// end

	public int create(BbsFreeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(
					" INSERT INTO bbsFree(wno, wtitle,wcontent, wpasswd, userid, ccode, filename, filesize, wview, windent, wnum, ip, wdate, mdate) ");
			sql.append(" VALUES( Free_seq.nextval, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?, sysdate, sysdate) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWtitle());
			pstmt.setString(2, dto.getWcontent());
			pstmt.setString(3, dto.getWpasswd());
			pstmt.setString(4, dto.getUserid());
			pstmt.setString(5, dto.getCcode());
			pstmt.setString(6, dto.getFilename());
			pstmt.setLong(7, dto.getFilesize());
			pstmt.setInt(8, dto.getWview());
			pstmt.setInt(9, dto.getWindent());
			pstmt.setInt(10, dto.getWnum());
			pstmt.setString(11, dto.getIp());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("글작성실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;

	}// create() end

	public LinkedList<BbsFreeDTO> search(String word, int start, int end) {
		LinkedList<BbsFreeDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT wno, wtitle, userid, ccode, wview, wdate, mdate ,wnum, windent");
			sql.append(" FROM bbsFree ");
			sql.append(" WHERE wtitle like '%" + word + "%' or userid like '%" + word + "%' or wcontent like '%" + word
					+ "%' "); // 제목, 작성자, 내용 검색
			sql.append(" ORDER BY wno DESC ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = make_list(rs, start, end);
			} // if end
		} catch (Exception e) {
			System.out.println("검색실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// search() end

	public LinkedList<BbsFreeDTO> list(int start, int end) {
		LinkedList<BbsFreeDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT wno, wtitle, userid, ccode, wview, wdate, mdate ,wnum, windent ");
			sql.append(" FROM bbsFree ");
			sql.append(" ORDER BY wno DESC ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = make_list(rs, start, end);

			} // if end
		} catch (Exception e) {
			System.out.println("목록 실패:" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;
	}// list() end

	private LinkedList<BbsFreeDTO> make_list(ResultSet rs, int start, int end) {
		try {
			ArrayList<BbsFreeDTO> temp = new ArrayList<>();
			do {
				BbsFreeDTO dto = new BbsFreeDTO();
				dto.setWno(rs.getInt("wno"));
				dto.setWtitle(rs.getString("wtitle"));
				dto.setUserid(rs.getString("userid"));
				dto.setCcode(setCode(rs.getString("ccode")));
				dto.setWview(rs.getInt("wview"));
				dto.setWdate(rs.getString("wdate"));
				dto.setMdate(rs.getString("mdate"));
				dto.setWnum(rs.getInt("wnum"));
				dto.setWindent(rs.getInt("windent"));
				temp.add(dto);
			} while (rs.next());
			LinkedList<BbsFreeDTO> temp2 = new LinkedList<>();
			
			int wind = 0;
			do {
				for (int i = 0; i < temp.size(); i++) {
					if (temp.get(i).getWindent() == wind) {
						if (wind == 0)
							temp2.add(temp.get(i));
						else {
							for (int k = 0; k < temp2.size(); k++) {
								if (temp.get(i).getWnum() == temp2.get(k).getWno()) {
									temp2.add(k + 1, temp.get(i));
									break;
								} // if end
							} // for end
						} // if end
					} // if end
				} // for end
				wind++;
			} while (temp.size() != temp2.size());
			LinkedList<BbsFreeDTO> list = new LinkedList<>();// 본글과 댓글을 순선대로 저장했으므로 페이지에 보여지는 만큼 추출하여 저장하기 공간
			for (int i = start - 1; i < end && i < temp.size(); i++) {// 페이지에 보여지는 글 번호 순서위치의 인덱스 부터 5개또는 마지막 글 까지 반복
				list.add(temp2.get(i));
			}
			return list;

		} catch (Exception e) {
			System.out.println("목록생성실패" + e);
		} finally {
			DBClose.close(con);
		} // end
		return null;
	}// make_list() end

	public BbsFreeDTO read(int wno) {
		BbsFreeDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(
					" SELECT wno, wtitle, wcontent, userid, ccode, filename, filesize, wview,windent,wdate, mdate  ");
			sql.append(" FROM bbsFree ");
			sql.append(" WHERE wno = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, wno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new BbsFreeDTO();
				dto.setWno(rs.getInt("wno"));
				dto.setWtitle(rs.getString("wtitle"));
				dto.setWcontent(rs.getString("wcontent"));
				dto.setUserid(rs.getString("userid"));
				dto.setCcode(setCode(rs.getString("ccode")));
				dto.setWindent(rs.getInt("windent"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getInt("filesize"));
				dto.setWview(rs.getInt("wview"));
				dto.setWdate(rs.getString("wdate"));
				dto.setMdate(rs.getString("mdate"));
			} // if end

		} catch (Exception e) {
			System.out.println("상세보기실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return dto;
	}// read() end

	public int delete(BbsFreeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM bbsFree");
			sql.append(" WHERE wno=? and wpasswd=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getWno());
			pstmt.setString(2, dto.getWpasswd());
			cnt = pstmt.executeUpdate();
			if (cnt != 0) { // ->자식글이 있는 상태에서 부모글만 지우면 무한루프.
				delete_child(dto.getWno()); // ->부모글이 지워지면 자식글도 같이 지워줘야함
			} // if end
		} catch (Exception e) {
			System.out.println("삭제실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end

	private void delete_child(int no) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT wno, wnum ");
		sql.append(" FROM bbsFree ");
		sql.append(" WHERE wnum=? ");
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		pstmt.setInt(1, no);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			delete_child(rs.getInt("wno")); // delete_childe()로 가장 하위의 글부터 찾아서 재귀로 동작함
			delete_go(rs.getInt("wno"));
		} // end
		return;
	}// delete_child() end

	private void delete_go(int no) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM bbsFree ");
		sql.append(" WHERE wno=? ");
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
	}// delete_go() end
		// 1)delete_child()로 자식글 찾아내고
		// 2)delete_go()가 찾은 글 삭제함
		// 3)delete_childe()로 가장 하위의 글부터 찾아서 재귀로 동작함
		// 4)본글 또는 댓글을 삭제해도 그 밑에 작성된 댓글이나 대댓글도 같이 삭제됨

	public int update(BbsFreeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE bbsFree ");
			sql.append(" SET wtitle=?, wcontent=?, ccode=?, filename=?, filesize=? ,mdate=sysdate");
			sql.append(" WHERE wno=? and wpasswd=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWtitle());
			pstmt.setString(2, dto.getWcontent());
			pstmt.setString(3, dto.getCcode());
			pstmt.setString(4, dto.getFilename());
			pstmt.setLong(5, dto.getFilesize());
			pstmt.setInt(6, dto.getWno());
			pstmt.setString(7, dto.getWpasswd());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("수정실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update end

	public boolean increment_view_count(int wno) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE bbsFree set wview= wview+1 where wno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, wno);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("조회수 증가 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return false;
	}// increment_view_count() end

	private String setCode(String ccode) {
		String code_val = "영어";
		switch (ccode) {
		case "HU001":
			code_val = "유머";
			break;
		case "ST001":
			code_val = "공부";
			break;
		case "RE001":
			code_val = "후기";
			break;
		case "LI001":
			code_val = "자격증";
			break;
		case "SH001":
			code_val = "자료공유";
			break;
		}// switch() end
		return code_val + "-" + ccode;
	}// setCode() end

	public LinkedList<BbsFreeDTO> list2(int start, int end) {
		LinkedList<BbsFreeDTO> pageList = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();

			sql.append(" SELECT AA.* ");
			sql.append(" FROM ( ");
			sql.append("        SELECT ROWNUM as RNUM, BB.* ");
			sql.append("        FROM ( ");
			// sql.append(" SELECT wno, wtitle "); 이 코드를 아래 같이 수정
			sql.append("               SELECT wno, wtitle, userid, ccode, wview, wdate, mdate, wnum, windent ");
			sql.append("               FROM BBSFREE ");
			sql.append("               ORDER BY wno DESC ");
			sql.append("             )BB ");
			sql.append("      ) AA ");
			sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			if (rs.next()) {

			} // if end

		} catch (Exception e) {
			System.out.println("페이징목록실패: " + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return pageList;
	}// list2() end

	public int totalRowCount() {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM BBSFREE ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			} // if end
		} catch (Exception e) {
			System.out.println("전체 행 갯수:" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	}// totalRowCount() end

}// class end
