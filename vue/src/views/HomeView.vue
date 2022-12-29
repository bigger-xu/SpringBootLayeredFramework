<template>
  <div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
      <div class="custom-height"></div>
      <h2>欢迎登录inspinia 2.9.2</h2>
      <form class="m-t" role="form">
        <div class="form-group">
          <input type="text" name="aa" class="form-control" placeholder="用户名">
        </div>
        <div class="form-group">
          <input type="password" name="bb" class="form-control" placeholder="密码">
        </div>
        <div class="form-group">
          <input type="text" name="cc" class="form-control" placeholder="验证码" style="width: 70%">
        </div>
        <button type="submit" class="btn btn-primary block full-width m-b" @click="doLogin">登录</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  methods:{
    doLogin(){
      //指定跳转地址
      this.$router.push('/about')
    }
  }
}
</script>
<style>
.custom-height{
  height: 210px;
}
</style>
