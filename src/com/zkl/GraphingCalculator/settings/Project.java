package com.zkl.GraphingCalculator.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

public class Project {
	static ObjectOutputStream out = null;
	static ObjectInputStream in = null;
	Context context = null;
	String fileName = "conf.gc";
	public Project(Context context){
		this.context = context;
	}
	public void SaveProject(SystemConfigeration systemConfigeration){
		try{
			FileOutputStream fos = context.openFileOutput(fileName, 0);
			out=new ObjectOutputStream(fos);
			out.writeObject(systemConfigeration);
			out.flush();
			out.close();
			out=null;
			System.gc();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}
	public SystemConfigeration OpenProject(){
		SystemConfigeration systemConfigeration = null;
		try{ 
			in =null;
			FileInputStream fis =context.openFileInput(fileName); 
			in =new ObjectInputStream(fis);
			systemConfigeration = (SystemConfigeration)in.readObject();
			in.close();
			in=null;
			System.gc();
			}
		catch (FileNotFoundException e) {
			systemConfigeration = new SystemConfigeration();
			SaveProject(systemConfigeration);
		}
		catch(Exception e){
			return null;
			}
		return systemConfigeration;
	}
	
}
