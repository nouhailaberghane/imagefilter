import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFilterApp extends JFrame {
    private JLabel imageLabel;
    private BufferedImage originalImage;
    private BufferedImage filteredImage;
    private JFileChooser fileChooser;

    public ImageFilterApp() {
        // Set up the JFrame
        setTitle("Image Filter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create the file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png"));

        // Create the panel for the buttons and sliders
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        JButton openButton = new JButton("Open");
        JButton resetButton = new JButton("Reset");
        JButton grayScaleButton = new JButton("Grayscale");
        JButton invertButton = new JButton("Invert");
        JButton blurButton = new JButton("Blur");
        JSlider brightnessSlider = new JSlider(-255, 255, 0);
        JSlider contrastSlider = new JSlider(-100, 100, 0);

        // Add the buttons and sliders to the panel
        buttonPanel.add(openButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(grayScaleButton);
        buttonPanel.add(invertButton);
        buttonPanel.add(blurButton);
        buttonPanel.add(new JLabel("Brightness"));
        buttonPanel.add(brightnessSlider);
        buttonPanel.add(new JLabel("Contrast"));
        buttonPanel.add(contrastSlider);

        // Create the label for the image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the panels to the JFrame
        add(buttonPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);

        // Add listeners to the buttons and sliders
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(ImageFilterApp.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        originalImage = ImageIO.read(file);
                        filteredImage = originalImage;
                        imageLabel.setIcon(new ImageIcon(originalImage));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the image to the original
                filteredImage = originalImage;
                imageLabel.setIcon(new ImageIcon(originalImage));
                brightnessSlider.setValue(0);
                contrastSlider.setValue(0);
            }
        });

        grayScaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Convert the image to grayscale
                filteredImage = applyGrayScaleFilter(originalImage);
                imageLabel.setIcon(new ImageIcon(filteredImage));
            }
        });

        invertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Invert the colors of the image
                filteredImage = applyInvertFilter(originalImage);
                imageLabel.setIcon(new ImageIcon(filteredImage));
            }
        });

        blurButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Apply a blur filter to the image
                filteredImage = applyBlurFilter(originalImage);
                imageLabel.setIcon(new ImageIcon(filteredImage));
            }
        });