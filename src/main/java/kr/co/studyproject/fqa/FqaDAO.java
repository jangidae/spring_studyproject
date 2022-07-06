package kr.co.studyproject.fqa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class FqaDAO {
	
	private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
	
    public FqaDAO() {
    	dbopen=new DBOpen();
    }//end



    
	
}//class end