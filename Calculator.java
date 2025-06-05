import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
 
public class Calculator extends JFrame implements ActionListener { 
    private JTextField display; 
    private StringBuilder currentInput; 
 
    public Calculator() { 
        setTitle("Calculator"); 
        setSize(400, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLayout(new BorderLayout()); 
 
        // Create display 
        display = new JTextField(); 
        display.setEditable(false); 
        display.setFont(new Font("Arial", Font.PLAIN, 24)); 
        add(display, BorderLayout.NORTH); 
 
        // Create panel for buttons 
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); 
 
        // Button labels 
        String[] buttons = { 
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", "C", "=", "+" 
        }; 
 
        // Add buttons to the panel 
        for (String label : buttons) { 
            JButton button = new JButton(label); 
            button.addActionListener(this); 
            buttonPanel.add(button); 
        } 
 
        add(buttonPanel, BorderLayout.CENTER); 
 
        currentInput = new StringBuilder(); 
    } 
 
    @Override 
    public void actionPerformed(ActionEvent e) { 
        String command = e.getActionCommand(); 
 
        if (command.equals("C")) { 
            currentInput.setLength(0); 
            display.setText(""); 
        } else if (command.equals("=")) { 
            try { 
                double result = evaluateExpression(currentInput.toString()); 
                display.setText(String.valueOf(result)); 
                currentInput.setLength(0); 
                currentInput.append(result); 
            } catch (Exception ex) { 
                display.setText("Error"); 
                currentInput.setLength(0); 
            } 
        } else { 
            currentInput.append(command); 
            display.setText(currentInput.toString()); 
        } 
    } 
 
    private double evaluateExpression(String expression) { 
        // Simple evaluation of expression (note: this is a very basic implementation) 
        // You can replace this with a more robust expression parser if needed 
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])"); 
        double result = Double.parseDouble(tokens[0]); 
        for (int i = 1; i < tokens.length; i += 2) { 
            String operator = tokens[i]; 
            double nextValue = Double.parseDouble(tokens[i + 1]); 
            switch (operator) { 
                case "+": 
                    result += nextValue; 
                    break; 
                case "-": 
                    result -= nextValue; 
                    break; 
                case "*": 
                    result *= nextValue; 
                    break; 
                case "/": 
                    result /= nextValue; 
                    break; 
            } 
        } 
        return result; 
    } 
 
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> { 
            Calculator calculator = new Calculator(); 
            calculator.setVisible(true); 
        }); 
    } 
} 