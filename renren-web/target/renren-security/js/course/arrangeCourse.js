$(function () {
    $("#jqGrid").jqGrid({
        url: '../arrclass/allweek?year=' + parent.vm.course.year + '&quarter=' + parent.vm.course.quarter,
        datatype: "json",
        colModel: [
            {
                label: '时间', name: 'classtimeName', width: 20,
                formatter: function (value, options, row) {
                    return row.classtimeName + '<br>(' + row.startTime + '~' + row.endTime + ')'
                }
            },
            {
                label: '星期一', name: 'mon', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            },
            {
                label: '星期二', name: 'tues', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            },
            {
                label: '星期三', name: 'wed', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            },
            {
                label: '星期四', name: 'thur', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            },
            {
                label: '星期五', name: 'fri', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            },
            {
                label: '星期六', name: 'sat', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            },
            {
                label: '星期日', name: 'sun', width: 20,
                formatter: function (value, options, row) {
                    if (value <= 0) {
                        return '剩余0间教室'
                    }
                    return '<button onclick=\'vm.selectClassroom("' + row.classtimeId + '","' + options.colModel.name + '")\''
                        + 'class="layui-btn layui-btn-radius layui-btn-small">选择教室</button>'
                        + '<br>剩余<span style="font-size: 20px">' + value + '</span>间教室'
                }
            }
        ],
        viewrecords: true,
        height: 'auto',
        autowidth: false,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        jsonReader: {
            root: "arrClass"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        title: null,
        arrclass: {
            weekId: '',
            courseId:'',
            classroomId: '',
            classtimeId:''
        }

    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        },
        selectClassroom: function (classtimeId, weekday) {
            vm.toFormdata(weekday);
            vm.arrclass.classtimeId = classtimeId;
            vm.arrclass.courseId=parent.vm.course.courseId;
            layer.open({
                type: 2,
                title: '请选择排课教室',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['400px', '400px'],
                content: '../course/arrRoom.html',
                end: function () {
                    //如果arrclass.classroomId不为空，则说明已经选中了教室，而不是直接点击关闭按钮。执行插入排课
                    if(vm.arrclass.classroomId !=""){
                        //添加排课数据
                        debugger;
                        $.ajax({
                            type: "POST",
                            url: '../arrclass/save',
                            data: JSON.stringify(vm.arrclass),
                            success: function(r){
                                if(r.code === 0){
                                    alert('操作成功', function(index){
                                        layer.close(layer.index); //如果设定了yes回调，需进行手工关闭
                                    });
                                }else{
                                    alert(r.msg);
                                    layer.close(layer.index); //如果设定了yes回调，需进行手工关闭
                                }
                            }
                        });

                    }
                }
            });

        },
        toFormdata: function (weekday) {
            switch (weekday) {
                case 'mon':
                    vm.arrclass.weekId = 1
                    break;
                case 'tues':
                    vm.arrclass.weekId = 2
                    break;
                case 'wed':
                    vm.arrclass.weekId = 3
                    break;
                case 'thur':
                    vm.arrclass.weekId = 4
                    break;
                case 'fri':
                    vm.arrclass.weekId = 5
                    break;
                case 'sat':
                    vm.arrclass.weekId = 6
                    break;
                case 'sun':
                    vm.arrclass.weekId = 7
                    break;
            }
        }
    }
});

