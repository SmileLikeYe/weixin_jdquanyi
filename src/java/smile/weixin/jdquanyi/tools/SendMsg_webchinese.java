/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.tools;

/**
 *
 * @author smile
 */
 
import java.io.IOException;  
  
import org.apache.commons.httpclient.Header;  
import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.HttpException;  
import org.apache.commons.httpclient.NameValuePair;  
import org.apache.commons.httpclient.methods.PostMethod;  
  
public class SendMsg_webchinese {  
  
    /** 
     * @author dengsilinming 
     * @date Sep 18, 2012 
     * @time 9:38:25 AM 
     * @param args 
     * @throws IOException 
     * @throws HttpException 
     * @description 
     */  
    public void send(String telephone, int id) throws HttpException, IOException {  
        HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");  
        // PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");  
        post.addRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("Uid", "jdquanyi"),// 注册的用户名  
                new NameValuePair("Key", "82594d54b061255b1d6d"),// 注册成功后，登录网站后得到的密钥  
                new NameValuePair("smsMob", telephone),// 手机号码  
                new NameValuePair("smsText", "同学您好，您的验证码为：yzm " + id) };// 短信内容  
        post.setRequestBody(data);  
  
        client.executeMethod(post);  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  
        System.out.println("statusCode:" + statusCode);  
        for (Header h : headers) {  
            System.out.println("---" + h.toString());  
        }  
        String result = new String(post.getResponseBodyAsString().getBytes(  
                "gbk"));  
        System.out.println(result);  
        post.releaseConnection();
  
    }  
  
}
