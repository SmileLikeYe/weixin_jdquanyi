/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smile
 */
public class BusTime {
    
    public  List duanbobus_weekend_go = new ArrayList<>();
    public  List duanbobus_weekend_back = new ArrayList<>();
    public  List duanbobus_weekday_go = new ArrayList<>();
    public  List duanbobus_weekday_back = new ArrayList<>();
    
    public static List darunfabus_go = new ArrayList<>();
    public static List darunfabus_back = new ArrayList<>();
    
    
    public static List gaosubus_go = new ArrayList<>();
    public static List gaosubus_back = new ArrayList<>();
      
    //返回一个最近发车时间String数组，分别对应
    /*
    短驳车
    String0：（嘉定?>地铁站）的时间
    String1：（地铁站?>嘉定）的时间
    大润发
    String2：（学校大门?>大润发）的时间
    String3：（大润发?>学校大门）的时间
    曹杨路高速车
    String4：（7，8号楼门前?>曹杨路地铁站）的时间       
    */       
    public List<String> getNextBus() {
        /**
         * @param args the command line arguments
         */                        
        List<String> respContent = new ArrayList<String>();
        //获取系统时间
        String date = new Date().toString();
        String week = date.toString().substring(0, 3);
        String hour = date.toString().substring(11, 13);
        String minute = date.toString().substring(14, 16);
        String month = date.substring(4,7);        
        String str_day =  date.substring(8,10);
        String year = date.substring(24,28);
        int day = Integer.parseInt(str_day);
        
        //这里是专为2015年暑假短驳车调整而做的判断
        //有效期：2015.12.31
        if (year.equals("2015")){
            System.out.println("year" + year);            
            if (month.equals("Jul")){
                System.out.println("month" + month);
                if (day<20){
                    //Type1??正常
                    System.out.println("day" + day);
                    this.getType1();
                }else{
                    //Type2??7月特别运行篇                    
                    this.getType2();
                }
            }else if (month.equals("Aug")){
                //Type3??8月特别运行篇
                this.getType3();
                
            }else if (month.equals("Sep")){
                if (day<7){
                    //Type2??7月特别运行篇
                    this.getType2();
                    
                }else if (day==11 || day==12){
                    //Type4??开学特别运行篇
                    this.getType4();
                    
                }else{
                    //Type1??正常 
                    this.getType1();
                }                                 
            }else {
                //Type1??正常 其他月份
            }
        }
            
            
            

        String str_nowTime = hour + minute;
        //这里会把如09：53换成952,0没了位数会变
        int i_nowTime = Integer.parseInt(str_nowTime);
        //如果是在周末
        if ("Sun".equals(week) || "Sat".equals(week)) {
            //遍历前往地铁站的短驳车时间表
            for (int i = 0; i < duanbobus_weekend_go.size(); i++) {
                //找下一趟前往地铁站的短驳车
                if (i_nowTime <= (int) duanbobus_weekend_go.get(i)) {
                    //时间格式为xxx(0：00 - 9：59)
                    if (duanbobus_weekend_go.get(i).toString().length() == 3) {
                        respContent.add(duanbobus_weekend_go.get(i).toString().substring(0, 1) 
                                + ":" + duanbobus_weekend_go.get(i).toString().substring(1, 3));
//                                += "-->上海汽车城站:" + duanbobus_weekend_go.get(i).toString().substring(0, 1) + ":"
//                                + duanbobus_weekend_go.get(i).toString().substring(1, 3) + //                                += "-->上海汽车城站:" + duanbobus_weekend_go.get(i).toString().substring(0, 1) + ":"'\n';
                    } else {//时间格式为xxxx
                        respContent.add(duanbobus_weekend_go.get(i).toString().substring(0, 2) 
                                + ":" + duanbobus_weekend_go.get(i).toString().substring(2, 4));
                        
//                        respContent += "-->上海汽车城站:" + duanbobus_weekend_go.get(i).toString().substring(0, 2) + ":"
//                                + duanbobus_weekend_go.get(i).toString().substring(2, 4) + '\n';
                    }
                    break;//出现第一个就立马跳出
                }
            }
            
            if(respContent.size() != 1)  respContent.add("今日已停运");
            
            //遍历返回嘉定的短驳车时间表
            for (int j = 0; j < duanbobus_weekend_back.size(); j++) {
                //找下一趟返回嘉定的短驳车
                if (i_nowTime <= (int) duanbobus_weekend_back.get(j)) {
                    //时间格式为xxx
                    if (duanbobus_weekend_back.get(j).toString().length() == 3) {
                        respContent.add(duanbobus_weekend_back.get(j).toString().substring(0, 1) 
                                + ":" + duanbobus_weekend_back.get(j).toString().substring(1, 3)); 
                        
//                        respContent += "-->同济嘉定校区:" + duanbobus_weekend_back.get(j).toString().substring(0, 1) + ":"
//                                + duanbobus_weekend_back.get(j).toString().substring(1, 3) + '\n';
                    } else {//时间格式为xxxx
                        respContent.add(duanbobus_weekend_back.get(j).toString().substring(0, 2) 
                                + ":" + duanbobus_weekend_back.get(j).toString().substring(2, 4)); 
//                        respContent += "-->同济嘉定校区:" + duanbobus_weekend_back.get(j).toString().substring(0, 2) + ":"
//                                + duanbobus_weekend_back.get(j).toString().substring(2, 4) + '\n';
                    }
                    break;//出现第一个就立马跳出
                }
            }
            
            if(respContent.size() != 2)  respContent.add("今日已停运");
            
        } else {//如果是在工作日
            //遍历前往地铁站的短驳车时间表
            for (int i = 0; i < duanbobus_weekday_go.size(); i++) {
                //找下一趟前往地铁站的短驳车
                if (i_nowTime <= (int) duanbobus_weekday_go.get(i)) {
                    //时间格式为xxx
                    if (duanbobus_weekday_go.get(i).toString().length() == 3) {
                        
                        respContent.add(duanbobus_weekday_go.get(i).toString().substring(0, 1) 
                                + ":" + duanbobus_weekday_go.get(i).toString().substring(1, 3));
                        
//                        respContent += "-->上海汽车城站:" + duanbobus_weekday_go.get(i).toString().substring(0, 1) + ":"
//                                + duanbobus_weekday_go.get(i).toString().substring(1, 3) + '\n';
                    } else {//时间格式为xxxx
                        respContent.add(duanbobus_weekday_go.get(i).toString().substring(0, 2) 
                                + ":" + duanbobus_weekday_go.get(i).toString().substring(2, 4));
                        
//                        respContent += "-->上海汽车城站:" + duanbobus_weekday_go.get(i).toString().substring(0, 2) + ":"
//                                + duanbobus_weekday_go.get(i).toString().substring(2, 4) + '\n';
                    }
                    break;//出现第一个就立马跳出
                }
            }
//            System.out.println(respContent.size());
            if(respContent.size() != 1)  respContent.add("今日已停运");
            
            //遍历返回嘉定的短驳车时间表
            for (int j = 0; j < duanbobus_weekday_back.size(); j++) {
                //找下一趟返回嘉定的短驳车
                if (i_nowTime <= (int) duanbobus_weekday_back.get(j)) {
                    //时间格式为xxx
                    if (duanbobus_weekday_back.get(j).toString().length() == 3) {
                        respContent.add(duanbobus_weekday_back.get(j).toString().substring(0, 1) 
                                + ":" + duanbobus_weekday_back.get(j).toString().substring(1, 3));
                        
//                        respContent += "-->同济嘉定校区" + duanbobus_weekday_back.get(j).toString().substring(0, 1) + ":"
//                                + duanbobus_weekday_back.get(j).toString().substring(1, 3) + '\n';
                    } else {//时间格式为xxxx
                        respContent.add(duanbobus_weekday_back.get(j).toString().substring(0, 2) 
                                + ":" + duanbobus_weekday_back.get(j).toString().substring(2, 4));
                        
//                        respContent += "-->同济嘉定校区:" + duanbobus_weekday_back.get(j).toString().substring(0, 2) + ":"
//                                + duanbobus_weekday_back.get(j).toString().substring(2, 4) + '\n';
                    }
                    break;//出现第一个就立马跳出
                }
            }
//            System.out.println(respContent.size());
            if(respContent.size() != 2)  respContent.add("今日已停运");

        }
        
      
/*****************************darunfabus******************************************/ 
    //大润发班车：学校大门 --> 大润发
        darunfabus_go.add(751);
        darunfabus_go.add(906);
        darunfabus_go.add(1036);
        darunfabus_go.add(1221);
        darunfabus_go.add(1436);
        darunfabus_go.add(1636);
        darunfabus_go.add(1836);
                
    //大润发班车：大润发 --> 学校大门
        darunfabus_back.add(715);
        darunfabus_back.add(830);
        darunfabus_back.add(1000);
        darunfabus_back.add(1145);
        darunfabus_back.add(1400);
        darunfabus_back.add(1600);
        darunfabus_back.add(1800);
        darunfabus_back.add(2020);
        
        
    //遍历前往大润发的大润发发车时间表
            for (int i = 0; i < darunfabus_go.size(); i++) {
                //找下一趟前往大润发的车
                if (i_nowTime <= (int) darunfabus_go.get(i)) {
                    //时间格式为xxx(0：00 - 9：59)
                    if (darunfabus_go.get(i).toString().length() == 3) {
                        respContent.add(darunfabus_go.get(i).toString().substring(0, 1) 
                                + ":" + darunfabus_go.get(i).toString().substring(1, 3));

                    } else {//时间格式为xxxx
                        respContent.add(darunfabus_go.get(i).toString().substring(0, 2) 
                                + ":" + darunfabus_go.get(i).toString().substring(2, 4));                       
                    }
                    break;//出现第一个就立马跳出
                }
            }
            
//            System.out.println(respContent.size());
            if(respContent.size() != 3)  respContent.add("今日已停运");
        
     //遍历返回嘉定学校大门的大润发车时间表
            for (int j = 0; j < darunfabus_back.size(); j++) {
                //找下一趟返回嘉定学校大门的车
                if (i_nowTime <= (int) darunfabus_back.get(j)) {
                    //时间格式为xxx
                    if (darunfabus_back.get(j).toString().length() == 3) {
                        respContent.add(darunfabus_back.get(j).toString().substring(0, 1) 
                                + ":" + darunfabus_back.get(j).toString().substring(1, 3));                        
                    } else {//时间格式为xxxx
                        respContent.add(darunfabus_back.get(j).toString().substring(0, 2) 
                                + ":" + darunfabus_back.get(j).toString().substring(2, 4)); 
                    }
                    break;//出现第一个就立马跳出
                }
            }  
            
            if(respContent.size() != 4)  respContent.add("今日已停运");
/*****************************gaosubus******************************************/         
    //曹杨路高速车：7、8号楼门前 --> 曹杨路兰溪路公交站 (周末不发车)
        gaosubus_go.add(720);
        gaosubus_go.add(1000);
        gaosubus_go.add(1230);
        gaosubus_go.add(1530);
        gaosubus_go.add(1730);
        
        
         if ("Sun".equals(week) || "Sat".equals(week)) {
             respContent.add("周末不开！");
         } else {
    //遍历前往曹杨路兰溪路公交站发车时间表
            for (int i = 0; i < gaosubus_go.size(); i++) {
                //找下一趟前往曹杨路兰溪路公交站的车
                if (i_nowTime <= (int) gaosubus_go.get(i)) {
                    //时间格式为xxx(0：00 - 9：59)
                    if (gaosubus_go.get(i).toString().length() == 3) {
                        respContent.add(gaosubus_go.get(i).toString().substring(0, 1) 
                                + ":" + gaosubus_go.get(i).toString().substring(1, 3));

                    } else {//时间格式为xxxx
                        respContent.add(gaosubus_go.get(i).toString().substring(0, 2) 
                                + ":" + gaosubus_go.get(i).toString().substring(2, 4));                       
                    }
                    break;//出现第一个就立马跳出
                }
            }
            
         }
            
            if(respContent.size() != 5)  respContent.add("今日已停运");
            
        return respContent;
    }
    
