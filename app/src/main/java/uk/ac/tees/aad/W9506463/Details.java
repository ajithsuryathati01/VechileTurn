package uk.ac.tees.aad.W9506463;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Details extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    List<Car> cars;
    List<String> listCars;
    Spinner spin;
    int Selected;
    Button today;
    Button tommarow;
    Button submit;


    TextView name ;
    TextView model ;
    TextView brand ;
    TextView phone ;

    int date = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        spin = (Spinner) findViewById(R.id.spinner);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference myRef = database.getReference("cars").child(user.getUid());

        cars = new ArrayList<Car>();

        listCars = new ArrayList<String>();
        submit = findViewById(R.id.submit);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot data: dataSnapshot.getChildren()) {
                       cars.add(data.getValue(Car.class));
                        listCars.add(data.getValue(Car.class).carNumber);
                    }
                String[] k =new String [listCars.size()];
                for (int i=0;i<listCars.size();i++)
                {
                    k[i]=listCars.get(i);
                }
                ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,k);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spin.setAdapter(aa);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("dasdf", "Failed to read value.", error.toException());
            }
        });




        spin.setOnItemSelectedListener(this);


       name = findViewById(R.id.carname);
        model = findViewById(R.id.model);
         brand = findViewById(R.id.brand);
      phone = findViewById(R.id.mobile);

        today = findViewById(R.id.today);
        tommarow = findViewById(R.id.tommarow);

           today.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   date = 1;
                   change();
               }
           });
           tommarow.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   date =2;
                   change();
               }
           });
           submit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Date dt = new Date();
                   Calendar c = Calendar.getInstance();
                   String ldt="";
                   if(date == 1){
                       c.setTime(dt);
                       ldt = c.toString();
                   }else{
                       c.setTime(dt);
                       c.add(Calendar.DATE, 1);
                      ldt = c.toString();
                   }
                   Intent intent = new Intent(getApplicationContext(),Success.class);
                   intent.putExtra("car",cars.get(Selected).getCarNumber());
                   intent.putExtra("date",ldt);
                   intent.putExtra("service",getIntent().getStringExtra("option"));
                   startActivity(intent);
               }
           });




       change();
    }
    public void change()
    {
        if(date == 1)
        {
            today.setBackgroundColor(Color.parseColor("#3CEA40"));
            tommarow.setBackgroundColor(Color.parseColor("#F2EFEE"));
        }else
        {
            today.setBackgroundColor(Color.parseColor("#F2EFEE"));
            tommarow.setBackgroundColor(Color.parseColor("#3CEA40"));
        }

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Selected = position;
        fillDetails();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public  void fillDetails()
    {
        Car c = cars.get(Selected);
        name.setText(c.getCarNumber());
        model.setText(getIntent().getStringExtra("option"));
        phone.setText(c.getMobile());
        brand.setText(c.getCarBrand());

    }
}
