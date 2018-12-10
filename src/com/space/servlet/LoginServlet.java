package com.space.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.space.dao.UserDao;

/**
 * Servlet ·����ʽ��
 * 	1. ע�ⷽʽ�� @WebServlet("/LoginServlet")
 * 		����Ҫע����� web.xml�ļ���metadata-complete="false"���� <web-app metadata-complete="false" >
 *  2. xml���÷�ʽ ��
 * 
 */

/*@WebServlet("/login/loginServlet")*/
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.setCharacterEncoding("utf-8");
		//1. ��ȡ����Ϣ
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		
		//2. ����userDao,�������ݿ���Ϣ�����жϣ������жϽ��
		String isLogin = userDao.isLogin(username, password);
		System.out.println("�Ƿ��¼��"+isLogin);
		
		//3. �Ƿ��¼�ɹ��� ת�������ض���ҳ��
		if("success".equals(isLogin)){ //��¼�ɹ�
			//�ʺ�������ȷ
			/*request.setAttribute("username", username);
			request.setAttribute("password", password);*/
			
			//���û���Ϣ���浽session����
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else{ //��¼ʧ��
			System.out.println("����ԭ��"+isLogin);
			//�ض����¼����
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
		
		
		//��������ԣ�ʹ�ù̶��ʺ�����ʵ���ض���
		/*if("asdf".equals(username) && "asdf".equals(password)){
			System.out.println("�ʺ�������ȷ");
			//ת�� success����
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else{
			System.out.println("�ʺŻ��������");
			//�ض����¼����
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}*/
		
		
	}

}
