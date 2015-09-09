/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.database;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotatedClassType;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *!!!!!注意
 * 由于在本地链接BAE的Mysql是要发起Http请求的，也就是说真是我们是没法访问的。
 * @author smile
 */
public class Database {

    public Database() {
    }
    
    /*
    *
    *  判断是否存在
    */
    public boolean FuXiuExists(String fromUserName) {
        boolean exist = false;
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
//        System.out.println("begintransaction");
        Query q = s.createQuery("from FX_FuXiu");
        List<FX_FuXiu> fx_list = q.list();
        s.getTransaction().commit();
        sf.close();
        
        for(FX_FuXiu f : fx_list) {
            if(f.getFx_fromUserName().equals(fromUserName)) {
                exist = true;
                break;
            }
        }
        return exist;
   }    
    //如果存在于名单中，并且通过了验证就返回2；如果存在于名单中，但没有通过验证就返回1，如果没有存在于名单中，就返回0；
    public int GongYiSanExists(String fromUsername) {
        int exits = 0;
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from GYS_GongYiSan");
        List<GYS_GongYiSan> gys_list = q.list();
        s.getTransaction().commit();
        sf.close();
        
        for(GYS_GongYiSan gys :gys_list) {
            if(gys.getGYS_fromUserName().equals(fromUsername) ) {
                exits = 1;
            }
            if(gys.getGYS_fromUserName().equals(fromUsername) && gys.getGYS_verification()) {
                exits = 2;
            }   
        }
        return exits;       
    }
    
    /*
    *
    *  增
    */
    public boolean AddFuxiu(FX_FuXiu fx_user) {
        SessionFactory sf = new AnnotationConfiguration().configure()
                .buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(fx_user);
        s.getTransaction().commit();
        sf.close();
        return true;
    }
    //储存公益伞的用户
    public boolean AddGongYiSan(GYS_GongYiSan gys_user) {
        SessionFactory sf = new AnnotationConfiguration().configure()
                .buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(gys_user);
        s.getTransaction().commit();
        sf.close();
        return true;
    }
    
    
    /*
    *
    *  判断某个flag
    */
    public boolean GongYiSanIsPure(String fromUserName) {
        boolean pure = true;
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from GYS_GongYiSan");
        List<GYS_GongYiSan> gys_list = q.list();
        s.getTransaction().commit();
        sf.close();
        
        for(GYS_GongYiSan gys :gys_list) {
            if(gys.getGYS_fromUserName().equals(fromUserName)) {
                pure = gys.getGYS_pure();
                break;
            }
        }
        return pure;
    }
    
    public boolean GongYiSanIsVerified(String fromUserName) {
        boolean verified = false;
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from GYS_GongYiSan");
        List<GYS_GongYiSan> gys_list = q.list();
        s.getTransaction().commit();
        sf.close();
        
        for(GYS_GongYiSan gys :gys_list) {
            if(gys.getGYS_fromUserName().equals(fromUserName)) {
                verified = gys.getGYS_verification();
                break;
            }
        }
        return verified;
    }
    
    
    /*
    *
    *  查
    */
    public List<SW_ShiWu> GetShiWuInfs() {
        SessionFactory sf = new AnnotationConfiguration().configure()
                .buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from SW_ShiWu");
        List<SW_ShiWu> sw = q.list();
        s.getTransaction().commit();
        sf.close();
        //System.out.println("Query OK!");
        
        return sw;
    }
    
    public int getGYS_Id(String fromUserName) {
        int id = 0;
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from GYS_GongYiSan");
        List<GYS_GongYiSan> gys_list = q.list();
        s.getTransaction().commit();
        sf.close();
        
        for(GYS_GongYiSan gys :gys_list) {
            if(gys.getGYS_fromUserName().equals(fromUserName) ) {
                id = gys.getGYS_id();
                break;
               // System.out.println(id);
            }
        }
        return id;
    }
    
    public List<WM_WaiMai> getWaiMai() {
         SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
         Session s = sf.getCurrentSession();
         s.beginTransaction();
         Query q = s.createQuery("from WM_WaiMai");
         List<WM_WaiMai> wm_list = q.list();
         s.close();
         
         return wm_list;
         
                 
     }
     
    public int getWC_count() {
         int weekcount = -1;
         SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
         Session s = sf.getCurrentSession();
         s.beginTransaction();
         Query q = s.createQuery("from WC_WeekCount");
         List<WC_WeekCount> wc_list = q.list();
         sf.close();
         
         for(WC_WeekCount wc : wc_list) {
             if(1 == wc.getId()){
                 weekcount = wc.getCount();
                 break;
             }
         }         
         return weekcount;
     }
   
    
    /*
    *
    *  改
    */
    public boolean updateFX_count(String fromUsername) {
         SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
         Session s = sf.getCurrentSession();
         s.beginTransaction();
         Query q = s.createQuery("from FX_FuXiu");        
         List<FX_FuXiu> fx_list = q.list();
         
         for (FX_FuXiu fx : fx_list) {
             if (fx.getFx_fromUserName().equals(fromUsername)) {
                 if (0 == fx.getFx_flag()) {
                     fx.setFx_count(fx.getFx_count()+1);
                     fx.setFx_flag(1);
                     s.save(fx);
                     s.getTransaction().commit();                     
                 }
                 break;
             }
         }
         s.close();
         return true;
     } 
    
