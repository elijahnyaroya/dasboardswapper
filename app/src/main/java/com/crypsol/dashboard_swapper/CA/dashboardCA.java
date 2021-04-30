    package com.crypsol.dashboard_swapper.CA;

    import android.annotation.SuppressLint;
    import android.app.ProgressDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.cardview.widget.CardView;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.crypsol.dashboard_swapper.DMC.dashboardDMC;
    import com.crypsol.dashboard_swapper.R;
    import com.crypsol.dashboard_swapper.sessionmanager;

    import java.util.ArrayList;
    import java.util.Random;

    public class dashboardCA extends RecyclerView.Adapter<dashboardCA.facilityCAViewHolder>{
    /*
     * problem*/
    private Context mContext;
    private ArrayList<dashboardDMC> facilityDCMList;
    sessionmanager session;
    private dashboardCA.OnItemClickListener mListener;
     int countforme=0;
    public interface OnItemClickListener {
        void onItemClick(int position);

    }


    public void setOnItemClickListener(dashboardCA.OnItemClickListener listener){
        mListener = listener;
    }

    public dashboardCA(Context context, ArrayList<dashboardDMC> ListfacilityDCMList){
        mContext = context;
        facilityDCMList = ListfacilityDCMList;
        session = new sessionmanager(context);
    }

    @Override
    public facilityCAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      String layout = sessionmanager.getLayoutView();


      if (layout.equals("listview_dashboard_view")){
          View v = LayoutInflater.from(mContext).inflate(R.layout.listview_dashboard_view,parent,false);
          return new facilityCAViewHolder(v);
      } else if (layout.equals("tiled_dashboard_view")){
          View v = LayoutInflater.from(mContext).inflate(R.layout.tiled_dashboard_view,parent,false);
          return new facilityCAViewHolder(v);
      }else if (layout.equals("staggeredgridiew")){
          View v = LayoutInflater.from(mContext).inflate(R.layout.staggeredgridiew,parent,false);
          return new facilityCAViewHolder(v);
      }else if(layout.equals("filmstrip")){
          View v = LayoutInflater.from(mContext).inflate(R.layout.filmstripe_listview,parent,false);
          return new facilityCAViewHolder(v);
      }else{
          View v = LayoutInflater.from(mContext).inflate(R.layout.tiled_dashboard_view,parent,false);
          return new facilityCAViewHolder(v);
      }

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final facilityCAViewHolder holder, final int position) {
        dashboardDMC currentitem = facilityDCMList.get(position);

        if (sessionmanager.getLayoutView().equals("staggeredgridiew")) {

            countforme=countforme+1;
            System.out.println("UUUUUUU pppppppppppppp"+countforme+" ggggggg "+countforme%2);
            Random rand = new Random();
            int value = rand.nextInt(getItemCount());
            if ((countforme%2)==0){
               holder.cardTile.setVisibility(View.VISIBLE);
                holder.category.setText(currentitem.getShowwheel_title()); // Tile title
                Glide.with(mContext).load(currentitem.getImagePath() + currentitem.getImage()).into(holder.imagebg);
            }else{
                holder.cardTile2.setVisibility(View.VISIBLE);
                holder.category2.setText(currentitem.getShowwheel_title()); // Tile title
                Glide.with(mContext).load(currentitem.getImagePath() + currentitem.getImage()).into(holder.imagebg2);
            }


        }else{
            holder.cardTile.setVisibility(View.VISIBLE);
            holder.category.setText(currentitem.getShowwheel_title()); // Tile title
            Glide.with(mContext).load(currentitem.getImagePath() + currentitem.getImage()).into(holder.imagebg);
        }

    }

    @Override
    public int getItemCount() {
        return facilityDCMList.size();
    }

    public class facilityCAViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagebg;
        public TextView category;
        CardView cardTile;
        public ImageView imagebg2;
        public TextView category2;
        CardView cardTile2;
        public facilityCAViewHolder(View itemView) {

            super(itemView);

            imagebg =itemView.findViewById(R.id.imagebg);
            category =itemView.findViewById(R.id.category);
            cardTile =itemView.findViewById(R.id.cardTile);

            imagebg2 =itemView.findViewById(R.id.imagebg2);
            category2 =itemView.findViewById(R.id.category2);

            cardTile2 =itemView.findViewById(R.id.cardTile2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                           mListener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }
    }

