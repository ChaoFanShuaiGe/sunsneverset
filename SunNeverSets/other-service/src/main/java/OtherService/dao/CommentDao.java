package OtherService.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import model.*;
import java.util.List;

@Mapper
public interface CommentDao {

    /**
     * 添加评论
     * @param comment
     */
    @Insert("insert into ri_comment(product_id, value, user_id, comment_time) VALUES" +
            " (#{comment.product_id},#{comment.value},#{comment.user_id},now())")
    public void addComment(@Param("comment") Comment comment);

    /**
     * 获取评论
     * @return
     */
    @Select("select * from ri_comment r ,users u where product_id = #{prodect_id} and r.user_id = u.user_id order by r.comment_time desc")
    List<Comment> getComment(int prodect_id);
}
