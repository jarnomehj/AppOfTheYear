package jarno.oussama.com.examenmonitor.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import jarno.oussama.com.examenmonitor.R;

import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth auth;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(android.R.id.content);
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {

        } else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(), RC_SIGN_IN);
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
       }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                //user signed in
                Snackbar.make(view, "user signed in" , Snackbar.LENGTH_LONG);
            } else {
                // user not authenticated
                Log.i("auth", "not authenticated");
            }
        }
    }


    public void AddUserActivity(View view) {
        Intent IntentAdduser = new Intent(this, AddUserActivity.class);
        startActivity(IntentAdduser);
    }

    public void StudentsListActivity(View view) {
        Intent StudentList = new Intent(this, StudentsListActivity.class);
        startActivity(StudentList);
    }

    public void NewExamActivity(View view) {
        Intent newExam = new Intent(this, NewExamActivity.class);
        startActivity(newExam);
    }

    public void MyExamsActivity(View view) {
        Intent MyExams = new Intent(this, MyExamsActivity.class);
        startActivity(MyExams);
    }
}
