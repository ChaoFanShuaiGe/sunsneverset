package ShoppingCartService.dao;


import model.Category;
import model.Product;
import model.Product_picture;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDao {

    @Select("select * from category")
    List<Category> getCategory();

    @Select("select * from product where status=1 limit #{page1},#{pageSize}")
    List<Product> getAllProduct(int page1, int pageSize);

    @Select("select * from product_picture where product_id=#{product_id}")
    List<Product_picture> getDetailsPicture(int product_id);

    @Select("select * from product where product_id=#{product_id}")
    Product getProductByID(int product_id);

    @Select("select category_id from category")
    List getCategory_id();

    @Select("select * from product where category_id=#{categoryID} and status=1 order by product_sales limit #{currentPage},#{pageSize}")
    List<Product> getProductByCategory(int categoryID, int currentPage, int pageSize);

    @Select("select p.* from product p,category c where p.category_id=c.category_id and c.category_name=#{categoryName} and p.status=1")
    List<Product> getProductByCategoryName(String categoryName);

    @Select("select * from product where status=1 and product_name like '%${search}%' or product_title like '%${search}%' or product_intro like '%${search}%'")
    List<Product> getProductBySearch(String search);

    @Select("select product_sales from product where product_id=#{product_id}")
    Integer getSales(int product_id);

    @Update("update product set product_sales=product_sales+#{sales} where product_id=#{product_id}")
    void updateSales(int product_id, int sales);

    @Select("select count(*) from product")
    int getGoodsCount();

    @Select("select count(*) from product where category_id = #{categoryID}")
    int getGoodsCountByCategory(int categoryID);
}
