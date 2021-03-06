<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>农场管理系统</title>
  <link rel="stylesheet" href="${re.contextPath}/plugin/plugins/layui/css/layui.css" media="all" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/plugins/font-awesome/css/font-awesome.min.css" media="all" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/build/css/app.css" media="all" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/build/css/themes/default.css" media="all" id="skin" kit-skin />
  <style>
    <#--前端无聊美化ing-->
    .layui-footer{background-color: #2F4056;}
    .layui-side-scroll{border-right: 3px solid #009688;}
  </style>
</head>

<body class="kit-theme">
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <#--头部导航栏-->
  <div class="layui-header">
    <div class="layui-logo">农场管理系统</div>
    <div class="layui-logo kit-logo-mobile"></div>
    <div class="layui-hide-xs">
    <ul class="layui-nav layui-layout-left kit-nav">
    </ul>
    </div>
    <ul class="layui-nav layui-layout-right kit-nav">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <i class="layui-icon">&#xe63f;</i> 皮肤</a>
        </a>
        <dl class="layui-nav-child skin">
          <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i class="layui-icon">&#xe658;</i> 默认</a></dd>
          <dd><a href="javascript:;" data-skin="orange" style="color:#ff6700;"><i class="layui-icon">&#xe658;</i> 橘子橙</a></dd>
          <dd><a href="javascript:;" data-skin="green" style="color:#00a65a;"><i class="layui-icon">&#xe658;</i> 春天绿</a></dd>
          <dd><a href="javascript:;" data-skin="pink" style="color:#FA6086;"><i class="layui-icon">&#xe658;</i> 少女粉</a></dd>
          <dd><a href="javascript:;" data-skin="blue.1" style="color:#00c0ef;"><i class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
          <dd><a href="javascript:;" data-skin="red" style="color:#dd4b39;"><i class="layui-icon">&#xe658;</i> 枫叶红</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item">
        <a href="javascript:;">
        <#assign currentUser = Session["curentUser"]>
          <img src="${re.contextPath}/images/${currentUser.photo}" class="layui-nav-img">${currentUser.username}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;" kit-target data-options="{url:'basic.html',icon:'&#xe658;',title:'基本资料',id:'966'}"><span>基本资料</span></a></dd>
          <dd><a href="javascript:;">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
    </ul>
  </div>
<#--目录树：垂直导航栏-->
      <#--原来的管理员页面-->
    <#if showInfo!=true>
        <#macro tree data start end>
          <#if (start=="start")>
  <div class="layui-side layui-nav-tree layui-bg-black kit-side">
  <div class="layui-side-scroll">
    <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
  <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
          </#if>
          <#list data as child>
            <#if child.children?size gt 0>
      <li class="layui-nav-item">
          <a class="" href="javascript:;"><i aria-hidden="true" class="layui-icon">${child.icon}</i><span> ${child.name}</span></a>
          <dl class="layui-nav-child">
          <@tree data=child.children start="" end=""/>
          </dl>
      </li>
            <#else>
      <dd>
          <a href="javascript:;" kit-target data-options="{url:'${child.url}',icon:'${child.icon}',title:'${child.name}',id:'${child.num?c}'}">
              <i class="layui-icon">${child.icon}</i><span> ${child.name}</span></a>
      </dd>
            </#if>
          </#list>
          <#if (end=="end")>
  </ul>
  </div>
  </div>
          </#if>
        </#macro>
        <@tree data=menu start="start" end="end"/>
  <div class="layui-body" id="container">
      <!-- 内容主体区域 -->
      <div style="padding: 15px;"><i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#xe63e;</i> 请稍等...</div>
  </div>

<script src="${re.contextPath}/plugin/plugins/layui/layui.js"></script>
<script src="${re.contextPath}/plugin/tools/main.js"></script>
    </#if>
<#--现在的技术人员页面-->
    <#if showInfo==true>
    <div class="layui-side layui-nav-tree layui-bg-black kit-side">
    <div class="layui-side-scroll">
        <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">基本信息管理</a>
                    <dl class="layui-nav-child">
                        <#--<dd><a href="/crop/showCrop">作物长势</a></dd>-->
                        <dd><a href="javascript:;" data-id="1" data-title="作物长势" data-url="/crop/showCrop"
                               class="site-demo-active" data-type="tabAdd">作物长势</a></dd>
                        <dd><a href="javascript:;" data-id="2" data-title="地块信息" data-url="/block/showBlock"
                               class="site-demo-active" data-type="tabAdd">地块信息</a></dd>
                            <dd><a href="javascript:;" data-id="3" data-title="传感器信息" data-url="/sensor/showSensor"
                                   class="site-demo-active" data-type="tabAdd">传感器信息</a></dd>
                            <dd><a href="javascript:;" data-id="4" data-title="机器人巡检数据信息" data-url="/pollingData/showPollingData"
                                   class="site-demo-active" data-type="tabAdd">机器人巡检数据信息</a></dd>
                            <dd><a href="javascript:;" data-id="5" data-title="土壤参数信息" data-url="/soilData/showSoilData"
                                   class="site-demo-active" data-type="tabAdd">土壤参数信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">智能化信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="6" data-title="无人机拍照显示" data-url="/pic/getFileList.do"
                               class="site-demo-active" data-type="tabAdd">无人机拍照显示</a></dd>
                        <dd><a href="javascript:;" data-id="6" data-title="田间机器人拍照显示" data-url="/pic/getFileList2.do"
                               class="site-demo-active" data-type="tabAdd">田间机器人拍照显示</a></dd>
                        <dd><a href="javascript:;" data-id="6" data-title="机器人采集温度图表" data-url="/pollingData/show"
                               class="site-demo-active" data-type="tabAdd">机器人采集温度图表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 200px;">
        <ul class="layui-tab-title"></ul>
        <div class="layui-tab-content"></div>
    </div>
    <#--<div class="layui-body">-->
        <#--<iframe src="" id="main" height="100%"></iframe>-->
    <#--</div>-->
  <div class="layui-footer">
      <!-- 底部固定区域 -->
  </div>
</div>

<script src="${re.contextPath}/plugin/layui/layui.all.js"></script>
<script>
    layui.use(['element', 'layer', 'jquery'], function () {
        var element = layui.element;
        // var layer = layui.layer;
        var $ = layui.$;
        // 配置tab实践在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在
                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);//删除
            }
        };

        function FrameWH() {
            var h = $(window).height();
            $("iframe").css("height", h + "px");
        }
    });
</script>
    </#if>

</body>

</html>
