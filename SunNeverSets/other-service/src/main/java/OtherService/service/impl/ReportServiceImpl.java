package OtherService.service.impl;

import OtherService.dao.ReportDao;
import OtherService.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.UserReportVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired(required = false)
    private ReportDao userReportDao;

    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {

        //存放从begin.到end.之间的每天对应的日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        while (!begin.equals(end)){
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //存放每天的新增用户数量select count(id)from user where create time<?and create_time>?
        List<Integer>newUserList = new ArrayList<>();
        //存放每天的.总用户数量select count(id)from user where create_time<?
        List<Integer>totalUserList = new ArrayList<>();

        for (LocalDate date : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("end",endTime);

            //总用户数量
            Integer totalUser =  userReportDao.countsByMap(map);

            map.put("begin", beginTime);
            //新增用户数量
            Integer newUser = userReportDao.counts2ByMap(map);

            totalUserList.add(totalUser);
            newUserList.add(newUser);
        }
        //封装结果数据
        return UserReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .build();
    }
}