    //Type1:短驳车正常车次: 7.19及之前 && 9.7以后
     public void getType1() {
        /*****************************duanbobus******************************************/          
    //周末去地铁站的短驳车发车时间
        duanbobus_weekend_go.add(645);
        duanbobus_weekend_go.add(700);
        duanbobus_weekend_go.add(715);
        duanbobus_weekend_go.add(730);
        duanbobus_weekend_go.add(745);
        duanbobus_weekend_go.add(800);
        duanbobus_weekend_go.add(1000);
        duanbobus_weekend_go.add(1200);
        duanbobus_weekend_go.add(1400);
        duanbobus_weekend_go.add(1600);
        duanbobus_weekend_go.add(1800);
        duanbobus_weekend_go.add(2030);
        duanbobus_weekend_go.add(2100);
        duanbobus_weekend_go.add(2200);

    //周末回嘉定的短驳车发车时间
        duanbobus_weekend_back.add(700);
        duanbobus_weekend_back.add(715);
        duanbobus_weekend_back.add(730);
        duanbobus_weekend_back.add(745);
        duanbobus_weekend_back.add(800);
        duanbobus_weekend_back.add(815);
        duanbobus_weekend_back.add(1015);
        duanbobus_weekend_back.add(1215);
        duanbobus_weekend_back.add(1415);
        duanbobus_weekend_back.add(1615);
        duanbobus_weekend_back.add(1815);
        duanbobus_weekend_back.add(2045);
        duanbobus_weekend_back.add(2115);
        duanbobus_weekend_back.add(2215);

    //工作日去地铁站的短驳车发车时间
        duanbobus_weekday_go.add(615);
        duanbobus_weekday_go.add(630);
        duanbobus_weekday_go.add(645);
        duanbobus_weekday_go.add(700);
        duanbobus_weekday_go.add(715);
        duanbobus_weekday_go.add(730);
        duanbobus_weekday_go.add(745);
        duanbobus_weekday_go.add(800);
        duanbobus_weekday_go.add(900);
        duanbobus_weekday_go.add(1000);
        duanbobus_weekday_go.add(1200);
        duanbobus_weekday_go.add(1300);
        duanbobus_weekday_go.add(1500);
        duanbobus_weekday_go.add(1600);
        duanbobus_weekday_go.add(1700);
        duanbobus_weekday_go.add(1800);
        duanbobus_weekday_go.add(1900);
        duanbobus_weekday_go.add(2000);
        duanbobus_weekday_go.add(2030);
        duanbobus_weekday_go.add(2100);
        duanbobus_weekday_go.add(2200);

    //工作日回嘉定的短驳车发车时间
        duanbobus_weekday_back.add(600);
        duanbobus_weekday_back.add(645);
        duanbobus_weekday_back.add(700);
        duanbobus_weekday_back.add(715);
        duanbobus_weekday_back.add(730);
        duanbobus_weekday_back.add(745);
        duanbobus_weekday_back.add(800);
        duanbobus_weekday_back.add(815);
        duanbobus_weekday_back.add(915);
        duanbobus_weekday_back.add(1015);
        duanbobus_weekday_back.add(1215);
        duanbobus_weekday_back.add(1315);
        duanbobus_weekday_back.add(1515);
        duanbobus_weekday_back.add(1615);
        duanbobus_weekday_back.add(1715);
        duanbobus_weekday_back.add(1815);
        duanbobus_weekday_back.add(1915);
        duanbobus_weekday_back.add(2015);
        duanbobus_weekday_back.add(2045);
        duanbobus_weekday_back.add(2115);
        duanbobus_weekday_back.add(2215);
    }
     
