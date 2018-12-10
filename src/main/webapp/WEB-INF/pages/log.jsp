<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>日志列表页面</h1>
	<h2><%=new java.util.Date()%></h2>
	<div id="containerId">
		<table border="1" cellpadding="2" cellpadding="0" width="100%">
			<tr>
			<thead>
				<tr>
					<th>id</th>
					<th>username</th>
					<th>createdTime</th>
				</tr>
			</thead>
			<tbody id="tbodyId">
				<tr>
					<td colspan="3">数据正在加载中.....</td>
				</tr>
			</tbody>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		//dom event (页面加载完成后执行js函数)
		window.onload = function() {
			doGetObject();
		}
		function doGetObject() {
			//1.创建Ajax请求对象(Ajax应用的入口对象)
			var xhr = new XMLHttpRequest();
			//console.log("xhr",xhr);
			//2.设置状态监听(监听与服务器的通讯状态)
			xhr.onreadystatechange = function() { //callback回调 
				console.log("onreadystatechange", xhr.readyState);
				//4 表示通讯状态结束
				//200表示服务端响应ok
				if (xhr.readyState == 4 && xhr.status == 200) {
					//输出响应结果(xhr.responseText代表服务器响应文本)
					//console.log(xhr.responseText)
					console.log(xhr.responseText);
					doHandleResponseResult(xhr.responseText);

				}
			}
			doSendGetRequest(xhr);
		}
		function doPostRequest(xhr) {
			//3.执行open操作 打开链接
			var url = "doFindPageObjects?name=admin&pageCurrent=1"
			xhr.open("POST", url, true);//true 表示异步,false表示同步
			//post请求必须设置此请求头
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			//4.执行请求发送
			xhr.send("pageCurrent=1");//POST请求sent方法
		}
		function doSendGetRequest(xhr) {
			//3.执行open操作 打开链接
			var url = "doFindPageObjects?name=admin&pageCurrent=1"
			xhr.open("GET", url, true);//true 表示异步,false表示同步
			//4.执行请求发送
			xhr.send();//GET请求send方法内部不传数据
		}

		//处理响应结果
		function doHandleResponseResult(responseText) {			
			//1.获取tBody对象,并清空tBody中内容
			var tBody = document.getElementById("tbodyId");
			tBody.innerHTML = "";
			//2.
			var result = JSON.parse(responseText);
			//console.log("result",result);
			var records = result.data.records;
			//PageObject对象中的records
			for (var i = 0; i < records.length; i++) {
				var tr = document.createElement("tr");
				//2.构建多个td对象,并追加到tr中
				doCreateTds(tr, records[i]);
				//3.将tr追加到tbody中
				tBody.appendChild(tr);
			}
		}
		function doCreateTds(tr, row) {
			var td = document.createElement("td");
			td.innerText = row.id;
			tr.appendChild(td);
			
			var td = document.createElement("td");
			td.innerText=row.username;
			tr.appendChild(td);
				  
			var td=document.createElement("td");
			td.innerText=row.method;
			tr.appendChild(td);

		}
	</script>
</body>