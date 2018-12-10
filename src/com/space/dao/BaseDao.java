package com.space.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.space.entity.User;
import com.space.jdbc.JDBCUtil;

/**
 * JDBC ��ɾ�Ĳ� ����
 * @author space
 *
 */
public class BaseDao {
	private JDBCUtil jdbcUtil = new JDBCUtil();
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	private int executeUpdate = 0 ;
	
	public BaseDao() {
		conn = jdbcUtil.getConnection();
	}
	/*
	 * ��ѯ
	 */
	public void getQuery(){
		String sql = "select * from admin";
		try {
			pstmt= (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//��ȡ���ֶ�id
				String id = rs.getString("id");
				System.out.println(id);
				//��ȡ���ֶ� username
				String username = rs.getString("username");
				System.out.println(username);
				//��ȡ���ֶ� password 
				String password = rs.getString("password");
				System.out.println(password);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر�����
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * ����;
	 * 	��admin �� id��������
	 * 	ִ��excuteUpdate()��������int���ͣ���ʾ��Ӱ���������0��ʾ����ʧ��
	 * 	
	 * 	��ô������ܻ��׳��쳣����username������ Ψһ���������ظ����뽫�׳��쳣��
	 * 	��Ҫ�Ƚ��� ��ѯ�ظ��жϣ�
	 * 
	 */
	public int insert(User user){
		String sql = "insert into admin(id,username,password) value(null,?,?)";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
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
	
	/*
	 * �޸�
	 */
	public int update(User user){
		String sql = "update admin set password = ? where username = ?";
		
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
			executeUpdate = pstmt.executeUpdate();
			System.out.println("executeUpdate:"+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate ;
	}
	
	/*
	 * ɾ��
	 */
	public int delete(User user){
		String sql = "delete from admin where username = ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			executeUpdate = pstmt.executeUpdate();
			System.out.println("executeUpdate:"+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return executeUpdate;
	}
	
	
	/*
	 * ���ԣ� ʹ��ʱ��ע�������������������ݻ���
	 */
	public static void main(String[] args) {
		BaseDao baseDao = new BaseDao();
		User user = new User();
		
		//1. ��ѯ����
		System.out.println("��ѯ--------------------");
		baseDao.getQuery();
		
		//2. ����
		System.out.println("����--------------------");
		user.setUsername("hello1");
		user.setPassword("hello1");
		int insert = baseDao.insert(user);
		if(insert != 0){
			System.out.println("�������ݳɹ�");
		}
		
		//3. �޸�
		System.out.println("�޸�--------------------");
		user.setUsername("hello");
		user.setPassword("main");
		int update  = baseDao.update(user);
		if(update != 0){
			System.out.println("�޸����ݳɹ�");
		}
		
		//4. ɾ��
		System.out.println("ɾ��--------------------");
		user.setUsername("qwer");
		int delete = baseDao.delete(user);
		if(delete != 0){
			System.out.println("ɾ�����ݳɹ�");
		}
	}
	
}
