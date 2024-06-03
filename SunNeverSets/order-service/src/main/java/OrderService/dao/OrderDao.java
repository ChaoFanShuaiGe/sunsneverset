package OrderService.dao;

import model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface OrderDao {

    /*从购物车结算*/
    @Insert("insert into orders(order_id,user_id,product_id,product_num,product_price,order_time,pay_time,status,pay_status) values(#{order_id},#{user_id},#{product_id},#{product_num},#{product_price},now(),now(),1,0)")
    void addOrder(Long order_id, int user_id, int product_id, int product_num, BigDecimal product_price);

    @Select("select * from orders where order_id=#{order_id}")
    List<Order> getOrders(Long order_id);

    @Select("select distinct order_id from orders where user_id=#{user_id} order by order_id desc")
    List<Long> getOrderIdByUser(int user_id);

    @Select("select get_trans_num('order')")
    Long getOrderId();


    @Insert("insert into orders(order_id,user_id,product_id,product_num,product_price,order_time,status,pay_status) values(#{order_id},#{user_id},#{product_id},#{product_num},#{product_price},now(),1,0)")
    void addOrderNoPay(Long order_id, int user_id, int product_id, int product_num, BigDecimal product_price);

    /*付款*/
    @Update("update orders set pay_time=now(),pay_status=1 where user_id=#{user_id} and order_id=#{order_id}")
    int orderPay(Integer user_id, Long order_id);

    /*付款2*/
    @Update("update orders set pay_time=now(),pay_status=1 where order_id=#{order_id}")
    int payOrder(Long order_id);

    @Select("select count(1) from orders where user_id=#{user_id} and order_id=#{order_id}")
    Long findCollect(Long user_id, Long order_id);
    @Delete("delete from orders where user_id=#{user_id} and order_id=#{order_id}")
    void deleteCollect(Long user_id, Long order_id);

    /**
     * 分页获取所有订单
     */
//    @Select("select * from orders where order_id like '%${searchName}%' order by order_time desc limit #{currentPage1},#{pageSize}")
    @Select("select o.*, u.userName from orders o join users u on o.user_id = u.user_id where order_id like '%${searchName}%' order by order_time desc limit #{currentPage1},#{pageSize}")
    List<Order> selectAllOrdersByPage(int currentPage1, int pageSize, String searchName);

    @Select("select count(*) from orders where order_id like '%${searchName}%'")
    int getOrderCount(String searchName);


    //获取订单id列表,这个是带有每个订单的所有商品的
    @Select("select distinct order_id from orders order by order_id desc")
    List<Long> getOrderIdByUser2();

    /**
     *    直接删除订单
     */
    @Delete("delete from orders where order_id = #{order_id}")
    void deleteOrder(Long order_id);
}
