package com.pck;

import com.pck.ContactDAO;
import com.pck.Contact;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ContactDAO dao = new ContactDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if("add".equals(action)){
                Contact c = new Contact();
                c.setName(req.getParameter("name"));
                c.setEmail(req.getParameter("email"));
                c.setPhone(req.getParameter("phone"));
                dao.addContact(c);
                req.setAttribute("message","Contact added successfully!");
                req.getRequestDispatcher("listContacts.jsp").forward(req,resp);
            } else if("update".equals(action)){
                Contact c = new Contact();
                c.setId(Integer.parseInt(req.getParameter("id")));
                c.setName(req.getParameter("name"));
                c.setEmail(req.getParameter("email"));
                c.setPhone(req.getParameter("phone"));
                dao.updateContact(c);
                req.setAttribute("message","Contact updated successfully!");
                req.getRequestDispatcher("listContacts.jsp").forward(req,resp);
            } else if("delete".equals(action)){
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteContact(id);
                req.setAttribute("message","Contact deleted successfully!");
                req.getRequestDispatcher("listContacts.jsp").forward(req,resp);
             }
        }
        catch(Exception e){
            req.setAttribute("error","Error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
