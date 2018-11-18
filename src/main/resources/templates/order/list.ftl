<#--<h1>${orderDTOPage.getTotalPages()}</h1>-->
<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单Id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>${orderDTO.orderId} </td>
                    <td>${orderDTO.buyerName}</td>
                    <td>${orderDTO.buyerPhone}</td>
                    <td>${orderDTO.buyerAddress}</td>
                    <td>${orderDTO.orderAmount}</td>
                    <td>${orderDTO.getOrderStatusEnum().message}</td>
                    <td>${orderDTO.getPayStatusEnum().message}</td>
                    <td>${orderDTO.createTime}</td>
                    <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>

                    <td>
                        <#if orderDTO.getOrderStatusEnum().message == "新订单">
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#assign limitPage=10 />
                <#assign minPage=1 />
                <#assign maxPage=limitPage />
                <#if currentPage gt limitPage/2 >
                    <#assign minPage=currentPage-limitPage/2 />
                    <#assign maxPage=minPage + limitPage - 1 />
                </#if>
                <#if maxPage gt orderDTOPage.getTotalPages()>
                    <#assign maxPage=orderDTOPage.getTotalPages() />
                </#if>
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#"><上一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}"><上一页</a></li>
                </#if>
                <#list minPage..maxPage as index>
                    <#if currentPage == index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页&gt;</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页&gt;</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#--弹窗-->
<div class="modal fade" id="new-order-prompt-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript: document.getElementById('music-notice').pause()"
                        type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>

    </div>

</div>

<#--播放音乐-->
<audio id="music-notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    if (!("WebSocket" in window)) {
        alert("你的浏览器不支持 WebSocket，请升级或更换浏览器！");
    } else {
        var websocket = new WebSocket("ws://127.0.0.1:8080/sell/seller/web-socket");
        websocket.onopen = function (ev) {
            console.log("建立连接");
        }
        websocket.onclose = function (ev) {
            console.log("连接关闭");
        }
        websocket.onmessage = function (ev) {
            console.log("收到消息：" + ev.data);
            // 弹窗提醒，播放音乐
            $('#new-order-prompt-modal').modal('show');
            document.getElementById("music-notice").play();
        }
        websocket.onerror = function (ev) {
            alert("WebSocket通信发生错误");
        }

        window.onbeforeunload = function (ev) {
            websocket.close();
        }
    }
</script>
</body>
</html>
<#--<#list orderDTOPage.content as orderDTO>-->
<#--${orderDTO.orderId}<br>-->
<#--</#list>-->