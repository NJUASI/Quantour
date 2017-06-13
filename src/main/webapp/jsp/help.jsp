<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>帮助</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/startLoader.css" rel="stylesheet">
    <%@ include file="header.jsp" %>

    <style>
        /* Custom Styles */
        ul.nav-tabs{
            width: 140px;
            margin-top: 20px;
            border-radius: 4px;
            border: 1px solid #ddd;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
        }
        ul.nav-tabs li{
            margin: 0;
            border-top: 1px solid #ddd;
        }
        ul.nav-tabs li:first-child{
            border-top: none;
        }
        ul.nav-tabs li a{
            margin: 0;
            padding: 8px 16px;
            border-radius: 0;
        }
        ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
            color: #fff;
            background: #0088cc;
            border: 1px solid #0088cc;
        }
        ul.nav-tabs li:first-child a{
            border-radius: 4px 4px 0 0;
        }
        ul.nav-tabs li:last-child a{
            border-radius: 0 0 4px 4px;
        }
        ul.nav-tabs.affix{
            top: 30px; /* Set the top position of pinned element */
        }
    </style>
    <script>
        $(document).ready(function(){
            $("#myNav").affix({
                offset: {
                    top: 125
                }
            });
        });
    </script>
    </head>
    <body data-spy="scroll" data-target="#myScrollspy">
    <div class="container">
        <div class="jumbotron">
        </div>
        <div class="row">
            <div class="col-xs-3" id="myScrollspy">
                <ul class="nav nav-tabs nav-stacked affix"  style="margin-top: 110px" id="myNav">
                    <li class="active"><a href="#section-1">行情指标</a></li>
                    <li><a href="#section-2">技术指标</a></li>
                    <li><a href="#section-3">策略评估指标</a></li>
                    <li><a href="#section-4">排名和筛选</a></li>
                    <li><a href="#section-5">市场择时</a></li>
                </ul>
            </div>
            <div class="col-xs-9">
                <h2 id="section-1">行情指标</h2>
                开盘价/收盘价/最高价/最低价/前日收盘价 ：股票在用户指定日期的价格。

                日均成交价：当日股票平均成交价格，等于当日成交额除以当日成交量。

                后复权收盘价 ：股票当日收盘价的向后复权价格。算进股票分红、拆股的因素， 方便比较股票的价格历史和计算股票在历史上的真实收益。

                后复权均价 ：股票5日/20日/60日/120日/250日的后复权收盘价平均值。股票的停牌日不计算在内。 当股票非停牌交易日数少于N时，N日后复权平均价为空值。

                成交金额 ：股票当日成交额。5日/20日/60日/120日/250日平均成交额。N日平均成交额，股票的停牌日不计算在内。股票非停牌交易日数少于N时，N日平均成交额为空值。

                成交量 ：股票当日成交量。5日/20日/60日/120日/250日平均成交量。N日平均成交量，股票的停牌日不计算在内。股票非停牌交易日数少于N时，N日平均成交量为空值。

                股价涨幅 ：股票1日/5日/20日/60日/120日/250日的累计涨跌幅。N日累计涨跌幅，股票的停牌日计算在内。 股票交易日数少于N时，N日涨幅为空值。

                股价相对涨幅 ：股票1日/5日/20日/60日/120日/250日的相对于上证指数的累计涨跌幅。 N日股价相对涨幅，股票的停牌日计算在内。 股票交易日数少于N时，N日相对涨幅为空值。

                累计换手率 ：股票当日/5日/20日/60日/120日/250日的累计换手率。 等于成交股数除以总股本。 N日累计换手率，股票的停牌日计算在内。 股票交易日数少于N时，N日累计换手率为空值。

                涨跌停标记 ：股票当日涨停，跌停，一字涨停，一字跌停标记。事件发生为1，未发生为0。

                总股本/流通股本 ：A股当日总股数或总流通股数

                总市值/流通市值 ：A股当日总市值或总流通市值

                股东数 ：最近公告中持有A股的股东户总数

                户均持股数 ：等于总股本除以股东数

                股东数60日变化率 ：股东总数当日和60个交易日前相比的变化率。

                户均持股数60日变化率 ：户均持股当日和60个交易日前相比的变化率。

                机构持股比例 ：机构最近一季度的持股比例。

                机构持股环比增长 ：机构持股比例最近一季度比上一季度的增长率。

                10大股东持股比例 ：股票前十大股东最近一季度的持股比例。

                10大股东持股环比增长 ：股票前十大股东持股比例最近一季度比上一季度的增长率。

                股价振幅 ：股票当日最高价最低价之差除以前日收盘价。

                上市天数 ：股票当日的总上市天数。上市天数按照自然日计算。

                交易天数 ：股票当日的总上市交易日数。

                 <hr>
                <h2 id="section-2">技术指标</h2>
                乖离率 ：股票当日的收盘价与5日/20日/60日/120日/250日均线之间的差距。 等于后复权收盘价除以N日复权均价 -1。

                波动率 ：股票20日/60日/120日/250日收益波动率。停牌日不算入交易日内。 等于于stdev(1日涨幅，N日) * sqrt(N日) 。

                MACD ：根据个股的后复权收盘价计算出来的MACD(12, 26,9)。 MACD_DIFF = ema(后复权收盘价,12)-ema(后复权收盘价,26)。MACD_DEA = ema(MACD_DIF,9)。MACD柱状值 = 2 *(MACD_DIFF - MACD_DEA)。

                RSI ：代表股票在过去14个交易日的相对强弱情况。股票的停牌日不计算在内。值域在0到100之间。 值越大表示股票最近表现越强势。 RSI=SMA(greater(1日涨幅,0)*后复权因子,14,1)/SMA(ABS(1日涨幅)*后复权因子,14,1)*100。

                BBIC ：多空指标，等于BBI除以收盘价，BBIC越小股价越强势，BBIC < 1 为多头行情， BBIC>1 为空头行情。BBI等于(3日均价+6日均价+12日均价+24日均价)/4。计算使用后复权收盘价。

                CCI ：14日顺势指标，测量股价是否已超出常态分布范围。 +100以上为超买区，—100以下为超卖区，+100到—100之间为震荡区。

                历史贝塔 ：代表过去250个交易日的股价波动和沪深300波动相关程度。 历史贝塔 >1 表明个股价格波动幅度大于大盘的波动幅度，股价波动受到大盘的影响大； 历史贝塔 < 1 表明个股的价格波动受到大盘波动的影响较小。股票上市交易少于250日时，历史贝塔是空值。

                多头排列标记 ：标记为1，表示5日均线、20日均线、60日均线、120日均线依次排列，短线在长线的上方， 股价呈上升趋势。

                1日5日量比 ： 当日成交量与5日平均成交量比值。

                布林线 ：布林线上线等于20日复权均价+2*stdev(后复权收盘价，20), 布林线下线等于20日复权均价-2*stdev(后复权收盘价，20)。

                ATR ：平均真实波动范围 (Average True Range)。真实波幅等于当日股价振幅、最高与昨收差价、最低与昨收差价中的最大值。ATR = 真实波幅的14日移动平均。
                <hr>
                <h2 id="section-3">策略评估指标</h2>
                夏普比率 ：表示每承受一单位总风险，会产生多少的超额回报。 值越大策略越好。 比率>1, 策略的收益很好；比率>3时，代表组合的收益超好。计算公式 ( 年化收益率 - 无风险收益) / 年化收益波动率, 其中 年化收益率 = (总收益 + 1)^(365.25 / 天数) - 1, 无风险收益 =4%, 年化收益波动率 =stdev(日收益) *sqrt(250) 。

                最大回撤率 ：策略历史收益的最大跌幅。具体计算方法为 max(1 - 策略当日价值 / 当日之前虚拟账户最高价值)。

                收益波动率 ：用来测量策略的风险性，波动越大代表策略风险越高。具体计算方法为: 日收益的标准差 × SQRT(250)，其中250意思是一年250个交易日。

                信息比率 ：衡量策略是否能稳定跑赢基准。 计算公式：(策略年化收益 - 基准年化收益)/年化相对收益波动率，其中 年化相对收益波动率 = stdev(策略每日收益 - 基准每日收益) * sqrt(250)。当基准为无风险资产时， 信息比率等同夏普比率。

                Beta ：贝塔值愈大，策略收益相对于收益基准的波动越大。具体计算方法为策略每日收益与收益基准每日收益的协方差(covariance) / 收益基准每日收益的方差 (variance)。 Beta =1时，策略收益的波动和收益基准基本一样。 Beta > 1，收益基准的波动会引起策略收益的较大变化。

                Alpha ：Alpha是投资者获得与市场波动无关的回报，具体计算方式为 (策略年化收益 - 无风险年化收益) - beta× (收益基准年化收益 - 无风险年化收益)，这里的无风险年化收益按4%计算。Alpha > 0, 策略相对于风险，获得了超额收益; Alpha < 0, 策略相对于风险，获得了较少收益。

                <hr>
                <h2 id="section-4">排名和筛选</h2>
                大家在过去一直没有机会接触排名选股的方法，会感到比较陌生，其实它的原理非常简单。在学校里， 一个班的学生按各科成绩的综合起来进行排名，排在前面的学生会得奖学金。 类似的方法，用户可以对市场上的股票按几项指标进行综合排名，并买入排名靠前的股票。和筛选相比，排名可以实现更精细更稳定的选股策略，原因如下：

                筛选条件的阈值往往难以设定。条件过松时，筛出的股票可能过多，条件过严时，筛出的股票可能过少甚至为空。 而使用排名，股票之间总是能排出先后，用户可以较精确地控制选股数量，如前10前100等。

                在筛选股票时，股票只要有一个筛选条件不满足就会被剔除，即使其它指标表现再好都没用。而排名可以多个条件综合考量，即便是有一个条件排名得分低，只要其它条件排名得分高，股票依然可能入选。

                在筛选中，所有条件的重要性等同，而在排名中，不同条件可以有不同的权重，重要性可以不同。

                当用户同时定义筛选条件和排名条件时，首先按照筛选条件筛选股票，再按照排名条件对股票排名。 在实际选股中，大家可首先使用筛选来粗选出一大批股票，再用排名精选出少量的股票作为投资对象。

                <hr>
                <h2 id="section-5">大盘择时</h2>
                选股策略使用市场择时可以有效减小由整体市场波动带来的风险， 减小策略收益的最大回撤率。市场择时可分为两类，一类是趋势择时，即根据大盘指数的均线金叉死叉来判断趋势的转换。趋势择时有一定的滞后性，即大盘趋势转换一定时间后，才会发出信号。 另一类是反转择时，即抄底逃顶择时，就是在大盘跌破某个下限时转为买入，在大盘涨破上限时卖出。反转择时有一定的提前性，即大盘趋势还没有发生转换就会发出信号。在实际使用中，趋势择时更为常用，而趋势择时和反转择时可以结合使用。

                趋势择时有4个技术面指标，分别是MA（移动平均），MACD，DMA（平均线差指标），和TRIX（三重指数平滑移动平均指标）。当指标出现金叉时， 转为牛市，即买入； 当指标出现死叉时，转为熊市，即卖出。大盘指数默认为上证指数，用户可以在指标编辑界面选择其它主要大盘指数和行业指数。

                MA金叉和死叉的判定条件

                DMA = MA短线 – MA长线 （MA短线默认为上证5日均线， MA长线默认为上证60日均线。）

                金叉条件: MA短线(t) > MA短线(t-1) and DMA (t) > 0 and DMA (t-1) < 0

                死叉条件 MA短线(t)< MA短线(t-1) and DMA (t) < 0 and DMA (t-1) > 0

                其中‘XX(t)’表示当日的指标， ‘XX(t-1)’表示前一日的指标。 以下公式皆同。

                MACD金叉和死叉的判定条件

                DIF(快线) = EMA短线 - EMA长线 （EMA短线默认为上证12日指数移动平均线，EMA长线默认为上证26日指数移动平均线。）

                DEA(慢线)= EMA(DIF, M ) (DEA 默认为DIF的9日指数移动平均线。)

                金叉条件：DIF(t) > 0 and DIF(t) >DIF(t-1) and DIF (t) > DEA(t) and DIF (t -1) < DEA(t-1)

                死叉条件：DIF(t)<0 and DIF(t) < DIF(t-1) and DIF (t) < DEA(t) and DIF (t -1) > DEA(t-1)

                DMA金叉和死叉的判定条件

                DMA = MA短线 – MA长线 （MA短线默认为上证5日均线， MA长线默认为上证60日均线。）

                AMA = MA（DMA， M） （AMA默认为20日DMA移动平均）

                金叉条件: DMA(t) > DMA(t-1) and DMA (t) > AMA (t) and DMA (t -1) < AMA (t-1)

                死叉条件:DMA(t) < DMA(t-1) and DMA (t) < AMA (t) and DMA (t -1) > AMA (t-1)
                </div>
        </div>
    </div>
    </body>
</html>
<footer>

</footer>

<!-- 登录模态框（Modal） -->
<%@ include file="logIn.jsp" %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.js"></script>
<script src="../js/jquery.validate.js"></script>;
<script src="../js/logIn.js"></script>
<script src="../js/startLoaded.js"></script>
<script type="text/javascript">
    $("#stocks").click(function() {
        $("body").removeClass('loaded');
        window.location.href = "/stocks";
        $("#stocks").unbind("click");
    });
    $("#help").addClass("act");

    $('#myAffix').affix({
        offset: {
            top: 100, bottom: function () {
                return (this.bottom =
                    $('.bs-footer').outerHeight(true))
            }
        }
    });
</script>
</body>
</html>