package zcq.myjpa.pos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.Enumeration;

//import javax.comm.CommPortIdentifier;
//import javax.comm.SerialPort;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

/**
 * Created by Administrator on 2017/11/6.
 */
public class SerialPost {
//
//	/**
//     * des向量
//     */
//    private final static String DES = "DES";
//
//    /**
//     * des向量
//     */
//    private final static String CIPHER_ALGORITHM = "DES/ECB/NoPadding";
//
//	/**
//     * 转16进制
//     */
//    private static final String hexStr =  "0123456789ABCDEF";
//
//    /**
//     * 地址
//     */
//    public static String url = "";
//
//    /**
//     * 终端号
//     */
//    public static String num = "";
//
//    /**
//     * 服务地址
//     */
//    public static final String url1 = "/bank/key";
//
//    /**
//     * 加载的html地址
//     */
//    public static String html = "";
//
//    /**
//     * 富文本
//     */
//    public static JTextArea logArea = null;
//
//    /**
//     * 初始化串口
//     * @param comboBox
//     */
//    public static void initJComboBox(JComboBox comboBox){
//    	  //获取本地所有的端口并输出其名称：
//        //用于标识端口的变量
//        CommPortIdentifier portIdentifier = null;
//
//        //记录所有端口的变量
//        Enumeration<?> allPorts = CommPortIdentifier.getPortIdentifiers();
//
//        //输出每一个端口
//        while(allPorts.hasMoreElements()){
//            portIdentifier = (CommPortIdentifier) allPorts.nextElement();
//            comboBox.addItem(portIdentifier.getName());
//        }
//    }
//
//    /**
//     * 添加日志
//     * @param log
//     */
//    public static void append(String log){
//    	logArea.append(log);
//		logArea.append("\r\n");
//		logArea.setCaretPosition(logArea.getText().length());
//    }
//
//    /**
//     * 读取数据
//     * @return
//     * @throws Exception
//     */
//    public static void read(String com) throws Exception{
//        SerialPost.append("开始读取" + com);
//        //获取两个端口
//        CommPortIdentifier com11 = CommPortIdentifier.getPortIdentifier(com);
//
//
//        //open方法的第1个参数表示串口被打开后的所有者名称，
//        //第2个参数表示如果串口被占用的时候本程序的最长等待时间，以毫秒为单位。
//        SerialPort serialCom11 = (SerialPort)com11.open("OpenerAndCloser", 1000);
//
//        //设置两个端口的参数
//        serialCom11.setSerialPortParams(
//                9600, //波特率
//                SerialPort.DATABITS_8,//数据位数
//                SerialPort.STOPBITS_1,//停止位
//                SerialPort.PARITY_NONE//奇偶位
//        );
//
//        //获取输入留
//        InputStream inputStream = serialCom11.getInputStream();
//
//        //无限循环，每隔1秒对串口COM21进行一次扫描，检查是否有数据到达
//        for(int i = 0 ; i < 60 ; i++){
//            //获取串口收到的可用字节数
//            int availableBytes = inputStream.available();
////            if(i == 5){
////            	inputStream.close();
////            	read("97531BDF86420ACEFEDCBA0123456789","5FBBB9CD4720CE060BE368319FC81D02");
////                serialCom11.close();
////            	return;
////            }
//
//            if(availableBytes > 0){
//                byte[] cache = new byte[availableBytes];
//                //读取数据
//                inputStream.read(cache);
//                //转16进制
//                String mainKey = bin2HexStr(cache);
//                //获取密钥
//                mainKey = mainKey.substring(8,40);
//                SerialPost.append("读取串口密钥成功");
//                //发送密钥并且判断是否成功
//                SerialPost.append("开始发送串口密钥");
//                String result = sendGet(url,"key=" + mainKey);
//                if(result.equals("1")){
//                	SerialPost.append("发送成功：" + mainKey);
//                }else{
//                	SerialPost.append(result);
//                }
//                write(serialCom11);
//                inputStream.close();
//                //关闭端口的方法在SerialPort类中
//                serialCom11.close();
//                SerialPost.append("开始灌入密钥至密码键盘");
//                read("97531BDF86420ACEFEDCBA0123456789",mainKey);
//                return;
//            }
//            SerialPost.append("正在读取" + com + "串口......");
//            //让线程睡眠1秒
//            Thread.sleep(1000);
//        }
//
//        inputStream.close();
//        //关闭端口的方法在SerialPort类中
//        serialCom11.close();
//        SerialPost.append("没有获取到串口信息");
//    }
//
//    /**
//     * 写入消息
//     * @throws Exception
//     */
//    public static void write(SerialPort serialCom11){
//    	SerialPost.append("向串口返回值......");
//    	String str = "\\x02\\x00\\x07SUCCESS\\x03\\xEE";
//    	try{
//	        //回复串口
//	        OutputStream os = serialCom11.getOutputStream();
//	        os.write(bin2HexStr(str.getBytes()).getBytes());
//	        os.flush();
//	        os.close();
//    	}
//    	catch (Exception e) {
//    		SerialPost.append("向串口发送返回值失败");
//		}
//
//        //关闭端口的方法在SerialPort类中
//        serialCom11.close();
//    }
//
//    /**
//     * 将二进制数组转换为十六进制字符串  2-16
//     * @param bytes
//     * @return
//     */
//    public static String bin2HexStr(byte[] bytes){
//
//        String result = "";
//        String hex = "";
//        for(int i=0;i<bytes.length;i++){
//            //字节高4位
//            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
//            //字节低4位
//            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
//            result +=hex;  //+" "
//        }
//        return result;
//    }
//
//    /**
//     * 向指定URL发送GET方法的请求
//     *
//     * @param url
//     *            发送请求的URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return URL 所代表远程资源的响应结果
//     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + url1 + "?" + param + "&num=" + num;
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            return "发送GET请求出现异常！";
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//            	return "发送GET请求出现异常！";
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 打开html加载主密钥
//     * @param html
//     */
//    public static void loadKey(String html){
//    	 File file = new File(html);
//         Runtime ce=Runtime.getRuntime();
//         System.out.println(file.getAbsolutePath());
//         try {
//			ce.exec("cmd   /c   start  "+file.getAbsolutePath());
//		} catch (IOException e) {
//			SerialPost.append("加载主密钥失败" + e.getMessage());
//		}
////        try {
////			Thread.sleep(5000);
////		} catch (InterruptedException e) {
////		}
////        file.delete();
//    }
//
//    /**
//     * 读取文件内容，修改内容
//     *
//     * @param mainEncKey
//     * @param mainEnc
//     * @return
//     */
//    public static void read(String mainEncKey, String mainEnc) {
//    	SerialPost.append("调用html加载主密钥");
//    	String key = desDecrypt(mainEnc,mainEncKey);
//    	String filePath = html;
//    	String writefilePath = filePath.replace(".html", "b.html");
//        BufferedReader br = null;
//        String line = null;
//        String main = "{MAIN}";
//        StringBuffer buf = new StringBuffer();
//        try {
//            // 根据文件路径创建缓冲输入流
//            br = new BufferedReader(new FileReader(filePath));
//            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
//            while ((line = br.readLine()) != null) {
//            	//修改需要修改的文本
//            	line = line.replace(main, key);
//                buf.append(line).append(System.getProperty("line.separator"));
//            }
//        } catch (Exception e) {
//        	SerialPost.append("加载主密钥失败" + e.getMessage());
//        } finally {
//            // 关闭流
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    br = null;
//                    SerialPost.append("加载主密钥失败" + e.getMessage());
//                }
//            }
//        }
//
//        try {
//			writeHtml(writefilePath,buf.toString());
//		} catch (IOException e) {
//			SerialPost.append("加载主密钥失败" + e.getMessage());
//		}
//    }
//
//    /**
//     * 将内容回写到文件中
//     *
//     * @param filePath
//     * @param content
//     * @throws IOException
//     */
//    public static void writeHtml(String filePath, String content) throws IOException{
//    	 File file = new File(filePath);
//         if(file.exists()){
//             file.delete();
//         }
//         file.createNewFile();
//         //报文保存到文件
//         FileOutputStream fileOutputStream = new FileOutputStream(file);
//         fileOutputStream.write(content.getBytes("utf-8"));
//         fileOutputStream.flush();
//         fileOutputStream.close();
//         loadKey(filePath);
//    }
//
//    /**
//     * 加密
//     *
//     * @param src 数据源
//     * @param key 密钥，长度必须是8的倍数
//     * @return 返回加密后的数据
//     */
//    private static byte[] encrypt(byte[] src, byte[] key) {
//        SecureRandom sr = new SecureRandom();
//        try {
//            DESKeySpec dks = new DESKeySpec(key);
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
//            SecretKey securekey = keyFactory.generateSecret(dks);
//            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
//            return cipher.doFinal(src);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 解密
//     *
//     * @param src 数据源
//     * @param key 密钥，长度必须是8的倍数
//     * @return 返回解密后的原始数据
//     */
//    public static byte[] decrypt(byte[] src, byte[] key) {
//        SecureRandom sr = new SecureRandom();
//        try {
//            DESKeySpec dks = new DESKeySpec(key);
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
//            SecretKey securekey = keyFactory.generateSecret(dks);
//            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
//            return cipher.doFinal(src);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 3des解密
//     *
//     * @param src 数据源
//     * @param key 密钥，长度必须是8的倍数
//     * @return 返回解密后的原始数据
//     */
//    public static String desDecrypt(String src, String key) {
//        byte [] lk = new byte[8];
//        byte [] rk = new byte[8];
//        byte [] bkey = hexStr2Bytes(key);
//        byte [] bsrc = hexStr2Bytes(src);
//        System.arraycopy(bkey,0,lk,0,8);
//        System.arraycopy(bkey,8,rk,0,8);
//        //3des 双倍加密双法
//        byte[] srcBytes = decrypt(encrypt(decrypt(bsrc,lk),rk),lk);
//        return bin2HexStr(srcBytes);
//    }
//
//    /**
//     * 十六进制字符串转换成bytes
//     * @param src
//     * @return
//     */
//    public static byte [] hexStr2Bytes(String src)
//    {
//        int m = 0, n = 0;
//        int l = src.length () / 2;
//        byte [] ret = new byte [l];
//        for (int i = 0;i < l;i++ )
//        {
//            m = i * 2 + 1;
//            n = m + 1;
//            ret [i] = uniteBytes (src.substring (i * 2,m),
//                    src.substring (m,n));
//        }
//        return ret;
//    }
//
//    /**
//     * 获取字节
//     * @param src0
//     * @param src1
//     * @return
//     */
//    private static byte uniteBytes(String src0, String src1)
//    {
//        byte b0 = Byte.decode ("0x" + src0).byteValue ();
//        b0 = (byte) (b0 << 4);
//        byte b1 = Byte.decode ("0x" + src1).byteValue ();
//        byte ret = (byte) (b0 | b1);
//        return ret;
//    }
}
