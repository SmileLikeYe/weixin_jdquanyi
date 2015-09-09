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
      
    //����һ���������ʱ��String���飬�ֱ��Ӧ
    /*
    �̲���
    String0�����ζ�?>����վ����ʱ��
    String1��������վ?>�ζ�����ʱ��
    ����
    String2����ѧУ����?>���󷢣���ʱ��
    String3��������?>ѧУ���ţ���ʱ��
    ����·���ٳ�
    String4����7��8��¥��ǰ?>����·����վ����ʱ��       
    */       
    public List<String> getNextBus() {
        /**
         * @param args the command line arguments
         */                        
        List<String> respContent = new ArrayList<String>();
        //��ȡϵͳʱ��
        String date = new Date().toString();
        String week = date.toString().substring(0, 3);
        String hour = date.toString().substring(11, 13);
        String minute = date.toString().substring(14, 16);
        String month = date.substring(4,7);        
        String str_day =  date.substring(8,10);
        String year = date.substring(24,28);
        int day = Integer.parseInt(str_day);
        
        //������רΪ2015����ٶ̲��������������ж�
        //��Ч�ڣ�2015.12.31
        if (year.equals("2015")){
            System.out.println("year" + year);            
            if (month.equals("Jul")){
                System.out.println("month" + month);
                if (day<20){
                    //Type1??����
                    System.out.println("day" + day);
                    this.getType1();
                }else{
                    //Type2??7���ر�����ƪ                    
                    this.getType2();
                }
            }else if (month.equals("Aug")){
                //Type3??8���ر�����ƪ
                this.getType3();
                
            }else if (month.equals("Sep")){
                if (day<7){
                    //Type2??7���ر�����ƪ
                    this.getType2();
                    
                }else if (day==11 || day==12){
                    //Type4??��ѧ�ر�����ƪ
                    this.getType4();
                    
                }else{
                    //Type1??���� 
                    this.getType1();
                }                                 
            }else {
                //Type1??���� �����·�
            }
        }
            
            
            

        String str_nowTime = hour + minute;
        //��������09��53����952,0û��λ�����
        int i_nowTime = Integer.parseInt(str_nowTime);
        //���������ĩ
        if ("Sun".equals(week) || "Sat".equals(week)) {
            //����ǰ������վ�Ķ̲���ʱ���
            for (int i = 0; i < duanbobus_weekend_go.size(); i++) {
                //����һ��ǰ������վ�Ķ̲���
                if (i_nowTime <= (int) duanbobus_weekend_go.get(i)) {
                    //ʱ���ʽΪxxx(0��00 - 9��59)
                    if (duanbobus_weekend_go.get(i).toString().length() == 3) {
                        respContent.add(duanbobus_weekend_go.get(i).toString().substring(0, 1) 
                                + ":" + duanbobus_weekend_go.get(i).toString().substring(1, 3));
//                                += "-->�Ϻ�������վ:" + duanbobus_weekend_go.get(i).toString().substring(0, 1) + ":"
//                                + duanbobus_weekend_go.get(i).toString().substring(1, 3) + //                                += "-->�Ϻ�������վ:" + duanbobus_weekend_go.get(i).toString().substring(0, 1) + ":"'\n';
                    } else {//ʱ���ʽΪxxxx
                        respContent.add(duanbobus_weekend_go.get(i).toString().substring(0, 2) 
                                + ":" + duanbobus_weekend_go.get(i).toString().substring(2, 4));
                        
//                        respContent += "-->�Ϻ�������վ:" + duanbobus_weekend_go.get(i).toString().substring(0, 2) + ":"
//                                + duanbobus_weekend_go.get(i).toString().substring(2, 4) + '\n';
                    }
                    break;//���ֵ�һ������������
                }
            }
            
            if(respContent.size() != 1)  respContent.add("������ͣ��");
            
            //�������ؼζ��Ķ̲���ʱ���
            for (int j = 0; j < duanbobus_weekend_back.size(); j++) {
                //����һ�˷��ؼζ��Ķ̲���
                if (i_nowTime <= (int) duanbobus_weekend_back.get(j)) {
                    //ʱ���ʽΪxxx
                    if (duanbobus_weekend_back.get(j).toString().length() == 3) {
                        respContent.add(duanbobus_weekend_back.get(j).toString().substring(0, 1) 
                                + ":" + duanbobus_weekend_back.get(j).toString().substring(1, 3)); 
                        
//                        respContent += "-->ͬ�üζ�У��:" + duanbobus_weekend_back.get(j).toString().substring(0, 1) + ":"
//                                + duanbobus_weekend_back.get(j).toString().substring(1, 3) + '\n';
                    } else {//ʱ���ʽΪxxxx
                        respContent.add(duanbobus_weekend_back.get(j).toString().substring(0, 2) 
                                + ":" + duanbobus_weekend_back.get(j).toString().substring(2, 4)); 
//                        respContent += "-->ͬ�üζ�У��:" + duanbobus_weekend_back.get(j).toString().substring(0, 2) + ":"
//                                + duanbobus_weekend_back.get(j).toString().substring(2, 4) + '\n';
                    }
                    break;//���ֵ�һ������������
                }
            }
            
            if(respContent.size() != 2)  respContent.add("������ͣ��");
            
        } else {//������ڹ�����
            //����ǰ������վ�Ķ̲���ʱ���
            for (int i = 0; i < duanbobus_weekday_go.size(); i++) {
                //����һ��ǰ������վ�Ķ̲���
                if (i_nowTime <= (int) duanbobus_weekday_go.get(i)) {
                    //ʱ���ʽΪxxx
                    if (duanbobus_weekday_go.get(i).toString().length() == 3) {
                        
                        respContent.add(duanbobus_weekday_go.get(i).toString().substring(0, 1) 
                                + ":" + duanbobus_weekday_go.get(i).toString().substring(1, 3));
                        
//                        respContent += "-->�Ϻ�������վ:" + duanbobus_weekday_go.get(i).toString().substring(0, 1) + ":"
//                                + duanbobus_weekday_go.get(i).toString().substring(1, 3) + '\n';
                    } else {//ʱ���ʽΪxxxx
                        respContent.add(duanbobus_weekday_go.get(i).toString().substring(0, 2) 
                                + ":" + duanbobus_weekday_go.get(i).toString().substring(2, 4));
                        
//                        respContent += "-->�Ϻ�������վ:" + duanbobus_weekday_go.get(i).toString().substring(0, 2) + ":"
//                                + duanbobus_weekday_go.get(i).toString().substring(2, 4) + '\n';
                    }
                    break;//���ֵ�һ������������
                }
            }
//            System.out.println(respContent.size());
            if(respContent.size() != 1)  respContent.add("������ͣ��");
            
            //�������ؼζ��Ķ̲���ʱ���
            for (int j = 0; j < duanbobus_weekday_back.size(); j++) {
                //����һ�˷��ؼζ��Ķ̲���
                if (i_nowTime <= (int) duanbobus_weekday_back.get(j)) {
                    //ʱ���ʽΪxxx
                    if (duanbobus_weekday_back.get(j).toString().length() == 3) {
                        respContent.add(duanbobus_weekday_back.get(j).toString().substring(0, 1) 
                                + ":" + duanbobus_weekday_back.get(j).toString().substring(1, 3));
                        
//                        respContent += "-->ͬ�üζ�У��" + duanbobus_weekday_back.get(j).toString().substring(0, 1) + ":"
//                                + duanbobus_weekday_back.get(j).toString().substring(1, 3) + '\n';
                    } else {//ʱ���ʽΪxxxx
                        respContent.add(duanbobus_weekday_back.get(j).toString().substring(0, 2) 
                                + ":" + duanbobus_weekday_back.get(j).toString().substring(2, 4));
                        
//                        respContent += "-->ͬ�üζ�У��:" + duanbobus_weekday_back.get(j).toString().substring(0, 2) + ":"
//                                + duanbobus_weekday_back.get(j).toString().substring(2, 4) + '\n';
                    }
                    break;//���ֵ�һ������������
                }
            }
//            System.out.println(respContent.size());
            if(respContent.size() != 2)  respContent.add("������ͣ��");

        }
        
      
/*****************************darunfabus******************************************/ 
    //���󷢰೵��ѧУ���� --> ����
        darunfabus_go.add(751);
        darunfabus_go.add(906);
        darunfabus_go.add(1036);
        darunfabus_go.add(1221);
        darunfabus_go.add(1436);
        darunfabus_go.add(1636);
        darunfabus_go.add(1836);
                
    //���󷢰೵������ --> ѧУ����
        darunfabus_back.add(715);
        darunfabus_back.add(830);
        darunfabus_back.add(1000);
        darunfabus_back.add(1145);
        darunfabus_back.add(1400);
        darunfabus_back.add(1600);
        darunfabus_back.add(1800);
        darunfabus_back.add(2020);
        
        
    //����ǰ�����󷢵Ĵ��󷢷���ʱ���
            for (int i = 0; i < darunfabus_go.size(); i++) {
                //����һ��ǰ�����󷢵ĳ�
                if (i_nowTime <= (int) darunfabus_go.get(i)) {
                    //ʱ���ʽΪxxx(0��00 - 9��59)
                    if (darunfabus_go.get(i).toString().length() == 3) {
                        respContent.add(darunfabus_go.get(i).toString().substring(0, 1) 
                                + ":" + darunfabus_go.get(i).toString().substring(1, 3));

                    } else {//ʱ���ʽΪxxxx
                        respContent.add(darunfabus_go.get(i).toString().substring(0, 2) 
                                + ":" + darunfabus_go.get(i).toString().substring(2, 4));                       
                    }
                    break;//���ֵ�һ������������
                }
            }
            
//            System.out.println(respContent.size());
            if(respContent.size() != 3)  respContent.add("������ͣ��");
        
     //�������ؼζ�ѧУ���ŵĴ��󷢳�ʱ���
            for (int j = 0; j < darunfabus_back.size(); j++) {
                //����һ�˷��ؼζ�ѧУ���ŵĳ�
                if (i_nowTime <= (int) darunfabus_back.get(j)) {
                    //ʱ���ʽΪxxx
                    if (darunfabus_back.get(j).toString().length() == 3) {
                        respContent.add(darunfabus_back.get(j).toString().substring(0, 1) 
                                + ":" + darunfabus_back.get(j).toString().substring(1, 3));                        
                    } else {//ʱ���ʽΪxxxx
                        respContent.add(darunfabus_back.get(j).toString().substring(0, 2) 
                                + ":" + darunfabus_back.get(j).toString().substring(2, 4)); 
                    }
                    break;//���ֵ�һ������������
                }
            }  
            
            if(respContent.size() != 4)  respContent.add("������ͣ��");
/*****************************gaosubus******************************************/         
    //����·���ٳ���7��8��¥��ǰ --> ����·��Ϫ·����վ (��ĩ������)
        gaosubus_go.add(720);
        gaosubus_go.add(1000);
        gaosubus_go.add(1230);
        gaosubus_go.add(1530);
        gaosubus_go.add(1730);
        
        
         if ("Sun".equals(week) || "Sat".equals(week)) {
             respContent.add("��ĩ������");
         } else {
    //����ǰ������·��Ϫ·����վ����ʱ���
            for (int i = 0; i < gaosubus_go.size(); i++) {
                //����һ��ǰ������·��Ϫ·����վ�ĳ�
                if (i_nowTime <= (int) gaosubus_go.get(i)) {
                    //ʱ���ʽΪxxx(0��00 - 9��59)
                    if (gaosubus_go.get(i).toString().length() == 3) {
                        respContent.add(gaosubus_go.get(i).toString().substring(0, 1) 
                                + ":" + gaosubus_go.get(i).toString().substring(1, 3));

                    } else {//ʱ���ʽΪxxxx
                        respContent.add(gaosubus_go.get(i).toString().substring(0, 2) 
                                + ":" + gaosubus_go.get(i).toString().substring(2, 4));                       
                    }
                    break;//���ֵ�һ������������
                }
            }
            
         }
            
            if(respContent.size() != 5)  respContent.add("������ͣ��");
            
        return respContent;
    }
    
    //Type1:�̲�����������: 7.19��֮ǰ && 9.7�Ժ�
     public void getType1() {
        /*****************************duanbobus******************************************/          
    //��ĩȥ����վ�Ķ̲�������ʱ��
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

    //��ĩ�ؼζ��Ķ̲�������ʱ��
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

    //������ȥ����վ�Ķ̲�������ʱ��
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

    //�����ջؼζ��Ķ̲�������ʱ��
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
    //��ĩȥ����վ�Ķ̲�������ʱ��
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

    //��ĩ�ؼζ��Ķ̲�������ʱ��
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

    //������ȥ����վ�Ķ̲�������ʱ��        
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

    //�����ջؼζ��Ķ̲�������ʱ��       
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
    //��ĩȥ����վ�Ķ̲�������ʱ��
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


    //��ĩ�ؼζ��Ķ̲�������ʱ��
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

    //������ȥ����վ�Ķ̲�������ʱ��
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

     //�����ջؼζ��Ķ̲�������ʱ��
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
        //��ĩȥ����վ�Ķ̲�������ʱ��,����12�ŵ�
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
        
        //��ĩ�ؼζ��Ķ̲�������ʱ��
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
      
        //������ȥ����վ�Ķ̲�������ʱ��,����11�ŵ�
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
        
        //�����ջؼζ��Ķ̲�������ʱ��,����11�ŵ�
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

