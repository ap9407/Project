<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="cpath" value="${pageContext.request.contextPath }" />
<c:set var="dao" value="${EmpDAO.getInstance() }"/>
<c:set var="dao2" value="${CliDAO.getInstance() }"/>
<c:set var="dao3" value="${BPDAO.getInstance() }"/>
<c:set var="dao4" value="${SaleDAO.getInstance() }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        nav {
            width: auto;
            margin: auto;
        }
        ul, ol {
            list-style: none; 
            padding-left: 0;
        }
        a.logo {
        	all: unset;
       		margin-right: auto;
       		cursor: pointer;
       	}
      
        a {
            text-decoration: none;
            color: inherit;
            font-size: 20px;
            font-weight: bold;
            display: flex;
            width: 100%;
            height: 100%;
            justify-content: center;
            align-items: center;
        }
        ul {
            background-color: #2085c1;
            display: flex;
        }
        ol {
            display: none;
            position: absolute;
            top: 37px;
            left: 0;
            width: 160px;
            background-color: #2085c1;
        }
        ul > li {
            width: 100%;
            height: 35px;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
            border-bottom: 2px solid transparent;
            color: white;
            font-family: 맑은 고딕;
        }
        ul > li:hover {
        	background-color: white;
            color: #2085c1; 
        }
        ol > li > a {
            font-size: 15px;
            font-weight: normal;
            display: block;
            padding: 5px 10px;
            font-weight: bold;
            color: white;
        }
        ol > li > a:hover {
            text-decoration: underline;
        }
        ul > li:hover > ol {
            display: block;
        }
        
        .legend  {
        	font-size: 25px;
        	font-weight: bold;
        
        }
        
        <%-- 고객관리 / 상품관리 --%>
        
        .scrolltable {
        	table-layout: fixed;
        	border-collapse: collapse;
        	border: 1px solid #888;
			width: auto;

        }
		.scrolltable th, .scrolltable td {
			padding: 10px;
			text-align: left;
			width: 100px;
			text-align: center;
			font-size: 0.875em;
		}
		.scrolltable tbody tr > td:nth-child(2n+1){
  			background-color: #e3f2fd;
		}
		.scrolltable thead {
		    display: block;
		    background: #8aacc8;
		    color: #fff;

		}
		.scrolltable tbody{
  			display: block;
   		    overflow: scroll;
    		height: 400px;
		}
		
		<%-- 직원관리 --%>
		
		.scrolltable2 {
        	table-layout: fixed;
        	border-collapse:collapse;
        	border: 1px solid #888;
			width: auto;
 			margin: auto;
        }
		.scrolltable2 th, .scrolltable2 td {
			padding: 10px;
			text-align: left;
			width: 100px;
			text-align: center;
			font-size: 0.875em;
		}
		.scrolltable2 tbody tr > td:nth-child(2n+1){
  			background-color: #e3f2fd;
		}
		.scrolltable2 thead {
		    display: block;
		    background: #8aacc8;
		    color:#fff;

		}
		.scrolltable2 tbody{
  			display: block;
   		    overflow: scroll;
    		height: 600px;
		}
		.emp {
			display: flex;
			align-items: center;
			justify-content: center;
		}
 		.emp > form { 
 			position: relative;
 			margin-right: 300px;		
			width: 20%;
		}

		
		<%-- 판매(상품 - 상품별갯수파악) --%>
		
		 .scrolltable3 {
        	table-layout: fixed;
        	border-collapse: collapse;
        	border: 1px solid #888;
			width: auto;

        }
		.scrolltable3 th, .scrolltable3 td {
			padding: 10px;
			text-align: left;
			width: 100px;
			text-align: center;
			font-size: 0.875em;
		}
		.scrolltable3 tbody tr > td:nth-child(2n+1){
  			background-color: #e3f2fd;
		}
		.scrolltable3 thead {
		    display: block;
		    background: #8aacc8;
		    color: #fff;

		}
		.scrolltable3 tbody{
  			display: block;
   		    overflow: scroll;
    		height: auto;
		}
		
		<%--판매(전체 / 월별 / 연도별) --%>
		.scrolltable4 {
        	table-layout: fixed;
        	border-collapse: collapse;
        	border: 1px solid #888;
			width: auto;

        }
		.scrolltable4 th, .scrolltable4 td {
			padding: 10px;
			text-align: left;
			width: 100px;
			text-align: center;
			font-size: 0.875em;
		}
		.scrolltable4 tbody tr > td:nth-child(2n+1){
  			background-color: #e3f2fd;
		}
		.scrolltable4 thead {
		    display: block;
		    background: #8aacc8;
		    color: #fff;

		}
		.scrolltable4 tbody{
  			display: block;
   		    overflow: scroll;
    		height: 600px;
		}	
			
</style>
</head>
<body>
<header>
<a class="logo" href="${cpath }/index.jsp"><img src="${cpath}/img/logo2.jpg" height="70px"/></a>
    <nav>
        <ul>
            <li>
                <a>직원관리</a>
                 <ol>
                    <li><a href="emplist.jsp">직원 목록</a></li>
                    <li><a href="empadd.jsp">직원 추가</a></li>
                    <li><a href="empmodify.jsp">직원 수정/삭제</a></li>                  
                </ol>
            </li>
            <li>
                <a>고객관리</a>
                <ol>
                    <li><a href="clilist.jsp">고객 목록</a></li>
                    <li><a href="cliadd.jsp">고객 추가</a></li>
                    <li><a href="climodify.jsp">고객 수정/삭제</a></li>                    
                </ol>
            </li>
         
            <li>
                <a>상품관리</a>
                <ol>
                    <li><a href="bplist.jsp">상품 목록</a></li>
                    <li><a href="bpadd.jsp">상품 추가</a></li>
                    <li><a href="bpmodify.jsp">상품 수정/삭제</a></li>
                </ol>
            </li>
  
            <li>
                <a>판매</a>
                <ol>
                    <li><a href="saleAllList.jsp">전체 판매 목록</a></li>
                    <li><a href="saleMonthList.jsp">월별 판매 목록</a></li>
                    <li><a href="saleYearList.jsp">연도별 판매 목록</a></li>
                    <li><a href="saleBpList.jsp">상품별 판매 목록</a></li>
                    <li><a href="saleBpSearch.jsp">상품별 판매 목록</a></li>
                </ol>
            </li>
            <li>
                <a href="index.jsp">스케줄 관리</a>
            </li>
            <li>
                <a href="">회계</a>
            </li>
        </ul>
	</nav>
</header>
