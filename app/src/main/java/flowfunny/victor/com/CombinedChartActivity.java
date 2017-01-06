package flowfunny.victor.com;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;

import java.util.ArrayList;

public class CombinedChartActivity extends Activity {

    private CombinedChart chart;
    private CombinedData data;
    private ArrayList<String> xVals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chart=new CombinedChart(this);
        setContentView(chart);
        xVals=new ArrayList<>();
        for (int i=0;i<12;i++){
            xVals.add(i,"Hello");
        }

        data=new CombinedData(xVals);
        chart.setData(data);

    }
}
