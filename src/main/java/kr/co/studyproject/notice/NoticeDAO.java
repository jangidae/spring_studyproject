package kr.co.studyproject.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import net.utility.DBClose;
import net.utility.DBOpen;

public class NoticeDAO {

	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;

	public NoticeDAO() {
		dbopen = new DBOpen();
	}// end

	public int create(NoticeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(
					" INSERT INTO tb_notice(wno, wtitle, wcontent, userid, ncode, poster,filename, filesize, wview) ");
			sql.append(" VALUES( tb_notice_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWtitle());
			pstmt.setString(2, dto.getWcontent());
			pstmt.setString(3, dto.getUserid());
			pstmt.setString(4, dto.getNcode());
			pstmt.setString(5, dto.getPoster());
			pstmt.setString(6, dto.getFilename());
			pstmt.setLong(7, dto.getFilesize());
			pstmt.setInt(8, dto.getWview());

			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("글작성실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;

	}// create() end

	public ArrayList<NoticeDTO> list() {
		ArrayList<NoticeDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT wno, wtitle, userid, ncode, wview, wdate ");
			sql.append(" FROM tb_notice ");
			sql.append(" ORDER BY wno DESC ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<>();
				do {
					NoticeDTO dto = new NoticeDTO();
					dto.setWno(rs.getInt("wno"));
					dto.setWtitle(rs.getString("wtitle"));
					dto.setUserid(rs.getString("userid"));
					dto.setNcode(rs.getString("ncode"));
					dto.setWview(rs.getInt("wview"));
					dto.setWdate(rs.getString("wdate"));

					list.add(dto);

				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("목록 실패:" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return list;

	}// list() end
	
	 
	public NoticeDTO read(int wno) { // NoticeController.java에서 글번호를 넘깁니다
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
			if (rs.next()) {
				//increment_view_count() 함수를 호출해야 조회수가 증가됩니다~
				//대부분 상세보기를 하면 조회수가 호출되니, 여기서 호출하겠습니다
				increment_view_count(wno);
				
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
				//여기서 날짜 데이터를 dto 담지 않았습니다
				dto.setWdate(rs.getString("wdate"));
			} // if end

		} catch (Exception e) {
			System.out.println("상세보기실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // end
		return dto;
	}// read() end

	public int delete(NoticeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_notice");
			sql.append(" WHERE wno = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getWno());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// delete() end

	public int update(NoticeDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_notice ");
			sql.append(" SET wtitle=?, ncode=?, wcontent=?, poster=? ,filename=?, filesize=?");
			sql.append(" WHERE wno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWtitle());
			pstmt.setString(2, dto.getNcode());
			pstmt.setString(3, dto.getWcontent());
			pstmt.setString(4, dto.getPoster());
			pstmt.setString(5, dto.getFilename());
			pstmt.setLong(6, dto.getFilesize());
			pstmt.setInt(7, dto.getWno());

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("수정실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		return cnt;
	}// update() end


	public void increment_view_count(int wno) {
		try {
			con = dbopen.getConnection();
			
			sql = new StringBuilder();
			sql.append(" UPDATE tb_notice");
			sql.append(" SET wview= wview+1");
			sql.append(" WHERE wno=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, wno);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("조회수 증가 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // end
		
	}// increment_view_count() end
	

	 public ArrayList<NoticeDTO> list2(int start, int end){
	        ArrayList<NoticeDTO> list=null;
	        try {
	            con=dbopen.getConnection();
	            sql=new StringBuilder();
	           
	            sql.append(" SELECT AA.* ");
	            sql.append(" FROM ( ");
	            sql.append("        SELECT ROWNUM as RNUM, BB.* ");
	            sql.append("        FROM ( ");
	            //sql.append("               SELECT wno, wtitle "); DB에서 자료를 가져오기 않았습니다
	            sql.append("               SELECT wno, ncode, wtitle, wdate, wview ");
	            sql.append("               FROM tb_notice ");
	            sql.append("               ORDER BY wno DESC ");
	            sql.append("             )BB ");
	            sql.append("      ) AA ");
	            sql.append(" WHERE AA.RNUM >=? AND AA.RNUM<=? ");           
	           
	            pstmt=con.prepareStatement(sql.toString());
	            pstmt.setInt(1, start);
	            pstmt.setInt(2, end);
	           
	            rs=pstmt.executeQuery();
	            if(rs.next()) {
	                list=new ArrayList<NoticeDTO>();
	                do{
	                	NoticeDTO dto=new NoticeDTO();
	                    dto.setWno(rs.getInt("wno"));
	                    dto.setNcode(rs.getString("ncode"));
	                    dto.setWtitle(rs.getString("wtitle"));
	                    dto.setWdate(rs.getString("wdate"));
	                    dto.setWview(rs.getInt("wview"));
	                    list.add(dto);
	                }while(rs.next());
	            }//if end
	           
	        }catch(Exception e) {
	            System.out.println("페이징목록실패: "+e);
	        }finally{
	            DBClose.close(con, pstmt, rs);
	        }//end   
	        return list;
	    }//list2() end

	 public int totalRowCount() {
	        int cnt=0;
	        try {
	            con=dbopen.getConnection();
	            sql=new StringBuilder();
	            sql.append(" SELECT COUNT(*) FROM tb_Notice ");
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

}// class end
