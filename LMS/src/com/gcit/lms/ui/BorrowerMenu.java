package com.gcit.lms.ui;

import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibraryService;

public class BorrowerMenu {
	public void BorrowerOptions()
	{
		
		AdminService ads=new AdminService();
		BorrowerService brs=new BorrowerService();
		LibraryService lbs=new LibraryService();
		
		int loop1=1;
		Borrower brChosen=new Borrower();
		while(loop1!=0)
		{
			System.out.println("Enter the your Card Number or enter 0 to quit");
			Scanner scan=new Scanner(System.in);
			int cardNo=Integer.parseInt(scan.nextLine());
			if(cardNo==0)
			{
				break;
			}
			
			List<Borrower> borrowers=new ArrayList<>();
			try {
				borrowers=brs.readBorrower();

				for(Borrower br:borrowers)
				{
						
					if(br.getCardNo()==cardNo)
					{
						System.out.println("valid id ");
						brChosen=br;
						loop1=-1;  //-1 means found a record
						break;
					}
					else 
					{
						loop1=-2; //-2 means didn't find a record
					}
				}
				
				if(loop1==-1)
				{
					int options1=1;
					while(options1!=0)
					{
						System.out.println("1> Check Out a Book");
						System.out.println("2> Return a Book");
						System.out.println("Enter 0 to return to previous Menu");
						
						scan=new Scanner(System.in);
						options1=Integer.parseInt(scan.nextLine());
						
						if(options1==0)
						{
							break;
						}
						
						if(options1==1)  //check out a book
						{
							int loop3=1;
							while(loop3!=0)
							{
								
								List<LibraryBranch> libraryBranches=new ArrayList<>();
								try {
									libraryBranches=lbs.readLibraryBranch();

									System.out.println("BranchId \t BranchName \t BranchAddress");
									for(LibraryBranch lb:libraryBranches)
									{

										System.out.println(lb.getBranchId()+"\t \t"+lb.getBranchName()+ "\t \t "+lb.getBranchAddress());
									}


								} catch (SQLException e) {
									e.printStackTrace();
								}
								
								
								System.out.println("Pick the BranchId you want to check out from");
								System.out.println("Enter 0 to return to previous Menu");
								scan=new Scanner(System.in);
								loop3=Integer.parseInt(scan.nextLine());
								
								LibraryBranch lbChosen=new LibraryBranch();
								for(LibraryBranch lb:libraryBranches)
								{

									if(lb.getBranchId()==loop3)
									{
										lbChosen=lb;
									}
								}
								
								if(loop3==0)
								{
									break;
								}
								else
								{
									int loop4=1;
									while(loop4!=0)
									{
										
										List<Book> books=new ArrayList<>();
										try {
											books=ads.readBook();

											System.out.println("BookId \t \t  BookTitle");
											for(Book book1:books)
											{
												//list only those books which are available in the branch
												List<BookCopies> bcCopies=new ArrayList<>();
												bcCopies= lbs.readBookCopiesById(book1.getBookId(), lbChosen.getBranchId());
												if(!bcCopies.isEmpty())
												{
													if(bcCopies.get(0).getNoOfCopies()!=0)
													{
														System.out.println(book1.getBookId()+"\t \t"+book1.getTitle());
													}
												}
												
											}

										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										System.out.println("Pick the BookId you want to check out");
										System.out.println("Enter 0 to return to previous Menu");
										scan=new Scanner(System.in);
										loop4=Integer.parseInt(scan.nextLine());
										if(loop4==0)
										{
											break;
										}
										else 
										{
											// final code to checkout the book
											//add entry to bookLoans  bookId-loop4  branchId-lbChosen.getBranchId()  cardNo
											
										    Date dateOut = new Date();
										    Calendar c = Calendar.getInstance();
										    c.setTime(dateOut);
										    c.add(Calendar.DATE, 7);
											
											
											BookLoans bookLoan=new BookLoans();
											bookLoan.setBookId(loop4);
											bookLoan.setBranchId(lbChosen.getBranchId());
											bookLoan.setCardNo(cardNo);
											bookLoan.setDateOut(dateOut);
											bookLoan.setDueDate(c.getTime());
											
											
											brs.saveBookLoan(bookLoan);
											
											
											//add entry to book copies subtract 1 from noOfCopies  bookId-loop4  branchId-lbChosen.getBranchId()
											
											//first get the noOfCopies
											
											List<BookCopies> bookCopiesLoan=lbs.readBookCopiesById(loop4, lbChosen.getBranchId());
											
											BookCopies bookCopyLoan=new BookCopies();
											bookCopyLoan=bookCopiesLoan.get(0);
											
											BookCopies bookCopyLoan2=new BookCopies();
											bookCopyLoan2.setBookId(loop4);
											bookCopyLoan2.setBranchId(lbChosen.getBranchId());
											bookCopyLoan2.setNoOfCopies(bookCopyLoan.getNoOfCopies()-1);
											
											brs.loanBookCopies(bookCopyLoan2);
											
										}
										
										
									} //loop4 ends here
									
									
								} //else loop ends here
								
							} //while loop ends
							
							
						}  // if loop ends for check out
						
						if(options1==2)  // return a book
						{
							int loop3=1;
							while(loop3!=0)
							{
								List<LibraryBranch> libraryBranches=new ArrayList<>();
								try {
									libraryBranches=lbs.readLibraryBranch();

									System.out.println("BranchId \t BranchName \t BranchAddress");
									for(LibraryBranch lb:libraryBranches)
									{

										System.out.println(lb.getBranchId()+"\t \t"+lb.getBranchName()+ "\t \t "+lb.getBranchAddress());
									}


								} catch (SQLException e) {
									e.printStackTrace();
								}
								
								
								System.out.println("Pick the BranchId you want to return Book to");
								System.out.println("Enter 0 to return to previous Menu");
								scan=new Scanner(System.in);
								loop3=Integer.parseInt(scan.nextLine());
								
								LibraryBranch lbChosen=new LibraryBranch();
								for(LibraryBranch lb:libraryBranches)
								{

									if(lb.getBranchId()==loop3)
									{
										lbChosen=lb;
									}
								}
								
								if(loop3==0)
								{
									break;
								}
								else
								{
									int loop4=1;
									while(loop4!=0)
									{
										//get bookId from tbl_book_loans based on brnchId-lbChosen  and cardNo

										List<BookLoans> bookLoansUserBranch=new ArrayList<>();
										bookLoansUserBranch=brs.ReadBookLoansByUserBranch(lbChosen.getBranchId(), cardNo);
										if(bookLoansUserBranch.isEmpty())
										{
											loop4=0;
											System.out.println("---No Books Loaned from this Branch Try again----");
											break;
										}
										if(!bookLoansUserBranch.isEmpty())
										{
											System.out.println("BookId \t \t  BookTitle");
											for(BookLoans bl: bookLoansUserBranch)
											{
												List<Book> bk=brs.ReadBooksByBookID(bl);
												System.out.println(bk.get(0).getBookId()+"\t \t"+bk.get(0).getTitle());
											}
											

//											List<Book> books=new ArrayList<>();
//											try {
//												books=ads.readBook();
//
//												System.out.println("BookId \t \t  BookTitle");
//												for(Book book1:books)
//												{
//													//list only those books which are available in the branch
//													List<BookCopies> bcCopies=new ArrayList<>();
//													bcCopies= ads.readBookCopiesById(book1.getBookId(), lbChosen.getBranchId());
//													if(!bcCopies.isEmpty())
//													{
//														if(bcCopies.get(0).getNoOfCopies()!=0)
//														{
//															System.out.println(book1.getBookId()+"\t \t"+book1.getTitle());
//														}
//													}
//
//												}
//
//											} catch (SQLException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}   								//try catch ends here

											System.out.println("Pick the BookId you want to return");
											System.out.println("Enter 0 to return to previous Menu");
											scan=new Scanner(System.in);
											loop4=Integer.parseInt(scan.nextLine());  //now loo4 is the bookId
											if(loop4==0)
											{
												break;
											}
											else 
											{
												// final code to return the book
												//update entry to bookLoans  bookId-loop4  branchId-lbChosen.getBranchId()  cardNo

												Date dateIn = new Date();



												BookLoans bookLoan=new BookLoans();
												bookLoan.setBookId(loop4);
												bookLoan.setBranchId(lbChosen.getBranchId());
												bookLoan.setCardNo(cardNo);
												bookLoan.setDateIn(dateIn);

												brs.returnBookLoan(bookLoan);


												//update entry to book copies Add 1 to noOfCopies  bookId-loop4  branchId-lbChosen.getBranchId()

												//first get the noOfCopies

												List<BookCopies> bookCopiesLoan=lbs.readBookCopiesById(loop4, lbChosen.getBranchId());

												BookCopies bookCopyLoan=new BookCopies();
												bookCopyLoan=bookCopiesLoan.get(0);

												BookCopies bookCopyLoan2=new BookCopies();
												bookCopyLoan2.setBookId(loop4);
												bookCopyLoan2.setBranchId(lbChosen.getBranchId());
												bookCopyLoan2.setNoOfCopies(bookCopyLoan.getNoOfCopies()+1);

												brs.loanBookCopies(bookCopyLoan2);

											}

										}

									} //while loop4 ends here

									
								} //else loop ends here
								
							}
							
						}
						
						
					}
					
					
					
				}
				
				
				if(loop1==-2)
				{
					System.out.println("Invalid Card No. Entered try again!!!! ");
				}
				
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//while ends
		
		LibraryManagementSystem lms=new LibraryManagementSystem();
		  lms.StartApplication();
		
	}

}
