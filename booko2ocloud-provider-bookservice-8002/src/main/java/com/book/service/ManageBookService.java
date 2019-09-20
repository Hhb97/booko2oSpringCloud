package com.book.service;

import com.book.common.ServerResponse;
import com.book.pojo.Book;

import javax.servlet.http.HttpSession;

public interface ManageBookService {
    public ServerResponse getList(Integer pageNum,Integer pageSize,Integer categoryId);
    public ServerResponse<Book> getInfoById(Integer bookId);
    public ServerResponse<String> updateBookById(Integer bookId,Book book);
    public ServerResponse<String> addBook(Book book, HttpSession session);
    public ServerResponse getAppraise(Integer id);
    public ServerResponse getbookBycategoryId(Integer pageNum,Integer pageSize,Integer categoryId);
    public ServerResponse deletebook(Integer bookId);
}
