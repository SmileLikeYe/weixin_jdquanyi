/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.req;

/**
 * ͼƬ��Ϣ(�û�����)
 *
 * @author smile
 */
public class ImageMessage extends BaseMessage {

    //ͼƬ������
    private String PicUrl;

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public String getPicUrl() {
        return PicUrl;
    }

}
