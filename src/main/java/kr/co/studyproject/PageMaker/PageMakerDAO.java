package kr.co.studyproject.PageMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.studyproject.bbsFree.BbsFreeDTO;
import net.utility.Criteria;
import net.utility.DBClose;
import net.utility.DBOpen;

public class PageMakerDAO {

    private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public PageMakerDAO() {
    	dbopen=new DBOpen();
    }//end
	    
    public int create(PageMakerDTO dto) {
        int cnt = 0;
        try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO bbsFree(wno, wtitle,wcontent, wpasswd, userid, ccode, filename, filesize, wview, windent, wnum, ip, wdate, mdate) ");
            sql.append(" VALUES( Free_seq.nextval, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?, sysdate, sysdate) "); 
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, dto.getTotalCount());    
            pstmt.setInt(2, dto.getDisplayPageNum());   
            pstmt.setInt(3, dto.getStartPage());   
            pstmt.setInt(4, dto.getEndPage());       


            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("글작성실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    	
    }//create() end   

	public Object listCri(Criteria cri) {

		return null;
	}

	public int pageCount() {
	
		return 0;
	}
	    
}//class end
