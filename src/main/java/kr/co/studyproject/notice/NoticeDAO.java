package kr.co.studyproject.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class NoticeDAO {

    private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public NoticeDAO() {
    	dbopen=new DBOpen();
    }//end
    
    public int create(NoticeDTO dto) {
        int cnt = 0;
        try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO tb_notice(wno, wtitle, wcontent, userid, ncode, poster,filename, filesize, wview) ");
            sql.append(" VALUES( tb_notice_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)"); 
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getWtitle());
            pstmt.setString(2, dto.getWcontent());
            pstmt.setString(3, dto.getUserid());
            pstmt.setString(4, dto.getNcode());
            pstmt.setString(5, dto.getPoster());
            pstmt.setString(6, dto.getFilename());
            pstmt.setInt(7, dto.getFilesize());
            pstmt.setInt(8, dto.getWview()); 

            
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("글작성실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    	
    }//create() end
    
    
    public ArrayList<NoticeDTO> list() {
        ArrayList<NoticeDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wtitle, userid, ncode, wview, wdate ");
            sql.append(" FROM tb_notice ");
            sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<>();
                do {
                    NoticeDTO dto=new NoticeDTO();
                    dto.setWno(rs.getInt("wno"));
                    dto.setWtitle(rs.getString("wtitle")); 
                    dto.setUserid(rs.getString("userid"));
                    dto.setNcode(rs.getString("ncode"));
                    dto.setWview(rs.getInt("wview"));
                    dto.setWdate(rs.getString("wdate"));
                
                    list.add(dto);
                    
                }while(rs.next());
            }//if end
        }catch(Exception e){
            System.out.println("목록 실패:"+e);
         }finally{
            DBClose.close(con, pstmt, rs);
         }//end
         return list;
    	
    }//list() end
    
    
    public NoticeDTO read(int wno) { //NoticeController.java에서 글번호를 넘깁니다
    	NoticeDTO dto = null;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" SELECT wno, wtitle, wcontent, userid, ncode, poster, filename, filesize, wview, wdate ");
          sql.append(" FROM tb_notice ");
          sql.append(" WHERE wno=? ");
          
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, wno);
          
          rs = pstmt.executeQuery();
          if(rs.next()) {
            dto = new NoticeDTO();
            dto.setWno(rs.getInt("wno"));
            dto.setWtitle(rs.getString("wtitle"));
            dto.setWcontent(rs.getString("wcontent"));
            dto.setUserid(rs.getString("userid"));
            dto.setNcode(rs.getString("ncode"));
            dto.setPoster(rs.getString("poster"));
            dto.setFilename(rs.getString("filename"));
            dto.setFilesize(rs.getInt("filesize"));
            dto.setWview(rs.getInt("wview"));                   
          }//if end

        } catch (Exception e) {
            System.out.println("상세보기실패"+e);
        } finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
    
    
    public int delete(NoticeDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" DELETE FROM tb_notice" ); 
          sql.append(" WHERE wno = ?");
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, dto.getWno());
          cnt = pstmt.executeUpdate();				  
        } catch (Exception e) {
            System.out.println("삭제실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
    
    public int update(NoticeDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" UPDATE tb_notice ");
          sql.append(" SET wtitle=?, ncode=?, filename=?, filesize=? ");
          pstmt.setInt(1, dto.getWno());
          pstmt.setString(2, dto.getWtitle());
          pstmt.setString(3, dto.getWcontent());
          pstmt.setString(4, dto.getUserid());
          pstmt.setString(5, dto.getNcode());
          pstmt.setString(6, dto.getFilename());
          pstmt.setInt(7, dto.getFilesize());
          pstmt.setInt(8, dto.getWview());
          
          cnt = pstmt.executeUpdate();
          
        } catch (Exception e) {
           System.out.println("수정실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//update() end
    
  

}//class end
