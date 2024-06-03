package OtherService.service;
import model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressService {

    List<Address> selectAllAddress(int user_id);

    void addAddress(Address address);

    void deleteAddress(int id);

    void updateAddress(Address address);
    /**
     * 分页地址查询
     */
    List<Address> selectAllAddressByPage(int user_id, int currentPage,int pageSize, String address);

    /**
     * 分页地址总数查询
     */
    int getAddressCount(int user_id, String address);
}
