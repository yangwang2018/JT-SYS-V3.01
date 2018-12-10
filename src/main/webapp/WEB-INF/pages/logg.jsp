<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Ajax</h1>
	<script type="text/javascript">
		window.onload=function(){
			doGetObject();
		}
		function doGetObject(){
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){ //callback
				if(xhr.readyState==4 && xhr.status==200){
					console.log(xhr.responseText);
					doHandleResponseResult(xhr.responseText);
				}
			}
			var url = "doFindPageObjects?name=admin&pageCurrent=1";
			xhr.open("GET", url, true);//true 表示异步,false表示同步
			xhr.send();//GET请求send方法内部不传数据

		}
		function doHandleResponseResult(result){
			var result=JSON.parse(result);
	    	console.log(result);
		}
	</script>
</body>
</html>