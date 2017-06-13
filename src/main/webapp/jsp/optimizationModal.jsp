<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/6/13
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="optimizationModal" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">智能优化参数设置</h4>
            </div>
            <div class="modal-body" style="margin-top: -10px">

                <div class="row" style="margin-bottom: 20px">
                    <div class="col-md-offset-1 col-md-1">
                        <span>策略: </span>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                        <span class="nameOfStrategy">6bushibu</span>
                    </div>
                    <div class="col-md-offset-2 col-md-3">
                        <span class="">搜索空间节点数 </span><span id="resultNum" style="color:indianred">1</span><span> 个</span>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 20px">
                    <div class="col-md-offset-1 col-md-2">
                        <span style="margin-top: 10px">目标核心函数: </span>
                    </div>
                    <div class="col-md-2">
                        <select class="form-control" id="mainFunc">
                            <option value="夏普比率">夏普比率</option>
                            <option value="年化收益">年化收益</option>
                        </select>
                    </div>

                    <div class="col-md-offset-2 col-md-3" hidden  id="inputError">
                        <strong style="color:indianred">参数原值不得超出最大最小值范围</strong>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-2 col-md-offset-1"><p>筛选条件</p></div>
                    <div class="col-md-8" style="margin-left: -40px">
                        <div class="row ">
                            <div class=" col-md-6 ">
                                <span class="col-md-6">指标</span><span class="col-md-4">比较符</span> <span class="col-md-1">值</span>
                            </div>
                        </div>

                        <!--<c:forEach items="${filterConditions}" var="condition" varStatus="vs">-->
                        <!--<div class="row chooseRow">-->
                        <!--<div class=" col-md-6 ">-->
                        <!--<span class="col-md-6">${condition.indicatorType}</span><span class="col-md-4">${condition.comparatorType}</span> <span class="col-md-1">${condition.value}</span>-->
                        <!--</div>-->
                        <!--<div class=" col-md-6 ">-->
                        <!--<div class="col-md-4">-->
                        <!--<input type="text" class="form-control max_num"    placeholder="最大">-->
                        <!--</div>-->
                        <!--<div class="col-md-4">-->
                        <!--<input type="text" class="form-control min_num"   placeholder="最小">-->
                        <!--</div>-->
                        <!--<div class="col-md-4">-->
                        <!--<input type="text" class="form-control length_num"   placeholder="步长">-->
                        <!--</div>-->
                        <!--</div>-->
                        <!--</div>-->
                        <!--</c:forEach>-->
                        <div class="row chooseRow">
                            <div class=" col-md-6 ">
                                <span class="col-md-6 indicatorType">${condition.indicatorType}</span><span class="col-md-4 comparatorType">${condition.comparatorType}</span> <span class="col-md-1 value">${condition.value}</span>
                            </div>
                            <div class=" col-md-6 ">
                                <div class="col-md-4">
                                    <input type="text" class="form-control max_num num"    placeholder="最大">
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control min_num num"   placeholder="最小">
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control length_num num"   placeholder="步长">
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row" style="margin-top: 5px">
                    <div class="col-md-2 col-md-offset-1"><p>排名条件</p></div>
                    <div class="col-md-8" style="margin-left: -40px">
                        <div class="row ">
                            <div class=" col-md-6 ">
                                <span class="col-md-6">指标</span><span class="col-md-4">次序</span> <span class="col-md-2" style="padding-right: 0px">权重</span>
                            </div>
                        </div>
                        <!--<c:forEach items="${rankConditions}" var="condition" varStatus="vs">-->
                        <div class="row rankRow">
                            <div class=" col-md-6 ">
                                <span class="col-md-6 indicatorType">${condition.indicatorType}</span><span class="col-md-4 rankType">${condition.rankType}</span> <span class="col-md-1 weight">${condition.weight}</span>
                            </div>
                            <div class=" col-md-6 ">
                                <div class="col-md-4">
                                    <input type="text" class="form-control max_num num"    placeholder="最大">
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control min_num num"   placeholder="最小">
                                </div>
                                <div class="col-md-4">
                                    <input type="text" class="form-control length_num num"   placeholder="步长">
                                </div>
                            </div>
                        </div>
                        <!--</c:forEach>-->

                    </div>
                </div>
                <button class="btn btn-primary submit col-md-offset-6">提交调优</button>



            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div><!-- /.modal -->
</div>


