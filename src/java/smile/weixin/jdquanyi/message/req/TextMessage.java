/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.req;

/**
 * �ı���Ϣ(�û�����)
 *
 * @author smile
 */
public class TextMessage extends BaseMessage {

    //��Ϣ����

    private String Content;

    public void setContent(String content) {
        this.Content = content;
    }

    public String getContent() {
        return Content;
    }

}
