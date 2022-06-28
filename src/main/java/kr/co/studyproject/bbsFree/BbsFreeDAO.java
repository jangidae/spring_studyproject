package kr.co.studyproject.bbsFree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
            sql.append(" INSERT INTO bbsFree(wno, wtitle, wgrpno, userid, ccode, filename, filesize, wview, windent, wnum, ip, wdate, mdate) ");
            sql.append(" VALUES( Free_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate) "); 
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, dto.getWno());
            pstmt.setString(2, dto.getWtitle());
            pstmt.setInt(3, dto.getWgrpno());
            pstmt.setString(4, dto.getUserid());
            pstmt.setString(5, dto.getCcode());
            pstmt.setString(6,  dto.getFilename());
            pstmt.setInt(7, dto.getFilesize());
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
    
    
    public ArrayList<BbsFreeDTO> list(int wgrpno) {
        ArrayList<BbsFreeDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, wtitle, wgrpno, userid, ccode, filename, filesize, wview, windent, wnum, ip, wdate, mdate ");
            sql.append(" FROM bbsFree ");
            sql.append(" WHERE wgrpno=? ");
            sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, wgrpno);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<>();
                do {
                    BbsFreeDTO dto=new BbsFreeDTO();
                    dto.setWno(rs.getInt("wno"));
                    dto.setWtitle(rs.getString("wtitle"));
                    dto.setWgrpno(rs.getInt("wgrpno"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setCcode(rs.getString("ccode"));
                    dto.setFilename(rs.getString("filename"));
                    dto.setFilesize(rs.getInt("filesize"));
                    dto.setWview(rs.getInt("wview"));
                    dto.setWindent(rs.getInt("windent"));
                    dto.setWnum(rs.getInt("wnum"));
                    dto.setIp(rs.getString("ip"));
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
    
    
    public BbsFreeDTO read(int wno) {
    	BbsFreeDTO dto = null;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" SELECT wno, wtitle, wgrpno, userid, ccode, filename, filesize, wview, windent, wnum, ip, wdate, mdate  ");
          sql.append(" FROM bbsFree ");
          sql.append(" WHERE wno = ? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, wno);
          rs = pstmt.executeQuery();
          if(rs.next()) {
            dto = new BbsFreeDTO();
            dto.setWno(rs.getInt("wno"));
            dto.setWtitle(rs.getString("wtitle"));
            dto.setWgrpno(rs.getInt("wgrpno"));
            dto.setUserid(rs.getString("userid"));
            dto.setCcode(rs.getString("ccode"));
            dto.setFilename(rs.getString("filename"));
            dto.setFilesize(rs.getInt("filesize"));
            dto.setWview(rs.getInt("wview"));
            dto.setWindent(rs.getInt("windent"));
            dto.setWnum(rs.getInt("wnum"));
            dto.setIp(rs.getString("ip"));
          }//if end

        } catch (Exception e) {
            System.out.println("상세보기실패"+e);
        } finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
    
    
    public int delete(int wno) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" DELETE FROM bbsFree" );
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
    
    
    public int update(BbsFreeDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" UPDATE bbsFree ");
          sql.append(" SET wtitle=?, ccode=?, filename=?, filesize=? ");
          sql.append(" WHERE wno=? "); 
          pstmt.setInt(1, dto.getWno());
          pstmt.setString(2, dto.getWtitle());
          pstmt.setInt(3, dto.getWgrpno());
          pstmt.setString(4, dto.getUserid());
          pstmt.setString(5, dto.getCcode());
          pstmt.setString(6, dto.getFilename());
          pstmt.setInt(7, dto.getFilesize());
          pstmt.setInt(8, dto.getWview());
          pstmt.setInt(9, dto.getWindent());
          pstmt.setInt(10, dto.getWnum());
          pstmt.setString(11, dto.getIp());
          cnt = pstmt.executeUpdate();
        } catch (Exception e) {
           System.out.println("수정실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//update() end
    
  

}//class end
