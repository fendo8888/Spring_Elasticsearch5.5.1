<%@ page language="java" pageEncoding="UTF-8"%>
<%response.setHeader("Pragma","no-cache");response.setHeader("Cache-Control","no-store");response.setDateHeader("Expires",-1);%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<!-- 页面中使用的bootstrap -->
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/kkpager_blue.css" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />

<script type="text/javascript" src="${ctx}/static/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/kkpager.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js" ></script>