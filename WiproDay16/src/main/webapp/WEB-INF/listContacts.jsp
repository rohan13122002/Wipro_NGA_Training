<%@ page import="java.util.*,com.pck.ContactDAO,com.pck.Contact" %>
<%
    ContactDAO dao = new ContactDAO();
    List<Contact> contacts = dao.getAllContacts();
%>
<html>
<head><title>Contact List</title></head>
<body>
<h2>My Contacts</h2>
<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Actions</th></tr>
<%
for(Contact c : contacts){
%>
<tr>
<td><%=c.getId()%></td>
<td><%=c.getName()%></td>
<td><%=c.getEmail()%></td>
<td><%=c.getPhone()%></td>
<td>
<form action="ContactServlet" method="post">
    <input type="hidden" name="id" value="<%=c.getId()%>"/>
    <input type="submit" name="action" value="delete"/>
</form>
<a href="editContact.jsp?id=<%=c.getId()%>">Edit</a>
</td>
</tr>
<% } %>
</table>
<a href="addContact.jsp">Add New Contact</a>
</body>
</html>
