<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/6/12
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="modifyTimingModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:40%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">择时条件设定</h4>
            </div>
            <div class="modal-body">
                <p>择时条件:</p>
                <p class="ma indicator" hidden>以移动平均线(MA)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="macd indicator" hidden>以指数平滑异同移动平均线(MACD)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="dma indicator" hidden>以平均线差指标(DMA)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="trix indicator" hidden>以三重指数平滑移动平均指标(TRIX)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
                <p class="mavol indicator" hidden>以指数成交量移动平均线(MAVOL)的金叉死叉分别作为牛市和熊市的转换条件。设立择时条件将影响策略回测结果。</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary save">确定</button>
            </div>
        </div>
    </div><!-- /.modal -->
</div>
