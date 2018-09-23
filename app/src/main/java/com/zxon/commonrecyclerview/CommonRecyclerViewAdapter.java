package com.zxon.commonrecyclerview;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<BaseCommonItemModel<?, ?>> mCommonItemModel = new ArrayList();

    Context mContext;

    CommonRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mCommonItemModel.get(viewType).onCreateViewHolder(mContext, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mCommonItemModel.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return mCommonItemModel.size();
    }

    public void addItem(BaseCommonItemModel<?, ?> baseCommonItemModel) {
        mCommonItemModel.add(mCommonItemModel.size(), baseCommonItemModel);
    }

    public void addItem(int index, BaseCommonItemModel<?, ?> baseCommonItemModel) {
        mCommonItemModel.add(index, baseCommonItemModel);
    }

    private void checkNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }


    public static abstract class BaseCommonItemModel<T, VH extends BaseCommonViewHolder> {
        T realItem;

        BaseCommonItemModel(T item) {
            realItem = item;
        }

        public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
            //noinspection unchecked
            onBind((VH) viewHolder);
        }

        protected abstract void onBind(VH viewHolder);

        public RecyclerView.ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
            return getCommonViewHolderCreator().create(context, parent, getLayoutResId());
        }

        public abstract CommonViewHolderCreator getCommonViewHolderCreator();

        @LayoutRes
        public abstract int getLayoutResId();
    }

    public static abstract class BaseCommonViewHolder extends RecyclerView.ViewHolder {
        public BaseCommonViewHolder(Context context, ViewGroup parent, int layoutResId) {
            super(LayoutInflater.from(context).inflate(layoutResId, parent, false));
        }
    }

    public interface CommonViewHolderCreator {
        RecyclerView.ViewHolder create(Context context, ViewGroup parent, int layoutResId);
    }
}
