package mac.soubhagya.videoplayer;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsPage extends AppCompatActivity {
    ImageView image_id;
    TextView title_id,description_id,related_id;
    ImageView button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        image_id=findViewById(R.id.image_id);
        title_id=findViewById(R.id.title_id);
        description_id=findViewById(R.id.description_id);
        button1=findViewById(R.id.button1);
        related_id=findViewById(R.id.related_id);



        String gettitle=getIntent().getStringExtra("title");
        String getdesc=getIntent().getStringExtra("description");
        final String getimurl=getIntent().getStringExtra("thumburl");
        final String dgetvdourl=getIntent().getStringExtra("vdourl");

        title_id.setText(gettitle);
        description_id.setText(getdesc);

        Picasso.Builder builder=new Picasso.Builder(getApplicationContext());
        builder.downloader(new OkHttp3Downloader(getApplicationContext()));
        builder.build().load(getimurl)
                .resize(150,60)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(image_id);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailsPage.this,VideoScreen.class);
                intent.putExtra("vdourl1",dgetvdourl);
                startActivity(intent);
            }
        });


    }
}
