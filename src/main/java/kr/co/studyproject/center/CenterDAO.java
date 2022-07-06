package kr.co.studyproject.center;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class CenterDAO {
	
	private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
	
    public CenterDAO() {
    	dbopen=new DBOpen();
    }//end
    
    public int create(CenterDTO dto) { //구인 테이블 생성
        int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" INSERT INTO center(wno, userid, wtitle, wcontent, wpasswd, wview) ");
            sql.append(" VALUES( Cen_seq.nextval, ?,?,?,?,? ) ");
            pstmt=con.prepareStatement(sql.toString());
            
            pstmt.setString(1, dto.getUserid());
            pstmt.setString(2, dto.getWtitle());
            pstmt.setString(3, dto.getWcontent());
            pstmt.setString(4, dto.getWpasswd());
            pstmt.setInt(5, dto.getWview());
            
            cnt=pstmt.executeUpdate();            
        }catch(Exception e){
            System.out.println("게시판 글쓰기 실패:" + e);
        }finally{
            DBClose.close(con, pstmt);
        }
        return cnt;
    }//create() end	
    
    public ArrayList<CenterDTO> list(){
		ArrayList<CenterDTO> list=null;
		try {
			
			con=dbopen.getConnection();//DB 연결
			
			sql=new StringBuilder();
			sql.append(" SELECT wno , wcode, wtitle, userid, wdate, wview ");
			sql.append(" FROM center");
			sql.append(" ORDER BY wno DESC ");
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<CenterDTO>();//전체 행을 저장
				do {
					CenterDTO dto=new CenterDTO();//커서가 가리키는 한줄 가져오기
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
			DBClose.close(con, pstmt, rs);
		}//end
		return list;
		
		}//list() end
    

       

    
	
}//class end
