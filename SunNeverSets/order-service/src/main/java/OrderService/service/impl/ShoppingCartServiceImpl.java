package OrderService.service.impl;

import OrderService.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

//    @Override
//    public void deleteShoppingCart(int user_id, int product_id) {
//
//    }

    @Override
    public Map deleteShoppingCart(Map<String, Integer> paramMap) {
        return Collections.emptyMap();
    }
}