    //Type2: 2015  7.20-7.31 && 9.1-9.6     
    public void getType2() {
        /*****************************duanbobus******************************************/          
    //周末去地铁站的短驳车发车时间
        duanbobus_weekend_go.add(645);
        duanbobus_weekend_go.add(700);
        duanbobus_weekend_go.add(715);
        duanbobus_weekend_go.add(730);
        duanbobus_weekend_go.add(745);
        duanbobus_weekend_go.add(800);
        duanbobus_weekend_go.add(1000);
        duanbobus_weekend_go.add(1200);        
        duanbobus_weekend_go.add(1600);
        duanbobus_weekend_go.add(1800);
        duanbobus_weekend_go.add(1930);
        duanbobus_weekend_go.add(2030);
        duanbobus_weekend_go.add(2100);
        duanbobus_weekend_go.add(2200);

    //周末回嘉定的短驳车发车时间
        duanbobus_weekend_back.add(700);
        duanbobus_weekend_back.add(715);
        duanbobus_weekend_back.add(730);
        duanbobus_weekend_back.add(745);
        duanbobus_weekend_back.add(800);
        duanbobus_weekend_back.add(815);
        duanbobus_weekend_back.add(1015);
        duanbobus_weekend_back.add(1215);       
        duanbobus_weekend_back.add(1615);
        duanbobus_weekend_back.add(1815);
        duanbobus_weekend_back.add(1945);
        duanbobus_weekend_back.add(2045);
        duanbobus_weekend_back.add(2115);
        duanbobus_weekend_back.add(2215);

    //工作日去地铁站的短驳车发车时间        
        duanbobus_weekday_go.add(645);
        duanbobus_weekday_go.add(700);
        duanbobus_weekday_go.add(715);
        duanbobus_weekday_go.add(730);
        duanbobus_weekday_go.add(745);
        duanbobus_weekday_go.add(800);        
        duanbobus_weekday_go.add(1000);
        duanbobus_weekday_go.add(1200);        
        duanbobus_weekday_go.add(1600);      
        duanbobus_weekday_go.add(1800);
        duanbobus_weekday_go.add(1930);        
        duanbobus_weekday_go.add(2030);
        duanbobus_weekday_go.add(2100);
        duanbobus_weekday_go.add(2200);

    //工作日回嘉定的短驳车发车时间       
        duanbobus_weekday_back.add(700);
        duanbobus_weekday_back.add(715);
        duanbobus_weekday_back.add(730);
        duanbobus_weekday_back.add(745);
        duanbobus_weekday_back.add(800);
        duanbobus_weekday_back.add(815);        
        duanbobus_weekday_back.add(1015);
        duanbobus_weekday_back.add(1215);        
        duanbobus_weekday_back.add(1615);       
        duanbobus_weekday_back.add(1815);
        duanbobus_weekday_back.add(1945);        
        duanbobus_weekday_back.add(2045);
        duanbobus_weekday_back.add(2115);
        duanbobus_weekday_back.add(2215);
    }
    
