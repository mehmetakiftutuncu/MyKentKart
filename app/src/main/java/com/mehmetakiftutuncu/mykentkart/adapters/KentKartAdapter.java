/*
 * Copyright (C) 2015 Mehmet Akif Tütüncü
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mehmetakiftutuncu.mykentkart.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mehmetakiftutuncu.mykentkart.R;
import com.mehmetakiftutuncu.mykentkart.activities.KentKartEditActivity;
import com.mehmetakiftutuncu.mykentkart.activities.KentKartInformationActivity;
import com.mehmetakiftutuncu.mykentkart.models.KentKart;
import com.mehmetakiftutuncu.mykentkart.utilities.Constants;
import com.mehmetakiftutuncu.mykentkart.utilities.StringUtils;

import java.util.ArrayList;

/**
 * A {@link android.support.v7.widget.RecyclerView.Adapter} that maps each
 * {@link com.mehmetakiftutuncu.mykentkart.models.KentKart} to a
 * {@link android.support.v7.widget.CardView} in KentKart list
 *
 * @author mehmetakiftutuncu
 */
public class KentKartAdapter extends RecyclerView.Adapter<KentKartAdapter.ViewHolder> {
    /**
     * A utility class containing data of a {@link com.mehmetakiftutuncu.mykentkart.models.KentKart}
     * and references to it's components in {@link android.support.v7.widget.CardView} representing it
     *
     * @author mehmetakiftutuncu
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** Name of the KentKart */
        public String name;
        /** Number of the KentKart */
        public String number;
        /** Region code of the KentKart */
        public String regionCode;
        /** Reference to {@link android.widget.TextView} that shows name of the KentKart */
        public TextView nameTextView;
        /** Reference to {@link android.widget.TextView} that shows number of the KentKart */
        public TextView numberTextView;
        /** Reference to {@link android.widget.ImageView} that indicates when the KentKart is saved using NFC */
        public ImageView nfcImageView;
        /** Reference to {@link android.widget.ImageButton} that is used to edit the KentKart */
        public ImageButton editImageButton;

        /**
         * Constructor initializing all values
         *
         * @param view A reference to {@link android.support.v7.widget.CardView}
         */
        public ViewHolder(View view) {
            super(view);

            this.nameTextView = (TextView) view.findViewById(R.id.textView_kentKart_name);
            this.numberTextView = (TextView) view.findViewById(R.id.textView_kentKart_number);
            this.nfcImageView = (ImageView) view.findViewById(R.id.imageView_kentKart_nfcState);
            this.editImageButton = (ImageButton) view.findViewById(R.id.imageButton_kentKart_edit);
        }
    }

    /** An {@link java.util.ArrayList} of {@link com.mehmetakiftutuncu.mykentkart.models.KentKart}s as data source */
    private ArrayList<KentKart> kentKarts;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_kentkart, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KentKartInformationActivity.class);
                intent.putExtra(Constants.KENT_KART_NAME, viewHolder.name);
                intent.putExtra(Constants.KENT_KART_NUMBER, viewHolder.number);
                intent.putExtra(Constants.KENT_KART_REGION_CODE, viewHolder.regionCode);
                ((Activity) v.getContext()).startActivityForResult(intent, KentKartEditActivity.REQUEST_CODE);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final KentKart kentKart = kentKarts != null ? kentKarts.get(position) : null;

        if (kentKart != null) {
            viewHolder.name = kentKart.name;
            viewHolder.number = kentKart.number;
            viewHolder.regionCode = kentKart.regionCode;

            viewHolder.nameTextView.setText(kentKart.name);
            viewHolder.numberTextView.setText(kentKart.getFormattedNumber());
            viewHolder.nfcImageView.setVisibility(StringUtils.isEmpty(kentKart.nfcId) ? View.GONE : View.VISIBLE);

            viewHolder.editImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), KentKartEditActivity.class);
                    intent.putExtra(Constants.EDIT_MODE, true);
                    intent.putExtra(Constants.KENT_KART_NAME, kentKart.name);
                    intent.putExtra(Constants.KENT_KART_NUMBER, kentKart.number);
                    intent.putExtra(Constants.KENT_KART_REGION_CODE, kentKart.regionCode);
                    intent.putExtra(Constants.KENT_KART_NFC_ID, kentKart.nfcId);

                    ((Activity) v.getContext()).startActivityForResult(intent, KentKartEditActivity.REQUEST_CODE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return kentKarts != null ? kentKarts.size() : 0;
    }

    /**
     * Gets current data source
     *
     * @return {@link com.mehmetakiftutuncu.mykentkart.adapters.KentKartAdapter#kentKarts}
     */
    public ArrayList<KentKart> getKentKarts() {
        return kentKarts;
    }

    /**
     * Sets current data source
     *
     * @param kentKarts Value to set as {@link com.mehmetakiftutuncu.mykentkart.adapters.KentKartAdapter#kentKarts}
     */
    public void setKentKarts(ArrayList<KentKart> kentKarts) {
        this.kentKarts = kentKarts;
        notifyDataSetChanged();
    }
}
