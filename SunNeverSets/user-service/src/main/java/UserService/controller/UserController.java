package UserService.controller;


import UserService.service.UserService;
import UserService.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/user",method = {RequestMethod.POST})

public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register")
    public Map userRegister(@RequestBody Map<String,String> userMap,HttpServletRequest req,HttpServletResponse res){
        String userName = userMap.get("userName");
        String password = userMap.get("password");
        HashMap map = new HashMap<String,String>();
        if(userService.findUserByName(userName)>0){
            map.put("code","004");
            map.put("msg","用户名已存在，不能注册");
        }else{
            User user = userService.registerUser(userName, password);
            map.put("code","001");
            map.put("msg","注册成功");
        }
        return map;
    }

    @RequestMapping(value = "/findUserName")
    public Map findByName(@RequestBody Map<String,String> userMap){
        String userName = userMap.get("userName");
        HashMap map = new HashMap<String,String>();
        if(userService.findUserByName(userName)>0){
            map.put("code","004");
            map.put("msg","用户名已存在，不能注册");
        }else{
            map.put("code","001");
            map.put("msg","可以注册");
        }
        return map;
    }

    @PostMapping(value = "/login")
    public Map userLogin(@RequestBody Map<String,String> userMap, HttpServletRequest req, HttpServletResponse res){
        String userName = userMap.get("userName");
        String password = userMap.get("password");
        HashMap map = new HashMap<String,String>();
        User user = userService.login(userName, password);
        if(user!=null){
            String token = JWTUtils.getToken(String.valueOf(user.getUser_id()),user.getUserName());
            map.put("token",token);
            map.put("code","001");
            map.put("msg","登录成功");
            map.put("user",user);
        }else{
            map.put("code","004");
            map.put("msg","登录失败");
        }
        return map;
    }

//    @RequestMapping(value = "/updateUser")
//    public Map updateUser(User user){
//        HashMap map = new HashMap<String,String>();
//        userService.updateUser(user);
//        map.put("code","001");
//        map.put("msg","修改成功");
//        return map;
//    }

    @RequestMapping(value = "/logout")
    public void userLogout(@RequestBody Map<String,String> map, HttpServletRequest req){
        //TODO
        /*String userTicket = CookieUtil.getCookieValue(req, "userTicket", false);
        req.getSession().removeAttribute(userTicket);
        log.info("用户"+map.get("username")+"退出登录");*/
    }

    @RequestMapping("/updateLoginCount")
    void updateLoginCount(String username, int count){
        userService.updateLoginCount(username, count);
    }

    //

    /**
     * 用户 分页查询
     */
    @GetMapping("/getUsersByPage")
    public Map getAddressByPage(@RequestParam int currentPage, int pageSize, String searchName){
        List<User> users = userService.selectAllUsersByPage(currentPage, pageSize, searchName);
        int usersCount = userService.getUsersCount(searchName) ;

        HashMap<String, Object> map = new HashMap<>();
        map.put("users",users);
        map.put("total",usersCount);
        map.put("code","001");
        map.put("msg","成功获取用户");
        return map;
    }


    /**
     *  更新用户信息
     * @param
     */
    @GetMapping(value = "/updateUser")
    public Map updateUser(@RequestParam String userName, String password, String oldName){
        HashMap map = new HashMap<String,String>();
        userService.updateUser(userName, password, oldName);
        map.put("code","001");
        map.put("msg","修改成功");
        return map;
    }


    /**
     *  删除用户信息
     * @param
     */
    @DeleteMapping(value = "/deleteUser")
    public Map deleteUser(@RequestParam String user_id){
        HashMap map = new HashMap<String,String>();
        userService.deleteUser(user_id);
        map.put("code","001");
        map.put("msg","修改成功");
        return map;
    }

}
