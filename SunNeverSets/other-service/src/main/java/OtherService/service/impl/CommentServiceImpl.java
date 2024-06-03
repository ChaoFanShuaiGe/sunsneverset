package OtherService.service.impl;

import OtherService.dao.CommentDao;
import OtherService.service.CommentService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    CommentDao commentDao;
    /**
     * 添加评论
     * @param comment
     */
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }


    /**
     * 获取评论
     * @return
     */
    public List<Comment> getComment(int prodect_id) {
        return commentDao.getComment(prodect_id);
    }
}
