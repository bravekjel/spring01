<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script>
$(function(){
	$("#stockInfo").click(function(){
		var key = $("#number").val();
		var num = repl(key);
		location.href="${path}/search_stock/stockInfo.do?num="+num;
	});
	$("#financialInfo").click(function(){
		var key = $("#number").val();
		var num = repl(key);
		location.href="${path}/search_stock/financialInfo.do?num="+num;
	});
	$("#disInfo").click(function(){
		var key = $("#number").val();
		var num = repl(key);
		location.href="${path}/search_stock/disInfo.do?num="+num;
	});
	$("#finan1").click(function(){
		var key = $("#number").val();
		var num = repl(key)
		location.href="${path}/search_stock/finan1.do?num="+num;
	});
	
	
function repl(key){
	var size = key.length;
	if(size>6){
		alert("잘못된 종목번호입니다.");
		$("#number").focus();
		return $("#number")
	}
	else if(size<6){
		var zeros ="";
		for (i=0; i<6-size; i++){
			zeros="0"+zeros;
		}
		key = zeros+key;
	}
	return key
}	

	});


		
</script>
  <div class="modal fade" id="search_stock" >
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- header -->
      <div class="modal-header">
        <!-- 닫기(x) 버튼 -->
        <button type="button" class="close" data-dismiss="modal">×</button>
        <!-- header title -->
        <h4 class="modal-title">주식정보검색하기</h4>
        
      </div>
      <!-- body -->
      <div class="modal-body">
           주식번호 <input type="number" id = number name= number>
       <button type="button" id="disInfo" class="stock_num" data-dismiss="modal">disInfo</button>
       <button type="button" id="financialInfo" class="stock_num" data-dismiss="modal">financialInfo</button>
       <button type="button" id="stockInfo" class="stock_num" data-dismiss="modal" >stockInfo</button>
       <button type="button" id="finan1" class="stock_num" data-dismiss="modal">finan1</button>
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        Footer
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
