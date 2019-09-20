package com.book.service;

import com.book.common.ServerResponse;
import com.book.ov.CartVo;

import javax.servlet.http.HttpSession;

public interface CartService {
    public ServerResponse add(HttpSession session, Integer bookId, Integer count);
    public ServerResponse<CartVo> update(Integer userId, Integer bookId, Integer count);
    public  ServerResponse<CartVo> cartLimit(Integer userid);
    public ServerResponse<CartVo> delete(Integer userId,Integer bookId);
    public ServerResponse<CartVo> isChecked(Integer userId,Integer bookId,Integer checked);
    public boolean isNull(Integer userid);
}
