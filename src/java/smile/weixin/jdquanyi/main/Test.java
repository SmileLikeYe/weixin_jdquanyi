/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import smile.weixin.jdquanyi.tools.BusTime;
import java.util.List;
import smile.weixin.jdquanyi.database.Database;
import smile.weixin.jdquanyi.database.MQ_MyQuestions;
import smile.weixin.jdquanyi.database.WM_WaiMai;
import smile.weixin.jdquanyi.util.MessageUtil;

/**
 *
 * @author smile
 */
public class Test {
    
     public static void main(String[] args) { 
//         List<String> nextBusTimes = new BusTime().getNextBus();
//         
//         for (int i = 0 ; i < nextBusTimes.size(); i++ ) {
//             System.out.println(nextBusTimes.get(i));
//                     
//         }
         
//         String date = new Date().toString();
//        String week = date.toString().substring(0, 3);
//        System.out.println(week);
         
         //获取时间
//        System.out.println("java.util.Date:  "+ new java.util.Date());
//        String date = new Date().toString();
//        String month = date.substring(4,7);
//        System.out.println(month);
//        String day =  date.substring(8,10);
//        System.out.println(day);
//        String year = date.substring(24,28);
//        System.out.println(year);
         
         Database db = new Database();
         
         String currentTime;
        //获得当前时间 
        //格式如下： 20150703-17:12:43
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("20yyMMdd-HH:mm:ss");
        currentTime = sdf.format(calendar.getTime());
        String content = "ts too noise";
        
        //ts 18号楼外的小青蛙好吵吖!能不能解决下~
                if (content.trim().substring(0, 2).equals("ts")) {                    
                    //存问题
                    MQ_MyQuestions mq = new MQ_MyQuestions();
                    mq.setFromUserName("hx");              
                    
                    
                    mq.setPutTime(currentTime);
                    mq.setDescription(content.substring(3));
                    mq.setReply("ing...");
                    mq.setTag(false);
                    db.AddQuestion(mq);
                    System.out.println( "问题已经受理，静待处理。\n你可以点开【投诉追踪】，查看问题进度。");                    
                }
                
                String s =null;
                List<MQ_MyQuestions> mq_list = db.GetMyQuestions("hx");
                if (mq_list.isEmpty()) {
                                    s = "你没有投诉问题哦。";
                                }else { 
                                
                                s = "Yout questions：\n";
                                int i = 1;
                                for (MQ_MyQuestions mq : mq_list) {
                                    s += i + ". " + mq.getDescription() + "\n" + mq.getReply() + "\n";                                 
                                    i ++;
                                }
                } 
                                System.out.println(s);
     }
}
     
