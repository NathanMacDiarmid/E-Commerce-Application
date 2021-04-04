package myStore;

import com.sun.codemodel.internal.JOp;
import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StoreView {
    private StoreManager storeManager;
    private final int CART_ID;
    private final JFrame frame;

    /**
     * Constructor
     * @param storeManager
     * @param iD
     *
     * Raises a IllegalArgumentException if the id is negative
     */
    public StoreView(StoreManager storeManager, int iD){
        if (iD < 0) {
            throw new IllegalArgumentException("ID cannot be negative.");
        }
        else {
            this.storeManager = storeManager;
            this.CART_ID = iD;
            this.frame = new JFrame();
        }
    }

    public ImageIcon getImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("src/myStore/resources/antiquebooks450.png"));
        Image img = image.getScaledInstance(150, 150, 20);
        ImageIcon photo = new ImageIcon(img);
        return photo;
    }


    public static void main(String[] args) throws IOException {
        //initialize new StoreManager with inventory and StoreView
        StoreManager storeManager = new StoreManager();
        Product p1 = new Product("SYSC2004", 1, 45.0f);
        Product p2 = new Product("SYSC2320", 2, 50.0f);
        Product p3 = new Product("CCDP2100", 3, 35.0f);
        Product p4 = new Product("COMP2804", 4, 80.0f);
        storeManager.getInventory().addStock(p1, 30);
        storeManager.getInventory().addStock(p2, 10);
        storeManager.getInventory().addStock(p3, 20);
        storeManager.getInventory().addStock(p4, 35);
        StoreView storeView = new StoreView(storeManager, storeManager.assignNewCartID());
        storeView.frame.setTitle("Client StoreView");
        storeView.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bodyPanel = new JPanel(new GridLayout(2, 2));

        //create header
        JLabel headerLabel = new JLabel("Welcome to The Course Store! (ID: " + storeView.CART_ID + ")");
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(headerLabel);
        headerPanel.setPreferredSize(new Dimension(400, 40));

        //create shopping cart panel
        JPanel cartPanel = new JPanel(new BorderLayout());
        JLabel cartLabel = new JLabel(storeManager.returnCartRepresentation(storeView.CART_ID));

        //create shopping button panel
        JPanel cartButtonPanel = new JPanel(new BorderLayout());

        JButton checkout = new JButton("Checkout");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(storeView.frame,
                         storeManager.returnCartRepresentation(storeView.CART_ID) + "\n\n"
                + "Total: " + "$" + storeManager.getPrice(storeView.CART_ID), "Checkout", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.OK_OPTION) {
                    storeView.frame.setVisible(false);
                    storeView.frame.dispose();
                }
            }
        });

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(storeView.frame, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.OK_OPTION) {
                    storeView.frame.setVisible(false);
                    storeView.frame.dispose();
                }
            }
        });

        cartButtonPanel.add(checkout, BorderLayout.NORTH);
        cartButtonPanel.add(quit, BorderLayout.SOUTH);
        cartPanel.add(cartLabel, BorderLayout.CENTER);
        cartPanel.add(cartButtonPanel, BorderLayout.SOUTH);
        cartPanel.setPreferredSize(new Dimension(200, 400));

        //create SYSC2004Panel
        JLabel SYSC2004Label1 = new JLabel("SYSC2004 Textbook");
        JLabel SYSC2004Label2 = new JLabel("($" + storeManager.managerGetPrice(1) + ") - Stock:" + storeManager.managerGetStock(1));
        JPanel SYSC2004LabelPanel = new JPanel(new BorderLayout());
        JLabel SYSC2004PictureLabel = new JLabel(storeView.getImage());
        JPanel SYSC2004PicturePanel = new JPanel();
        JPanel SYSC2004ButtonPanel = new JPanel(new BorderLayout());
        JPanel SYSC2004Panel = new JPanel(new BorderLayout());

        JButton addSYSC2004Button = new JButton("+");
        addSYSC2004Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartAddProduct(storeView.CART_ID, 1, 1);
                SYSC2004Label2.setText("($" + storeManager.managerGetPrice(1) + ") - Stock:" + storeManager.managerGetStock(1));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton removeSYSC2004Button = new JButton("-");
        removeSYSC2004Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartRemoveProduct(storeView.CART_ID, 1, 1);
                SYSC2004Label2.setText("($" + storeManager.managerGetPrice(1) + ") - Stock:" + storeManager.managerGetStock(1));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton SYSC2004Details = new JButton("Details");
        SYSC2004Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(storeView.frame, "This textbook for SYSC2004, Object Oriented Programming,\n" +
                        "will aide the student in understanding course material.", "SYSC2004 Textbook Details", JOptionPane.DEFAULT_OPTION);
            }
        });

        SYSC2004LabelPanel.add(SYSC2004Label1, BorderLayout.NORTH);
        SYSC2004LabelPanel.add(SYSC2004Label2, BorderLayout.CENTER);
        SYSC2004LabelPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2004LabelPanel.setBackground(new Color(255, 194, 253));

        SYSC2004PicturePanel.add(SYSC2004PictureLabel, BorderLayout.CENTER);
        SYSC2004PicturePanel.setPreferredSize(new Dimension(200, 50));
        SYSC2004PicturePanel.setBackground(new Color(255, 194, 253));

        SYSC2004ButtonPanel.add(addSYSC2004Button, BorderLayout.LINE_START);
        SYSC2004ButtonPanel.add(SYSC2004Details, BorderLayout.CENTER);
        SYSC2004ButtonPanel.add(removeSYSC2004Button, BorderLayout.LINE_END);
        SYSC2004ButtonPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2004ButtonPanel.setBackground(new Color(255, 194, 253));

        SYSC2004Panel.add(SYSC2004LabelPanel, BorderLayout.PAGE_START);
        SYSC2004Panel.add(SYSC2004PicturePanel, BorderLayout.CENTER);
        SYSC2004Panel.add(SYSC2004ButtonPanel, BorderLayout.PAGE_END);
        SYSC2004Panel.setPreferredSize(new Dimension(200, 200));
        SYSC2004Panel.setBackground(new Color(255, 194, 253));

        //create SYSC2320Panel
        JLabel SYSC2320Label1 = new JLabel("SYSC2320 Textbook");
        JLabel SYSC2320Label2 = new JLabel("($" + storeManager.managerGetPrice(2) + ") - Stock:" + storeManager.managerGetStock(2));
        JPanel SYSC2320LabelPanel = new JPanel(new BorderLayout());
        JLabel SYSC2320PictureLabel = new JLabel(storeView.getImage());
        JPanel SYSC2320PicturePanel = new JPanel();
        JPanel SYSC2320ButtonPanel = new JPanel(new BorderLayout());
        JPanel SYSC2320Panel = new JPanel(new BorderLayout());

        JButton addSYSC2320Button = new JButton("+");
        addSYSC2320Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartAddProduct(storeView.CART_ID, 2, 1);
                SYSC2320Label2.setText("($" + storeManager.managerGetPrice(2) + ") - Stock:" + storeManager.managerGetStock(2));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton removeSYSC2320Button = new JButton("-");
        removeSYSC2320Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartRemoveProduct(storeView.CART_ID, 2, 1);
                SYSC2320Label2.setText("($" + storeManager.managerGetPrice(2) + ") - Stock:" + storeManager.managerGetStock(2));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });

        JButton SYSC2320Details = new JButton("Details");
        SYSC2320Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(storeView.frame, "This textbook for SYSC2320, Computer Organization\n" +
                        "and Architecture, will aide the student in understanding course material.", "SYSC2320 Textbook Details",
                        JOptionPane.DEFAULT_OPTION);
            }
        });

        SYSC2320LabelPanel.add(SYSC2320Label1, BorderLayout.NORTH);
        SYSC2320LabelPanel.add(SYSC2320Label2, BorderLayout.CENTER);
        SYSC2320LabelPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2320LabelPanel.setBackground(new Color(137, 245, 166));

        SYSC2320PicturePanel.add(SYSC2320PictureLabel, BorderLayout.CENTER);
        SYSC2320PicturePanel.setPreferredSize(new Dimension(200, 50));
        SYSC2320PicturePanel.setBackground(new Color(137, 245, 166));

        SYSC2320ButtonPanel.add(addSYSC2320Button, BorderLayout.LINE_START);
        SYSC2320ButtonPanel.add(SYSC2320Details, BorderLayout.CENTER);
        SYSC2320ButtonPanel.add(removeSYSC2320Button, BorderLayout.LINE_END);
        SYSC2320ButtonPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2320ButtonPanel.setBackground(new Color(137, 245, 166));

        SYSC2320Panel.add( SYSC2320LabelPanel, BorderLayout.PAGE_START);
        SYSC2320Panel.add(SYSC2320PicturePanel, BorderLayout.CENTER);
        SYSC2320Panel.add( SYSC2320ButtonPanel, BorderLayout.PAGE_END);
        SYSC2320Panel.setPreferredSize(new Dimension(200, 200));
        SYSC2320Panel.setBackground(new Color(137, 245, 166));

        //create CCDP2100Panel
        JLabel CCDP2100Label1 = new JLabel("CCDP2100 Textbook");
        JLabel CCDP2100Label2 = new JLabel("($" + storeManager.managerGetPrice(3) + ") - Stock:" + storeManager.managerGetStock(3));
        JPanel CCDP2100LabelPanel = new JPanel(new BorderLayout());
        JLabel CCDP2100PictureLabel = new JLabel(storeView.getImage());
        JPanel CCDP2100PicturePanel = new JPanel();
        JPanel CCDP2100ButtonPanel = new JPanel(new BorderLayout());
        JPanel CCDP2100Panel = new JPanel(new BorderLayout());

        JButton addCCDP2100Button = new JButton("+");
        addCCDP2100Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartAddProduct(storeView.CART_ID, 3, 1);
                CCDP2100Label2.setText("($" + storeManager.managerGetPrice(3) + ") - Stock:" + storeManager.managerGetStock(3));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton removeCCDP2100Button = new JButton("-");
        removeCCDP2100Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartRemoveProduct(storeView.CART_ID, 3, 1);
                CCDP2100Label2.setText("($" + storeManager.managerGetPrice(3) + ") - Stock:" + storeManager.managerGetStock(3));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton CCDP2100Details = new JButton("Details");
        CCDP2100Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(storeView.frame, "This textbook for CCDP2100, Communications for\n" +
                        "Engineers, will aide the student in understanding course material.", "CCDP2100 Textbook Details",
                        JOptionPane.DEFAULT_OPTION);
            }
        });

        CCDP2100LabelPanel.add(CCDP2100Label1, BorderLayout.NORTH);
        CCDP2100LabelPanel.add(CCDP2100Label2, BorderLayout.CENTER);
        CCDP2100LabelPanel.setPreferredSize(new Dimension(200, 50));
        CCDP2100LabelPanel.setBackground(new Color(194, 239, 255));

        CCDP2100PicturePanel.add(CCDP2100PictureLabel, BorderLayout.CENTER);
        CCDP2100PicturePanel.setPreferredSize(new Dimension(200, 50));
        CCDP2100PicturePanel.setBackground(new Color(194, 239, 255));

        CCDP2100ButtonPanel.add(addCCDP2100Button, BorderLayout.LINE_START);
        CCDP2100ButtonPanel.add(CCDP2100Details, BorderLayout.CENTER);
        CCDP2100ButtonPanel.add(removeCCDP2100Button, BorderLayout.LINE_END);
        CCDP2100ButtonPanel.setPreferredSize(new Dimension(200, 50));
        CCDP2100ButtonPanel.setBackground(new Color(194, 239, 255));

        CCDP2100Panel.add(CCDP2100LabelPanel, BorderLayout.PAGE_START);
        CCDP2100Panel.add(CCDP2100PicturePanel, BorderLayout.CENTER);
        CCDP2100Panel.add(CCDP2100ButtonPanel, BorderLayout.PAGE_END);
        CCDP2100Panel.setPreferredSize(new Dimension(200, 200));
        CCDP2100Panel.setBackground(new Color(194, 239, 255));

        //create COMP2804Panel
        JLabel COMP2804Label1 = new JLabel("COMP2804 Textbook");
        JLabel COMP2804Label2 = new JLabel("($" + storeManager.managerGetPrice(4) + ") - Stock:" + storeManager.managerGetStock(4));
        JPanel COMP2804LabelPanel = new JPanel(new BorderLayout());
        JLabel COMP2804PictureLabel = new JLabel(storeView.getImage());
        JPanel COMP2804PicturePanel = new JPanel();
        JPanel COMP2804ButtonPanel = new JPanel(new BorderLayout());
        JPanel COMP2804Panel = new JPanel(new BorderLayout());

        JButton addCOMP2804Button = new JButton("+");
        addCOMP2804Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartAddProduct(storeView.CART_ID, 4, 1);
                COMP2804Label2.setText("($" + storeManager.managerGetPrice(4) + ") - Stock:" + storeManager.managerGetStock(4));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton removeCOMP2804Button = new JButton("-");
        removeCOMP2804Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeView.storeManager.cartRemoveProduct(storeView.CART_ID, 4, 1);
                COMP2804Label2.setText("($" + storeManager.managerGetPrice(4) + ") - Stock:" + storeManager.managerGetStock(4));
                cartLabel.setText(storeManager.returnCartRepresentation(storeView.CART_ID));
            }
        });
        JButton COMP2804Details = new JButton("Details");
        COMP2804Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(storeView.frame, "This textbook for COMP2804, Discrete Structures II,\n" +
                        "will aide the student in understanding course material.", "COMP2804 Textbook Details",
                        JOptionPane.DEFAULT_OPTION);
            }
        });

        COMP2804LabelPanel.add(COMP2804Label1, BorderLayout.NORTH);
        COMP2804LabelPanel.add(COMP2804Label2, BorderLayout.CENTER);
        COMP2804LabelPanel.setPreferredSize(new Dimension(200, 50));
        COMP2804LabelPanel.setBackground(new Color(255, 230, 194));

        COMP2804PicturePanel.add(COMP2804PictureLabel, BorderLayout.CENTER);
        COMP2804PicturePanel.setPreferredSize(new Dimension(200, 50));
        COMP2804PicturePanel.setBackground(new Color(255, 230, 194));

        COMP2804ButtonPanel.add(addCOMP2804Button, BorderLayout.LINE_START);
        COMP2804ButtonPanel.add(COMP2804Details, BorderLayout.CENTER);
        COMP2804ButtonPanel.add(removeCOMP2804Button, BorderLayout.LINE_END);
        COMP2804ButtonPanel.setPreferredSize(new Dimension(200, 50));
        COMP2804ButtonPanel.setBackground(new Color(255, 230, 194));

        COMP2804Panel.add(COMP2804LabelPanel, BorderLayout.PAGE_START);
        COMP2804Panel.add(COMP2804PicturePanel, BorderLayout.CENTER);
        COMP2804Panel.add(COMP2804ButtonPanel, BorderLayout.PAGE_END);
        COMP2804Panel.setPreferredSize(new Dimension(200, 200));
        COMP2804Panel.setBackground(new Color(255, 230, 194));

        //add everything to mainPanel then add mainPanel to frame then pack frame
        bodyPanel.add(SYSC2004Panel);
        bodyPanel.add(SYSC2320Panel);
        bodyPanel.add(CCDP2100Panel);
        bodyPanel.add(COMP2804Panel);
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);
        mainPanel.add(cartPanel, BorderLayout.LINE_END);
        storeView.frame.add(mainPanel);
        storeView.frame.pack();
        storeView.frame.setVisible(true);
    }
}
