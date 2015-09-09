/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smile.weixin.jdquanyi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import smile.weixin.jdquanyi.database.*;
import smile.weixin.jdquanyi.message.resp.Article;
import smile.weixin.jdquanyi.message.resp.NewsMessage;
import smile.weixin.jdquanyi.message.resp.TextMessage;
import smile.weixin.jdquanyi.tools.BusTime;
import smile.weixin.jdquanyi.tools.SendMsg_webchinese;
import smile.weixin.jdquanyi.tools.WaimaiMenu;
import smile.weixin.jdquanyi.util.MessageUtil;

/**
 * ���ķ�����
 *
 * @author liufeng
 * @date 2013-05-20
 */
public class CoreService {

    //Key��21 �˵���ѧУ������ϵ�绰
    public static String getSchoolDepartmentNumber() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(emoji(0xe148)).append("ͬ�ô�ѧ�ζ�У����Ҫ������ϵ��ʽ").append("\n");
        buffer.append("������ƪ��").append("\n");
        buffer.append("-У��������").append(69589110).append("\n");
        buffer.append("-�������").append(69584635).append("\n");
        buffer.append("-ѧУҽԺ��").append(69589213).append("\n").append("�칫�ص㣺����������").append("\n");
        buffer.append("-�ڹ���ѧ��").append(69584670).append("\n").append("�칫�ص㣺��¥114").append("\n");
        buffer.append("-������ѯ��").append(69589851).append("\n").append("�칫�ص㣺��¥415").append("\n");
        buffer.append("-��ҵָ����").append(69584643).append("\n").append("�칫�ص㣺��¥113-1").append("\n");
        buffer.append("-�������").append(69589848).append("\n").append("�칫�ص㣺��¥316-2,3").append("\n");
        buffer.append("-ͼ��ݣ�").append(69589619).append("\n").append("�칫�ص㣺ͼ���107��").append("\n");

        buffer.append("������ƪ��").append("\n");
        buffer.append("-����칫�ң�").append(69589126).append("\n").append("�칫�ص㣺ͬ��¥112��").append("\n");
        buffer.append("-��ҵ������").append(69589108).append("\n").append("�칫�ص㣺ͬ��¥116��").append("\n");
        buffer.append("-�������񲿣�").append(69589986).append("\n").append("�칫�ص㣺����Էʳ�ð칫��").append("\n");
        buffer.append("-��ѧ¥����").append(69588907).append("\n").append("�칫�ص㣺��¥211��").append("\n");
        buffer.append("-��Ԣ������").append(69589719).append("\n").append("�칫�ص㣺ѧ����Ԣ2��¥").append("\n");
        buffer.append("-���ŷ��񲿣�").append(69589002).append("\n").append("�칫�ص㣺��¥416��").append("\n");
        buffer.append("-�������ģ�").append(69588908).append("\n").append("�칫�ص㣺����¥104-1��").append("\n");
        buffer.append("-���ڳ��ӣ�").append(69589092).append("\n").append("�칫�ص㣺����¥104-3��").append("\n");

        buffer.append("��֪�����࣬�������ĸ�����Ŷ��");

