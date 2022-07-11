package kr.co.studyproject.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.studyproject.Member.MemberDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class groupDAO2 {
	private DBOpen dbOpen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public groupDAO2() {
		dbOpen=new DBOpen();
		
	}
	 public int create(groupDTO dto) { //구인 테이블 생성
	        int cnt=0;
	        try {
	            con=dbOpen.getConnection();
	            sql=new StringBuilder();
	            sql.append(" INSERT INTO studygroup(wno, wid, title, wdate, wcontent, wviewcount ) ");
	            sql.append(" VALUES( studygroup_seq.nextval, ? ) ");
	            pstmt=con.prepareStatement(sql.toString());
	            pstmt.setInt(1, dto.getWno());
	            pstmt.setString(2, dto.getWid());
	            pstmt.setString(3, dto.getTitle());
	            pstmt.setString(4, dto.getWdate());
	            pstmt.setString(5, dto.getWcontent());
	            pstmt.setInt(6, dto.getWviewcount());
	            cnt=pstmt.executeUpdate();            
	        }catch(Exception e){
	            System.out.println("팀 구인 등록실패:" + e);
	        }finally{
	            DBClose.close(con, pstmt);
	        }
	        return cnt;
	    }//create() end
	 
	public ArrayList<groupDTO> list(){ //구인 테이블 리스트 
		ArrayList<groupDTO> list=null;
        try {
            con=dbOpen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wid, title, wdate, wcontent, wviewcount");
            sql.append(" FROM studygroup ");
            sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<groupDTO>();
                do {
                    groupDTO dto=new groupDTO();
                    dto.setWno(rs.getInt("wno"));
                    dto.setWid(rs.getString("wid"));
                    dto.setTitle(rs.getString("title"));
                    dto.setWdate(rs.getString("wdate"));
                    dto.setWcontent(rs.getString("wcontent"));  
                    dto.setWviewcount(rs.getInt("wviewcount"));
                    list.add(dto); //list 저장
                }while(rs.next());
            }//if end
        }catch(Exception e){
            System.out.println("studygroup 목록 실패:" +e);
         }finally{
            DBClose.close(con, pstmt, rs);
         }//리스트 end
         return list;
	}
	public groupDTO read(int wno) {
        groupDTO dto=null;
        try {
            con=dbOpen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wid, title, wdate, wcontent, wviewcount ");
            sql.append(" FROM studygroup ");
            sql.append(" WHERE wno = ? ");
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, wno);
            
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto=new groupDTO();
                dto.setWno(rs.getInt("wno"));
                dto.setWid(rs.getString("wid"));
                dto.setTitle(rs.getString("title"));
                dto.setWdate(rs.getString("wdate"));
                dto.setWcontent(rs.getString("wcontent"));
                dto.setWviewcount(rs.getInt("wviewcount"));
                
            }//if end            
        }catch(Exception e) {
            System.out.println("스터디그룹 상세보기 실패: "+e);
        }finally{
            DBClose.close(con, pstmt, rs);
        }//end    
        return dto;
    }//read() end
	
	 public int delete(int wno) {
	        int cnt = 0;
	        try {
	          con = dbOpen.getConnection();
	          sql = new StringBuilder();
	          sql.append(" DELETE FROM studygroup" );
	          sql.append(" WHERE wno=? ");  
	          pstmt = con.prepareStatement(sql.toString());
	          pstmt.setInt(1, wno);
	          cnt = pstmt.executeUpdate();
	        } catch (Exception e) {
	            System.out.println("삭제실패"+e);
	        } finally {
	            DBClose.close(con, pstmt);
	        }//end
	        return cnt;
	    }//delete() end
	 
	 
	 public int update(groupDTO dto) {
	        int cnt=0;
	        try{
	            con = dbOpen.getConnection();
	            sql = new StringBuilder();
	            sql.append(" UPDATE wno ");
	            sql.append(" SET title = ? ");
	            sql.append(" WHERE wno = ?");
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, dto.getTitle());
	            pstmt.setInt(2, dto.getWno());
	            cnt = pstmt.executeUpdate();
	        }catch(Exception e){
	            System.out.println("스터디 그룹 수정실패: " + e);
	        }finally{
	            DBClose.close(con, pstmt);
	        }//end
	        return cnt;
	    }//update() end
	 
	 
	 
	 
	 public int sgcreate(groupDTO dto) {
			int cnt=0;
			try {
				con = dbOpen.getConnection();
				
				sql=new StringBuilder();
				sql.append(" INSERT INTO member(userid, ulevel, upw, uname, email, phnum, introd) ");
				sql.append(" VALUES(?,'C',?,?,?,?,?) ");
				
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getUserid());
				//pstmt.setString(2, dto.getUlevel()); 이부분은 입력값이 아니라 무조건 'C'가 입력되는 값이라 없어도 되는 부분입니다
				//부적합한 열 인덱스 ->물음표의 갯수와 순서가 맞지 않아서 나오는 에러메세지 입니다
				pstmt.setString(2, dto.getUpw());
				pstmt.setString(3, dto.getUname());
				pstmt.setString(4, dto.getEmail());
				pstmt.setString(5, dto.getPhnum());
				pstmt.setString(6, dto.getIntrod()); //
				
				cnt=pstmt.executeUpdate();
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					cnt=rs.getInt("cnt");
				}//if end
				
			} catch (Exception e) {
				System.out.println("회원가입 실패 : " + e);
			}finally {
				DBClose.close(con, pstmt);
			}//end
			return cnt; 
			
		}//create() end
	 
	
	
}