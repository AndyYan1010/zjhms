package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.HAirMeasureTypeInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ThreadUtils;
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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
    //    private RecyclerView recy_type;
    private List<Float>  mData;//Y轴数据
    private List<String> mSList;//X轴数据
    private CheckBox     cb_six;
    private CheckBox     cb_twelve;
    private CheckBox     cb_twentyfour;
    private LinearLayout lin01;
    private LinearLayout lin02;
    private LinearLayout lin03;
    private LinearLayout lin04;
    private LinearLayout lin05;
    private LinearLayout lin06;
    private TextView     tv_cont01, tv_cont02, tv_cont03, tv_cont04, tv_cont05,tv_cont06;
    private TextView tv_color01, tv_color02, tv_color03, tv_color04, tv_color05,tv_color06;
    private String dev_id;
    private String type;
    private int hNum = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_measure_type);
        setView();
        setData();
    }

    private void setView() {
        lineChart = (LineChart) findViewById(R.id.lineChart);
        lin01 = (LinearLayout) findViewById(R.id.lin01);
        lin02 = (LinearLayout) findViewById(R.id.lin02);
        lin03 = (LinearLayout) findViewById(R.id.lin03);
        lin04 = (LinearLayout) findViewById(R.id.lin04);
        lin05 = (LinearLayout) findViewById(R.id.lin05);
        lin06 = (LinearLayout) findViewById(R.id.lin06);
        tv_cont01 = (TextView) findViewById(R.id.tv_cont01);
        tv_cont02 = (TextView) findViewById(R.id.tv_cont02);
        tv_cont03 = (TextView) findViewById(R.id.tv_cont03);
        tv_cont04 = (TextView) findViewById(R.id.tv_cont04);
        tv_cont05 = (TextView) findViewById(R.id.tv_cont05);
        tv_cont06 = (TextView) findViewById(R.id.tv_cont06);
        tv_color01 = (TextView) findViewById(R.id.tv_color01);
        tv_color02 = (TextView) findViewById(R.id.tv_color02);
        tv_color03 = (TextView) findViewById(R.id.tv_color03);
        tv_color04 = (TextView) findViewById(R.id.tv_color04);
        tv_color05 = (TextView) findViewById(R.id.tv_color05);
        tv_color06 = (TextView) findViewById(R.id.tv_color06);

        cb_six = (CheckBox) findViewById(R.id.cb_six);
        cb_twelve = (CheckBox) findViewById(R.id.cb_twelve);
        cb_twentyfour = (CheckBox) findViewById(R.id.cb_twentyfour);
        //        recy_type = (RecyclerView) findViewById(R.id.recy_type);
    }

    private void setData() {
        mData = new ArrayList<>();
        mSList = new ArrayList<>();
        //获取测量类型的数据
        dev_id = getIntent().getStringExtra("dev_ID");
        type = getIntent().getStringExtra("type");
        //        getMeasureTypeInfo();
        cb_six.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_twelve.setChecked(false);
                    cb_twentyfour.setChecked(false);
                    hNum = 6;
                    //获取信息
                    getMeasureTypeInfo();
                }
            }
        });
        cb_twelve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_six.setChecked(false);
                    cb_twentyfour.setChecked(false);
                    hNum = 12;
                    //获取信息
                    getMeasureTypeInfo();
                }
            }
        });
        cb_twentyfour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_six.setChecked(false);
                    cb_twelve.setChecked(false);
                    hNum = 24;
                    //获取信息
                    getMeasureTypeInfo();
                }
            }
        });
        cb_six.setChecked(true);
    }

    private void getMeasureTypeInfo() {
        ProgressDialogUtil.startShow(this, "正在加载");
        RequestParamsFM params = new RequestParamsFM();
        params.put("device_id", dev_id);
        params.put("type", type);
        params.put("hh", hNum);
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
                    final List<HAirMeasureTypeInfo.HairListBean> hairList = hAirMeasureTypeInfo.getHairList();
                    if (hairList.size() > 0) {
                        ThreadUtils.runOnSubThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mSList.size() != 0) {
                                    mData.clear();
                                    mSList.clear();
                                }
                                //放入数值初始化图表
                                if ("pm25".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getPm25().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("pm100".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getPm100().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("co2".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getCo2().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("co".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getCo().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("formaldehyde".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getFormaldehyde().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("temperature".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getTemperature().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("humidity".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getHumidity().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                } else if ("voc".equals(getIntent().getStringExtra("type"))) {
                                    for (HAirMeasureTypeInfo.HairListBean bean : hairList) {
                                        mData.add(Float.valueOf(bean.getVoc().replace(",", "")));
                                        mSList.add(bean.getFaddtime());
                                    }
                                }
                                Collections.reverse(mData);
                                Collections.reverse(mSList);
                                //存放颜色
                                final int[] colorInt = new int[mData.size()];
                                if ("pm25".equals(getIntent().getStringExtra("type")) || "pm100".equals(getIntent().getStringExtra("type"))) {
                                    for (int n = 0; n < mData.size(); n++) {
                                        if (mData.get(n) < 35) {
                                            colorInt[n] = (R.color.green_100);
                                        } else if (mData.get(n) >= 35 && mData.get(n) < 75) {
                                            colorInt[n] = (R.color.yellow);
                                        } else if (mData.get(n) >= 75 && mData.get(n) < 115) {
                                            colorInt[n] = (R.color.orange_10);
                                        } else if (mData.get(n) >= 115 && mData.get(n) < 150) {
                                            colorInt[n] = (R.color.red_30);
                                        } else if (mData.get(n) >= 150 && mData.get(n) < 250) {
                                            colorInt[n] = (R.color.purple_30);
                                        } else {
                                            colorInt[n] = (R.color.brown_30);
                                        }
                                    }
                                }
                                if ("co2".equals(getIntent().getStringExtra("type"))) {
                                    for (int n = 0; n < mData.size(); n++) {
                                        if (mData.get(n) < 450) {
                                            colorInt[n] = (R.color.green_100);
                                        } else if (mData.get(n) >= 450 && mData.get(n) < 1000) {
                                            colorInt[n] = (R.color.yellow);
                                        } else if (mData.get(n) >= 1000 && mData.get(n) < 2000) {
                                            colorInt[n] = (R.color.orange_10);
                                        } else if (mData.get(n) >= 2000 && mData.get(n) < 5000) {
                                            colorInt[n] = (R.color.red_30);
                                        } else {
                                            colorInt[n] = (R.color.purple_30);
                                        }
                                    }
                                    lin01.setVisibility(View.INVISIBLE);
                                }
                                if ("co".equals(getIntent().getStringExtra("type"))) {
                                    for (int n = 0; n < mData.size(); n++) {
                                        if (mData.get(n) < 1) {
                                            colorInt[n] = (R.color.green_100);
                                        } else if (mData.get(n) >= 1 && mData.get(n) < 5) {
                                            colorInt[n] = (R.color.yellow);
                                        } else if (mData.get(n) >= 5 && mData.get(n) < 15) {
                                            colorInt[n] = (R.color.orange_10);
                                        } else if (mData.get(n) >= 15 && mData.get(n) < 100) {
                                            colorInt[n] = (R.color.red_30);
                                        } else if (mData.get(n) >= 100 && mData.get(n) < 200) {
                                            colorInt[n] = (R.color.purple_30);
                                        } else {
                                            colorInt[n] = (R.color.brown_30);
                                        }
                                    }
                                    tv_cont01.setText(">200");
                                    tv_cont02.setText("100-200");
                                    tv_cont03.setText("15-100");
                                    tv_cont04.setText("5-15");
                                    tv_cont05.setText("1-5");
                                    tv_cont06.setText("0-1");
                                }
                                if ("formaldehyde".equals(getIntent().getStringExtra("type"))) {
                                    for (int n = 0; n < mData.size(); n++) {
                                        if (mData.get(n) < 1) {
                                            colorInt[n] = (R.color.green_100);
                                        } else if (mData.get(n) >= 1 && mData.get(n) < 5) {
                                            colorInt[n] = (R.color.yellow);
                                        } else if (mData.get(n) >= 5 && mData.get(n) < 15) {
                                            colorInt[n] = (R.color.orange_10);
                                        } else if (mData.get(n) >= 15 && mData.get(n) < 100) {
                                            colorInt[n] = (R.color.red_30);
                                        } else if (mData.get(n) >= 100 && mData.get(n) < 200) {
                                            colorInt[n] = (R.color.purple_30);
                                        } else {
                                            colorInt[n] = (R.color.brown_30);
                                        }
                                    }
                                    lin01.setVisibility(View.GONE);
                                    lin02.setVisibility(View.GONE);
                                    tv_cont03.setText(">1");
                                    tv_cont04.setText("1-1");
                                    tv_cont05.setText("0-1");
                                    tv_cont06.setText("0-0");
                                }
                                if ("temperature".equals(getIntent().getStringExtra("type"))) {
                                    lin01.setVisibility(View.GONE);

                                }
                                if ("humidity".equals(getIntent().getStringExtra("type"))) {

                                }
                                if ("voc".equals(getIntent().getStringExtra("type"))) {

                                }
                                ThreadUtils.runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        initLineChart(mData, mSList, colorInt);
                                        //    LineData lineData = lineChart.getLineData();
                                        //    lineChart.notifyDataSetChanged();
                                        //    //图标刷新
                                        //    lineChart.invalidate();
                                    }
                                });
                            }
                        });
                    }
                } else {
                    //查询数据失败

                }
            }
        });
    }

    /**
     * 初始化曲线图表
     *
     * @param list 数据集
     */
    private void initLineChart(final List<Float> list, final List<String> sList, int[] colorInt) {
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
        ArrayList<ILineDataSet> allLinesList = new ArrayList<ILineDataSet>();
        //LineDataSet可以看做是一条线
        LineDataSet dataSet1 = new LineDataSet(entries, "dataLine1");
        dataSet1.setColors(colorInt, HAirMeasureTypeActivity.this);
        dataSet1.setDrawCircles(false);
        allLinesList.add(dataSet1);
        LineData data = new LineData(allLinesList);


        //无数据时显示的文字
        lineChart.setNoDataText("暂无数据");
        //折线图不显示数值
        data.setDrawValues(false);

        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(5);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(sList.size());
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        //        xAxis.setAxisMinimum(0f);
        //        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(90);

        MyXFormatter formatter1 = new MyXFormatter(sList);

        xAxis.setValueFormatter(formatter1);


        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(10);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+5
        yAxis.setLabelCount((int) (Collections.max(list) + 5), false);
        //设置从Y轴值
        yAxis.setAxisMinimum(Collections.min(list) - 10);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 10);

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
            //            tvContent.setText(format(e.getX()) + "\n" + "PM2.5数值：" + format.format(e.getY()));
            tvContent.setText("PM2.5数值：" + format.format(e.getY()));
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

    public class MyXFormatter implements IAxisValueFormatter {

        private List<String> mValues;

        public MyXFormatter(List<String> values) {
            this.mValues = values;
        }

        private static final String TAG = "MyXFormatter";

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // "value" represents the position of the label on the axis (x or y)
            Log.d(TAG, "----->getFormattedValue: " + value);
            return mValues.get((int) value % mValues.size());
        }
    }
}