    //Type3: 2015  8.1-8.31      
    public void getType3() {
        /*****************************duanbobus******************************************/          
    //周末去地铁站的短驳车发车时间
        duanbobus_weekend_go.add(640);
        duanbobus_weekend_go.add(710);
        duanbobus_weekend_go.add(740);
        duanbobus_weekend_go.add(810);
        duanbobus_weekend_go.add(840);
        duanbobus_weekend_go.add(910);
        duanbobus_weekend_go.add(1000);
        duanbobus_weekend_go.add(1200);
        duanbobus_weekend_go.add(1600);
        duanbobus_weekend_go.add(1800);
        duanbobus_weekend_go.add(1930);
        duanbobus_weekend_go.add(2030);
        duanbobus_weekend_go.add(2100);
        duanbobus_weekend_go.add(2200);


    //周末回嘉定的短驳车发车时间
        duanbobus_weekend_back.add(655);
        duanbobus_weekend_back.add(720);
        duanbobus_weekend_back.add(755);
        duanbobus_weekend_back.add(825);
        duanbobus_weekend_back.add(855);
        duanbobus_weekend_back.add(925);
        duanbobus_weekend_back.add(1015);
        duanbobus_weekend_back.add(1215);
        duanbobus_weekend_back.add(1615);
        duanbobus_weekend_back.add(1815);
        duanbobus_weekend_back.add(1945);
        duanbobus_weekend_back.add(2045);
        duanbobus_weekend_back.add(2115);
        duanbobus_weekend_back.add(2215);

    //工作日去地铁站的短驳车发车时间
        duanbobus_weekday_go.add(640);
        duanbobus_weekday_go.add(710);
        duanbobus_weekday_go.add(740);
        duanbobus_weekday_go.add(810);
        duanbobus_weekday_go.add(840);
        duanbobus_weekday_go.add(910);
        duanbobus_weekday_go.add(1000);
        duanbobus_weekday_go.add(1200);
        duanbobus_weekday_go.add(1600);
        duanbobus_weekday_go.add(1800);
        duanbobus_weekday_go.add(1930);
        duanbobus_weekday_go.add(2030);
        duanbobus_weekday_go.add(2100);
        duanbobus_weekday_go.add(2200);

     //工作日回嘉定的短驳车发车时间
        duanbobus_weekday_back.add(655);
        duanbobus_weekday_back.add(720);
        duanbobus_weekday_back.add(755);
        duanbobus_weekday_back.add(825);
        duanbobus_weekday_back.add(855);
        duanbobus_weekday_back.add(925);
        duanbobus_weekday_back.add(1015);
        duanbobus_weekday_back.add(1215);
        duanbobus_weekday_back.add(1615);
        duanbobus_weekday_back.add(1815);
        duanbobus_weekday_back.add(1945);
        duanbobus_weekday_back.add(2045);
        duanbobus_weekday_back.add(2115);
        duanbobus_weekday_back.add(2215);
    }
    
