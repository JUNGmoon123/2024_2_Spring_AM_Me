<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<table class="table-box-1" border="1">
			<tbody>
				<tr>
					<th>아이디</th>
					<td><input type="text" placeholder="아이디" />${article.id }</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="text" placeholder="비밀번호" />${article.regDate }</td>
				</tr>
			</tbody>
				 	<input type="submit" id="btn" value="로그인">
		</table>
	</div>
</section>
<div class="btns">
	<button class="hover:underline" type="button" onclick="history.back();">뒤로가기</button>
</div>


<%@ include file="../common/foot.jspf"%>