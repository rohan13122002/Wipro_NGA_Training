<%@ page import="com.pck.ContactDAO,com.pck.Contact" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    ContactDAO dao = new ContactDAO();
    Contact c = null;
    for(Contact ct : dao.getAllContacts()){
        if(ct.getId() == id){ c = ct; break; }
    }
%>
<html>
<head><title>Edit Contact</title></head>
<body>
<h2>Edit Contact</h2>
<form action="ContactServlet" method="post">
    <input type="hidden" name="id" value="<%=c.getId()%>"/>
    Name: <input type="text" name="name" value="<%=c.getName()%>"/><br/>
    Email: <input type="text" name="email" value="<%=c.getEmail()%>"/><br/>
    Phone: <input type="text" name="phone" value="<%=c.getPhone()%>"/><br/>
    <input type="submit" name="action" value="update"/>
</form>
</body>
</html>
