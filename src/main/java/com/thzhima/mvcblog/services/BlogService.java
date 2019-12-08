package com.thzhima.mvcblog.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.thzhima.mvcblog.beans.Blog;
import com.thzhima.mvcblog.dao.BlogDAO;

@Service
public class BlogService {

	@Autowired
	private BlogDAO dao;
	
	
	/**
	 * �����û�ID�����Ҳ��͡�
	 * @param userID
	 * @return �������Blog����
	 */
	public Blog findByUserID(int userID) {
		return dao.findByUserID(userID);
	}
	
	
	/**
	 * ���벩��
	 * @param userID
	 * @param blogName
	 * @param photoName
	 * @return �ɹ�����true.
	 */
	public boolean apply(int userID,String blogName,String photoName) {
		boolean ok = false;
		if(1 == this.dao.add(userID, blogName, photoName)) {
			ok=true;
		}
		return ok;
	}
	
	
}
