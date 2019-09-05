package zcq.myjpa.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import zcq.myjpa.utils.HttpClientUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/30
 */
public class TestWater {

    public static void main(String[] args) {
        String url = "http://szgk.sz-water.com.cn/testapi/op/BillInfo/GetLadderdetail";
        final HashMap<String, String> params = new HashMap<>(5);
        params.put("channel", "zzzd");
        params.put("openid","Terminal-admin-001");
        for (int i=11; i< 100; i++){
            for (int j=0; j<300; j++){
                params.put("customercode", "17420802"+i);
                params.put("billmonth", ""+(201703+j));
                System.out.println();
                final String post = post(url, params);
                System.out.println("customercode : "+"17420802"+i+" ; billmonth : "+(201703+j)+"\n"+((!"null".equals(post))?"nonono":"")+post);
                //System.out.println("teuer");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        params.put("customercode", "1742080239");
        params.put("billmonth", ""+201908);
        System.out.println(post(url, params));
    }

    public static String post(String url,HashMap<String, String> params){
        final String post = HttpClientUtils.newInstance().postPayload(url, params, 2);
        System.out.println("post : "+post);
        final JSONObject jsonObject = JSON.parseObject(post);
        final Object data = jsonObject.get("data");
        return JSON.toJSONString(data);
    }
}
