import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class OutlinePing extends JFrame{
	
	String getPort;
	//Thread th = new Thread(new TimerRunable());
	public OutlinePing() {
		super("Network Scanner");
		Font myFont = new Font("Serif", Font.BOLD, 16);
		// menu begin
		JDialog di = new JDialog();
		JButton btStart;
		ImageIcon img = new ImageIcon("./Button_Image/start.png");
		btStart = new JButton(img);
		btStart.setPreferredSize(new Dimension(300, 50));

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu scanMenu = new JMenu("Scan");
		JMenu gotoMenu = new JMenu("Go to");
		JMenu commandsMenu = new JMenu("Commands");
		JMenu favoritesMenu = new JMenu("Favorite");
		JMenu toolsMenu = new JMenu("Tools");
		JMenu helpMenu = new JMenu("Help");

		menubar.add(scanMenu);
		menubar.add(gotoMenu);
		menubar.add(commandsMenu);
		menubar.add(favoritesMenu);
		menubar.add(toolsMenu);
		menubar.add(helpMenu);

		JMenuItem loadFromFileAction = new JMenuItem("Load from file...");
		JMenuItem exportAllAction = new JMenuItem("Export all...");
		JMenuItem exportSelectionAction = new JMenuItem("Export selection...");
		JMenuItem quitAction = new JMenuItem("Quit");

		scanMenu.add(loadFromFileAction);
		scanMenu.add(exportAllAction);
		scanMenu.add(exportSelectionAction);
		scanMenu.add(quitAction);

		JMenuItem nextAliveHostAction = new JMenuItem("Next alive host");
		JMenuItem nextOpenPortAction = new JMenuItem("Next open port");
		JMenuItem NextDeadHostAction = new JMenuItem("Next dead host");
		JMenuItem previousAliveHostAction = new JMenuItem("previous alive host");
		JMenuItem previousOpenPortAction = new JMenuItem("previous open port");
		JMenuItem previousDeadHostAction = new JMenuItem("Previous dead host");
		JMenuItem findAction = new JMenuItem("Find...");

		gotoMenu.add(nextAliveHostAction);
		gotoMenu.add(nextOpenPortAction);
		gotoMenu.add(NextDeadHostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(previousAliveHostAction);
		gotoMenu.add(previousOpenPortAction);
		gotoMenu.add(previousDeadHostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(findAction);

		JMenuItem showDetailsAction = new JMenuItem("Show details");
		JMenuItem rescanIPsAction = new JMenuItem("Rescan IP(s)");
		JMenuItem deleteIPsAction = new JMenuItem("Delete IP(s)");
		JMenuItem copyIPAction = new JMenuItem("Copy IP");
		JMenuItem copyDetailsAction = new JMenuItem("Copy details");
		JMenuItem openaction = new JMenuItem("Open");

		commandsMenu.add(showDetailsAction);
		commandsMenu.addSeparator();
		commandsMenu.add(rescanIPsAction);
		commandsMenu.add(deleteIPsAction);
		commandsMenu.addSeparator();
		commandsMenu.add(copyIPAction);
		commandsMenu.add(copyDetailsAction);
		commandsMenu.addSeparator();
		commandsMenu.add(openaction);

		JMenuItem addCurrentAction = new JMenuItem("Add current...");
		JMenuItem manageFavoritesAction = new JMenuItem("Manage favorites...");

		favoritesMenu.add(addCurrentAction);
		favoritesMenu.add(manageFavoritesAction);

		JMenuItem preferencesAction = new JMenuItem("Preferences...");
		JMenuItem fetchersAction = new JMenuItem("Fetchers...");
		JMenuItem selectionAction = new JMenuItem("Selection");
		JMenuItem scanStatisticsAction = new JMenuItem("Scan statistics");

		toolsMenu.add(preferencesAction);
		toolsMenu.add(fetchersAction);
		toolsMenu.addSeparator();
		toolsMenu.add(selectionAction);
		toolsMenu.add(scanStatisticsAction);

		JMenuItem gettingStatedAction = new JMenuItem("Getting Stated");
		JMenuItem officialWebsiteAction = new JMenuItem("Official website");
		JMenuItem faqAction = new JMenuItem("FAQ");
		JMenuItem reportAnIssueAction = new JMenuItem("Report an issue");
		JMenuItem pluginsAction = new JMenuItem("Plugins");
		JMenuItem commandLineUsageAction = new JMenuItem("Command-line usage");
		JMenuItem checkForNewerVersionAction = new JMenuItem("Check for newer version...");
		JMenuItem aboutAction = new JMenuItem("About");

		helpMenu.add(gettingStatedAction);
		helpMenu.add(officialWebsiteAction);
		helpMenu.add(faqAction);
		helpMenu.add(reportAnIssueAction);
		helpMenu.add(pluginsAction);
		helpMenu.add(commandLineUsageAction);
		helpMenu.add(checkForNewerVersionAction);
		helpMenu.add(aboutAction);
		
	
		aboutAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//String name=JOptionPane.showInputDialog("about iptester");
				JOptionPane.showMessageDialog(null, "about ipscanner"+"\n"+"\n"+"\n"+"program is belongs to pjpark");	
				
			}
		});
		quitAction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		// menu end

		// statusbar begin

		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		JLabel readyLabel = new JLabel("Ready");
		JLabel displayLabel = new JLabel("Display All");
		JLabel threadLabel = new JLabel("Thread: 0");
		statusPanel.add(readyLabel);
		statusPanel.add(displayLabel);
		statusPanel.add(threadLabel);
		readyLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		displayLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		threadLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		readyLabel.setPreferredSize(new Dimension(300, 20));
		displayLabel.setPreferredSize(new Dimension(150, 20));
		threadLabel.setPreferredSize(new Dimension(150, 20));
		readyLabel.setFont(myFont);
		displayLabel.setFont(myFont);
		threadLabel.setFont(myFont);
		// statusbar end

		// table begin
		String titles[] = new String[] { "IP", "Ping", "TTL", "Hostname", "Ports[1024+]" };
		Object[][] stats = initializeTableData();
		JTable jTable = new JTable(stats, titles);

		JScrollPane sp = new JScrollPane(jTable);
		add(sp, BorderLayout.CENTER);
		// table end

		// toolbar begin
		JToolBar toolbar1 = new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolbar2 = new JToolBar();
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel IbRangStart = new JLabel("IP Range:");
		JTextField tfRangeStart = new JTextField(10);
		JLabel IbRangend = new JLabel("to ");
		JTextField tfRangend = new JTextField(10);

		IbRangend.setFont(myFont);
		tfRangend.setPreferredSize(new Dimension(90, 30));
		IbRangStart.setFont(myFont);
		tfRangeStart.setPreferredSize(new Dimension(90, 30));

		JLabel lbHostname = new JLabel("Hostname: ");
		JTextField tfHostname = new JTextField(10);
		JButton btup = new JButton("IP");
		JComboBox cbOption = new JComboBox();
		cbOption.addItem("/24");
		cbOption.addItem("/26");
		// JButton btStart = new JButton(img);

		lbHostname.setFont(myFont);
		tfHostname.setPreferredSize(new Dimension(90, 30));
		btup.setPreferredSize(new Dimension(90, 30));
		cbOption.setPreferredSize(new Dimension(90, 30));
		btStart.setPreferredSize(new Dimension(90, 30));
		toolbar1.add(IbRangStart);
		toolbar1.add(tfRangeStart);
		toolbar1.add(IbRangend);
		toolbar1.add(tfRangend);

		toolbar2.add(lbHostname);
		toolbar2.add(tfHostname);
		toolbar2.add(btup);
		toolbar2.add(cbOption);
		toolbar2.add(btStart);

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1, BorderLayout.NORTH);
		pane.add(toolbar2, BorderLayout.SOUTH);

		add(pane, BorderLayout.NORTH);

		// toolbar end

		String myIp = null;
		String myHostname = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();

			myIp = ia.getHostAddress();
			myHostname = ia.getHostName();
		} catch (Exception e) {

		}
		String fixedIp = myIp.substring(0, myIp.lastIndexOf(".") + 1);
		tfRangeStart.setText(fixedIp + "1");
		tfRangend.setText(fixedIp + "254");
		tfHostname.setText(myHostname);

		// System.out.println(myIp + " " + myHostname);

		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		btStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "���� �մϴ�!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				Pinging[] pi = new Pinging[254];
				for (int i = 0; i <= 253; i++) {
					pi[i] = new Pinging(fixedIp + (i + 1));
					pi[i].start();
				}
				for (int i = 0; i <= 253; i++) {
					Object[] msg = pi[i].getMsg();
					if (msg[1] != null) {
						// port SCanner

						ExecutorService es = Executors.newFixedThreadPool(20);

						String myHostname = null;
						try {
							InetAddress ia = InetAddress.getLocalHost();
							
							pi[i].ip = ia.getHostAddress();
							myHostname = ia.getHostName();
						} catch (Exception e1) {
						}
						String ip = fixedIp+i;
						int timeout = 200;
						ArrayList<Future<ScanResult>> futures = new ArrayList<>();
						for (int port = 1; port <= 1024; port++) {
							futures.add(portlsOpen(es, ip, port, timeout));
						}
						try {
							es.awaitTermination(200L, TimeUnit.MILLISECONDS);
							int openPorts = 0;
							getPort = "";
							for (final Future<ScanResult> f : futures) {
								if (f.get().isOpen()) {
									openPorts++;
									if (getPort.equals("")) {
										getPort = Integer.toString(f.get().getPort());
									} else {
										getPort += ", " + Integer.toString(f.get().getPort());
									}
								}
							}
						} catch (Exception ee) {
							ee.printStackTrace();
						}

						stats[i][4] = getPort;

					}
					
					

					if (msg[1] == null) {
						msg[1] = "[n/a]";
						msg[2] = "[n/s]";
						msg[3] = "[n/s]";
						stats[i][4] = "[n/s]";
					} else if (msg[3] == null) {
						msg[3] = "[n/a]";
					}
					stats[i][0] = msg[0];
					stats[i][1] = msg[1];
					stats[i][2] = msg[2];
					stats[i][3] = msg[3];
				}
				jTable.repaint();
				JOptionPane.showMessageDialog(null, "���� �Ϸ�!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		rescanIPsAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {	

				JOptionPane.showMessageDialog(null, "���� �մϴ�!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				Pinging[] pi = new Pinging[254];
				for (int i = 0; i <= 253; i++) {
					pi[i] = new Pinging(fixedIp + (i + 1));
					pi[i].start();
				}
				for (int i = 0; i <= 253; i++) {
					Object[] msg = pi[i].getMsg();
					if (msg[1] != null) {
						// port SCanner

						ExecutorService es = Executors.newFixedThreadPool(20);

						String myHostname = null;
						try {
							InetAddress ia = InetAddress.getLocalHost();

							pi[i].ip = ia.getHostAddress();
							myHostname = ia.getHostName();
						} catch (Exception e1) {
						}
						String ip = fixedIp+i;
						int timeout = 200;
						ArrayList<Future<ScanResult>> futures = new ArrayList<>();
						for (int port = 1; port <= 1024; port++) {
							futures.add(portlsOpen(es, ip, port, timeout));
						}
						try {
							es.awaitTermination(200L, TimeUnit.MILLISECONDS);
							int openPorts = 0;
							getPort = "";
							for (final Future<ScanResult> f : futures) {
								if (f.get().isOpen()) {
									openPorts++;
									if (getPort.equals("")) {
										getPort = Integer.toString(f.get().getPort());
									} else {
										getPort += ", " + Integer.toString(f.get().getPort());
									}
								}
							}
						} catch (Exception ee) {
							ee.printStackTrace();
						}

						stats[i][4] = getPort;

					}
					
					

					if (msg[1] == null) {
						msg[1] = "[n/a]";
						msg[2] = "[n/s]";
						msg[3] = "[n/s]";
						stats[i][4] = "[n/s]";
					} else if (msg[3] == null) {
						msg[3] = "[n/a]";
					}
					stats[i][0] = msg[0];
					stats[i][1] = msg[1];
					stats[i][2] = msg[2];
					stats[i][3] = msg[3];
				}
				jTable.repaint();
				JOptionPane.showMessageDialog(null, "���� �Ϸ�!", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});
	
	
}
	public static Future<ScanResult> portlsOpen(final ExecutorService es, final String ip, final int port,
			final int timeout) {
		return es.submit(new Callable<ScanResult>() {
			public ScanResult call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new ScanResult(port, true);
				} catch (Exception ex) {
					return new ScanResult(port, false);
				}
			}
		});
	}

	public Object[][] initializeTableData() {
		Object[][] results = new Object[254][5];
		return results;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutlinePing op = new OutlinePing();
	}


}