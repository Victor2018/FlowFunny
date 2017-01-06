package flowfunny.victor.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import datastructrue.victor.com.BubbleSort;
import datastructrue.victor.com.SelectionSort;
import view.victor.com.RippleView;

public class AlgorithmActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton mFab;
    private TextView mTvAlogrithm;
    private RippleView mRbBubble,mRbSelect,mRbDoubleLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);

        initialize();
    }
    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTvAlogrithm = (TextView) findViewById(R.id.tv_algorithm);
        mRbBubble = (RippleView) findViewById(R.id.rv_bubble);
        mRbSelect = (RippleView) findViewById(R.id.rv_select);
        mRbDoubleLink = (RippleView) findViewById(R.id.rv_double_link);

        mFab.setOnClickListener(this);
        mRbBubble.setOnClickListener(this);
        mRbSelect.setOnClickListener(this);
        mRbDoubleLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        StringBuffer sb = new StringBuffer();
        int[] datas;
        switch (view.getId()) {
            case R.id.rv_btn:
                Toast.makeText(getApplicationContext(), "Ripples Yo! :D", Toast.LENGTH_LONG).show();
                break;
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                break;
            case R.id.rv_bubble:
                datas = BubbleSort.getSortDatas();
                sb.setLength(0);
                sb.append("冒泡排序前：");
                for (int i=0;i<datas.length;i++) {
                    sb.append(datas[i] + ",");
                }
                sb.append("\n");
                datas = BubbleSort.sort(datas);
                sb.append("冒泡排序后：");
                for (int i=0;i<datas.length;i++) {
                    sb.append(datas[i] + ",");
                }
                sb.append("\n");
                mTvAlogrithm.setText(sb.toString());
                break;
            case R.id.rv_select:
                datas = SelectionSort.getSortDatas();
                sb.setLength(0);
                sb.append("选择排序前：");
                for (int i=0;i<datas.length;i++) {
                    sb.append(datas[i] + ",");
                }
                sb.append("\n");
                datas = SelectionSort.sort(datas);
                sb.append("选择排序后：");
                for (int i=0;i<datas.length;i++) {
                    sb.append(datas[i] + ",");
                }
                sb.append("\n");
                mTvAlogrithm.setText(sb.toString());
                break;
            case R.id.rv_double_link:
                break;
        }
    }
}
