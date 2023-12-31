package com.example.travelplanner.adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.data.Destination;
import com.example.travelplanner.data.Destination2;
import com.example.travelplanner.data.MyPrefs;
import com.example.travelplanner.data.Trips;
import com.example.travelplanner.fragments.HomeFragment;
import com.example.travelplanner.utils.FavoriteManager;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {
    public List<Destination> destinationList;
    private List<Trips> tripsList;
    private Context context;

    public DestinationAdapter(Context context, List<Destination> destinationList) {
        this.context = context;
        this.destinationList = destinationList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Destination destination = destinationList.get(position);

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
                    FavoriteManager.addFavoriteDestination(destination);

                } else {
                    holder.heartIcon.setImageResource(R.drawable.heart_border);
                    FavoriteManager.removeFavoriteDestination(destination);
                }
            }
        });

        holder.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context != null) {
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
                } else {
                    Log.d("DestinationAdapter", "Fragment or Fragment Activity is null");
                }
            }
        });
        holder.addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Create a Trips object
                    Trips trips = createTripsList(destination);

                    // Convert the Trips object to JSON
                    String tripsJson = new Gson().toJson(trips);

                    // Save the JSON data to a file
                    saveTripsToJsonFile(context, tripsJson);
            }
        });

    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView destinationImage;
        public TextView destinationName;
        public TextView description;
        public ImageView heartIcon;
        public ImageView searchIcon;
        public ImageView addIcon;
        public TextView budget;
        public EditText date;

        public ViewHolder(View itemView) {
            super(itemView);
            destinationImage = itemView.findViewById(R.id.destinationImage);
            destinationName = itemView.findViewById(R.id.destinationName);
            description = itemView.findViewById(R.id.description);
            budget = itemView.findViewById(R.id.budget);
            heartIcon = itemView.findViewById(R.id.heart);
            searchIcon = itemView.findViewById(R.id.glass);
            addIcon = itemView.findViewById(R.id.addtotrips);
            date = itemView.findViewById(R.id.dateInput);
        }
    }
    private Trips createTripsList(Destination destination){
        Toast.makeText(context, "tyfuhio", Toast.LENGTH_SHORT).show();
        return (new Trips("India","Delhi",destination.getName(),destination.getCapital(),""));
    }
    private void saveTripsToJsonFile(Context context, String jsonData) {
        // Define a filename for the JSON file
        String filename = "C:\\Users\\mstdh\\Documents\\Codes\\Java\\AndroidDevelopment\\Apps\\VES\\TravelPlanner\\app\\src\\main\\java\\com\\example\\travelplanner\\data\\trips.json";

        try {
            // Open the file for writing
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);

            // Write the JSON data to the file
            fos.write(jsonData.getBytes());
            fos.close();

            // Show a toast message indicating success
            Toast.makeText(context, "Trips added and saved to file.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








