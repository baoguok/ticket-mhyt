<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
      <div class="title-container" align="center">
        <img class="logo_a" src="../../img/logo400.png">
        <h3 class="title">慧景通智慧景区购票管理系统</h3>
      </div>
      <el-form-item prop="username">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input v-model="loginForm.username" name="username" type="text" auto-complete="on" placeholder="账号" />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input :type="passwordType" v-model="loginForm.password" name="password" auto-complete="on" placeholder="密码" @keyup.enter.native="handleLogin" />
        <span class="show-pwd" @click="showPwd">
          <svg-icon icon-class="eye" />
        </span>
      </el-form-item>

      <el-button :loading="loading" type="success" style="width:100%;height:43px;margin-bottom:30px;background:#00cc66;border-color:#00C563;" @click.native.prevent="handleLogin">登录</el-button>

      <!-- <div style="position:relative">
        <div class="tips">
          <span> 超级管理员用户名: admin123</span>
          <span> 超级管理员用户名：admin123</span>
        </div>
        <div class="tips">
          <span> 商城管理员用户名: mall123</span>
          <span> 商城管理员用户名：mall123</span>
        </div>
        <div class="tips">
          <span> 推广管理员用户名: promotion123</span>
          <span> 推广管理员用户名：promotion123</span>
        </div>
      </div> -->
      <div class="edition">Powered&nbsp;by&nbsp;SYticket&nbsp;v2.1&nbsp;&nbsp;|&nbsp;&nbsp;Copyright&nbsp;©&nbsp;宋云科技-syjava.com &nbsp;&nbsp;All&nbsp;Rights&nbsp;Reserved.</div>
    </el-form>

  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (validateUsername == null) {
        callback(new Error('请输入正确的管理员用户名'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('管理员密码长度应大于6'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      loading: false
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }

  },
  created() {
    // window.addEventListener('hashchange', this.afterQRScan)
  },
  destroyed() {
    // window.removeEventListener('hashchange', this.afterQRScan)
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid && !this.loading) {
          this.loading = true
          this.$store.dispatch('LoginByUsername', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({ path: this.redirect || '/' })
          }).catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
            this.loading = false
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg:#2d3a4b;
$light_gray:#eee;

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }
  .el-form-item {
    /*border: 1px solid rgba(255, 255, 255, 0.1);*/
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: white;
  }
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-image: linear-gradient( 135deg, #3C8CE7 10%, #00EAFF 100%);
  /*background-image: linear-gradient( 135deg, #ABDCFF 10%, #0396FF 100%);*/
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    padding: 35px 35px 15px 35px;
    margin: 80px auto;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: white;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
    &_login {
      font-size: 20px;
    }
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      font-weight: 400;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      letter-spacing:2px;
    }
    .logo_a{
      width: 100px;
      height: 100px;
      margin: 0px auto 35px;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: white;
    cursor: pointer;
    user-select: none;
  }
  .edition {
    color:#fff;
    display:table;
    position: fixed;
    bottom: 20px;
    left: 0;
    width: 100%;
    text-align: center;
  }
}
</style>
