package kr.co.studyproject.Member;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.utility.*;

public class MemberDAO {
	//실질적으로 데이터베이스에서 회원정보를 불러오거나 넣으려할때 사용
	private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
	
    public MemberDAO() {
    	dbopen=new DBOpen();
    }//end
    
    
    public String loginProc(MemberDTO dto) {//등급을 리턴해야하므로 리턴값 String
		String ulevel=null; //로그인(아이디비번일치)하면 회원등급 가져오고 로그인 성공
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT ulevel ");
			sql.append(" FROM member "); //테이블명 tb_user에서 member로 변경
			sql.append(" WHERE userid=? and upw=? ");
			sql.append(" AND ulevel in ('MASTER','A','B','C') "); //회원등급기준 탈퇴회원은 로그인 못하도록
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUpw());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				ulevel=rs.getString("ulevel");
			}//if end
			
		} catch (Exception e) {
			System.out.println("로그인 실패 : " + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return ulevel;
	}//loginProc() end
    
    
    
    // 아이디, 비밀번호 찾기
    // 이름과 이메일이 일치하면 메일로 서버에 등록된 아이디와 임시비밀번호 발급
    public void findID() {}//
    
    
    
    public int create(MemberDTO dto) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" INSERT INTO member(userid, ulevel, upw, uname, email, phnum, introd) ");
			sql.append(" VALUES(?,'C',?,?,?,?,?) ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUserid());
			//pstmt.setString(2, dto.getUlevel()); 이부분은 입력값이 아니라 무조건 'C'가 입력되는 값이라 없어도 되는 부분입니다
			//부적합한 열 인덱스 ->물음표의 갯수와 순서가 맞지 않아서 나오는 에러메세지 입니다
			pstmt.setString(2, dto.getUpw());
			pstmt.setString(3, dto.getUname());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhnum());
			pstmt.setString(6, dto.getIntrod()); //
			
			cnt=pstmt.executeUpdate();
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}//if end
			
		} catch (Exception e) {
			System.out.println("회원가입 실패 : " + e);
		}finally {
			DBClose.close(con, pstmt);
		}//end
		return cnt; 
		
	}//create() end
    
    
    public int duplecateID(String userid) {
    	int cnt=0;
    	try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(userid) as cnt ");
			sql.append(" FROM member ");
			sql.append(" WHERE userid=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}//if end
			
		} catch (Exception e) {
			System.out.println("아이디 중복확인 실패 : " + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return cnt; 
	}//duplecateID() end
    
    
    public int duplecateemail(String email) {
    	int cnt=0;
    	try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(email) as cnt ");
			sql.append(" FROM member ");
			sql.append(" WHERE email=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}//if end
			
		} catch (Exception e) {
			System.out.println("이메일 중복확인 실패 : " + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return cnt; 
	}//duplecateID() end
    
    
    
    public MemberDTO read(String userid) {
        MemberDTO dto=null;
        try {
            con=dbopen.getConnection();
            
            sql=new StringBuilder(); //객체생성
            sql.append(" SELECT userid, ulevel, upw, uname, phnum, email, introd ");
            sql.append(" FROM member ");
            sql.append(" WHERE userid=? ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, userid); //쿼리문 첫번째 물음표(id=?)에 값 넣어주기
            
            //pstmt.executeUpdate();  -> insert, update, delete문 실행할때 
            rs=pstmt.executeQuery();//-> select문 실행할때
            if(rs.next()) {//자료가(행이) 존재하는지?
                dto=new MemberDTO(); //현재 dto설정은 null이기 때문에 객체를 먼저 생성. 이후 dto에 하단 자료들 담기
                dto.setUpw(rs.getString("upw"));
                dto.setUname(rs.getString("uname"));
                dto.setPhnum(rs.getString("phnum"));
                dto.setEmail(rs.getString("email"));
                dto.setIntrod(rs.getString("introd"));
                
            }//end
            
        }catch (Exception e) {
            System.out.println("회원정보 가져오기 실패:"+e);
        }finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto; //한사람에 대한 모든 정보이기 때문에 정보량이 많아 dto에 담아 가져옴
    }//read() end
    
    
    
    
    public int modifyProc(MemberDTO dto) {
        int cnt=0; //SQL문을 실행한 행의 갯수를 저장 후 반환
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" UPDATE member ");
            sql.append(" SET upw=? ");
            sql.append(" , uname=? ");
            sql.append(" , phnum=? ");
            sql.append(" , introd=? ");
            sql.append(" WHERE userid=? ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getUpw());
            pstmt.setString(2, dto.getUname());
            pstmt.setString(3, dto.getPhnum());
            pstmt.setString(4, dto.getIntrod());
            pstmt.setString(5, dto.getUserid());
            
            cnt=pstmt.executeUpdate();//insert, update, delete문을 실행할때 사용
            
        }catch (Exception e) {
            System.out.println("회원정보 수정 실패:" + e);
        }finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//updateproc2() end
    
    
    public int delete(MemberDTO dto) {
        int cnt=0;
        try {
            
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" UPDATE member ");
            sql.append(" SET ulevel='F' ");
            sql.append(" WHERE upw=? ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getUpw());
            cnt=pstmt.executeUpdate();
            
        }catch (Exception e) {
            System.out.println("회원 탈퇴 실패:"+e);
        }finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
    
    
    public boolean findID(MemberDTO dto) {
		boolean flag=false;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			//이름과 이메일이 일치하는 id 가져오기
			sql.append(" SELECT userid ");
			sql.append(" FROM member ");
			sql.append(" WHERE uname=? AND email=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUname());
			pstmt.setString(2, dto.getEmail());
			rs=pstmt.executeQuery();
			if(rs.next()) {//이름과 이메일이 일치한다면
				String userid=rs.getString("userid");//1)아이디
				
				//[임시 비밀번호 발급] - 대문자, 소문자, 숫자를 이용해서 랜덤하게 10글자 생성
				String[] ch= {
						"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                        "0","1","2","3","4","5","6","7","8","9"
				};//0~61 인덱스
				
				//ch배열에서 랜덤하게 10글자 발생
				StringBuilder imsiPW=new StringBuilder();//2)임시 비밀번호 발급
				for(int i=0; i<10; i++) {
					int num=(int)(Math.random()*ch.length);//ch[0]~ch[61]까지 존재
					imsiPW.append(ch[num]);
				}//for end
				
				//회원정보를 발급받은 임시비밀번호로 업데이트하기
				sql=new StringBuilder();
				sql.append(" UPDATE member ");
				sql.append(" SET upw=? ");
				sql.append(" WHERE uname=? AND email=? ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, imsiPW.toString());//임시비밀번호
				pstmt.setString(2, dto.getUname());
				pstmt.setString(3, dto.getEmail());
				
				int cnt=pstmt.executeUpdate();
				if(cnt==1) {//임시 비밀번호로 업데이트 되었다면, 아이디와 임시비번을 이메일로 전송하기
					String content="";
					content += "<hr>";
					content += "<table border='1'>";
					content += "<tr>";
					content += "	<th>아이디</th>";
					content += "	<td>" + userid + "</td>";
					content += "</tr>";
					content += "<tr>";
					content += "	<th>임시비밀번호</th>";
					content += "	<td>" + imsiPW.toString() + "</td>";
					content += "</tr>";
					content += "</table>";
					
					String mailServer="mw-002.cafe24.com"; //cafe24 메일서버
					Properties props=new Properties();     //properties 클래스 베이직자바시간 복습
					props.put("mail.smtp.host", mailServer);
					props.put("mail.smtp.auth", true);
					Authenticator myAuth=new MyAuthenticator();//다형성
					Session sess=Session.getInstance(props, myAuth);
					
					InternetAddress[] address={ new InternetAddress(dto.getEmail()) };
					Message msg=new MimeMessage(sess); //msg변수에 순차적으로 정보 작성
					
					
					//받는 사람
					msg.setRecipients(Message.RecipientType.TO, address);
					//참조     Message.RecipientType.CC
					//숨은참조  Message.RecipientType.BCC
					
					//보내는 사람
					msg.setFrom(new InternetAddress("ichae0302@gmail.com"));
					
					//메일 제목
					msg.setSubject("스터디하시2조 아이디/임시비밀번호입니다");
					
					//메일 내용
					msg.setContent(content, "text/html; charset=UTF-8" );
					
					//메일 보낸날짜
					msg.setSentDate(new Date());
					
					//메일전송
					Transport.send(msg);
					
					flag=true; //최종적으로 성공
					
				}else {
					flag=false;
				}//if end
			}//if end
			
			
		} catch (Exception e) {
			System.out.println("아이디/비밀번호 찾기 실패 : " + e);
		}finally {
			DBClose.close(con, pstmt, rs);
		}//end
		return flag;
	}//findID() end
    
    
    
	
}//class end
