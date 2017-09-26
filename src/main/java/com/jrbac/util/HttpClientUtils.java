package com.jrbac.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求发送类
 * 
 * @author Administrator
 */
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 注册短信发送
     * 
     * @param telephone
     * @param verifyCode
     * @return
     */
    public static Integer sendTelephoneVerify(String telephone, String verifyCode) {
        /* url内容组成 */
        String CorpID = "LKSDK0003772";// 账户名
        String Pwd = "jkwqm814@";// 密码
        String send_time = "";
        StringBuilder content = new StringBuilder();
        content.append("您正在注册快译猫用户,验证码:").append(verifyCode).append(",请在15分钟内按页面提示提交验证码,切勿将验证码泄露于他人【快译猫】");
        String send_content = "";
        try {
            send_content = URLEncoder.encode(content.toString().replaceAll("<br/>", " "), "GBK");// 发送内容
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder url = new StringBuilder();
        url.append("http://mb345.com:999/ws/BatchSend2.aspx?").append("CorpID=").append(CorpID).append("&Pwd=").append(Pwd).append("&Mobile=").append(telephone).append("&Content=").append(send_content).append("&Cell=&SendTime=").append(send_time);
        // 发送短信的返回结果
        String result = creteHttpGet(url.toString());
        if ("error-create-get".equals(result)) {
            result = "0";
        }
        /* 记录短信日志 */
        logger.error("电话号码：{} ; 内容：{} ; 状态码：{}", telephone, content.toString(), result);
        /* 记录短信日志 */
        return Integer.parseInt(result);
    }

    /**
     * 修改密码短信提醒
     * 
     * @param telephone
     * @param verifyCode
     * @return
     */
    public static Integer updatePwdPhoneVerify(String telephone, String verifyCode) {
        /* url内容组成 */
        String CorpID = "LKSDK0003772";// 账户名
        String Pwd = "jkwqm814@";// 密码
        String send_time = "";
        StringBuilder content = new StringBuilder();
        content.append("您正在找回密码,验证码:").append(verifyCode).append(",请在15分钟内按页面提示提交验证码,切勿将验证码泄露于他人【快译猫】");
        String send_content = "";
        try {
            send_content = URLEncoder.encode(content.toString().replaceAll("<br/>", " "), "GBK");// 发送内容
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder url = new StringBuilder();
        url.append("http://mb345.com:999/ws/BatchSend2.aspx?").append("CorpID=").append(CorpID).append("&Pwd=").append(Pwd).append("&Mobile=").append(telephone).append("&Content=").append(send_content).append("&Cell=&SendTime=").append(send_time);
        // 发送短信的返回结果
        String result = creteHttpGet(url.toString());
        if ("error-create-get".equals(result)) {
            result = "0";
        }
        /* 记录短信日志 */
        logger.error("电话号码：{} ; 内容：{} ; 状态码：{}", telephone, content.toString(), result);
        /* 记录短信日志 */
        return Integer.parseInt(result);
    }

    public static String creteHttpGet(String url) {
        /* 创建httpclient */
        CloseableHttpClient client = HttpClients.createDefault();
        /* 创建get请求 */
        HttpGet get = new HttpGet(url);
        String result = "";
        try {
            /* 创建请求响应 */
            CloseableHttpResponse response = client.execute(get);
            /* 获取请求内容 */
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            result = "error-create-get";
        }
        return result;
    }

    public static String createHttpPost(String url, Map<String, String> map) throws Exception {
        /* 创建httpclient */
        CloseableHttpClient client = HttpClients.createDefault();
        /* 创建post */
        HttpPost post = new HttpPost(url);
        /* 创建参数列表 */
        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> val : map.entrySet()) {
            String name = val.getKey();
            String value = val.getValue();
            NameValuePair pair = new BasicNameValuePair(name, value);
            valuePairs.add(pair);
        }
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, "utf-8");
        post.setEntity(urlEncodedFormEntity);
        /* 获取请求响应 */
        CloseableHttpResponse response = client.execute(post);
        /* 获取响应正文内容 */
        HttpEntity entity = response.getEntity();
        /* 将正文内容转化成字符串 */
        String result = EntityUtils.toString(entity);
        return result;
    }

    public static void main(String[] args) {
        sendTelephoneVerify("13551034100", "458426");
        // updatePwdPhoneVerify("13551034100", "222222");
    }
}