    //Type4: 2015  9.11||9.12
    public void getType4() {
        /*****************************duanbobus******************************************/  
        //周末去地铁站的短驳车发车时间,增加12号的
        duanbobus_weekend_go.add(645);
        duanbobus_weekend_go.add(700);
        duanbobus_weekend_go.add(715);
        duanbobus_weekend_go.add(730);
        duanbobus_weekend_go.add(745);
        duanbobus_weekend_go.add(800);
        duanbobus_weekend_go.add(1000);
        duanbobus_weekend_go.add(1200);
	duanbobus_weekend_go.add(1300);
        duanbobus_weekend_go.add(1400);
	duanbobus_weekend_go.add(1500);
        duanbobus_weekend_go.add(1600);
	duanbobus_weekend_go.add(1700);
        duanbobus_weekend_go.add(1800);
	duanbobus_weekend_go.add(1900);
        duanbobus_weekend_go.add(2030);
        duanbobus_weekend_go.add(2100);
        duanbobus_weekend_go.add(2200);
	duanbobus_weekend_go.add(2300);
        
        //周末回嘉定的短驳车发车时间
        duanbobus_weekend_back.add(700);
        duanbobus_weekend_back.add(715);
        duanbobus_weekend_back.add(730);
        duanbobus_weekend_back.add(745);
        duanbobus_weekend_back.add(800);
        duanbobus_weekend_back.add(815);
        duanbobus_weekend_back.add(1015);
        duanbobus_weekend_back.add(1215);
	duanbobus_weekend_back.add(1315);
        duanbobus_weekend_back.add(1415);
        duanbobus_weekend_back.add(1515);
        duanbobus_weekend_back.add(1615);
	duanbobus_weekend_back.add(1715);
        duanbobus_weekend_back.add(1815);
	duanbobus_weekend_back.add(1915);
        duanbobus_weekend_back.add(2045);
        duanbobus_weekend_back.add(2115);
        duanbobus_weekend_back.add(2215);
	duanbobus_weekend_back.add(2315);
      
        //工作日去地铁站的短驳车发车时间,增加11号的
        duanbobus_weekday_go.add(615);
        duanbobus_weekday_go.add(630);
        duanbobus_weekday_go.add(645);
        duanbobus_weekday_go.add(700);
        duanbobus_weekday_go.add(715);
        duanbobus_weekday_go.add(730);
        duanbobus_weekday_go.add(745);
        duanbobus_weekday_go.add(800);
        duanbobus_weekday_go.add(900);
        duanbobus_weekday_go.add(1000);
        duanbobus_weekday_go.add(1200);
        duanbobus_weekday_go.add(1300);
        duanbobus_weekday_go.add(1500);
        duanbobus_weekday_go.add(1600);
        duanbobus_weekday_go.add(1700);
        duanbobus_weekday_go.add(1800);
        duanbobus_weekday_go.add(1900);
        duanbobus_weekday_go.add(2000);
        duanbobus_weekday_go.add(2030);
        duanbobus_weekday_go.add(2100);
        duanbobus_weekday_go.add(2200);
	duanbobus_weekday_go.add(2300);
        
        //工作日回嘉定的短驳车发车时间,增加11号的
        duanbobus_weekday_back.add(600);
        duanbobus_weekday_back.add(645);
        duanbobus_weekday_back.add(700);
        duanbobus_weekday_back.add(715);
        duanbobus_weekday_back.add(730);
        duanbobus_weekday_back.add(745);
        duanbobus_weekday_back.add(800);
        duanbobus_weekday_back.add(815);
        duanbobus_weekday_back.add(915);
        duanbobus_weekday_back.add(1015);
        duanbobus_weekday_back.add(1215);
        duanbobus_weekday_back.add(1315);
        duanbobus_weekday_back.add(1515);
        duanbobus_weekday_back.add(1615);
        duanbobus_weekday_back.add(1715);
        duanbobus_weekday_back.add(1815);
        duanbobus_weekday_back.add(1915);
        duanbobus_weekday_back.add(2015);
        duanbobus_weekday_back.add(2045);
        duanbobus_weekday_back.add(2115);
        duanbobus_weekday_back.add(2215);
        duanbobus_weekday_back.add(2315);
    
    }
      
}

