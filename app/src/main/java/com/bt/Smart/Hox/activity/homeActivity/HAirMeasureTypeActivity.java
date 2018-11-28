package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.HAirMeasureTypeInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/28 15:08
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HAirMeasureTypeActivity extends BaseActivity {
    private LineChart    lineChart;
    private RecyclerView recy_type;
    private List<Float>  mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_measure_type);
        setView();
        setData();
    }

    private void setView() {
        lineChart = (LineChart) findViewById(R.id.lineChart);
        recy_type = (RecyclerView) findViewById(R.id.recy_type);
    }

    private void setData() {
        mData = new ArrayList<>();
        mData.add(10f);
        mData.add(20f);
        mData.add(30f);
        mData.add(40f);
        mData.add(50f);
        mData.add(10f);
        mData.add(20f);
        initLineChart(mData);
        //获取测量类型的数据
        getMeasureTypeInfo(getIntent().getStringExtra("dev_ID"), getIntent().getStringExtra("type"));
    }

    private void getMeasureTypeInfo(String dev_id, String type) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("device_id", dev_id);
        params.put("type", type);
        params.put("hh", 6);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HAIR, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HAirMeasureTypeActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HAirMeasureTypeActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HAirMeasureTypeInfo hAirMeasureTypeInfo = gson.fromJson(resbody, HAirMeasureTypeInfo.class);
                ToastUtils.showToast(HAirMeasureTypeActivity.this, hAirMeasureTypeInfo.getMessage());
                if (1 == hAirMeasureTypeInfo.getCode()) {
                    List<HAirMeasureTypeInfo.HairListBean> hairList = hAirMeasureTypeInfo.getHairList();

                }
            }
        });
    }

    /**
     * 初始化曲线图表
     *
     * @param list 数据集
     */
    private void initLineChart(final List<Float> list) {
        //显示边界
        lineChart.setDrawBorders(false);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new Entry(i, list.get(i)));
        }

        /*
        * 线段之间不同颜色连接
        */
        /*ArrayList<ILineDataSet> allLinesList = new ArrayList<ILineDataSet>();
        //LineDataSet可以看做是一条线
        LineDataSet dataSet1 = new LineDataSet(entries, "dataLine1");

        dataSet1.setColors(new int[]{R.color.red, R.color.black, R.color.blue, R.color.orange, R.color.gray_99, R.color.green2, R.color.orange}, HAirMeasureTypeActivity.this);
        allLinesList.add(dataSet1);
        LineData data = new LineData(allLinesList);*/


        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "这是？");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#F15A4A"));
        //线条颜色
        lineDataSet.setColor(Color.BLUE);
        //点的颜色
        //lineDataSet.setCircleColors(colors);
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //显示圆点
        lineDataSet.setDrawCircles(true);
        //线条平滑
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);

        LineData data = new LineData(lineDataSet);
        //无数据时显示的文字
        lineChart.setNoDataText("暂无数据");
        //折线图不显示数值
        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(list.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                CharSequence format = DateFormat.format("MM/dd",
                        System.currentTimeMillis() - (long) (list.size() - IValue) * 24 * 60 * 60 * 1000);
                return format.toString();
            }
        });


        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(5);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount((int) (Collections.max(list) + 2), false);
        //设置从Y轴值
        yAxis.setAxisMinimum(Collections.min(list) - 1);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 1);

        //y轴
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //折线图点的标记
        MyMarkerView mv = new MyMarkerView(this);
        lineChart.setMarker(mv);


        //设置数据
        lineChart.setData(data);
        //在动态添加移除数据时使用
        //        lineChart.notifyDataSetChanged();
        //图标刷新
        lineChart.invalidate();
    }

    public class MyMarkerView extends MarkerView {

        private TextView tvContent;
        private DecimalFormat format = new DecimalFormat("##0");

        public MyMarkerView(Context context) {
            super(context, R.layout.layout_markerview);//这个布局自己定义
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        //显示的内容
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            tvContent.setText(format(e.getX()) + "\n" + "PM2.5数值：" + format.format(e.getY()));
            super.refreshContent(e, highlight);
        }

        //标记相对于折线图的偏移量
        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }

        //时间格式化（显示今日往前30天的每一天日期）
        public String format(float x) {
            CharSequence format = DateFormat.format("MM月dd日",
                    System.currentTimeMillis() - (long) (30 - (int) x) * 24 * 60 * 60 * 1000);
            return format.toString();
        }
    }
}
