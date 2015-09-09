/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smile.weixin.jdquanyi.pojo.AccessToken;
import smile.weixin.jdquanyi.pojo.Button;
import smile.weixin.jdquanyi.pojo.CommonButton;
import smile.weixin.jdquanyi.pojo.ComplexButton;
import smile.weixin.jdquanyi.pojo.Menu;
import smile.weixin.jdquanyi.pojo.ViewButton;
import smile.weixin.jdquanyi.util.WeixinUtil;


/**
 * 菜单管理器类
 *
 * @author smile
 */
public class MenuManager {

    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证  
        String appId = "wx60122c28b6abebb7";
        // 第三方用户唯一凭证密钥  
        String appSecret = "6c37b2fe61fc2ab619447d72c4588ea0";

        // 调用接口获取access_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单  
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果  
            if (0 == result) {
                log.info("菜单创建成功！");
            } else {
                log.info("菜单创建失败，错误码：" + result);
            }
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("短驳车");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公益伞");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("辅修车票");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("失物查询");
        btn14.setType("click");
        btn14.setKey("14");
//        btn14.setUrl("http://jdquanyi.duapp.com/LostThingsDisplay.jsp");

        CommonButton btn21 = new CommonButton();
        btn21.setName("工作时间");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("联系方式");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("嘉定外卖");
        btn23.setType("click");
        btn23.setKey("23");
//
//        CommonButton btn24 = new CommonButton();
//        btn24.setName("");
//        btn24.setType("click");
//        btn24.setKey("24");

        CommonButton btn31 = new CommonButton();
        btn31.setName("我要投诉");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("投诉追踪");
        btn32.setType("click");
        btn32.setKey("32");
        
        CommonButton btn33 = new CommonButton();
        btn33.setName("往期问题");
        btn33.setType("click");
        btn33.setKey("33");
        
        CommonButton btn34 = new CommonButton();
        btn34.setName("加入我们");
        btn34.setType("click");
        btn34.setKey("34");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("你最在意");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("你想知道");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("你有烦恼");
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33, btn34});

        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
