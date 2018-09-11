
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class kPDTS_swing extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JLabel vrijeme = new JLabel("");
	public static JLabel datum = new JLabel("");
	public static JLabel mjesec = new JLabel("");
	public static JLabel godina = new JLabel("");
	public static JLabel provjeraPodsjetnika = new JLabel("Nemate podsjetnika za danas!");
	static DatumIvrijeme dV = new DatumIvrijeme();
	static int year = Integer.parseInt(dV.getYear());
	static int month1 = Integer.parseInt(dV.getMonth());
	static Thread t = new Thread() {
		public void run() {
			DatumIvrijeme vd = new DatumIvrijeme();

			for (;;) {
				try {
					hh = vd.getHours();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mm = vd.getMinutes();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ss = vd.getSekunde();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vrijeme.setText("" + hh + ":" + mm + ":" + ss);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	static ActionListener click = null;
	public JButton d1, d3, d2, d4, d5, d6, d7;
	public static JFrame okvir = new JFrame("Izbornik");
	public static JFrame okvir2 = new JFrame("Kalendar");
	public static JFrame okvir3 = new JFrame("Postojeæi podsjetnici");
	public static JFrame okvir4 = new JFrame("kPDTS_swing");
	public static JFrame okvir5 = new JFrame("kPDTS_swing");
	public static JFrame okvir6 = new JFrame("kPDTS_swing");
	public static JFrame okvir7 = new JFrame("kPDTS_swing");
	public static TableModel tabela;
	public static JTable kal = new JTable(tabela);

	// metoda koja definiše izgled okvira kalendara te odgovarajuæe dogaðaje
	public static void okvir2GUI() {

		okvir.setVisible(false);
		okvir2.setPreferredSize(new Dimension(500, 500));
		okvir2.setResizable(false);
		okvir2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Container panel = okvir2.getContentPane();
		SpringLayout sL = new SpringLayout();
		panel.setLayout(sL);
		Dimension dim = new Dimension(90, 30);
		JButton sledeci = new JButton("Sledeci");
		sledeci.setPreferredSize(dim);
		JButton prethodni = new JButton("Prethodni");
		prethodni.setPreferredSize(dim);
		JButton nazad = new JButton("Nazad");
		nazad.setPreferredSize(dim);
		JButton ok = new JButton("OK");
		ok.setPreferredSize(dim);
		panel.add(prethodni);
		panel.add(sledeci);
		panel.add(nazad);
		panel.add(ok);
		panel.add(mjesec);
		panel.add(godina);
		sL.putConstraint(SpringLayout.NORTH, mjesec, 280, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, mjesec, 200, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, godina, 280, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, godina, 220, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, prethodni, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, prethodni, 75, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, ok, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, ok, 165, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, nazad, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, nazad, 255, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, sledeci, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, sledeci, 345, SpringLayout.NORTH, panel);
		// niz koji sadrži imena kolona kalendara
		String[] kolone = { "Ned", "Pon", "Uto", "Sri", "Èet", "Pet", "Sub" };
		// definisanje modela tabele koja sadrži kolone iz niza kolone
		tabela = new DefaultTableModel(null, kolone);

		kal.setModel(tabela);

		JScrollPane pane = new JScrollPane(kal);
		pane.setPreferredSize(new Dimension(300, 200));
		kal.setPreferredSize(new Dimension(300, 250));
		panel.add(pane);

		sL.putConstraint(SpringLayout.NORTH, pane, 50, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, pane, 100, SpringLayout.NORTH, panel);
		prethodni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (month1 == 1) {
					month1 = 12;
					year = year - 1;
				} else {
					month1 = month1 - 1;
				}
				updateMonth();
			}
		});
		sledeci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (month1 == 12) {
					month1 = 1;
					year = year + 1;
				} else {
					month1 = month1 + 1;
				}
				updateMonth();
			}
		});
		updateMonth();
		okvir2.pack();
		okvir2.setVisible(true);
	}

	// metoda koja u sluèaju promjene updejtuje mjesec koji se prikazuje
	public static void updateMonth() {
		// definisanje novog gregorijanskog kalendara
		GregorianCalendar kalendar = new GregorianCalendar();
		// podešavanje trenutnog datuma kalendara
		kalendar.set(year, month1 - 1, 1);
		// vraæanje koji je dan prvi u sedmici zbog uvlaèenja prvog dana u sedmici
		int prviDan = kalendar.get(Calendar.DAY_OF_WEEK);
		// vraæanje maksimalnog broja dana u datom mjesecu
		int brojDana = kalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// vraæanje broja sedmica u datom mjesecu
		int brojSedmica = kalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		// èišæenje modela tabele radi nobvog ispisa
		for (int i = tabela.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) tabela).removeRow(i);
		}

		// podešavanje broja redova modela tabele
		((DefaultTableModel) tabela).setRowCount(brojSedmica + 1);

		// popunjavanje podela tabele sa danima u mjesecu
		int i = prviDan - 1;
		// petlja koja popunjava tabelu za npr i=3 prvi dan æe biti na mjesu u tabeli
		// 0,3 odnosno srijeda prema kolonama tabele
		for (int dan = 1; dan <= brojDana; dan++) {
			tabela.setValueAt(dan, i / 7, i % 7);
			i++;
		}
		// podešavanje labela na trenutni mjesec i godinu
		mjesec.setText("" + month1 + ",");
		godina.setText("" + year + " godine");
	}

	public static JLabel zaDatum = new JLabel("Podsjetnik za datum:");
	public static JLabel napomena = new JLabel("Napomena:");
	public static JLabel datuma = new JLabel("Izraðeno(Updejetovano) datuma:");
	public static JLabel ID = new JLabel("ID podsjetnika");
	public static JTextField datum2 = new JTextField("");
	public static JTextField napomena2 = new JTextField("");
	public static JTextField ID2 = new JTextField("");
	public static JTextField datuma2 = new JTextField("");
	public static JLabel lijevo = new JLabel("N");
	public static JLabel desno = new JLabel("N");

	// definisanje okvira i dogaðaja za èitanje postojeæih podsjetnika u bazi
	public static void okvir3GUI() throws ClassNotFoundException, SQLException {
		// skrivanje okvira sa izbornikom
		okvir.setVisible(false);
		// definisanje parametara novog okvira
		okvir3.setPreferredSize(new Dimension(500, 500));
		okvir3.setResizable(false);
		okvir3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// definisanje panela okvira te izrada dimenzioniranje i pozicioniranje
		// elemenata
		Container panel = okvir3.getContentPane();
		SpringLayout sL = new SpringLayout();

		panel.setLayout(sL);
		panel.add(zaDatum);
		panel.add(napomena);
		panel.add(datuma);
		panel.add(ID);
		panel.add(lijevo);
		panel.add(desno);
		panel.add(datum2);
		panel.add(ID2);
		panel.add(napomena2);
		panel.add(datuma2);
		datum2.setPreferredSize(new Dimension(100, 30));
		ID2.setPreferredSize(new Dimension(60, 30));
		napomena2.setPreferredSize(new Dimension(200, 100));
		datuma2.setPreferredSize(new Dimension(200, 30));
		ID2.setEnabled(false);
		datuma2.setEditable(false);
		// pozicioniranje elemenata na lajoutu
		sL.putConstraint(SpringLayout.NORTH, ID2, 40, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, ID2, 230, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, datum2, 100, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, datum2, 230, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, napomena2, 160, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, napomena2, 230, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, datuma2, 280, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, datuma2, 230, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, ID, 40, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, ID, 40, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, zaDatum, 100, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, zaDatum, 40, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, napomena, 160, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, napomena, 40, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, datuma, 280, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, datuma, 40, SpringLayout.NORTH, panel);
		Dimension dim = new Dimension(90, 30);
		JButton sledeci = new JButton("Sledeci");
		sledeci.setPreferredSize(dim);
		JButton prethodni = new JButton("Prethodni");
		prethodni.setPreferredSize(dim);
		JButton brisanje = new JButton("Izbriši");
		brisanje.setPreferredSize(dim);
		JButton update = new JButton("Update");
		update.setPreferredSize(dim);
		panel.add(prethodni);
		panel.add(sledeci);
		panel.add(brisanje);
		panel.add(update);

		sL.putConstraint(SpringLayout.NORTH, desno, 400, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, desno, 425, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, lijevo, 400, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, lijevo, 75, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, prethodni, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, prethodni, 75, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, update, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, update, 165, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, brisanje, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, brisanje, 255, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, sledeci, 420, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, sledeci, 345, SpringLayout.NORTH, panel);
		// metoda koja se spaja na bazu te ispisuje prvi podsjetnik u polja
		getContent();
		desno.setText("" + (((counter - counterN) - 1) + ">"));
		lijevo.setText("<" + (counterN));
		counterP = counterN;
		// dogoðaj koji uèitava sledeæi podsjetnik (ako postoji)
		sledeci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (counterN < counter) {
					if (counterN == (counter - 1)) {
						counterN = 0;
					} else {
						counterN++;
					}
					ID2.setText(set[counterN][0]);
					datum2.setText(set[counterN][1]);
					napomena2.setText(set[counterN][2]);
					datuma2.setText(set[counterN][3]);

				} else if (counterN == (counter - 1)) {
					counterN = 0;
					ID2.setText(set[counterN][0]);
					datum2.setText(set[counterN][1]);
					napomena2.setText(set[counterN][2]);
					datuma2.setText(set[counterN][3]);
					counterN++;

				}
				desno.setText("" + ((counter - counterN) - 1 + ">"));
				lijevo.setText("<" + (counterN));
				counterP = counterN;
			}

		});
		prethodni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (counterN == 0) {
					counterN = counter - 1;
				} else {
					counterN--;
				}

				ID2.setText(set[counterN][0]);
				datum2.setText(set[counterN][1]);
				napomena2.setText(set[counterN][2]);
				datuma2.setText(set[counterN][3]);

				desno.setText("" + ((counter - counterN) - 1 + ">"));
				lijevo.setText("<" + (counterN));

			}

		});
		// dogaðaj koji updejtuje promjene koje je korisnik izvršio te ispisuje poruku
		// ako su promjene uspješno izvršene
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final String USERNAME = "admin";
				final String PASSWORD = "admin";

				final String CONN_STRING = "jdbc:mysql://localhost:3306/Napomene?useLegacyDatetimeCode=false&serverTimezone=UTC";
				String query1 = "UPDATE `podsjetnici` SET `Datum_izrade` = '" + datuma2.getText() + "', `Napomena` = '"
						+ napomena2.getText() + "', `ZaDatum` = '" + datum2.getText() + "' WHERE `podsjetnici`.`ID` = "
						+ Integer.parseInt(ID2.getText()) + " ";

				try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

						Statement statement = connection.createStatement();) {
					statement.executeUpdate(query1);
					JOptionPane.showMessageDialog(okvir3, "Update uspješno izvršen!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter = 1;
				counterN = 0;
				try {
					getContent();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				desno.setText("" + ((counter - counterN) - 1 + ">"));
				lijevo.setText("<" + (counterN));
			}
		});
		brisanje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final String USERNAME = "admin";
				final String PASSWORD = "admin";

				final String CONN_STRING = "jdbc:mysql://localhost:3306/Napomene?useLegacyDatetimeCode=false&serverTimezone=UTC";
				String query1 = "DELETE FROM `podsjetnici`  WHERE `podsjetnici`.`ID` = "
						+ Integer.parseInt(ID2.getText()) + " ";

				try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

						Statement statement = connection.createStatement();) {
					statement.executeUpdate(query1);
					JOptionPane.showMessageDialog(okvir3, "Podsjetnik uspjeðno izbrisan!!!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					getContent();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		okvir3.pack();
		okvir3.setVisible(true);
	}

	static String[][] set;
	static int counter = 1, counterN = 0, counterP = 0;

	// metoda koja se spaja na bazu te na osnovu odgovora na SQl upit popunjava
	// dvodimenzionalni niz 'set'
	public static void getContent() throws SQLException, ClassNotFoundException {
		final String USERNAME = "admin";
		final String PASSWORD = "admin";

		final String CONN_STRING = "jdbc:mysql://localhost:3306/Napomene?useLegacyDatetimeCode=false&serverTimezone=UTC";
		try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			ResultSet rS = null;

			String query = "SELECT * FROM podsjetnici";
			rS = statement.executeQuery(query);
			if (rS.first() == false) {
				napomena2.setText("Nemate podsjetnika!!!!");
			} else {
				while (rS.isLast() == false) {
					counter++;
					rS.next();
				}
			}
			set = new String[counter][4];
			int k = 0;
			rS = statement.executeQuery(query);
			while (k < counter) {
				if (rS.isLast() == false) {
					rS.next();
				}
				set[k][0] = String.valueOf(rS.getInt("ID"));
				set[k][1] = String.valueOf(rS.getString("zaDatum"));
				set[k][2] = String.valueOf(rS.getString("napomena"));
				set[k][3] = String.valueOf(rS.getString("Datum_izrade"));
				k++;

			}
			if (rS.first() == false) {
				napomena2.setText("Nemate podsjetnika!!!!");
			} else {
				ID2.setText("" + rS.getInt("ID"));
				datum2.setText(rS.getString("ZaDatum"));
				napomena2.setText(rS.getString("napomena"));
				datuma2.setText(rS.getString("Datum_izrade"));
			}

		}
	}

	public static void okvir4GUI() {

		okvir.setVisible(false);
		okvir4.setPreferredSize(new Dimension(500, 500));
		okvir4.setResizable(false);
		okvir4.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Container panel = okvir4.getContentPane();
		SpringLayout sL = new SpringLayout();
		okvir4.pack();
		okvir4.setVisible(true);
	}

	public static void okvir5GUI() {

		okvir.setVisible(false);
		okvir5.setPreferredSize(new Dimension(500, 500));
		okvir5.setResizable(false);
		okvir5.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Container panel = okvir5.getContentPane();
		SpringLayout sL = new SpringLayout();
		okvir5.pack();
		okvir5.setVisible(true);
	}

	public static void okvir6GUI() {

		okvir.setVisible(false);
		okvir6.setPreferredSize(new Dimension(500, 500));
		okvir6.setResizable(false);
		okvir6.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Container panel = okvir6.getContentPane();
		SpringLayout sL = new SpringLayout();
		okvir6.pack();
		okvir6.setVisible(true);
	}

	public static void okvir7GUI() {

		okvir.setVisible(false);
		okvir7.setPreferredSize(new Dimension(500, 500));
		okvir7.setResizable(false);
		okvir7.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		Container panel = okvir7.getContentPane();
		SpringLayout sL = new SpringLayout();
		okvir7.pack();
		okvir7.setVisible(true);
	}

	// metoda koja kreira izgleda i funkcije poèetnog okvira i izbornika
	public static void createAndShowGUI() throws IOException, InterruptedException {

		// postavljanje opcije koja zatvara okvir u sluèaju klika na X
		okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okvir.setBounds(0, 0, 200, 200);
		// kreiranje panela i lajouta okvira
		Container panel = okvir.getContentPane();
		SpringLayout sL = new SpringLayout();
		// definisanje lebela dugmadi i poziconiranje na okviru

		JLabel datumIvrijeme = new JLabel("Vrijeme i datum:");

		okvir.setPreferredSize(new Dimension(500, 500));
		okvir.setResizable(false);
		JButton d1 = new JButton("Kalendar");
		Dimension dim;
		dim = new Dimension(250, 35);
		d1.setPreferredSize(dim);
		JButton d2 = new JButton("Postojeæi podsjetnici");
		d2.setPreferredSize(dim);
		JButton d3 = new JButton("Izmjeniti postojeæe podsjetnike");
		d3.setPreferredSize(dim);
		JButton d4 = new JButton("Brisati poostojeæe podsjetnike");
		d4.setPreferredSize(dim);
		JButton d5 = new JButton("Dodati novi podsjetnik");
		d5.setPreferredSize(dim);
		JButton d6 = new JButton("Provjeriti raèunarsko vrijeme");
		d6.setPreferredSize(dim);

		// dodavanje dogaðaja na dugmad izbornika
		d1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				okvir2GUI();
				okvir2.addWindowListener(new WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						okvir.setVisible(true);
						okvir2.setVisible(false);
					}
				});

			}

		});
		d2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					okvir3GUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				okvir3.addWindowListener(new WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						okvir.setVisible(true);
						okvir3.setVisible(false);
					}
				});
			}

		});
		d3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				okvir4GUI();

				okvir4.addWindowListener(new WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						okvir.setVisible(true);
						okvir4.setVisible(false);
					}
				});
			}

		});
		d4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				okvir5GUI();
				okvir5.addWindowListener(new WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						okvir.setVisible(true);
						okvir5.setVisible(false);
					}
				});
			}

		});
		d5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				okvir6GUI();
				okvir6.addWindowListener(new WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						okvir.setVisible(true);
						okvir6.setVisible(false);
					}
				});
			}

		});
		d6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				okvir7GUI();
				okvir7.addWindowListener(new WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						okvir.setVisible(true);
						okvir7.setVisible(false);
					}
				});
			}

		});

		panel.add(d1);
		panel.add(d2);
		panel.add(d3);
		panel.add(d4);
		panel.add(d5);
		panel.add(d6);

		panel.add(provjeraPodsjetnika);
		panel.add(datumIvrijeme);
		panel.add(vrijeme);
		panel.add(datum);
		panel.setLayout(sL);
		sL.putConstraint(SpringLayout.NORTH, datum, 450, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, datum, 390, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, vrijeme, 450, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, vrijeme, 330, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, datumIvrijeme, 450, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, datumIvrijeme, 220, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, provjeraPodsjetnika, 50, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, provjeraPodsjetnika, 125, SpringLayout.NORTH, panel);

		sL.putConstraint(SpringLayout.NORTH, d1, 100, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, d1, 125, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, d2, 140, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, d2, 125, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, d3, 180, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, d3, 125, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, d4, 220, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, d4, 125, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, d5, 260, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, d5, 125, SpringLayout.NORTH, panel);
		sL.putConstraint(SpringLayout.NORTH, d6, 300, SpringLayout.WEST, panel);
		sL.putConstraint(SpringLayout.WEST, d6, 125, SpringLayout.NORTH, panel);

//sL.putConstraint(SpringLayout.NORTH, d2,
		// 2,
		// SpringLayout.EAST, d1);
		// finiširanje okvira te pokazivanje okvira
		okvir.pack();
		okvir.setVisible(true);
		// metoda koja poziva vrijeme koje je updejtuje nakon svake sekunde
		t.start();
		getDate();
	}

	public static String hh, mm;
	public static String ss;
	public static String dd;
	public static String MM;
	public static String yy;

	public static void getDate() {
		DatumIvrijeme vd = new DatumIvrijeme();
		dd = vd.getDay();
		MM = vd.getMonth();
		yy = vd.getYear();
		datum.setText("" + dd + "." + MM + "." + yy);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				try {
					createAndShowGUI();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		// TODO Auto-generated method stub
		if (click.getSource() == d1.getComponent(getComponentCount())) {
			provjeraPodsjetnika.setText("Dugme");
			t.start();

		}
	}

}
