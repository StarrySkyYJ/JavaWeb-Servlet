package com.space.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.space.jdbc.JDBCUtil;

public class UserDao {
	private JDBCUtil jdbcUtil = new JDBCUtil();
	private Connection conn ;
	private PreparedStatement pstmt ;
	private ResultSet rs ;
	private int i = 0 ;
	
	public UserDao() {
		conn = jdbcUtil.getConnection();
	}
	
	//��¼�ж�
	public String isLogin(String username ,String password){
		String sql = "select * from admin where username='"+username+"'";
		System.out.println("sql:"+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//�ж��û����Ƿ���ȷ
			rs.last(); //������� �ƶ������һ�� ;�ж��Ƿ�������
			if(rs.getRow() == 0){ //������
				return "username is not exits";
			}else{ 
				//�����ݣ�rs������ƶ�����һ��
				rs.beforeFirst();
				//�ж������Ƿ���ȷ
				while(rs.next()){
					if(password.equals(rs.getString("password"))){
						return "success" ;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return "password Error";
	}
	
	//ע�� ����������
	public int isRegister(String username ,String password){
		String sql = "insert into admin(id,username,password) value(null,?,?)";
		int executeUpdate = 0 ;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//ִ�в�����£�����int���ͣ���ʾ���µ���������Ӱ����������������ݸ��»��߲��뷵��0
			executeUpdate = pstmt.executeUpdate(); 
			System.out.println("executeUpdate:"+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return executeUpdate;
	}
	
	//�ж��Ƿ��и��û�
	public String isUsernameExist(String username){
		String sql = "select * from admin where username='"+username+"'";
		System.out.println("sql:"+sql);
	
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last(); //������� �ƶ������һ�� ;�ж��Ƿ�������
			if(rs.getRow() == 0){
				return "noExist";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "exist";
	}
	
	//��ȡ���� �û���Ϣ
	public void getAllUser(){
		String sql = "select * from admin";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("�����ֶ�����"+col);
			while(rs.next()){
				i++ ;
				System.out.println(i+":"+"id="+rs.getString("id")+" ; "
				+"username="+rs.getString("username")+" ; "
				+"password="+rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
