package ProductServiceProvider.service;


import model.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductService {

    List<Category> getCategory();

    List<Product> getAllProduct(int page,int pageSize);

    List getCategory_id();

    List<Product> getProductByCategory(int categoryID, int currentPage, int pageSize);

    List<Product> getProductByCategoryName(String categoryName);

    Product getProductByID(int product_id);

    List<Product_picture> getDetailsPicture(int product_id);

    List<Product> getProductBySearch(String search);

    int getGoodsCount();

    int getGoodsCountByCategory(int categoryID);

    void updateSales(int productId, int sales);

    /**
     * 后台商品分页查询
     */
    List<Product> getProductsByPage(int currentPage, int pageSize, String searchName);

    int getProductsCount(String searchName);

    /**
     * 新增商品
     */
    void addProduct(Product product);

    /**
     * 编辑商品
     */
    void updateProduct(Product product);

    /**
     *  删除商品
     */
    void deleteProduct(int product_id);

    /**
     * 获取热销商品
     */
    List<Product> getGoodGood(int order);
}
