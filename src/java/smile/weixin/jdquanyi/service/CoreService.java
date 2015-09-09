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
 * 核心服务类
 *
 * @author liufeng
 * @date 2013-05-20
 */
public class CoreService {

    //Key：21 菜单：学校部门联系电话
    public static String getSchoolDepartmentNumber() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(emoji(0xe148)).append("同济大学嘉定校区重要部门联系方式").append("\n");
        buffer.append("【常用篇】").append("\n");
        buffer.append("-校区报警：").append(69589110).append("\n");
        buffer.append("-网络管理：").append(69584635).append("\n");
        buffer.append("-学校医院：").append(69589213).append("\n").append("办公地点：体育场西侧").append("\n");
        buffer.append("-勤工助学：").append(69584670).append("\n").append("办公地点：复楼114").append("\n");
        buffer.append("-心理咨询：").append(69589851).append("\n").append("办公地点：复楼415").append("\n");
        buffer.append("-就业指导：").append(69584643).append("\n").append("办公地点：复楼113-1").append("\n");
        buffer.append("-教务管理：").append(69589848).append("\n").append("办公地点：复楼316-2,3").append("\n");
        buffer.append("-图书馆：").append(69589619).append("\n").append("办公地点：图书馆107室").append("\n");

        buffer.append("【后勤篇】").append("\n");
        buffer.append("-管理办公室：").append(69589126).append("\n").append("办公地点：同心楼112室").append("\n");
        buffer.append("-物业管理部：").append(69589108).append("\n").append("办公地点：同心楼116室").append("\n");
        buffer.append("-餐饮服务部：").append(69589986).append("\n").append("办公地点：春禾苑食堂办公室").append("\n");
        buffer.append("-教学楼管理：").append(69588907).append("\n").append("办公地点：安楼211室").append("\n");
        buffer.append("-公寓管理部：").append(69589719).append("\n").append("办公地点：学生公寓2号楼").append("\n");
        buffer.append("-电信服务部：").append(69589002).append("\n").append("办公地点：复楼416室").append("\n");
        buffer.append("-物资中心：").append(69588908).append("\n").append("办公地点：济人楼104-1室").append("\n");
        buffer.append("-后勤车队：").append(69589092).append("\n").append("办公地点：济人楼104-3室").append("\n");

        buffer.append("想知道更多，可以悄悄告诉我哦！");

