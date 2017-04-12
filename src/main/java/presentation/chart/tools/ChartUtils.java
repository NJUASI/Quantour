package presentation.chart.tools;

		import java.awt.BasicStroke;
		import java.awt.Color;
		import java.awt.Font;
		import java.awt.Paint;
		import java.text.DecimalFormat;
		import java.text.ParseException;
		import java.text.SimpleDateFormat;
		import java.util.Date;
		import java.util.Vector;

		import org.jfree.chart.ChartFactory;
		import org.jfree.chart.JFreeChart;
		import org.jfree.chart.StandardChartTheme;
		import org.jfree.chart.axis.*;
		import org.jfree.chart.block.BlockBorder;
		import org.jfree.chart.labels.ItemLabelAnchor;
		import org.jfree.chart.labels.ItemLabelPosition;
		import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
		import org.jfree.chart.labels.StandardXYItemLabelGenerator;
		import org.jfree.chart.labels.StandardXYToolTipGenerator;
		import org.jfree.chart.plot.CategoryPlot;
		import org.jfree.chart.plot.DefaultDrawingSupplier;
		import org.jfree.chart.plot.PieLabelLinkStyle;
		import org.jfree.chart.plot.Plot;
		import org.jfree.chart.plot.XYPlot;
		import org.jfree.chart.renderer.category.BarRenderer;
		import org.jfree.chart.renderer.category.StandardBarPainter;
		import org.jfree.chart.renderer.xy.StandardXYBarPainter;
		import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
		import org.jfree.data.category.DefaultCategoryDataset;
		import org.jfree.data.general.DefaultPieDataset;
		import org.jfree.data.time.Day;
		import org.jfree.data.time.TimeSeries;
		import org.jfree.ui.RectangleInsets;
		import org.jfree.ui.TextAnchor;
        import presentation.view.tools.ColorUtils;

/**
 * Jfreechart工具类
 *
 * @author 61990
 * @since:2017-3-8
 *
 */
public class ChartUtils {
	private static String NO_DATA_MSG = "数据加载失败";
	private static Font FONT = new Font("宋体", Font.PLAIN, 12);
	public static Color[] CHART_COLORS = {
			new Color(255,61,61), new Color(15,195,81), new Color(255,61,61), new Color(15,195,81),
			new Color(255,61,61), new Color(15,195,81),
			new Color(255,61,61), new Color(15,195,81), };// 颜色

	static {
		setChartTheme();
	}

	public ChartUtils() {
	}

	/**
	 * 中文主题样式 解决乱码
	 */
	public static void setChartTheme() {
		// 设置中文主题样式 解决乱码
		StandardChartTheme chartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		chartTheme.setExtraLargeFont(FONT);
		// 设置图例的字体
		chartTheme.setRegularFont(FONT);
		// 设置轴向的字体
		chartTheme.setLargeFont(FONT);
		chartTheme.setSmallFont(FONT);
		chartTheme.setTitlePaint(ColorUtils.fontColor());
		chartTheme.setSubtitlePaint(ColorUtils.fontColor());

		chartTheme.setLegendBackgroundPaint(ColorUtils.backgroundColor());// 设置标注
		chartTheme.setLegendItemPaint(ColorUtils.fontColor());//
		chartTheme.setChartBackgroundPaint(ColorUtils.backgroundColor());
		// 绘制颜色绘制颜色.轮廓供应商
		// paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

		Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[] { ColorUtils.backgroundColor() };
		// 绘制器颜色源
		DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, OUTLINE_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
		chartTheme.setDrawingSupplier(drawingSupplier);

		chartTheme.setPlotBackgroundPaint(ColorUtils.backgroundColor());// 绘制区域
		chartTheme.setPlotOutlinePaint(ColorUtils.backgroundColor());// 绘制区域外边框
		chartTheme.setLabelLinkPaint(ColorUtils.linkColor());// 链接标签颜色
		chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

		chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
		chartTheme.setDomainGridlinePaint(ColorUtils.lineColor());// X坐标轴垂直网格颜色
		chartTheme.setRangeGridlinePaint(ColorUtils.lineColor());// Y坐标轴水平网格颜色

		chartTheme.setBaselinePaint(ColorUtils.lineColor());
		chartTheme.setCrosshairPaint(ColorUtils.lineColor());// 不确定含义
		chartTheme.setAxisLabelPaint(ColorUtils.fontColor());// 坐标轴标题文字颜色
		chartTheme.setTickLabelPaint(ColorUtils.fontColor());// 刻度数字
		chartTheme.setBarPainter(new StandardBarPainter());// 设置柱状图渲染
		chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar 渲染

		chartTheme.setItemLabelPaint(Color.black);
		chartTheme.setThermometerPaint(Color.white);// 温度计

		ChartFactory.setChartTheme(chartTheme);
	}

