<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>有一腿</title>
		<script src="js/vue.js"></script>
		<!--先引入引入vue.js-->
		<script src="js/index.js"></script>
		<script src="js/axios.js"></script>
		<link rel="stylesheet" href="css/index.css">
		<style>
			* {
			    margin: 0;
			    padding: 0;
			}
			html {
			    height: 100%;
			}
			body {
			    height: 100%;
			}
			.container {
			    height: 100%;
			    background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
			}
			#app {
           background-color: rgba(255,255,255,0.6);
           width: 358px;
           height: 588px;
           border-radius: 15px;
           padding: 0 50px;
           position: relative;
           left: 50%;
           top: 50%;
           transform: translate(-50%, -50%);
        }

        .el-input {
            width: 300px;
        }
		.header {
			color:#a6c1ee;
		    font-size: 40px;
		    font-weight: bold;
		    text-align: center;
		    line-height: 200px;
		}
		.input-item {
			text-align: center;
		    display: block;
		    width: 100%;
		    margin-bottom: 20px;
		    border: 0;
		    padding: 10px;
		    font-size: 15px;
		    outline: none;

		}
		.input-item:placeholder {
		    text-transform: uppercase;
		} 

		.btn {
		    text-align: center;
		    padding: 10px;
		    width: 100%;
		    margin-top: 40px;
		    background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
		    color: #fff;
		}
		.msg {
		    text-align: center;
		    line-height: 88px;
		}
		a {
		    text-decoration-line: none;
		    color: #abc1ee;
		}

    </style>
	</head>
	<body>
		<div class="container">
			<div id="app">
				<el-dialog :title="log.title" :visible.sync="log.dialogVisible" :show-close="log.showClose" width="50%">
					<span>{{log.text}}</span>
					<span slot="footer" class="dialog-footer">
						<el-button type="primary" @click="log.dialogVisible = false">确 定</el-button>
					</span>
				</el-dialog>

				<div class="header">🏃‍♂️有一腿</div>
				<!-- ref获取表单中的规则 -->
				
					<el-form class="form-wrapper" :model="user" :rules="rules" label-width="5px" ref="123">
						<el-form-item  label="" prop="username">
							<el-input v-model="user.username" class="input-item" placeholder="账号"></el-input>
						</el-form-item>
						<el-form-item  label="" prop="password" >
							<el-input v-model="user.password" class="input-item" placeholder="密码"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button class="btn" @click="login('123')">登录</el-button>
						</el-form-item>
					</el-form>		
 				<div class="msg">
					没 有 账 号 ?
					<a href="#"> 注 册 </a>
				</div> 
			</div>
			<script>
				new Vue({ //创建vue实例,注意Vue的V要大写，先引入vue.js
						el: '#app', //绑定id为app的元素
						data: {
							user: {
								username: '',
								password: '',
							},
							log: {
								dialogVisible: false,
								title: "",
								text: "",
								showClose: false
							},
							// 定义表单规则的属性
							rules: {
								username: [{
										required: true, //开启验证
										message: '账号不能为空',
										trigger: 'blur' //trigger触发器 blur失去焦点时触发 change改变时触发
									},
									{
										min: 5,
										max: 10,
										message: '长度在 5 到 10 个字符',
										trigger: 'blur'
									}
								],
								password: [{
										required: true, //开启验证
										message: '密码不能为空',
										trigger: 'blur' //trigger触发器 blur失去焦点时触发 change改变时触发
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
							login: function(user) {
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
											if (res.data.status) {
												// 跳转到主页
												if (res.data.type == 1) {
													location.href = "/pages/sender/index.jsp";
												} else if (res.data.type == 2) {
													location.href = "/pages/receiver/index.jsp";
												} else if (res.data.type == 0) {
													location.href = "/pages/admin/index.jsp";
												} else {
													this.openLog("登录失败", "未知错误")
													location.href = "/login.jsp";
												}

											} else {
												// alert(res.data.message)
												//运行openLog方法
												this.openLog("登录失败", res.data.message)

											}
										})

									} else {
										// console.log('请按规则填写账号和密码');
										this.openLog("登录失败", "请按规则填写账号和密码")
										return false;
									}
								});
							},
							openLog: function(title, text) {
								this.log.dialogVisible = true
								this.log.title = title
								this.log.text = text
							},

						}
					})

					! function(e, t, a) {
						function r() {
							for (var e = 0; e < s.length; e++) s[e].alpha <= 0 ? (t.body.removeChild(s[e].el), s.splice(e, 1)) : (s[
									e].y--, s[e].scale += .004, s[e].alpha -= .013, s[e].el.style.cssText = "left:" + s[e].x +
								"px;top:" + s[e].y + "px;opacity:" + s[e].alpha + ";transform:scale(" + s[e].scale + "," + s[e]
								.scale + ") rotate(45deg);background:" + s[e].color + ";z-index:99999");
							requestAnimationFrame(r)
						}

						function n() {
							var t = "function" == typeof e.onclick && e.onclick;
							e.onclick = function(e) {
								t && t(), o(e)
							}
						}

						function o(e) {
							var a = t.createElement("div");
							a.className = "heart", s.push({
								el: a,
								x: e.clientX - 5,
								y: e.clientY - 5,
								scale: 1,
								alpha: 1,
								color: c()
							}), t.body.appendChild(a)
						}

						function i(e) {
							var a = t.createElement("style");
							a.type = "text/css";
							try {
								a.appendChild(t.createTextNode(e))
							} catch (t) {
								a.styleSheet.cssText = e
							}
							t.getElementsByTagName("head")[0].appendChild(a)
						}

						function c() {
							return "rgb(" + ~~(255 * Math.random()) + "," + ~~(255 * Math.random()) + "," + ~~(255 * Math
								.random()) + ")"
						}
						var s = [];
						e.requestAnimationFrame = e.requestAnimationFrame || e.webkitRequestAnimationFrame || e
							.mozRequestAnimationFrame || e.oRequestAnimationFrame || e.msRequestAnimationFrame || function(e) {
								setTimeout(e, 1e3 / 60)
							}, i(
								".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: fixed;}.heart:after{top: -5px;}.heart:before{left: -5px;}"
							), n(), r()
					}(window, document);
			</script>
	</body>
</html>
