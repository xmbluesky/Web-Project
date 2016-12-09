package com.xf.page.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xf.page.dao.Constant;
import com.xf.page.model.Pager;
import com.xf.page.model.Student;
import com.xf.page.service.StudentService;
import com.xf.page.service.SublistStudentServiceImpl;

public class SublistServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5459092412782251847L;
	
	private StudentService studentService = new SublistStudentServiceImpl();

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//�����к������ģ���req��������Ϊutf-8
		req.setCharacterEncoding("utf-8");
		//����request����,�ȸ�ֵΪĬ��ֵ������Ϊnull������error
		String name = req.getParameter("name");
		System.out.println("servlet : name: "+name);
		//
		int gender = Constant.DEFAULT_GENDER;
		String genderStr = req.getParameter("gender");
		if(genderStr != null && !("".equals(genderStr.trim()))){
			gender = Integer.parseInt(genderStr);
		}
		//
		int pageNum = Constant.DEFAULT_PAGE_NUM;
		String pageNumStr = req.getParameter("pageNum");
		if(pageNumStr != null && !("".equals(pageNumStr.trim()))){
			pageNum = Integer.parseInt(pageNumStr);
			
		}
		//
		int pageSize = Constant.DEFAULT_PAGE_SIZE;
		String pageSizeStr = req.getParameter("pageSize");
		if(pageSizeStr != null && !("".equals(pageSizeStr.trim()))){
			pageSize = Integer.parseInt(pageSizeStr);
		}

		
		//��װ��ѯ����
		Student searchModel = new Student();
		searchModel.setName(name);
		searchModel.setGender(gender);
		
		//��ѯ���
		Pager<Student> result = studentService.findStudent(searchModel, pageNum, pageSize);
		//�洢���صĽ��
		req.setAttribute("result", result);
		//�洢��ѯ��Ϣ��Ϊ��ҳ����ҳ�ȱ�ǩ������ѯ��Ϣ
		req.setAttribute("name", name);
		req.setAttribute("gender", gender);
		req.getRequestDispatcher("/sublistStudent.jsp").forward(req, res);
	}

}