    public boolean updatePure(String fromUserName,boolean change) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        GYS_GongYiSan gys = (GYS_GongYiSan)s.get(GYS_GongYiSan.class, getGYS_Id(fromUserName));
        gys.setGYS_pure(change);
        s.save(gys);
        s.getTransaction().commit();
        sf.close();
        return true;
    }
    
    public boolean updateTelephone(String fromUserName,String telephone) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        GYS_GongYiSan gys = (GYS_GongYiSan)s.get(GYS_GongYiSan.class, getGYS_Id(fromUserName));
        gys.setGYS_telephone(telephone);
        s.save(gys);
        s.getTransaction().commit();
        sf.close();
        return true;
    }
    
    public boolean updateVerification(String fromUserName,boolean change) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        GYS_GongYiSan gys = (GYS_GongYiSan)s.get(GYS_GongYiSan.class, getGYS_Id(fromUserName));
        gys.setGYS_verification(change);
        s.save(gys);
        s.getTransaction().commit();
        sf.close();
        return true;
    }
    
    public boolean updateMenuClickCount(int menu_id){
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.getCurrentSession();
       
        try {
            s.beginTransaction();
            MCC_MenuClickCount mcc = (MCC_MenuClickCount) s.get(MCC_MenuClickCount.class, menu_id);
            mcc.setHistory_count(mcc.getHistory_count() + 1);

            String date = new Date().toString();
            String week = date.toString().substring(0, 3);
            switch (week) {
                case "Mon":
                    mcc.setMonday_count(mcc.getMonday_count() + 1);
                    break;
                case "Tue":
                    mcc.setTuesday_count(mcc.getTuesday_count() + 1);
                    break;
                case "Wed":
                    mcc.setWednesday_count(mcc.getWednesday_count() + 1);
                    break;
                case "Thu":
                    mcc.setThursday_count(mcc.getThursday_count() + 1);
                    break;
                case "Fri":
                    mcc.setFriday_count(mcc.getFriday_count() + 1);
                    break;
                case "Sat":
                    mcc.setSaturday_count(mcc.getSaturday_count() + 1);
                    break;
                case "Sun":
                    mcc.setSunday_count(mcc.getSunday_count() + 1);
                    break;
                default:
                    break;
            }
            s.save(mcc);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sf.close();
        }
        return true;
                
        
        
    }
    
    
    /********************************标准增删改查*************************/
      /*
    *
    *  增
    */
    public boolean AddQuestion(MQ_MyQuestions question) {
        SessionFactory sf = new AnnotationConfiguration().configure()
                .buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(question);
//        int id = question.getId();
        s.getTransaction().commit();
        sf.close();
        return true;
    }
    
    
    /*
    *
    *  查
    */
    public List<MQ_MyQuestions> GetMyQuestions(String fromUserName) {
        SessionFactory sf = new AnnotationConfiguration().configure()
                .buildSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        Query q = s.createQuery("from MQ_MyQuestions");
        List<MQ_MyQuestions> mq_list = q.list();
        s.getTransaction().commit();
        sf.close();        
        List<MQ_MyQuestions> result = new ArrayList<MQ_MyQuestions>();
        
        for (MQ_MyQuestions mq : mq_list) {
            if (mq.getFromUserName().equals(fromUserName)) {
                result.add(mq);
            }
        }
            
        
        return result;
    }
    
//     public int getMQ_Id(String fromUserName) {
//        int id = 0;
//        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session s = sf.getCurrentSession();
//        s.beginTransaction();
//        Query q = s.createQuery("from GYS_GongYiSan");
//        List<GYS_GongYiSan> gys_list = q.list();
//        s.getTransaction().commit();
//        sf.close();
//        
//        for(GYS_GongYiSan gys :gys_list) {
//            if(gys.getGYS_fromUserName().equals(fromUserName) ) {
//                id = gys.getGYS_id();
//                break;
//               // System.out.println(id);
//            }
//        }
//        return id;
//    }
//    
//    public boolean updateReply(String reply) {
//        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session s = sf.getCurrentSession();
//        s.beginTransaction();
//        GYS_GongYiSan gys = (GYS_GongYiSan)s.get(GYS_GongYiSan.class, getGYS_Id(fromUserName));
//        gys.setGYS_pure(change);
//        s.save(gys);
//        s.getTransaction().commit();
//        sf.close();
//        return true;
//    }
    
    
    /**************************************************************/
    
    
    
}
