<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="module">
import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.12.4/firebase-app.js'
import { getFirestore, collection, getDocs, setDoc, doc } 
	from 'https://www.gstatic.com/firebasejs/10.12.4/firebase-firestore.js'

  var firebaseConfig = {
    apiKey: "AIzaSyBZIH5I2XcO5bDJa3rG0r5rn2xcFiWo7Fg",
    authDomain: "springbootstudy-57416.firebaseapp.com",
    databaseURL: "https://springbootstudy-57416-default-rtdb.firebaseio.com",
    projectId: "springbootstudy-57416",
    storageBucket: "springbootstudy-57416.appspot.com",
    messagingSenderId: "862961602481",
    appId: "1:862961602481:web:cf2b4d5d3ae359f2047790"
  };

var app = initializeApp(firebaseConfig);
	var db = getFirestore(app);

async function writeDoc() {
  var email = $('#email').val();
  var pwd = $('#pwd').val();

  var postData = {
    email: email,
    pw : pwd
  };

  var timeElapsed = Date.now();
  var newRef = doc(db, 'members', 'member' + timeElapsed);

  await setDoc(newRef, postData);
}

async function selectDoc() {
  $('#chatMessageArea').empty();

  const snapshot = await getDocs(collection(db, "members"));
  snapshot.forEach((doc) => {
    var doc_id = doc.id;
    var data = doc.data();
    console.log(doc_id + " - " + data.email + " : " + data.pw);
    appendMessage(data.email + " : " + data.pw);
  });
}

function appendMessage(msg) {
	$('#chatMessageArea').append(msg + "<br>");
	var chatAreaHeight = $('#chatArea').height();
	var maxScroll = $('#chatMessageArea').height() - chatAreaHeight;
	$('#chatArea').scrollTop(maxScroll);
}

$(document).ready(function() {
	console.log(111);
	$('#select').click(function() { selectDoc(); });
	$('#write').click(function() { writeDoc(); });
	
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
  <input type="button" id="select" value="조회"><br><p>

  <div id="chatArea"><div id="chatMessageArea"></div></div>

  <input type="text" id="email">
	<input type="text" id="pwd">
	<input type="button" id="write" value="작성">

</body>
</html>