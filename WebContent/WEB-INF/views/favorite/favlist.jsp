<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>お気に入り日報 一覧</h2>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>

                </tr>
                <c:forEach var="favorite" items="${favoritelist}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out
                                value="${favorite.report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate
                                value='${favorite.report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${favorite.report.title}</td>
                        <td class="report_action"><a
                            href="<c:url value='/reports/show?id=${favorite.report.id}' />">詳細を見る</a></td>

                    </tr>
                </c:forEach>
            </tbody>

            <c:forEach var="favorite" items="${favorites}">
                <li><c:out value="${favorite.id}" /> ：<c:out
                        value="${favorite.report}"></c:out> &gt; <c:out
                        value="${favorite.report}" /></li>
            </c:forEach>

        </table>
        <div id="pagination">
            （全 ${favorite_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((favorite_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/favorite/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p>
            <a href="<c:url value="/favorite/index" />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>