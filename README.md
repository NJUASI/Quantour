# Quantour

## 简介
本项目是由ASI小组开发的A股股票金融证券分析系统，由ASI享有全部解释权。此 README 针对迭代二版本。

## 运行要求
项目需在搭载了JRE的环境中运行。编码环境要求UTF-8。（在Windows下运行架包2.0，可能会出现汉字乱码的情况）

可直接运行分支Harvey目录（ jar/asi-2.0-SNAPSHOT-jar-with-dependencies.jar ）下架包。

> 我们没有给默认的账户密码，烦请助教自己注册一个。密码需要数字加字母（比如qwertyuiop123456）

##项目层级目录
项目主页下，_***lib***_ 为项目依赖的一些 maven 依赖库里面没有的架包，_***src***_ 中包含项目源码和测试源码，_***文档***_中包含所有文档的 word 和 pdf 文件，_***jar***_ 中包含可执行文件。

编译后的文件和部分IDEA的配置文件未上传至git。

## 文档
文档包括迭代二项目计划文档、迭代二需求规格说明文档、迭代二体系规格文档、迭代二项目部署阶段文档、迭代三计划文档。

建议助教直接全屏查看 _***文档/pdf/迭代二/***_ 目录下pdf。

## 附加功能点
+ 软件跨系统（macOS / windows）
	+ 生成隐藏文件（.attachments），并在此隐藏目录下加密存放用户密码。
	+ 软件自适应全屏。
+ 用户管理
	+ 用户登录、注册。
	+ 自选股：快速查看自己喜爱的股票。
	+ 数据源替换：用户上传与原数据源哥是相同的csv文件，即可采用替换的数据源方便、快捷地进行所有系统功能（查看行情／K线、进行股票比较／回测）。
+ 策略组合
	+ 形成指标：N日涨幅、N日乖离率、N日平均成交量。（N可取5/10/20/30/60/120）。
	+ 筛选条件：排名最大、排名最小、排名%最大、排名%最小。数值由自己输入（大于1）。
	+ 调仓周期：策略中的一个持有周期。基于此，除了需求给出的所需数据外，还如果仁网一样，给出了每个持仓周期的详情。
	+ 在回测的图表中，多显示了回测的最大回撤点。

	> 动量策略即`涨幅``排名%最大（20）`，均值回归策略即`乖离率``排名最大（持股数）`，用户在选项中组合即可形成需求所要求的回测策略。
+ 拖拽实现：在 _***行情***_ 界面可单选或多选（`SHIFT`）选择股票，并拖拽至左下角自选回测股票池中，也可单选或多选拖拽移出。
+ 双击实现：在 _***行情***_ 界面双击一只股票，即可直接进入 _***个股***_ 界面查看K线图
+ 拼音首字母搜索：在 _***个股／对比***_ 界面的股票输入框内，可输入代码／名称／拼音三种形式进行搜索。

注：_自选股_与_自选股池_是两个不同的功能。_自选股_是我们基于用户管理增加的功能，方便用户查看自己收藏的股票信息；_自选股池_则是为了适应需求中的自选股票池进行策略回测。两者均在 _***行情***_ 界面进行添加，加入收藏即加入_自选股_，拖拽到左下角界面中即加入回测功能的_自选股池_。两者数据是完全分离的，望注意。

## 联系方式
冯俊杰：151250037@smail.nju.edu.cn

董金玉：151250032@smail.nju.edu.cn

高源：151250040@smail.nju.edu.cn

龚尘淼：151250043@smail.nju.edu.cn
