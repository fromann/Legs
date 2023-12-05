<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/js/vue.js"></script><!--先引入引入vue.js-->
    <script src="/js/index.js"></script>
    <script src="/js/axios.js"></script>
    <link rel="stylesheet" href="/css/index.css">
    <title>校园跑腿——By：108Club</title>

    <style>
        * {
            margin: 0px;
            padding: 0px;
        }

        a {
            text-decoration: none;
            color: black;

        }

        html, body, #box, .el-container {
            height: 100%;
            width: 100%;
        }

        .el-header, .el-footer {
            background-color: #fff;
            color: #333;
            text-align: center;
            line-height: 60px;
        }

        .el-main {
            background-color: #fff;
            color: #333;
            text-align: center;
            line-height: 160px;
        }

        body > .el-container {
            margin-bottom: 40px;
        }


    </style>
</head>
<body>
<div id="box">
    <el-container>
        <el-header>
            <el-row :gutter="20">
                <el-col :span="6"><h1 style="color:#409eff"><i class="el-icon-thumb"></i>有一腿</h1></el-col>
                <el-col :span="8">
                    <div>
                        <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
                            <el-menu-item index="1">
                                <a href="order.jsp" target="main">
                                    <i class="el-icon-menu"></i>
                                    <span slot="title">订单管理</span>
                                </a>
                            </el-menu-item>
                            <el-menu-item index="2">
                                <a href="user.jsp" target="main">
                                    <i class="el-icon-menu"></i>
                                    <span slot="title">用户管理</span>
                                </a>
                            </el-menu-item>
                        </el-menu>
                    </div>
                </el-col>
                <el-col :span="6">
                    <h3>
                        欢迎：<span style="color: #409eff">管理员：${name}</span>&nbsp
                        <a href="/LogOutServlet">注销</a>
                    </h3>
                </el-col>
            </el-row>


        </el-header>
        <%--                    <a href="type.jsp" target="main">--%>
        <%--                        <el-menu-item index="1">--%>
        <%--                            <i class="el-icon-s-shop"></i>--%>
        <%--                            <span slot="title">客房类型管理</span>--%>
        <%--                        </el-menu-item>--%>
        <%--                    </a>--%>
        <el-main>
            <iframe frameborder="0" name="main" width="100%" height="100%" src="order.jsp"></iframe>
        </el-main>
        <el-footer><a href="/about.jsp" target="main">© 2023 💖 108Club</a></el-footer>
    </el-container>


</div>
<script>
    new Vue({
        el: "#box",
        data: {
            activeIndex: '1',
        },
        methods: {
            handleSelect: function (key, keyPath) {
                console.log(key, keyPath);
            }
        }
    })
</script>
</body>
</html>
