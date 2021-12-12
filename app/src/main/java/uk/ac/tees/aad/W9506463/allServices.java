package uk.ac.tees.aad.W9506463;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class allServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);

        LinearLayout gs  = findViewById(R.id.gs);
        LinearLayout vec  = findViewById(R.id.vechileWash);
        LinearLayout spare = findViewById(R.id.spare);

        gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(),Details.class);
                intent.putExtra("option","general service");
                startActivity(intent);
            }
        });
        vec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(),Details.class);
                intent.putExtra("option","cleaning");
                startActivity(intent);
            }
        });
        spare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(),Details.class);
                intent.putExtra("option","spare part fitting");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(getApplicationContext(),Services.class));
    }
}
