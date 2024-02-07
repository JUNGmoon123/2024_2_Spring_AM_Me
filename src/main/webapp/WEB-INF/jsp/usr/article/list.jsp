<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="#{board.code } ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto overflow-x-auto">
		<div class="badge badge-outline">${articlesCount }개</div>
		<table class="table-box-1 table" border="1">
			<colgroup>
				<col style="width: 10%" />
				<col style="width: 20%" />
				<col style="width: 60%" />
				<col style="width: 10%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="article" items="${articles }">
					<tr class="hover">
						<td>${article.id }</td>
						<td>${article.regDate.substring(0,10) }</td>
						<td>
							<a href="detail?id=${article.id }">${article.title }</a>
						</td>
						<td>${article.extra__writer }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 	검색기능 -->
	<form method="post" name="search" action="">
		<table class="pull-right">
			<tr>
				<td>
					<select class="select select-warning w-full max-w-xs">
						<option disabled selected>선택</option>
						<option>제목</option>
						<option>작성자</option>
						<option>내용</option>
					</select>
				</td>
<%-- 				<c:set var="${article.TypeCode}" /> --%>
				<!-- 조건문 시작 -->
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${article.searchKeyword} == ${article.TypeCode}"> --%>
<%-- 						<option value="${article.searchKeyword }">제목</option> --%>
<%-- 					</c:when> --%>
<%-- 					<c:when test="${article.searchKeyword} == ${article.TypeCode }"> --%>
<%-- 						<option value="${article.searchKeyword }">내용</option> --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
				<td>
					<input type="text" placeholder="검색어 입력" class="input input-bordered input-warning w-full max-w-xs" />
				</td>
				<td>
					<button type="submit" class="btn btn-success">검색</button>
				</td>
			</tr>

		</table>
	</form>


	<!-- 	동적 페이징 -->
	<div class="pagination flex justify-center mt-3">
		<c:set var="paginationLen" value="3" />
		<c:set var="startPage" value="${page -  paginationLen  >= 1 ? page - paginationLen : 1}" />
		<c:set var="endPage" value="${page +  paginationLen  <= pagesCount ? page + paginationLen : pagesCount}" />

		<c:if test="${startPage > 1 }">
			<a class="btn btn-sm" href="?page=1&boardId=${boardId }">1</a>
			<button class="btn btn-sm btn-disabled">...</button>
		</c:if>

		<c:forEach begin="${startPage }" end="${endPage }" var="i">
			<a class="btn btn-sm ${param.page == i ? 'btn-active' : '' }" href="?page=${i }&boardId=${boardId}">${i }</a>
		</c:forEach>

		<c:if test="${endPage < pagesCount }">
			<button class="btn btn-sm btn-disabled">...</button>
			<a class="btn btn-sm" href="?page=${pagesCount }&boardId=${boardId }">${pagesCount }</a>
		</c:if>

	</div>

	<!-- 	원래 페이징 -->
	<div class="pagination flex justify-center mt-3">
		<div class="btn-group">
			<c:forEach begin="1" end="${pagesCount }" var="i">
				<a class="btn btn-sm ${param.page == i ? 'btn-active' : '' }" href="?page=${i }&boardId=${param.boardId}">${i }</a>
			</c:forEach>
		</div>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>