package com.echo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.echo.ioc.view.annotation.ContentView;
import com.echo.ioc.view.annotation.OnClick;
import com.echo.ioc.view.annotation.ViewInject;
import com.example.myandroidframework.R;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends BaseActivity
{
	@ViewInject(R.id.id_btn)
	private Button mBtn1;
	@ViewInject(R.id.id_btn02)
	private Button mBtn2;

	@OnClick({ R.id.id_btn, R.id.id_btn02 })
	public void clickBtnInvoked(View view)
	{
		switch (view.getId())
		{
		case R.id.id_btn:
			Toast.makeText(this, "Inject Btn01 !", Toast.LENGTH_SHORT).show();
			break;
		case R.id.id_btn02:
			Toast.makeText(this, "Inject Btn02 !", Toast.LENGTH_SHORT).show();
			break;
		}
	}

}
