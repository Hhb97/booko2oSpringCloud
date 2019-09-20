package com.book.service;

        import com.book.common.ServerResponse;
        import com.book.ov.OrderVo;

        import javax.servlet.http.HttpSession;

public interface OrderService {
    public ServerResponse<OrderVo> addOrder(Integer userId, String cartIdlist, Integer shippingId);
    public ServerResponse payment(Long orderNo, Integer platform, HttpSession session);
    public ServerResponse orderCanel(Integer orderid);
    public ServerResponse getOrders(HttpSession session,Integer paeNum,Integer pageSize);
    public ServerResponse getOrderInfo(Long orderNo);
    public ServerResponse sendOver(Long orderNo);
    public ServerResponse bookApperaise(String desc,Integer bookId,HttpSession session,Long orderNo);
    public ServerResponse orderOver(Long orderNo);
    public ServerResponse getAppraises(Integer pageNum,Integer pageSize,HttpSession session);
    public ServerResponse deleteAppraise(Integer id);
}
