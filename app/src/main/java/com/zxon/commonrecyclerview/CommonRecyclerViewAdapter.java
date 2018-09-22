package com.zxon.commonrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<BaseCommonItemModel<?, ?>> mCommonItemModel = new ArrayList();

    // todo : visibility
    public HashMap<Class, Integer> mClassToTypeMap = new HashMap<>();
    public HashMap<Integer, BaseCommonViewHolderCreator> mTypeToCreator = new HashMap<>();

    Context mContext;

    CommonRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        Integer value = mClassToTypeMap.get(mCommonItemModel.get(position).getClass());
        checkNotNull(value);
        return value;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseCommonViewHolderCreator baseCommonViewHolderCreator = mTypeToCreator.get(viewType);
        checkNotNull(baseCommonViewHolderCreator);
        return baseCommonViewHolderCreator.getViewHolder(mContext, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mCommonItemModel.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return mCommonItemModel.size();
    }

    public void addItem(BaseCommonItemModel<?,?> baseCommonItemModel) {
        mCommonItemModel.add(mCommonItemModel.size(), baseCommonItemModel);
    }

    public void addItem(int index, BaseCommonItemModel<?,?> baseCommonItemModel) {
        mCommonItemModel.add(index, baseCommonItemModel);
    }

    private void checkNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }

    public static abstract class BaseCommonItemModel<T, VH extends CommonViewHolder> {
        T realItem;

        BaseCommonItemModel(T item) {
            realItem = item;
        }

        public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
            onBind((VH) viewHolder);
        }

        protected abstract void onBind(VH viewHolder);

    }

    public static class CommonViewHolder extends RecyclerView.ViewHolder {

        public CommonViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static abstract class BaseCommonViewHolderCreator<VH extends RecyclerView.ViewHolder> {

        abstract int getLayoutResId();

        RecyclerView.ViewHolder getViewHolder(Context context, ViewGroup parent) {
            View view = getView(context, parent);
            return createViewHolder(view);
        }

        protected abstract VH createViewHolder(View view);

        protected View getView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(getLayoutResId(), parent, false);
        }
    }
}
