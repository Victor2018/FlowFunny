package flowfunny.victor.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import view.victor.com.RippleView;

public class RippleViewActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton mFab;
    private RippleView mRvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_view);

        initialize();

    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mRvBtn = (RippleView) findViewById(R.id.rv_btn);

        mFab.setOnClickListener(this);
        mRvBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_btn:
                Toast.makeText(getApplicationContext(), "Ripples Yo! :D", Toast.LENGTH_LONG).show();
                break;
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                break;
        }
    }
}
