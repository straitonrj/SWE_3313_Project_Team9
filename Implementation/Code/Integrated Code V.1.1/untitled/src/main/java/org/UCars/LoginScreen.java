package org.UCars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JPanel LoginPanel;
    private JTextField UsernameLogin;
    private JPasswordField PasswordLogin;
    private JButton loginButton;
    private JButton SignupSwitchButton;
    private JLabel UCarsLogoPicture;
    private JPanel LoginScreen;

    public LoginScreen() {

        setContentPane(LoginScreen);
        setTitle("Ucars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adds the logo onto the screen.

        ImageIcon LogoBlue = new ImageIcon("untitled/src/main/java/org/UCars/UCarsLogoBlue.png");
        UCarsLogoPicture.setIcon(LogoBlue);

        //On button press, login to the website.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen HomePage = new HomeScreen();
                setVisible(false);
                HomePage.setExtendedState(JFrame.MAXIMIZED_BOTH);


            }
        });

        //On button press, gets rid of the current login screen and provides the Sign-up Screen .
        SignupSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpScreen SignUpPage = new SignUpScreen();
                setVisible(false);
                 SignUpPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
