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
    <title>我的订单</title>
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
                <el-button type="text" size="medium" @click="back(scope.row.id)">退单</el-button>
            </template>
        </el-table-column>
    </el-table>

    <%--        提示框  :visible.sync控制弹出框是否打开--%>
    <el-dialog title="提示" :visible.sync="backVisible" width="30%">
        <span>确定退单吗</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="backVisible = false">取 消</el-button>
    <el-button type="primary" @click="backType">确 定</el-button>
  </span>
    </el-dialog>
    <input type="hidden" id="sessionName" value="${username}" />

</div>

<script>
    new Vue({
        el: "#box",
        data: {
            backVisible:false,
            rid:"",
            orderList:[
            ],
            receiver:""

        },
        methods: {
            receiverSelectMyOrder: function () {
                //预加载函数在页面启动后开始执行，赋值完毕
                //请求：请求需要处理大量的数据，当请求完成时，预加载函数已经加载完毕。预加载函数>请求
                //this相当于局部变量
                var that = this;//把this定义成全局变量
                var receiver = document.getElementById("sessionName").value;
                axios.get("/receiverSelectMyOrder?receiver="+receiver).then(function (res) {
                    if (res.data.status) {
                        that.orderList = res.data.list;//将查询到的集合绑定给变量

                        console.log(that.orderList)
                    } else {
                        //axios中提供报错的形式
                        that.$message.error(res.data.message)
                    }
                })
            },
            back: function (rid) {
                this.backVisible = true;
                this.rid = rid;
            },
            backType: function () {
                var rid = this.rid;
                var that = this;
                //发送请求
                axios.get("/backOrderSerclet?rid=" +rid).then(function (res) {

                    if (res.data.status) {
                        that.receiverSelectMyOrder();
                        that.backVisible = false;
                    } else {
                        //axios中提供报错的形式
                        that.$message.error(res.data.message)
                    }
                })
            },
        },
        mounted: function () {
            this.receiverSelectMyOrder();
        }

    })
</script>
</body>
</html>
