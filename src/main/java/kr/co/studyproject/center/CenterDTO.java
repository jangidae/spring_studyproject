package kr.co.studyproject.center;

public class CenterDTO {
	
	private int wno;
	private String wtitle;
	private String wcontent;
	private String wpasswd;
	
	private String userid;
	private String wdate;
	private String mdate;
	private int wview;
	private String	wcode;
	
	private int windent;
	private int wnum;
	private String ip;
	
	public CenterDTO() {} //기본 생성자 함수

	public int getWno() {
		return wno;
	}

	public void setWno(int wno) {
		this.wno = wno;
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

	public String getWpasswd() {
		return wpasswd;
	}

	public void setWpasswd(String wpasswd) {
		this.wpasswd = wpasswd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public int getWview() {
		return wview;
	}

	public void setWview(int wview) {
		this.wview = wview;
	}

	public String getWcode() {
		return wcode;
	}

	public void setWcode(String wcode) {
		this.wcode = wcode;
	}

	public int getWindent() {
		return windent;
	}

	public void setWindent(int windent) {
		this.windent = windent;
	}

	public int getWnum() {
		return wnum;
	}

	public void setWnum(int wnum) {
		this.wnum = wnum;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "CenterDTO [wno=" + wno + ", wtitle=" + wtitle + ", wcontent=" + wcontent + ", wpasswd=" + wpasswd
				+ ", userid=" + userid + ", wdate=" + wdate + ", mdate=" + mdate + ", wview=" + wview + ", wcode="
				+ wcode + ", windent=" + windent + ", wnum=" + wnum + ", ip=" + ip + "]";
	}
	

}//class end
