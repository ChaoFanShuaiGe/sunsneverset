package ShoppingCartService.service.impl;

import ShoppingCartService.dao.ProductDao;
import ShoppingCartService.dao.ShoppingCartDao;
import ShoppingCartService.service.ProductServiceClient;
import ShoppingCartService.service.ShoppingCartService;
import model.Product;
import model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private ProductDao productDao;

//    @Autowired
//    private ShoppingCartService shoppingCartService;
//    @Autowired
//    private ProductServiceClient productServiceClient;

    @Override
    public void updateShoppingCart(int user_id, int product_id, int num) {
        shoppingCartDao.updateShoppingCart(user_id,product_id,num);
    }

    @Override
    public void addShoppingCart(int user_id, int product_id) {
        shoppingCartDao.addShoppingCart(user_id,product_id);
    }

    @Override
    public ShoppingCart findShoppingCart(int user_id, int product_id) {
        return shoppingCartDao.findShoppingCart(user_id,product_id);
    }

    @Override
    public List<ShoppingCart> getShoppingCart(int user_id) {
        return shoppingCartDao.getShoppingCart(user_id);
    }

    /*获取购物车相关信息*/
    @Override
    public Map[] getShoppingCartData(List<ShoppingCart> shoppingCarts) {
        Map[] ShoppingCartData = new Map[shoppingCarts.size()];
        for(int i=0;i<ShoppingCartData.length;i++){
            ShoppingCart shoppingCart = shoppingCarts.get(i);
            //Product product = productServiceClient.getProductByID(shoppingCart.getProduct_id());
            Product product = productDao.getProductByID(shoppingCart.getProduct_id());
            HashMap<String, Object> tempData = new HashMap<>();
            tempData.put("id",shoppingCart.getId());
            tempData.put("productID",shoppingCart.getProduct_id());
            tempData.put("productName",product.getProduct_name());
            tempData.put("productImg",product.getProduct_picture());
            tempData.put("price",product.getProduct_selling_price());
            tempData.put("num",shoppingCart.getNum());
            tempData.put("maxNum",Math.floor(product.getProduct_num() / 2.0));
            tempData.put("check",true);
            ShoppingCartData[i] = tempData;
        }
        return ShoppingCartData;
    }

    @Override
    public boolean deleteShoppingCart(Integer user_id, Integer product_id) {
        if(null==user_id||null==product_id)
            return false;
        shoppingCartDao.deleteShoppingCart(user_id,product_id);
        return true;
    }


}
