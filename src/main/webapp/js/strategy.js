/**
 * Created by cuihua on 2017/6/8.
 */
// 导入本js需要用的js
document.write("<script src='traceBack.js'></script>");

/**
 * 直接跳转至要查看的策略界面
 */
function directToGeneralStrategy(strategy_id) {
    window.location.href = "/strategy/" + strategy_id;
}
