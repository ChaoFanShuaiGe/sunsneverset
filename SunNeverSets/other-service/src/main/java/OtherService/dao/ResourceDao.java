package OtherService.dao;


import model.Carousel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResourceDao {

    @Select("select * from carousel")
    List<Carousel> getCarousel();

}
