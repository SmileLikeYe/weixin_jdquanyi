/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.tools;

import java.util.List;
import smile.weixin.jdquanyi.database.Database;
import smile.weixin.jdquanyi.database.WM_WaiMai;
import smile.weixin.jdquanyi.message.resp.Article;

/**
 *
 * @author smile
 */
public class WaimaiMenu {
    
    public Article getMenuDisplay(String restaurantName) {
            Database db = new Database();
            List<WM_WaiMai> wm_list = db.getWaiMai();
            Article article = new Article();
            article.setTitle("不存在");
            
            for (WM_WaiMai wm : wm_list) {
                if (wm.getWM_name().equals(restaurantName)) {
                    article.setTitle(wm.getWM_name() + "菜单");
                    article.setDescription("点我！");
                    article.setUrl(wm.getWM_menuLink());
                    break;
                }                
            }            
            return article;                     
    }   
}
