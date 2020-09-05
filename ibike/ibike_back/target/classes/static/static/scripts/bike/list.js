$(function() {
	$('.table-sort').DataTable({
				order : [ [ 1, 'asc' ] ],
				ajax : {
					url : "/searchBike",
					type : 'GET',
				},
				columns : [ 
                   {
					data : "data.obj.bid"
				}, {
					data : "data.obj.status",
					defaultContent : ""
				}, {
					data : "data.obj.latitude",
					defaultContent : ""
				}, {
					data : "data.obj.longitude",
					defaultContent : ""
				}],
				columnDefs : [{
					targets : [ 0 ],
					orderable : false,
					render : function(id, type, row, meta) {
						return '<input id="input-' + id
								+ '" type="checkbox" name="ids" value=' + id
								+ '><label for="input-' + id + '"></label>';
					}
				}, {
                    targets: [5],
                    render: function(data, type, row, meta) {
                        return '<a title="编辑" href="javascript:;" onclick="bike_edit('+ data +')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a><a title="删除" href="javascript:;" onclick="bike_del(' + data +')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>'
                    }
                }]
			});
});
