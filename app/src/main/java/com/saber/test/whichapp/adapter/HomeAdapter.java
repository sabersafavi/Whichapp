package com.saber.test.whichapp.adapter;

import android.content.Context;
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

        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new ViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            //Inflating header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false);
            return new HeaderViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, parent, false);
            return new FooterViewHolder(itemView);
        } else return null;


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
        } else if (holder instanceof ViewHolder) {

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

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        public HeaderViewHolder(View view) {
            super(view);
            headerTitle = (TextView) view.findViewById(R.id.header_text);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView footerText;

        public FooterViewHolder(View view) {
            super(view);
            footerText = (TextView) view.findViewById(R.id.footer_text);
        }
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry, tvIso, tvPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvIso = itemView.findViewById(R.id.tvIso);
            tvPhone = itemView.findViewById(R.id.tvPhone);

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
