package OtherService.service.impl;

import model.*;
import OtherService.dao.AddressDao;
import OtherService.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> selectAllAddress(int user_id) {
        return addressDao.selectAllAddress(user_id);
    }

    @Override
    public void addAddress(Address address) {
        addressDao.addAddress(address);
    }

    @Override
    public void deleteAddress(int id) {
        addressDao.deleteAddress(id);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.updateAddress(address);
    }
    /**
     * 分页 地址查询
     */
    public List<Address> selectAllAddressByPage(int user_id, int currentPage, int pageSize, String address) {
        int currentPage1 = (currentPage-1)*pageSize;
        return addressDao.selectAllAddressByPage(user_id, currentPage1, pageSize, address);
    }

    /**
     * 分页 地址总数查询
     */
    @Override
    public int getAddressCount(int user_id, String address)  {
        return addressDao.getAddressCount(user_id, address);
    }
}
