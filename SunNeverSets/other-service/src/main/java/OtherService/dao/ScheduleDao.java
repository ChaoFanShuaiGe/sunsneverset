package OtherService.dao;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleDao {
    /*未付款订单5s失效*/
//    @Update("update orders set status=0 where status=1 and pay_time is null and ROUND((unix_timestamp(NOW())-unix_timestamp(order_time))/60,0)>60")
    @Update("update orders set status=0 where pay_status=0 and  (unix_timestamp(NOW()) - unix_timestamp(order_time)) > 5;")
    int handleOrder();
}
