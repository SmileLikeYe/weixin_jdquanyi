/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.message.resp;

/**
 *�ı���Ϣ�����ںŻ�Ӧ��
 * @author smile
 */
public class TextMessage extends BaseMessage {
    
    //����
    private String Content;

    public void setContent(String content) {
        this.Content = content;
    }

    public String getContent() {
        return Content;
    }
    
    
}
