package mac.soubhagya.videoplayer;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<Videos> datalist;
    private Context context;

    public MyAdapter(Context context,List<Videos> datalist){
        this.context=context;
        this.datalist=datalist;


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_row,viewGroup,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.title.setText(datalist.get(i).getTitle());
        viewHolder.description.setText(datalist.get(i).getDescription());

       // Picasso.with(context).load(android_versions.get(i).getAndroid_image_url()).resize(120, 60).into(viewHolder.img_android);


        Picasso.Builder builder=new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(datalist.get(i).getThumb())
                .resize(370,160)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.image);



    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public  final View mView;

        public TextView title;
        public TextView description;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            title=mView.findViewById(R.id.title_id);
            description=mView.findViewById(R.id.description_id);
            image=mView.findViewById(R.id.image_id);
        }
    }
}
