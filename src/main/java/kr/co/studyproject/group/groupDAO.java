package kr.co.studyproject.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.studyproject.Member.MemberDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class groupDAO {
	private DBOpen dbOpen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public groupDAO() {
		dbOpen=new DBOpen();
		}
	 
	 
	 
	 public int sgcreate(groupDTO dto) {
			int cnt=0;
			try {
				con = dbOpen.getConnection();
				
				sql=new StringBuilder();
				
				//여기서 sgno는 시퀀스로 입력되기 때문에
				//groupCont.java에서 Integer.parseInt(request.getParameter("sgno")); 이부분은 필요X
				sql.append(" INSERT INTO studyg(sgno, sgname, sgleader, sgintro, sgmaxuserno, sgselectlang, sgdate) ");
				sql.append(" VALUES(studyg_seq.nextval,?,?,?,?,?,sysdate) ");
				
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getSgname());
				pstmt.setString(2, dto.getSgleader());
				pstmt.setString(3, dto.getSgintro());
				pstmt.setInt(4, dto.getSgmaxuserno());
				pstmt.setString(5, dto.getSgselectlang());
				//pstmt.setString(6, dto.getSgdate()); ->여기는 sysdate 입력 
				
				cnt=pstmt.executeUpdate();
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					cnt=rs.getInt("cnt");
				}//if end
				
			} catch (Exception e) {
				System.out.println("스터디그룹 생성 실패 : " + e);
			}finally {
				DBClose.close(con, pstmt);
			}//end
			return cnt; 
			
		}//create() end
	 
	
	
}