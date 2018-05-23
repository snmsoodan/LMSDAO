package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;

public class LibraryService {
	
	public ConnectionUtil connUtil= new ConnectionUtil();
	
	
	public void saveBookCopy(BookCopies bookCopy) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			bcdao.addBookCopies(bookCopy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	
	public List<BookCopies> readBookCopiesById(Integer bookId,Integer branchId) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			List<BookCopies> bookCopies=bcdao.ReadBookCopiesById(bookId, branchId);
			conn.commit();
			return bookCopies;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}
	
	public void updateBookCopies(BookCopies bookCopy) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			bcdao.updateBookCopies(bookCopy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			lbdao.updateLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			lbdao.deleteLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public void saveLibraryBranch(LibraryBranch libraryBranch) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			lbdao.addLibraryBranch(libraryBranch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
	}
	
	public List<LibraryBranch> readLibraryBranch() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			LibraryBranchDAO lbdao=new LibraryBranchDAO(conn);
			List<LibraryBranch> libraryBranches=lbdao.ReadAllLibraryBranches();
			conn.commit();
			return libraryBranches;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null)
			{
				conn.rollback();
			}
		}
		finally
		{ 	
			
			if(conn!=null)
			{
				conn.close();
			}
			
		}
		return null;
	}

}
