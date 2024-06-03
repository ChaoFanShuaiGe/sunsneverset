package OtherService.service;

import OtherService.service.impl.ProductServiceImpl;
import model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "product-service-provider",fallback = ProductServiceImpl.class)
public interface ProductService {

    @RequestMapping("/getProductByID")
    Product getProductByID(@RequestParam("product_id") int product_id);
}
