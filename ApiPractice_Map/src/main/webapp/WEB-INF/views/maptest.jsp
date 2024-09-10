<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>카카오 맵 사용하기</h2>

<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a3a11c56ba7572d124d51d25132354db&libraries=services,clusterer,drawing"></script>
	<script>
		var container = document.getElementById('map');
		var baseLongitude = 127.00225226116126;
		var baseLatitude = 37.58169863717817;
		/* var longitude = 127.01049296509703;
		var latitude = 37.571417966123946; */
		
		var options = {
			center: new kakao.maps.LatLng(baseLatitude, baseLongitude),
			level: 4
		};

		var map = new kakao.maps.Map(container, options);
		
		var markerPosition  = new kakao.maps.LatLng(${latitude}, ${longitude}); 
		
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		marker.setMap(map);
	</script>
<br>
</body>
</html>