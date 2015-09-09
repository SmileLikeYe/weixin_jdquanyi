/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.database;

import java.util.List;
import smile.weixin.jdquanyi.tools.BusTime;

/**
 *
 * @author smile
 */
public class Test {
    
    public static void main(String args[]) throws InterruptedException {
//        Database db = new Database();
//        if(db.FuXiuExists("huxing")) {
//            System.out.println("huxing在辅修名单里！");
//        }else {
//            System.out.println("huxing没在辅修名单里！");
//        }
//        
//        List<SW_ShiWu> sw = db.GetShiWuInfs();
//        for (SW_ShiWu s : sw) {
//            System.out.print(s.getSw_decsription());
//        }
        
//        BusTime d = new BusTime();
//        System.out.println(d.getNextDuanboBus());
         String a = "辅修fxsdfd";
         if (a.startsWith("辅修fx") )
         {
             System.out.println("OK !");
         }

    }
    
}
