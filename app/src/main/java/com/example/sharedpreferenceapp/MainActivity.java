package com.example.sharedpreferenceapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText agentName, realEstatePriAmtPerUnit, numberRealEstSold, agentComm;
    private TextView agentNameResult, commissionNetPay;
    private Button submitButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agentName = findViewById(R.id.agent_name);
        realEstatePriAmtPerUnit = findViewById(R.id.real_estate_per_unit);
        numberRealEstSold = findViewById(R.id.num_of_real_est_sold);
        agentComm = findViewById(R.id.agents_comm);
        submitButton = findViewById(R.id.submit_button);
        clearButton = findViewById(R.id.clear_button);
        agentNameResult = findViewById(R.id.agent_name_result);
        commissionNetPay = findViewById(R.id.comission_net_pay);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData("agent_name", agentName.getText().toString());
                saveData("real_estate", realEstatePriAmtPerUnit.getText().toString());
                saveData("real_estate_sold", numberRealEstSold.getText().toString());
                saveData("agent_comm", agentComm.getText().toString());
                String agent_name = loadData("agent_name");
                String real_estate = loadData("real_estate");
                String real_estate_sold = loadData("real_estate_sold");
                String agent_comm = loadData("agent_comm");

                agentNameResult.setText(agent_name);
                double priAmt = Double.valueOf(real_estate);
                int numSold = Integer.valueOf(real_estate_sold);
                double VAT = (priAmt * numSold) * 0.10; // constant 10%
                double aComm = Double.valueOf(agent_comm);
                double comNetPay = ((priAmt * numSold) * (aComm * 0.01)) - VAT;
                commissionNetPay.setText(Double.toString(comNetPay));
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agentName.setText("");
                realEstatePriAmtPerUnit.setText("");
                numberRealEstSold.setText("");
                agentComm.setText("");
                agentNameResult.setText("");
                commissionNetPay.setText("");
            }
        });
    }

    private void saveData(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String loadData(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}