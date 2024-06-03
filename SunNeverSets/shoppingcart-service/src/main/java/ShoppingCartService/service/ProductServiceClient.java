package ShoppingCartService.service;

import ShoppingCartService.service.impl.ProductServiceClientImpl;
import model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "product-service-provider",fallback = ProductServiceClientImpl.class)

public interface ProductServiceClient {
    @RequestMapping("/getProductByID")
    Product getProductByID(int product_id);
}
