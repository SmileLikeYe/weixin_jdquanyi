/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.req;

/**
 *
 * @author smile
 */
public class VoiceMessge extends BaseMessage {

    //ý���ID
    private String MediaId;
    //���ֵĸ�ʽ
    private String Format;

    public void setMediaId(String MediaId) {
        this.MediaId = MediaId;
    }

    public void setFormat(String Format) {
        this.Format = Format;
    }

    public String getMediaId() {
        return MediaId;
    }

    public String getFormat() {
        return Format;
    }

}
