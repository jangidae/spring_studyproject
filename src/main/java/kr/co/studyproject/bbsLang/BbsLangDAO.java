package kr.co.studyproject.bbsLang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class BbsLangDAO {

    private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public BbsLangDAO() {
    	dbopen=new DBOpen();
    }//end
    
    public int create(BbsLangDTO dto) {
        int cnt = 0;
        try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO BBSLANG(wno,wcontent, lcode, wtitle, userid,  wview, wdate, mdate) ");
            sql.append(" VALUES(Lang_seq.nextval, ?, ?, ?, ?,?, sysdate, sysdate) "); 
            pstmt = con.prepareStatement(sql.toString());
            
            pstmt.setString(1, dto.getWcontent());
            pstmt.setString(2, dto.getLcode());            
            pstmt.setString(3, dto.getWtitle());
            pstmt.setString(4, dto.getUserid());
            pstmt.setInt(5, dto.getWview()); 
      
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("글작성실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    	
    }//create() end
    
    
    public ArrayList<BbsLangDTO> list() {
        ArrayList<BbsLangDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT wno, lcode, wtitle, userid,  wview, wdate, mdate ");
            sql.append(" FROM bbslang ");
           sql.append(" ORDER BY wno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<>();
                do {
                    BbsLangDTO dto=new BbsLangDTO();
                    
                    dto.setWno(rs.getInt("wno"));
                    dto.setLcode(rs.getString("lcode"));
                    dto.setWtitle(rs.getString("wtitle"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setWview(rs.getInt("wview"));
                    dto.setWdate(rs.getString("wdate"));
                    dto.setMdate(rs.getString("mdate"));
             
                   
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
    
    
    
  

}//class end
