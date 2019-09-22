package com.protractor.angle.measure.degree.radian.slope;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.mfzyzzyfnimpxdmujcwv.AdController;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;


public class Settings extends Activity {
	
	 RadioGroup radioGroup2,radioGroup3;
	 StartAppAd startappad = new StartAppAd(this);
	 private PublisherInterstitialAd interstitialAd;
	 
	 final String KEY_SAVED_RADIO_BUTTON_INDEX1 = "SAVED_RADIO_BUTTON_INDEX1";
	 final String KEY_SAVED_RADIO_BUTTON_INDEX2 = "SAVED_RADIO_BUTTON_INDEX2";
	 final String KEY_SAVED_RADIO_BUTTON_INDEX3 = "SAVED_RADIO_BUTTON_INDEX3";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		StartAppSDK.init(this, "102371226", "205653372", true);
		
		setContentView(R.layout.settings);
		
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
	        	
//	        	 ////////////////////AdMob add
//	        	  AdView adview = (AdView) findViewById(R.id.adView);
//	        	  AdRequest adRequest = new AdRequest();
//	        	  adview.loadAd(adRequest);
		
		
		
		
        
        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener2);
        
        radioGroup3 = (RadioGroup)findViewById(R.id.radioGroup3);
        radioGroup3.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener3);
        
  
        LoadPreferences2();
        LoadPreferences3();
		
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


			OnCheckedChangeListener radioGroupOnCheckedChangeListener2 =
				      new OnCheckedChangeListener(){

				    @Override
				    public void onCheckedChanged(RadioGroup group2, int checkedId2) {
				    		
				  
				     
				     RadioButton checkedRadioButton2 = (RadioButton)radioGroup2.findViewById(checkedId2);
				     int checkedIndex2 = radioGroup2.indexOfChild(checkedRadioButton2);
				    
				    
				     
		
				     SavePreferences2(KEY_SAVED_RADIO_BUTTON_INDEX2, checkedIndex2);
				  
			
				    
				    }};
				    
				    
				    
				    OnCheckedChangeListener radioGroupOnCheckedChangeListener3 =
						      new OnCheckedChangeListener(){

						    @Override
						    public void onCheckedChanged(RadioGroup group3, int checkedId3) {
						    		
						  
						     
						     RadioButton checkedRadioButton3 = (RadioButton)radioGroup3.findViewById(checkedId3);
						     int checkedIndex3 = radioGroup3.indexOfChild(checkedRadioButton3);
						    
						   
						     
				
						     SavePreferences3(KEY_SAVED_RADIO_BUTTON_INDEX3, checkedIndex3);
						  
					
						    
						    }};
				    
		    
		   
		    private void SavePreferences2(String key2, int value2){
		    	  SharedPreferences sharedPreferences2 = getSharedPreferences("MY_SHARED_PREF2", MODE_PRIVATE);
		    	  SharedPreferences.Editor editor2 = sharedPreferences2.edit();
		    	  editor2.putInt(key2, value2);
		    	  editor2.commit(); 
		    	 }
		    private void SavePreferences3(String key3, int value3){
		    	  SharedPreferences sharedPreferences3 = getSharedPreferences("MY_SHARED_PREF3", MODE_PRIVATE);
		    	  SharedPreferences.Editor editor3 = sharedPreferences3.edit();
		    	  editor3.putInt(key3, value3);
		    	  editor3.commit(); 
		    	 }
		    	 
		    	
		    	 private void LoadPreferences2(){
			    	  SharedPreferences sharedPreferences2 = getSharedPreferences("MY_SHARED_PREF2", MODE_PRIVATE);
			    	  
			    	  int savedRadioIndex2 = sharedPreferences2.getInt(KEY_SAVED_RADIO_BUTTON_INDEX2, 0);
			  
			    	  
			    	  RadioButton savedCheckedRadioButton2 = (RadioButton)radioGroup2.getChildAt(savedRadioIndex2);
			    	  savedCheckedRadioButton2.setChecked(true);
			    		    	  
			    	  
			    	 }
		    	 private void LoadPreferences3(){
			    	  SharedPreferences sharedPreferences3 = getSharedPreferences("MY_SHARED_PREF3", MODE_PRIVATE);
			    	
			    	  int savedRadioIndex3 = sharedPreferences3.getInt(KEY_SAVED_RADIO_BUTTON_INDEX3, 0);
			    	  
			    	  RadioButton savedCheckedRadioButton3 = (RadioButton)radioGroup3.getChildAt(savedRadioIndex3);
			    	  savedCheckedRadioButton3.setChecked(true);
			    		    	  
			    	  
			    	 }
				@Override
				public void onBackPressed() {
					// TODO Auto-generated method stub
					super.onBackPressed();
					Settings.this.finish();
				}
		    	 
		    	 
		    	 
		    	 
	
}
