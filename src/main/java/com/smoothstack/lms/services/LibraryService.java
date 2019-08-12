package com.smoothstack.lms.services;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.impl.csv.CsvRepository;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.BookSupply;
import com.smoothstack.lms.models.Library;

public class LibraryService extends DaoServiceImpl<Library> {
    private static LibraryService _service;

    private DaoRepository<BookSupply> bookSupplyRepo;
    private DaoRepository<Book> bookRepo;
    public LibraryService(DaoRepository<Library> repo_, DaoRepository<BookSupply> bookSupplyRepo_,
        DaoRepository<Book> bookRepo_
    ) {
        super(repo_);
        this.bookSupplyRepo = bookSupplyRepo_;
        this.bookRepo = bookRepo_;

	}

	public static LibraryService getLibraryService() {
        if (_service == null) {
            synchronized (LibraryService.class) {
                if (_service == null) {
                    _service = new LibraryService(
                        new CsvRepository<Library>("",""),
                        new CsvRepository<BookSupply>("", ""),
                        new CsvRepository<Book>("", "")
                    );
                }
            }
        }
		return _service;
    }
    public Dao<BookSupply> getAmounts(Dao<Library> library) {
        return null;
    }

    public Dao<Book> getBooks(Dao<Library> library) {
        return null;
    }

}
