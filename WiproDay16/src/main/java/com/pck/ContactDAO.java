package com.pck;
import java.sql.*;
import java.util.*;
import com.pck.Contact;

public class ContactDAO {
	
		private Connection getConnection() throws Exception {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/contactdb","root","password");
	    }

	    public List<Contact> getAllContacts() throws Exception {
	        List<Contact> list = new ArrayList<>();
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM contacts");
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()){
	            Contact c = new Contact();
	            c.setId(rs.getInt("id"));
	            c.setName(rs.getString("name"));
	            c.setEmail(rs.getString("email"));
	            c.setPhone(rs.getString("phone"));
	            list.add(c);
	        }
	        con.close();
	        return list;
	    }

	    public void addContact(Contact c) throws Exception {
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement("INSERT INTO contacts(name,email,phone) VALUES(?,?,?)");
	        ps.setString(1, c.getName());
	        ps.setString(2, c.getEmail());
	        ps.setString(3, c.getPhone());
	        ps.executeUpdate();
	        con.close();
	    }

	    public void updateContact(Contact c) throws Exception {
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement("UPDATE contacts SET name=?, email=?, phone=? WHERE id=?");
	        ps.setString(1, c.getName());
	        ps.setString(2, c.getEmail());
	        ps.setString(3, c.getPhone());
	        ps.setInt(4, c.getId());
	        ps.executeUpdate();
	        con.close();
	    }

	    public void deleteContact(int id) throws Exception {
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement("DELETE FROM contacts WHERE id=?");
	        ps.setInt(1, id);
	        ps.executeUpdate();
	        con.close();
	    }
	}



