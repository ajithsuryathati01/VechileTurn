package uk.ac.tees.aad.W9506463;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddCar extends AppCompatActivity {

    AutoCompleteTextView brand;

    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cars");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        EditText cardnumber = findViewById(R.id.carnumber);
        brand = findViewById(R.id.brand);
        EditText model = findViewById(R.id.brand);
        EditText mobile = findViewById(R.id.mobile);
        Button add = findViewById(R.id.addCarList);

        RequestQueue que = Volley.newRequestQueue(getApplicationContext());
        StringRequest getRequest = new StringRequest(Request.Method.GET, "https://car-data.p.rapidapi.com/cars/makes", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> list = new ArrayList<String>();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        list.add((String)jsonArray.get(i));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
                    brand.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("x-rapidapi-host", "car-data.p.rapidapi.com");
                params.put("x-rapidapi-key", "13ce61de2emsh86fbd2f541a31edp1de4e7jsn0280018fa044");
                return params;
            }
        };
        que.add(getRequest);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                if (cardnumber.getText().toString().equals("")){
                    cardnumber.setError("Enter Value");
                    error =true;
                }
                if( mobile.getText().toString().length()!=10 ){
                    mobile.setError("Enter Number");
                    error = true;
                }
                if( brand.getText().toString().equals("")){
                    brand.setError("Enter Number");
                    error = true;
                }
                if( model.getText().toString().equals("") ){
                    model.setError("Enter Number");
                    error = true;
                }

                if(!error)
                {
                    Car car = new Car(cardnumber.getText().toString(), mobile.getText().toString(),brand.getText().toString(),model.getText().toString());
                    myRef.child(firebaseUser.getUid()).child(car.carNumber).setValue(car).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"car Added",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Services.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed to add Car",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
