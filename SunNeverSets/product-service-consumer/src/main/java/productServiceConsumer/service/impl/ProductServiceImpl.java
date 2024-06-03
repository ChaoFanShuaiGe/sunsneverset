package productServiceConsumer.service.impl;

import model.Product;
import org.springframework.stereotype.Service;
import productServiceConsumer.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Map getCategory() {
        return Collections.emptyMap();
    }

    @Override
    public Map getAllProduct(Map<String, Object> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getProductByCategory(Map<String, Object> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getPromoProduct(Map<String, String> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getHotProduct(Map<String, List<String>> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getDetails(Map<String, Integer> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getDetailsPicture(Map<String, Integer> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getProductBySearch(Map<String, Object> paramMap) {
        return Collections.emptyMap();
    }

    @Override
    public Map getAllProduct(int currentPage, int pageSize, String searchName) {
        return Collections.emptyMap();
    }

    @Override
    public Map addProduct(Product product) {
        return Collections.emptyMap();
    }

    @Override
    public Map updateProduct(Product product) {
        return Collections.emptyMap();
    }

    @Override
    public Map deleteProduct(int product_id) {
        return Collections.emptyMap();
    }

    @Override
    public Map getGoodGood(int order) {
        return Collections.emptyMap();
    }
}
