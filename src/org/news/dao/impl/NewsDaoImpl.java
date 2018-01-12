package org.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import org.news.dao.BaseDao;
import org.news.dao.NewsDao;
import org.news.entity.News;
import org.news.util.DatabaseUtil;

public class NewsDaoImpl extends BaseDao implements NewsDao {

	public NewsDaoImpl(Connection conn) {
		super(conn);
	}

	// 获取所有新闻
	public List<News> getAllnews() throws SQLException {
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
				+ " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
				+ " WHERE `NEWS`.`ntid` = `TOPIC`.`tid`"
				+ " ORDER BY `ncreateDate` DESC";
		try {
			rs = this.executeQuery(sql);
			News news = null;
			while (rs.next()) {
				news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getTimestamp("ncreateDate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNtname(rs.getString("tname"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	// 获取某主题下的所有新闻
	public List<News> getAllnewsByTID(int tid) throws SQLException {
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
				+ " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
				+ " WHERE `NEWS`.`ntid` = `TOPIC`.`tid` AND `NEWS`.`ntid` = ?"
				+ " ORDER BY `ncreateDate` DESC";
		try {
			rs = this.executeQuery(sql, tid);
			News news = null;
			while (rs.next()) {
				news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getTimestamp("ncreateDate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNtname(rs.getString("tname"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	// 获取某主题下的最新新闻
	public List<News> getLatestNewsByTID(int tid, int limit)
			throws SQLException {
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "SELECT `nid`, `ntid`, `ntitle` FROM `NEWS` WHERE"
				+ " `ntid` = ? ORDER BY `ncreatedate` DESC LIMIT ?";
		try {
			rs = this.executeQuery(sql, tid, limit);
			News news = null;
			while (rs.next()) {
				news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	// 获取某主题下的新闻数量
	public int getNewsCountByTID(int tid) throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT COUNT(`ntid`) FROM `news` WHERE `ntid` = ?";
		int count = -1;
		try {
			rs = this.executeQuery(sql, tid);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return count;
	}

	// 获取某条新闻
	public News getNewsByNID(int nid) throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT * FROM `NEWS`, `TOPIC`"
				+ " WHERE `NEWS`.`ntid` = `TOPIC`.`tid` AND `NEWS`.`nid` = ?"
				+ " ORDER BY `ncreateDate` DESC";
		News news = null;
		try {
			rs = this.executeQuery(sql, nid);
			if (rs.next()) {
				news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getTimestamp("ncreateDate"));
				news.setNpicpath(rs.getString("npicPath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getTimestamp("nmodifyDate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNtname(rs.getString("tname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return news;
	}

	public List<News> getAllnewsByTname(String tname) throws SQLException {
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;
		// 获取某主题下的所有新闻
		String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
				+ " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
				+ " WHERE `NEWS`.`ntid` = `TOPIC`.`tid` AND `TOPIC`.`tname` = ?"
				+ " ORDER BY `ncreateDate` DESC";
		try {
			rs = this.executeQuery(sql, tname);
			News news = null;
			while (rs.next()) {
				news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getTimestamp("ncreateDate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNtname(rs.getString("tname"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	// 获得所有新闻的数量
	public int getTotalCount() throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT COUNT(`nid`) FROM `news`";
		int count = -1;
		try {
			rs = this.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return count;
	}

	// 分页获得新闻
	public List<News> getPageNewsList(int pageNo, int pageSize)
			throws SQLException {
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "SELECT `nid`, `ntid`, `ntitle`, `nauthor`,"
				+ " `ncreateDate`, `nsummary`, `tname` FROM `NEWS`, `TOPIC`"
				+ " WHERE `NEWS`.`ntid` = `TOPIC`.`tid`"
				+ " ORDER BY `ncreateDate` DESC LIMIT ?, ?";
		try {
			rs = this.executeQuery(sql, (pageNo - 1) * pageSize, pageSize);
			News news = null;
			while (rs.next()) {
				news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getTimestamp("ncreateDate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setNtname(rs.getString("tname"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(null, null, rs);
		}
		return list;
	}
	//添加一条新闻
	@Override
	public int insertNews(News news) {
		int num = 0;
		String sql = "insert into news(ntid,ntitle,nauthor,ncreatedate,npicpath,ncontent,nmodifydate,nsummary) values(?,?,?,?,?,?,?,?)";
		Object[] params = { news.getNtid(), news.getNtitle(),
				news.getNauthor(), news.getNcreatedate(), news.getNpicpath(),
				news.getNcontent(), news.getNmodifydate(), news.getNsummary() };
		try {
			num = this.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	//删除一条新闻
	@Override
	public int deleteNews(int nid) {
		int num = 0;
		String sql = "delete from news where nid=?";
		Object[] params = { nid };
		try {
			num = this.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	//根据id修改新闻
	public int updateNews(News news) {
		int num = 0;
		String sql = "update news set ntid=?,ntitle=?,nauthor=?,nsummary=?,ncontent=?,nmodifydate=?,npicpath=? where nid=? ";
		Object[] params = {news.getNtid(),news.getNtitle(),news.getNauthor(),news.getNsummary(),news.getNcontent(),news.getNmodifydate(),news.getNpicpath(),news.getNid()};
		try {
			num = this.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
