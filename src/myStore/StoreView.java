package myStore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public static void main(String[] args) {
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
        cartPanel.add(cartLabel);
        cartPanel.setPreferredSize(new Dimension(200, 400));

        //create SYSC2004Panel
        JLabel SYSC2004Label1 = new JLabel("SYSC2004");
        JLabel SYSC2004Label2 = new JLabel("($" + storeManager.managerGetPrice(1) + ") - Stock:" + storeManager.managerGetStock(1));
        JPanel SYSC2004LabelPanel = new JPanel(new BorderLayout());
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

        SYSC2004LabelPanel.add(SYSC2004Label1, BorderLayout.NORTH);
        SYSC2004LabelPanel.add(SYSC2004Label2, BorderLayout.CENTER);
        SYSC2004LabelPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2004LabelPanel.setBackground(new Color(255, 194, 253));
        SYSC2004ButtonPanel.add(addSYSC2004Button, BorderLayout.LINE_START);
        SYSC2004ButtonPanel.add(removeSYSC2004Button, BorderLayout.LINE_END);
        SYSC2004ButtonPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2004ButtonPanel.setBackground(new Color(255, 194, 253));
        SYSC2004Panel.add(SYSC2004LabelPanel, BorderLayout.PAGE_START);
        SYSC2004Panel.add(SYSC2004ButtonPanel, BorderLayout.PAGE_END);
        SYSC2004Panel.setPreferredSize(new Dimension(200, 200));
        SYSC2004Panel.setBackground(new Color(255, 194, 253));

        //create SYSC2320Panel
        JLabel SYSC2320Label1 = new JLabel("SYSC2320");
        JLabel SYSC2320Label2 = new JLabel("($" + storeManager.managerGetPrice(2) + ") - Stock:" + storeManager.managerGetStock(2));
        JPanel SYSC2320LabelPanel = new JPanel(new BorderLayout());
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

        SYSC2320LabelPanel.add(SYSC2320Label1, BorderLayout.NORTH);
        SYSC2320LabelPanel.add(SYSC2320Label2, BorderLayout.CENTER);
        SYSC2320LabelPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2320LabelPanel.setBackground(new Color(137, 245, 166));
        SYSC2320ButtonPanel.add(addSYSC2320Button, BorderLayout.LINE_START);
        SYSC2320ButtonPanel.add(removeSYSC2320Button, BorderLayout.LINE_END);
        SYSC2320ButtonPanel.setPreferredSize(new Dimension(200, 50));
        SYSC2320ButtonPanel.setBackground(new Color(137, 245, 166));
        SYSC2320Panel.add( SYSC2320LabelPanel, BorderLayout.PAGE_START);
        SYSC2320Panel.add( SYSC2320ButtonPanel, BorderLayout.PAGE_END);
        SYSC2320Panel.setPreferredSize(new Dimension(200, 200));
        SYSC2320Panel.setBackground(new Color(137, 245, 166));

        //create CCDP2100Panel
        JLabel CCDP2100Label1 = new JLabel("CCDP2100");
        JLabel CCDP2100Label2 = new JLabel("($" + storeManager.managerGetPrice(3) + ") - Stock:" + storeManager.managerGetStock(3));
        JPanel CCDP2100LabelPanel = new JPanel(new BorderLayout());
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

        CCDP2100LabelPanel.add(CCDP2100Label1, BorderLayout.NORTH);
        CCDP2100LabelPanel.add(CCDP2100Label2, BorderLayout.CENTER);
        CCDP2100LabelPanel.setPreferredSize(new Dimension(200, 50));
        CCDP2100LabelPanel.setBackground(new Color(194, 239, 255));
        CCDP2100ButtonPanel.add(addCCDP2100Button, BorderLayout.LINE_START);
        CCDP2100ButtonPanel.add(removeCCDP2100Button, BorderLayout.LINE_END);
        CCDP2100ButtonPanel.setPreferredSize(new Dimension(200, 50));
        CCDP2100ButtonPanel.setBackground(new Color(194, 239, 255));
        CCDP2100Panel.add(CCDP2100LabelPanel, BorderLayout.PAGE_START);
        CCDP2100Panel.add(CCDP2100ButtonPanel, BorderLayout.PAGE_END);
        CCDP2100Panel.setPreferredSize(new Dimension(200, 200));
        CCDP2100Panel.setBackground(new Color(194, 239, 255));

        //create COMP2804Panel
        JLabel COMP2804Label1 = new JLabel("COMP2804");
        JLabel COMP2804Label2 = new JLabel("($" + storeManager.managerGetPrice(4) + ") - Stock:" + storeManager.managerGetStock(4));
        JPanel COMP2804LabelPanel = new JPanel(new BorderLayout());
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

        COMP2804LabelPanel.add(COMP2804Label1, BorderLayout.NORTH);
        COMP2804LabelPanel.add(COMP2804Label2, BorderLayout.CENTER);
        COMP2804LabelPanel.setPreferredSize(new Dimension(200, 50));
        COMP2804LabelPanel.setBackground(new Color(255, 230, 194));
        COMP2804ButtonPanel.add(addCOMP2804Button, BorderLayout.LINE_START);
        COMP2804ButtonPanel.add(removeCOMP2804Button, BorderLayout.LINE_END);
        COMP2804ButtonPanel.setPreferredSize(new Dimension(200, 50));
        COMP2804ButtonPanel.setBackground(new Color(255, 230, 194));
        COMP2804Panel.add(COMP2804LabelPanel, BorderLayout.PAGE_START);
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
