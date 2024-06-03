package OtherService;

import OtherService.dao.ResourceDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("OtherService.dao")
@EnableFeignClients
@EnableScheduling   //开启定时任
public class OtherServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherServiceApplication.class,args);
    }
}
