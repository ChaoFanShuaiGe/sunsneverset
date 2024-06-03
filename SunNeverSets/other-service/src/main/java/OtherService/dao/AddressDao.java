package OtherService.dao;
import model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao {
    @Select("select * from address where user_id=#{user_id}")
    List<Address> selectAllAddress(int user_id);

    @Insert("insert into address(id,name,phone,address,user_id) values (null,#{address.name},#{address.phone},#{address.address},#{address.user_id})")
    void addAddress(@Param("address") Address address);

    @Delete("delete from address where id = #{id}")
    void deleteAddress(@Param("id") int id);

    @Update("update address set name=#{address.name},phone=#{address.phone},address=#{address.address} where id = #{address.id}")
    void updateAddress(@Param("address") Address address);

    //-----------------------------------后台------------------------------------//
    /**
     * 分页  地址查询
     */
    //    @Select("select * from address where user_id=#{user_id} limit #{currentPage1},#{pageSize}")
    @Select("select * from address where address like '%${address}%' limit #{currentPage1},#{pageSize}")
    List<Address> selectAllAddressByPage(int user_id, int currentPage1, int pageSize, String address);

    /**
     * 分页  地址总数查询
     */
    @Select("select count(*) from address where address like '%${address}%' ")
    int getAddressCount(int user_id,  String address);
}
