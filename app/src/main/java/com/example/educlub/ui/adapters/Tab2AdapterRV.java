package com.example.educlub.ui.adapters;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educlub.R;
import com.example.educlub.pojo.Group;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import at.blogc.android.views.ExpandableTextView;

public class Tab2AdapterRV extends RecyclerView.Adapter<Tab2AdapterRV.ViewHolder> {
    private ArrayList<Group> groups;

    public Tab2AdapterRV(ArrayList<Group> groups) {
        this.groups = groups;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tab2_item_index_fragment, viewGroup, false);
        return new Tab2AdapterRV.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.onBind(groups.get(i));
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTV;
        private TextView typeTV;
        private TextView categoryTV;
        private TextView subCategoryTV;
        private ExpandableTextView descTV;
        private RecyclerView tegRV;
        private TextView adresTV;
        private TextView startTimeTV;
        private TextView endTimeTV;
        private TextView mondayTV;
        private TextView tuesdayTV;
        private TextView wednesdayTV;
        private TextView thursdayTV;
        private TextView fridayTV;
        private TextView saturdayTV;
        private TextView sundayTV;
        private ImageView joinIV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.name_group);
            typeTV=itemView.findViewById(R.id.type_group);
            categoryTV=itemView.findViewById(R.id.category_group);
            subCategoryTV=itemView.findViewById(R.id.subcategory_group);
            descTV=itemView.findViewById(R.id.desc_group);
            tegRV=itemView.findViewById(R.id.tegRV_group);
            adresTV=itemView.findViewById(R.id.addressTV_group);
            startTimeTV=itemView.findViewById(R.id.startTimeTV_group);
            endTimeTV=itemView.findViewById(R.id.endTimeTV_group);
            mondayTV=itemView.findViewById(R.id.mondayTV);
            tuesdayTV=itemView.findViewById(R.id.tuesdayTV);
            wednesdayTV=itemView.findViewById(R.id.wednesdayTV);
            thursdayTV=itemView.findViewById(R.id.thursdayTV);
            fridayTV=itemView.findViewById(R.id.fridayTV);
            saturdayTV=itemView.findViewById(R.id.saturdayTV);
            sundayTV=itemView.findViewById(R.id.sundayTV);
            joinIV=itemView.findViewById(R.id.joinIV_group);

            descTV.setAnimationDuration(750L);
            descTV.setInterpolator(new OvershootInterpolator());
            descTV.setExpandInterpolator(new OvershootInterpolator());
            descTV.setCollapseInterpolator(new OvershootInterpolator());
            descTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    descTV.toggle();
                }
            });
        }


        void onBind(Group group) {
            SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
            nameTV.setText(group.getName());
            typeTV.setText(group.getTypaName());
            categoryTV.setText(group.getCategory());
            subCategoryTV.setText(group.getSubcategory());
            descTV.setText(group.getDesc());
            //tegRV ni qilish kerak
            adresTV.setText(group.getAddress());
            startTimeTV.setText(dateFormat.format(group.getStartTime()));
            endTimeTV.setText(dateFormat.format(group.getEndTime()));
            String [] days= group.getWeekDays().split(",");
            for(String item : days){
                switch (item){
                    case "1":{
                        mondayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                    case "2":{
                        tuesdayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                    case "3":{
                        wednesdayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                    case "4":{
                        thursdayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                    case "5":{
                        fridayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                    case "6":{
                        saturdayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                    case "7":{
                        sundayTV.setBackgroundResource(R.drawable.bg_red);
                    }break;
                }
            }
        }
    }
}