        return buffer.toString();
    }

    //Key:22 菜单：保安叔叔联系电话
    public static String getSchoolDepartmentWorkday() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(emoji(0xe148)).append("同济大学嘉定校区重要部门工作时间和场地开放时间").append("\n");
        buffer.append("(温馨提示：请直接回复下面的关键字，来获取更多我们知道你想知道的更多信息。如回复“校医院”。)").append("\n");
        buffer.append("-校医院").append("\n");
        buffer.append("-高尔夫球场").append("\n");
        buffer.append("-健身房").append("\n");
        buffer.append("-乒乓球场").append("\n");
        buffer.append("-桌球房").append("\n");
        buffer.append("-网球场").append("\n");
        buffer.append("-羽毛球场").append("\n");
        buffer.append("想知道更多，可以悄悄告诉我哦！").append("\n");

        return buffer.toString();
    }

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        Database db = new Database();
        String currentTime;
        //获得当前时间 
        //格式如下： 20150703-17:12:43
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("20yyMMdd-HH:mm:ss");
        currentTime = sdf.format(calendar.getTime());
        
        try {
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");
            // 消息类型  
            String msgType = requestMap.get("MsgType");

            // 默认回复文本消息  
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            //创建图文信息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setFuncFlag(0);
            

            // 文本消息     
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //拿到用户发来的信息
                String content = requestMap.get("Content");
                //去除用户回复信息的前后空格
                content = content.trim();               
//                respContent = "你的权益，我最在意！\n"
//                        + "你反应的任何问题，权小益每晚21：00前一定会回复的 ^_^！"; 
                respContent = "暑假期间，权小益可能回复不及时，还请谅解~";
                
                //respContent = content.trim();
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);

                List<Article> articleList = new ArrayList<Article>();
                
                //获取外卖菜单
                WaimaiMenu wmmenu = new WaimaiMenu();
                Article menu_article = new Article();
                menu_article = wmmenu.getMenuDisplay(content);
                if (! "不存在".equals(menu_article.getTitle())) {
                    articleList.add(menu_article);
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合  f
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }  
                               
                
                //确认领取公益伞 => 改变公益伞名单中对应的人为再借转态, pure=false 
                if ("借用".equals(content)) {
                    //v = \, p =\ (数据库中没有记录) 
                    if (db.getGYS_Id(fromUserName) == 0) {
                        respContent = "是要借公益伞么~\n"
                                + "先点击左下角菜单中的公益伞哦！";
                    } else {
                        //v = 0, p= 1  未通过验证，还未借用(这里v=0，p肯定=1, 因为没通过验证肯定不可能借伞)
                        if (db.GongYiSanIsVerified(fromUserName) == false /*&& db.GongYiSanIsPure(fromUserName) == true*/) {
                            respContent = "你好，你的手机号码还没有通过验证哦！\n"
                                    + "请严格按照这个格式进行回复：gys 手机号码 姓名\n"
                                    + "60秒内将会收到有验证码的短信。\n"
                                    + "到时请将验证码回复给微信平台。";
                        } else {
                            // v = 1 , p = 1 通过验证，还未借用
                            if (/*db.GongYiSanIsVerified(fromUserName) == true && */db.GongYiSanIsPure(fromUserName) == true) {
                                try {
                                    db.updatePure(fromUserName, false);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                String date = new Date().toString();
                                respContent = "【恭喜您于" + date + "成功借用公益伞。】\n\n"
                                        + "请将此回复出示给阿姨作为借伞凭证就行啦 ^-^\n"
                                        + "---------\n"
                                        + "请及时归还到F楼一楼吧台值班处。\n"
                                        + "【温馨提醒，还伞时你的验证码为: " + db.getGYS_Id(fromUserName) + "】\n"
                                        + "你也可以再次点击【公益伞】查看验证码。\n";
//                                        +"（ps:如果没有归还就不能再借哦！）"; 
                            } else {
                                // v = 1 , p = 0 已经借用 (不会出现 p = 0 ,v = 0的情况)
                                respContent = "同学您好，请及时将借用的伞请换至F楼一楼吧台，\n"
                                        + "还伞时你的验证码为：" + db.getGYS_Id(fromUserName) + "\n"
                                        + "（ps:如果没有归还就不能再借哦！）";
                            }
                        }
                    }

//                   respContent =  content + " 看到这里应该是db.getGYS_Id()出错了";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("时刻表".equals(content)) {
                    Article article = new Article();
                    article.setTitle("嘉定校区班车出行表");
                    article.setDescription("这里是最新的时刻表，包括：短驳车，北安跨及其增加班次，大润发，曹杨路高速车。");
                    article.setPicUrl("http://jdquanyi.duapp.com/img/logo.png");
                    article.setUrl("http://jdquanyi.duapp.com/Bus.jsp");
                    articleList.add(article);

                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合  f
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }

                if ("校医院".equals(content)) {
                    respContent = "开放科室：内科、口腔科、输液室、换药室、药房收费、化验、外科\n"
                            + "（注：外科门诊时间：\n"
                            + "上午 9：00 - 11：30 \n"
                            + "下午 12：30 - 17：00\n\n"
                            + "每周三开设皮肤科,每周五开设内科专家门诊,"
                            + "其余时间段为急诊时间\n"
                            + "-----\n"
                            + "报销说明：每月第一、二周周三本部报销；第三周周三嘉定报销\n"
                            + "TIPS:不要忘记带一卡通哦~ ";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("高尔夫球场".equals(content)) {
                    respContent = "地点：图书馆后方；\n"
                            + "价格：对水池打：1盒球（30个）/15元\n"
                            + "     场地打：30元/小时\n"
                            + "     提供球杆；\n"
                            + "开放时间：8：:00-17:00（在不影响体育教学及协会活动的情况下）\n"
                            + "注：周末可能会因接待来宾不开放";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("健身房".equals(content)) {
                    respContent = "地点：济人楼（国会）一楼（进门右转）\n"
                            + "价格: 单次卡 20 元/ 次\n"
                            + "     月卡250 元/10 次\n"
                            + "     季卡400 元/20 次\n"
                            + "     学期卡 600元 不限次数\n"
                            + "     年卡 850 元 不限次数\n"
                            + "开放时间：11:30-22:00";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("乒乓球场".equals(content)) {
                    respContent = "地点: 济人楼（国会）进门右转（健身房内）；仰望星空（中央天桥）；德楼（D楼）一楼、二楼\n"
                            + "开放时间：\n"
                            + "济人楼  周四17：30?21：30   周二、三、五15：30?21：30  \n"
                            + "双休日 13： 00?21：30；\n"
                            + "仰望星空 见通知，一般为8:00-20:00\n"
                            + "德楼 8：00-17：00（工作日）不影响体育教学前提下\n"
                            + "周二，周四中午为教师活动时间\n"
                            + "场地数量:济人楼 8-10 桌左右；仰望星空 4桌；德楼 7桌，二楼2桌\n";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("桌球房".equals(content)) {
                    respContent = "地点: 同德楼（汽展中心）三楼\n"
                            + "价格：花式桌球 15元/小时，斯诺克18元/小时，有会员卡可优惠\n"
                            + "场地数量：花式桌球3桌，斯诺克1桌\n"
                            + "开放时间：17:00-22:00 （工作日）13:00-22:00 （周末）\n"
                            + "Tips:可以打电话预定哦，预定电话13764647792";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("网球场".equals(content)) {
                    respContent = "地点: 足球场旁，校医院北方\n"
                            + "开放时间：8：00-20：00 在不影响体育教学的前提下\n"
                            + "场地数量:4片"
                            + "Tips:买卡消费，需提前订场；价格与本部同";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                if ("羽毛球场".equals(content)) {
                    respContent = "地点: 仰望星空（中央天桥）；德楼（D楼）一楼；同德楼（汽展中心）一楼\n"
                            + "------\n"
                            + "开放时间: \n"
                            + "仰望星空 见通知，一般为8:00-20:00\n"
                            + "周二，周四中午为教师活动时间\n"
                            + "同德楼 见通知，一般为8:00-20:00\n(需要提前在健身馆预约)"
                            + "------\n"
                            + "场地数量:仰望星空 4片；德楼 3片；同德楼 4片";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }

                //fxfx 1252855 胡星 软件学院
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
                            respContent = "恭喜你，辅修车票登记成功！" + "\n" + content;
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        } else {
                            respContent = "你已经登记过了哦！";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        }
                    } else {
                        respContent = "你要登记辅修吧？格式不对哦！~" + "\n" + content;
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);

                    }

                }

                //gys 13122363681 胡星
                if (content.trim().substring(0, 3).equals("gys")) {

                    String[] keywords = content.trim().split("\\s+");
                    if (keywords.length == 3) {
                        String telephone = keywords[1];
                        String name = keywords[2];

                        int tag = db.GongYiSanExists(fromUserName);
                        //没有登记过公益伞
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
                            respContent = "请输入您手机短信收到的验证码，如：yzm 12";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                            //已登记，手机号码验证未通过
                        } else if (tag == 1) {
                            db.updateTelephone(fromUserName, telephone);
                            int id = db.getGYS_Id(fromUserName);
                            SendMsg_webchinese sendMsg = new SendMsg_webchinese();
                            sendMsg.send(telephone, id);
                            respContent = "请输入您手机短信收到的验证码，如：yzm 12";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        } //已登记，手机号码验证已通过
                        else {
                            respContent = "你已经登记过了哦！";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                        }
                        //gys开头 但是格式不对
                    } else {
                        respContent = "你要登记注册公益伞信息吧？格式不对哦！~\n"
                                + "请严格按照：gys 手机号码 姓名 \n"
                                + "的格式进行回复哦！";
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
                        //没有登记过公益伞 
                        if (tag == 0) {
                            respContent = "第一次使用公益伞？赶快来验证您的手机号码吧！\n"
                                    + "请严格按照这个格式进行回复：gys 手机号码 姓名\n"
                                    + "60秒内将会收到有验证码的短信。\n"
                                    + "到时请将验证码回复给微信平台。";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                            //已登记，手机号码验证未通过
                        } else if (tag == 1) {
                            //一致
                            if (i_id == db.getGYS_Id(fromUserName)) {
                                db.updateVerification(fromUserName, true);
                                respContent = "恭喜你验证成功！可以点击菜单中的【公益伞】领取公益伞!";
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else {
                                respContent = "对不起，验证码不一致！\n"
                                        + "请再次严格按照这个格式进行回复：gys 手机号码 姓名\n"
                                        + "60秒内将会收到有验证码的短信。\n"
                                        + "到时请将验证码回复给微信平台。";;
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            }
                        } //已登记，手机号码验证已通过
                        else {
                            respContent = "你的手机号码之前已经通过验证了哦！";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                        }

                    } else {
                        respContent = "验证码格式错误";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    }
                }
                
                //ts 18号楼外的小青蛙好吵吖!能不能解决下~
                if (content.trim().substring(0, 2).equals("ts")) {                    
                    //存问题
                    MQ_MyQuestions mq = new MQ_MyQuestions();
                    mq.setFromUserName(fromUserName);
                    mq.setPutTime(currentTime);
                    mq.setDescription(content.substring(3));
                    mq.setReply("权小益走访中...");
                    mq.setTag(false);
                    db.AddQuestion(mq);
                    respContent = "问题已经受理，静待处理。\n你可以点开【投诉追踪】，查看问题进度。";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
                

            } // 图片消息  
            else {
                if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                    respContent = "我喜欢你发的图片！";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                } // 地理位置消息  
                else {
                    if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                        respContent = "同学你发的是地理位置哦！";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } // 链接消息  
                    else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                        respContent = "同学你发送的是链接消息哦";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } // 音频消息  
                    else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                        respContent = "同学你发送的是音频消息哦！";
                        textMessage.setContent(respContent);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    } // 事件推送  
                    else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                        // 事件类型  
                        String eventType = requestMap.get("Event");
                        // 订阅  
                        if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                            respContent
                                    = "感谢您关注同济大学学生会嘉定校区权益保障与生活福利部！\n"
                                    + "我们的微信一直在不断完善，同时您的支持给了我们更多的动力！"
                                    + "并希望您能对我们的微信平台和日常工作提出宝贵意见与建议，一经采纳，我们会为"
                                    + "您提供特别的礼物！赶快玩起下方菜单的精品功能吧！\n"
                                    + "---------\n"
                                    + "";
                            textMessage.setContent(respContent);
                            respMessage = MessageUtil.textMessageToXml(textMessage);

                        } // 取消订阅  
                        else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                            // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                        } // 自定义菜单点击事件  
                        else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                            // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                            String eventKey = requestMap.get("EventKey");
                            if (eventKey.equals("11")) {
                                db.updateMenuClickCount(11);
                                List<Article> articleList1 = new ArrayList<Article>();
                                List<String> nextBusTimes = new BusTime().getNextBus();

                                Article articleSKB = new Article();
                                articleSKB.setTitle("嘉定校区2015年暑假班车出行表");
                                articleSKB.setDescription("这里是最新的时刻表，包括：短驳车，北安跨及其增加班次，大润发，曹杨路高速车。");
                                articleSKB.setPicUrl("http://jdquanyi.duapp.com/img/shujia.png");
//                                articleSKB.setUrl("http://jdquanyi.duapp.com/Bus.jsp");
                                articleSKB.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MTY1NjYyOA==&mid=208194187&idx=1&sn=1eccd3d28841a370003aaa62e0422297&scene=1&key=c76941211a49ab5840ddd109192a835cf49fc0ba4281ae8ede11d78132d625edb4cc7fa6a7d5aa5dfb55089c1fe52fb8&ascene=0&uin=MTA4MTU5NjEwMQ%3D%3D&devicetype=iMac+MacBookAir6%2C2+OSX+OSX+10.10.4+build(14E46)&version=11020012&pass_ticket=Zr9uPDo0TNM81WLntyzIICs5uRRJkUwipDFxpwWMIzcaJ6LS1rNjbwh5i9WIaoAD");

                                Article articleT = new Article();
                                articleT.setTitle(emoji(0x1F551) + "离你最近的一班车（暑假版已更新~）");
                                articleT.setDescription("");

                                Article article = new Article();
                                article.setTitle(emoji(0x1F68C) + "【短驳车】");
                                article.setDescription("");
//                                article.setUrl("");
//                                article.setPicUrl("");

                                Article article0 = new Article();
                                article0.setTitle(emoji(0x1F51C) + "汽车城站      " + nextBusTimes.get(0));
                                article0.setDescription("");
//                                article0.setUrl("");
//                                article0.setPicUrl("");

                                Article article1 = new Article();
                                article1.setTitle(emoji(0x1F51C) + "嘉定校区      " + nextBusTimes.get(1));
                                article1.setDescription("");
//                                article1.setPicUrl("");
//                                article1.setUrl("");

                                Article articleDA = new Article();
                                articleDA.setTitle(emoji(0x1F68C) + "【大润发】");
                                articleDA.setDescription("");
//                                articleDA.setUrl("");
//                                articleDA.setPicUrl("");

                                Article article2 = new Article();
                                article2.setTitle(emoji(0x1F51C) + "安亭大润发    " + nextBusTimes.get(2));
                                article2.setDescription("");
//                                article2.setPicUrl("");
//                                article2.setUrl("");

                                Article article3 = new Article();
                                article3.setTitle(emoji(0x1F51C) + "学校正门口    " + nextBusTimes.get(3));
                                article3.setDescription("");
//                                article3.setPicUrl("");
//                                article3.setUrl("");

                                Article articleGS = new Article();
                                articleGS.setTitle(emoji(0x1F68C) + "【高速车】(7号楼前上车)");
                                articleGS.setDescription("");
                                articleGS.setUrl("");
                                articleGS.setPicUrl("");

                                Article article4 = new Article();
                                article4.setTitle(emoji(0x1F51C) + "曹杨路地铁站    " + nextBusTimes.get(4));
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

                                // 设置图文消息个数  
                                newsMessage.setArticleCount(articleList1.size());
                                // 设置图文消息包含的图文集合  f
                                newsMessage.setArticles(articleList1);
                                // 将图文消息对象转换成xml字符串  
                                respMessage = MessageUtil.newsMessageToXml(newsMessage);

                            } else if (eventKey.equals("12")) {//公益伞
                                db.updateMenuClickCount(12);
                                int tag = db.GongYiSanExists(fromUserName);
                                //v=?，没有登记
                                if (tag == 0) {
                                    respContent = "公益伞功能现已全面更新!\n"
                                            + "想要开通公益伞功能？赶快来验证您的手机号码吧！\n"
                                            + "请严格按照这个格式进行回复：gys 手机号码 姓名\n"
                                            + "60秒内将会收到有验证码的短信。\n"
                                            + "到时请将验证码回复给微信平台。";
//                                            + "------\n"
//                                            + "第一次用公益伞，请向值班阿姨出示验证短信作为借用凭证就可以啦 ^-^\n"
//                                            + "以后就直接点击菜单中的【公益伞】就可以啦！";
                                } //v=0，已登记，手机号码没有通过验证
                                else if (tag == 1) {
                                    respContent = "你手机号码还没有通过验证哦！"
                                            + "请尽快并且严格按照这个格式进行回复：gys 手机号码 姓名\n"
                                            + "60秒内将会收到有验证码的短信。\n"
                                            + "到时请将验证码回复给微信平台。";
                                } //v=1,p=1手机号码通过验证了，没有借伞
                                else if (db.GongYiSanIsPure(fromUserName)) {
                                    respContent = "同学您好，欢迎您在B楼、F楼、济事楼借用公益伞。请问您是否确认要"
                                            + "借取公益伞？确认请回复：借用。";
                                } else {
                                    respContent = "同学您好，记得及时归还至F楼一楼吧台，\n" //v=1, p=0已经借出了
                                            + "（ps:还伞时，记得填写你唯一的验证码: yzm" + db.getGYS_Id(fromUserName);
                                }

                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);

                            } else if (eventKey.equals("13")) {
                                db.updateMenuClickCount(13);
                                if (db.FuXiuExists(fromUserName)) {
                                    respContent = "恭喜你拿到第" + db.getWC_count() + "周的辅修车票。 ^-^"
                                            + "\n 这是你辅修车票的电子凭证。\n";
                                           
                                    db.updateFX_count(fromUserName);

                                            //"由于教务处本周还在统计辅修人数联系安排车辆，所以本周（第二周）没有辅修车- -、";
                                    //+ "本周(15周)辅修班车因考研暂停一次。下周辅修班车车票继续开放。对此为您造成的麻烦我们深感抱歉.\n"
                                    //+ "也请同学们互相转告，谢谢^-^";
                                    textMessage.setContent(respContent);
                                    respMessage = MessageUtil.textMessageToXml(textMessage);
                                } else {
//                                    FX_FuXiu fx_user = new FX_FuXiu();
//                                    fx_user.setFx_fromUserName(fromUserName);
//                                    fx_user.setFx_description("New Add");
//                                    db.AddFuxiu(fx_user);
                                    respContent = "对不起，同学你不在辅修车票名单中 ~~.";
                                    textMessage.setContent(respContent);
                                    respMessage = MessageUtil.textMessageToXml(textMessage);
                                }
                            } else if (eventKey.equals("14")) {
                                db.updateMenuClickCount(14);
                                respContent = "请尽快到您丢失物品周围的失物存放点找寻！";
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
                                respContent = "直接点击店名，可查看菜单哦！\n";
                                List<WM_WaiMai> wm_list = db.getWaiMai();                                
                                
                                respContent += "*****【嘉实广场】*****\n";  
                                for (WM_WaiMai wm : wm_list) {                                    
//                                    respContent += "--------\n" + emoji(0x1F35A) + " "+ wm.getWM_name() + "\n"
//                                           + emoji(0xE009)+ wm.getWM_telephone() + "\n";  
                                                                              
                                    if ("嘉实广场".equals(wm.getWM_address())) {
                                        respContent += "----\n" + "<a href=\"" + wm.getWM_menuLink()+"\">"+ 
                                                wm.getWM_name() +"</a> "+ "\n" + "Tel: "+wm.getWM_telephone() + "\n";
                                    }                                    
                                    
                                }
                                respContent += "\n*****【新天地】*****\n";
                                for (WM_WaiMai wm : wm_list) {                                    
//                                    respContent += "--------\n" + emoji(0x1F35A) + " "+ wm.getWM_name() + "\n"
//                                           + emoji(0xE009)+ wm.getWM_telephone() + "\n";
                                                                                                                    
                                    if ("新天地".equals(wm.getWM_address())) {
                                        respContent += "----\n" + "<a href=\"" + wm.getWM_menuLink()+"\">"+ 
                                                wm.getWM_name() +"</a> "+ "\n" + "Tel:"+wm.getWM_telephone() + "\n";
                                    }                                    
                                    
                                }
                                
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                                                               
                            } else if (eventKey.equals("31")) {//我要投诉
                                db.updateMenuClickCount(31);                                
                                respContent = "同学，你好哇。\n"
                                        + "如果想要投诉，请按照下面例子格式：\n"                                        
                                        + "ts 18号楼外的小青蛙好吵吖！能不能解决下?"
                                        + "\n---------------"                                        
                                        + "\n点击【投诉追踪】即可及时查看自己问题的走访情况。";
                                        
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else if (eventKey.equals("32")) {
                                db.updateMenuClickCount(32);
                                List<MQ_MyQuestions> mq_list = db.GetMyQuestions(fromUserName);
                                if (mq_list.isEmpty()) {
                                    respContent = "你过得很舒服，没有投诉问题哦。";
                                }else { 
                                
                                respContent = "你投诉的问题有：\n";
                                int i = 1;
                                for (MQ_MyQuestions mq : mq_list) {
                                    respContent += i + ". " + mq.getDescription() + "\n回复：" + mq.getReply() + "\n";                                 
                                    i ++;
                                }
                                }                                                                       
                                textMessage.setContent(respContent);
                                respMessage = MessageUtil.textMessageToXml(textMessage);
                            } else if (eventKey.equals("33")) {
                                db.updateMenuClickCount(33);
                                List<Article> articleList = new ArrayList<Article>();
                                Article article = new Article();
                                article.setTitle("嘉定大修会议笔记");
                                article.setDescription("");
                                article.setPicUrl("http://jdquanyi.duapp.com/img/quanyi.jpg");
                                article.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MTY1NjYyOA==&mid=206502641&idx=1&sn=f3dacb61f565f12fcf14dfb72d10fe7b#rd");
                                articleList.add(article);
                                // 设置图文消息个数  
                                newsMessage.setArticleCount(articleList.size());
                                // 设置图文消息包含的图文集合  
                                newsMessage.setArticles(articleList);
                                // 将图文消息对象转换成xml字符串  
                                respMessage = MessageUtil.newsMessageToXml(newsMessage);

                            } else if (eventKey.equals("34")) {
                                db.updateMenuClickCount(34);
                                respContent
                                        = "-你是否也想和学校部门进行更多的沟通？\n"
                                        + "-你是否也想为同学们的生活提供更多福利？\n"
                                        + "-你是否也想举办饮食文化节女生节等等活动？\n"
                                        + "~~快来加入我们吧！\n"
                                        + "请在微信下留言：【报名权益】姓名 学号 手机号\n"
                                        + "我们会尽快与你联系。！";
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
     * emoji表情转换(hex -> utf-16)
     *
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }
}
