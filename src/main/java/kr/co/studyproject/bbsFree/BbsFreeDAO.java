package kr.co.studyproject.bbsFree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import kr.co.studyproject.bbsLang.BbsLangDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class BbsFreeDAO {

    private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public BbsFreeDAO() {
    	dbopen=new DBOpen();
    }//end
    
    public int create(BbsFreeDTO dto) {
        int cnt = 0;
        try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO bbsFree(wno, wtitle,wcontent, wpasswd, userid, ccode, filename, filesize, wview, windent, wnum, ip, wdate, mdate) ");
            sql.append(" VALUES( Free_seq.nextval, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?, sysdate, sysdate) "); 
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getWtitle());
            pstmt.setString(2, dto.getWcontent());
            pstmt.setString(3, dto.getWpasswd());
            pstmt.setString(4, dto.getUserid());
            pstmt.setString(5, dto.getCcode());
            pstmt.setString(6,  dto.getFilename());
            pstmt.setLong(7, dto.getFilesize());
            pstmt.setInt(8, dto.getWview()); 
            pstmt.setInt(9, dto.getWindent());
            pstmt.setInt(10, dto.getWnum());
            pstmt.setString(11, dto.getIp());
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("글작성실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    	
    }//create() end
    
   
    public ArrayList<BbsFreeDTO> search(String word) {
    	ArrayList<BbsFreeDTO> list=null;
    	try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wtitle, userid, ccode, wview, wdate, mdate ,wnum, windent");
            sql.append(" FROM bbsFree ");
            sql.append(" WHERE wtitle like '%"+word+"%' or userid like '%"+word+"%' or wcontent like '%"+word+"%' ");
            sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()) {
            	list=new ArrayList<>();
                do {
                    BbsFreeDTO dto=new BbsFreeDTO();
                    dto.setWno(rs.getInt("wno"));
                    dto.setWtitle(rs.getString("wtitle"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setCcode(setCode(rs.getString("ccode")));
                    dto.setWview(rs.getInt("wview"));
                    dto.setWdate(rs.getString("wdate"));
                    dto.setMdate(rs.getString("mdate"));
                    dto.setWnum(rs.getInt("wnum"));
                    dto.setWindent(rs.getInt("windent"));
                    list.add(dto);
                }while(rs.next());
                
            }//if end
    	} catch(Exception e) {
    		System.out.println("검색실패"+e);
	    } finally {
	           DBClose.close(con, pstmt, rs);
	    }//end
    	return list;
    }//search() end
    
    public LinkedList<BbsFreeDTO> list() {
    	LinkedList<BbsFreeDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wtitle, userid, ccode, wview, wdate, mdate ,wnum, windent ");
            sql.append(" FROM bbsFree ");
            sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()) {
            	list=make_list(rs);
                
            }//if end
        }catch(Exception e){
            System.out.println("목록 실패:"+e);
         }finally{
            DBClose.close(con, pstmt, rs);
         }//end
         return list;
    }//list() end
    
    private LinkedList<BbsFreeDTO> make_list(ResultSet rs){
    	try {
    	ArrayList<BbsFreeDTO> temp=new ArrayList<>();
        do {
            BbsFreeDTO dto=new BbsFreeDTO();
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
        }while(rs.next());
        LinkedList<BbsFreeDTO> list=new LinkedList<>();
        int wind=0;
        do {
        	for(int i=0; i<temp.size(); i++) {
        		if(temp.get(i).getWindent()== wind) {
        			if(wind==0)list.add(temp.get(i));
        			else {
            			for(int k=0; k<list.size(); k++) {
            				if(temp.get(i).getWnum() == list.get(k).getWno()) {
            					list.add(k+1,temp.get(i));
            					break;
	            				}//if end
	            			}//for end
	        			}//if end
	        		}//if end
	        	}//for end
	        	wind++;
	        }while(temp.size() != list.size());
        return list;
        
    	}catch(Exception e) {
    		System.out.println("목록생성실패"+e);
    	}finally{
    		DBClose.close(con);
    	}//end
    	return null;  //반환타입이 객체인 경우 반환문에 null값을 반환함
    }//make_list() end
    
    public BbsFreeDTO read(int wno) {
    	BbsFreeDTO dto = null;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" SELECT wno, wtitle, wcontent, userid, ccode, filename, filesize, wview,windent,wdate, mdate  ");
          sql.append(" FROM bbsFree ");
          sql.append(" WHERE wno = ? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, wno);
          rs = pstmt.executeQuery();
          if(rs.next()) {
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
          }//if end

        } catch (Exception e) {
            System.out.println("상세보기실패"+e);
            e.printStackTrace();
        } finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
    
    
    public int delete(BbsFreeDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" DELETE FROM bbsFree" );
          sql.append(" WHERE wno=? and wpasswd=?");  
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, dto.getWno());
          pstmt.setString(2, dto.getWpasswd());
          cnt = pstmt.executeUpdate();
          if(cnt!=0) {                         //->자식글이 있는 상태에서 부모글만 지우면 무한루프.
        	  delete_child(dto.getWno());	   //->부모글이 지워지면 자식글도 같이 지워줘야함
          }//if end							  
        } catch (Exception e) {
            System.out.println("삭제실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
    private void delete_child(int no) throws Exception {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" SELECT wno, wnum "); 
    	sql.append(" FROM bbsFree ");
    	sql.append(" WHERE wnum=? ");
    	PreparedStatement pstmt = con.prepareStatement(sql.toString()); 
    	pstmt.setInt(1, no);
    	ResultSet rs=pstmt.executeQuery(); 
    	
    	while(rs.next()) {
	    	delete_child(rs.getInt("wno")); //delete_childe()로 가장 하위의 글부터 찾아서 재귀로 동작함
	    	delete_go(rs.getInt("wno")); 		
    	}//end
    	return;
    }//delete_child() end
    
    private void delete_go(int no) throws Exception {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" DELETE FROM bbsFree ");		
    	sql.append(" WHERE wno=? ");
    	PreparedStatement pstmt = con.prepareStatement(sql.toString()); 
    	pstmt.setInt(1, no);
    	pstmt.executeUpdate();
    }//delete_go() end			
    							//1)delete_child()로 자식글 찾아내고
    							//2)delete_go()가 찾은 글 삭제함
    							//3)delete_childe()로 가장 하위의 글부터 찾아서 재귀로 동작함
    							//4)본글 또는 댓글을 삭제해도 그 밑에 작성된 댓글이나 대댓글도 같이 삭제됨
    
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
	          sql.append(" UPDATE bbsFree set wview= wview+1 where wno=?");
	          pstmt = con.prepareStatement(sql.toString());
   		 pstmt.setInt(1, wno);
   		 pstmt.executeUpdate();
   		 return true;
   	 }catch (Exception e) {
         System.out.println("조회수 출력 실패"+e);
      } finally {
          DBClose.close(con, pstmt);
      }//end
   	 	return false;
    }//increment_view_count() end
    
    private String setCode(String lcode) {
    	String code_val="영어";
    	switch(lcode) {
    	case "HU001" : code_val="유머";break;
    	case "ST001" : code_val="공부";break;
    	case "RE001" : code_val="후기";break;
    	case "LI001" : code_val="자격증";break;
    	case "SH001" : code_val="자료공유";break;
    	}//switch() end
    	return code_val+"-"+lcode;
    }// setCode() end
  

}//class end
