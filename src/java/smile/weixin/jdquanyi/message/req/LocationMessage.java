/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.req;

/**
 * λ����Ϣ(�û�����)
 *
 * @author smile
 */
public class LocationMessage extends BaseMessage {

    //����λ�õľ���
    private String Location_X;
    //����λ�õ�γ��
    private String Location_Y;
    //��ͼ���ŵĴ�С
    private String Scale;
    //����λ�õ���Ϣ
    private String Label;

    public void setLocation_X(String Location_X) {
        this.Location_X = Location_X;
    }

    public void setLocation_Y(String Location_Y) {
        this.Location_Y = Location_Y;
    }

    public void setScale(String Scale) {
        this.Scale = Scale;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public String getLocation_X() {
        return Location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public String getLabel() {
        return Label;
    }

}
