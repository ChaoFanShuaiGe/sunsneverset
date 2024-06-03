package OtherService.service;
import model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    //添加评论
    void addComment(Comment comment);

    /**
     * 获取评论
     * @return
     */
    List<Comment> getComment(int product_id);
}
