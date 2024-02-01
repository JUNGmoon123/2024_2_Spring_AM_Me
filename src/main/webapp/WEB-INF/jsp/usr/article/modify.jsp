<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<table class="table-box-1" border="1">
			<tbody>
				<tr>
					<th>제목</th>
					<td>${article.title }</td>
				</tr>
				<tr>
					<th>기존내용</th>
					<td>${article.body }</td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
<div class="btns">
	<button class="hover:underline" type="button" onclick="history.back();">뒤로가기</button>
</div>
<%@ include file="../common/foot.jspf"%>