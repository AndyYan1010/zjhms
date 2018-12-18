package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/18 16:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class NormalQuestionActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_back;
    private TextView  tv_title;
    private WebView   web_rule;
    private String    questionStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_rule);
        getView();
        setString();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        web_rule = (WebView) findViewById(R.id.web_rule);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("常见问题");
        setWebContent();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void setWebContent() {
        web_rule.loadDataWithBaseURL("", getNewContent(questionStr), "text/html", "utf-8", "");
        //启用支持javascript
        WebSettings settings = web_rule.getSettings();
        settings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        web_rule.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        Log.d("VACK", doc.toString());
        return doc.toString();
    }

    private void setString() {
        questionStr = "<p>\n" +
                "\t设备联网<br />\n" +
                "<br />\n" +
                "怎么联网<br />\n" +
                "<br />\n" +
                "第一步：添加设备<br />\n" +
                "<br />\n" +
                "1. 请先给设备通电，并开启<br />\n" +
                "<br />\n" +
                "2. 进入 App 后，在首页点击右上方“+”添加设备，进入配网页面，选择相应的产品品类<br />\n" +
                "<br />\n" +
                "3. 将设备置于配网状态：Wi-Fi 指示灯快速闪烁（每秒闪烁2次）<br />\n" +
                "<br />\n" +
                "在“确认指示灯在快闪”按钮上方有“如何将指示灯设置为快闪”的操作说明，请您参考操作。<br />\n" +
                "<br />\n" +
                "如果像插座等有开关但是没复位键的产品的话一般长按开关5秒；<br />\n" +
                "<br />\n" +
                "如果是灯之类自己没有开关的话，连接到灯座上，操作“开-关-开-关-开” 3次，并停留到开灯状态，看到灯快闪即可；<br />\n" +
                "<br />\n" +
                "如果是有复位键的产品，一般就是按复位键就可以。<br />\n" +
                "<br />\n" +
                "建议您查看设备说明书可以更加快速将设备置于配网状态。<br />\n" +
                "<br />\n" +
                "第二步：输入Wi-Fi密码<br />\n" +
                "<br />\n" +
                "选择设备可连接互联⽹的 Wi-Fi，输入 Wi-Fi 密码，点击“确定”进入配网过程。现只支持2.4G的Wi-Fi进行配网，请检查该Wi-Fi是否是2.4G网络。<br />\n" +
                "<br />\n" +
                "最后，稍等几秒，显示配网成功即可。<br />\n" +
                "<br />\n" +
                "联网失败（添加设备失败）怎么排查<br />\n" +
                "<br />\n" +
                "1.确保设备通电并开机。<br />\n" +
                "<br />\n" +
                "2.确保设备处于待配网状态。<br />\n" +
                "<br />\n" +
                "3.确保设备、手机、路由器三者靠近。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "4.确保路由器、手机网络畅通。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "5.确保输入的路由器密码正确。<br />\n" +
                "<br />\n" +
                "6.确保使用 2.4G 的 Wi-Fi 频段添加设备，Wi-Fi 需要开启广播，不可设置为隐藏。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "7.确保路由器无线设置中加密方式为 WPA2-PSK 类型、认证类型为 AES，或两者皆设置为自动。 无线模式不能为 11n only。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "8.确保路由器 Wi-Fi 名称未使用中文。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "9.若路由器接入设备量达到上限，可尝试关闭某个设备的 Wi-Fi 功能空出通道重新配置。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "10.若路由器开启无线 MAC 地址过滤，可尝试将设备移出路由器的 MAC 过滤列表，保证路由器没有禁止设备联网。<br />\n" +
                "<br />\n" +
                "￼<br />\n" +
                "<br />\n" +
                "设备显示离线怎么办<br />\n" +
                "<br />\n" +
                "1.请确认设备是否通电状态；<br />\n" +
                "<br />\n" +
                "2.请排查下设备所在网络是否稳定，排查办法：将手机或者Ipad置于同一个网络，并放到设备边上，尝试打开网页；<br />\n" +
                "<br />\n" +
                "3.请确认家庭Wi-Fi网络是否正常，或者是否修改过Wi-Fi名称、密码等，如果有，请重置设备并重新添加；（设备移除方式：打开App进入该设备控制页面，点击右上角“…”按钮进入更多页面，页面下方点击“移除设备”）<br />\n" +
                "<br />\n" +
                "4.请尝试重启路由器之后，静待3分钟观察设备状态；<br />\n" +
                "<br />\n" +
                "5.如排查后网络正常也没有更改过的情况，请进入该设备的控制页面，点击右上角“…”按钮进入更多页面，点击“意见反馈”输入您遇到的问题，并提供您的路由器型号，我们将竭诚为您服务。<br />\n" +
                "<br />\n" +
                "APP相关<br />\n" +
                "<br />\n" +
                "APP无法远程控制设备，控制后不生效<br />\n" +
                "<br />\n" +
                "APP突然无法控制，可能是网络原因或者是设备已离线。<br />\n" +
                "<br />\n" +
                "1. 请确认设备是否通电状态；<br />\n" +
                "<br />\n" +
                "2. 建议您保持APP打开状态静待3分钟左右，看下该设备状态是否还是正常在线状态；<br />\n" +
                "<br />\n" +
                "3. 如果依然在线，请将手机置于设备旁边，然后连接跟设备相同的Wi-Fi，尝试打开网页看是否流畅，来判断网络状况。<br />\n" +
                "<br />\n" +
                "4. 如果设备在线，且网络良好，但是还是无法控制，请进入该设备的控制页面，点击右上角“…”按钮进入更多页面，点击”意见反馈”输入您遇到的问题，并提供您的路由器型号，我们将竭诚为您服务。<br />\n" +
                "<br />\n" +
                "设备联网后，在同一个地点可以控制，但是手机换别的地址或者别的网络就无法远程控制了<br />\n" +
                "<br />\n" +
                "如果设备遇到这样的情况，有可能是设备和手机是连接在同一个局域网，但是未连接至网络。<br />\n" +
                "<br />\n" +
                "1. 请将手机置于设备旁边，然后连接跟设备相同的Wi-Fi，尝试打开网页看是否可以打开，来判断Wi-Fi是否可连接至网络；<br />\n" +
                "<br />\n" +
                "2. 如果无法访问网络，建议重启下路由器；<br />\n" +
                "<br />\n" +
                "3. 如果设备已连接至网络，出现以上问题，请进入该设备的控制页面，点击右上角“…”按钮进入更多页面，点击”意见反馈”输入您遇到的问题，并提供您的路由器型号，我们将竭诚为您服务。<br />\n" +
                "<br />\n" +
                "设备状态无法与APP上显示的状态同步<br />\n" +
                "<br />\n" +
                "如果用物理开关控制设备后，打开APP可能设备状态未及时同步。<br />\n" +
                "<br />\n" +
                "建议您打开App，操作设备后APP上的设备状态即可更新。<br />\n" +
                "<br />\n" +
                "灯断电再来电后，原本关闭状态的灯突然开启<br />\n" +
                "<br />\n" +
                "该情况会出现本身无开关的灯具（且家里的开关非智能开关，未设置场景联动），此类设备如需联网，则需确保灯的物理开关是打开状态，然后可以用APP去控制灯。但是当断电后，灯和APP失去联系，这时候的灯就变成普通的灯，当再次来电时，这个灯之前的物理开关状态为“开”的状态，所以灯是开着的。<br />\n" +
                "<br />\n" +
                "倒计时设置后要怎么取消<br />\n" +
                "<br />\n" +
                "请将倒计时时间设置为0小时0分钟，即可取消倒计时。<br />\n" +
                "<br />\n" +
                "温度怎么切换摄氏度和华氏度<br />\n" +
                "<br />\n" +
                "请进入APP-个人中心，点击个人头像进入基本信息页，在“温度单位”选择您所需要的温度单位即可，更改后，后续设置场景等就会按照您选择的温度单位来执行。<br />\n" +
                "<br />\n" +
                "兼容模式配网没找到 SmarLife-xxxx怎么办？<br />\n" +
                "<br />\n" +
                "可以寻找WiFi列表中的 undefined-xxxx 或者其他类似形式热点进行连接。<br />\n" +
                "<br />\n" +
                "什么是本地场景？<br />\n" +
                "<br />\n" +
                "ZigBee 场景开关使用了特殊的设备连接方式，建立本地场景，支持在断网情况下执行，所以只能添加在同一网关下的ZigBee设备。<br />\n" +
                "<br />\n" +
                "第三方控制<br />\n" +
                "<br />\n" +
                "怎么查看我的设备支持哪些第三方控制？<br />\n" +
                "<br />\n" +
                "请先将设备添加至APP，并进入该设备控制页，点击右上角“…”按钮进入更多页面，在“支持的第三方控制”可查看到该设备支持的第三方控制，点击对应的第三方控制的图标，可查看该第三方控制的操作手册。<br />\n" +
                "<br />\n" +
                "第三方已连接但是无法语音控制怎么办？<br />\n" +
                "<br />\n" +
                "请在对应第三方控制的APP，查看该条指令是否被识别准确，具体可查看第三方控制的操作说明。<br />\n" +
                "</p>";
    }
}
