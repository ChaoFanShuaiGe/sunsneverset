package UserService.service;


import model.User;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserService {
    User login(String userName, String formPass);
    int findUserByName(String userName);
    User registerUser(String userName, String formPass);


    void updateLoginCount(String username, int count);

    /**
     * 分页 用户查询
     */
    List<User> selectAllUsersByPage(int currentPage, int pageSize, String searchName);

    /**
     * 分页  用户总数查询
     */
    int getUsersCount(String searchName);

    /**
     *  更新用户信息
     * @param
     */
    void updateUser(String userName, String password, String oldName);


    /**
     *  删除用户信息
     * @param
     */

    void deleteUser(String user_id);
}
