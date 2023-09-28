package com.example.travelplanner.adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.data.Destination;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private List<Destination> favoriteDestinations;
    private Context context;
    public FavouriteAdapter(Context context,List<Destination> destinationList) {
        this.context = context;
        this.favoriteDestinations = favoriteDestinations;
    }
    public FavouriteAdapter(List<Destination> favoriteDestinations) {
        this.favoriteDestinations = favoriteDestinations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_destination_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Destination destination = favoriteDestinations.get(position);

        // Bind destination data to ViewHolder views
        holder.destinationName.setText(destination.getName());
        holder.description.setText(destination.getDescription());
        holder.destinationImage.setImageResource(destination.getImageResourceId());
        holder.budget.setText(destination.getBudget());
        holder.heartIcon.setOnClickListener(new View.OnClickListener() {
            boolean isLiked = false; // Track whether it's liked or not
            @Override
            public void onClick(View view) {
                isLiked = !isLiked; // Toggle the liked state

                if (isLiked) {
                    holder.heartIcon.setImageResource(R.drawable.heart_fill);
                } else {
                    holder.heartIcon.setImageResource(R.drawable.heart_border);
                }
            }
        });

        holder.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context!=null) {
                    Dialog featuresDialog = new Dialog(context);
                    featuresDialog.setContentView(R.layout.dialog_features);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(featuresDialog.getWindow().getAttributes());
                    layoutParams.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.8);
                    layoutParams.height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.5);

                    featuresDialog.getWindow().setAttributes(layoutParams);

                    ImageView dialogImageView = featuresDialog.findViewById(R.id.imagefd);

                    TextView fdname = featuresDialog.findViewById(R.id.destinationNamefd);
                    TextView fddetail = featuresDialog.findViewById(R.id.destinationdetailfd);

                    TextView fdticket = featuresDialog.findViewById(R.id.airtickets);
                    TextView fdstay = featuresDialog.findViewById(R.id.stay);
                    TextView fdeat = featuresDialog.findViewById(R.id.places2eat);
                    TextView fdvisit = featuresDialog.findViewById(R.id.places2visit);
                    TextView fdduration = featuresDialog.findViewById(R.id.duration);
                    TextView fdcal = featuresDialog.findViewById(R.id.calculate);
                    TextView fdtips = featuresDialog.findViewById(R.id.tip);
                    TextView fdtime = featuresDialog.findViewById(R.id.time);
                    TextView fdcurrency = featuresDialog.findViewById(R.id.currency);
                    TextView fdlang = featuresDialog.findViewById(R.id.language);
                    TextView fdreach = featuresDialog.findViewById(R.id.reach);

                    dialogImageView.setImageResource(destination.getImageResourceId());
                    fdname.setText(destination.getDianame());
                    fddetail.setText(destination.getDetail());
                    fdticket.setText(destination.getTicket());
                    fdstay.setText(destination.getStay());
                    fdeat.setText(destination.getEat());
                    fdvisit.setText(destination.getVisit());
                    fdduration.setText(destination.getDuration());
                    fdcal.setText(destination.getCalculation());
                    fdtips.setText(destination.getTips());
                    fdtime.setText(destination.getTime());
                    fdcurrency.setText(destination.getCurrency());
                    fdlang.setText(destination.getLanguage());
                    fdreach.setText(destination.getReach());

                    featuresDialog.setTitle("Features Dialog");
                    featuresDialog.show();
                }
                else {
                    Log.d("DestinationAdapter", "Fragment or Fragment Activity is null");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteDestinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView destinationImage;
        public TextView destinationName;
        public TextView description;
        public ImageView heartIcon;
        public ImageView searchIcon;
        public TextView budget;

        public ViewHolder(View itemView) {
            super(itemView);
            destinationImage = itemView.findViewById(R.id.destinationImage);
            destinationName = itemView.findViewById(R.id.destinationName);
            description = itemView.findViewById(R.id.description);
            budget = itemView.findViewById(R.id.budget);
            heartIcon = itemView.findViewById(R.id.heart);
            searchIcon = itemView.findViewById(R.id.glass);

            // Initialize other views here if needed
        }
    }
}
