/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.resp;

/**
 *���ֵĶ���
 * (����ΪʲôҪ�ඨ���music)
 * @author smile
 */
public class Music extends BaseMessage{

    //��������
    private String Title;
    //��������
    private String Description;
    //��������
    private String MusicUrl;
    //��Ʒ���������ӣ�wifi����������ʹ��
    private String HQMusicUrl;

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setMusicUrl(String MusicUrl) {
        this.MusicUrl = MusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

}