        return buffer.toString();
    }

    //Key:22 �˵�������������ϵ�绰
    public static String getSchoolDepartmentWorkday() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(emoji(0xe148)).append("ͬ�ô�ѧ�ζ�У����Ҫ���Ź���ʱ��ͳ��ؿ���ʱ��").append("\n");
        buffer.append("(��ܰ��ʾ����ֱ�ӻظ�����Ĺؼ��֣�����ȡ��������֪������֪���ĸ�����Ϣ����ظ���УҽԺ����)").append("\n");
        buffer.append("-УҽԺ").append("\n");
        buffer.append("-�߶�����").append("\n");
        buffer.append("-����").append("\n");
        buffer.append("-ƹ����").append("\n");
        buffer.append("-����").append("\n");
        buffer.append("-����").append("\n");
        buffer.append("-��ë��").append("\n");
        buffer.append("��֪�����࣬�������ĸ�����Ŷ��").append("\n");

        return buffer.toString();
    }

    /**
     * ����΢�ŷ���������
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        Database db = new Database();
        String currentTime;
        //��õ�ǰʱ�� 
        //��ʽ���£� 20150703-17:12:43
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("20yyMMdd-HH:mm:ss");
        currentTime = sdf.format(calendar.getTime());
        
        try {
            // Ĭ�Ϸ��ص��ı���Ϣ����  
            String respContent = "�������쳣�����Ժ��ԣ�";

            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");

            // Ĭ�ϻظ��ı���Ϣ  
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            //����ͼ����Ϣ
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setFuncFlag(0);
            

            // �ı���Ϣ     
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //�õ��û���������Ϣ
                String content = requestMap.get("Content");
                //ȥ���û��ظ���Ϣ��ǰ��ո�
                content = content.trim();               
//                respContent = "���Ȩ�棬�������⣡\n"
//                        + "�㷴Ӧ���κ����⣬ȨС��ÿ��21��00ǰһ����ظ��� ^_^��"; 
                respContent = "����ڼ䣬ȨС����ܻظ�����ʱ�������½�~";
                
                //respContent = content.trim();
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);

                List<Article> articleList = new ArrayList<Article>();
                
                //��ȡ�����˵�
                WaimaiMenu wmmenu = new WaimaiMenu();
                Article menu_article = new Article();
                menu_article = wmmenu.getMenuDisplay(content);
                if (! "������".equals(menu_article.getTitle())) {
                    articleList.add(menu_article);
                    // ����ͼ����Ϣ����  
                    newsMessage.setArticleCount(articleList.size());
                    // ����ͼ����Ϣ������ͼ�ļ���  f
                    newsMessage.setArticles(articleList);
                    // ��ͼ����Ϣ����ת����xml�ַ���  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }  
                               
                
                //ȷ����ȡ����ɡ => �ı乫��ɡ�����ж�Ӧ����Ϊ�ٽ�ת̬, pure=false 
                if ("����".equals(content)) {
                    //v = \, p =\ (���ݿ���û�м�¼) 
                    if (db.getGYS_Id(fromUserName) == 0) {
                        respContent = "��Ҫ�蹫��ɡô~\n"
                                + "�ȵ�����½ǲ˵��еĹ���ɡŶ��";
                    } else {
                        //v = 0, p= 1  δͨ����֤����δ����(����v=0��p�϶�=1, ��Ϊûͨ����֤�϶������ܽ�ɡ)
                        if (db.GongYiSanIsVerified(fromUserName) == false /*&& db.GongYiSanIsPure(fromUserName) == true*/) {
                            respContent = "��ã�����ֻ����뻹û��ͨ����֤Ŷ��\n"
                                    + "���ϸ��������ʽ���лظ���gys �ֻ����� ����\n"
                                    + "60���ڽ����յ�����֤��Ķ��š�\n"
                                    + "��ʱ�뽫��֤��ظ���΢��ƽ̨��";
                        } else {
                            // v = 1 , p = 1 ͨ����֤����δ����
                            if (/*db.GongYiSanIsVerified(fromUserName) == true && */db.GongYiSanIsPure(fromUserName) == true) {
                                try {
                                    db.updatePure(fromUserName, false);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                String date = new Date().toString();
                                respContent = "����ϲ����" + date + "�ɹ����ù���ɡ����\n\n"
                                        + "�뽫�˻ظ���ʾ��������Ϊ��ɡƾ֤������ ^-^\n"
                                        + "---------\n"
                                        + "�뼰ʱ�黹��F¥һ¥��ֵ̨�ദ��\n"
                                        + "����ܰ���ѣ���ɡʱ�����֤��Ϊ: " + db.getGYS_Id(fromUserName) + "��\n"
                                        + "��Ҳ�����ٴε��������ɡ���鿴��֤�롣\n";
//                                        +"��ps:���û�й黹�Ͳ����ٽ�Ŷ����"; 
                            } else {
                                // v = 1 , p = 0 �Ѿ����� (������� p = 0 ,v = 0�����)
                                respContent = "ͬѧ���ã��뼰ʱ�����õ�ɡ�뻻��F¥һ¥��̨��\n"
                                        + "��ɡʱ�����֤��Ϊ��" + db.getGYS_Id(fromUserName) + "\n"
                                        + "��ps:���û�й黹�Ͳ����ٽ�Ŷ����";
                            }
                        }
                    }

