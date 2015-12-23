package com.yujin.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujin on 2015/12/23.
 */
public class GridViewActivity extends ActionBarActivity implements AdapterView.OnItemLongClickListener {
    public static final String TAG = GridViewActivity.class.getSimpleName();
    private GridView mGridView;
    private GridViewAdapter mAdapter;
    private List<String> list  = new ArrayList<String>();
    private boolean isShowDelete=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        initData();
        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setOnItemLongClickListener(this);
        mAdapter=new GridViewAdapter(GridViewActivity.this, list);
        mGridView.setAdapter(mAdapter);

    }
    private void initData() {
        for (int i = 'A'; i <= 'z'; i++) {
            list.add("" + (char) i);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (isShowDelete) {
            isShowDelete = false;
        } else {
            isShowDelete = true;
        }
        mAdapter.setIsShowDelete(isShowDelete);
        return true;
    }

    class GridViewAdapter extends BaseAdapter {
        private List<String> mList;
        private Context mContext;
        private TextView name_tv;
        private ImageView img;
        private View deleteView;
        private boolean isShowDelete;//根据这个变量来判断是否显示删除图标，true是显示，false是不显示

        public GridViewAdapter(Context mContext,List<String> list) {
            this.mContext = mContext;
            this.mList = list;
        }
        public void setIsShowDelete(boolean isShowDelete){
            this.isShowDelete=isShowDelete;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            img = (ImageView) convertView.findViewById(R.id.img);
            name_tv = (TextView) convertView.findViewById(R.id.name_tv);
            deleteView = convertView.findViewById(R.id.delete_markView);
            deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);//设置删除按钮是否显示
            deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    mAdapter.notifyDataSetChanged();
                }
            });
            img.setBackgroundResource(R.drawable.avatar_fish);
            name_tv.setText(mList.get(position));
            return convertView;
        }
    }
}
