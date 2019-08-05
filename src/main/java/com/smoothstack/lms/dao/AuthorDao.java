package com.smoothstack.lms.dao;

import com.smoothstack.lms.dao.BookDao;
import java.util.List;

public interface AuthorDao {
    public void putBook(BookDao b);
    public void removeBook(BookDao b);
    public List<BookDao> getBooks();
    public int getId();
    public String getFirstName();
    public void setFirstName(String fName);
    public String getLastName();
    public void setLastName(String lName);
	public String csv();
}