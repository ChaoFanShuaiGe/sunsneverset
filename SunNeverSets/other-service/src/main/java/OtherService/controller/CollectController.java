package OtherService.controller;

import OtherService.service.CollectService;
import OtherService.service.ProductService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/collect",method = {RequestMethod.POST})
public class CollectController {

    @Autowired
    private CollectService collectService;
    
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getCollect")
    public Map getCollect(@RequestBody Map<String,Integer> paramMap, HttpServletRequest req){
        //TODO
        /*String ticket = CookieUtil.getCookieValue(req, "userTicket", false);
        if(StringUtils.isEmpty(ticket)||req.getSession().getAttribute(ticket)==null){
            return RespBeanEnum..getMap();
        }*/
        Integer user_id = paramMap.get("user_id");
        HashMap<String, Object> map = new HashMap<>();
        if(user_id==null){
            map.put("code","004");
            map.put("msg","用户未登录");
        }else{
            List<Collect> collectList = collectService.getCollect(user_id);
            if(collectList==null){
                map.put("code","002");
                map.put("msg","该用户没有收藏的商品");
            }else{
                for(Collect collect:collectList){
                    Product product = productService.getProductByID(collect.getProduct_id());
                    collect.setProduct_name(product.getProduct_name());
                    collect.setProduct_picture(product.getProduct_picture());
                    collect.setProduct_price(product.getProduct_price());
                    collect.setProduct_selling_price(product.getProduct_selling_price());
                    collect.setProduct_title(product.getProduct_title());
                }
                map.put("collectList",collectList);
                map.put("code","001");
            }
        }
        return map;
    }

    @RequestMapping(value = "/addCollect")
    public Map addCollect(@RequestBody Map<String,Integer> paramMap, HttpServletRequest req){
        //TODO
        /*String ticket = CookieUtil.getCookieValue(req, "userTicket", false);
        if(StringUtils.isEmpty(ticket)||req.getSession().getAttribute(ticket)==null){
            return RespBeanEnum..getMap();
        }*/
        Integer user_id = paramMap.get("user_id");
        Integer product_id = paramMap.get("product_id");
        HashMap<String, Object> map = new HashMap<>();
        if(collectService.findCollect(user_id,product_id)<1){   //未收藏
            collectService.addCollect(user_id,product_id);
            map.put("code","001");
            map.put("msg","收藏成功");
        }else{
            map.put("code","003");
            map.put("msg","该商品已经添加收藏，请到我的收藏查看");
        }
        return map;
    }

    @RequestMapping(value = "/deleteCollect")
    public Map deleteCollect(@RequestBody Map<String,Integer> paramMap, HttpServletRequest req){
        //TODO
        /*String ticket = CookieUtil.getCookieValue(req, "userTicket", false);
        if(StringUtils.isEmpty(ticket)||req.getSession().getAttribute(ticket)==null){
            return RespBeanEnum..getMap();
        }*/
        Integer user_id = paramMap.get("user_id");
        Integer product_id = paramMap.get("product_id");
        HashMap<String, String> map = new HashMap<>();
        if(collectService.findCollect(user_id,product_id)>0){   //收藏里存在
            collectService.deleteCollect(user_id,product_id);
            map.put("code","001");
            map.put("msg","取消收藏成功");
        }
        return map;
    }

}
