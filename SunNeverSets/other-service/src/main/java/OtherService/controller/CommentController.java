package OtherService.controller;

import OtherService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import model.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/addComment")
    public Map addComment(@RequestBody Comment comment){
        System.out.println("comment: " + comment);
        commentService.addComment(comment);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code","001");
        map.put("msg","评论成功");
        return map;
    }

    @GetMapping("/getComment")
    public Map getComment(@RequestParam("productID") int product_id){
        List<Comment> commentList = commentService.getComment(product_id);
        HashMap<Object, Object> commentMap = new HashMap<>();
        commentMap.put("code","001");
        commentMap.put("msg","已获取评论");
        commentMap.put("commentList",commentList);
        return commentMap;
    }
}
