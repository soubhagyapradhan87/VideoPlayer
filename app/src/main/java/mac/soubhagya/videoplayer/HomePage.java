package mac.soubhagya.videoplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    ProgressDialog progressDialog;
    List vdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        progressDialog=new ProgressDialog(HomePage.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();;

        VdoInterface service=RetrofitInstance.getRetrofitInstance().create(VdoInterface.class);
        Call<List<Videos>> call=service.getVdoData();
        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {
                progressDialog.dismiss();
                 vdo=response.body();
                // System.out.println(new Gson().toJson(vdo));
                //System.out.println(vdo.toString());
                generateDataList(vdo);

            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {

            }
        });

    }

    private void generateDataList(final List<Videos> vdoList) {

        recyclerView=findViewById(R.id.recyclerView);
        adapter=new MyAdapter(this,vdoList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(HomePage.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Animal animal = animalList.get(position);
                String settitle=vdoList.get(position).getTitle();
                String setdesc=vdoList.get(position).getDescription();
                String setimurl=vdoList.get(position).getThumb();
                String setvdourl=vdoList.get(position).getUrl();
                Toast.makeText(getApplicationContext(),   vdoList.get(position).getTitle()+"listview is selected!", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(HomePage.this,DetailsPage.class);
                intent.putExtra("title",settitle);
                intent.putExtra("description",setdesc);
                intent.putExtra("thumburl",setimurl);
                intent.putExtra("vdourl",setvdourl);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }
//    public  void row(View view){
//        System.out.println("rinkuuuuu");
//        Intent intent=new Intent(HomePage.this,DetailsPage.class);
//
//        startActivity(intent);
//
//    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private HomePage.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final HomePage.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }



}
