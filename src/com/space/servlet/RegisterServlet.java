package com.space.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.space.dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        userDao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. ��ȡ������
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("ajax ��̨���ݣ�"+username);
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		
		PrintWriter printWriter = response.getWriter();
		//2. �������ݿ⣬ ��ȡ������Ϣ���ж�
		if("exist".equals(userDao.isUsernameExist(username))){ //�ж��û����Ƿ����
			printWriter.write("username is exits");
		}else{
			//�û��������ڣ�����ע��
			int register = userDao.isRegister(username, password);
			if(register == 0){
				printWriter.write("ע��ʧ��");
			}else{
				printWriter.write("ע��ɹ�");
			}
		}
	}

}
