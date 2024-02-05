<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/doModify" method="POST">
			<table class="table-box-1" border="1">
				<tbody>
					<tr>
						<th>작성날짜</th>
						<td>${article.regDate }</td>
					</tr>
					<tr>
						<th>수정날짜</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.extra__writer }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input autocomplete="off" type="text" placeholder="수정할 제목" name="title" />
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<input autocomplete="off" type="text" placeholder="수정할 내용" name="body" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns">
			<button class="hover:underline" type="button" onclick="history.back();">뒤로가기</button>
			<a href="../article/modify?id=${article.id }">수정</a>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>