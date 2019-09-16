<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script>
$(function(){
function find(){
	var sname=$("#snames").val();
	$.ajax({
		type:"POST",
		dataType:"json",
		crossDomain: true,
		url:" http://api.seibro.or.kr/openapi/service/StockSvc/getStkIsinByNmN1?serviceKey=qMWBfGFhp4J1REtArquWOwxObYzh%2FoesRVV7scn4Hf3Xm%2BFEMMen0BMT4MhI%2BBwsrAlz5ik%2Bb87Q0pffXDuEFw%3D%3D&secnNm="+sname+"&numOfRows=2&pageNo=1",
		data:str,
		form:formName
	});
}	

	});


		
</script>
 
