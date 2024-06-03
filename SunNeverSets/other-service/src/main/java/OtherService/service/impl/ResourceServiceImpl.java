package OtherService.service.impl;


import OtherService.dao.ResourceDao;
import OtherService.service.ResourceService;
import model.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Carousel> getCarousel() {
        Map<Object,Object> carousel = redisTemplate.opsForHash().entries("carousel");
        if(carousel.isEmpty()){
            List<Carousel> carouselList = resourceDao.getCarousel();
            carousel.put("carousel",carouselList);
            System.out.println("carouselList" + carouselList);
            redisTemplate.opsForHash().putAll("carousel",carousel);
        }
        return (List<Carousel>) carousel.get("carousel");
    }
}
