package OtherService.controller;

import OtherService.dao.ReportDao;
import OtherService.service.ReportService;
import vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 数据统计
 */
@RestController
@RequestMapping("/report")
@Slf4j
public class ReportController {
    @Autowired(required = false)
    private ReportService reportService;
    @Autowired(required = false)
    private ReportDao ReportDao;
    /**
     * 用户数据统计
     */
    @GetMapping("/user")
    public UserReportVO userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("用户数据统计：{},{}",begin,end);
        return reportService.getUserStatistics(begin,end);
    }
}
