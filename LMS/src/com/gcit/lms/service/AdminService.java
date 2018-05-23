package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService {

	public ConnectionUtil connUtil= new ConnectionUtil();
	
	public void saveAuthor(Author author) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO adao=new AuthorDAO(conn);
			adao.addAuthor(author);
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
	

	public void saveGenre(Genre genre) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			gndao.addGenre(genre);
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
	

	
	public void saveBook(Book book) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bdao=new BookDAO(conn);
			Integer bookId=bdao.addBookWithId(book);
			
			for(Author a : book.getAuthors())
			{
				bdao.addBookAuthors(bookId, a.getAuthorId());
			}
			
			for(Genre g: book.getGenres())
			{
				bdao.addBookGenres(bookId, g.getGenreId());
			}
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
	
	

	
	
	public void savePublisher(Publisher publisher) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			pdao.addPublisher(publisher);
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
	
	public List<Publisher> readPublisher() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			List<Publisher> publishers=pdao.ReadAllPublishers();
			conn.commit();
			return publishers;
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
	
	public List<Author> readAuthor() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO authdao=new AuthorDAO(conn);
			List<Author> authors=authdao.ReadAllAuthors();
			conn.commit();
			return authors;
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
	

	

	
	
	public List<BookLoans> readAllBookLoans() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookLoansDAO bldao=new BookLoansDAO(conn);
			List<BookLoans> bookLoans=bldao.ReadAllBookLoans();
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
	
	

	
	public List<Genre> readGenre() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			List<Genre> genres=gndao.ReadAllGenres();
			conn.commit();
			return genres;
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
	
	
	
	public List<Book> readBook() throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bookdao=new BookDAO(conn);
			List<Book> books=bookdao.ReadAllBooks();
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
	
	public void updatePublisher(Publisher publisher) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			pdao.updatePublisher(publisher);
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
	

	
	public void updateGenre(Genre genre) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			gndao.updateGenre(genre);
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
	
	

	
	
	
	public void updateAuthor(Author author) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO authdao=new AuthorDAO(conn);
			authdao.updateAuthor(author);
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
	
	

	
	public void updateBook(Book book) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bookdao=new BookDAO(conn);
			bookdao.updateBook(book);
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
	

	
	public void deletePublisher(Publisher publisher) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			PublisherDAO pdao=new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
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
	
	public void deleteBook(Book book) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			BookDAO bookdao=new BookDAO(conn);
			bookdao.deleteBook(book);
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
	
	public void deleteAuthor(Author author) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			AuthorDAO authdao=new AuthorDAO(conn);
			authdao.deleteAuthor(author);
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
	
	public void deleteGenre(Genre genre) throws SQLException
	{
		Connection conn=null;
		try {
			conn=connUtil.getConnection();
			GenreDAO gndao=new GenreDAO(conn);
			gndao.deleteGenre(genre);
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
