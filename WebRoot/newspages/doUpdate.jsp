<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.news.entity.News"%>
<%@page import="org.news.service.impl.NewsServiceImpl"%>
<%@page import="org.news.service.NewsService"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	int ntid = 0;
	String ntitle = null;
	String nauthor = null;
	String nsummary = null;
	String ncontent = null;
	String picpath = null;
	String  id=request.getParameter("nid");
	int nid=Integer.parseInt(id);
	
	

	//设置编码格式
	request.setCharacterEncoding("UTF-8");
	//上传的地址
	String uploadPath = request.getSession().getServletContext()
			.getRealPath("upLoad/");
	//检查请求类型
	boolean flag = ServletFileUpload.isMultipartContent(request);
	if (flag) {
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(
				fileItemFactory);
		List<FileItem> list = fileUpload.parseRequest(request);
		//转化为迭代器
		Iterator<FileItem> iterator = list.iterator();
		while (iterator.hasNext()) {
			// 上传的数据
			FileItem fileItem = iterator.next();
			//是否是文件类型
			if (fileItem.isFormField()) {
				if (fileItem.getFieldName().equals("ntid")) {
				String sid=fileItem.getString("UTF-8");
				if(id!=null)
					ntid = Integer.parseInt(sid);
				}
				if (fileItem.getFieldName().equals("ntitle")) {
					ntitle = fileItem.getString("UTF-8");
				}
				if (fileItem.getFieldName().equals("nauthor")) {
					nauthor = fileItem.getString("UTF-8");
				}
				if (fileItem.getFieldName().equals("nsummary")) {
					nsummary = fileItem.getString("UTF-8");
				}
				if (fileItem.getFieldName().equals("ncontent")) {
					ncontent = fileItem.getString("UTF-8");
				}
			} else {
				String FileName = fileItem.getName();
				if (FileName != null && !FileName.equals(" ")) {
					File saveFile = new File(fileItem.getName());
					File uploadFile = new File(uploadPath,
							saveFile.getName());
					fileItem.write(uploadFile);
					picpath = FileName;
				}
			}
		}
	} else {
		out.print("上传不合法");
	}
	/* Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH-mm-ss");
	out.print(ntid);
	out.print(ntitle);
	out.print(nauthor);
	out.print(date);
	out.print(ncontent);
	out.print(nsummary);
	out.print(uploadPath + "/" + picpath);
	 */
	
	 NewsService newsService = new NewsServiceImpl();
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH-mm-ss");
	String time = dateFormat.format(date);
	News news = new News();
	news.setNid(nid);
	news.setNtid(ntid);
	news.setNauthor(nauthor);
	news.setNcontent(ncontent);
	news.setNmodifydate(date);
	news.setNsummary(nsummary);
	news.setNpicpath(uploadPath + "/" + picpath);
	news.setNtitle(ntitle);
	NewsService service=new NewsServiceImpl();
	int num=service.updateNews(news);
	if(num>0){
	response.sendRedirect("admin.jsp");
	}else{
	request.getRequestDispatcher("updateNews.jsp").forward(request, response);
	}
	
%>
