package productServiceConsumer.controller;


import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import productServiceConsumer.service.ProductService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/getCategory")
    public Map getCategory() {
        return productService.getCategory();
    }

    @RequestMapping(value = "/getAllProduct")
    public Map getAllProduct(@RequestBody Map<String, Object> paramMap) {
        return productService.getAllProduct(paramMap);
    }

    @RequestMapping(value = "/getProductByCategory")
    public Map getProductByCategory(@RequestBody Map<String, Object> paramMap) {
        return productService.getProductByCategory(paramMap);
    }

    @RequestMapping(value = "/getPromoProduct")
    public Map getPromoProduct(@RequestBody Map<String, String> paramMap) {
        return productService.getPromoProduct(paramMap);
    }

    @RequestMapping(value = "/getHotProduct")
    public Map getHotProduct(@RequestBody Map<String, List<String>> paramMap) {
        return productService.getHotProduct(paramMap);
    }

    @RequestMapping(value = "/getDetails")
    public Map getDetails(@RequestBody Map<String, Integer> paramMap) {
        return productService.getDetails(paramMap);
    }

    @RequestMapping(value = "/getDetailsPicture")
    public Map getDetailsPicture(@RequestBody Map<String, Integer> paramMap) {
        return productService.getDetailsPicture(paramMap);
    }

    @RequestMapping(value = "/getProductBySearch")
    public Map getProductBySearch(@RequestBody Map<String, Object> paramMap) {
        return productService.getProductBySearch(paramMap);
    }

    //    ---------------------------------后台-----------------------------------
    /**
     * 后台商品分页查询
     */
    @GetMapping("/getProductsByPage")
    public Map getAllProduct(@RequestParam("currentPage") int currentPage,@RequestParam("pageSize") int pageSize,@RequestParam("searchName") String searchName) {
        return productService.getAllProduct(currentPage,pageSize,searchName);
    }

    /**
     * 新增商品
     */
    @PostMapping("/addProduct")
    public Map addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }


    /**
     * 编辑商品
     */
    @PostMapping("/updateProduct")
    public Map updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    /**
     *  删除商品
     */
    @DeleteMapping("/deleteProduct")
    public Map deleteProduct(@RequestParam int product_id){
        return productService.deleteProduct(product_id);
    }

    /**
     * 获取热销商品
     */
    @GetMapping("/getGoodGood")
    public Map getGoodGood(@RequestParam int order){
        return productService.getGoodGood(order);
    }


}
