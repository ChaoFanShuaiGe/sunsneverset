package OtherService.controller;
import OtherService.service.AddressService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//@RequestMapping(value = "/address",method = {RequestMethod.POST})
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/getAddress")
    //public Map getAddress(@RequestBody Map<String,Object> paramMap, HttpServletRequest req){
    public Map getAddress(@RequestParam("user_id")int user_id){
        /*String ticket = CookieUtil.getCookieValue(req, "userTicket", false);
        if(StringUtils.isEmpty(ticket)||req.getSession().getAttribute(ticket)==null){
            return RespBeanEnum.WITHOUT_LOGIN.getMap();
        }*/
        //Integer user_id = (Integer) paramMap.get("user_id");

        List<Address> addresses = addressService.selectAllAddress(user_id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("address",addresses);
        map.put("code","001");
        map.put("msg","成功获取地址");
        return map;
    }

    @PostMapping("/addAddress")
    public Map addAddress(@RequestBody Address address){
        addressService.addAddress(address);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code","001");
        map.put("msg","新增地址成功");
        return map;
    }
    @DeleteMapping("/deleteAddress")
    public Map deleteAddress(@RequestParam int id){
        addressService.deleteAddress(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code","001");
        map.put("msg","删除地址成功");
        return map;
    }
    @PutMapping("/updateAddress")
    public Map updateAddress(@RequestBody Address address){
        addressService.updateAddress(address);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code","001");
        map.put("msg","修改地址成功");
        return map;
    }
    //    ---------------------------------后台-----------------------------------

    /**
     * 分页地址查询
     */
    @GetMapping("/getAddressByPage")
    public Map getAddressByPage(@RequestParam("user_id")int user_id, int currentPage, int pageSize, String address){
        List<Address> addresses = addressService.selectAllAddressByPage(user_id, currentPage, pageSize, address);
        int addressCount = addressService.getAddressCount(user_id, address) ;

        HashMap<String, Object> map = new HashMap<>();
        map.put("address",addresses);
        map.put("total",addressCount);
        map.put("code","001");
        map.put("msg","成功获取地址");
        return map;
    }
}
