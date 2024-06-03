package OrderService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@FeignClient(value = "shoppingcart-service")
public interface ShoppingCartService {
    @RequestMapping(value = "/shoppingCart/deleteShoppingCart")
    public Map deleteShoppingCart(@RequestBody Map<String,Integer> paramMap);
}
