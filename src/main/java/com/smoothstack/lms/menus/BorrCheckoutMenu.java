package com.smoothstack.lms.menus;

import java.io.BufferedReader;
import java.io.IOException;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.BookSupply;
import com.smoothstack.lms.models.Library;
import com.smoothstack.lms.services.BookSupplyService;
import com.smoothstack.lms.services.LibraryService;

public class BorrCheckoutMenu implements IMenu {

    public BorrCheckoutMenu(int id) {}
    @Override
    public void call(BufferedReader br) {
        System.out.println("Pick the Branch you want to check out from:");
        for (Dao<Library> l : LibraryService.getLibraryService().list()) {
            System.out.println(String.valueOf(l.getId())+". "+l.getData().getName());
        }
        System.out.println("");
        int libraryId = -1;
        do {
            try {
                libraryId = Integer.parseInt(br.readLine());
                Dao<Library> l = LibraryService.getLibraryService().findById(libraryId);
                if (l==null) {
                    libraryId = -1;
                    System.out.println("No library found with the ID specified, try again "+
                        "or enter 0 to go back");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number of a given library, try again "+
                    "or enter 0 to go back");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } while (libraryId == -1);
        System.out.println("Select a book to check it out");
        BookSupplyService bss = BookSupplyService.getBookSupplyService();
        for (Dao<BookSupply> bs : bss.listAllFromLibrary(libraryId)
        ) {
            Dao<Book> bk = bss.getBook(bs);
            System.out.println(String.valueOf(bs.getId()+". "+bk.getData().getTitle()));
        }
        int bookCheckout = -1;
        BookLoanService ls;
        do {
            ls = BookLoanService.getLoanService();
            try {
                bookCheckout = Integer.parseInt(br.readLine());
                Dao<Book> = 
            } catch (NumberFormatException e) {
                System.out.println()
            } catch (IOException e) {

            }
        } while (bookCheckout == -1);
    }

    @Override
    public IMenu parentMenu(BufferedReader br) {
		return null;
    }
}
