package OrderService.controller;

import OrderService.service.OrderService;
import OrderService.service.ProductService;
import OrderService.service.ShoppingCartService;
import model.*;
import org.springframework.web.bind.annotation.*;
import vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/getOrder")
    public Map getOrder(@RequestBody Map<String,Object> paramMap, HttpServletRequest req){
        //TODO
        /*Map loginMap = CookieUtil.validateLogin(req);
        if(loginMap!=null)
            return loginMap;*/
        Integer user_id = (Integer) paramMap.get("user_id");
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Long> Order_Ids = orderService.getOrderIdByUser(user_id);  //获取订单id
        if(Order_Ids==null){
            return RespBeanEnum.FIND_ORDER_ERROR.getMap();
        }else{
            List<List<Order>> ordersList = new ArrayList<>();   //需要返回前端的list
            for(Long order_id:Order_Ids){
                List<Order> tempList = new ArrayList<>();
                List<Order> orders = orderService.getOrders(order_id);   //同一笔订单的商品
                for(Order order:orders){
                    Product product = productService.getProductByID(order.getProduct_id());
                    order.setProduct_name(product.getProduct_name());
                    order.setProduct_picture(product.getProduct_picture());
                    order.setPay_status(order.getPay_status());
                    tempList.add(order);
                }
                ordersList.add(tempList);
            }
            map.put("orders",ordersList);
            map.put("code","001");
        }
        return map;
    }

    @RequestMapping(value = "/addOrder")
    public Map addOrder(@RequestBody Map<String,Object> paramMap, HttpServletRequest req){
        //TODO
        /*Map loginMap = CookieUtil.validateLogin(req);
        if(loginMap!=null)
            return loginMap;*/
        Integer user_id = (Integer) paramMap.get("user_id");
        List<Map> products = (List<Map>) paramMap.get("products");
        if(user_id==null||products==null){
            return RespBeanEnum.FIND_ORDER_ERROR.getMap();
        }else{
            Long orderId = orderService.getOrderId();
            for (Map<String,Object> product : products) {
                boolean flag = (Boolean)product.get("check");
                if (flag) {  //已勾选的商品
                    int product_id = (int)(product.get("productID"));
                    BigDecimal product_price = new BigDecimal((Integer)product.get("price"));
                    int product_num = (int)(product.get("num"));
                    orderService.addOrder(orderId,user_id,product_id,product_num,product_price);  //添加订单

                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("user_id", user_id);
                    map.put("product_id", product_id);
                    Map result = shoppingCartService.deleteShoppingCart(map);//删除购物车
                }
            }
            return RespBeanEnum.ORDER_SUCCESS.getMap();
        }
    }

    @RequestMapping(value = "/orderPay")
    public Map orderPay(@RequestBody Map<String,Object> paramMap, HttpServletRequest req){
        //TODO
        /*Map loginMap = CookieUtil.validateLogin(req);
        if(loginMap!=null)
            return loginMap;*/
        Integer user_id = (Integer) paramMap.get("user_id");
        Long order_id = (Long) paramMap.get("order_id");
        int excute = orderService.orderPay(user_id, order_id);
        if(excute>0){
            return RespBeanEnum.PAY_SUCCESS.getMap();
        }else{
            return RespBeanEnum.PAY_ERROR.getMap();
        }
    }


    @RequestMapping(value = "/deleteOrder")
    public Map deleteCollect(@RequestBody Map<String,Long> paramMap){
//        String ticket = CookieUtil.getCookieValue(req, "userTicket", false);
//        if(StringUtils.isEmpty(ticket)||req.getSession().getAttribute(ticket)==null){
//            return RespBeanEnum.WITHOUT_LOGIN.getMap();
//        }
        Long user_id = paramMap.get("user_id");
        Long order_id = paramMap.get("order_id");
        System.out.println("order_id :   " + order_id);
        HashMap<String, String> map = new HashMap<>();
        if(orderService.findCollect(user_id,order_id)>0){   //存在
            orderService.deleteCollect(user_id,order_id);
            map.put("code","001");
            map.put("msg","删除订单成功");
        }
        return map;
    }

    /**
     * 分页获取所有订单
     */
    @GetMapping("/getOrdersByPage")
    public Map getOrdersByPage(@RequestParam int currentPage, int pageSize, String searchName){
        List<Order> orders = orderService.selectAllOrdersByPage(currentPage, pageSize, searchName);
        int ordersCount = orderService.getOrdersCount(searchName) ;
        List<Long> Order_Ids2 = orderService.getOrderIdByUser2();
        List<List<Order>> ordersList = new ArrayList<>();   //需要返回前端的list，这个是带有每个订单的商品的
        for(Long order_id:Order_Ids2){
            List<Order> tempList = new ArrayList<>();
            List<Order> orders1 = orderService.getOrders(order_id);   //同一笔订单的商品
            for(Order order:orders1){
                Product product = productService.getProductByID(order.getProduct_id());
                order.setProduct_name(product.getProduct_name());
                order.setProduct_picture(product.getProduct_picture());
                order.setPay_status(order.getPay_status());
                tempList.add(order);
            }
            ordersList.add(tempList);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("orders",orders);
        map.put("ordersList",ordersList);

        map.put("total",ordersCount);
        map.put("code","001");
        map.put("msg","成功获取订单");
        return map;
    }


    /**
     *    直接删除订单
     */
    @DeleteMapping("/deleteOrder")
    public Map deleteOrder(@RequestParam Long order_id){
        System.out.println("order_id :   " + order_id);
        HashMap<String, String> map = new HashMap<>();
        orderService.deleteOrder(order_id);
        map.put("code","001");
        map.put("msg","删除订单成功");

        return map;
    }
}
