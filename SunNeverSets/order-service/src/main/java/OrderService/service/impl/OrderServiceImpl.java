package OrderService.service.impl;

import OrderService.dao.OrderDao;
import OrderService.service.OrderService;
import OrderService.service.ProductService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void addOrder(Long order_id,int user_id, int product_id, int product_num, BigDecimal product_price) {
        orderDao.addOrder(order_id,user_id,product_id,product_num,product_price);
    }

    @Override
    public void addOrderNoPay(Long order_id,int user_id, int product_id, int product_num, BigDecimal product_price) {
        orderDao.addOrderNoPay(order_id,user_id,product_id,product_num,product_price);
    }


    @Override
    public List<Order> getOrders(Long order_id) {
        return orderDao.getOrders(order_id);
    }

    @Override
    public List<Long> getOrderIdByUser(int user_id) {
        return orderDao.getOrderIdByUser(user_id);
    }


    @Override
    public Long getOrderId() {
        return orderDao.getOrderId();
    }

    @Override
    public int orderPay(Integer user_id, Long order_id) {
        if(null==user_id||null==order_id)
            return 0;
        List<Order> orders = orderDao.getOrders(order_id);
        //更新redis中的销量
        Map<Object, Object> sales_num = redisTemplate.opsForHash().entries("sales_num");
        for(Order order:orders){
            int product_id = order.getProduct_id();
            int product_num = order.getProduct_num();
            /*
            if(sales_num.isEmpty()||sales_num.get(product_id+"")==null){
                sales_num.put(product_id+"",product_num);
            }else{
                sales_num.put(product_id+"",(int)sales_num.get(product_id+"")+product_num);
            }
            redisTemplate.opsForHash().putAll("sales_num",sales_num);*/
            productService.updateSales(product_id,product_num);
        }
        return orderDao.orderPay(user_id,order_id);
    }
    /*付款2*/
    @Override
    public int payOrder(Long order_id) {
        if(null==order_id)
            return 0;
        List<Order> orders = orderDao.getOrders(order_id);
        //更新redis中的销量
        Map<Object, Object> sales_num = redisTemplate.opsForHash().entries("sales_num");
        for(Order order:orders){
            int product_id = order.getProduct_id();
            int product_num = order.getProduct_num();
            /*if(sales_num.isEmpty()||sales_num.get(product_id+"")==null){
                sales_num.put(product_id+"",product_num);
            }else{
                sales_num.put(product_id+"",(int)sales_num.get(product_id+"")+product_num);
            }*/
            //redisTemplate.opsForHash().putAll("sales_num",sales_num);
            productService.updateSales(product_id,product_num);
        }
        return orderDao.payOrder(order_id);
    }

    @Override
    public Long findCollect(Long user_id, Long order_id) {
        return orderDao.findCollect(user_id,order_id);
    }

    @Override
    public void deleteCollect(Long user_id, Long order_id) {
        orderDao.deleteCollect(user_id,order_id);
    }

    /**
     * 分页获取所有订单
     */
    public List<Order> selectAllOrdersByPage(int currentPage, int pageSize, String searchName) {
        int currentPage1 = (currentPage-1)*pageSize;
        return orderDao.selectAllOrdersByPage(currentPage1, pageSize, searchName);
    }

    @Override
    public int getOrdersCount(String searchName) {
        return orderDao.getOrderCount(searchName);
    }

    //获取订单id列表
    public List<Long> getOrderIdByUser2() {
        return orderDao.getOrderIdByUser2();
    }

    /**
     *    直接删除订单
     */
    public void deleteOrder(Long order_id) {
        orderDao.deleteOrder(order_id);
    }
}
