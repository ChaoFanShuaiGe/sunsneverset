package OtherService.service;


import model.Carousel;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ResourceService {

    List<Carousel> getCarousel();

}
