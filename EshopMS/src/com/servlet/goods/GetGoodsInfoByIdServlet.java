package com.servlet.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.GoodsClass;
import com.dao.GoodsClassDao;
import com.dao.GoodsDao;
import com.daoFactory.GoodsClassDaoFactory;
import com.daoFactory.GoodsDaoFactory;
/**
 * 通过编号获取商品信息的控制类
 * @version 1.0.0
 */
public class GetGoodsInfoByIdServlet extends HttpServlet {

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		String id=request.getParameter("id");
		
		Vector v=new Vector();
		
		GoodsDao dao1=new GoodsDaoFactory().getGoodsDaoInstance();
		
		v=dao1.getGoodsInfoById(id);
		
		HttpSession session=request.getSession();
		session.setAttribute("goodsInfo", v);
		
		
		ArrayList<GoodsClass> list=new ArrayList<GoodsClass>();
		
		GoodsClassDao dao2=new GoodsClassDaoFactory().getGoodsClassDaoInstance();
		
		list=dao2.getGoodsClassName();
		
		session.setAttribute("goodsClassName", list);
		
		response.sendRedirect("/server/view/goods/updateGoodsInfo.jsp");
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
