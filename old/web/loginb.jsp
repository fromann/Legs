<%--
  Created by IntelliJ IDEA.
  User: SunShine
  Date: 2023/11/7
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="js/vue.js"></script><!--先引入引入vue.js-->
    <script src="js/index.js"></script>
    <script src="js/axios.js"></script>
    <link rel="stylesheet" href="css/index.css">
    <style>
        #app {
            margin: 100px auto;
            width: 400px;
            height: 300px;
            padding: 20px;
            background-color: rgba(255,255,255,0.8);
            border-radius: 5%;
        }


        .el-input {
            width: 300px;
        }

        body {
            background-repeat: no-repeat;
            background-size: cover;
            background-image: url("img/b.jpg");
        }
    </style>
</head>
<body>
<div id="app">
    <el-dialog :title="log.title"
                :visible.sync="log.dialogVisible"
                :show-close="log.showClose"
                width="30%">
        <span>{{log.text}}</span>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="log.dialogVisible = false">确 定</el-button>
        </span>
    </el-dialog>

    <!-- ref获取表单中的规则 -->
    <el-form :model="user" :rules="rules" label-width="100px" ref="123">
        <el-form-item label="账号" prop="username">
            <el-input v-model="user.username"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
            <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button @click="login('123')">登录</el-button>
        </el-form-item>
    </el-form>
</div>
<script>
    new Vue({//创建vue实例,注意Vue的V要大写，先引入vue.js
        el: '#app',//绑定id为app的元素
        data: {
            user: {
                username: '',
                password: '',
            },
            log:{
                dialogVisible: false,
                title:"",
                text:"" ,
                showClose: false
            },
            // 定义表单规则的属性
            rules: {
                username: [
                    {
                        required: true,//开启验证
                        message: '账号不能为空',
                        trigger: 'blur'//trigger触发器 blur失去焦点时触发 change改变时触发
                    },
                    {
                        min: 5,
                        max: 10,
                        message: '长度在 5 到 10 个字符',
                        trigger: 'blur'
                    }
                ],
                password: [
                    {
                        required: true,//开启验证
                        message: '密码不能为空',
                        trigger: 'blur'//trigger触发器 blur失去焦点时触发 change改变时触发
                    },
                    {
                        min: 3,
                        max: 10,
                        message: '长度在 3 到 10 个字符',
                        trigger: 'blur'
                    }
                ],

            }
        },
        methods: {
            login: function (user) {
                // this.$refs[formName]获取表单对象的规则认证
                // validate验证表单的规则，valid验证是否通过 认证成功返回true，失败返回false
                this.$refs[user].validate((valid) => {
                    if (valid) {
                        var username = this.user.username;
                        var password = this.user.password;
                        axios.get(
                            "login?username=" + username +
                            "&password=" + password
                        ).then((res) => {
                            console.log(res)
                            if (res.data.status){
                                // 跳转到主页
                                if(res.data.type==1){
                                    location.href="/pages/sender/index.jsp";
                                }else if (res.data.type==2){
                                    location.href="/pages/receiver/index.jsp";
                                }else if (res.data.type==0){
                                    location.href="/pages/admin/index.jsp";
                                }else {
                                    this.openLog("登录失败","未知错误")
                                    location.href="/login.jsp";
                                }

                            }else {
                                // alert(res.data.message)
                                //运行openLog方法
                                this.openLog("登录失败",res.data.message)

                            }
                        })

                    } else {
                        // console.log('请按规则填写账号和密码');
                        this.openLog("登录失败","请按规则填写账号和密码")
                        return false;
                    }
                });
            },
            openLog:function (title,text){
                this.log.dialogVisible=true
                this.log.title=title
                this.log.text=text
            },

        }
    })
</script>
</body>
</html>
