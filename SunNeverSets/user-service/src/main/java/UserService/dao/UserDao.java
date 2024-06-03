package UserService.dao;


import model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    @Select("select user_id,userName,userPhoneNumber,email,gender from users where userName=#{userName} and password=#{password}")
    User login(String userName, String password);

    @Select("select count(1) from users where userName=#{username}")
    int findUserByName(String userName);

    @Select("select count(1) from users where user_id=#{user_id}")
    int findUserById(Integer user_id);

    @Insert("insert into users(userName,password,salt,register_time,login_count) values(#{userName},#{password},#{salt},now(),1)")
    void registerUser(String userName, String password, String salt);

//    @Update("update users set password=#{password},userPhoneNumber=#{userPhoneNumber},email=#{email},gender=#{gender}")
//    void updateUser(User user);

    @Select("select salt from users where username=#{userName}")
    String getSalt(String userName);

    @Select("select * from users where username=#{userName}")
    User findByUserName(String userName);

    @Update("update users set login_count=login_count+#{count} where username=#{username}")
    void updateLoginCount(String username, int count);

    /**
     * 分页 用户查询
     */
    @Select("select * from users where userName like '%${searchName}%' order by login_count desc limit #{currentPage1},#{pageSize} ")
    List<User> selectAllUsersByPage(int currentPage1, int pageSize, String searchName);

    /**
     * 分页  用户总数查询
     */
    @Select("select count(*) from users where userName like '%${searchName}%'")
    int getUsersCount(String searchName);

    /**
     *  更新用户信息
     * @param
     */
    @Update("update users set userName=#{userName},password=#{password},salt=#{salt} where userName=#{oldName}")
    void updateUser(String userName, String password, String salt, String oldName);


    /**
     *  删除用户信息
     * @param
     */
    @Delete("delete from users where user_id=#{user_id}")
    void deleteUser(String user_id);
}
