package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties(); //전역으로 선언해서 사용하기 편하게 함
	
	public MemberDao() {//생성자
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath)); //파일 불려오기
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Member loginMember(Connection conn,String userId, String userPwd) {
		//select문 => ResultSet 객체(한 행) => Member 객체  => 리턴값 m => m의 자료형은 Member(아래코드확인)
		Member m = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember"); //loginMember => "키값" //쿼리를 직접 작성 하면 쿼리가 바뀔때 마다 바꿔줘야 한다. (=>loginMember은 해당 쿼리문 제목으로 입력한 값)
														// db->sql->member-mapper.xml안에 있다.
		
		try {
			pstmt = conn.prepareStatement(sql);//미완성된 쿼리 => 필요한 값 2개 순서대로 userId, userPwd
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); //조회된 결과가 있다면 한 행 이 조회가 될 것이다. | 조회결과가 없다고 하면 아무것도 안담긴다. select => executeQuery()
			
			if(rset.next()) {
				m = new Member(rset.getInt("user_no"),
								rset.getString("user_id"),
								rset.getString("user_pwd"),
								rset.getString("user_name"),
								rset.getString("phone"),
								rset.getString("email"),
								rset.getString("address"),
								rset.getString("interest"),
								rset.getDate("enroll_date"),
								rset.getDate("modify_date"),
								rset.getString("status"));
						
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			/*JDBCTemplate.*/close(rset);
			/*JDBCTemplate.*/close(pstmt); //반납은 생성된 순서 반대로
		}
		return m;
	}
	
	
	
	public int insertMember(Connection conn, Member m) {
		//insert문 => 처리된 행 수 => 트랜젝션 처리
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember"); //insertMember => 해당 쿼리문 제목 => db->sql->member-mapper.xml안에 있다.
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 쿼리 => 쿼리문에 ?가 있다.
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest()); //여기까지 오면 완변해진다.
			
			result = pstmt.executeUpdate(); // insert => executeUpdate() => 쿼리 돌릴준비 끝
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			/*JDBCTemplate.*/close(pstmt);
		}
		return result;
	}
	
	
	public int updateMember(Connection conn,Member m) {
		
		//update문 => 처리된 행수(오라클에서 "1행"이 업데이트 되었습니다.) => 트랜젝션 처리
		
		int result = 0; //처리된 행수 담을 곳
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 쿼리
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;

	}
	
	public Member selectMember(Connection conn,String userId) {
		//select문=> 한행 => ResultSet 객체 => Member객체
		Member m = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember"); //미완성
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("user_no"),
						rset.getString("user_id"),
						rset.getString("user_pwd"),
						rset.getString("user_name"),
						rset.getString("phone"),
						rset.getString("email"),
						rset.getString("address"),
						rset.getString("interest"),
						rset.getDate("enroll_date"),
						rset.getDate("modify_date"),
						rset.getString("status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	public int updatePwd(Connection conn, String userId, String userPwd, String updatePwd) {
		//update 문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	
	}
	
	public int deleteMember(Connection conn, String userId, String userPwd) {
		//update문 => 처리된 행수 => 트랜젝션 처리
		
		//처리된 행수를 담아둘 공간
		int result = 0;
		
		//쿼리 돌리기 위해 필요
		PreparedStatement pstmt = null;
		
		//돌여야 하는 쿼리
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 쿼리(쿼리 돌리기 준비)
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate(); //완벽해진 쿼리(자료형이 int)를 result에 담기
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
		
	}
	
	public int idcheck(Connection conn,String checkId) {
		//select문 => ResultSet => 한개숫자 => int
		int count=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("idcheck");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, checkId);

			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return count;
		
		
	}
	
}
