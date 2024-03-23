<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>
<head>
<title>welcome page
</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<h2>Welcome ${username} your email is : ${email} ...!<br/>
your id is (  ${l_id} ), and your phone number is ${l_phonen}
</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">name</th>
      <th scope="col">address</th>
      <th scope="col">email</th>
      <th scope="col">phone number</th>
    </tr>
  </thead>
  <tbody>

  <c:forEach items="${labs}" var="row">

  <tr>
        <th scope="row">${row.l_id}</th>
        <td>${row.l_name}</td>
        <td>${row.l_address}</td>
        <td>${row.l_email}</td>
        <td>${row.l_phoneN}</td>
      </tr>

        </c:forEach>


  </tbody>
</table>
 </br>


</body>
</html>