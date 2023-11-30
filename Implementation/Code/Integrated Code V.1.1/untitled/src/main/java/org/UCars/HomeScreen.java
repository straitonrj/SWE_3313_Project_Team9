package org.UCars;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;

public class HomeScreen extends JFrame{

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
    private JLabel CartUserLabel;
    private JLabel InfoUserLabel;
    private JPanel InventoryHolder = new JPanel();
    private JPanel InfoPanel = new JPanel();
    private JScrollPane ScrollHome = new JScrollPane(InventoryHolder,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JScrollPane ScrollInfo = new JScrollPane(InfoPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel CartPanel = new JPanel();
    private JScrollPane ScrollCart = new JScrollPane(CartPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private Font f1 = new Font(Font.SANS_SERIF,Font.PLAIN,24);
    private Font f2 = new Font(Font.SANS_SERIF,Font.PLAIN,50);


    //For testing purpose.
    String[] Cars = new String[Main.CheckAvailableInventory().size()];
    BigDecimal[] CarsPrice = new BigDecimal[Main.CheckAvailableInventory().size()];
    String[] ImagePaths = new String[Main.CheckAvailableInventory().size()];


    ImageIcon[] CarIcons = new ImageIcon[Cars.length];
    public HomeScreen(){
        ArrayList<Inventory> Temp = Main.CheckAvailableInventory();
        int Temp2 = Temp.size();
        System.out.println(Temp2);
         for(int i = 0; i < 2; i++ ){
            Cars[i] = Main.CheckAvailableInventory().get(i).getName();
            //ImagePaths[i] = Main.CheckAvailableInventory().get(i).getImageURL();
            CarsPrice[i] = Main.CheckAvailableInventory().get(i).getPrice();
            //System.out.println(Main.CheckAvailableInventory().get(i).getName());
        }







        setContentPane(HomePageBackground);
        setTitle("UCars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);

        //Setting up the logos for the buttons ont he main toolbar
        ImageIcon HomeLogo = new ImageIcon("untitled/src/main/java/org/UCars/UCarsLogoWhite.png");
        HomeButton.setIcon(HomeLogo);
        HomeButton2.setIcon(HomeLogo);
        CartHomeButton.setIcon(HomeLogo);
        ImageIcon CartLogo = new ImageIcon("untitled/src/main/java/org/UCars/CartLogo.png");
        CartButton.setIcon(CartLogo);
        CartButton2.setIcon(CartLogo);
        CartCart.setIcon(CartLogo);
        ImageIcon ProfileLogo = new ImageIcon("untitled/src/main/java/org/UCars/ProfileLogo.png");
        ProfileButton.setIcon(ProfileLogo);
        ProfileButton2.setIcon(ProfileLogo);
        CartProfile.setIcon(ProfileLogo);

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
                ((CardLayout)HomePageBackground.getLayout()).last(HomePageBackground);
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
                InfoPanel.removeAll();
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });
        CartButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPanel.removeAll();
                ((CardLayout)HomePageBackground.getLayout()).last(HomePageBackground);
            }
        });

        //Setting up the cart page
        CartPage.add(ScrollCart, BorderLayout.CENTER);
        ScrollCart.getVerticalScrollBar().setUnitIncrement(16);
        CartPanel.setLayout(new GridLayout(2,1,0,0));
        CartHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartPanel.removeAll();
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
        Bar.add(Button[CellNumber],position);
        if(ButtonType == 1){
            Button[CellNumber].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        } else if (ButtonType == 2) {
            Button[CellNumber].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InfoImageCreation(CarIcons[CellNumber]);


                    CarInfoCardCreation(Cars[CellNumber],ArrayConverter(CarsPrice)[CellNumber]);

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
                ((CardLayout)HomePageBackground.getLayout()).first(HomePageBackground);
            }
        });
        Panel.add(Back);
    }
    //
    public void CarInfoCardCreation(String CarName, String CarPrice){
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
        Description.setText("Quality in Product means the ability of the vehicle to fulfill its expected functions and behavior, such as engine efficiency, product features and environmental exhaust standards.");
        Description.setLineWrap(true);
        Card.add(Description);
        InfoAddButtonCreation(Card);
        InfoPanel.add(Card, Component.LEFT_ALIGNMENT);

    }
    public void InfoAddButtonCreation(JPanel Panel){
        JButton Add = new JButton();
        Add.setText("Add to Cart");
        Add.setFont(f1);
        Add.setBackground(Color.GREEN);
        Panel.add(Add);
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

    //Converts an array of integers into an array of String variables with a US price format.
    public String[] ArrayConverter(BigDecimal[] IArray){
        String[] SArray = new String[IArray.length];
        for(int i = 0; i< IArray.length; i++){
            //String Temp = NumberFormat.getNumberInstance(Locale.US).format(IArray[i]);
            SArray[i] = "$"+IArray[i];
        }
        return SArray;
    }
    private void createUIComponents() {
        // HomePage.setLayout(new GridLayout(2,1));
    }
}
