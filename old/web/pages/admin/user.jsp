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
    <el-dialog title="编辑提示框" :visible.sync="editUserVis" width="53%">
        <el-form :model="user" label-width="100px">
            <el-form-item label="用户类型">
                <el-input v-model="user.type" width="319px"></el-input>
            </el-form-item>
            <el-form-item  label="联系方式">
                <el-input v-model="user.phone" width="319px"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="editUserVis = false">取 消</el-button>
        <el-button type="primary" @click="editUser">确 定</el-button>
        </span>
    </el-dialog>

    <el-dialog title="删除此用户数据" :visible.sync="delUserVis" width="50%">
        <span>确定删除吗</span>
        <span slot="footer" class="dialog-footer">
                <el-button @click="delUserVis = false">取 消</el-button>
                <el-button type="primary" @click="delUser">确 定</el-button>
            </span>
    </el-dialog>
    <el-table :data="userList" style="width: 100%" @selection-change="ChangeFun">
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column prop="name" label="用户昵称"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column  label="用户类型">
            <template slot-scope="scope">
                <i class="el-icon-s-custom" v-if="scope.row.type==0"></i>
                <i class="el-icon-user" v-if="scope.row.type==1"></i>
                <i class="el-icon-bicycle" v-if="scope.row.type==2"></i>
            </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
                <el-button type="text" size="small" @click="openEditPanel(scope.row)">编辑</el-button>
                <el-button type="text" size="small" @click="delUserVis = true">删除</el-button>
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
    <input type="hidden" id="sessionName" value="${username}" />
</div>

<script>
    new Vue({
        el: "#box",
        data: {
            delUserVis:false,
            addOrderVis: false,
            editUserVis:false,
            user:{
                username:"",
                type:"",
                phone:""
            },
            userList: [],
            total: 0,//数据总量
            currentPage: 1,//当前页码
            pageSize: 5,//一页显示多少条

        },
        methods: {
            handleSizeChange: function (val) {
                this.pageSize = val
                this.selectAllUser();
            },
            //当前页码
            handleCurrentChange: function (val) {
                this.currentPage = val;
                this.selectAllUser();
            },

            //查询所有订单信息
            selectAllUser: function () {
                var currentPage = this.currentPage;
                var pageSize = this.pageSize
                var that = this;//相当于把data中的属性，定义为全局变量
                axios.get("/adminSelectAllUser?currentPage=" + currentPage + "&pageSize=" + pageSize).then(function (res) {
                    if (res.data.status) {
                        that.userList = res.data.list;//对typeList属性进行赋值
                        that.total = res.data.total;
                    } else {
                        that.$message.error(res.data.message)
                    }
                })
            },

            //点击删除提示框中的确定按钮
            delUser: function () {
                var username = document.getElementById("sessionName").value;
                var that = this;
                axios.get("/adminDelUser?username=" + username).then(function (res) {
                    if (res.data.status) {
                        that.delUserVis = false;
                        that.selectAllUser();
                    } else {
                        that.$message.error(res.data.message)
                    }
                })
            },
            //编辑用户
            openEditPanel:function (user) {
                this.editUserVis = true;
                this.user = user;
            },
            editUser: function () {
                var username = this.user.username;
                var type =this.user.type;
                var phone = this.user.phone;
                var that = this;
                axios.get("/adminEditUserController?username="+username+"&type="+type+"&phone="+phone).then(function (res) {
                    if (res.data.status) {
                        that.editUserVis = false;
                        //刷新
                        that.selectAllUser();
                    } else {
                        alert(res.data.message)
                    }
                })
            },
        },
        mounted: function () {
            this.selectAllUser();
        }
    })
</script>
</body>
</html>
