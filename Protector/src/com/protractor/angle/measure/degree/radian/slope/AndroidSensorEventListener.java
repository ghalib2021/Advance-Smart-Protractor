package com.protractor.angle.measure.degree.radian.slope;

import java.text.NumberFormat;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mfzyzzyfnimpxdmujcwv.AdController;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class AndroidSensorEventListener extends Activity {
	float currentDegree=0f;
	public float val;
	private float radian;
	public static int temp1=0;
	 ImageView imageneedle,protractor;
	Button settings,calibrate;
	String output1,output,rad,per;
	int s2,s3;
	int percent;
	NumberFormat formatter,formatter1;
	 float bearing=0;
    int checkedIndex;
    RadioGroup radioGroup;
	
	final String KEY_SAVED_RADIO_BUTTON_INDEX2 = "SAVED_RADIO_BUTTON_INDEX2";
	final String KEY_SAVED_RADIO_BUTTON_INDEX3 = "SAVED_RADIO_BUTTON_INDEX3";
	RelativeLayout lowerscreen,roundscreen;
	SharedPreferences prefs2,prefs3;
	float var1,vall,temp=0f;
	 RotateAnimation ra;
	TextView textviewAzimuth, textviewPitch, textviewRoll,angle1,tvextra,tvrad;
	char deg = 0x00B0;
	int var;
	StartAppAd startappad = new StartAppAd(this);
	float tempo;
	
	List<Sensor> mySensors;
	
	
	float templeBearing=0f;
	private static SensorManager mySensorManager;
	private boolean sersorrunning;
	public static double temp2=0.0;
	//View line;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StartAppSDK.init(this, "102371226", "205653372", true);
        setContentView(R.layout.main);
       
        StartAppAd.init(this, "102371226", "205653372");
        //
        	StartAppAd.showSlider(this);
        	startappad.showAd();
        	startappad.loadAd();
        ////	
        	AdController adController;
        	adController=new AdController(this,"661610579");
        	adController.loadAd();
        	
        	AdView adView = (AdView) this.findViewById(R.id.adView);
	  		  adView.loadAd(new AdRequest.Builder().build());
        	
        	 ////////////////////AdMob add
//        	  AdView adview = (AdView) findViewById(R.id.adView);
//        	  AdRequest adRequest = new AdRequest();
//        	  adview.loadAd(adRequest);
        
    
        
        
        roundscreen=(RelativeLayout)findViewById(R.id.roundscreen);
        lowerscreen=(RelativeLayout)findViewById(R.id.lowerscreen);
        protractor=(ImageView)findViewById(R.id.protractor);
        
        
        prefs2 = getSharedPreferences("MY_SHARED_PREF2", Context.MODE_PRIVATE);
        prefs3 = getSharedPreferences("MY_SHARED_PREF3", Context.MODE_PRIVATE);
  
     
      s2=  prefs2.getInt(KEY_SAVED_RADIO_BUTTON_INDEX2, 0);
      s3=  prefs3.getInt(KEY_SAVED_RADIO_BUTTON_INDEX3, 0);
      
        
        imageneedle = (ImageView)findViewById(R.id.imageView1);
     
        textviewAzimuth = (TextView)findViewById(R.id.textazimuth);
        textviewPitch = (TextView)findViewById(R.id.textpitch);
        textviewRoll = (TextView)findViewById(R.id.textroll);

        angle1 = (TextView)findViewById(R.id.angle1);
        tvrad = (TextView)findViewById(R.id.tvrad);
        settings=(Button)findViewById(R.id.settingbt);
        calibrate=(Button)findViewById(R.id.calibrate);
        
        
        
        settings.setOnTouchListener(new OnTouchListener() {
     	   public boolean onTouch(View v, MotionEvent event) {

     	    switch (event.getAction()) {
     	    case MotionEvent.ACTION_DOWN:
     	    	settings.setBackgroundResource(R.drawable.presettings);
     	     break;
     	    case MotionEvent.ACTION_UP:
     	    	settings.setBackgroundResource(R.drawable.settings);
     	    	Intent i = new Intent(AndroidSensorEventListener.this, Settings.class);
    			startActivity(i);
 			
     	   break;
     	     }
     	    return true; 
     	    }
     	  });
        
        
      
        Typeface localTypeface2 = Typeface.createFromAsset(getAssets(),
				"digital.ttf");
        angle1 .setTypeface(localTypeface2);
        angle1.setTextSize(40);
        
        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        
        @SuppressWarnings("deprecation")
		List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
		
       
        
        
        if(mySensors.size() > 0){
        	mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        	sersorrunning = true;
        	
        }
        else{
        	Toast.makeText(this, "No ORIENTATION Sensor", Toast.LENGTH_LONG).show();
        	sersorrunning = false;
        	finish();
        }
 
     // Toggle Button handler.

        final ToggleButton onOffButton=(ToggleButton)findViewById(
            R.id.on_off_toggle_button);
        onOffButton.setText(null);
    	onOffButton.setTextOn(null);
    	onOffButton.setTextOff(null);
        ToggleButton.OnClickListener tbListener =
            new ToggleButton.OnClickListener() {
        	
        	
        	
          @Override
          public void onClick(View v) {
        	  
            if (onOffButton.isChecked()) {
            	onOffButton.setBackgroundResource(R.drawable.play);
            	if(sersorrunning){
        			mySensorManager.unregisterListener(mySensorEventListener);
        			
        		}
            	
              
            } else {
            	onOffButton.setBackgroundResource(R.drawable.pause);
            	@SuppressWarnings("deprecation")
				List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
            	mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
            	
            }
          }
        };
        onOffButton.setOnClickListener(tbListener);
        
        
    }
   
    private SensorEventListener mySensorEventListener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(final SensorEvent event) {
			
			
			prefs2 = getSharedPreferences("MY_SHARED_PREF2", Context.MODE_PRIVATE);
			prefs3 = getSharedPreferences("MY_SHARED_PREF3", Context.MODE_PRIVATE);

		      
		      s2=  prefs2.getInt(KEY_SAVED_RADIO_BUTTON_INDEX2, 0);
		      s3=  prefs3.getInt(KEY_SAVED_RADIO_BUTTON_INDEX3, 0);
			
			if(s2==0)
			{
				angle1.setTextColor(Color.BLACK);
				textviewAzimuth.setTextColor(Color.BLACK);
				 textviewPitch.setTextColor(Color.BLACK);
				 textviewRoll.setTextColor(Color.BLACK);
				 tvrad.setTextColor(Color.BLACK);
				roundscreen.setBackgroundResource(R.drawable.round_screen1);
				lowerscreen.setBackgroundResource(R.drawable.lower_screen);
				protractor.setBackgroundResource(R.drawable.pro1);
			}
			else if(s2==1)
			{
				
				angle1.setTextColor(Color.WHITE);
				 textviewAzimuth.setTextColor(Color.WHITE);
				 textviewPitch.setTextColor(Color.WHITE);
				 textviewRoll.setTextColor(Color.WHITE);
				 tvrad.setTextColor(Color.WHITE);
				roundscreen.setBackgroundResource(R.drawable.round_screen_black);
				lowerscreen.setBackgroundResource(R.drawable.lower_screen_black);
				protractor.setBackgroundResource(R.drawable.glass);
				
				
			}
			
			formatter = NumberFormat.getNumberInstance();
			formatter.setMinimumFractionDigits(2);
			formatter.setMaximumFractionDigits(2);
			output = formatter.format(event.values[2]);
        
            textviewPitch.setText("Pitch= "+output);
           
            
            
            tempo=(float) ((event.values[1]*3.625/Math.PI)-templeBearing);
            val=(float) (tempo);
         // Toast.makeText(getBaseContext(), Float.toString(event.values[2]),Toast.LENGTH_SHORT).show();
            if(val>90||val<-90)
            {
       
            	event.values[1]=-event.values[1];
         	
            }
            else
            {
            	
            update(val);
            
            }
            
            calibrate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					bearing=0;
					 bearing = val;
									
			            update(bearing);
			            templeBearing=bearing+templeBearing; 
			       	}
			});
            
           
         		if(val>=0)
         		{  
         			if(val>90)
         			{
         				val=0;
         			}
         			else
         			{
         			
         			var=(int) (91-val);
           	  var1=(0+val);
           	formatter1 = NumberFormat.getNumberInstance();
			formatter1.setMinimumFractionDigits(1);
			formatter1.setMaximumFractionDigits(1);
           	output1 = formatter1.format(var1);
          	 radian = (float) (var1 * (Math.PI/180));
          	temp2 = Math.toRadians(var1);
          	temp1=(int) (Math.tan(temp2)*100);
          			
          	 if(s3==0)
         			{
          		 tvrad.setVisibility(View.GONE);
          		textviewAzimuth.setText(output1+deg);
           	 angle1.setText(output1+deg);
           	textviewRoll.setText(Float.toString(var)+deg); 
          		 
          		
         			}
          	 else if(s3==1)
          	 {
          		tvrad.setVisibility(View.GONE);
          		textviewAzimuth.setText((Double.toString(temp1))+"%");
           	 angle1.setText((Double.toString(temp1))+"%");
           	textviewRoll.setText(Float.toString(var)+deg);  
          	 }
          	 
          	 else if(s3==2)
          	 {
          		rad = formatter.format(radian);
          		tvrad.setText(" rad");
          		tvrad.setVisibility(View.VISIBLE);
            	 textviewAzimuth.setText(rad+" rad");
            	 angle1.setText(rad);
            	textviewRoll.setText(Float.toString(var)+deg);
          	 }
          	 	}
         		}
         		else
         		{
         			if(val<-90)
         			{
         				val=0;
         			}
         			else
         			{
         			var=(int) (91+val);
         			var1=(0-val);
         			formatter1 = NumberFormat.getNumberInstance();
        			formatter1.setMinimumFractionDigits(1);
        			formatter1.setMaximumFractionDigits(1);
         			output1 = formatter1.format(var1);
         			radian = (float) (var1 * (Math.PI/180));
         
         			temp2 = Math.toRadians(var1);
         			temp1=(int) (Math.tan(temp2)*100);
         			
         			if(s3==0)
          			{
         				tvrad.setVisibility(View.GONE);
                   		textviewAzimuth.setText(output1+deg);
                     	 angle1.setText(output1+deg);
                     	textviewRoll.setText(Float.toString(var)+deg); 
          			}
         			
         			else if(s3==1)
         			{
         				tvrad.setVisibility(View.GONE);
         				textviewAzimuth.setText((Double.toString(temp1))+"%");
                    	 angle1.setText((Double.toString(temp1))+"%");
                    	textviewRoll.setText(Float.toString(var)+deg); 
         			}
         			
         			
         			else if(s3==2)
           	 {
         				tvrad.setVisibility(View.VISIBLE);
         				rad = formatter.format(radian);
        	         	 textviewAzimuth.setText(rad+" rad");
        	         	tvrad.setText(" rad");
        	         	 angle1.setText(rad);
        	         	textviewRoll.setText(Float.toString(var)+deg);;
         		
           	 }
         		  
         		}
         		}
		}
		 	  
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};
	public void update(float bear)
	{
		 ra = new RotateAnimation(currentDegree,bear,
  				Animation.RELATIVE_TO_SELF, 0.0f, 
  				Animation.RELATIVE_TO_SELF,
  				0.0f);

  		// how long the animation will take place
  		ra.setDuration(210);
  		
  		// set the animation after the end of the reservation status
  		ra.setFillAfter(true);

  		// Start the animation
  		
  		imageneedle.startAnimation(ra);
  		currentDegree = bear;
  		
  	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	
		startappad.onBackPressed(); 
		super.onBackPressed();
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(sersorrunning){
			mySensorManager.unregisterListener(mySensorEventListener);
			
		}
	}
}