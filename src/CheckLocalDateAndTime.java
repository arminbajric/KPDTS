
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class CheckLocalDateAndTime {

	public String strDateToSet;
	public String strTimeToSet;
	public void CLDAT() throws IOException {
		// TODO Auto-generated method stub
   
    URL adresa=new URL("http://www.pool.ntp.org");
    URLConnection con=adresa.openConnection();
    long d=con.getDate();
    
    Date datum=new Date(d);
    char[] date=new char[35];
    String hours=datum.toString();
    date=hours.toCharArray();
    String hh=String.valueOf(date[11])+date[12];
    String mm=String.valueOf(date[14])+date[15];
    String ss=String.valueOf(date[17])+date[18];
    String yy=String.valueOf(date[25])+date[26]+date[27]+date[28];
    String MM=String.valueOf(date[4])+date[5]+date[6];
    DatumIvrijeme primjer=new DatumIvrijeme();
    String MMM=primjer.getMonthint(MM);
    String dd=String.valueOf(date[8])+date[9];
    strDateToSet=dd+"-"+MMM+"-"+yy;
     strTimeToSet=hh+":"+mm+":"+ss;
    
    
   
    
	}
public void SetComputertime() throws IOException {
	Runtime.getRuntime().exec("cmd /C date " + strDateToSet + "& time " + strTimeToSet);
	System.out.println("Vrijeme i datum podešeni!");
}
}
