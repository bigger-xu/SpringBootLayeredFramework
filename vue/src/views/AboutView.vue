<template>
  <div id="page-wrapper" class="gray-bg">
    <div class="row border-bottom">
      <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
          <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
          <form role="search" class="navbar-form-custom" action="search_results.html">
            <div class="form-group">
              <input type="text" placeholder="请输入搜索内容" class="form-control" name="top-search" id="top-search">
            </div>
          </form>
        </div>
        <ul class="nav navbar-top-links navbar-right">
          <li>
            <span class="m-r-sm text-muted welcome-message">欢迎来到inspinia 2.9.2管理后台</span>
          </li>
          <li class="dropdown">
            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
              <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
            </a>
            <ul class="dropdown-menu dropdown-messages">
              <li>
                <div class="dropdown-messages-box">
                  <a class="dropdown-item float-left" href="profile.html">
                    <img alt="image" class="rounded-circle" src="img/a7.jpg">
                  </a>
                  <div class="media-body">
                    <small class="float-right">46 小时前</small>
                    <strong>小明</strong> 评论了 <strong>小红</strong>. <br>
                    <small class="text-muted">2017.10.06 7:58</small>
                  </div>
                </div>
              </li>
              <li class="dropdown-divider"></li>
              <li>
                <div class="dropdown-messages-box">
                  <a class="dropdown-item float-left" href="profile.html">
                    <img alt="image" class="rounded-circle" src="img/a4.jpg">
                  </a>
                  <div class="media-body ">
                    <small class="float-right text-navy">5 小时前</small>
                    <strong>小红</strong> 打电话给了 <strong>小黑</strong>. <br>
                    <small class="text-muted">2017.10.06 7:58</small>
                  </div>
                </div>
              </li>
              <li class="dropdown-divider"></li>
              <li>
                <div class="dropdown-messages-box">
                  <a class="dropdown-item float-left" href="profile.html">
                    <img alt="image" class="rounded-circle" src="img/profile.jpg">
                  </a>
                  <div class="media-body ">
                    <small class="float-right">23 小时前</small>
                    <strong>小黑</strong> 点赞了 <strong>小红</strong>. <br>
                    <small class="text-muted">2017.10.06 7:58</small>
                  </div>
                </div>
              </li>
              <li class="dropdown-divider"></li>
              <li>
                <div class="text-center link-block">
                  <a href="mailbox.html" class="dropdown-item">
                    <i class="fa fa-envelope"></i> <strong>阅读所有消息</strong>
                  </a>
                </div>
              </li>
            </ul>
          </li>
          <li class="dropdown">
            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
              <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
            </a>
            <ul class="dropdown-menu dropdown-alerts">
              <li>
                <a href="mailbox.html" class="dropdown-item">
                  <div>
                    <i class="fa fa-envelope fa-fw"></i> 你有16条消息
                    <span class="float-right text-muted small">4 分钟前</span>
                  </div>
                </a>
              </li>
              <li class="dropdown-divider"></li>
              <li>
                <a href="profile.html" class="dropdown-item">
                  <div>
                    <i class="fa fa-twitter fa-fw"></i> 3 个新的关注者
                    <span class="float-right text-muted small">12 分钟前</span>
                  </div>
                </a>
              </li>
              <li class="dropdown-divider"></li>
              <li>
                <a href="grid_options.html" class="dropdown-item">
                  <div>
                    <i class="fa fa-upload fa-fw"></i> 重启服务器
                    <span class="float-right text-muted small">4 分钟前</span>
                  </div>
                </a>
              </li>
              <li class="dropdown-divider"></li>
              <li>
                <div class="text-center link-block">
                  <a href="notifications.html" class="dropdown-item">
                    <strong>查看所有信息</strong>
                    <i class="fa fa-angle-right"></i>
                  </a>
                </div>
              </li>
            </ul>
          </li>


          <li>
            <a href="login.html">
              <i class="fa fa-sign-out"></i> 注销
            </a>
          </li>
        </ul>

      </nav>
    </div>
    <div class="row wrapper border-bottom white-bg page-heading">
      <div class="col-lg-10">
        <h2>静态表格</h2>
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="index.html">主页</a>
          </li>
          <li class="breadcrumb-item">
            <a>表格</a>
          </li>
          <li class="breadcrumb-item active">
            <strong>静态表格</strong>
          </li>
        </ol>
      </div>

    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
      <div class="row">
        <div class="col-lg-6">
          <div class="ibox ">
            <div class="ibox-title">
              <h5>基本表</h5>
              <div class="ibox-tools">
                <a class="collapse-link">
                  <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                  <li><a href="#" class="dropdown-item">选项 1</a>
                  </li>
                  <li><a href="#" class="dropdown-item">选项 2</a>
                  </li>
                </ul>
                <a class="close-link">
                  <i class="fa fa-times"></i>
                </a>
              </div>
            </div>
            <div class="ibox-content">

              <table class="table">
                <thead>
                <tr>
                  <th>#</th>
                  <th>姓名</th>
                  <th>姓</th>
                  <th>用户名</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>小明</td>
                  <td>王</td>
                  <td>@wangxiaoming</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>小红</td>
                  <td>王</td>
                  <td>@wangxiaohong</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>小黑</td>
                  <td>王</td>
                  <td>@wangxiaohei</td>
                </tr>
                </tbody>
              </table>

            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="ibox ">
            <div class="ibox-title">
              <h5>条纹表 </h5>
              <div class="ibox-tools">
                <a class="collapse-link">
                  <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                  <li><a href="#" class="dropdown-item">选项 1</a>
                  </li>
                  <li><a href="#" class="dropdown-item">选项 2</a>
                  </li>
                </ul>
                <a class="close-link">
                  <i class="fa fa-times"></i>
                </a>
              </div>
            </div>
            <div class="ibox-content">

              <table class="table table-striped">
                <thead>
                <tr>
                  <th>#</th>
                  <th>数据</th>
                  <th>用户</th>
                  <th>数值</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td><span class="line">5,3,2,-1,-3,-2,2,3,5,2</span></td>
                  <td>小明</td>
                  <td class="text-navy"><i class="fa fa-level-up"></i> 40%</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td><span class="line">5,3,9,6,5,9,7,3,5,2</span></td>
                  <td>小红</td>
                  <td class="text-warning"><i class="fa fa-level-down"></i> -20%</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td><span class="line">1,6,3,9,5,9,5,3,9,6,4</span></td>
                  <td>小黑</td>
                  <td class="text-navy"><i class="fa fa-level-up"></i> 26%</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="ibox ">
            <div class="ibox-title">
              <h5>边界表 </h5>
              <div class="ibox-tools">
                <a class="collapse-link">
                  <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                  <li><a href="#" class="dropdown-item">选项 1</a>
                  </li>
                  <li><a href="#" class="dropdown-item">选项 2</a>
                  </li>
                </ul>
                <a class="close-link">
                  <i class="fa fa-times"></i>
                </a>
              </div>
            </div>
            <div class="ibox-content">

              <table class="table table-bordered">
                <thead>
                <tr>
                  <th>#</th>
                  <th>姓名</th>
                  <th>姓</th>
                  <th>用户名</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>小明</td>
                  <td>王</td>
                  <td>@wangxiaoming</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>小红</td>
                  <td>王</td>
                  <td>@wangxiaohong</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>小黑</td>
                  <td>王</td>
                  <td>@wangxiaohei</td>
                </tr>
                </tbody>
              </table>

            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="ibox ">
            <div class="ibox-title">
              <h5>悬停表 </h5>
              <div class="ibox-tools">
                <a class="collapse-link">
                  <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                  <li><a href="#" class="dropdown-item">选项 1</a>
                  </li>
                  <li><a href="#" class="dropdown-item">选项 2</a>
                  </li>
                </ul>
                <a class="close-link">
                  <i class="fa fa-times"></i>
                </a>
              </div>
            </div>
            <div class="ibox-content">

              <table class="table table-hover">
                <thead>
                <tr>
                  <th>#</th>
                  <th>数据</th>
                  <th>用户</th>
                  <th>数值</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td><span class="pie">0.52,1.041</span></td>
                  <td>小明</td>
                  <td class="text-navy"><i class="fa fa-level-up"></i> 40%</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td><span class="pie">226,134</span></td>
                  <td>小红</td>
                  <td class="text-warning"><i class="fa fa-level-down"></i> -20%</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td><span class="pie">0.52/1.561</span></td>
                  <td>小黑</td>
                  <td class="text-navy"><i class="fa fa-level-up"></i> 26%</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="ibox ">
            <div class="ibox-title">
              <h5>自定义响应表 </h5>
              <div class="ibox-tools">
                <a class="collapse-link">
                  <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                  <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                  <li><a href="#" class="dropdown-item">选项 1</a>
                  </li>
                  <li><a href="#" class="dropdown-item">选项 2</a>
                  </li>
                </ul>
                <a class="close-link">
                  <i class="fa fa-times"></i>
                </a>
              </div>
            </div>
            <div class="ibox-content">
              <div class="row">
                <div class="col-sm-5 m-b-xs"><select class="form-control-sm form-control input-s-sm inline">
                  <option value="0">选项 1</option>
                  <option value="1">选项 2</option>
                  <option value="2">选项 3</option>
                  <option value="3">选项 4</option>
                </select>
                </div>
                <div class="col-sm-4 m-b-xs">
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-sm btn-white ">
                      <input type="radio" name="options" id="option1" autocomplete="off" checked> 天
                    </label>
                    <label class="btn btn-sm btn-white active">
                      <input type="radio" name="options" id="option2" autocomplete="off"> 周
                    </label>
                    <label class="btn btn-sm btn-white">
                      <input type="radio" name="options" id="option3" autocomplete="off"> 月
                    </label>
                  </div>
                </div>
                <div class="col-sm-3">
                  <div class="input-group"><input placeholder="请输入搜索内容" type="text"
                                                  class="form-control form-control-sm"> <span
                      class="input-group-append"> <button type="button" class="btn btn-sm btn-primary">搜索!
                                    </button> </span></div>

                </div>
              </div>
              <div class="table-responsive">
                <table class="table table-striped">
                  <thead>
                  <tr>

                    <th></th>
                    <th>项目</th>
                    <th>完成度</th>
                    <th>任务</th>
                    <th>数据</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td><input type="checkbox" checked class="i-checks" name="input[]"></td>
                    <td>乐高玩具<small>例子</small></td>
                    <td><span class="pie">0.52/1.561</span></td>
                    <td>20%</td>
                    <td>2017年12月12日</td>
                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" class="i-checks" name="input[]"></td>
                    <td>乐高玩具</td>
                    <td><span class="pie">6,9</span></td>
                    <td>40%</td>
                    <td>2017年12月12日</td>
                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" class="i-checks" name="input[]"></td>
                    <td>乐高玩具</td>
                    <td><span class="pie">3,1</span></td>
                    <td>75%</td>
                    <td>2017年12月12日</td>
                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" class="i-checks" name="input[]"></td>
                    <td>乐高玩具</td>
                    <td><span class="pie">4,9</span></td>
                    <td>18%</td>
                    <td>2017年12月12日</td>
                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                  </tr>
                  </tbody>
                </table>
              </div>

            </div>
          </div>
        </div>
        <button type="submit" class="btn btn-primary block full-width m-b" @click="cusTip">提示</button>
      </div>
    </div>
    <Footer></Footer>
  </div>

</template>
<script>

import Footer from "@/components/Footer.vue";
import toa from "toastr/toastr"

export default {
  components: {Footer},
  methods:{
    cusTip(){
      //指定跳转地址
      console.log(toa);
      toa.warning('My name is Inigo Montoya. You killed my father, prepare to die!')
      // toast.success("13");
      // alert(toast.options)
      // alert("123")
    }
  }
}

</script>
<style>

</style>