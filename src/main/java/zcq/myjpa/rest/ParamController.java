package zcq.myjpa.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zcq.myjpa.config.ServerConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/23
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/getIP")
    public Map getIP() {
        System.out.println(ServerConfig.getHostAddress());
        System.out.println(ServerConfig.getPort());
        final HashMap<String, String> map = new HashMap<>();
        map.put("address", ServerConfig.getHostAddress());
        map.put("port", ServerConfig.getPort() + "");
        return map;
    }
}
