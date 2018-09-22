package com.zxon.commonrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = this.findViewById(R.id.main_recycler_view);

        CommonRecyclerViewAdapter adapter = new CommonRecyclerViewAdapter(this);

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 1; i++) {
                DataType1 dataType1 = new DataType1();
                dataType1.title = "title :  " + i;
                dataType1.content = "content : " + i;
                adapter.mCommonItemModel.add(new CommonData1(dataType1));
            }

            for (int i = 0; i < 1; i++) {
                DataType2 dataType2 = new DataType2();
                dataType2.title = "title :  " + i;
                dataType2.content = "content : " + i;
                adapter.mCommonItemModel.add(new CommonData2(dataType2));
            }

            for (int i = 0; i < 1; i++) {
                DataType3 dataType3 = new DataType3();
                dataType3.title = "title :  " + i;
                dataType3.content = "content : " + i;
                adapter.mCommonItemModel.add(new CommonData3(dataType3));
            }
        }


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    class Common1ViewHolder extends CommonRecyclerViewAdapter.BaseCommonViewHolder {

        @BindView(R.id.list_item_title)
        TextView title;

        @BindView(R.id.list_item_content)
        TextView content;

        public Common1ViewHolder(Context context, ViewGroup parent, int layoutResId) {
            super(context, parent, layoutResId);
            ButterKnife.bind(this, itemView);
        }
    }
    class Common2ViewHolder extends CommonRecyclerViewAdapter.BaseCommonViewHolder {

        @BindView(R.id.list_item_title)
        TextView title;

        @BindView(R.id.list_item_content)
        TextView content;

        public Common2ViewHolder(Context context, ViewGroup parent, int layoutResId) {
            super(context, parent, layoutResId);
            ButterKnife.bind(this, itemView);
        }
    }

    class Common3ViewHolder extends CommonRecyclerViewAdapter.BaseCommonViewHolder {

        @BindView(R.id.list_item_title)
        TextView title;

        @BindView(R.id.list_item_content)
        TextView content;

        public Common3ViewHolder(Context context, ViewGroup parent, int layoutResId) {
            super(context, parent, layoutResId);
            ButterKnife.bind(this, itemView);
        }
    }

    class CommonData1 extends CommonRecyclerViewAdapter.BaseCommonItemModel<DataType1, Common1ViewHolder> {


        CommonData1(DataType1 item) {
            super(item);
        }

        @Override
        public RecyclerView.ViewHolder performCreateViewHolder(Context context, ViewGroup parent, int layoutResId) {
            return new Common1ViewHolder(context, parent, layoutResId);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.list_item_type1;
        }

        @Override
        protected void onBind(Common1ViewHolder viewHolder) {
            viewHolder.title.setText(realItem.title);
            viewHolder.content.setText(realItem.content);
        }
    }

    class CommonData2 extends CommonRecyclerViewAdapter.BaseCommonItemModel<DataType2, Common2ViewHolder> {


        CommonData2(DataType2 item) {
            super(item);
        }

        @Override
        public RecyclerView.ViewHolder performCreateViewHolder(Context context, ViewGroup parent, int layoutResId) {
            return new Common2ViewHolder(context, parent, layoutResId);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.list_item_type2;
        }

        @Override
        protected void onBind(Common2ViewHolder viewHolder) {
            viewHolder.title.setText(realItem.title);
            viewHolder.content.setText(realItem.content);
        }
    }
    class CommonData3 extends CommonRecyclerViewAdapter.BaseCommonItemModel<DataType3, Common3ViewHolder> {


        CommonData3(DataType3 item) {
            super(item);
        }

        @Override
        public RecyclerView.ViewHolder performCreateViewHolder(Context context, ViewGroup parent, int layoutResId) {
            return new Common3ViewHolder(context, parent, layoutResId);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.list_item_type3;
        }

        @Override
        protected void onBind(Common3ViewHolder viewHolder) {
            viewHolder.title.setText(realItem.title);
            viewHolder.content.setText(realItem.content);
        }
    }


    class BaseDataType {
        public String prefix;
        public String title;
        public String content;
    }

    class DataType1 extends BaseDataType {
        DataType1() {
            super.prefix = "type 1";
        }
    }

    class DataType2 extends BaseDataType {
        DataType2() {
            super.prefix = "type 2";
        }
    }

    class DataType3 extends BaseDataType {
        DataType3() {
            super.prefix = "type 3";
        }
    }
}
