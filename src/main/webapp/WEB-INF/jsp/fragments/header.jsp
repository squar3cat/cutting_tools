<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>Cutting Tools</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="shortcut icon" href="#">

    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/topmenu.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.5.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/demo/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.21/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/lib/noty.css"/>

    <script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js" defer></script>
    <script type="text/javascript" src="webjars/bootstrap/4.5.3/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.21/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.21/js/dataTables.bootstrap4.min.js" defer></script>
    <script type="text/javascript" src="webjars/momentjs/2.29.1/min/moment-with-locales.min.js" defer></script>
    <script type="text/javascript" src="resources/js/datetime-moment.js" defer></script>
    <script type="text/javascript" src="webjars/noty/3.1.4/lib/noty.min.js" defer></script>
    <script type="text/javascript" src="resources/js/topmenu.js" defer></script>
    <script type="text/javascript" src="resources/js/validating/joi.min.js" defer></script>
</head>