package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

import static com.kh.common.JDBCTemplate.*;

public class NoticeService {
	
	public ArrayList<Notice> selectNoticeList() {
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDao().insertNotice(conn,n);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int increaseCount(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn,noticeNo);
		
		if(result >0) {//성공
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotic(conn,noticeNo);
		
		close(conn);
		return n;
		
	}
	
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn,n);
		
		if(result >0) {//성공
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public int deleteNotice(int noticeNo) {
		//커넥션 생성
		Connection conn = getConnection();
		
		//dao호출 
		int result = new NoticeDao().deleteNotice(conn,noticeNo);
		
		//트렌젝션처리
		if(result > 0) { //성공
			commit(conn);
		}else {//실패
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
}
