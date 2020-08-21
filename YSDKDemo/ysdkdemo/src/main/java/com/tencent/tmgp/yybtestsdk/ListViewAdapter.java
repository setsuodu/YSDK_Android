package com.tencent.tmgp.yybtestsdk;

import android.content.Context;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.utils.ModuleUtils;
import com.tencent.ysdk.framework.common.ePlatform;

public class ListViewAdapter extends BaseAdapter {
    private SparseArray<BaseModule> mDataArray;
    private Context mContext;

    public ListViewAdapter(@NonNull Context context, @NonNull SparseArray<BaseModule> moduleArray) {
        this.mDataArray = moduleArray;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDataArray.size();
    }

    @Override
    public BaseModule getItem(int position) {
        return mDataArray.get(mDataArray.keyAt(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder {
        TextView name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View view = convertView;
//        if (view == null) {
//            view = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);
//            holder = new ViewHolder();
//            TextView itemText = view.findViewById(R.id.item_txt);
//            holder.name = itemText;
//            view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }

//        String item = getItem(position).name;
//        ePlatform tempPlat = ModuleUtils.getPlatform();
//        if (TextUtils.isEmpty(item)) {
//            return null;
//        }
//        if (ePlatform.QQ == tempPlat) {
//            if (item.equals("微信登录") || item.equals("游客登录")) {
//                view.getBackground().setAlpha(60);
//                holder.name.setTextColor(0x60000000);
//            }
//        } else if (ePlatform.WX == tempPlat) {
//            if (item.equals("QQ登录") || item.equals("游客登录")) {
//                view.getBackground().setAlpha(60);
//                holder.name.setTextColor(0x60000000);
//            }
//        } else if (ePlatform.Guest == tempPlat) {
//            if (item.equals("QQ登录") || item.equals("微信登录")) {
//                view.getBackground().setAlpha(60);
//                holder.name.setTextColor(0x60000000);
//            }
//        } else {
//            if (item.equals("支付模块")) {
//                view.getBackground().setAlpha(60);
//                holder.name.setTextColor(0x60000000);
//            } else {
//                view.getBackground().setAlpha(255);
//                holder.name.setTextColor(0xff000000);
//            }
//        }

//        holder.name.setText(item);
        return view;
    }
}
