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
         
         //��ȡʱ��
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
        //��õ�ǰʱ�� 
        //��ʽ���£� 20150703-17:12:43
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("20yyMMdd-HH:mm:ss");
        currentTime = sdf.format(calendar.getTime());
        String content = "ts too noise";
        
        //ts 18��¥���С���ܺó�߹!�ܲ��ܽ����~
                if (content.trim().substring(0, 2).equals("ts")) {                    
                    //������
                    MQ_MyQuestions mq = new MQ_MyQuestions();
                    mq.setFromUserName("hx");              
                    
                    
                    mq.setPutTime(currentTime);
                    mq.setDescription(content.substring(3));
                    mq.setReply("ing...");
                    mq.setTag(false);
                    db.AddQuestion(mq);
                    System.out.println( "�����Ѿ�������������\n����Ե㿪��Ͷ��׷�١����鿴������ȡ�");                    
                }
                
                String s =null;
                List<MQ_MyQuestions> mq_list = db.GetMyQuestions("hx");
                if (mq_list.isEmpty()) {
                                    s = "��û��Ͷ������Ŷ��";
                                }else { 
                                
                                s = "Yout questions��\n";
                                int i = 1;
                                for (MQ_MyQuestions mq : mq_list) {
                                    s += i + ". " + mq.getDescription() + "\n" + mq.getReply() + "\n";                                 
                                    i ++;
                                }
                } 
                                System.out.println(s);
     }
}
     
