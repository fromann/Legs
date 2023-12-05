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
    <%--下达订单按钮--%>
    <el-button type="primary" @click="openAddPanel" round>下达订单</el-button>
    <%--   新增提示框--%>
    <el-dialog title="下达订单信息框" :visible.sync="addOrderVis" width="50%">
        <%--信息 ： 表单--%>
        <el-form :model="order" label-width="80px">
            <el-form-item label="订单详情">
                <el-input v-model="order.task" width="300px">
                </el-input>
            </el-form-item>

            <el-form-item label="订单价格">
                <el-input v-model="order.price" width="300px">
                </el-input>
            </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
    <el-button @click="addOrderVis = false">取 消</el-button>
    <el-button type="primary" @click="createOrder">确 定</el-button>
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

            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="editOrderVis = false">取 消</el-button>
        <el-button type="primary" @click="editOrder">确 定</el-button>
        </span>
    </el-dialog>
    <el-dialog title="删除此订单数据" :visible.sync="delVisible" width="50%">
            <span>确定删除吗</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="delOrder">确 定</el-button>
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
                <el-button type="text" size="small" @click="openDelPanel(scope.row.id)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
        <input type="hidden" id="sessionName" value="${username}" />
</div>

<script>
    new Vue({
        el: "#box",
        data: {
            order:{
                task:"",
                price:"",
                state:"",
            },
            addOrderVis: false,
            editOrderVis:false,
            orderList:[],
            id:"",
            delVisible:false,
        },
        methods: {
            senderSelectAllOrder: function () {
                //预加载函数在页面启动后开始执行，赋值完毕
                //请求：请求需要处理大量的数据，当请求完成时，预加载函数已经加载完毕。预加载函数>请求
                //this相当于局部变量
                var that = this;//把this定义成全局变量
                // 获取session里面的username 并且获得user.getusername
                var sender = document.getElementById("sessionName").value;
                axios.get("/senderSelectAllOrder?sender="+sender).then(function (res) {
                    if (res.data.status) {
                        that.orderList = res.data.list;//将查询到的集合绑定给变量
                    } else {
                        //axios中提供报错的形式
                        that.$message.error(res.data.message)
                    }
                })
            },
            openDelPanel: function (id) {
                this.id = id
                this.delVisible=true
            },
            delOrder: function () {
                var id = this.id;
                var that = this;
                //发送请求
                axios.get("/senderDelOrder?id=" + id).then(function (res) {
                    if (res.data.status) {
                        that.senderSelectAllOrder();
                        that.delVisible = false;
                    } else {
                        //axios中提供报错的形式
                        that.$message.error(res.data.message)
                    }
                })
            },

            //添加订单（成功）
            openAddPanel: function () {
                this.addOrderVis = true;
                this.order.task = "";//清空上一次输入框中的内容
                this.order.price = "";
                this.order.state = "";
            },
            createOrder: function () {
                var task =this.order.task;
                var price = this.order.price;
                var sender = document.getElementById("sessionName").value;
                // var sender = "123"
                var that = this;
                axios("/AddOrderServlet?task="+task+"&price="+price+"&sender="+sender).then(function (res) {
                    if (res.data.status) {
                        //把弹出框关闭
                        that.addOrderVis = false;
                        //添加查询方法
                        that.senderSelectAllOrder();
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
                var that = this;
                axios.get("/senderEditOrderController?id=" + id + "&task=" + task + "&price=" + price).then(function (res) {
                    if (res.data.status) {
                        that.editOrderVis = false;
                        //刷新
                        that.senderSelectAllOrder();
                    } else {
                        alert(res.data.message)
                    }
                })
            },

        },
        mounted: function () {
            this.senderSelectAllOrder();
        }

    })
</script>
</body>
</html>