	/**
	 * 必须设置文本抗锯齿
	 */
	public static void setAntiAlias(JFreeChart chart) {
		chart.setTextAntiAlias(false);

	}

	/**
	 * 设置图例无边框，默认黑色边框
	 */
	public static void setLegendEmptyBorder(JFreeChart chart) {
		chart.getLegend().setFrame(new BlockBorder(ColorUtils.backgroundColor()));

	}

	/**
	 * 创建类别数据集合
	 */
	public static DefaultCategoryDataset createDefaultCategoryDataset(Vector<Serie> series, String[] categories) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Serie serie : series) {
			String name = serie.getName();
			Vector<Object> data = serie.getData();
			if (data != null && categories != null && data.size() == categories.length) {
				for (int index = 0; index < data.size(); index++) {
					String value = data.get(index) == null ? "" : data.get(index).toString();
					if (isPercent(value)) {
						value = value.substring(0, value.length() - 1);
					}
					if (isNumber(value)) {
						dataset.setValue(Double.parseDouble(value), name, categories[index]);
					}
				}
			}

		}
		return dataset;

	}


	/**
	 * 创建时间序列数据
	 *
	 * @param category
	 *            类别
	 * @param dateValues
	 *            日期-值 数组
	 * @return
	 */
	public static TimeSeries createTimeseries(String category, Vector<Object[]> dateValues) {
		TimeSeries timeseries = new TimeSeries(category);

		if (dateValues != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			for (Object[] objects : dateValues) {
				Date date = null;
				try {
					date = dateFormat.parse(objects[0].toString());
				} catch (ParseException e) {
				}
				String sValue = objects[1].toString();
				double dValue = 0;
				if (date != null && isNumber(sValue)) {
					dValue = Double.parseDouble(sValue);
					timeseries.add(new Day(date), dValue);
				}
			}
		}

		return timeseries;
	}



	/**
	 * 设置时间序列图样式
	 *
	 * @param plot
	 * @param isShowData
	 *            是否显示数据
	 * @param isShapesVisible
	 *            是否显示数据节点形状
	 */
	public static void setTimeSeriesRender(Plot plot, boolean isShowData, boolean isShapesVisible) {

		XYPlot xyplot = (XYPlot) plot;
		xyplot.setNoDataMessage(NO_DATA_MSG);
		xyplot.setInsets(new RectangleInsets(10, 10, 5, 10));

		XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();

		xyRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		xyRenderer.setBaseShapesVisible(false);
		if (isShowData) {
			xyRenderer.setBaseItemLabelsVisible(true);
			xyRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
			xyRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));// weizhi
			xyRenderer.setBaseItemLabelPaint(Color.WHITE);
		}
		xyRenderer.setBaseShapesVisible(isShapesVisible);// 数据点绘制形状

		DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
		domainAxis.setAutoTickUnitSelection(false);
		DateTickUnit dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 5, new SimpleDateFormat("yyyy-MM")); // 第二个参数是时间轴间距
		domainAxis.setTickUnit(dateTickUnit);

		StandardXYToolTipGenerator xyTooltipGenerator = new StandardXYToolTipGenerator("时间：{1}"
				+ "   {2}", new SimpleDateFormat("yyyy-MM-dd"), new DecimalFormat("0"));
		xyRenderer.setBaseToolTipGenerator(xyTooltipGenerator);

		setXY_XAixs(xyplot);
		setXY_YAixs(xyplot);

	}


	/**
	 * 设置柱状图渲染
	 *
	 * @param plot
	 * @param isShowDataLabels
	 */
	public static void setBarRenderer(CategoryPlot plot, boolean isShowDataLabels) {

		plot.setNoDataMessage(NO_DATA_MSG);
		plot.setInsets(new RectangleInsets(10, 10, 5, 10));
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setMaximumBarWidth(0.035);// 设置柱子最大宽度
		renderer.setItemMargin(0.05);

		if (isShowDataLabels) {
			renderer.setBaseItemLabelsVisible(true);
		}

		setXAixs(plot);
		setYAixs(plot);
	}




	/**
	 * 设置类别图表(CategoryPlot) X坐标轴线条颜色和样式
	 *
	 * @param plot
	 */
	public static void setXAixs(CategoryPlot plot) {
		Color lineColor = new Color(31, 121, 170);
		plot.getDomainAxis().setAxisLinePaint(lineColor);// X坐标轴颜色
		plot.getDomainAxis().setTickMarkPaint(lineColor);// X坐标轴标记|竖线颜色
	}

	/**
	 * 设置类别图表(CategoryPlot) Y坐标轴线条颜色和样式 同时防止数据无法显示
	 *
	 * @param plot
	 */
	public static void setYAixs(CategoryPlot plot) {
		Color lineColor = new Color(192, 208, 224);
		ValueAxis axis = plot.getRangeAxis();
		axis.setAxisLinePaint(lineColor);// Y坐标轴颜色
		axis.setTickMarkPaint(lineColor);// Y坐标轴标记|竖线颜色
		// 隐藏Y刻度
		axis.setAxisLineVisible(false);
		axis.setTickMarksVisible(false);
		// Y轴网格线条
		plot.setRangeGridlinePaint(new Color(192, 192, 192));
		plot.setRangeGridlineStroke(new BasicStroke(1));

		plot.getRangeAxis().setUpperMargin(0.1);// 设置顶部Y坐标轴间距,防止数据无法显示
		plot.getRangeAxis().setLowerMargin(0.1);// 设置底部Y坐标轴间距

	}

	/**
	 * 设置XY图表(XYPlot) X坐标轴线条颜色和样式
	 *
	 * @param plot
	 */
	public static void setXY_XAixs(XYPlot plot) {
		Color lineColor = new Color(31, 121, 170);
		plot.getDomainAxis().setAxisLinePaint(lineColor);// X坐标轴颜色
		plot.getDomainAxis().setTickMarkPaint(lineColor);// X坐标轴标记|竖线颜色

	}

	/**
	 * 设置XY图表(XYPlot) Y坐标轴线条颜色和样式 同时防止数据无法显示
	 *
	 * @param plot
	 */
	public static void setXY_YAixs(XYPlot plot) {
		Color lineColor = new Color(192, 208, 224);
		ValueAxis axis = plot.getRangeAxis();
		axis.setAxisLinePaint(lineColor);// X坐标轴颜色
		axis.setTickMarkPaint(lineColor);// X坐标轴标记|竖线颜色
		// 隐藏Y刻度
		axis.setAxisLineVisible(false);
		axis.setTickMarksVisible(false);
		// Y轴网格线条
		plot.setRangeGridlinePaint(new Color(192, 192, 192));
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.setDomainGridlinesVisible(false);

		plot.getRangeAxis().setUpperMargin(0.12);// 设置顶部Y坐标轴间距,防止数据无法显示
		plot.getRangeAxis().setLowerMargin(0.12);// 设置底部Y坐标轴间距

	}


	/**
	 * 是不是一个%形式的百分比
	 *
	 * @param str
	 * @return
	 */
	public static boolean isPercent(String str) {
		return str != null ? str.endsWith("%") && isNumber(str.substring(0, str.length() - 1)) : false;
	}

	/**
	 * 是不是一个数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return str != null ? str.matches("^[-+]?(([0-9]+)((([.]{0})([0-9]*))|(([.]{1})([0-9]+))))$") : false;
	}

}
