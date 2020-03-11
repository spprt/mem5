<c:forEach items="${list}" var="item">
	 <a href="#" data-id="${item.id}" class="list-group-item list-group-item-action bg-light">${item.title}</a>
</c:forEach>