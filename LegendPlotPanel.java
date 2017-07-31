
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


public class LegendPlotPanel extends JPanel {
    private String[] list;
    public LegendPlotPanel() {
        list = new String[1];
    }
    public LegendPlotPanel(String[] list) {
        this.list = list;
    }
    public String[] getList() {
        return list;
    }
    public void setList(String[] list) {
        this.list = list;
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        plot(grphcs);
    }
   void plot(Graphics grphcs) {
        int parts = list.length;
        int x = getWidth()/20;
        int y = getHeight()/20;
        grphcs.setFont(new Font("Monospaced", 0, 11));
        for (int i = 0; i < parts; i++) {
            grphcs.setColor(ColorList.getColor(i));
            grphcs.fillRect(x, y + 25*i, 15, 15);
            grphcs.setColor(Color.BLACK);
            grphcs.drawRect(x, y + 25*i, 15, 15);
            grphcs.drawString(list[i], x + 20, y + 12 + 25*i);
        }
   }
}
