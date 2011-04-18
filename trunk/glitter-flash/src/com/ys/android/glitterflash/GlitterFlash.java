package com.ys.android.glitterflash;


import java.util.List;

import com.ys.android.glitterflash.R;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.SeekBar;


public class GlitterFlash extends Activity  implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{
    /** Called when the activity is first created. */
	private Camera camera;
	private boolean flag;
	private Button btnSwitch;
	private SeekBar seekBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        flag = false;
        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        btnSwitch.setText("open");
        btnSwitch.setOnClickListener(this);
      
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
    }
    public void onClick(View v) {
    	    	
    	if(!flag)
    	{
    		if(OpenCamera())
    		{
    			flag = !flag;
    			btnSwitch.setText("close");
    		}
    		else
    		{
    			Toast.makeText(
    	                this,
    	                "failed to open",
    	                Toast.LENGTH_LONG).show();  
    		}
    	}
    	else
    	{    		
    		if(CloseCamera())
    		{
    			flag = !flag;
    			btnSwitch.setText("open");
    		}
    		else
    		{
    			Toast.makeText(
    	                this,
    	                "failed to close",
    	                Toast.LENGTH_LONG).show();  
    		}
    	}
    	
    	
    }
       
    private boolean OpenCamera()
    {
    	try
    	{    		
    	    camera = Camera.open();
    	    Parameters parameters = camera.getParameters();
//    	    List<String> list = parameters.getSupportedFlashModes();
    	   
//    	    for(int i = 0; i < list.size(); i++)
//    	    {
//    	    	Toast.makeText(this, list.get(i), Toast.LENGTH_LONG).show(); 
//    	    }

    	    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
    	    
    	    camera.setParameters(parameters);
    	    camera.startPreview();    	      	    
    	}
    	catch(Exception e)
    	{
    		System.out.print(e.toString());
    		return false;
    	}
    	
    	return true;        
    }

    private boolean CloseCamera()
    {
    	try
    	{   
    		camera.stopPreview();
    		camera.release();
    	}
    	catch(Exception e)
    	{
    		System.out.print(e.toString());
    		return false;
    	}    	       
        
        return true;
    }
	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		   Toast.makeText(this,
                   String.valueOf(seekBar.getProgress()),
                   Toast.LENGTH_SHORT).show();

	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}