/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.message.resp;

import java.util.List;

/**
 *news��Ϣ����������Ӧ��
 * @author smile
 */
public class NewsMessage extends BaseMessage {
    
        // ͼ����Ϣ����������Ϊ10������  
    private int ArticleCount;  
    // ����ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ  
    private List<Article> Articles; 

    public void setArticleCount(int ArticleCount) {
        this.ArticleCount = ArticleCount;
    }

    public void setArticles(List<Article> Articles) {
        this.Articles = Articles;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }
    
    
    
}
