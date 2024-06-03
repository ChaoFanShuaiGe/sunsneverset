package OtherService.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
@Mapper
public interface ReportDao {
    @Select("select count(user_id) from users where register_time < #{end}")
    Integer countsByMap(Map map);

    @Select("select count(user_id) from users where register_time > #{begin} and register_time < #{end}")
    Integer counts2ByMap(Map map);
}
