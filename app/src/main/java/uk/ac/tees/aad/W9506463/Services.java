package uk.ac.tees.aad.W9506463;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        View v = findViewById(R.id.constraintLayout4);

        TextView email = findViewById(R.id.email);

        TextView logout = findViewById(R.id.logout);

        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });




        View top = findViewById(R.id.top);
        top.bringToFront();
        top.setVisibility(View.INVISIBLE);

        ImageView ima = findViewById(R.id.nav);
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top.setVisibility(View.VISIBLE);
            }
        });

        ImageView ima2 = findViewById(R.id.nav2);
        ima2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top.setVisibility(View.INVISIBLE);
            }
        });

        TextView f = findViewById(R.id.textView3);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),allServices.class));
            }
        });

        View v1 = findViewById(R.id.constraintLayout5);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmergencyServices.class));
            }
        });

        Button add = findViewById(R.id.addCar);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddCar.class));
            }
        });
    }


}
