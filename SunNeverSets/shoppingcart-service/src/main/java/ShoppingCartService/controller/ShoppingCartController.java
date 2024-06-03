package ShoppingCartService.controller;


import ShoppingCartService.service.ShoppingCartService;
import model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/shoppingCart")

public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/updateShoppingCart")
    public Map updateShoppingCart(@RequestBody Map<String,Integer> paramMap, HttpServletRequest req){
        Integer user_id = paramMap.get("user_id");
        HashMap<String, String> map = new HashMap<>();
        int product_id = paramMap.get("product_id");
        int num = paramMap.get("num");
        if(num<1){     //数量不合法
            map.put("code","004");
            map.put("msg","数量不合法");
            return map;
        }
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCart(user_id, product_id);
        if(shoppingCart.getNum()==num){
            map.put("code","003");
            map.put("msg","数量没有发生变化");
            return map;
        }
         //商品已在购物车
        shoppingCartService.updateShoppingCart(user_id,product_id,num);
        map.put("code","001");
        map.put("msg","修改购物车数量成功");
        return map;
    }

    @RequestMapping("/addShoppingCart")
    public Map addShoppingCart(@RequestBody Map<String,Integer> paramMap, HttpServletRequest req){
        Integer user_id = paramMap.get("user_id");
        Integer product_id = paramMap.get("product_id");
        HashMap<String, Object> map = new HashMap<>();
        if(user_id==null||product_id==null){
            map.put("code","004");
            map.put("msg","添加购物车失败");
        }else{
            int u = user_id,p = product_id;
            ShoppingCart tempCart = shoppingCartService.findShoppingCart(u, p);
            if(tempCart==null){   //商品不在购物车
                shoppingCartService.addShoppingCart(user_id,product_id);
                map.put("code","001");
            }else{     //商品在购物车，数量加一
                shoppingCartService.updateShoppingCart(user_id,product_id,tempCart.getNum()+1);
                map.put("code","002");
            }
            ShoppingCart shoppingCart = shoppingCartService.findShoppingCart(user_id, product_id);
            map.put("msg","添加购物车成功");
            map.put("shoppingCartData",shoppingCart);
        }
        return map;
    }

    @RequestMapping("/getShoppingCart")
    public Map getShoppingCart(@RequestBody Map<String,Integer> paramMap, HttpServletRequest req){
        Integer user_id = paramMap.get("user_id");
        HashMap<String, Object> map = new HashMap<>();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCart(user_id);
        Map[] shoppingCartData = shoppingCartService.getShoppingCartData(shoppingCarts);
        map.put("shoppingCartData",shoppingCartData);
        map.put("code","001");
        map.put("msg","成功获取购物车");
        return map;
    }

    @RequestMapping(value = "/deleteShoppingCart")
    public Map deleteShoppingCart(@RequestBody Map<String,Integer> paramMap){
        Integer user_id = paramMap.get("user_id");
        Integer product_id = paramMap.get("product_id");
        boolean isExecute = shoppingCartService.deleteShoppingCart(user_id, product_id);
        HashMap<String, String> map = new HashMap<>();
        if(isExecute){
            map.put("code","001");
            map.put("msg","删除成功");
        }else{
            map.put("code","003");
            map.put("msg","删除失败");
        }
        return map;
    }
//    @RequestMapping(value = "/deleteShoppingCartService")
//    public void deleteShoppingCart(@RequestParam("user_id") int user_id,@RequestParam("product_id") int product_id){
//        boolean isExecute = shoppingCartService.deleteShoppingCart(user_id, product_id);
//    }



}
