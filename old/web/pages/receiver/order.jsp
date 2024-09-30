<%--
  Created by IntelliJ IDEA.
  User: SunShine
  Date: 2023/11/9
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>接单广场</title>
    <script src="/js/vue.js"></script><!--先引入引入vue.js-->
    <script src="/js/index.js"></script>
    <script src="/js/axios.js"></script>
    <link rel="stylesheet" href="/css/index.css">

</head>
<body>
<div id="box">

    <el-table :data="orderList" style="width: 100%" @selection-change="ChangeFun">
        <el-table-column prop="date" label="订单日期"></el-table-column>
        <el-table-column prop="task" label="订单详细"></el-table-column>
        <el-table-column prop="price" label="订单价格"></el-table-column>
        <el-table-column  label="订单状态">
            <template slot-scope="scope">
                <i class="el-icon-close" v-if="scope.row.state==0"></i>
                <i class="el-icon-finished" v-if="scope.row.state==1"></i>
            </template>
        </el-table-column>

        <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
                <el-button type="text" size="medium" @click="rec(scope.row.id)">接单</el-button>
            </template>
        </el-table-column>
    </el-table>

    <%--        提示框  :visible.sync控制弹出框是否打开--%>
    <el-dialog title="提示" :visible.sync="recVisible" width="30%">
        <span>确定接单吗</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="recVisible = false">取 消</el-button>
    <el-button type="primary" @click="recType">确 定</el-button>
  </span>
    </el-dialog>
    <input type="hidden" id="sessionName" value="${username}" />

    <el-pagination
            @size-change="handleSizeChange"<%--    切换一页多少条触发        --%>
            @current-change="handleCurrentChange"<%--    切换页码触发        --%>
            :current-page="currentPage" <%--  当前的页码数  --%>
            :page-sizes="[5, 10, 15, 20]"<%--一页显示多少条--%>
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>

</div>

<script>
    new Vue({
        el: "#box",
        data: {
            recVisible:false,
            total: 0,//数据总量
            currentPage: 1,//当前页码
            pageSize: 5,//一页显示多少条
            orderList:[],
            rid:"",

        },
        methods: {
            handleSizeChange: function (val) {
                this.pageSize = val
                this.receiverSelectAllOrder();
            },
            //当前页码
            handleCurrentChange: function (val) {
                this.currentPage = val;
                this.receiverSelectAllOrder();
            },
            receiverSelectAllOrder: function () {
                var currentPage = this.currentPage;
                var pageSize = this.pageSize
                var that = this;//相当于把data中的属性，定义为全局变量
                axios.get("/receiverSelectAllOrder?currentPage=" + currentPage + "&pageSize=" + pageSize).then(function (res) {
                    if (res.data.status) {
                        that.orderList = res.data.list;//将查询到的集合绑定给变量
                        that.total = res.data.total;

                        console.log(that.orderList)
                    } else {
                        //axios中提供报错的形式
                        that.$message.error(res.data.message)
                    }
                })
            },
            rec: function (rid,) {
                this.recVisible = true;
                this.rid = rid;

            },
            recType: function () {
                var rid = this.rid;
                var that = this;
                var receiver = document.getElementById("sessionName").value;
                //发送请求
                axios.get("/recOrderSerclet?rid=" +rid+"&receiver="+ receiver).then(function (res) {

                    if (res.data.status) {
                        that.receiverSelectAllOrder();
                        that.recVisible = false;
                    } else {
                        //axios中提供报错的形式
                        that.$message.error(res.data.message)
                    }
                })
            },
        },
        mounted: function () {
        this.receiverSelectAllOrder();
    }

    })
</script>
</body>
</html>
