package ShoppingCartService.service.impl;

import ShoppingCartService.service.ProductServiceClient;
import model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClientImpl implements ProductServiceClient {
    @Override
    public Product getProductByID(int product_id) {
        return null;
    }
}
