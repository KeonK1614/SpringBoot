<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="module">
import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js'
import { getDatabase, ref, onValue, set, child, push, onChildAdded, query, limitToLast } 
	from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-database.js'

  var firebaseConfig = {
    apiKey: "",
    authDomain: "springbootstudy-57416.firebaseapp.com",
    databaseURL: "https://springbootstudy-57416-default-rtdb.firebaseio.com",
    projectId: "springbootstudy-57416",
    storageBucket: "springbootstudy-57416.appspot.com",
    messagingSenderId: "862961602481",
    appId: "1:862961602481:web:cf2b4d5d3ae359f2047790"
  };

 	var app = initializeApp(firebaseConfig);
	var db = getDatabase(app);
	var roomName;
	var userName;

function connect() {
	roomName = $("#roomName").val();
	userName = $("#userName").val();

	var dbRef = ref(db, 'chat/' + roomName);
	var lastDbRef = query(dbRef, limitToLast(1));

	onChildAdded(lastDbRef, (data) => {
		var name = data.val().userName;
		var msg = data.val().message;
		
		console.log("[3] " + name + ":" + msg);
		appendMessage(name + ":" + msg);
	});
}

function writeNewPost(roomId, name, msg) { 
	var postData = {
		userName: name,
		message : msg
	};

	var newPostKey = push(child(ref(db), 'chat/' + roomId)).key;
	var newRef = ref(db, 'chat/' + roomId + '/' + newPostKey);

	set(newRef, postData);
}

function send() {
	var msg = $('#message').val();
	writeNewPost(roomName, userName, msg);
}

function appendMessage(msg) {
	$('#chatMessageArea').append(msg + "<br>");
	var chatAreaHeight = $('#chatArea').height();
	var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
	$('#chatArea').scrollTop(maxScroll);
}

$(document).ready(function() {
	console.log(111);
	$('#sendBtn').click(function() { send(); });
	$('#enterBtn').click(function() { connect(); });
	
});

</script>
<style>
#chatArea {
	width: 200px;
	height: 100px;
	overflow-y: auto;
	border: 1px solid black;
}
</style>
</head>
<body>
	Room Name :
	<input type="text" id="roomName">
	<br> User Name :
	<input type="text" id="userName">
	<br>
	<input type="button" id="enterBtn" value="입장">

	<h1>대화 영역</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	<br>

	<input type="text" id="message">
	<input type="button" id="sendBtn" value="전송">

</body>
</html>