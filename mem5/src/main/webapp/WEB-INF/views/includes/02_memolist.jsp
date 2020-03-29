<div class="bg-light border-right" id="memolist-wrapper">
   	<div class="list-group-item list-group-item-action bg-light memo-heading" id="memoHeader">
   		<input type="text" placeholder="Search" id="searchMemoInput" onkeyup="memoSearch()"/> 
		<a onclick="memoType();" class="addMemo"><i class="fas fa-plus"></i></a>
	</div>
    <div class="list-group list-group-flush" >
   		<div id="memoSort" class="list-group-item list-group-item-action bg-light" style="cursor: auto;">
   		<select id="orderSelect" class="selectpicker" onchange="javascript:memoSort(true);">
		  <option value="1">Updated</option>
		  <option value="2">Created</option>
		  <option value="3">Alphabetically</option>
		</select>
		<a id="memoSortUp" onclick="javascript:memoSort(true);">
    		<i class="fas fa-chevron-up"></i>
		</a>
   		<a id="memoSortDown" onclick="javascript:memoSort(false);">
    		<i class="fas fa-chevron-down"></i>
   		</a>
   		</div>
    	<div id="memoList">
	      <!-- add memo -->
    	</div>
    </div>
</div>
<script type="text/javascript">
function memoSort(isAsc) {
	$("#memoList").html(
			$(".memoItem").sort(function(a, b){
				const option = document.getElementById("orderSelect").value;
				switch(option) {
					case '1': 
						var aVal = $(a).data('moddate');
						var bVal = $(b).data('moddate');
						
						if(isAsc) {
							return aVal - bVal;
						} else {
							return bVal - aVal;
						}
						break;
					case '2':
						var aVal = $(a).data('regdate');
						var bVal = $(b).data('regdate');
						console.log('aVal', aVal)
						
						if(isAsc) {
							return aVal - bVal;
						} else {
							return bVal - aVal;
						}
						break;
					case '3':
						var aVal = $(a).data('title').toLowerCase();
						var bVal = $(b).data('title').toLowerCase();
						
						if (isAsc) {
							return aVal < bVal ? -1 : aVal > bVal ? 1 : 0;
						} else {
							return aVal > bVal ? -1 : aVal < bVal ? 1 : 0;
						}
						break;
				}
			})
	);
}
</script>