package productServiceConsumer.service;



import model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import productServiceConsumer.service.impl.ProductServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@FeignClient(name = "product-service-provider",fallback = ProductServiceImpl.class)
public interface ProductService {
    @PostMapping("/getCategory")
    public Map getCategory();

    @RequestMapping(value = "/getAllProduct")
    public Map getAllProduct(@RequestBody Map<String, Object> paramMap);

    @RequestMapping(value = "/getProductByCategory")
    public Map getProductByCategory(@RequestBody Map<String, Object> paramMap);

    @RequestMapping(value = "/getPromoProduct")
    public Map getPromoProduct(@RequestBody Map<String, String> paramMap);

    @RequestMapping(value = "/getHotProduct")
    public Map getHotProduct(@RequestBody Map<String, List<String>> paramMap);

    @RequestMapping(value = "/getDetails")
    public Map getDetails(@RequestBody Map<String, Integer> paramMap);

    @RequestMapping(value = "/getDetailsPicture")
    public Map getDetailsPicture(@RequestBody Map<String, Integer> paramMap);

    @RequestMapping(value = "/getProductBySearch")
    public Map getProductBySearch(@RequestBody Map<String, Object> paramMap);

    //    ---------------------------------后台-----------------------------------
    /**
     * 后台商品分页查询
     */
    @GetMapping("/getProductsByPage")
    public Map getAllProduct(@RequestParam("currentPage") int currentPage,@RequestParam("pageSize") int pageSize,@RequestParam("searchName") String searchName);

    /**
     * 新增商品
     */
    @PostMapping("/addProduct")
    public Map addProduct(@RequestBody Product product);


    /**
     * 编辑商品
     */
    @PostMapping("/updateProduct")
    public Map updateProduct(@RequestBody Product product);

    /**
     *  删除商品
     */
    @DeleteMapping("/deleteProduct")
    public Map deleteProduct(@RequestParam int product_id);

    /**
     * 获取热销商品
     */
    @GetMapping("/getGoodGood")
    public Map getGoodGood(@RequestParam int order);


}
