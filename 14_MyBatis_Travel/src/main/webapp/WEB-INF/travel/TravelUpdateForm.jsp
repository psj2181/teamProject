<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
TravelUpdateForm.jsp<br>

<%
	String[] areaArr = {"유럽","동남아","일본","중국"};
	pageContext.setAttribute("areaArr", areaArr);
	
	String[] styleArr = {"패키지","크루즈","자유여행","골프여행"};
	
	String[] priceArr = {"100~200","200~300","300~400","400~500"};
	pageContext.setAttribute("priceArr", priceArr);
%>

<style type="text/css">
	.err{
		font-size: 10pt;
		color: red;
		font-weight: bold;
	}
</style>

<h1>상품 수정 화면</h1>
<form:form commandName="travel" action="update.tv" method="post">
<input type="hidden" name="num" value="${travel.num }">
<table border="1">
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name="name" value="${travel.name }">
			<form:errors cssClass="err" path="name"/>
		</td>
	</tr>
	<tr>
		<td>나이</td>
		<td>
			<input type="text" name="age" value="${travel.age }">
			<form:errors cssClass="err" path="age"/>
		</td>
	</tr>
	<tr>
		<td>지역</td>
		<td>
			<%-- <input type="checkbox" name="area" value="유럽" <c:if test="${travel.area.contains('유럽')}">checked</c:if>>유럽
			<input type="checkbox" name="area" value="동남아" <c:if test="${travel.area.contains('동남아')}">checked</c:if>>동남아
			<input type="checkbox" name="area" value="일본" <c:if test="${travel.area.contains('일본')}">checked</c:if>>일본
			<input type="checkbox" name="area" value="중국" <c:if test="${travel.area.contains('중국')}">checked</c:if>>중국 --%>
			<c:forEach var="area" items="${areaArr }">
				<input type="checkbox" name="area" value="${area}" <c:if test="${fn:contains(travel.area, area)}">checked</c:if> > ${area}
			</c:forEach>
			<form:errors cssClass="err" path="area"/>
		</td>
	</tr>
	<tr>
		<td>여행 타입</td>
		<td>
			<%-- <input type="radio" name="style" value="패키지" <c:if test="${travel.style == '패키지' }">checked</c:if>>패키지
			<input type="radio" name="style" value="크루즈" <c:if test="${travel.style == '크루즈' }">checked</c:if>>크루즈
			<input type="radio" name="style" value="자유여행" <c:if test="${travel.style == '자유여행' }">checked</c:if>>자유여행
			<input type="radio" name="style" value="골프여행" <c:if test="${travel.style == '골프여행' }">checked</c:if>>골프여행 --%>
			<c:forEach var="style" items="<%=styleArr %>">
				<input type="radio" name="style" value="${style }" <c:if test="${fn:contains(travel.style,style) }">checked</c:if>>${style }
			</c:forEach>
			<form:errors cssClass="err" path="style"/>
		</td>
	</tr>
	<tr>
		<td>가격</td>
		<td>
			<select name="price">
				<option value="">선택하세요</option>
				<%-- <option value="100~200" <c:if test="${travel.price == '100~200' }">selected</c:if>>100~200</option>
				<option value="200~300" <c:if test="${travel.price == '200~300' }">selected</c:if>>200~300</option>
				<option value="300~400" <c:if test="${travel.price == '300~400' }">selected</c:if>>300~400</option>
				<option value="400~500" <c:if test="${travel.price == '400~500' }">selected</c:if>>400~500</option> --%>
				<c:forEach var="pr" items="${priceArr }">
					<option value="${pr}" <c:if test="${travel.price == pr}">selected</c:if>>${pr}</option>
				</c:forEach>
			</select>
			<form:errors cssClass="err" path="price"/>
		</td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<input type="submit" value="수정하기">
		</td>
	</tr>
</table>
</form:form>
