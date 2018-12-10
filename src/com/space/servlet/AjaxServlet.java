package com.space.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.space.dao.UserDao;
import com.space.entity.User;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/login/ajaxServlet")
public class AjaxServlet extends HttpServlet {
	private UserDao userDao ;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("ajax ��̨���ݣ�");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		
		//2. ���ݿ����� У��
		String isLogin = userDao.isLogin(username, password);
		System.out.println("�Ƿ��¼��"+isLogin);
		
		//3. �����¼�ɹ������û�����������ȷ
		//	���û���Ϣ���浽session��
		if("success".equals(isLogin)){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
		}
		
		//4. ʹ��JSON��ʽ �������ݽ��
		PrintWriter printWriter = response.getWriter();
		//ʹ��GSON  ������ת��ΪJSON���ݸ�ʽ
		Gson gson = new Gson();
		
		ArrayList<User> arrayList = new ArrayList<>();
		User user = new User() ;
		user.setUsername(username);
		user.setPassword(password);
		arrayList.add(user);
		
		//ʹ��map���������� ��֤�ɹ�����ʧ��
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("isLogin", isLogin); //�Ƿ��¼�ɹ�
		hashMap.put("user", arrayList);
		
		//��arraylist�������� ת��Ϊ json��ʽ
		String json = gson.toJson(hashMap);
		System.out.println("json���ݣ�"+json);
		//���ؽ��
		printWriter.write(json);
						
	}

}
