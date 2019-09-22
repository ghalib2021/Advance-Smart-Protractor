package com.protractor.angle.measure.degree.radian.slope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.mfzyzzyfnimpxdmujcwv.AdController;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class Start extends Activity {
 
	Button b;
	StartAppAd startappad = new StartAppAd(this);
	 private PublisherInterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StartAppSDK.init(this, "102371226", "205653372", true);
        
        setContentView(R.layout.start);
        // Create the layout
       
        StartAppAd.init(this, "102371226", "205653372");
        //
        	StartAppAd.showSlider(this);
        	startappad.showAd();
        	startappad.loadAd();
        ////	
        	AdController adController;
        	adController=new AdController(this,"661610579");
        	adController.loadAd();
        
        	InterstitialAdmob();
        	
        	AdView adView = (AdView) this.findViewById(R.id.adView);
	  		  adView.loadAd(new AdRequest.Builder().build());
        	
        	
        ////////////////////AdMob add
//  	  AdView adview = (AdView) findViewById(R.id.ad);
//  	  AdRequest adRequest = new AdRequest();
//  	  adview.loadAd(adRequest);
        b = (Button)findViewById(R.id.button1);
        
        b.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				b.setBackgroundResource(R.drawable.preseleted);
				 switch(event.getAction()){
				    case MotionEvent.ACTION_DOWN:
				   
				    	
				    	b.setBackgroundResource(R.drawable.select);
				    	
				         break;
				   
				    case MotionEvent.ACTION_UP:
				    	b.setBackgroundResource(R.drawable.preseleted);
				    
				    	Intent i = new Intent(Start.this, AndroidSensorEventListener.class);
				    	startActivity(i);
				    	finish();
				         break;
				    }
				
				return true;
			}
		});
    }
		public void InterstitialAdmob() {
		interstitialAd = new PublisherInterstitialAd(this);
		interstitialAd.setAdUnitId("ca-app-pub-8000764337105822/1662172998");
		interstitialAd.loadAd(new PublisherAdRequest.Builder().build());

		interstitialAd.setAdListener(new AdListener() {
			public void onAdLoaded() {
				if (interstitialAd.isLoaded()) {
					interstitialAd.show();
				}
			}
		});
	}
    
   
}