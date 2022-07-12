package kr.co.studyproject.center;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import kr.co.studyproject.bbsLang.BbsLangDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class CenterDAO {
	 private DBOpen dbopen=null;
	 private Connection con=null;
	 private PreparedStatement pstmt=null;
	 private ResultSet rs=null;
	 private StringBuilder sql=null;
	 
	 public CenterDAO() {
		 dbopen = new DBOpen();
	 }// end
	 
	 
	 
	 //새글쓰기와 댓글쓰기가 동시에 같이 사용할수 있는 함수
	 //새글쓰기:부모굴들여쓰기0, 
	 //댓글쓰기:자식글들여쓰기1, 그룹번호가 있어야 함
	 public int create(CenterDTO dto) {
		 
		 
		 
	        int cnt = 0;
	        try {
	            con = dbopen.getConnection();
	            sql = new StringBuilder();
	            if(dto.getWindent()==1) {//자식글인가요? 즉, 댓글쓰기
		            sql.append(" INSERT INTO center(wno, wtitle,wcontent,wpasswd, userid, wcode, wview, windent, wnum, ip, wdate, mdate) ");
		            sql.append(" VALUES( cen_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate) "); 
		            pstmt = con.prepareStatement(sql.toString());
		            pstmt.setString(1, dto.getWtitle());
		            pstmt.setString(2, dto.getWcontent());
		            pstmt.setString(3, dto.getWpasswd());
		            pstmt.setString(4, dto.getUserid());
		            pstmt.setString(5, dto.getWcode());
		            pstmt.setInt(6, dto.getWview()); 
		            pstmt.setInt(7, dto.getWindent());
		            pstmt.setInt(8, dto.getWnum()); 
		            pstmt.setString(9, dto.getIp());
		            
	            }else {//최초부모글. 즉 새글쓰기
	            	
		            sql.append(" INSERT INTO center(wno, wtitle,wcontent,wpasswd, userid, wcode, wview, windent, wnum, ip, wdate, mdate) ");
		            sql.append(" VALUES( cen_seq.nextval, ?, ?, ?, ?, ?, ?, ?, (SELECT NVL(MAX(wno),0)+1 FROM center), ?, sysdate, sysdate) "); 
		            pstmt = con.prepareStatement(sql.toString());
		            pstmt.setString(1, dto.getWtitle());
		            pstmt.setString(2, dto.getWcontent());
		            pstmt.setString(3, dto.getWpasswd());
		            pstmt.setString(4, dto.getUserid());
		            pstmt.setString(5, dto.getWcode());
		            pstmt.setInt(6, dto.getWview()); 
		            pstmt.setInt(7, dto.getWindent());
		            pstmt.setString(8, dto.getIp());
	            }//if end
	            
	            
	            
	            
	            cnt = pstmt.executeUpdate();
	        } catch (Exception e) {
	            System.out.println("글작성실패"+e);
	        } finally {
	            DBClose.close(con, pstmt);
	        }//end
	        return cnt;
	    	
	    }//create() end
	    
	 public LinkedList<CenterDTO> search(String word, int start, int end) {
		 LinkedList<CenterDTO> list=null;
		 try {
	            con=dbopen.getConnection();
	            sql=new StringBuilder();
	            sql.append(" SELECT wno, wtitle, userid, wcode, wview, wdate, mdate ,wnum, windent");
	            sql.append(" FROM center ");
	            sql.append(" WHERE wtitle like '%"+word+"%' or userid like '%"+word+"%' or wcontent like '%"+word+"%' "); // 제목, 작성자, 내용 검색
	            sql.append(" ORDER BY wno DESC ");
	            pstmt=con.prepareStatement(sql.toString());
	            rs=pstmt.executeQuery();
	           
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
	 
    public LinkedList<CenterDTO> list(int start, int end) {
    	LinkedList<CenterDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wtitle, userid, wcode, wview, wdate, mdate ,wnum, windent");
            sql.append(" FROM center ");
            sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()) {
            	list=make_list(rs, start, end);
                
            }//if end
        }catch(Exception e){
            System.out.println("목록 실패:"+e);
         }finally{
            DBClose.close(con, pstmt, rs);
         }//end
         return list;	
    }//list() end
	    
    private LinkedList<CenterDTO> make_list(ResultSet rs, int start, int end){  //  본글과 답글의 순서 위아래 재배치
    	try {
    	ArrayList<CenterDTO> temp=new ArrayList<>();
	        do {
	        	CenterDTO dto=new CenterDTO();
	            dto.setWno(rs.getInt("wno"));
	            dto.setWtitle(rs.getString("wtitle"));
	            dto.setWdate(rs.getString("wdate"));
	            dto.setMdate(rs.getString("mdate"));
	            dto.setUserid(rs.getString("userid"));
	            dto.setWcode(setCode(rs.getString("wcode")));
	            dto.setWview(rs.getInt("wview"));
	            dto.setWnum(rs.getInt("wnum"));
	            dto.setWindent(rs.getInt("windent"));
	            temp.add(dto);
	        }while(rs.next());
	        
        LinkedList<CenterDTO> temp2=new LinkedList<>();
        int wind = 0; 
		do {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getWindent() == wind) {
					if (wind == 0)
						temp2.add(temp.get(i));
					else {
						for (int k = 0; k < temp2.size(); k++) {
							if (temp.get(i).getWnum() == temp2.get(k).getWno()) {// 현재 LinkedList에 저장된 글의 wno와
																					// ArrayList의 wnum이 일치한다면 해당글의
																					// 댓글이다.
								temp2.add(k + 1, temp.get(i));// 해당 글의 댓글로서 현재글의 LinkedList 위치 뒤에 삽입하기위해 k+1
								break;
            				}//if end
            			}//for end
        			}//if end
        		}//if end
        	}//for end
			wind++; // 댓글의 깊이증가
		} while (temp.size() != temp2.size());
		LinkedList<CenterDTO> list = new LinkedList<>();// 본글과 댓글을 순선대로 저장했으므로 페이지에 보여지는 만큼 추출하여 저장하기 공간
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
	    
    public CenterDTO read(int wno) {
    	CenterDTO dto = null;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" SELECT wno, wtitle, wcontent, userid, wcode, wview, windent,wdate, mdate, wnum ");   //wnum추가 컨트롤러에 read.Wnum
          sql.append(" FROM center ");
          sql.append(" WHERE wno = ? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, wno);
          rs = pstmt.executeQuery();
          if(rs.next()) {
            dto = new CenterDTO();
            dto.setWno(rs.getInt("wno"));
            dto.setWtitle(rs.getString("wtitle"));
            dto.setWcontent(rs.getString("wcontent"));
            dto.setUserid(rs.getString("userid"));
            dto.setWindent(rs.getInt("windent"));
            dto.setWcode(setCode(rs.getString("wcode")));
            dto.setWview(rs.getInt("wview"));
            dto.setWdate(rs.getString("wdate"));
            dto.setMdate(rs.getString("mdate"));
            dto.setWnum(rs.getInt("wnum"));
          }//if end

        } catch (Exception e) {
            System.out.println("상세보기실패"+e);
        } finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
	    
	    
    public int delete(CenterDTO dto) {
    	int cnt=0;
    	
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" DELETE FROM center" );
          sql.append(" WHERE wno=? and wpasswd=? ");  
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, dto.getWno());
          pstmt.setString(2, dto.getWpasswd());
          cnt = pstmt.executeUpdate();
          
          if(cnt!=0) {
        	  delete_child(dto.getWno());
          }//if end
        } catch (Exception e) {
            System.out.println("삭제실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
    
    private void delete_child(int no) throws SQLException {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" SELECT wno, wnum "); 
    	sql.append(" FROM center ");
    	sql.append(" WHERE wnum=? ");
    	PreparedStatement pstmt = con.prepareStatement(sql.toString()); 
    	pstmt.setInt(1, no);
    	ResultSet rs=pstmt.executeQuery(); 
    	while(rs.next()) {
    		delete_child(rs.getInt("wno")); 
    		delete_go(rs.getInt("wno")); 
    	}//end
    	return;
    }//delete_child() end
    
    private void delete_go(int no) throws SQLException {
    	 StringBuilder sql = new StringBuilder();
    	 sql.append(" DELETE FROM center "); 
    	 sql.append(" WHERE wno=? ");
    	 PreparedStatement pstmt = con.prepareStatement(sql.toString()); 
    	 pstmt.setInt(1, no);
    	 pstmt.executeUpdate();
    }//delete_go() end
    
    
    public int update(CenterDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" UPDATE center ");
          sql.append(" SET wtitle=?, wcontent=?, wcode=?,  mdate=sysdate");
          sql.append(" WHERE wno=? and wpasswd=? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setString(1, dto.getWtitle());
          pstmt.setString(2, dto.getWcontent());
          pstmt.setString(3, dto.getWcode());
          pstmt.setInt(4, dto.getWno());
          pstmt.setString(5, dto.getWpasswd());
          cnt = pstmt.executeUpdate();
        } catch (Exception e) {
           System.out.println("수정실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//update end
    
     public boolean increment_view_count(int wno) {
    	 try {
    		 con = dbopen.getConnection();
	          sql = new StringBuilder();
	          sql.append(" UPDATE center set wview= wview+1 where wno=?");
	          pstmt = con.prepareStatement(sql.toString());
    		 pstmt.setInt(1, wno);
    		 pstmt.executeUpdate();
    		 return true;
    	 }catch (Exception e) {
	            System.out.println("조회수 증가 실패"+e);
         }finally {
            DBClose.close(con, pstmt);
         }//end
    	 return false;
     }//increment_view_count() end
     
    private String setCode(String wcode) {
    	String code_val="욕설 및 공격적 언행 신고";
    	switch(wcode) {
    	case "BB" : code_val="비매너 신고";break;
    	case "CC" : code_val="성희롱 신고";break;
    	case "DD" : code_val="다른 문제가 있어요";break;
    	
    	}//switch() end
    	return code_val+"-"+wcode;
    }//setCode() end
    
    public LinkedList<CenterDTO> list2(int start, int end){
    	LinkedList<CenterDTO> pageList=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
           
            sql.append(" SELECT AA.* ");
            sql.append(" FROM ( ");
            sql.append("        SELECT ROWNUM as RNUM, BB.* ");
            sql.append("        FROM ( ");
            //sql.append("               SELECT wno, wtitle "); 이 코드를 아래 같이 수정했습니다
            sql.append("               SELECT wno, wtitle, userid, wcode, wview, wdate, wnum, windent ");
            sql.append("               FROM CENTER ");
            sql.append("               ORDER BY wnum DESC ");
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
        int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT COUNT(*) FROM CENTER ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()){
                cnt=rs.getInt(1);            
            }//if end          
        }catch(Exception e){
            System.out.println("전체 행 갯수:" + e);
        }finally{
            DBClose.close(con, pstmt);
        }
        return cnt;
    }//totalRowCount() end
	    
}//class end