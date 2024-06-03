package OtherService.schedule;


import OtherService.dao.ScheduleDao;
import OtherService.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 *
 * 对失效的杀商品和订单进行处理
 */
@Component
@Slf4j
public class OutDateSchedule {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    /*处理失效订单*/
    //@Scheduled(cron = "0 0/10 * * * ?")   //每10分钟执行一次
    //@Scheduled(cron = "0/5 * * * * ?")   //每5s执行一次
//    创建定时任务
    public void handleOrder(){
        int count = scheduleDao.handleOrder();
        log.info("处理过期订单共"+count+"笔");
    }










    /*处理失效杀商品*//*
    @Scheduled(cron = "0 50 * * * ?")   //每小时执行一次
    public void handleSecGood(){
        List<Integer> ids = scheduleDao.selectSecGood();
        //清理redis
        redisTemplate.delete("secgood");
        redisTemplate.delete("secgoods");
        for(int id:ids){
            scheduleDao.handleSecGood(id);
            redisUtil.hdel("secStock",id+"");
        }
        log.info("处理失效的秒杀商品共"+ids.size()+"件");
    }*/

}
