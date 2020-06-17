<%@ page import="mvcIntelliJIdea.model.User" %>
<%@ page import="mvcIntelliJIdea.model.Picture" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: forest
  Date: 16.12.2014
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%!
    public String name = "Bob";
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<form action="UploadPictureController" method="post">
    You are:<input type="text" id="flag" name="flag">
    <label for="img">Select image:</label>
    <input type="file" id="img" name="img" accept="image/*">
    <input type="submit">
</form>
<br>
<br>

<form action="UploadPictureController" method="post">
    You are:<input type="text" id="voter" name="voter">
    Vote for:<input type="text" id="voted" name="voted">
    Rate:<input type="text" id="rate" name="rate">
    <input type="submit">
<br>
<br>

<form action="UploadPictureController" method="post">
    Get N most rated pictures:<input type="text" id = "N" name="N">
    <input type="submit">
</form>

<br>
<br>




<%
ArrayList<mvcIntelliJIdea.model.Picture> pictures = (ArrayList<mvcIntelliJIdea.model.Picture>) request.getAttribute("pictures");
    Boolean showAll =  (Boolean)request.getAttribute("showAll");
if(pictures != null && showAll.booleanValue() == true){
for ( Picture picture : pictures){ %>

   author: <%out.print(picture.get_name());%>
   id : <%out.print(picture.get_id());%>

<img src = "<%out.print(picture.get_file());%>" width="300" height="300">
  score: <%out.print(picture.get_score());%>
    <br>
<%}
}%>


<%
ArrayList<mvcIntelliJIdea.model.Picture> pictures2 = (ArrayList<mvcIntelliJIdea.model.Picture>) request.getAttribute("sorted");
    Boolean showN =  (Boolean)request.getAttribute("showN");
    if(pictures2 != null && showN.booleanValue() == true){
        Integer n = (Integer) request.getAttribute("size");
        for ( int i = 0 ; i < n ; i++){ %>

author: <%out.print(pictures2.get(i).get_name());%>
id : <%out.print(pictures2.get(i).get_id());%>

<img src = "<%out.print(pictures2.get(i).get_file());%>" width="300" height="300">
score: <%out.print(pictures2.get(i).get_score());%>
<br>
<%}
}%>




</body>
</html>