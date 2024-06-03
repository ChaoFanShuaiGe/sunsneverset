package ProductServiceProvider.dao;


import model.Category;
import model.Product;
import model.Product_picture;
import org.apache.ibatis.annotations.*;
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

    /**
     * 后台商品分页查询
     */
    @Select("select * from product where product_name like '%${searchName}%' order by product_id desc limit #{currentPage1},#{pageSize}")
    List<Product> getProductsByPage(int currentPage1, int pageSize, String searchName);

    @Select("select count(*) from product where product_name like '%${searchName}%'")
    int getOrderCount(String searchName);


    /**
     * 新增商品
     */
    @Insert("insert into product(product_name, category_id, product_title, product_intro, product_picture, product_price, product_selling_price, product_num, product_sales, status)" +
            "VALUES(#{product.product_name}, 8, #{product.product_title}, 'wwwwwwwww', #{product.product_picture}, #{product.product_price}, 1, 1, 1, 1) ")
    void addProduct(@Param("product") Product product);


    /**
     * 编辑商品
     */
    @Update("update product set product_name = #{product.product_name}, product_title=#{product.product_title}, product_price=#{product.product_price} ,product_picture=#{product.product_picture} where product_id =#{product.product_id}")
    void updateProduct(@Param("product") Product product);

    /**
     *  删除商品
     */
    @Delete("delete from product where product_id=#{product_id}")
    void deleteProduct(int product_id);

    /**
     * 获取热销商品
     */
    @Select("select * from product order by product_sales desc limit #{order}")
    List<Product> getGoodGood(int order);
}
