package com.jsp.hibernate.hibernate_project_demo;

import javax.security.auth.login.Configuration;

import com.mysql.cj.xdevapi.Session;

public class App 
{
    public static void main( String[] args) {
    	Configuration cfg = new Configuration();
    	cfg.configure()
    	cfg,addAnnotatedClass(Product.class);
    	SessionFactory sf = cfg.buildSessionFactory();
    	Session session = sf.opensession();
    	Transaction tran = session.begintransaction();
    	
    	
    	
    	tran.commit();
    	sf.close();
    	session.close();
    }
}
