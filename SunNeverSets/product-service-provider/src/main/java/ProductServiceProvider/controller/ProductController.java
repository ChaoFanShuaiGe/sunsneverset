package ProductServiceProvider.controller;

import ProductServiceProvider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/getCategory")
    public Map getCategory() {
        List<Category> category = productService.getCategory();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("category", category);
        return map;
    }

    @RequestMapping(value = "/getAllProduct")
    public Map getAllProduct(@RequestBody Map<String, Object> paramMap) {
        System.out.println("paraMap" + paramMap);
        int count = 0;
        Object page = "";
        Object pageSize = "";

        for (String key : paramMap.keySet()) {
            if (count == 1) {
                page = paramMap.get(key);
                // 使用firstValue
            } else if (count == 2) {
                pageSize = paramMap.get(key);
                // 使用secondValue
                break; // 找到第二个后退出循环
            }
            count++;
        }
        System.out.println("page:" + page + "      pageSize；" + pageSize);

        List<Product> product = productService.getAllProduct((int) page, (int) pageSize);
        int goodsCount = productService.getGoodsCount();
        System.out.println("goodsCount: " + goodsCount);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Product", product);
        map.put("total", goodsCount);
        System.out.println("返回了。。。" + map);
        return map;
    }

    @RequestMapping(value = "/getProductByCategory")
    public Map getProductByCategory(@RequestBody Map<String, Object> paramMap) {
        ArrayList categoryIDList = (ArrayList) paramMap.get("categoryID");
        int categoryID = (int) categoryIDList.get(0);
        int currentPage = (int) paramMap.get("currentPage");
        int pageSize = (int) paramMap.get("pageSize");
        List<Product> product = productService.getProductByCategory(categoryID, currentPage, pageSize);
        int goodsCountByCategory = productService.getGoodsCountByCategory(categoryID);

        HashMap<String, Object> map = new HashMap<>();
        map.put("Product", product);
        map.put("total", goodsCountByCategory);
        return map;
    }

    @RequestMapping(value = "/getPromoProduct")
    public Map getPromoProduct(@RequestBody Map<String, String> paramMap) {
        String categoryName = paramMap.get("categoryName");
        List<Product> product = productService.getProductByCategoryName(categoryName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Product", product);
        return map;
    }

    @RequestMapping(value = "/getHotProduct")
    public Map getHotProduct(@RequestBody Map<String, List<String>> paramMap) {
        List<String> categoryName = paramMap.get("categoryName");
        List<Product> product = new ArrayList<>();
        for (String name : categoryName) {
            List<Product> tempProduct = productService.getProductByCategoryName(name);
            product.addAll(tempProduct);   //添加多个类型产品
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("Product", product);
        return map;
    }

    @RequestMapping(value = "/getDetails")
    public Map getDetails(@RequestBody Map<String, Integer> paramMap) {
        int productID = paramMap.get("productID");
        Product product = productService.getProductByID(productID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Product", new ArrayList<Product>() {{
            this.add(product);
        }});
        return map;
    }

    @RequestMapping(value = "/getDetailsPicture")
    public Map getDetailsPicture(@RequestBody Map<String, Integer> paramMap) {
        int productID = paramMap.get("productID");
        List<Product_picture> ProductPicture = productService.getDetailsPicture(productID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("ProductPicture", ProductPicture);
        return map;
    }

    @RequestMapping(value = "/getProductBySearch")
    public Map getProductBySearch(@RequestBody Map<String, Object> paramMap) {
        String search = (String) paramMap.get("search");
        int currentPage = (int) paramMap.get("currentPage");
        int pageSize = (int) paramMap.get("pageSize");
        int offset = (currentPage - 1) * pageSize;
        List<Category> categorys = productService.getCategory();
        HashMap<String, Object> map = new HashMap<>();
        for (Category category : categorys) {
            if (category.getCategory_name().equals(search)) {
                List<Product> product = productService.getProductByCategory(category.getCategory_id(), offset, pageSize);
                map.put("code", "001");
                map.put("Product", product);
                map.put("total", product.size());
                return map;
            }
        }
        List<Product> product = productService.getProductBySearch(search);
        map.put("code", "001");
        map.put("Product", product);
        map.put("total", product.size());
        return map;
    }

    @RequestMapping("/updateSales")
    void updateSales(@RequestParam("product_id") int product_id, @RequestParam("sales")int sales) {
        productService.updateSales(product_id,sales);
    }

    @RequestMapping("/getProductByID")
    Product getProductByID(@RequestParam("product_id") int product_id){
        Product productByID = productService.getProductByID(product_id);
        return productByID;
    }

    //    ---------------------------------后台-----------------------------------
    /**
     * 后台商品分页查询
     */
    @GetMapping("/getProductsByPage")
    public Map getAllProduct(@RequestParam("currentPage") int currentPage,@RequestParam("pageSize") int pageSize,@RequestParam("searchName") String searchName) {
        List<Product> product = productService.getProductsByPage(currentPage, pageSize, searchName);
        int productsCount = productService.getProductsCount(searchName) ;

        HashMap<String, Object> map = new HashMap<>();
        map.put("Product",product);
        map.put("total",productsCount);
        map.put("code","001");
        map.put("msg","成功获取订单");
//        System.out.println("返回了。。。"+map);
        return map;
    }

    /**
     * 新增商品
     */
    @PostMapping("/addProduct")
    public Map addProduct(@RequestBody Product product){
        System.out.println("product" + product );
//        product.setProduct_picture(product.getProduct_picture());
        productService.addProduct(product);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", "001");
        return map;
    }


    /**
     * 编辑商品
     */
    @PostMapping("/updateProduct")
    public Map updateProduct(@RequestBody Product product){
        System.out.println("product" + product );
        productService.updateProduct(product);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", "001");
        return map;
    }

    /**
     *  删除商品
     */
    @DeleteMapping("/deleteProduct")
    public Map deleteProduct(@RequestParam int product_id){
        productService.deleteProduct(product_id);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", "001");
        return map;
    }

    /**
     * 获取热销商品
     */
    @GetMapping("/getGoodGood")
    public Map getGoodGood(@RequestParam int order){
        List<Product> product = productService.getGoodGood(order);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("goodGood",product);
        map.put("code", "001");
        return map;
    }

}
