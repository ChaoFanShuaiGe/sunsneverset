package OrderService.service;
import model.*;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public interface OrderService {

    void addOrder(Long order_id, int user_id, int product_id, int product_num, BigDecimal product_price);

    void addOrderNoPay(Long order_id, int user_id, int product_id, int product_num, BigDecimal product_price);

    List<Order> getOrders(Long order_id);

    List<Long> getOrderIdByUser(int user_id);

    Long getOrderId();

    int orderPay(Integer user_id, Long order_id);

    int payOrder(Long order_id);

    Long findCollect(Long user_id, Long order_id);

    void deleteCollect(Long user_id, Long order_id);

    /**
     * 分页获取所有订单
     */
    List<Order> selectAllOrdersByPage(int currentPage, int pageSize, String searchName);

    int getOrdersCount(String searchName);
    //获取订单id列表
    List<Long> getOrderIdByUser2();

    /**
     *    直接删除订单
     */
    void deleteOrder(Long order_id);
}