//                   respContent =  content + " ��������Ӧ����db.getGYS_Id()������";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("ʱ�̱�".equals(content)) {
                    Article article = new Article();
                    article.setTitle("�ζ�У���೵���б�");
                    article.setDescription("���������µ�ʱ�̱��������̲����������缰�����Ӱ�Σ����󷢣�����·���ٳ���");
                    article.setPicUrl("http://jdquanyi.duapp.com/img/logo.png");
                    article.setUrl("http://jdquanyi.duapp.com/Bus.jsp");
                    articleList.add(article);

                    // ����ͼ����Ϣ����  
                    newsMessage.setArticleCount(articleList.size());
                    // ����ͼ����Ϣ������ͼ�ļ���  f
                    newsMessage.setArticles(articleList);
                    // ��ͼ����Ϣ����ת����xml�ַ���  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }

                if ("УҽԺ".equals(content)) {
                    respContent = "���ſ��ң��ڿơ���ǻ�ơ���Һ�ҡ���ҩ�ҡ�ҩ���շѡ����顢���\n"
                            + "��ע���������ʱ�䣺\n"
                            + "���� 9��00 - 11��30 \n"
                            + "���� 12��30 - 17��00\n\n"
                            + "ÿ��������Ƥ����,ÿ���忪���ڿ�ר������,"
                            + "����ʱ���Ϊ����ʱ��\n"
                            + "-----\n"
                            + "����˵����ÿ�µ�һ�������������������������������ζ�����\n"
                            + "TIPS:��Ҫ���Ǵ�һ��ͨŶ~ ";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("�߶�����".equals(content)) {
                    respContent = "�ص㣺ͼ��ݺ󷽣�\n"
                            + "�۸񣺶�ˮ�ش�1����30����/15Ԫ\n"
                            + "     ���ش�30Ԫ/Сʱ\n"
                            + "     �ṩ��ˣ�\n"
                            + "����ʱ�䣺8��:00-17:00���ڲ�Ӱ��������ѧ��Э��������£�\n"
                            + "ע����ĩ���ܻ���Ӵ�����������";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("����".equals(content)) {
                    respContent = "�ص㣺����¥�����ᣩһ¥��������ת��\n"
                            + "�۸�: ���ο� 20 Ԫ/ ��\n"
                            + "     �¿�250 Ԫ/10 ��\n"
                            + "     ����400 Ԫ/20 ��\n"
                            + "     ѧ�ڿ� 600Ԫ ���޴���\n"
                            + "     �꿨 850 Ԫ ���޴���\n"
                            + "����ʱ�䣺11:30-22:00";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("ƹ����".equals(content)) {
                    respContent = "�ص�: ����¥�����ᣩ������ת�������ڣ��������ǿգ��������ţ�����¥��D¥��һ¥����¥\n"
                            + "����ʱ�䣺\n"
                            + "����¥  ����17��30?21��30   �ܶ���������15��30?21��30  \n"
                            + "˫���� 13�� 00?21��30��\n"
                            + "�����ǿ� ��֪ͨ��һ��Ϊ8:00-20:00\n"
                            + "��¥ 8��00-17��00�������գ���Ӱ��������ѧǰ����\n"
                            + "�ܶ�����������Ϊ��ʦ�ʱ��\n"
                            + "��������:����¥ 8-10 �����ң������ǿ� 4������¥ 7������¥2��\n";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("����".equals(content)) {
                    respContent = "�ص�: ͬ��¥����չ���ģ���¥\n"
                            + "�۸񣺻�ʽ���� 15Ԫ/Сʱ��˹ŵ��18Ԫ/Сʱ���л�Ա�����Ż�\n"
                            + "������������ʽ����3����˹ŵ��1��\n"
                            + "����ʱ�䣺17:00-22:00 �������գ�13:00-22:00 ����ĩ��\n"
                            + "Tips:���Դ�绰Ԥ��Ŷ��Ԥ���绰13764647792";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("����".equals(content)) {
                    respContent = "�ص�: �����ԣ�УҽԺ����\n"
                            + "����ʱ�䣺8��00-20��00 �ڲ�Ӱ��������ѧ��ǰ����\n"
                            + "��������:4Ƭ"
                            + "Tips:�����ѣ�����ǰ�������۸��뱾��ͬ";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("��ë��".equals(content)) {
                    respContent = "�ص�: �����ǿգ��������ţ�����¥��D¥��һ¥��ͬ��¥����չ���ģ�һ¥\n"
                            + "------\n"
                            + "����ʱ��: \n"
                            + "�����ǿ� ��֪ͨ��һ��Ϊ8:00-20:00\n"
                            + "�ܶ�����������Ϊ��ʦ�ʱ��\n"
                            + "ͬ��¥ ��֪ͨ��һ��Ϊ8:00-20:00\n(��Ҫ��ǰ�ڽ����ԤԼ)"
                            + "------\n"
                            + "��������:�����ǿ� 4Ƭ����¥ 3Ƭ��ͬ��¥ 4Ƭ";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                //fxfx 1252855 ���� ���ѧԺ
                if (content.trim().substring(0, 4).equals("fxfx")) {

                    String[] keywords = content.trim().split("\\s+");
                    if (keywords.length == 4) {
                        String studentNumber = keywords[1];
                        String name = keywords[2];
                        String academy = keywords[3];
                        FX_FuXiu fx_user = new FX_FuXiu();
                        if (!db.FuXiuExists(fromUserName)) {
                            fx_user.setFx_fromUserName(fromUserName);
                            fx_user.setFx_description("New Add");
                            fx_user.setFx_studentNumber(studentNumber);
                            fx_user.setFx_academy(academy);
                            fx_user.setFx_name(name);
                            db.AddFuxiu(fx_user);
                            respContent = "��ϲ�㣬���޳�Ʊ�Ǽǳɹ���" + "\n" + content;
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        } else {
                            respContent = "���Ѿ��Ǽǹ���Ŷ��";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        }
                    } else {
                        respContent = "��Ҫ�ǼǸ��ްɣ���ʽ����Ŷ��~" + "\n" + content;
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);

                    }

                }

                //gys 13122363681 ����
                if (content.trim().substring(0, 3).equals("gys")) {

                    String[] keywords = content.trim().split("\\s+");
                    if (keywords.length == 3) {
                        String telephone = keywords[1];
                        String name = keywords[2];

                        int tag = db.GongYiSanExists(fromUserName);
                        //û�еǼǹ�����ɡ
                        if (tag == 0) {
                            GYS_GongYiSan gys_user = new GYS_GongYiSan();
                            gys_user.setGYS_name(name);
                            gys_user.setGYS_telephone(telephone);
                            gys_user.setGYS_fromUserName(fromUserName);
                            gys_user.setGYS_pure(true);
                            gys_user.setGYS_verification(false);
                            db.AddGongYiSan(gys_user);
                            int id = db.getGYS_Id(fromUserName);
                            SendMsg_webchinese sendMsg = new SendMsg_webchinese();
                            sendMsg.send(telephone, id);
                            respContent = "���������ֻ������յ�����֤�룬�磺yzm 12";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                            //�ѵǼǣ��ֻ�������֤δͨ��
                        } else if (tag == 1) {
                            db.updateTelephone(fromUserName, telephone);
                            int id = db.getGYS_Id(fromUserName);
                            SendMsg_webchinese sendMsg = new SendMsg_webchinese();
                            sendMsg.send(telephone, id);
                            respContent = "���������ֻ������յ�����֤�룬�磺yzm 12";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        } //�ѵǼǣ��ֻ�������֤��ͨ��
                        else {
                            respContent = "���Ѿ��Ǽǹ���Ŷ��";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                        }
                        //gys��ͷ ���Ǹ�ʽ����
                    } else {
                        respContent = "��Ҫ�Ǽ�ע�ṫ��ɡ��Ϣ�ɣ���ʽ����Ŷ��~\n"
                                + "���ϸ��գ�gys �ֻ����� ���� \n"
                                + "�ĸ�ʽ���лظ�Ŷ��";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);

                    }
                }

                //yzm 23
                if (content.trim().substring(0, 3).equals("yzm")) {

                    String[] keywords = content.trim().split("\\s+");
                    if (keywords.length == 2) {
                        String str_id = keywords[1];
                        int i_id = Integer.parseInt(str_id);

                        int tag = db.GongYiSanExists(fromUserName);
                        //û�еǼǹ�����ɡ 
                        if (tag == 0) {
                            respContent = "��һ��ʹ�ù���ɡ���Ͽ�����֤�����ֻ�����ɣ�\n"
                                    + "���ϸ��������ʽ���лظ���gys �ֻ����� ����\n"
                                    + "60���ڽ����յ�����֤��Ķ��š�\n"
                                    + "��ʱ�뽫��֤��ظ���΢��ƽ̨��";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                            //�ѵǼǣ��ֻ�������֤δͨ��
                        } else if (tag == 1) {
                            //һ��
                            if (i_id == db.getGYS_Id(fromUserName)) {
                                db.updateVerification(fromUserName, true);
                                respContent = "��ϲ����֤�ɹ������Ե���˵��еġ�����ɡ����ȡ����ɡ!";
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else {
                                respContent = "�Բ�����֤�벻һ�£�\n"
                                        + "���ٴ��ϸ��������ʽ���лظ���gys �ֻ����� ����\n"
                                        + "60���ڽ����յ�����֤��Ķ��š�\n"
                                        + "��ʱ�뽫��֤��ظ���΢��ƽ̨��";;
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            }
                        } //�ѵǼǣ��ֻ�������֤��ͨ��
                        else {
                            respContent = "����ֻ�����֮ǰ�Ѿ�ͨ����֤��Ŷ��";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                        }

                    } else {
                        respContent = "��֤���ʽ����";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    }
                }
                
                //ts 18��¥���С���ܺó�߹!�ܲ��ܽ����~
                if (content.trim().substring(0, 2).equals("ts")) {                    
                    //������
                    MQ_MyQuestions mq = new MQ_MyQuestions();
                    mq.setFromUserName(fromUserName);
                    mq.setPutTime(currentTime);
                    mq.setDescription(content.substring(3));
                    mq.setReply("ȨС���߷���...");
                    mq.setTag(false);
                    db.AddQuestion(mq);
                    respContent = "�����Ѿ�������������\n����Ե㿪��Ͷ��׷�١����鿴������ȡ�";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
                

            } // ͼƬ��Ϣ  
            else {
                if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                    respContent = "��ϲ���㷢��ͼƬ��";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } // ����λ����Ϣ  
                else {
                    if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                        respContent = "ͬѧ�㷢���ǵ���λ��Ŷ��";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } // ������Ϣ  
                    else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                        respContent = "ͬѧ�㷢�͵���������ϢŶ";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } // ��Ƶ��Ϣ  
                    else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                        respContent = "ͬѧ�㷢�͵�����Ƶ��ϢŶ��";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } // �¼�����  
                    else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                        // �¼�����  
                        String eventType = requestMap.get("Event");
                        // ����  
                        if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                            respContent
                                    = "��л����עͬ�ô�ѧѧ����ζ�У��Ȩ�汣�������������\n"
                                    + "���ǵ�΢��һֱ�ڲ������ƣ�ͬʱ����֧�ָ������Ǹ���Ķ�����"
                                    + "��ϣ�����ܶ����ǵ�΢��ƽ̨���ճ����������������뽨�飬һ�����ɣ����ǻ�Ϊ"
                                    + "���ṩ�ر������Ͽ������·��˵��ľ�Ʒ���ܰɣ�\n"
                                    + "---------\n"
                                    + "";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        } // ȡ������  
                        else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                            // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
                        } // �Զ���˵�����¼�  
                        else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                            // �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ  
                            String eventKey = requestMap.get("EventKey");
                            if (eventKey.equals("11")) {
                                db.updateMenuClickCount(11);
                                List<Article> articleList1 = new ArrayList<Article>();
                                List<String> nextBusTimes = new BusTime().getNextBus();

                                Article articleSKB = new Article();
                                articleSKB.setTitle("�ζ�У��2015����ٰ೵���б�");
                                articleSKB.setDescription("���������µ�ʱ�̱��������̲����������缰�����Ӱ�Σ����󷢣�����·���ٳ���");
                                articleSKB.setPicUrl("http://jdquanyi.duapp.com/img/shujia.png");
//                                articleSKB.setUrl("http://jdquanyi.duapp.com/Bus.jsp");
                                articleSKB.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MTY1NjYyOA==&mid=208194187&idx=1&sn=1eccd3d28841a370003aaa62e0422297&scene=1&key=c76941211a49ab5840ddd109192a835cf49fc0ba4281ae8ede11d78132d625edb4cc7fa6a7d5aa5dfb55089c1fe52fb8&ascene=0&uin=MTA4MTU5NjEwMQ%3D%3D&devicetype=iMac+MacBookAir6%2C2+OSX+OSX+10.10.4+build(14E46)&version=11020012&pass_ticket=Zr9uPDo0TNM81WLntyzIICs5uRRJkUwipDFxpwWMIzcaJ6LS1rNjbwh5i9WIaoAD");

                                Article articleT = new Article();
                                articleT.setTitle(emoji(0x1F551) + "���������һ�೵����ٰ��Ѹ���~��");
                                articleT.setDescription("");

                                Article article = new Article();
                                article.setTitle(emoji(0x1F68C) + "���̲�����");
                                article.setDescription("");
//                                article.setUrl("");
//                                article.setPicUrl("");

                                Article article0 = new Article();
                                article0.setTitle(emoji(0x1F51C) + "������վ      " + nextBusTimes.get(0));
                                article0.setDescription("");
//                                article0.setUrl("");
//                                article0.setPicUrl("");

                                Article article1 = new Article();
                                article1.setTitle(emoji(0x1F51C) + "�ζ�У��      " + nextBusTimes.get(1));
                                article1.setDescription("");
//                                article1.setPicUrl("");
//                                article1.setUrl("");

                                Article articleDA = new Article();
                                articleDA.setTitle(emoji(0x1F68C) + "�����󷢡�");
                                articleDA.setDescription("");
//                                articleDA.setUrl("");
//                                articleDA.setPicUrl("");

                                Article article2 = new Article();
                                article2.setTitle(emoji(0x1F51C) + "��ͤ����    " + nextBusTimes.get(2));
                                article2.setDescription("");
//                                article2.setPicUrl("");
//                                article2.setUrl("");

                                Article article3 = new Article();
                                article3.setTitle(emoji(0x1F51C) + "ѧУ���ſ�    " + nextBusTimes.get(3));
                                article3.setDescription("");
//                                article3.setPicUrl("");
//                                article3.setUrl("");

                                Article articleGS = new Article();
                                articleGS.setTitle(emoji(0x1F68C) + "�����ٳ���(7��¥ǰ�ϳ�)");
                                articleGS.setDescription("");
                                articleGS.setUrl("");
                                articleGS.setPicUrl("");

                                Article article4 = new Article();
                                article4.setTitle(emoji(0x1F51C) + "����·����վ    " + nextBusTimes.get(4));
                                article4.setDescription("");
//                                article4.setPicUrl("");
//                                article4.setUrl("");                                

                                articleList1.add(articleSKB);
                                articleList1.add(articleT);
                                articleList1.add(article);
                                articleList1.add(article0);
                                articleList1.add(article1);
                                articleList1.add(articleDA);
                                articleList1.add(article2);
                                articleList1.add(article3);
                                articleList1.add(articleGS);
                                articleList1.add(article4);

                                // ����ͼ����Ϣ����  
                                newsMessage.setArticleCount(articleList1.size());
                                // ����ͼ����Ϣ������ͼ�ļ���  f
                                newsMessage.setArticles(articleList1);
                                // ��ͼ����Ϣ����ת����xml�ַ���  
                                respMessage = MessageUtil.newsMessageToXml(newsMessage);

                            } else if (eventKey.equals("12")) {//����ɡ
                                db.updateMenuClickCount(12);
                                int tag = db.GongYiSanExists(fromUserName);
                                //v=?��û�еǼ�
                                if (tag == 0) {
                                    respContent = "����ɡ��������ȫ�����!\n"
                                            + "��Ҫ��ͨ����ɡ���ܣ��Ͽ�����֤�����ֻ�����ɣ�\n"
                                            + "���ϸ��������ʽ���лظ���gys �ֻ����� ����\n"
                                            + "60���ڽ����յ�����֤��Ķ��š�\n"
                                            + "��ʱ�뽫��֤��ظ���΢��ƽ̨��";
//                                            + "------\n"
//                                            + "��һ���ù���ɡ������ֵ�ఢ�̳�ʾ��֤������Ϊ����ƾ֤�Ϳ����� ^-^\n"
//                                            + "�Ժ��ֱ�ӵ���˵��еġ�����ɡ���Ϳ�������";
                                } //v=0���ѵǼǣ��ֻ�����û��ͨ����֤
                                else if (tag == 1) {
                                    respContent = "���ֻ����뻹û��ͨ����֤Ŷ��"
                                            + "�뾡�첢���ϸ��������ʽ���лظ���gys �ֻ����� ����\n"
                                            + "60���ڽ����յ�����֤��Ķ��š�\n"
                                            + "��ʱ�뽫��֤��ظ���΢��ƽ̨��";
                                } //v=1,p=1�ֻ�����ͨ����֤�ˣ�û�н�ɡ
                                else if (db.GongYiSanIsPure(fromUserName)) {
                                    respContent = "ͬѧ���ã���ӭ����B¥��F¥������¥���ù���ɡ���������Ƿ�ȷ��Ҫ"
                                            + "��ȡ����ɡ��ȷ����ظ������á�";
                                } else {
                                    respContent = "ͬѧ���ã��ǵü�ʱ�黹��F¥һ¥��̨��\n" //v=1, p=0�Ѿ������
                                            + "��ps:��ɡʱ���ǵ���д��Ψһ����֤��: yzm" + db.getGYS_Id(fromUserName);
                                }

                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);

                            } else if (eventKey.equals("13")) {
                                db.updateMenuClickCount(13);
                                if (db.FuXiuExists(fromUserName)) {
                                    respContent = "��ϲ���õ���" + db.getWC_count() + "�ܵĸ��޳�Ʊ�� ^-^"
                                            + "\n �����㸨�޳�Ʊ�ĵ���ƾ֤��\n";
                                           
                                    db.updateFX_count(fromUserName);

                                            //"���ڽ��񴦱��ܻ���ͳ�Ƹ���������ϵ���ų��������Ա��ܣ��ڶ��ܣ�û�и��޳�- -��";
                                    //+ "����(15��)���ް೵������ͣһ�Ρ����ܸ��ް೵��Ʊ�������š��Դ�Ϊ����ɵ��鷳������б�Ǹ.\n"
                                    //+ "Ҳ��ͬѧ�ǻ���ת�棬лл^-^";
                                    textMessage.setContent(respContent);
                                    respMessage = MessageUtil.textMessageToXml(textMessage);
                                } else {
//                                    FX_FuXiu fx_user = new FX_FuXiu();
//                                    fx_user.setFx_fromUserName(fromUserName);
//                                    fx_user.setFx_description("New Add");
//                                    db.AddFuxiu(fx_user);
                                    respContent = "�Բ���ͬѧ�㲻�ڸ��޳�Ʊ������ ~~.";
                                    textMessage.setContent(respContent);
                                    respMessage = MessageUtil.textMessageToXml(textMessage);
                                }
                            } else if (eventKey.equals("14")) {
                                db.updateMenuClickCount(14);
                                respContent = "�뾡�쵽����ʧ��Ʒ��Χ��ʧ���ŵ���Ѱ��";
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);

                            } else if (eventKey.equals("21")) {
                                db.updateMenuClickCount(21);
                                respContent = getSchoolDepartmentWorkday();
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else if (eventKey.equals("22")) {
                                db.updateMenuClickCount(22);
                                respContent = getSchoolDepartmentNumber();
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else if (eventKey.equals("23")) {
                                db.updateMenuClickCount(23);
                                respContent = "ֱ�ӵ���������ɲ鿴�˵�Ŷ��\n";
                                List<WM_WaiMai> wm_list = db.getWaiMai();                                
                                
                                respContent += "*****����ʵ�㳡��*****\n";  
                                for (WM_WaiMai wm : wm_list) {                                    
//                                    respContent += "--------\n" + emoji(0x1F35A) + " "+ wm.getWM_name() + "\n"
//                                           + emoji(0xE009)+ wm.getWM_telephone() + "\n";  
                                                                              
                                    if ("��ʵ�㳡".equals(wm.getWM_address())) {
                                        respContent += "----\n" + "<a href=\"" + wm.getWM_menuLink()+"\">"+ 
                                                wm.getWM_name() +"</a> "+ "\n" + "Tel: "+wm.getWM_telephone() + "\n";
                                    }                                    
                                    
                                }
                                respContent += "\n*****������ء�*****\n";
                                for (WM_WaiMai wm : wm_list) {                                    
//                                    respContent += "--------\n" + emoji(0x1F35A) + " "+ wm.getWM_name() + "\n"
//                                           + emoji(0xE009)+ wm.getWM_telephone() + "\n";
                                                                                                                    
                                    if ("�����".equals(wm.getWM_address())) {
                                        respContent += "----\n" + "<a href=\"" + wm.getWM_menuLink()+"\">"+ 
                                                wm.getWM_name() +"</a> "+ "\n" + "Tel:"+wm.getWM_telephone() + "\n";
                                    }                                    
                                    
                                }
                                
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                                                               
                            } else if (eventKey.equals("31")) {//��ҪͶ��
                                db.updateMenuClickCount(31);                                
                                respContent = "ͬѧ������ۡ�\n"
                                        + "�����ҪͶ�ߣ��밴���������Ӹ�ʽ��\n"                                        
                                        + "ts 18��¥���С���ܺó�߹���ܲ��ܽ����?"
                                        + "\n---------------"                                        
                                        + "\n�����Ͷ��׷�١����ɼ�ʱ�鿴�Լ�������߷������";
                                        
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else if (eventKey.equals("32")) {
                                db.updateMenuClickCount(32);
                                List<MQ_MyQuestions> mq_list = db.GetMyQuestions(fromUserName);
                                if (mq_list.isEmpty()) {
                                    respContent = "����ú������û��Ͷ������Ŷ��";
                                }else { 
                                
                                respContent = "��Ͷ�ߵ������У�\n";
                                int i = 1;
                                for (MQ_MyQuestions mq : mq_list) {
                                    respContent += i + ". " + mq.getDescription() + "\n�ظ���" + mq.getReply() + "\n";                                 
                                    i ++;
                                }
                                }                                                                       
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else if (eventKey.equals("33")) {
                                db.updateMenuClickCount(33);
                                List<Article> articleList = new ArrayList<Article>();
                                Article article = new Article();
                                article.setTitle("�ζ����޻���ʼ�");
                                article.setDescription("");
                                article.setPicUrl("http://jdquanyi.duapp.com/img/quanyi.jpg");
                                article.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MTY1NjYyOA==&mid=206502641&idx=1&sn=f3dacb61f565f12fcf14dfb72d10fe7b#rd");
                                articleList.add(article);
                                // ����ͼ����Ϣ����  
                                newsMessage.setArticleCount(articleList.size());
                                // ����ͼ����Ϣ������ͼ�ļ���  
                                newsMessage.setArticles(articleList);
                                // ��ͼ����Ϣ����ת����xml�ַ���  
                                respMessage = MessageUtil.newsMessageToXml(newsMessage);

                            } else if (eventKey.equals("34")) {
                                db.updateMenuClickCount(34);
                                respContent
                                        = "-���Ƿ�Ҳ���ѧУ���Ž��и���Ĺ�ͨ��\n"
                                        + "-���Ƿ�Ҳ��Ϊͬѧ�ǵ������ṩ���ร����\n"
                                        + "-���Ƿ�Ҳ��ٰ���ʳ�Ļ���Ů���ڵȵȻ��\n"
                                        + "~~�����������ǰɣ�\n"
                                        + "����΢�������ԣ�������Ȩ�桿���� ѧ�� �ֻ���\n"
                                        + "���ǻᾡ��������ϵ����";
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }

    /**
     * emoji����ת��(hex -> utf-16)
     *
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }
}
