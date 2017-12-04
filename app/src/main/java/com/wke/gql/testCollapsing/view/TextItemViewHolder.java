package com.wke.gql.testCollapsing.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.R;


public class TextItemViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private View separator;

    public TextItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.list_item);
        separator = itemView.findViewById(R.id.separator);
    }

    public void bind(String text) {
        textView.setText(text);
    }

    public void hideSeparator(boolean hide) {
        if (hide) {
            separator.setVisibility(View.GONE);
        } else {
            separator.setVisibility(View.VISIBLE);
        }
    }
}
