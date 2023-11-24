import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JPanel LoginScreen;
    private JTextField UsernameLogin;
    private JPasswordField PasswordLogin;
    private JButton loginButton;
    private JPanel LoginPanel;
    private JButton SignupSwitchButton;
    private JPanel SignUpScreen;

    public LoginScreen() {

        setContentPane(LoginScreen);
        setTitle("Ucars Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);

        //On button press, login to the website.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Complete");
            }
        });

        //On button press, Switch to the account creation screen.
        SignupSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpScreen page = new SignUpScreen();
                 setVisible(false);
                 page.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
