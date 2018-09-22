package com.zxon.commonrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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


        adapter.mClassToTypeMap.put(CommonData3.class, 3);
        adapter.mClassToTypeMap.put(CommonData2.class, 2);
        adapter.mClassToTypeMap.put(CommonData1.class, 1);

        adapter.mTypeToCreator.put(1, new CommonData1ViewHolderCreator());
        adapter.mTypeToCreator.put(2, new CommonData2ViewHolderCreator());
        adapter.mTypeToCreator.put(3, new CommonData3ViewHolderCreator());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    class CommonData1ViewHolder extends CommonRecyclerViewAdapter.CommonViewHolder {

        @BindView(R.id.list_item_title)
        TextView title;
        @BindView(R.id.list_item_content)
        TextView content;

        public CommonData1ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CommonData2ViewHolder extends CommonRecyclerViewAdapter.CommonViewHolder {

        @BindView(R.id.list_item_title)
        TextView title;
        @BindView(R.id.list_item_content)
        TextView content;

        public CommonData2ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(CommonData2ViewHolder.this, itemView);
        }
    }

    class CommonData3ViewHolder extends CommonRecyclerViewAdapter.CommonViewHolder {

        @BindView(R.id.list_item_title)
        TextView title;
        @BindView(R.id.list_item_content)
        TextView content;

        public CommonData3ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class CommonData1ViewHolderCreator extends CommonRecyclerViewAdapter.BaseCommonViewHolderCreator<CommonData1ViewHolder> {

        @Override
        int getLayoutResId() {
            return R.layout.list_item_type1;
        }

        @Override
        protected CommonData1ViewHolder createViewHolder(View view) {
            return new CommonData1ViewHolder(view);
        }
    }

    class CommonData2ViewHolderCreator extends CommonRecyclerViewAdapter.BaseCommonViewHolderCreator<CommonData2ViewHolder> {

        @Override
        int getLayoutResId() {
            return R.layout.list_item_type2;
        }

        @Override
        protected CommonData2ViewHolder createViewHolder(View view) {
            return new CommonData2ViewHolder(view);
        }
    }

    class CommonData3ViewHolderCreator extends CommonRecyclerViewAdapter.BaseCommonViewHolderCreator<CommonData3ViewHolder> {

        @Override
        int getLayoutResId() {
            return R.layout.list_item_type3;
        }

        @Override
        protected CommonData3ViewHolder createViewHolder(View view) {
            return new CommonData3ViewHolder(view);
        }
    }


    class CommonData1 extends CommonRecyclerViewAdapter.BaseCommonItemModel<DataType1, CommonData1ViewHolder> {

        CommonData1(DataType1 item) {
            super(item);
        }

        @Override
        protected void onBind(CommonData1ViewHolder viewHolder) {
            viewHolder.title.setText(realItem.prefix + realItem.title);
            viewHolder.content.setText(realItem.prefix + realItem.content);
        }
    }

    class CommonData2 extends CommonRecyclerViewAdapter.BaseCommonItemModel<DataType2, CommonData2ViewHolder> {

        CommonData2(DataType2 item) {
            super(item);
        }

        @Override
        protected void onBind(CommonData2ViewHolder viewHolder) {
            viewHolder.title.setText(realItem.prefix + realItem.title);
            viewHolder.content.setText(realItem.prefix + realItem.content);
        }
    }

    class CommonData3 extends CommonRecyclerViewAdapter.BaseCommonItemModel<DataType3, CommonData3ViewHolder> {

        CommonData3(DataType3 item) {
            super(item);
        }

        @Override
        protected void onBind(CommonData3ViewHolder viewHolder) {
            viewHolder.title.setText(realItem.prefix + realItem.title);
            viewHolder.content.setText(realItem.prefix + realItem.content);
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
