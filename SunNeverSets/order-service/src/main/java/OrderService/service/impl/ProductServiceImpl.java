package OrderService.service.impl;

import OrderService.service.ProductService;
import model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductByID(int product_id) {
        return null;
    }

    /**
     * 更新销量
     */
    @Override
    public void updateSales(int product_id, int sales) {

    }



}
