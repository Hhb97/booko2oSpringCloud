package com.book.service;

import com.book.common.ServerResponse;
import com.book.pojo.Book;

public interface ProtalBookService {
    public ServerResponse<Book> getAllBook(int pageNum,int pageSize);
}
