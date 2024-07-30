<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>NaverEditor in JSP</h1>

	<script type="text/javascript"
		src="./naver-editor/js/service/HuskyEZCreator.js" charset="UTF-8"></script>

	<form name="myeditor" id="myeditor" action="naver_submit" method=post>
		<textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
		<button onclick="form_check();">작성완료</button>
	</form>

	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "ir1",
			sSkinURI : "./naver-editor/SmartEditor2Skin.html",
			fCreator : "createSEditor2"
		});

		function form_check() {
			console.log(document.getElementById("ir1").value);
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			console.log(document.getElementById("ir1").value);

			document.myeditor.submit();
		}
	</script>


</body>
</html>