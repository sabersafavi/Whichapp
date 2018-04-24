package com.saber.test.whichapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.saber.test.whichapp.R;
import com.saber.test.whichapp.models.CountriesListData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
    *recyclerviews adapter for rendering countries
    * view injection in view holder provided by butterknife
    * header section and footer section added to this adapter
 */


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;


    private final OnItemClickListener listener;
    private List<CountriesListData> data;
    private Context context;

    public HomeAdapter(Context context, List<CountriesListData> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
            return new ViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            //Inflating header view
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false);
            return new HeaderViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, parent, false);
            return new FooterViewHolder(itemView);
        }
        return null;


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == data.size() + 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.headerTitle.setText(R.string.header_title);
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.footerText.setText(R.string.footer_title);
        } else if (holder instanceof ViewHolder && position > 0 && position <= data.size()) {

            ViewHolder itemViewHolder = (ViewHolder) holder;
            itemViewHolder.click(data.get(position - 1), listener);
            itemViewHolder.tvCountry.setText(data.get(position - 1).getName());
            itemViewHolder.tvIso.setText(data.get(position - 1).getIso());
            itemViewHolder.tvPhone.setText(data.get(position - 1).getPhone());
        }


    }


    @Override
    public int getItemCount() {
        return data.size() + 2;
    }


    public interface OnItemClickListener {
        void onClick(CountriesListData Item);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header_text)
        TextView headerTitle;

        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.footer_text)
        TextView footerText;

        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.tvCountry)
        TextView tvCountry;
        @Nullable
        @BindView(R.id.tvIso)
        TextView tvIso;
        @Nullable
        @BindView(R.id.tvPhone)
        TextView tvPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void click(final CountriesListData CountryListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(CountryListData);
                }
            });
        }
    }


}
