package uk.ac.tees.aad.W9506463;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    List<Car> cars;
    List<String> listCars;
    Spinner spin;
    int Selected;
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

        //Creating the ArrayAdapter instance having the country list
        TextView name = findViewById(R.id.carname);
        TextView model = findViewById(R.id.model);
        TextView brand = findViewById(R.id.brand);
        TextView phone = findViewById(R.id.brand);

        Button today = findViewById(R.id.today);
        Button tommarow = findViewById(R.id.tommarow);

        tommarow.setBackgroundColor(Color.parseColor("#F2EFEE"));








    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Selected = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
