package ShoppingCartService.service;


import ShoppingCartService.service.impl.ShoppingCartServiceImpl;
import model.Product;
import model.ShoppingCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Component
public interface ShoppingCartService {

    void updateShoppingCart(int user_id, int product_id, int num);

    void addShoppingCart(int user_id, int product_id);

    ShoppingCart findShoppingCart(int user_id, int product_id);

    List<ShoppingCart> getShoppingCart(int user_id);

    Map[] getShoppingCartData(List<ShoppingCart> shoppingCarts);

    boolean deleteShoppingCart(Integer user_id, Integer product_id);


}
