      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom" id="rightHeader">
        <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
<!--             <li class="nav-item active"> -->
<%--               <a class="nav-link" href="${pageContext.request.contextPath }/">Home <span class="sr-only">(current)</span></a> --%>
<!--             </li> -->
            <c:catch>
				<c:choose>
					<c:when test="${empty authInfo}">
					<li class="nav-item active">
		              <a class="nav-link" href="${pageContext.request.contextPath }/login">Sign in</a>
		            </li>
					</c:when>
					<c:otherwise>
					<li class="nav-item">
		              <a class="nav-link" href="${pageContext.request.contextPath }/mypage">${authInfo.name}</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="javascript:signOut();">Sign out</a>
		            </li>
					</c:otherwise>
				</c:choose>
			</c:catch>
            
<!--             <li class="nav-item dropdown"> -->
<!--               <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!--                 Dropdown -->
<!--               </a> -->
<!--               <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown"> -->
<!--                 <a class="dropdown-item" href="#">Action</a> -->
<!--                 <a class="dropdown-item" href="#">Another action</a> -->
<!--                 <div class="dropdown-divider"></div> -->
<!--                 <a class="dropdown-item" href="#">Something else here</a> -->
<!--               </div> -->
<!--             </li> -->
          </ul>
        </div>
      </nav>