package kr.co.studyproject.bbsLang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class BbsLangDAO {
	 private DBOpen dbopen=null;
	 private Connection con=null;
	 private PreparedStatement pstmt=null;
	 private ResultSet rs=null;
	 private StringBuilder sql=null;
	 
	 public BbsLangDAO() {
		 dbopen = new DBOpen();
	 }// end
	 
	 public int create(BbsLangDTO dto) {
	        int cnt = 0;
	        try {
	            con = dbopen.getConnection();
	            sql = new StringBuilder();
	            sql.append(" INSERT INTO bbsLang(wno, wtitle,wcontent,wpasswd, userid, lcode, wview, windent, wnum, ip, wdate, mdate) ");
	            sql.append(" VALUES( Lang_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate) "); 
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, dto.getWtitle());
	            pstmt.setString(2, dto.getWcontent());
	            pstmt.setString(3, dto.getWpasswd());
	            pstmt.setString(4, dto.getUserid());
	            pstmt.setString(5, dto.getLcode());
	            pstmt.setInt(6, dto.getWview()); 
	            pstmt.setInt(7, dto.getWindent());
	            pstmt.setInt(8, dto.getWnum());
	            pstmt.setString(9, dto.getIp());
	            cnt = pstmt.executeUpdate();
	        } catch (Exception e) {
	            System.out.println("글작성실패"+e);
	        } finally {
	            DBClose.close(con, pstmt);
	        }//end
	        return cnt;
	    	
	    }//create() end
	    
	 public ArrayList<BbsLangDTO> search(String word) {
		 ArrayList<BbsLangDTO> list=null;
		 try {
	            con=dbopen.getConnection();
	            sql=new StringBuilder();
	            sql.append(" SELECT wno, wtitle, userid, lcode, wview, wdate, mdate ,wnum, windent");
	            sql.append(" FROM bbsLang ");
	            sql.append(" WHERE wtitle like '%"+word+"%' or userid like '%"+word+"%' or wcontent like '%"+word+"%' "); // 제목, 작성자, 내용 검색
	            sql.append(" ORDER BY wno DESC ");
	            pstmt=con.prepareStatement(sql.toString());
	            rs=pstmt.executeQuery();
	           
	            if(rs.next()) {
	            	list=new ArrayList<>();
	            	 do {
		            	BbsLangDTO dto=new BbsLangDTO();
		                dto.setWno(rs.getInt("wno"));
		                dto.setWtitle(rs.getString("wtitle"));
		                dto.setWdate(rs.getString("wdate"));
		                dto.setMdate(rs.getString("mdate"));
		                dto.setUserid(rs.getString("userid"));
		                dto.setLcode(setCode(rs.getString("lcode")));
		                dto.setWview(rs.getInt("wview"));
		                dto.setWnum(rs.getInt("wnum"));
		                dto.setWindent(rs.getInt("windent"));
		                list.add(dto);
		            	}while(rs.next());
		            }//if end
		 } catch (Exception e) {
	            System.out.println("검색실패"+e);
	        } finally {
	            DBClose.close(con, pstmt, rs);
	        }//end
		 return list;
	 }//search() end
	 
    public LinkedList<BbsLangDTO> list() {
    	LinkedList<BbsLangDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wtitle, userid, lcode, wview, wdate, mdate ,wnum, windent");
            sql.append(" FROM bbsLang ");
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
	    
    private LinkedList<BbsLangDTO> make_list(ResultSet rs){  //  본글과 답글의 순서 위아래 재배치
    	try {
    	ArrayList<BbsLangDTO> temp=new ArrayList<>();
	        do {
	        	BbsLangDTO dto=new BbsLangDTO();
	            dto.setWno(rs.getInt("wno"));
	            dto.setWtitle(rs.getString("wtitle"));
	            dto.setWdate(rs.getString("wdate"));
	            dto.setMdate(rs.getString("mdate"));
	            dto.setUserid(rs.getString("userid"));
	            dto.setLcode(setCode(rs.getString("lcode")));
	            dto.setWview(rs.getInt("wview"));
	            dto.setWnum(rs.getInt("wnum"));
	            dto.setWindent(rs.getInt("windent"));
	            temp.add(dto);
	        }while(rs.next());
	        
        LinkedList<BbsLangDTO> list=new LinkedList<>();
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
        
    	}catch (Exception e) {
            System.out.println("목록생성실패"+e);
        } finally {
            DBClose.close(con);
        }//end
    	return null;
    }//make_list() end
	    
    public BbsLangDTO read(int wno) {
    	BbsLangDTO dto = null;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" SELECT wno, wtitle, wcontent, userid, lcode, wview, windent,wdate, mdate ");
          sql.append(" FROM bbsLang ");
          sql.append(" WHERE wno = ? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, wno);
          rs = pstmt.executeQuery();
          if(rs.next()) {
            dto = new BbsLangDTO();
            dto.setWno(rs.getInt("wno"));
            dto.setWtitle(rs.getString("wtitle"));
            dto.setWcontent(rs.getString("wcontent"));
            dto.setUserid(rs.getString("userid"));
            dto.setWindent(rs.getInt("windent"));
            dto.setLcode(setCode(rs.getString("lcode")));
            dto.setWview(rs.getInt("wview"));
            dto.setWdate(rs.getString("wdate"));
            dto.setMdate(rs.getString("mdate"));
          }//if end

        } catch (Exception e) {
            System.out.println("상세보기실패"+e);
        } finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
	    
	    
    public int delete(BbsLangDTO dto) {
    	int cnt=0;
    	
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" DELETE FROM bbsLang" );
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
    	sql.append(" FROM bbsLang ");
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
    	 sql.append(" DELETE FROM bbsLang "); 
    	 sql.append(" WHERE wno=? ");
    	 PreparedStatement pstmt = con.prepareStatement(sql.toString()); 
    	 pstmt.setInt(1, no);
    	 pstmt.executeUpdate();
    }//delete_go() end
    
    
    public int update(BbsLangDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" UPDATE bbsLang ");
          sql.append(" SET wtitle=?, wcontent=?, lcode=?,  mdate=sysdate");
          sql.append(" WHERE wno=? and wpasswd=? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setString(1, dto.getWtitle());
          pstmt.setString(2, dto.getWcontent());
          pstmt.setString(3, dto.getLcode());
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
	          sql.append(" UPDATE bbsLang set wview= wview+1 where wno=?");
	          pstmt = con.prepareStatement(sql.toString());
    		 pstmt.setInt(1, wno);
    		 pstmt.executeUpdate();
    		 return true;
    	 }catch (Exception e) {
	            System.out.println("조회수 출력 실패"+e);
         }finally {
            DBClose.close(con, pstmt);
         }//end
    	 return false;
     }//increment_view_count() end
     
    private String setCode(String lcode) {
    	String code_val="영어";
    	switch(lcode) {
    	case "TOE001" : code_val="토익";break;
    	case "TOF001" : code_val="토플";break;
    	case "JAP001" : code_val="일본어";break;
    	case "JLP001" : code_val="일본어자격증";break;
    	case "CHI001" : code_val="중국어";break;
    	case "HSK001" : code_val="중국어자격증";break;
    	case "STU001" : code_val="기타";
    	}//switch() end
    	return code_val+"-"+lcode;
    }//setCode() end
	    
}//class end
