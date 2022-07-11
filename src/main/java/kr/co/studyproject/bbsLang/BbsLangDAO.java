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
	 
    public LinkedList<BbsLangDTO> list(int start, int end) {
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
            	list=make_list(rs, start, end);
                
            }//if end
        }catch(Exception e){
            System.out.println("목록 실패:"+e);
         }finally{
            DBClose.close(con, pstmt, rs);
         }//end
         return list;	
    }//list() end
	    
    private LinkedList<BbsLangDTO> make_list(ResultSet rs,int start, int end){  //  본글과 답글의 순서 위아래 재배치
    	try {
    	ArrayList<BbsLangDTO> temp=new ArrayList<>();  // 임시로  전체 글 저장할 공간 생성(배열리스트 객체 생성)
	        do {  //DB에서 가져온 만큼  반복하기위한 반복문
	        	BbsLangDTO dto=new BbsLangDTO();  // DTO객체생성
	            dto.setWno(rs.getInt("wno"));
	            dto.setWtitle(rs.getString("wtitle"));
	            dto.setWdate(rs.getString("wdate"));
	            dto.setMdate(rs.getString("mdate"));
	            dto.setUserid(rs.getString("userid"));
	            dto.setLcode(setCode(rs.getString("lcode")));
	            dto.setWview(rs.getInt("wview"));
	            dto.setWnum(rs.getInt("wnum"));
	            dto.setWindent(rs.getInt("windent"));
	            temp.add(dto);  // DTO객체를 배열리스트에 저장  (  DB에서 조회한 순서대로 저장 - DB조회순서는  본글 댓글여기저기섞여있는상태)
	        }while(rs.next());
	        
        LinkedList<BbsLangDTO> temp2=new LinkedList<>(); // 본글위치에 댓글의위치를 넣어주기 위해 ArrayList가 아니라 LinkedList사용 - ArrayList보다 LinkedList가  삽입, 삭제에 유리
        int wind=0; // 댓글과 본글 구별 하기위한 값  - 0일때는 본글만 LinkedList에저장, 1부터는  본글에 맞는 댓글 찾아서 저장하기위한값
        do {// ArrayList에 저장된 갯수만큼   LinkedList에 저장 하기위한 반복
        	for(int i=0; i<temp.size(); i++) {// ArrayList 사이즈만큼 반복
        		if(temp.get(i).getWindent()== wind) { // ArrayList에 저장된 글이  본글인지 댓글인지 판단
        			if(wind==0)temp2.add(temp.get(i));// ArrayList에 저장된 글이 본글일경우 - wind가 0일때 저장하므로
        			else {// wind가  0 이 아니라면 - 0이 아닐경우는  LinkedList에  본글을 전부 저장한경우
            			for(int k=0; k<temp2.size(); k++) {// LinkedList 만큼 반복 ( 본글을 다 저장한경우이기에 반복문 가능
            				if(temp.get(i).getWnum() == temp2.get(k).getWno()) {// 현재 LinkedList에 저장된 글의 wno와 ArrayList의 wnum이 일치한다면  해당글의 댓글이다.
            					temp2.add(k+1,temp.get(i));// 해당 글의 댓글로서  현재글의 LinkedList 위치 뒤에 삽입하기위해 k+1
            					break;
            				}//if end
            			}//for end
        			}//if end
        		}//if end
        	}//for end
        	wind++; // 댓글의 깊이증가
        }while(temp.size() != temp2.size());
        LinkedList<BbsLangDTO> list=new LinkedList<>();// 본글과 댓글을 순선대로 저장했으므로  페이지에 보여지는 만큼 추출하여 저장하기 공간
        for(int i=start-1; i<end && i < temp.size(); i++) {// 페이지에 보여지는 글 번호 순서위치의 인덱스 부터 5개또는 마지막 글 까지 반복
        	list.add(temp2.get(i));
        }
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

	            System.out.println("조회수 증가 실패"+e);

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
    
    
    
    public LinkedList<BbsLangDTO> list2(int start, int end){
        LinkedList<BbsLangDTO> pageList=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
           
            sql.append(" SELECT AA.* ");
            sql.append(" FROM ( ");
            sql.append("        SELECT ROWNUM as RNUM, BB.* ");
            sql.append("        FROM ( ");
            //sql.append("               SELECT wno, wtitle "); 이 코드를 아래 같이 수정
            sql.append("               SELECT wno, wtitle, userid, lcode, wview, wdate, mdate ,wnum, windent ");
            sql.append("               FROM BBSLANG ");
            sql.append("               ORDER BY wno DESC ");
            sql.append("             )BB ");
            sql.append("      ) AA ");
            sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");           
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
           
            rs=pstmt.executeQuery();
            if(rs.next()) {
            	
            }//if end
           
        }catch(Exception e) {
            System.out.println("페이징목록실패: "+e);
        }finally{
            DBClose.close(con, pstmt, rs);
        }//end   
        return pageList;
    }//list2() end

 
    public int totalRowCount() {
        int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT COUNT(*) FROM BBSLANG ");
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