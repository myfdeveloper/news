package com.myf.health.ui.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myf.health.R;
import com.myf.health.bean.NewsBean;
import com.myf.health.ui.AnimRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class NewsAdapter extends AnimRecyclerViewAdapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<NewsBean> mData;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    @Inject
    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    public void setmDate(List<NewsBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewsBean news = mData.get(position);
            if (news == null) {
                return;
            }
            ((ItemViewHolder) holder).mTitle.setText(news.getTitle());
            ((ItemViewHolder) holder).mDesc.setText(news.getDigest());
            Glide.with(mContext).load(news.getImgsrc()).into( ((ItemViewHolder) holder).mNewsImg);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public NewsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTitle)
        public TextView mTitle;
        @Bind(R.id.tvDesc)
        public TextView mDesc;
        @Bind(R.id.ivNews)
        public ImageView mNewsImg;

        public ItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.cardView)
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getLayoutPosition());
            }
        }
    }

}
