import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

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
    private JPanel InventoryHolder = new JPanel();
    private JPanel InfoPanel = new JPanel();
    private JScrollPane ScrollHome = new JScrollPane(InventoryHolder,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JScrollPane ScrollInfo = new JScrollPane(InfoPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private Font f1 = new Font(Font.SANS_SERIF,Font.PLAIN,24);
    private Font f2 = new Font(Font.SANS_SERIF,Font.PLAIN,50);

    private GridBagConstraints Constraint = new GridBagConstraints();


    //For testing purpose.
    String[] Cars1 = {"Mazda","Acura","Ford","Acura","Ford","Acura","Ford","Mazda","Acura","Ford","Acura","Ford","Acura"};
    int[] CarsPrice = {22190,31300,31520,31300,31520,31300,31520,22190,31300,31520,31300,31520,31300};
    ImageIcon[] CarIcons = new ImageIcon[Cars1.length];
    public HomeScreen(){
        setContentPane(HomePageBackground);
        setTitle("Ucars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon HomeLogo = new ImageIcon("UCarsLogoWhite.png");
        HomeButton.setIcon(HomeLogo);
        HomeButton2.setIcon(HomeLogo);
        ImageIcon CartLogo = new ImageIcon("CartLogo.png");
        CartButton.setIcon(CartLogo);
        CartButton2.setIcon(CartLogo);
        ImageIcon ProfileLogo = new ImageIcon("ProfileLogo.png");
        ProfileButton.setIcon(ProfileLogo);
        ProfileButton2.setIcon(ProfileLogo);




        for (int i = 0; i < CarIcons.length; i++){
            CarIcons[i] = new ImageIcon("TestCarImage.png");
        }


        HomePage.add(ScrollHome, BorderLayout.CENTER);
        ScrollHome.getVerticalScrollBar().setUnitIncrement(16);
        InventoryHolder.setLayout(new BoxLayout(InventoryHolder,BoxLayout.Y_AXIS));
        CreateInventory(Cars1.length, Cars1, ArrayConverter(CarsPrice));

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
                    LabelCreation(CarName, CellNumber, CName[CellNumber], InventoryCells[CellNumber]);
                    LabelCreation(CarPrice,CellNumber, CPrice[CellNumber], InventoryCells[CellNumber]);
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
            Cell[CellNumber].setLayout(new BoxLayout(Cell[CellNumber],BoxLayout.PAGE_AXIS));
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
        //Creates and adds labels to go into cells
        public void LabelCreation(JLabel[] Label, int CellNumber, String text, JPanel Cell ){
            Label[CellNumber] = new JLabel();
            Label[CellNumber].setText(text);
            Label[CellNumber].setFont(f1);
            Cell.add(Label[CellNumber]);
        }
        //Creates and adds icons to the cells
        public void ImageCreation(JLabel[] CImage, Icon image, int CellNumber, JPanel Cell){
            CImage[CellNumber] = new JLabel(CarIcons[CellNumber]);
            Cell.add(CImage[CellNumber], Component.CENTER_ALIGNMENT);
        }
        //Creates and adds toolbars to the cells
        public void ToolBarCreation(JToolBar[] Bar, int CellNumber, JPanel Cell){
            Bar[CellNumber] = new JToolBar();
            Bar[CellNumber].setFloatable(false);
            Bar[CellNumber].setLayout(new BorderLayout());
            Bar[CellNumber].setPreferredSize(new Dimension(20, 50));
            Cell.add(Bar[CellNumber]);
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


                        CarInfoCardCreation(Cars1[CellNumber],ArrayConverter(CarsPrice)[CellNumber]);

                        ((CardLayout)HomePageBackground.getLayout()).next(HomePageBackground);

                    }
                });
            }

        }
        //Makes the car image on the info pages
        public void InfoImageCreation( ImageIcon CImage){
            JPanel Holder = new JPanel();
            Holder.setLayout(new BoxLayout(Holder, BoxLayout.Y_AXIS));
            BufferedImage Resize = new BufferedImage((int)(CImage.getIconWidth()*1.3),(int)(CImage.getIconHeight()*1.3), BufferedImage.TRANSLUCENT);
            Graphics2D Draw = (Graphics2D) Resize.createGraphics();
            Draw.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            Draw.drawImage(CImage.getImage(),0,0,(int)(CImage.getIconWidth()*1.3),(int)(CImage.getIconHeight()*1.3),null);
            ImageIcon New = new ImageIcon(Resize);
            JLabel ImageHolder = new JLabel();

            ImageHolder.setIcon(New);
            //ImageHolder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


            Holder.add(ImageHolder);
            JPanel Spacer = new JPanel();
            Spacer.setBackground(Color.white);
            Holder.add(Spacer);
            InfoBackButtonCreation(Holder);
            Holder.setBackground(Color.white);
           // Holder.setBorder(BorderFactory.createLineBorder(Color.black));
            InfoPanel.add(Holder);
        }
        public void InfoBackButtonCreation(JPanel Panel){
            JButton Back = new JButton();
            Back.setText("Back");
            Back.setFont(f1);
            Back.setBackground(Color.LIGHT_GRAY);
            Panel.add(Back);
        }

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
            Description.setText("oiuavgefiubwqepf iuvqpwieufvpqeuof bp;qelkwfnpow qeuif bvg[qwoiepnf';qoweifhbpiuqw efnweqf[ibwqe[ofi hbqwefknqw'leibf[d ouibwe");
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
        //Converts an array of integers into an array of String variables with a US price format.
        public String[] ArrayConverter(int[] IArray){
        DecimalFormat df = new DecimalFormat("#,###");
        String[] SArray = new String[IArray.length];
        for(int i = 0; i< IArray.length; i++){
            String Temp = NumberFormat.getNumberInstance(Locale.US).format(IArray[i]);
            SArray[i] = "$"+Temp;
        }
        return SArray;
    }


    private void createUIComponents() {
       // HomePage.setLayout(new GridLayout(2,1));
    }
}
