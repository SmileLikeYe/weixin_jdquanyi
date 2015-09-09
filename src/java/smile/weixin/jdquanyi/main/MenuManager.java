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
 * �˵���������
 *
 * @author smile
 */
public class MenuManager {

    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // �������û�Ψһƾ֤  
        String appId = "wx60122c28b6abebb7";
        // �������û�Ψһƾ֤��Կ  
        String appSecret = "6c37b2fe61fc2ab619447d72c4588ea0";

        // ���ýӿڻ�ȡaccess_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // ���ýӿڴ����˵�  
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // �жϲ˵��������  
            if (0 == result) {
                log.info("�˵������ɹ���");
            } else {
                log.info("�˵�����ʧ�ܣ������룺" + result);
            }
        }
    }

    /**
     * ��װ�˵�����
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("�̲���");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("����ɡ");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("���޳�Ʊ");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("ʧ���ѯ");
        btn14.setType("click");
        btn14.setKey("14");
//        btn14.setUrl("http://jdquanyi.duapp.com/LostThingsDisplay.jsp");

        CommonButton btn21 = new CommonButton();
        btn21.setName("����ʱ��");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("��ϵ��ʽ");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("�ζ�����");
        btn23.setType("click");
        btn23.setKey("23");
//
//        CommonButton btn24 = new CommonButton();
//        btn24.setName("");
//        btn24.setType("click");
//        btn24.setKey("24");

        CommonButton btn31 = new CommonButton();
        btn31.setName("��ҪͶ��");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("Ͷ��׷��");
        btn32.setType("click");
        btn32.setKey("32");
        
        CommonButton btn33 = new CommonButton();
        btn33.setName("��������");
        btn33.setType("click");
        btn33.setKey("33");
        
        CommonButton btn34 = new CommonButton();
        btn34.setName("��������");
        btn34.setType("click");
        btn34.setKey("34");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("��������");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("����֪��");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("���з���");
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33, btn34});

        /**
         * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
         *
         * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
         * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
