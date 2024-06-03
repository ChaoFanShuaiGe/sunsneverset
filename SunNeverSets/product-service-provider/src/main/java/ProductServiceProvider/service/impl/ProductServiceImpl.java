package ProductServiceProvider.service.impl;

import ProductServiceProvider.dao.ProductDao;
import ProductServiceProvider.service.ProductService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Category> getCategory() {
        return productDao.getCategory();
    }

    @Override
    public List<Product> getAllProduct(int page,int pageSize) {
        int page1 = (page-1)*pageSize;
        List<Product> allProduct = productDao.getAllProduct(page1,pageSize);
        return allProduct;
    }
    //没用到
    @Override
    public List getCategory_id() {
        Map<Object, Object> category_id = redisTemplate.opsForHash().entries("category_id");
        if(category_id.isEmpty()){
            List category_idList = productDao.getCategory_id();
            category_id.put("category_id",category_idList);
            redisTemplate.opsForHash().putAll("category_id",category_id);
        }
        return (List) category_id.get("category_id");
    }

    @Override
    public List<Product> getProductByCategory(int categoryID,int currentPage,int pageSize) {
        int page2 = (currentPage-1)*pageSize;

        return productDao.getProductByCategory(categoryID,page2,pageSize);
    }

    @Override
    public List<Product> getProductByCategoryName(String categoryName) {
        return productDao.getProductByCategoryName(categoryName);
    }
    //REDIS
    @Override
    public Product getProductByID(int product_id) {
        Map<Object, Object> productMap = redisTemplate.opsForHash().entries("product");
        if(productMap.isEmpty()){   //redis无map
            Product product = productDao.getProductByID(product_id);
            productMap.put(product_id+"",product);
            redisTemplate.opsForHash().putAll("product",productMap);
        }else if(productMap.get(product_id)==null){   //redis无该商品
            productMap.put(product_id+"",productDao.getProductByID(product_id));
            redisTemplate.opsForHash().putAll("product",productMap);
        }
        Product product = (Product) productMap.get(product_id + "");
        return product;
    }

    @Override
    public List<Product_picture> getDetailsPicture(int product_id) {
        return productDao.getDetailsPicture(product_id);
    }

    @Override
    public List<Product> getProductBySearch(String search) {
        return productDao.getProductBySearch(search);
    }

    /**
     * 获取物品总数
     * @return
     */
    @Override
    public int getGoodsCount() {
        return productDao.getGoodsCount();
    }
    /**
     * 获取分类之后的每个分类的物品总数
     * @return
     */
    @Override
    public int getGoodsCountByCategory(int categoryID) {
        return productDao.getGoodsCountByCategory(categoryID);
    }

    /**
     * 更新销量
     * @param productId
     * @param sales
     */
    @Override
    public void updateSales(int productId, int sales) {
        productDao.updateSales(productId,sales);
    }

    /**
     * 后台商品分页查询
     */
    public List<Product> getProductsByPage(int currentPage, int pageSize, String searchName) {
        int currentPage1 = (currentPage-1)*pageSize;
        return productDao.getProductsByPage(currentPage1, pageSize, searchName);

    }

    @Override
    public int getProductsCount(String searchName) {
        return productDao.getOrderCount(searchName);
    }


    /**
     * 新增商品
     */
    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    /**
     * 编辑商品
     */
    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    /**
     *  删除商品
     */
    @Override
    public void deleteProduct(int product_id) {
        productDao.deleteProduct(product_id);
    }


    /**
     * 获取热销商品
     */
    public List<Product> getGoodGood(int order) {
        return productDao.getGoodGood(order);
    }
}
