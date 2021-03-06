package com.amakenapp.website.amakenapp.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.amakenapp.website.amakenapp.R;
import com.amakenapp.website.amakenapp.activities.ExpandDetailsMapsActivityEvent;

import java.util.List;

/**
 * Created by User on 3/11/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<EventsListItem> listItems;
    private Context context;

    public EventsAdapter(List<EventsListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latestevent_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventsListItem listItem =listItems.get(position);

        Glide.with(context).load(listItem.getEventBusinessProfileImage()).into(holder.eventBusinessProfileImage);
        holder.eventBusinessName.setText(listItem.getEventBusinessName());
        Glide.with(context).load(listItem.getEvent_availableOrBusyLogo()).into(holder.event_availableOrBusyLogo);

        Glide.with(context).load(listItem.getEventPicture()).into(holder.eventPicture);

        holder.eventName.setText(listItem.getEventName());
        holder.eventCategory.setText(listItem.getEventCategory());
        holder.eventDescription.setText(listItem.getEventDescription());
        holder.eventDescriptionMultiLineText.setText(listItem.getEventDescriptionMultiLineText());

        holder.eventRatingStat.setText(listItem.getEventRatingStat());
        holder.ratingEvent.setRating(listItem.getRatingEvent());
        holder.eventExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),
                        ExpandDetailsMapsActivityEvent.class);
                context.startActivity(myIntent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView eventBusinessProfileImage;
        public TextView eventBusinessName;
        public ImageView event_availableOrBusyLogo;

        public ImageView eventPicture;
        public TextView eventName;
        public TextView eventCategory;
        public TextView eventDescription;
        public TextView eventDescriptionMultiLineText;
        public TextView eventExpand;

        public TextView eventRatingStat;
        public RatingBar ratingEvent;


        public ViewHolder(View itemView) {
            super(itemView);

            eventBusinessProfileImage=(ImageView) itemView.findViewById(R.id.imageViewEventBusinessProfile);
            eventBusinessName= (TextView) itemView.findViewById(R.id.TextEventBusinessName);
            event_availableOrBusyLogo=(ImageView) itemView.findViewById(R.id.event_on_logo);


            eventPicture=(ImageView) itemView.findViewById(R.id.imageViewEventBusinessPlace);
            eventName = (TextView) itemView.findViewById(R.id.events_eventName) ;
            eventCategory= (TextView) itemView.findViewById(R.id.events_eventCategory);
            eventDescription=(TextView) itemView.findViewById(R.id.TextEventsDiscretion);
            eventDescriptionMultiLineText=(TextView) itemView.findViewById(R.id.TextEventDiscretionMultiLine);

            eventExpand=(TextView) itemView.findViewById(R.id.TextExpandEvent);
            ratingEvent =(RatingBar) itemView.findViewById(R.id.ratingBarEvent);
            eventRatingStat =(TextView) itemView.findViewById(R.id.TextEventNumberOfRate);


        }

    }
}
