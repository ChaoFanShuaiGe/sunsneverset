package UserService.service.impl;
import UserService.dao.UserDao;
import UserService.service.UserService;
import UserService.utils.MD5Util;
import model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public User login(String userName, String formPass) {
        String salt = userDao.getSalt(userName);
        if(StringUtils.isEmpty(salt))
            return null;
        String DBPass = MD5Util.formPassToDBPass(formPass,salt);
        User user = userDao.login(userName, DBPass);
        if(user!=null){
            Map<Object, Object> login_count = redisTemplate.opsForHash().entries("login_count");
            if(login_count.isEmpty()||login_count.get(userName)==null){
                login_count.put(userName,1);
            }else{
                login_count.put(userName,(int) login_count.get(userName)+1);
            }
            redisTemplate.opsForHash().putAll("login_count",login_count);
        }
        return user;
    }

    @Override
    public int findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public User registerUser(String userName, String formPass) {
        String salt = UUID.randomUUID().toString().replace("-","").substring(0,15);
        String DBPass = MD5Util.formPassToDBPass(formPass, salt);
        userDao.registerUser(userName,DBPass,salt);
        return userDao.findByUserName(userName);
    }

    /**
     *  更新用户信息
     * @param
     */
    public void updateUser(String userName, String password, String oldName) {
        String salt = UUID.randomUUID().toString().replace("-","").substring(0,15);
        String DBPass = MD5Util.formPassToDBPass(password, salt);
        userDao.updateUser(userName, password, salt, oldName);
    }


    @Override
    public void updateLoginCount(String username, int count) {
        userDao.updateLoginCount(username, count);
    }


    /**
     * 分页 用户查询
     */
    public List<User> selectAllUsersByPage(int currentPage, int pageSize, String searchName) {
        int currentPage1 = (currentPage-1)*pageSize;
        return userDao.selectAllUsersByPage(currentPage1, pageSize, searchName);
    }

    /**
     * 分页  用户总数查询
     */
    public int getUsersCount(String searchName) {
        return userDao.getUsersCount(searchName);

    }






    /**
     *  删除用户信息
     * @param
     */
    @Override
    public void deleteUser(String user_id) {
        userDao.deleteUser(user_id);
    }
}
