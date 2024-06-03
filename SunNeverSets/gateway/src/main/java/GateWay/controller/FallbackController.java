package GateWay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @RequestMapping("")
    public Map fallback(){
        HashMap<String, String> map = new HashMap<>();
        map.put("code","005");
        map.put("msg","所访问的服务异常，这是一条来自网关的消息");
        return map;
    }
}
