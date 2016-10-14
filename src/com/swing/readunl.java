package com.swing;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class readunl {
	private String[] uid=new String[1000];
	private String[] dob=new String[1000];
	private String[] name=new String[1000];
	private String[] age=new String[1000];
	int i=0,j=0;
	readunl(){
		read();
	}
	public void read()
	{
		//File file=new File("/Swing/Employee.txt");
		Path path=Paths.get("C:/Users/s00377515/workspace/Swing/Employee.txt");
		Path path1=Paths.get("C:/Users/s00377515/workspace/Swing/Employee1.txt");
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(path,charset)) {
			String line = null;
			BufferedWriter writer = Files.newBufferedWriter(path1, charset);
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				String[] s=line.toString().split("-");
					uid[j]=s[0];
					dob[j]=s[1];
					name[j]=s[2];
					age[j++]=s[3];
				    writer.append(line, 0, line.length());
				    writer.newLine();
			}
			writer.flush();
			writer.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		
	}
	public String[] getuid(){
		return uid;
	}
	public String[] getdob(){
		return dob;
	}
	public String[] getname(){
		return name;
	}
	public String[] getage(){
		return age;
	}

}
