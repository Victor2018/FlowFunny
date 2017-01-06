package flowfunny.victor.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import mode.victor.com.BridgeMode;
import mode.victor.com.BuilderMode;
import mode.victor.com.ChainOfResponsibilityMode;
import mode.victor.com.CmdMode;
import mode.victor.com.FlyWeightMode;
import mode.victor.com.IntermediaryMode;
import mode.victor.com.PoxyMode;
import mode.victor.com.StateMode;
import mode.victor.com.TemplateMethodMode;
import mode.victor.com.VisitorMode;
import view.victor.com.RippleView;

public class DesignModeActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton mFab;
    private RippleView mRvBridge,mRvBuilder,mRvChainOfResponsibility,mRvCmd,mRvFly,
            mRvInterMediary,mRvPorxy,mRvState,mRvTemplateMethod,mRvVisitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_mode);
        initialize();
    }

    private void initialize () {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mRvBridge = (RippleView) findViewById(R.id.rv_bridge);
        mRvBuilder = (RippleView) findViewById(R.id.rv_builder);
        mRvChainOfResponsibility = (RippleView) findViewById(R.id.rv_chain_of_responsibility);
        mRvCmd = (RippleView) findViewById(R.id.rv_cmd);
        mRvFly = (RippleView) findViewById(R.id.rv_fly);
        mRvInterMediary = (RippleView) findViewById(R.id.rv_intermediary);
        mRvPorxy = (RippleView) findViewById(R.id.rv_proxy);
        mRvState = (RippleView) findViewById(R.id.rv_state);
        mRvTemplateMethod = (RippleView) findViewById(R.id.rv_template_method);
        mRvVisitor = (RippleView) findViewById(R.id.rv_visitor);

        mFab.setOnClickListener(this);
        mRvBridge.setOnClickListener(this);
        mRvBuilder.setOnClickListener(this);
        mRvChainOfResponsibility.setOnClickListener(this);
        mRvCmd.setOnClickListener(this);
        mRvFly.setOnClickListener(this);
        mRvInterMediary.setOnClickListener(this);
        mRvPorxy.setOnClickListener(this);
        mRvState.setOnClickListener(this);
        mRvTemplateMethod.setOnClickListener(this);
        mRvVisitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            finish();
            break;
            case R.id.rv_bridge:
                BridgeMode.test();
                Toast.makeText(getApplicationContext(), "桥梁模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_builder:
                BuilderMode.test();
                Toast.makeText(getApplicationContext(), "建造者模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_chain_of_responsibility:
                ChainOfResponsibilityMode.test();
                Toast.makeText(getApplicationContext(), "责任链模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_cmd:
                CmdMode.test();
                Toast.makeText(getApplicationContext(), "命令模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_fly:
                FlyWeightMode.test();
                Toast.makeText(getApplicationContext(), "享元模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_intermediary:
                IntermediaryMode.test();
                Toast.makeText(getApplicationContext(), "中介者模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_proxy:
                PoxyMode.test();
                Toast.makeText(getApplicationContext(), "代理模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_state:
                StateMode.test();
                Toast.makeText(getApplicationContext(), "状态模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_template_method:
                TemplateMethodMode.test();
                Toast.makeText(getApplicationContext(), "模板方法模式", Toast.LENGTH_LONG).show();
                break;
            case R.id.rv_visitor:
                VisitorMode.test();
                Toast.makeText(getApplicationContext(), "访问者模式", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
