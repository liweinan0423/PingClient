package ping.ui.list;

import javax.swing.*;



public class PingInfoList extends JTable {

    private static final String[] COLUMN_NAMES = {"服务器", "地址", "成功次数", "失败次数", "平均延迟(ms)",  "状态"};

    private static final Object[][] SAMPLE_DATA = {
            {"服务器1", "192.168.0.1", "10", "10", 25,  "正常"},
            {"服务器2", "192.168.0.2", "10", "10", 35, "正常"},
            {"服务器3", "192.168.0.3", "10", "10", 100, "正常"},
            {"服务器4", "192.168.0.4", "10", "10", 125, "正常"},
            {"服务器5", "192.168.0.5", "0",  "10", "--", "异常"},
    };


    public PingInfoList() {
        super(SAMPLE_DATA, COLUMN_NAMES);
    }

}
