package com.tencent.tmgp.yybtestsdk.appearance;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.tmgp.yybtestsdk.R;

public class ResultView {
    private LinearLayout parentView;

    public ResultView(LinearLayout parentView) {
        this.parentView = parentView;
    }

    /**
     * 根据blockTitle和content生成一个展示块视图
     *
     * @param blockTitle 展示块的标题
     * @param content    展示的内容
     */
    public void addView(String blockTitle, String content) {
        LayoutInflater inflater = LayoutInflater.from(AppUtils.getCurActivity());
        LinearLayout block = (LinearLayout) inflater.inflate(R.layout.layout_result_display, null);
        TextView title = block.findViewById(R.id.tv_result_view_title);
        title.setText(blockTitle + ":");

        TextView contentText = block.findViewById(R.id.tv_result_view_content);
        contentText.setText(content);
        parentView.addView(block);
    }
}
