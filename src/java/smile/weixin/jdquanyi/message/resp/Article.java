/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.message.resp;

/**
 * ͼ����Ϣ
 *(����֮�������⽨��һ��ͼ����Ϣ���࣬����Ϊ��ArticleMessage�п����ж���Aritcle)
 * @author smile
 */
public class Article extends BaseMessage{

    //����
    private String Title;
    //����
    private String Description;
    //ͼƬ����
    private String PicUrl;
    //���ͼƬ��ת������
    private String Url;

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public String getUrl() {
        return Url;
    }

}
