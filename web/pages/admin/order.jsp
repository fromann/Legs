<%--
  Created by IntelliJ IDEA.
  User: SunShine
  Date: 2023/11/9
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/js/vue.js"></script><!--先引入引入vue.js-->
    <script src="/js/index.js"></script>
    <script src="/js/axios.js"></script>
    <link rel="stylesheet" href="/css/index.css">
    <title>校园跑腿——By：108Club</title>
</head>
<body>
<div id="box">
    <el-dialog title="删除此订单数据" :visible.sync="delOrderVis" width="50%">
        <span>确定删除吗</span>
        <span slot="footer" class="dialog-footer">
                <el-button @click="delOrderVis = false">取 消</el-button>
                <el-button type="primary" @click="delOrder">确 定</el-button>
            </span>
    </el-dialog>


    <el-dialog title="编辑提示框" :visible.sync="editOrderVis" width="53%">
        <el-form :model="order" label-width="100px">
            <el-form-item label="订单详情">
                <el-input v-model="order.task" width="319px"></el-input>
            </el-form-item>
            <el-form-item label="订单价格">
                <el-input v-model="order.price" width="319px"></el-input>
            </el-form-item>
            <el-form-item label="订单状态">
                <el-input v-model="order.state" width="319px"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="editOrderVis = false">取 消</el-button>
        <el-button type="primary" @click="editOrder">确 定</el-button>
        </span>
    </el-dialog>


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
                <el-button type="text" size="small" @click="openEditPanel(scope.row)">编辑</el-button>
                <el-button type="text" size="small" @click="del(scope.row.id)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>

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
            delOrderVis:false,
            addOrderVis: false,
            editOrderVis:false,
            order:{
                task:"",
                price:"",
                state:"",
            },
            orderList: [],
            total: 0,//数据总量
            currentPage: 1,//当前页码
            pageSize: 5,//一页显示多少条
            id:""

        },
        methods: {
            handleSizeChange: function (val) {
                this.pageSize = val
                this.selectAllOrder();
            },
            //当前页码
            handleCurrentChange: function (val) {
                this.currentPage = val;
                this.selectAllOrder();
            },

            //查询所有订单信息
            selectAllOrder: function () {
                var currentPage = this.currentPage;
                var pageSize = this.pageSize
                var that = this;//相当于把data中的属性，定义为全局变量
                axios.get("/adminSelectAllOrder?currentPage=" + currentPage + "&pageSize=" + pageSize).then(function (res) {
                    if (res.data.status) {
                        that.orderList = res.data.list;//对typeList属性进行赋值
                        that.total = res.data.total;
                        //  this.total
                        // var that = this
                        // that.total  =this.total
                    } else {
                        that.$message.error(res.data.message)
                    }
                })
            },
            //点击删除按钮
            del: function (id) {
                this.id = id
                this.delOrderVis = true;
            },
            //点击删除提示框中的确定按钮
            delOrder: function () {
                var id = this.id;
                var that = this;
                axios.get("/adminDelOrder?id=" + id).then(function (res) {
                    if (res.data.status) {
                        that.delOrderVis = false;
                        that.selectAllOrder();
                    } else {
                        that.$message.error(res.data.message)
                    }
                })
            },
            //编辑订单
            openEditPanel:function (order) {
                this.editOrderVis = true;
                this.older = order;
            },
            editOrder: function () {
                var id = this.older.id;
                var task =this.order.task;
                var price = this.order.price;
                var state = this.order.state;
                var that = this;
                axios.get("/adminEditOrderController?id=" + id + "&task=" + task + "&price=" + price +"&state=" + state).then(function (res) {
                    if (res.data.status) {
                        that.editOrderVis = false;
                        //刷新
                        that.selectAllOrder();
                    } else {
                        alert(res.data.message)
                    }
                })
            },
        },
        mounted: function () {
            this.selectAllOrder();
        }
    })
</script>
</body>
</html>
