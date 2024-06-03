package OtherService.service;
import vo.*;

import java.time.LocalDate;

public interface ReportService {
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);
}
