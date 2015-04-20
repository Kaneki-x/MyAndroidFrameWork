package com.echo.ioc.view;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.app.Activity;
import android.view.View;

import com.echo.ioc.view.annotation.ContentView;
import com.echo.ioc.view.annotation.EventBase;
import com.echo.ioc.view.annotation.ViewInject;

public class ViewInjectUtils {
	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

	public static void inject(Activity activity) {
		injectContentView(activity);
		injectViews(activity);
		injectEvents(activity);
	}

	/**
	 * ע�����е��¼�
	 * 
	 * @param activity
	 */
	private static void injectEvents(Activity activity) {
		
		Class<? extends Activity> clazz = activity.getClass();
		Method[] methods = clazz.getMethods();
		//�������еķ���
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			//�õ������ϵ����е�ע��
			for (Annotation annotation : annotations) {
				Class<? extends Annotation> annotationType = annotation
						.annotationType();
				//�õ�ע���ϵ�ע��
				EventBase eventBaseAnnotation = annotationType
						.getAnnotation(EventBase.class);
				//�������ΪEventBase
				if (eventBaseAnnotation != null) {
					//ȡ�����ü����������ƣ������������ͣ����õķ�����
					String listenerSetter = eventBaseAnnotation
							.listenerSetter();
					Class<?> listenerType = eventBaseAnnotation.listenerType();
					String methodName = eventBaseAnnotation.methodName();

					try
					{
						//�õ�Onclickע���е�value����
						Method aMethod = annotationType
								.getDeclaredMethod("value");
						//ȡ�����е�viewId
						int[] viewIds = (int[]) aMethod
								.invoke(annotation, null);
						//ͨ��InvocationHandler���ô���
						DynamicHandler handler = new DynamicHandler(activity);
						//��map��ӷ���
						handler.addMethod(methodName, method);
						Object listener = Proxy.newProxyInstance(
								listenerType.getClassLoader(),
								new Class<?>[] { listenerType }, handler);
						//�������е�View�������¼�
						for (int viewId : viewIds) {
							View view = activity.findViewById(viewId);
							Method setEventListenerMethod = view.getClass()
									.getMethod(listenerSetter, listenerType);
							setEventListenerMethod.invoke(view, listener);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

	/**
	 * ע�����еĿؼ�
	 * 
	 * @param activity
	 */
	private static void injectViews(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		Field[] fields = clazz.getFields();
		// �������г�Ա����
		for (Field field : fields) {
			ViewInject viewInjectAnnotation = field
					.getAnnotation(ViewInject.class);
			if (viewInjectAnnotation != null) {
				int viewId = viewInjectAnnotation.value();
				if (viewId != -1) {
					// ��ʼ��View
					try {
						Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
								Integer.class);
						Object resView = method.invoke(clazz, viewId);
						field.setAccessible(true);
						field.set(clazz, resView);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ע���������ļ�
	 * 
	 * @param activity
	 */
	private static void injectContentView(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		// ��ѯ�����Ƿ����ContentViewע��
		ContentView contentView = clazz.getAnnotation(ContentView.class);
		// ����
		if (contentView != null) {
			int contentViewLayoutId = contentView.value();
			try{
				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW, int.class);
				method.setAccessible(true);
				method.invoke(activity, contentViewLayoutId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
