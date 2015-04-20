package com.echo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.echo.ioc.view.ViewInjectUtils;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
	}
}
