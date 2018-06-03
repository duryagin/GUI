
import javax.swing.*;
import java.awt.*;
import minesweeper.Cell;
import minesweeper.Coord;
import minesweeper.Ranges;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import minesweeper.Game;


public class Minesweeper extends JFrame {

    private JPanel panel;
    private Game game;
    private int lengthWidthOfPic = 50;
    private int columns = 10;
    private int rows = 10;
    private int mines = 9;
    private JLabel label;

    public static void main(String[] args) {
        new Minesweeper();
    }

    private Minesweeper() {
        game = new Game(columns, rows, mines);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() { // вывод сообщения
        label = new JLabel("Let's go!");
        add (label, BorderLayout.SOUTH);
    }

    private  void initPanel() {
        panel = new JPanel() {
        	
        	@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // цикл для прорисовки всех объектов
                for (Coord coord : Ranges.getAllCoords())  {
                    g.drawImage((Image) game.getCell(coord).image, // рисуем объект
                            coord.x * lengthWidthOfPic,
                            coord.y * lengthWidthOfPic,
                            this);
                }
            }
        };

        // настраиваем управление мышкой
        panel.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mousePressed(MouseEvent e) {
        		int x = e.getX() / lengthWidthOfPic;
        		int y = e.getY() / lengthWidthOfPic;
        		Coord coords = new Coord(x, y);
        		
        		if (e.getButton() == MouseEvent.BUTTON1) // ЛКМ
        			game.pressLeftButton(coords); // открыть ячейку
        		if (e.getButton() == MouseEvent.BUTTON2) // колёсико
        			game.start(); // новая игра
        		if (e.getButton() == MouseEvent.BUTTON3) // ПКМ
        			game.pressRightButton(coords); // пометить флажком
        		
        		label.setText(getMessage()); // вывод сообщения
        		panel.repaint();
        		}
        	});
        
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * lengthWidthOfPic,
        		Ranges.getSize().y * lengthWidthOfPic));
        add(panel);
    }

    private String getMessage() {
    	
    	switch (game.getStatus()) {
    	
    	default:
    		return "";
    	case PLAY:
    		return "Next..";
    	case LOST:
    		return "BOOM!";
    	case WON:
    		return "Great! You won!";
        }
    }

    private void initFrame() {
        pack(); // подгоняет размер формы до достаточного для отображения всех ячеек
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // закрытие программы
        setTitle("Minesweeper");
        setIconImage(getImage("minesweeper"));
        setLocationRelativeTo(null); // размещение по центру
        setResizable(false);
        setVisible(true); // видимость формы
    }

    private void setImages() {
        for (Cell cell : Cell.values()) // цикл для установки всех объектов
            cell.image = getImage(cell.name().toLowerCase());
    }

    private Image getImage (String name) {
        String filename = "png/" + name.toLowerCase() + ".png"; // имя файла для каждого объекта
        // обращаемся к папке res для доступа к объекту
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
