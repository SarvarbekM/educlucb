package com.example.educlub.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.educlub.App;
import com.example.educlub.R;
import com.example.educlub.pojo.Company;
import com.example.educlub.ui.fragments.Tab1Fragment;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;


public class Tab1AdapterRV extends RecyclerView.Adapter<Tab1AdapterRV.ViewHolder> {

    private List<Company> list;
    private  Fragment fragment;

    public<T extends Fragment> Tab1AdapterRV(List<Company> list, T fragment) {
        this.list = list;
        this.fragment=  fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab1_item_index_fragment, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onbind(list.get(position));
        //animate(holder);
    }

    @Override
    public int getItemCount() {
        return list!=null ? list.size():0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insert(int position, Company data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    public void remove(Company data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

//    private void animate(RecyclerView.ViewHolder viewHolder) {
//        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
//        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
//    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView companyLogoIV;
        private TextView companyNameTV;
        private ExpandableTextView descTV;
        private TextView phoneT;
        private TextView linkTV;
        private TextView addressTV;

        ViewHolder(View itemView) {
            super(itemView);
            companyLogoIV = itemView.findViewById(R.id.companyLogoIV);
            companyNameTV = itemView.findViewById(R.id.companyNameTV);
            descTV = itemView.findViewById(R.id.expandableTextView);
            phoneT = itemView.findViewById(R.id.phoneT);
            linkTV = itemView.findViewById(R.id.linkTV);
            addressTV = itemView.findViewById(R.id.addressTV);
            descTV.setAnimationDuration(750L);
            descTV.setInterpolator(new OvershootInterpolator());
            descTV.setExpandInterpolator(new OvershootInterpolator());
            descTV.setCollapseInterpolator(new OvershootInterpolator());
            descTV.setOnClickListener(v -> {
                descTV.toggle();
                if (!descTV.isExpanded()) {
                    descTV.setTextColor(App.getInstance().getResources().getColor(R.color.black));
                } else {
                    descTV.setTextColor(App.getInstance().getResources().getColor(R.color.test_desc_company));
                }
            });
//Clear cache:  https://www.raywenderlich.com/2945946-glide-tutorial-for-android-getting-started
        }

        void onbind(Company company) {
            companyLogoIV.setImageResource(R.drawable.ic_add_black_24dp);
            companyNameTV.setText(company.getName());
            descTV.setText(company.getDesc());
            phoneT.setText(company.getPhone());
            linkTV.setText(company.getLink());
            addressTV.setOnClickListener(view -> showMap(company.getLatitude(), company.getLongitude()));
            SpannableString content = new SpannableString(company.getAddress()==null?"Not Address":company.getAddress());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            addressTV.setText(content);
            Glide.with(fragment) //1
                    .load(Uri.parse(company.getLogoLink()))
                    .placeholder(R.drawable.bg_silver) //5
                    .error(R.drawable.cm_ic_plus) //6
                    .fallback(R.drawable.bg_red) //7
                    .skipMemoryCache(true) //2
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //3
                    .transform(new CircleCrop()) //4
                    .into(companyLogoIV);

//            Glide.with(context)  //2
//                    .load(company.getLogoLink()) //3
//                    .centerCrop() //4
//                    .placeholder(R.drawable.bg_silver) //5
////                    .error(R.drawable.cm_ic_plus) //6
////                    .fallback(R.drawable.bg_red) //7
//                    .into(companyLogoIV); //8
        }

        void showMap(double lat, double lon) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+lat+","+lon+"(Center)");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
//            if (mapIntent.resolveActivity(getPackageManager()) != null)
            {
                fragment.startActivity(mapIntent);
            }

        }

    }}
