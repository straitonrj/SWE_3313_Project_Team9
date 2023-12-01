package org.UCars;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class HomeScreen extends JFrame{
    DecimalFormat df = new DecimalFormat("#,##0.00");
    private JPanel HomePageBackground ;
    private JLabel HelloUser;
    private JTextField SearchBar;
    private JButton HomeButton;
    private JButton ProfileButton;
    private JButton CartButton;
    private JComboBox FilterBox;
    private JPanel HomePage;
    private JToolBar TopToolBar;
    private JPanel InfoPage;
    private JButton HomeButton2;
    private JButton CartButton2;
    private JButton ProfileButton2;
    private JComboBox FilterBox2;
    private JTextField SearchBar2;
    private JPanel CartPage;
    private JToolBar CartToolBar;
    private JButton CartHomeButton;
    private JTextField CartSearchBar;
    private JComboBox CartFilter;
    private JButton CartCart;
    private JButton CartProfile;
    private JLabel HelloUser3;
    private JLabel HelloUser2;
    private JButton HomeButton3;
    private JButton CartButton3;
    private JButton ProfileButton3;
    private JPanel PaymentPage;
    private JLabel HelloUser4;
    private JLabel InfoUserLabel;
    private JPanel InventoryHolder = new JPanel();
    private JPanel InfoPanel = new JPanel();
    private JScrollPane ScrollHome = new JScrollPane(InventoryHolder,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JScrollPane ScrollInfo = new JScrollPane(InfoPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel CartPanel = new JPanel();
    private JScrollPane ScrollCart = new JScrollPane(CartPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private JPanel PaymentPanel = new JPanel();
    private JScrollPane ScrollPayment = new JScrollPane(PaymentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private Font f1 = new Font(Font.SANS_SERIF,Font.PLAIN,24);
    private Font f2 = new Font(Font.SANS_SERIF,Font.PLAIN,50);
    private static User CUser;

    ArrayList<Inventory> Cart = new ArrayList<>();
    String[] Cars = new String[Main.CheckAvailableInventory().size()];
    BigDecimal[] CarsPrice = new BigDecimal[Main.CheckAvailableInventory().size()];
    String[] ImagePaths = new String[Main.CheckAvailableInventory().size()];



    ImageIcon[] CarIcons = new ImageIcon[Cars.length];
    public HomeScreen(){
         for(int i = 0; i < Main.CheckAvailableInventory().size(); i++ ){
            Cars[i] = Main.CheckAvailableInventory().get(i).getName();
            ImagePaths[i] = Main.CheckAvailableInventory().get(i).getImageURL();
            CarsPrice[i] = Main.CheckAvailableInventory().get(i).getPrice();

        }







        setContentPane(HomePageBackground);
        setTitle("UCars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);

        //Setting up the logos for the buttons on the main toolbar
        ImageIcon HomeLogo = new ImageIcon("untitled/src/main/java/org/UCars/UCarsLogoWhite.png");
        HomeButton.setIcon(HomeLogo);
        HomeButton2.setIcon(HomeLogo);
        CartHomeButton.setIcon(HomeLogo);
        HomeButton3.setIcon(HomeLogo);
        ImageIcon CartLogo = new ImageIcon("untitled/src/main/java/org/UCars/CartLogo.png");
        CartButton.setIcon(CartLogo);
        CartButton2.setIcon(CartLogo);
        CartCart.setIcon(CartLogo);
        CartButton3.setIcon(CartLogo);
        ImageIcon ProfileLogo = new ImageIcon("untitled/src/main/java/org/UCars/ProfileLogo.png");
        ProfileButton.setIcon(ProfileLogo);
        ProfileButton2.setIcon(ProfileLogo);
        CartProfile.setIcon(ProfileLogo);
        ProfileButton3.setIcon(ProfileLogo);
        //Recognize Current User
        CUser = Main.getCurrentUser();
        HelloUser.setText("Hello, " + CUser.getUsername());
        HelloUser2.setText("Hello, " + CUser.getUsername());
        HelloUser3.setText("Hello, " + CUser.getUsername());
        HelloUser4.setText("Hello, " + CUser.getUsername());
        //Loading all images for the panels
        CarImageLoad();


        //Setting up the main home page
        HomePage.add(ScrollHome, BorderLayout.CENTER);
        ScrollHome.getVerticalScrollBar().setUnitIncrement(16);
        InventoryHolder.setLayout(new BoxLayout(InventoryHolder,BoxLayout.Y_AXIS));
        CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
        CartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Cart.isEmpty()) {
                InventoryHolder.removeAll();
                ((CardLayout) HomePageBackground.getLayout()).show(HomePageBackground,"Card3");
                CreateCart();
                }
            }
        });

        //Setting up the info pages for each car
        InfoPage.add(ScrollInfo, BorderLayout.CENTER);
        ScrollInfo.getVerticalScrollBar().setUnitIncrement(16);
        InfoPanel.setLayout(new GridLayout(1,2,0,0));
        InfoPanel.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 17, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-500));
        HomeButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryHolder.removeAll();
                InfoPanel.removeAll();
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });
        CartButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Cart.isEmpty()) {
                    InventoryHolder.removeAll();
                    InfoPanel.removeAll();
                    ((CardLayout) HomePageBackground.getLayout()).show(HomePageBackground,"Card3");
                    CreateCart();

                }
            }
        });

        //Setting up the cart page
        CartPage.add(ScrollCart, BorderLayout.CENTER);
        ScrollCart.getVerticalScrollBar().setUnitIncrement(16);
        CartPanel.setLayout(new BoxLayout(CartPanel,BoxLayout.Y_AXIS));
        //CartPanel.setMinimumSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-30, 1));



        CartHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartPanel.removeAll();
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });

        //Setting up the payment page
        PaymentPage.add(ScrollPayment,BorderLayout.CENTER);
        ScrollPayment.getVerticalScrollBar().setUnitIncrement(16);
        PaymentPanel.setLayout(new BoxLayout(PaymentPanel,BoxLayout.PAGE_AXIS));
        HomeButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentPanel.removeAll();
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });

    }
    //Creates a home page based on the order of inventory used
    public void CreateInventory(int InventoryLength, String[] CName, String[] CPrice){







        JPanel[] InventoryCells = new JPanel[InventoryLength];
        JPanel[] InventoryRows = new JPanel[(int)Math.ceil(InventoryLength/(double)3)];
        JLabel[] CarName= new JLabel[InventoryLength];
        JLabel[] CarPrice= new JLabel[InventoryLength];
        JLabel[] CarImage = new JLabel[InventoryLength];
        JToolBar[] Options = new JToolBar[InventoryLength];
        JButton[] AddToCart = new JButton[InventoryLength];
        JButton[] CarInfo = new JButton[InventoryLength];

        JPanel[] BlankCells =  new JPanel[3-(InventoryLength%3)];
        int NumCells = InventoryLength;
        int Counter;
        int CellNumber;


        //For statement used to create the rows filled with 3 blocks. These blocks can be filled or blank
        for(int j = 0; j < InventoryRows.length; j++){

            RowCreation(InventoryRows,j);

            //If statement Used to determine the number of filled cells
            if(NumCells < 3){
                Counter = NumCells%3;

            }
            else{
                Counter = 3;
            }
            //For statement used to create cells and count the amount of cells made
            for(int i = 0; i < Counter; i++ ) {
                CellNumber = (3 * j) + i;
                NumCells--;
                CellCreation(InventoryCells,CellNumber, InventoryRows[j]);

                //If statement used to measure the amount of blank cells needed
                if (Counter < 3 && i == Counter-1) {
                    //For statement used to create the amount of blank cells needed
                    for (int k = 0; k < BlankCells.length; k++) {
                        BlankCellCreation(BlankCells,k,InventoryRows[j]);
                    }
                }
                //Using methods to create a  visual representation of the inventory
                CreateLabelHolder(CarName,CarPrice,CellNumber, CName[CellNumber], CPrice[CellNumber],InventoryCells[CellNumber]);
                ImageCreation(CarImage,CarIcons[CellNumber],CellNumber,InventoryCells[CellNumber]);
                ToolBarCreation(Options,CellNumber,InventoryCells[CellNumber]);
                ButtonCreation(AddToCart, CellNumber,Options[CellNumber],BorderLayout.EAST, Color.GREEN, "Add to Cart",1);
                ButtonCreation(CarInfo, CellNumber,Options[CellNumber],BorderLayout.WEST, Color.GRAY, "         Info        ",2);
            }

        }
    }

    //Creates a JPanel with 3 columns and adds it to the main page
    public void RowCreation(JPanel[] Rows, int RowNumber){

        Rows[RowNumber] = new JPanel();
        Rows[RowNumber].setLayout(new GridLayout(1, 3, 0, 0));
        InventoryHolder.add(Rows[RowNumber]);

    }
    //Creates the JPanels to fill the 3 columns of the row called cells
    public void CellCreation(JPanel[] Cell, int CellNumber, JPanel Row){
        Cell[CellNumber] = new JPanel();
        Cell[CellNumber].setLayout(new BorderLayout());
        Cell[CellNumber].setBackground(Color.WHITE);
        Cell[CellNumber].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        Row.add(Cell[CellNumber]);
    }
    //Creates blank cells
    public void BlankCellCreation(JPanel[] BCells, int CellNumber, JPanel Row ){
        BCells[CellNumber] = new JPanel();
        BCells[CellNumber].setPreferredSize(new Dimension(200 / 3, 200));
        Row.add(BCells[CellNumber]);
    }
    //Creates a panel to hold the labels for the cells
    public void CreateLabelHolder(JLabel[] Name, JLabel[] Price, int CellNumber, String NameText, String PriceText, JPanel Cell ){
        JPanel[] LabelHolder = new JPanel[Cars.length];
        LabelHolder[CellNumber] = new JPanel(new GridLayout(2,1));
        LabelHolder[CellNumber].setBackground(Color.WHITE);
        LabelCreation(Name, CellNumber,NameText,LabelHolder[CellNumber] );
        LabelCreation(Price, CellNumber,PriceText,LabelHolder[CellNumber] );
        Cell.add(LabelHolder[CellNumber],BorderLayout.NORTH);

    }
    //Creates and adds labels to go into cells
    public void LabelCreation(JLabel[] Label, int CellNumber, String text, JPanel Holder ){
        Label[CellNumber] = new JLabel();
        Label[CellNumber].setText(text);
        Label[CellNumber].setFont(f1);
        Holder.add(Label[CellNumber]);
    }
    //Creates and adds icons to the cells
    public void ImageCreation(JLabel[] CImage, Icon image, int CellNumber, JPanel Cell){
        CImage[CellNumber] = new JLabel(CarIcons[CellNumber]);
        Cell.add(CImage[CellNumber], BorderLayout.CENTER);
    }
    //Creates and adds toolbars to the cells
    public void ToolBarCreation(JToolBar[] Bar, int CellNumber, JPanel Cell){
        Bar[CellNumber] = new JToolBar();
        Bar[CellNumber].setFloatable(false);
        Bar[CellNumber].setLayout(new BorderLayout());
        Bar[CellNumber].setPreferredSize(new Dimension(20, 50));
        Cell.add(Bar[CellNumber], BorderLayout.SOUTH);
    }
    //Creates and adds buttons to the toolbars
    public void ButtonCreation(JButton[] Button, int CellNumber, JToolBar Bar, String position, Color Color, String text, int ButtonType ){
        Button[CellNumber] = new JButton();
        Button[CellNumber].setText(text);
        Button[CellNumber].setBackground(Color);
        if(Color == Color.GREEN){
            for(int i = 0; i < Cart.size();i++){
                if(Main.CheckAvailableInventory().get(CellNumber).getInventoryID() == Cart.get(i).getInventoryID()){
                    Button[CellNumber].setBackground(Color.ORANGE);
                    i = Cart.size() + 1;
                }
            }
        }



        Bar.add(Button[CellNumber],position);
        if(ButtonType == 1){
            Button[CellNumber].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(!Cart.isEmpty()){
                        for(int i = 0; i < Cart.size() ;i++) {
                            if (Main.CheckAvailableInventory().get(CellNumber).getInventoryID() != Cart.get(i).getInventoryID()) {
                                Cart.add(Main.CheckAvailableInventory().get(CellNumber));
                                i = Cart.size() + 1;
                            }
                        }
                    } else {Cart.add(Main.CheckAvailableInventory().get(CellNumber));}
                    Button[CellNumber].setBackground(Color.ORANGE);
                    //System.out.println(Cart);
                }
            });
        } else if (ButtonType == 2) {
            Button[CellNumber].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InventoryHolder.removeAll();
                    InfoImageCreation(CarIcons[CellNumber]);


                    CarInfoCardCreation(Cars[CellNumber],ArrayConverter(CarsPrice)[CellNumber], Main.CheckAvailableInventory().get(CellNumber).getDescription(), CellNumber);

                    ((CardLayout)HomePageBackground.getLayout()).next(HomePageBackground);

                }
            });
        }

    }
    //Makes the car image on the info pages
    public void InfoImageCreation( ImageIcon CImage){
        JPanel Holder = new JPanel();
        Holder.setLayout(new BoxLayout(Holder, BoxLayout.Y_AXIS));
        BufferedImage Resize = new BufferedImage((int)(CImage.getIconWidth()*2),(int)(CImage.getIconHeight()*2), BufferedImage.TRANSLUCENT);
        Graphics2D Draw = (Graphics2D) Resize.createGraphics();
        Draw.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        Draw.drawImage(CImage.getImage(),0,0,(int)(CImage.getIconWidth()*2),(int)(CImage.getIconHeight()*2),null);
        ImageIcon New = new ImageIcon(Resize);
        JLabel ImageHolder = new JLabel();

        ImageHolder.setIcon(New);
        ImageHolder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


        Holder.add(ImageHolder);
        JPanel Spacer = new JPanel();
        Spacer.setBackground(Color.white);
        Holder.add(Spacer);
        InfoBackButtonCreation(Holder);
        Holder.setBackground(Color.white);

        InfoPanel.add(Holder);
    }
    //Creates the back button on the info pages
    public void InfoBackButtonCreation(JPanel Panel){
        JButton Back = new JButton();
        Back.setText("Back");
        Back.setFont(f1);
        Back.setBackground(Color.LIGHT_GRAY);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel.removeAll();
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });
        Panel.add(Back);
    }
    //
    public void CarInfoCardCreation(String CarName, String CarPrice, String CarInfo, int InventoryID){
        JPanel Card = new JPanel();
        Card.setBackground(Color.white);
        Card.setLayout(new BoxLayout(Card,BoxLayout.Y_AXIS));
        //Card.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel Name = new JLabel();
        Name.setText(CarName);
        Name.setFont(f2);
        Card.add(Name,Component.LEFT_ALIGNMENT);
        JLabel Price = new JLabel();
        Price.setText(CarPrice);
        Price.setFont(f2);
        Card.add(Price, Component.LEFT_ALIGNMENT);
        JTextArea Description = new JTextArea();
        Description.setFont(f1);
        Description.setText(CarInfo);
        Description.setLineWrap(true);
        Card.add(Description);
        InfoAddButtonCreation(Card, InventoryID);
        InfoPanel.add(Card, Component.LEFT_ALIGNMENT);

    }
    public void InfoAddButtonCreation(JPanel Panel, int InventoryID){
        JButton Add = new JButton();
        Add.setText("Add to Cart");
        Add.setFont(f1);
        Add.setBackground(Color.GREEN);
        for(int i = 0; i < Cart.size();i++){
            if(Main.CheckAvailableInventory().get(InventoryID).getInventoryID() == Cart.get(i).getInventoryID()){
                Add.setBackground(Color.ORANGE);
                i = Cart.size() + 1;
            }
        }


        Panel.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Cart.isEmpty()){
                    for(int i = 0; i < Cart.size() ;i++) {
                        if (Main.CheckAvailableInventory().get(InventoryID).getInventoryID() != Cart.get(i).getInventoryID()) {
                            Cart.add(Main.CheckAvailableInventory().get(InventoryID));
                            i = Cart.size() + 1;
                        }
                    }
                } else {Cart.add(Main.CheckAvailableInventory().get(InventoryID));}
                Add.setBackground(Color.ORANGE);
            }
        });
    }

    public void CarImageLoad (){
        try{


            for (int i = 0; i < CarIcons.length; i++){
                String Path = ImagePaths[i];
                URL PictureLink = new URL(Path);
                BufferedImage CarImage = ImageIO.read(PictureLink);
                //CarImage.getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/6, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3, Image.SCALE_SMOOTH);
                CarIcons[i] = new ImageIcon(CarImage.getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, -1, Image.SCALE_SMOOTH));

            }
        }
        catch (IOException E){
            System.out.println("Cannot read URL");
        }
    }
    //Create the Cart Page
    public void CreateCart(){

        CartPanel.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-17, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*(Cart.size())));

        ImageIcon[] Img = new ImageIcon[Cart.size()];
        URL[] Link = new URL[Cart.size()];
        //System.out.println("Making Cart" + Cart.size());
        JPanel[] CartHolder = new JPanel[Cart.size()];
        JPanel[] ImageHolder = new JPanel[Cart.size()];
        JLabel[] ImageLabel = new JLabel[Cart.size()];
        JLabel[] CarName = new JLabel[Cart.size()];
        JLabel[] CarPrice = new JLabel[Cart.size()];
        JToolBar[] ButtonHolder = new JToolBar[Cart.size()];
        JButton[] Remove = new JButton[Cart.size()];

        //Set up the options at the bottom of the cart page
        JPanel Spacer = new JPanel();
        JPanel OptionBox = new JPanel(new BorderLayout());
        JToolBar OptionsBar = new JToolBar();
        OptionsBar.setFloatable(false);

        OptionsBar.setBackground(Color.WHITE);

        Spacer.setBackground(Color.WHITE);

        JButton Back = new JButton();
        Back.setText("Back");
        Back.setFont(f2);
        Back.setBackground(Color.GRAY);

        JButton CheckOut = new JButton();
        CheckOut.setText("Checkout");
        CheckOut.setFont(f2);
        CheckOut.setBackground(Color.YELLOW);

        //Spacer.setBackground(Color.WHITE);

        for(int i = 0; i < Cart.size(); i++){


            CartHolder[i] = new JPanel();
            CartHolder[i].setLayout(new GridLayout(1,4));
            ImageLabel[i] = new JLabel();
            ImageHolder[i] = new JPanel(new BorderLayout());
            CarName[i] = new JLabel();
            CarPrice[i] = new JLabel();
            ButtonHolder[i] = new JToolBar();
            ButtonHolder[i].setFloatable(false);
            Remove[i] = new JButton();

            Remove[i].setText("Remove from cart ");
            Remove[i].setFont(f2);
            Remove[i].setBackground(Color.ORANGE);

            CartHolder[i].setBackground(Color.WHITE);
            ButtonHolder[i].setBackground(Color.WHITE);
            ImageHolder[i].setBackground(Color.WHITE);

            CarName[i].setText(Cart.get(i).getName());
            CarName[i].setFont(f2);
            CarPrice[i].setText("$"+df.format(Cart.get(i).getPrice()));
            CarPrice[i].setFont(f2);

            try{
                Link[i] = new URL(Cart.get(i).getImageURL());
                BufferedImage CImg = ImageIO.read(Link[i]);
                Img[i] = new ImageIcon(CImg.getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, -1, Image.SCALE_SMOOTH));
            }
            catch (IOException E){
                System.out.println("Cannot read URL");
            }

            ImageLabel[i].setIcon(Img[i]);
            ImageLabel[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,17));
            ImageHolder[i].add(ImageLabel[i], BorderLayout.CENTER);
            CartHolder[i].add(ImageHolder[i]);
            CartHolder[i].add(CarName[i]);
            CartHolder[i].add(CarPrice[i]);
            ButtonHolder[i].add(Remove[i], BorderLayout.CENTER);
            CartHolder[i].add(ButtonHolder[i]);
            CartPanel.add(CartHolder[i]);

            int finalI = i;
            //System.out.println(finalI);
            Remove[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //JOptionPane pane = new JOptionPane();
                    int input = JOptionPane.showConfirmDialog(null, "Are you sure","Are you sure",JOptionPane.YES_NO_OPTION);
                    if (input == 0){
                        Cart.remove(Cart.get(finalI));
                        CartPanel.remove(CartHolder[finalI]);
                        CartPanel.removeAll();
                        CreateCart();
                        SwingUtilities.updateComponentTreeUI(HomePageBackground);
                        InventoryHolder.removeAll();
                        CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                        if(Cart.size()==0){((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);}

                    }


                }
            });

        }

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartPanel.removeAll();
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });
        CheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartPanel.removeAll();
                CreatePayment();
                ((CardLayout)HomePageBackground.getLayout()).show(HomePageBackground,"Card4");
            }
        });

        //OptionBox.add(OptionsBar, BorderLayout.SOUTH);
        OptionsBar.add(Back, BorderLayout.WEST);
        OptionsBar.add(Spacer, BorderLayout.CENTER);
        OptionsBar.add(CheckOut, BorderLayout.EAST);
        CartPanel.add(OptionsBar);
    }

    //Creates the payment Page
    public void CreatePayment(){


        PaymentPanel.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-17, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*(Cart.size())));

        ImageIcon[] Img = new ImageIcon[Cart.size()];
        URL[] Link = new URL[Cart.size()];
        //System.out.println("Making Cart" + Cart.size());
        JPanel[] CartHolder = new JPanel[Cart.size()];
        JPanel[] ImageHolder = new JPanel[Cart.size()];
        JLabel[] ImageLabel = new JLabel[Cart.size()];
        JLabel[] CarName = new JLabel[Cart.size()];
        JLabel[] CarPrice = new JLabel[Cart.size()];
        JToolBar[] ButtonHolder = new JToolBar[Cart.size()];
        JButton[] InCart = new JButton[Cart.size()];

        JLabel Shipping = new JLabel();
        Shipping.setText("Shipping: $0.00");
        Shipping.setFont(f1);
        //Shipping Selections

        Shipping[] Ships = new Shipping[3];
        Ships[0] = new Shipping(1, "Ground Shipping", new BigDecimal(0));
        Ships[1] = new Shipping(2, "3-Day Shipping", new BigDecimal(19));
        Ships[2] = new Shipping(3, "Overnight Shipping", new BigDecimal(29));

        String[] SO = {"Ground Shipping","3-Day Shipping","Overnight Shipping"};
        JToolBar Ship = new JToolBar();
        Ship.setFloatable(false);
        Ship.setBackground(Color.WHITE);

        final BigDecimal[] C = {new BigDecimal(0)};

        //Setting up the card and shipping information
        //JPanel CustomerInformation = new JPanel();
        //CustomerInformation.setLayout(new BoxLayout(CustomerInformation,BoxLayout.X_AXIS));

        JPanel Totals = new JPanel();
        Totals.setLayout(new BoxLayout(Totals,BoxLayout.Y_AXIS));

        JLabel Subtotal = new JLabel();
        BigDecimal ST = new BigDecimal(0);
        for(int i = 0; i < Cart.size(); i++){
            //System.out.println(Cart.get(i).getPrice());
            ST = ST.add(Cart.get(i).getPrice());
        }
        Subtotal.setText("Subtotal: $" + df.format(ST));
        Subtotal.setFont(f1);

        JLabel Tax = new JLabel();
        BigDecimal TX = ST.multiply(new BigDecimal(".06"));
        Tax.setText("Tax: $" + df.format(TX));
        Tax.setFont(f1);

        JLabel Total = new JLabel();
        final BigDecimal[] T = {ST.add(TX).add(C[0]).add(C[0])};
        Total.setText("Total: $" + df.format(T[0]));
        Total.setFont(f1);


        //Allows users to select what shipping option they want
        JComboBox ShippingOptions = new JComboBox(SO);
        ShippingOptions.setFont(f1);
        final int[] SType = {0};
        ShippingOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BigDecimal chosen;
                JComboBox<String> Options = (JComboBox<String>) e.getSource();
                String Picked = (String) Options.getSelectedItem();
                if(Picked.equals("Ground Shipping")){
                    chosen = Ships[0].getPrice();
                    SType[0] = Ships[0].getShippingID();
                } else if (Picked.equals("3-Day Shipping")) {
                    chosen = Ships[1].getPrice();
                    SType[0] = Ships[1].getShippingID();
                }else if (Picked.equals("Overnight Shipping")){
                    chosen = Ships[2].getPrice();
                    SType[0] = Ships[2].getShippingID();
                }else {
                    chosen = Ships[0].getPrice();
                    SType[0] = Ships[0].getShippingID();
                }



                PaymentPanel.revalidate();
                PaymentPanel.repaint();
                C[0] = chosen;

                Shipping.setText("Shipping: $" + df.format(C[0]));
                Shipping.setFont(f1);
                T[0] = T[0].add(chosen);
                Total.setText("Total: $" + df.format(T[0]));
                //CreatePayment();
                //SwingUtilities.updateComponentTreeUI(PaymentPanel);


            }
        });

            Ship.setBackground(Color.WHITE);
            Ship.add(ShippingOptions);


        //Info input for credit cards and addresses
        JPanel CustomerInformation = new JPanel();
        CustomerInformation.setBackground(Color.WHITE);
        CustomerInformation.setLayout(new BoxLayout(CustomerInformation,BoxLayout.X_AXIS));

        JPanel Address = new JPanel();
        Address.setBackground(Color.WHITE);
        Address.setLayout(new BoxLayout(Address,BoxLayout.Y_AXIS));

        JPanel CreditCard = new JPanel();
        CreditCard.setBackground(Color.BLUE);
        CreditCard.setLayout(new BoxLayout(CreditCard,BoxLayout.X_AXIS));


        MaskFormatter MFC;
        MaskFormatter MFED;
        MaskFormatter MFCCV;

        final int[] CCNum = new int[1];
        final int[] CCVNum = {0};
        final int[] CCExDNum = new int[1];
        final String[] CUAddress = new String[1];

        JFormattedTextField CardNumber = new JFormattedTextField();
        JFormattedTextField ED = new JFormattedTextField();
        JFormattedTextField CCV = new JFormattedTextField();
        JTextField CA = new JTextField();

        try{
            MFC = new MaskFormatter("################");
            MFC.setPlaceholder("Card Number");
            CardNumber = new JFormattedTextField(MFC);
            CardNumber.setFont(f1);
            CardNumber.setColumns(16);

            MFED = new MaskFormatter("##/##");
            MFED.setPlaceholder("MM/YY");
            ED = new JFormattedTextField(MFED);
            ED.setFont(f1);
            ED.setColumns(4);

            MFCCV = new MaskFormatter("###");
            MFCCV.setPlaceholder("CCV");
            CCV = new JFormattedTextField(MFCCV);
            CCV.setFont(f1);
            CCV.setColumns(3);

            JLabel AddressLabel = new JLabel();
            AddressLabel.setText("Address");
            AddressLabel.setFont(f1);
            AddressLabel.setBackground(Color.WHITE);

            CA = new JTextField();
            CA.setFont(f1);



            CreditCard.add(CardNumber);
            CreditCard.add(ED);
            CreditCard.add(CCV);

            Address.add(AddressLabel);
            Address.add(CA);


        }catch (Exception e){
            System.out.println("The card input didnt work");
        }



        Totals.add(Subtotal);
        Totals.add(Tax);
        Totals.add(Shipping);
        Totals.add(Total);
        CustomerInformation.add(Totals);
        CustomerInformation.add(Address);
        CustomerInformation.add(CreditCard);



        //Set up the options at the bottom of the cart page
        JPanel Spacer = new JPanel();
        JPanel OptionBox = new JPanel(new BorderLayout());
        JToolBar OptionsBar = new JToolBar();
        OptionsBar.setFloatable(false);

        //Spacer.setPreferredSize(new Dimension(200, 80));

        OptionsBar.setBackground(Color.WHITE);
        //OptionsBar.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-17, 80));

        Spacer.setBackground(Color.WHITE);

        JButton Back = new JButton();
        Back.setText("Back");
        Back.setFont(f2);
        Back.setBackground(Color.GRAY);

        JButton CheckOut = new JButton();
        CheckOut.setText("Confirm Order");
        CheckOut.setFont(f2);
        CheckOut.setBackground(Color.YELLOW);

        JFormattedTextField finalCardNumber = CardNumber;
        JFormattedTextField finalCCV = CCV;
        JFormattedTextField finalED = ED;
        JTextField finalCA = CA;
        /*
        CheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // CCNum[0] = Integer.parseInt(finalCardNumber.getText());
                //CCVNum[0] = Integer.parseInt(finalCCV.getText());
               // CCExDNum[0] = Integer.parseInt(finalED.getText());
               // CUAddress[0] = finalCA.getText();
                Main.Checkout(Cart, SType[0]);

                PaymentPanel.removeAll();
                Main.CheckAvailableInventory();
                System.out.println(Main.CheckAvailableInventory());
                 Cars = new String[Main.CheckAvailableInventory().size()];
                 CarsPrice = new BigDecimal[Main.CheckAvailableInventory().size()];
                 ImagePaths = new String[Main.CheckAvailableInventory().size()];
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);


            }
        });

         */


        //Spacer.setBackground(Color.WHITE);

        for(int i = 0; i < Cart.size(); i++){


            CartHolder[i] = new JPanel();
            CartHolder[i].setLayout(new GridLayout(1,4));
            ImageLabel[i] = new JLabel();
            ImageHolder[i] = new JPanel(new BorderLayout());
            CarName[i] = new JLabel();
            CarPrice[i] = new JLabel();
            ButtonHolder[i] = new JToolBar();
            ButtonHolder[i].setFloatable(false);
            InCart[i] = new JButton();

            InCart[i].setText("✓");
            InCart[i].setFont(f2);
            InCart[i].setBackground(Color.GREEN);

            CartHolder[i].setBackground(Color.WHITE);
            ButtonHolder[i].setBackground(Color.WHITE);
            ImageHolder[i].setBackground(Color.WHITE);

            CarName[i].setText(Cart.get(i).getName());
            CarName[i].setFont(f2);
            CarPrice[i].setText("$"+df.format(Cart.get(i).getPrice()));
            CarPrice[i].setFont(f2);

            try{
                Link[i] = new URL(Cart.get(i).getImageURL());
                BufferedImage CImg = ImageIO.read(Link[i]);
                Img[i] = new ImageIcon(CImg.getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, -1, Image.SCALE_SMOOTH));
            }
            catch (IOException E){
                System.out.println("Cannot read URL");
            }

            ImageLabel[i].setIcon(Img[i]);
            ImageLabel[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,17));
            ImageHolder[i].add(ImageLabel[i], BorderLayout.CENTER);
            CartHolder[i].add(ImageHolder[i]);
            CartHolder[i].add(CarName[i]);
            CartHolder[i].add(CarPrice[i]);
            ButtonHolder[i].add(InCart[i], BorderLayout.CENTER);
            //ButtonHolder[i].setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4,80));
            CartHolder[i].add(ButtonHolder[i]);
            PaymentPanel.add(CartHolder[i]);



        }

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentPanel.removeAll();
                CreateInventory(Cars.length, Cars, ArrayConverter(CarsPrice));
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });

        PaymentPanel.add(Ship);
        PaymentPanel.add(CustomerInformation);

        //OptionBox.add(OptionsBar, BorderLayout.SOUTH);
        OptionsBar.add(Back, BorderLayout.WEST);
        OptionsBar.add(Spacer, BorderLayout.CENTER);
        OptionsBar.add(CheckOut, BorderLayout.EAST);
        PaymentPanel.add(OptionsBar);
    }

    //Converts an array of integers into an array of String variables with a US price format.
    public String[] ArrayConverter(BigDecimal[] IArray){
        String[] SArray = new String[IArray.length];
        for(int i = 0; i< IArray.length; i++){
            //String Temp = NumberFormat.getNumberInstance(Locale.US).format(IArray[i]);
            SArray[i] = "$"+df.format(IArray[i]);
        }
        return SArray;
    }


    private void createUIComponents() {
        // HomePage.setLayout(new GridLayout(2,1));
    }
}
