/***************************
Class: MyObjectOutputStream.java
SWE314 Project phase 1
Fall 2023
Team:
Faisal Alwahhabi 443102495
Mishary Alaeena 443101459
Mishary Aldawood 443102219
Mishary Almuammmar 443101420
Talal Alrafee 443100850
Turki Alsugair 443101786




**************************/
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream{

	
    MyObjectOutputStream() throws IOException
    {
        // Super keyword refers to parent class instance
        super();
    }
	
    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }
    
	protected void writeStreamHeader() throws IOException
	{}
}
