package kr.co.studyproject.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class groupDAO {
	private DBOpen dbOpen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	public groupDAO() {
		dbOpen = new DBOpen();
	}

	public int sgcreate(groupDTO dto) {
		int cnt = 0;
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();

			// studyg의 pk 갑이 studyp의 fk로 들어가야되기 때문에 시퀀스 먼저 들고오기
			sql.append("SELECT studyg_seq.nextval AS sgno FROM DUAL");
			String sgno = "";
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sgno = rs.getString("sgno");
			}
			
			sql = new StringBuilder();
			
			// studyg insert
			// 여기서 sgno는 시퀀스로 입력되기 때문에
			// groupCont.java에서 Integer.parseInt(request.getParameter("sgno")); 이부분은 필요X
			sql.append(" INSERT INTO studyg(sgno, sgname, sgleader, sgintro, sgmaxuserno, sgselectlang, sgdate) ");
			sql.append(" VALUES(?,?,?,?,?,?,sysdate) ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, sgno);
			pstmt.setString(2, dto.getSgname());
			pstmt.setString(3, dto.getSgleader());
			pstmt.setString(4, dto.getSgintro());
			pstmt.setInt(5, dto.getSgmaxuserno());
			pstmt.setString(6, dto.getSgselectlang());
			// pstmt.setString(6, dto.getSgdate()); ->여기는 sysdate 입력

			cnt = pstmt.executeUpdate();
			
			sql = new StringBuilder();

			// studyp insert
			sql.append(" INSERT INTO studyp(SGNO, USERID, SGJOIND, SGLEVEL) ");
			sql.append(" VALUES(?, ?, sysdate, 'L') ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, sgno);
			pstmt.setString(2, dto.getSgleader());
			
			pstmt.executeUpdate();
			
			/*
			 * rs=pstmt.executeQuery(); if(rs.next()) { cnt=rs.getInt("cnt"); }
			 */// if end

		} catch (Exception e) {
			System.out.println("스터디그룹 생성 실패 : " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;

	}// create() end

	public ArrayList<groupDTO> myGroupList(String userId) {
		ArrayList<groupDTO> list = null;
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();
			sql.append(" select * from studyp P INNER JOIN STUDYG G ON (P.SGNO = G.SGNO)");
			sql.append(" WHERE P.USERID = ? ORDER BY P.SGNO DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<>();
				do {
					groupDTO dto = new groupDTO();
					dto.setSgno(rs.getInt("sgno"));
					dto.setSgname(rs.getString("sgname"));
					dto.setSgleader(rs.getString("sgleader"));
					dto.setSgintro(rs.getString("sgintro"));
					dto.setSgmaxuserno(rs.getInt("sgmaxuserno"));
					dto.setSgselectlang(rs.getString("sgselectlang"));
					dto.setSgdate(rs.getString("sgdate"));
					dto.setSglevel(rs.getString("sglevel"));

					list.add(dto);
				} while (rs.next());
			}

		} catch (Exception e) {

		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public ArrayList<groupDTO> groupList(String userId) {
		ArrayList<groupDTO> list = null;
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();
			sql.append(" SELECT * FROM STUDYG");
			sql.append(" WHERE SGLEADER != ? ORDER BY SGNO DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<>();
				do {
					groupDTO dto = new groupDTO();
					dto.setSgno(rs.getInt("sgno"));
					dto.setSgname(rs.getString("sgname"));
					dto.setSgleader(rs.getString("sgleader"));
					dto.setSgintro(rs.getString("sgintro"));
					dto.setSgmaxuserno(rs.getInt("sgmaxuserno"));
					dto.setSgselectlang(rs.getString("sgselectlang"));
					dto.setSgdate(rs.getString("sgdate"));

					list.add(dto);
				} while (rs.next());
			}

		} catch (Exception e) {

		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public groupDTO groupDetail(int sgno) {
		groupDTO dto = new groupDTO();
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();
			sql.append(" SELECT * FROM STUDYG");
			sql.append(" WHERE SGNO = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sgno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setSgno(rs.getInt("sgno"));
				dto.setSgname(rs.getString("sgname"));
				dto.setSgleader(rs.getString("sgleader"));
				dto.setSgintro(rs.getString("sgintro"));
				dto.setSgmaxuserno(rs.getInt("sgmaxuserno"));
				dto.setSgselectlang(rs.getString("sgselectlang"));
				dto.setSgdate(rs.getString("sgdate"));
			}

		} catch (Exception e) {

		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return dto;
	}
	
	public int groupJoin(int sgno, String userId) {
		int cnt = 0;
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();
			
			// studyp insert
			sql.append(" INSERT INTO studyp(SGNO, USERID, SGJOIND, SGLEVEL) ");
			sql.append(" VALUES(?, ?, sysdate, 'W') ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sgno);
			pstmt.setString(2, userId);
			
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("스터디그룹 신청 실패 : " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;

	}
	
	public ArrayList<groupDTO> myGroupTeamMembers(int sgno) {
		ArrayList<groupDTO> list = null;
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();
			sql.append(" SELECT * FROM STUDYP");
			sql.append(" WHERE SGNO = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sgno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<>();
				do {
					groupDTO dto = new groupDTO();
					dto.setSgno(rs.getInt("sgno"));
					dto.setUserid(rs.getString("userid"));
					dto.setSglevel(rs.getString("sglevel"));
					dto.setSgjoind(rs.getString("sgjoind"));			

					list.add(dto);
				} while (rs.next());
			}

		} catch (Exception e) {

		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public int groupConfirm(int sgno, String userId) {
		int cnt = 0;
		try {
			con = dbOpen.getConnection();

			sql = new StringBuilder();
			
			// studyp insert
			sql.append(" UPDATE STUDYP SET SGLEVEL = 'T' ");
			sql.append(" WHERE SGNO = ? AND USERID = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, sgno);
			pstmt.setString(2, userId);
			
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("스터디그룹 승인 실패 : " + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;

	}

}