package org.UCars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpScreen extends JFrame{
    private JPanel SignUpPanel;
    private JTextField UsernameSignUp;
    private JPasswordField PasswordSignUp;
    private JButton createYourAccountButton;
    private JButton LoginSwitchButton;
    private JTextField textField1;
    private JLabel UCarsLogoPicture;
    private JPanel SignUpScreen;

    public SignUpScreen(){

        //Sets up the page, makes it visible, and creates the ability to close the program
        setContentPane(SignUpScreen);
        setTitle("Ucars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adds the logo onto the screen.
        ImageIcon LogoBlue = new ImageIcon("untitled/src/main/java/org/UCars/UCarsLogoBlue.png");
        UCarsLogoPicture.setIcon(LogoBlue);

        //On button press, create account and go to the home page
        createYourAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen HomePage = new HomeScreen();
                setVisible(false);
                HomePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        //On button press, gets rid of the current Sign-up screen and provides the Login Screen .
        LoginSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen LoginPage = new LoginScreen();
                setVisible(false);
                LoginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });


    }
}
