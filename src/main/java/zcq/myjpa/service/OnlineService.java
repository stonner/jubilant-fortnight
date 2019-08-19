package zcq.myjpa.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import zcq.myjpa.bean.vo.CustomerVo;
import zcq.myjpa.utils.HttpClientUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/19
 */
@Service
public class OnlineService {

    public List<CustomerVo> getCustomerDetail(String customercode) {
        final HashMap<String, String> params = new HashMap<>(5);
        System.out.println(customercode);
        params.put("channel", "zzzd");
        params.put("openid","Terminal-admin-001");
        params.put("customercode", customercode);
        final String post = HttpClientUtils.newInstance().postPayload("http://szgk.sz-water.com.cn/testapi/op/CustomerInfo/GetCustomerDetail", params, 2);
        System.out.println("post : "+post);
        final JSONObject jsonObject = JSON.parseObject(post);
        final Object data = jsonObject.get("data");
        return JSON.parseArray(data.toString(), CustomerVo.class);
    }


}
