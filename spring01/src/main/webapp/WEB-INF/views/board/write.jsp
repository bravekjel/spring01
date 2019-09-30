<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<%-- <%@ include file="../include/session_check.jsp" %> --%>
<script type="text/javascript" src="${path}/include/js/common.js"></script>
<!-- ckeditor include -->
<script src="${path}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		$(".fileDrop").on("dragenter dragover", function(e) {
			e.preventDefault(); //기본효과 막음
		});
		$(".fileDrop")
				.on(
						"drop",
						function(e) {
							e.preventDefault(); //기본 효과 막음
							//드래그한 파일들
							var files = e.originalEvent.dataTransfer.files;
							//console.log(files);
							var file = files[0]; //첫번째 첨부파일
							var formData = new FormData(); //폼 데이터 객체
							formData.append("file", file); //첨부파일 추가
							// processData: false : header가 아닌 body로 전달
							$
									.ajax({
										url : "${path}/upload/uploadAjax",
										data : formData,
										dataType : "text",
										processData : false,
										contentType : false,
										type : "post",
										success : function(data) {
											console.log(data);
											//첨부파일의 정보
											var fileInfo = getFileInfo(data);
											//하이퍼링크
											var html = "<a href='"+fileInfo.getLink+"'>"
													+ fileInfo.fileName
													+ "</a><br>";
											//hidden 태그 추가
											html += "<input type='hidden' name='files' value='"+fileInfo.fullName+"'>";
											//div에 추가
											$("#uploadedList").append(html);
										}
									});
						});
		$("#btnSave").click(function() {
			var content = document.form1.content.value;
			var title = document.form1.title.value;
			//var title=$("#title").val();
			/* if(content==""){
			 alert("내용을 입력하세요");
			 document.form1.content.focus();
			 return;
			 } */
			if (title == "") {
				alert("제목을 입력하세요");
				document.form1.title.focus();
				return;
			}
			 
			document.form1.action="${path}/board/insert.do"; 
			document.form1.submit();
		});
	});
</script>
<style>
/* 첨부파일을 드래그할 영역의 스타일 */
.fileDrop {
	width: 600px;
	height: 100px;
	border: 1px dotted gray;
	background-color: gray;
}
</style>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<h2>게시물 작성</h2>
	<form id="form1" name="form1" method="post" action="${path}/board/insert.do">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div>
			제목 <input name="title" id="title" size="80" placeholder="제목을 입력하세요">
		</div>
		<div style="width: 800px;">
			내용
			<textarea id="content" name="content" rows="5" cols="80"
				placeholder="내용을 입력하세요"></textarea>
			<!-- textarea를 스마트에디터로 변경 -->
			<script>
				//CKEDITOR.replace("content"); // 태그의 id
				//이미지 업로드를 할 경우
				CKEDITOR.replace("content", {
					filebrowserUploadUrl : "${path}/imageUpload.do"
				});
			</script>
		</div>
		<div>
			첨부파일 등록
			<div class="fileDrop"></div>
			<!-- 첨부파일 목록 -->
			<div id="uploadedList"></div>
		</div>
		 <div>
이름
<input name="writer" placeholder="이름을 입력하세요">
</div>
		<div style="width: 700px; text-align: center;">
			<button type="button" id="btnSave">확인</button>
		</div>
	</form>
</body>
</html>
