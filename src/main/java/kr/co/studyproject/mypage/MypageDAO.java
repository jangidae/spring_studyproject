package kr.co.studyproject.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class MypageDAO {
	
	private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
	
    public MypageDAO() {
    	dbopen=new DBOpen();
    }//end
    
    public ArrayList<MypageDTO> list(){
		ArrayList<MypageDTO> list=null;
		try {
			
			con=dbopen.getConnection();//DB 연결
			
			sql=new StringBuilder();
			sql.append(" SELECT wno , wcode, wtitle, userid, wdate, wview ");
			sql.append(" FROM tb_center");
			sql.append(" ORDER BY wno DESC ");
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<MypageDTO>();//전체 행을 저장
				do {
					MypageDTO dto=new MypageDTO();//커서가 가리키는 한줄 가져오기
					dto.setWno(rs.getInt("wno"));
					dto.setWcode(rs.getString("wcode"));
					dto.setWtitle(rs.getString("wtitle"));
					dto.setUserid(rs.getString("userid"));
					dto.setWdate(rs.getString("wdate"));
					dto.setWview(rs.getInt("wview"));
					list.add(dto); //list저장
				}while(rs.next());
			}else {
				list=null;
			}// end
	
		}catch (Exception e) {
			System.out.println("전체목록실패 :" + e);
		}finally {
			DBClose.close(con);
		}//end
		return list;
		}//list() end
    

    
	
}//class end
