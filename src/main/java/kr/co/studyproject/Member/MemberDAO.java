package kr.co.studyproject.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.utility.DBClose;
import net.utility.DBOpen;

public class MemberDAO {
	//실질적으로 데이터베이스에서 회원정보를 불러오거나 넣으려할때 사용
	private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
	
    public MemberDAO() {
    	dbopen=new DBOpen();
    }//end
    
    
    public String loginProc(MemberDTO dto) {//등급을 리턴해야하므로 리턴값 String
		String ulevel=null; //로그인(아이디비번일치)하면 회원등급 가져오고 로그인 성공
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT ulevel ");
			sql.append(" FROM member "); //테이블명 tb_user에서 member로 변경
			sql.append(" WHERE userid=? and upw=? ");
			sql.append(" AND ulevel in ('MASTER','A','B','C') "); //회원등급기준 탈퇴회원은 로그인 못하도록
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUpw());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				ulevel=rs.getString("ulevel");
			}//if end
			
		} catch (Exception e) {
			System.out.println("로그인 실패 : " + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return ulevel;
	}//loginProc() end
    
    
    
    // 아이디, 비밀번호 찾기
    // 이름과 이메일이 일치하면 메일로 서버에 등록된 아이디와 임시비밀번호 발급
    public void findID() {}//
    
    
    
    
    
	
}//class end
