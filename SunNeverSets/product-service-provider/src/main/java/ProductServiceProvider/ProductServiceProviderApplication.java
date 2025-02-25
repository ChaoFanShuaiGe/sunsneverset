package ProductServiceProvider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("ProductServiceProvider.dao")
public class ProductServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceProviderApplication.class,args);
    }
}
