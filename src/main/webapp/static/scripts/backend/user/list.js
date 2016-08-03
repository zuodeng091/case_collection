var userFacade = {
    detailsGrid: null, // 数据详情
    url : '/user/findUser',
    query : function() {
        var queryString = userFacade.getParams();
        if(!queryString) {
        	return false;
        }
        // 加载详情
        userFacade.loadDetails(queryString);
    },
    getParams : function() {
        return $("form").serialize();
    },
    // 加载数据详情
    loadDetails : function(params) {
        var _this = this;
        var grid_selector = "#table-data-list";
        var pager_selector = "#table-data-list-pager";
        if(_this.detailsGrid) {
            // 根据搜索条件，重新加载
            $(grid_selector).jqGrid('setGridParam',{
                url : userFacade.url+'?'+params,
                page:1 
            }).trigger("reloadGrid");
        } else {
            // 首次加载
            _this.detailsGrid = $(grid_selector).jqGrid({
                url : userFacade.url+'?'+params,
                datatype : 'json',
                colNames : ['ID', '用户名', '真是姓名', '联系方式','客服类型','账号级别','问题处理','审核权限','状态','修改人','操作'],
                jsonReader : {  
                    root: "data",  
                    page: "curPage",  
                    total: "totalPage",  
                    records: "totalRows"
                },
                prmNames : {page:'curPage',rows:'pageSize', sort: 'sidx',order: 'sort'},
                cmTemplate: {sortable:true},
                colModel : [ {
                    name : 'id',
                    align :'center',
                    width : 40
                }, {
                    name : 'loginName',
                    align :'center',
                    width : 90
                }, {
                    name : 'realName',
                    align :'center',
                    width : 90
                }, {
                    name : 'email',
                    align:'left',
                    formatter : function(cellvalue, options, rowObject) {
                        var str = "邮箱：";
                        if(rowObject.email != null){
                            str += rowObject.email;
                        }
                        str += "<br>手机：";
                        if(rowObject.phoneNum != null){
                            str += rowObject.phoneNum;
                        }
                        return str;
                    }
                }, {
                    name : 'cusLevel',
                    align:'center',
                    formatter : function(cellvalue, options, rowObject) {
                        if(cellvalue == 0){
                            return "非客服";
                        }else if(cellvalue == 1){
                            return "普通";
                        }else if(cellvalue == 2){
                            return "专家";
                        }
                    },
                    width : 90
                }, {
                    name : 'dataLevel',
                    align:'left',
                    formatter : function(cellvalue, options, rowObject) {
                        if(cellvalue == 1){
                            return "一级账号";
                        }else if(cellvalue == 2){
                            return "二级账号";
                        }else if(cellvalue == 3){
                            return "三级账号";
                        }else if(cellvalue == -1){
                            return "系统管理员";
                        }
                    },
                    width : 90
                }, {
                    name : 'isHandling',
                    align:'left',
                    formatter : function(cellvalue, options, rowObject) {
                        var text = "是否处理问题：";
                        if(rowObject.isHandling == 0){
                            text+="否";
                        }else if(rowObject.isHandling == 1){
                            text+="是";
                        }
                        text += "<br>最大处理数量："+rowObject.handlingNum;
                        return text;
                    },
                }, {
                    name : 'hasApprove',
                    align:'center',
                    formatter : function(cellvalue, options, rowObject) {
                        if(cellvalue == 0){
                            return "否";
                        }else if(cellvalue == 1){
                            return "是";
                        }
                    },
                    width : 90
                },{
                    name : 'status',
                    align:'center',
                    formatter : function(cellvalue, options, rowObject) {
                        if(cellvalue == 1){
                            return "正常";
                        }else if(cellvalue == 2){
                            return "禁用";
                        }
                    },
                    width : 50
                }, {
                    name : 'modifier',
                    align:'left',
                    formatter : function(cellvalue, options, rowObject) {
                        return rowObject.modifier + "<br>" + $.dateFormat(rowObject.modifyTime,"yyyy-MM-dd hh:mm:ss");
                    }
                }, {
                    name : "id",
                    formatter : function(cellvalue, options, rowObject) {
                        var retVal = ' <button class="btn btn-minier btn-white btn-default btn-bold" onclick="$.showCommonEditDialog(\'/user/toUpdate?id='+rowObject.id+'\',\'修改\',600,500);">修改</button>';
                        retVal += ' <button class="btn btn-minier btn-white btn-default btn-bold" onclick="deleteUser('+rowObject.id+');">删除</button>';
                        retVal += ' <button class="btn btn-minier btn-white btn-default btn-bold" ';
                        if(rowObject.status == 1){
                            retVal +='onclick="updateStatus('+rowObject.id+',\'禁用\',2);">禁用';
                        }else{
                            retVal +='onclick="updateStatus('+rowObject.id+',\'启用\',1);">启用';
                        }
                        retVal += '</button>';
                        return retVal;
                    }
                }],
                rowNum : 30,
                rowList : [ 10, 30, 50 ],
                pager : pager_selector,
                pagerpos : 'left',
                viewrecords : true,
                height : 'auto',
                loadComplete : function() {
                    var table = this;
                    setTimeout(function() {
                        updatePagerIcons(table);
                    }, 0);
                }
            });
        }
        // 自适应宽度
        $.resizeGrid(grid_selector);
    }
}
function deleteUser(id){
    bootbox.confirm("您确定删除该记录?", function(result) {
        if(result) {
            $.ajax({
                type: "POST",
                url: "/user/delete",
                data: "id="+id
            }).done(function (data) {
                if (data.success) {
                    $.dialog({title: '提示', content: "删除成功", icon: 'success.gif',lock:true ,ok: '确定'});
                    userFacade.query();
                } else {
                    $.dialog({title: '提示', content: data.error, icon: 'error.gif',lock:true, ok: '确定'});
                }
            }).always(function () {
                //$("#submitBtn").removeClass("disabled");
            });
        }
    });
}

function updateStatus(id,text,status){
    bootbox.confirm("您确定"+text+"该记录?", function(result) {
        if(result) {
            $.ajax({
                type: "POST",
                url: "/user/updateStatus",
                data: "id="+id+"&status="+status
            }).done(function (data) {
                if (data.success) {
                    $.dialog({title: '提示', content: "操作成功", icon: 'success.gif',lock:true ,ok: '确定'});
                    userFacade.query();
                } else {
                    $.dialog({title: '提示', content: data.error, icon: 'error.gif',lock:true, ok: '确定'});
                }
            }).always(function () {
                //$("#submitBtn").removeClass("disabled");
            });
        }
    });
}

function addUser(){
    $.showCommonEditDialog("/user/toAdd","新增用户",600,500);
}

$(function() {
    userFacade.query();
    $("#searchBtn").click(userFacade.query);

    $("#addBtn").click(function(){
        addUser()
    });
});