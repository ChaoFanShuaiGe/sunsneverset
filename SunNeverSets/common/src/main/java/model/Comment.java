package model;
import lombok.Data;

import java.util.Date;

@Data
//评论
public class Comment {
    private int id;
    private int product_id;
    private String value;
    private int user_id;
    private Date comment_time;
    private String userName;
}
