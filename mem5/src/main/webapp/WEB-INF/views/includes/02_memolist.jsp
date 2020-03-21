<div class="bg-light border-right" id="memolist-wrapper">
   	<div class="list-group-item list-group-item-action bg-light memo-heading">
   		<input type="text" placeholder="Search" id="searchMemoInput" onkeyup="memoSearch()"/> 
		<a onclick="memoType();" class="addMemo"><i class="fas fa-plus"></i></a>
	</div>
    <div class="list-group list-group-flush" id="memoList" style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
      <!-- add memo -->
    </div>
</div>