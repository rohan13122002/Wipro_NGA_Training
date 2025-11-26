<%@ page import="java.util.List" %>
<%@ page import="com.pck.Contact" %>
<html>
<head>
    <title>All Contacts</title>
</head>
<body>
    <h2>Saved Contacts</h2>
    <table border="1">
        <tr><th>Name</th><th>Email</th><th>Phone</th></tr>
        <%
            List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
            if (contacts != null) {
                for (Contact c : contacts) {
        %>
            <tr>
                <td><%= c.getName() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getPhone() %></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
