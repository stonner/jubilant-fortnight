package zcq.myjpa.examples.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcq.myjpa.vo.BankMessageVo;
import zcq.myjpa.examples.Example;
import zcq.myjpa.utils.FormatUtils;
import zcq.myjpa.utils.LoUtils;


/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/05
 */
public class Example7 implements Example {
    private Logger logger = LoggerFactory.getLogger(Example7.class);

    public static void main(String[] args) {
        new Example7().doing1();
    }

    @Override
    public void doing1() {
        final String BANK_URL = "https://testpos.ebankunion.com:8443/nserver/service_pos";
        final String BANK_REQUEST_METHOD = "POST";
        String resMessage = "";
        //String message = "002C6000100000603200310000080000200000008000120000903535353535353532001100000000004000023031";
        String message = "003B600010000060320031000008000020000000C000120000923535353535353532303030303030303030303230393834001100000000004000023031";
        //String message = "01986000100000FD00724F7418C64E7C5480A6F78D0ED9AF180F0A2035346234643131363562333534386130386265613136623535353863346138340B06303030353336100E59323031373130323042303030331106313030303031F0001D7B22746561636374223A2230393434303030303438393333383039227D6032003100000200702406C020C09A111662148377154173030000000000000000540107172708051000000006376214837715417303D27030E4AF0EB14B43CC003231313330303135303030303030303030303030353336313536CAF03FD81AC12E59261000000000000001609F2608B6975E4B2E641C559F2701809F101307010103A0A000010A010000000000814908159F3704AD972CA19F360201AE950580000400009A031807069C01009F02060000000000005F2A02015682027C009F1A0201569F03060000000000009F3303E048209F74009F34034203009F3501149F1E0841424344313233348408A0000003330101019F090200309F410400000004910071007200DF31009F63000014220000000006003939303346384334";

        BankMessageVo bankMessageVo = new BankMessageVo(message, false);
        logger.info(FormatUtils.formatToJson(JSON.toJSONString(bankMessageVo)));
        try {
            resMessage = LoUtils.httpsRequest(BANK_URL, BANK_REQUEST_METHOD, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("total length:" + resMessage.length());
        System.out.println(resMessage);
        BankMessageVo reBankMessageVo = new BankMessageVo(resMessage, true);
        logger.info(FormatUtils.formatToJson(JSON.toJSONString(reBankMessageVo)));
        System.out.println("报文长度"+resMessage.substring(0, 4));
        System.out.println("TPDU"+resMessage.substring(4, 14));
        System.out.println("银联报文头"+resMessage.substring(14, 26));
        System.out.println("消息类型"+resMessage.substring(26, 30));
        System.out.println("位图"+resMessage.substring(30, 46));
        System.out.println("报文内容"+resMessage.substring(46));
    }

    @Override
    public void doing2() {

    }
}
