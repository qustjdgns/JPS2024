package news;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class NewsController
 */
@WebServlet("/ncontrol")
@MultipartConfig(maxFileSize=102*1024*2, location="C:/Temp/Img")

public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NewsDAO dao;
	private ServletContext ctx;
	private final String START_PAGE = "/newslist.jsp";
	
	
       
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		dao = new NewsDAO();
		ctx = getServletContext();
	}
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		dao = new NewsDAO();
		
		Method m;
		String view = null;
		
		if(action == null)
		{
			action = "listNews";
		}
		
		try
		{
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			view = (String)m.invoke(this, request);
		} catch(NoSuchMethodException e)
		{
			e.printStackTrace();
			ctx.log("요청 action없음");
			request.setAttribute("error", "action parameter가 잘못됨");
			view = START_PAGE;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			
		}
	}
	
	public String getFileName2(Part part) {
		String fileName = null;
		String header = part.getHeader("content-disposition");
		System.out.println("header =>" + header);
		
		int start = header.indexOf("filename=");
		fileName = header.substring(start+10, header.length()-1);
		ctx.log("파일명" + fileName);
		return fileName;
	}
	
	public String addNews(HttpServletRequest request) {
		News n = new News();
		
		try {
			Part part = request.getPart("file");
			String fileName = getFileName2(part);
			if(fileName != null && !fileName.isEmpty())
			{
				part.write(fileName);
			}
			
			n.setContent(request.getParameter("content"));
			n.setImg(request.getParameter("img"));
			n.setTitle(request.getParameter("title"));
			n.setDate(request.getParameter("cdate"));
			
			n.setImg("/img/"+fileName);
			dao.addNews(n);
			
		} catch(Exception e) {
			e.printStackTrace();
			ctx.log("addNews문제");
			request.setAttribute("error", "뉴스등록에러");
			return listNews(request);
		}
		return "redirect:/ncontrol?action=listNews";
	}
	
	
	public String listNews(HttpServletRequest request) {
		List<News> list;
		
		try {
			list = dao.getAll();
			request.setAttribute("newslist", list);
			
		} catch(Exception e) {
			e.printStackTrace();
			ctx.log("뉴스목록 에러");
			request.setAttribute("error", "뉴스 목록 에러 발생");
		}
		return "newslist.jsp";
	}
	
	public String getNews(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			News n = dao.getNews(aid);
			request.setAttribute("news", n);
			
		} catch(SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스가져오기 문제");
			request.setAttribute("error", "뉴스 가져오기 실패");
		}
		return "newsview.jsp";
	}
	
	public String deleteNews(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delNews(aid);
			
		} catch(SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스 삭제 문제");
			request.setAttribute("error", "뉴스 삭제 실패");
		}
		return "redirect:/ncontrol?action=listNews";
	}
}
