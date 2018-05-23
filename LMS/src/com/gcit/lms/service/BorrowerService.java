package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;

public class BorrowerService {
	
	public ConnectionUtil connUtil= new ConnectionUtil();
	
	public void saveBookLoan(BookLoans bookLoan) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			bldao.addBookLoans(bookLoan);
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
	
	
	public void returnBookLoan(BookLoans bookLoan) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			bldao.returnBookLoans(bookLoan);
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
	
	public void loanBookCopies(BookCopies bookCopy) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookCopiesDAO bcdao=new BookCopiesDAO(conn);
			bcdao.LoanBookCopies(bookCopy);
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
	
	public List<BookLoans> ReadBookLoansByUserBranch(Integer branchId,Integer cardNo) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			List<BookLoans> bookLoans=bldao.ReadBookLoansByUserBranch(branchId, cardNo);
			conn.commit();
			return bookLoans;
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
	
	public List<Book> ReadBooksByBookID(BookLoans bookLoan) throws SQLException  //for return book
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bdao=new BookDAO(conn);
			List<Book> books=bdao.ReadBooksByBookID(bookLoan);
			conn.commit();
			return books;
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
	
	public List<Borrower> readBorrower() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			List<Borrower> borrowers=brdao.ReadAllBorrowers();
			conn.commit();
			return borrowers;
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
	
	public void changeDueDate( Integer bookId,Integer branchId,Integer cardNo,Date dueDate) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			bldao.changeDueDate(bookId, branchId, cardNo, dueDate);
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
	
	public void updateBorrower(Borrower borrower) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			brdao.updateBorrower(borrower);
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
	
	public void deleteBorrower(Borrower borrower) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			brdao.deleteBorrower(borrower);
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
	
	public void saveBorrower(Borrower borrower) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BorrowerDAO brdao=new BorrowerDAO(conn);
			brdao.addBorrower(borrower);
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

}
