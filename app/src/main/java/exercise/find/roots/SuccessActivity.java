package exercise.find.roots;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SuccessActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_layout);
        Bundle b = getIntent().getExtras();
        long root1 =  b.getLong("root1");
        long root2 = b.getLong("root2",1);
        long orig =  b.getLong("original_number");
        long timeSpent =  b.getLong("time_spent");
        TextView firstView = findViewById(R.id.textViewForRoot1);
        TextView secondView = findViewById(R.id.textViewForRoot2);
        TextView viewSpent = findViewById(R.id.textViewTimeSpent);
        TextView originalNumberView = findViewById(R.id.textViewOriginalNumber);


        firstView.setText(Long.toString(root1));
        secondView.setText(Long.toString(root2));
        viewSpent.setText(Long.toString(timeSpent));
        originalNumberView.setText(Long.toString(orig));

    }
}
