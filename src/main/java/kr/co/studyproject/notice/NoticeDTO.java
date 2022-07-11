package kr.co.studyproject.notice;

import org.springframework.web.multipart.MultipartFile;

public class NoticeDTO {
	
	private int wno;          //PK
	private String ncode;     //구분코드
	private String wtitle;	  //제목
	private String wcontent;  //내용
	private String userid;    //작성자
	private String poster;    //이미지
	private String filename;  //파일명
	private long filesize;     //파일사이즈
	private String wdate;     //최초 작성 날짜
	private int wview;        //조회수
	private int indent;       //들여쓰기
	
	////////////////////////////////////////////////////////////
	 private MultipartFile posterMF;
	 private MultipartFile filenameMF;
	 
	 
	 public MultipartFile getPosterMF() {
		return posterMF;
	}
	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}
	public MultipartFile getFilenameMF() {
		return filenameMF;
	}
	public void setFilenameMF(MultipartFile filenameMF) {
		this.filenameMF = filenameMF;
	}



	
	//////////////////////////////////////////////////////////////
	
	public int getWno() {
		return wno;
	}
	public void setWno(int wno) {
		this.wno = wno;
	}
	public String getNcode() {
		return ncode;
	}
	public void setNcode(String ncode) {
		this.ncode = ncode;
	}
	public String getWtitle() {
		return wtitle;
	}
	public void setWtitle(String wtitle) {
		this.wtitle = wtitle;
	}
	public String getWcontent() {
		return wcontent;
	}
	public void setWcontent(String wcontent) {
		this.wcontent = wcontent;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getWview() {
		return wview;
	}
	public void setWview(int wview) {
		this.wview = wview;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	
	
	@Override
	public String toString() {
		return "NoticeDTO [wno=" + wno + ", ncode=" + ncode + ", wtitle=" + wtitle + ", wcontent=" + wcontent
				+ ", userid=" + userid + ", poster=" + poster + ", filename=" + filename + ", filesize=" + filesize
				+ ", wdate=" + wdate + ", wview=" + wview + ", indent=" + indent + "]";
	}

	/////////////////////////////////////////////////////////////////////
	
	

}//class end
