<%--
  Created by IntelliJ IDEA.
  User: 61990
  Date: 2017/6/12
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-body">
    <div class="row" style="margin-top: 20px">
        <!--左边选择栏  -->
        <div class="col-md-4 col-md-offset-1">
            <label class="">
                选股指标:
            </label>

            <select id="searchQuota" class="selectpicker col-md-6" onchange=quotaChange();
                    data-live-search="true" data-size="10" data-live-search-placeholder="Search"
                    data-actions-box="true" title="搜索指标">
                <option><span class="quota">N日平均成交额</span></option>
                <option class="quota">N日平均成交量</option>
                <option class="quota">N日乖离率</option>
                <option class="quota">N日波动率</option>
                <option class="quota">N日涨幅</option>
                <option class="quota">N日换手率</option>
                <optgroup label="价格">
                    <option class="quota">开盘价</option>
                    <option class="quota">收盘价</option>
                    <option class="quota">最高价</option>
                    <option class="quota">最低价</option>
                    <option class="quota">前日收盘价</option>
                    <option class="quota">后复权收盘价</option>
                    <option class="quota">后复权均价</option>
                </optgroup>
                <optgroup label="股本">
                    <option class="quota">总股本</option>
                    <option class="quota">流通股本</option>
                    <option class="quota">总市值</option>
                    <option class="quota">流通市值</option>
                    <option class="quota">股价振幅</option>
                </optgroup>
                <optgroup label="估值">
                    <option class="quota">市盈率</option>
                    <option class="quota">市销率</option>
                    <option class="quota">市净率</option>
                    <option class="quota">静态市盈率</option>
                    <option class="quota">动态市盈率</option>
                </optgroup>

            </select>

            <!-- 选项卡组件（菜单项nav-tabs）-->
            <ul id="myTab1" class="nav nav-tabs col-md-12 col-xs-12" style="margin-top: 20px"
                role="tablist">
                <li class="active"><a href="#bulletin" role="tab" data-toggle="tab">行情</a></li>
                <li><a href="#rule" role="tab" data-toggle="tab">技术指标</a></li>
                <li><a href="#forum" role="tab" data-toggle="tab">股指</a></li>
                <li><a href="#security" role="tab" data-toggle="tab">其他</a></li>
            </ul>
            <!-- 选项卡面板 -->
            <div id="myTabContent1" class="tab-content">
                <div class="tab-pane fade in active" id="bulletin">
                    <div class="row">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">股票价格
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation" class="dropdown-header">当日价格</li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">开盘价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">收盘价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">最高价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">最低价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation" class="dropdown-header">其他价格</li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">前日收盘价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">日均成交价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">后复权开盘价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">后复权收盘价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">后复权最高价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">后复权最低价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">前日后复权收盘价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">后复权均价<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">成交额
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">当日成交额<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">5日平均成交额<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">20日平均成交额<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">60日平均成交额<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">N日平均成交额<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">成交量
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">当日成交量<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">5日平均成交量<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">20日平均成交量<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">60日平均成交量<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">N日平均成交量<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">股价涨幅
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">当日涨幅<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                    <%--<span class="	glyphicon glyphicon-search"></span>--%>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">5日涨幅<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">20日涨幅<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">60日涨幅<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">N日涨幅<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">累计换手率
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">当日换手率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">5日换手率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">20日换手率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">60日换手率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">N日换手率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">股本和市值
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">总股本<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">流通股本<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">总市值<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">流通市值<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                                股价振幅<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign"  style="color:#337ab7"></span>
                            </button>

                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="rule">
                    <div class="row">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">乖离率
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">5日乖离率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">10日乖离率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">20日乖离率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">30日乖离率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">N日乖离率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-default dropdown-toggle"
                                    style="border: 0px solid white" data-toggle="dropdown">波动率
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="">
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">5日波动率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">10日波动率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">20日波动率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">30日波动率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" class="quota" tabindex="-1">N日波动率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span></a>
                                </li>

                            </ul>
                        </div>

                    </div>
                </div>
                <div class="tab-pane fade" id="forum">
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                            市盈率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span>
                        </button>

                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                            市净率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span>
                        </button>

                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                            市销率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign"  style="color:#337ab7"></span>
                        </button>

                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                            静态市盈率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign" style="color:#337ab7"></span>
                        </button>

                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default quota" style="border: 0px solid white">
                            动态市盈率<span> </span><span  data-toggle="tooltip" data-placement="right" title="右侧的 Tooltip" class="glyphicon glyphicon-question-sign"  style="color:#337ab7"></span>
                        </button>

                    </div>
                </div>
                <div class="tab-pane fade" id="security"></div>

            </div>
        </div>
        <!--右边数据框-->
        <div class="col-md-6" style=" border-left: 1px solid slategray;">
            <label class="row col-md-3" style="margin-top:5px">
                选股条件:
            </label>
            <!-- 选项卡组件（菜单项nav-tabs）-->

            <ul id="myTab2" class="nav nav-tabs col-md-12 col-xs-12" style="margin-top: 5px"role="tablist">
                <li class="active"><a href="#choose" role="tab" data-toggle="tab">筛选条件</a></li>
                <li><a href="#rank" role="tab" data-toggle="tab">排名条件</a></li>
            </ul>
            <!-- 选项卡面板 -->
            <div id="myTabContent2" class="tab-content">
                <div class="tab-pane fade in active" id="choose">

                    <div class="col-md-12 col-xs-12"
                         style="height:200px;max-height: 240px;overflow-y: auto">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>指标</th>
                                <th>比较符</th>
                                <th>值</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="quotaList">

                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="tab-pane fade in" id="rank">

                    <div class="col-md-12 col-xs-12"
                         style="height:200px;max-height:  240px;overflow-y: auto">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>指标</th>
                                <th>次序</th>
                                <th>权重</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="rankList">

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
