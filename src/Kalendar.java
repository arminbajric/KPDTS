
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Kalendar {
	public int godina=1,mjesec=1;
	GregorianCalendar kalendar=new GregorianCalendar();
  Kalendar()
{}
  //konstruktor kalednara
  Kalendar(int newgodina,int newmjesec)
  {
	  //dodjeljivanje korisnièkih ulaznih podataka globalnim varijablama godina i mjesec
	  godina=newgodina;
	  mjesec=newmjesec;
	  //setovanje kalendara na korisnièki unešene vrijednosti te na prvi dan u mjesecu
	  kalendar.set(godina, mjesec-1, 1);
  }
  //metoda koja ispisuje mjesec i godinu
  void ispisiKalendar()
  {
	  mjesecPrint();
	  System.out.print(godina+"g.  ");
	  
  }
  //metoda koja ispisuje mjesec(string) u zavisnosti od korisnièki unešene vrijednosti
  void mjesecPrint()
  {
	  //switch selekcija koja ispisuje mjesec na osnovu unešene brojèane vrijednosti
	  switch(mjesec)
	  {
	  case 1:
			System.out.print("\tJanuar,");
		break;
		case 2:
			System.out.print("\tFebruar,");
			break;
		case 3:
			System.out.print("\tMart,");
		break;
		case 4:
			System.out.print("\tApril,");
			break;
		case 5:
			System.out.print("\tMaj,");
		break;
		case 6:
			System.out.print("\tJun,");
			break;
		case 7:
			System.out.print("\tJul,");
		break;
		case 8:
			System.out.print("\tAugust,");
			break;
		case 9:
			System.out.print("\tSeptembar,");
		break;
		case 10:
			System.out.print("\tOktobar,");
			break;
		case 11:
			System.out.print("\tNovembar,");
		break;
		case 12:
			System.out.print("\tDecembar,");
			break;
			default : System.out.println("Porešan unos!");
		}
	  }
  void ispisiDane()
  {
	  //ispis zaglavlja kalendara
	  System.out.println();
	  System.out.println("\tNED\tPON\tUTO\tSRI\tCET\tPET\tSUB  ");
	  //odreðivanje prvog dana u mjesecu
	  int prviDan=kalendar.get(Calendar.DAY_OF_WEEK);
	  //odreðivanje broja dana u unešenom mjesecu
	  int brojDana=kalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	  //uvlaèenje (ako ima potrebe)   i ispis prve sedmice
	  for(int i=0;i<=6-(8-prviDan);i++)
	  {
		  System.out.print("\t");
	  }
	  int j=1;
	  for(j=1;j<=(8-prviDan);j++)
	  {
		  System.out.print("\t"+j);
	  }
	  System.out.println();
	  //ispis ostalih sedmica 
	 
	 while(j<=brojDana)
	 {
		 //varijabla m nam koristi za ispis punih sedmica
		  int m=0;
		  while(m<7)
		  {
			  //provjera da li su veæ svi dani ispisani tako što se poredi sa maksimalnim brojem dana u mjesecu
			  if(j<=brojDana)
			  {
			  
			  System.out.print("\t"+j);
			  j++;
			  m++;
			  }
			  //ukoliko je gornji uslov ispunjen pomoæu else zatvaraju se while petlje odnosno dani u mjesecu su veæ ispisani
			  else
			  {
			  m++;
			  j++;
			  }
		  }
		  //formatiranje kalendara tako što æe na kraju svake sedmice prebaciti u novi red
		  System.out.println();
	  }
	
	 
	  
  }
  
}
