package flowfunny.victor.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import adapter.victor.com.SlidingMenuLeftAdapter;
import view.victor.com.SlidingMenu;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton mFab;
    private Button btn_linechart;
    private Button btn_barchart, btn_horizonalbarchart;
    private Button btn_combinedchart;
    private Button btn_piechart;
    private Button btn_scatterchart;
    private Button btn_candlechart;
    private Button btn_radarchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initialize();
    }

    private void initialize (){
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        btn_linechart = (Button) findViewById(R.id.btn_linechart);
        btn_barchart = (Button) findViewById(R.id.btn_barchart);
        btn_horizonalbarchart = (Button) findViewById(R.id.btn_horizontalchart);
        btn_combinedchart = (Button) findViewById(R.id.btn_combinedchart);
        btn_piechart = (Button) findViewById(R.id.btn_piechart);
        btn_scatterchart = (Button) findViewById(R.id.btn_scatterchart);
        btn_candlechart = (Button) findViewById(R.id.btn_candlechart);
        btn_radarchart = (Button) findViewById(R.id.btn_radarchart);

        mFab.setOnClickListener(this);
        btn_linechart.setOnClickListener(this);
        btn_barchart.setOnClickListener(this);
        btn_horizonalbarchart.setOnClickListener(this);
        btn_combinedchart.setOnClickListener(this);
        btn_piechart.setOnClickListener(this);
        btn_scatterchart.setOnClickListener(this);
        btn_candlechart.setOnClickListener(this);
        btn_radarchart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                break;
            case R.id.btn_linechart:
                intent = new Intent(ChartActivity.this, LineChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_barchart:
                intent = new Intent(ChartActivity.this, BarChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_horizontalchart:
                intent = new Intent(ChartActivity.this, HorizontalChart.class);
                startActivity(intent);
                break;
            case R.id.btn_combinedchart:
                intent = new Intent(ChartActivity.this, CombinedChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_piechart:
                intent = new Intent(ChartActivity.this, PieChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_scatterchart:
                intent = new Intent(ChartActivity.this, ScatterChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_candlechart:
                intent = new Intent(ChartActivity.this, CandleChart.class);
                startActivity(intent);
                break;
            case R.id.btn_radarchart:
                intent = new Intent(ChartActivity.this, RadarChartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
