package org.news.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.news.dao.NewsDao;
import org.news.dao.impl.NewsDaoImpl;
import org.news.entity.News;
import org.news.service.NewsService;
import org.news.service.impl.NewsServiceImpl;
import org.news.util.DatabaseUtil;
import org.news.util.Page;

public class PageTest {
/*    public void pageTest() {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            NewsDao newsDao = new NewsDaoImpl(conn);
            int totalCount = newsDao.getTotalCount();
            Page page=new Page();
            page.setCurrPageNo(3);              //设置当前页面
            page.setPageSize(5);                //设置每页条数
            page.setTotalCount(totalCount);     //设置总数量
            System.out.println("新闻总数量是：" + page.getTotalCount());
            System.out.println("每页条数是：" + page.getPageSize());
            System.out.println("总页数：" + page.getTotalPageCount());
            System.out.println("当前是第" + page.getCurrPageNo() + "页：");
            List<News> newsList = newsDao
                .getPageNewsList(page.getCurrPageNo(), page.getPageSize());
            page.setNewsList(newsList);
            for (News news : page.getNewsList()) {
                System.out.println(news.getNid() + "\t" + news.getNtitle()
                                    + "\t" + news.getNcreatedate());
            }
            Date date=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String  time=dateFormat.format(date);
            News news=new News();
            news.setNtid(4);
            news.setNtitle("深足教练组：说我们买球是侮辱 朱广沪常暗中支招");
            news.setNauthor("sport");
            news.setNcreatedate(date);
            news.setNcontent("王宝山怎么可能送分给谢峰呢？");
            news.setNsummary("深足教练组：说我们买球是侮辱 朱广沪常暗中支招");
            int num=newsDao.insertNews(news);
            System.out.println(num);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }*/
    public static void main(String[] args) {
    	Connection conn=null;
		try {
			conn = DatabaseUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	NewsDaoImpl daoImpl=new NewsDaoImpl(conn);
    	News news=new News();
    	news.setNid(49);
    	news.setNtid(5);
    	news.setNauthor("aa");
    	news.setNcontent("asdasdd");
    	news.setNtitle("aaa");
    	news.setNsummary("aaa");
    	int num=daoImpl.updateNews(news);
    	System.out.println(num);
    	/*int num=daoImpl.deleteNews(48);
    	System.out.println(num);*/
	}
}
