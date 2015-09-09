package smile.weixin.jdquanyi.database;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static SessionFactory sessionFactory =null;
//	private static Configuration configuration = new Configuration();
	
	public HibernateSessionFactory(){
		
	}
	
	static{//????��???��?????
		try{
//			Configuration configure = configuration.configure("hibernate.cfg.xml");
//			sessionFactory = configure.buildSessionFactory();
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		}catch(Exception e){
			message("����SessionFactoryʧ��"+e);
		}
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void message(String msg){
		int type = JOptionPane.YES_NO_OPTION;
		String title = "��ʾ��Ϣ";
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
}
