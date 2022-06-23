package kr.co.studyproject.Member;

public class MemberDTO {
	private String userid;
	private String ulevel;
	private String upw;
	private String uname;
	private String email;
	private String phnum;
	private String introd;
	
	public MemberDTO() {} //기본생성자함수
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUlevel() {
		return ulevel;
	}
	public void setUlevel(String ulevel) {
		this.ulevel = ulevel;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhnum() {
		return phnum;
	}
	public void setPhnum(String phnum) {
		this.phnum = phnum;
	}
	public String getIntrod() {
		return introd;
	}
	public void setIntrod(String introd) {
		this.introd = introd;
	}

	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", ulevel=" + ulevel + ", upw=" + upw + ", uname=" + uname + ", email="
				+ email + ", phnum=" + phnum + ", introd=" + introd + "]";
	}
	
	
	
	
	
	
	
	
}//class end
