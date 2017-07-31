
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CirclePlotPanel extends JPanel {
    private float[] segments;
    public CirclePlotPanel() {
        segments = new float[1];
    }
    public CirclePlotPanel(float[] segments) {
        this.segments = segments;
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); 
        int parts = segments.length;
        float summary = 0f;
        for (int i = 0; i < parts; i++) summary += segments[i];
        int xBeg = getWidth() / 10;
        int yBeg = getHeight() / 10;
        int width = getWidth() - 2*xBeg;
        int height = getHeight() - 2*yBeg;
        int[] sumAngles = new int[parts];
        int[] angles = new int[parts];
        int sum = 0;
        for (int i = 0; i < parts; i++) {
            angles[i] = getAngle(summary, segments[i]);
            sumAngles[i] = sum;
            sum += angles[i];
        }
        if (sum < 360) {
            for (int i = parts - 1; i > 0; i--) {
                if (angles[i] != 0) {
                    angles[i] += 360 - sum;
                    break;
                }
            }
        }
        if (sum > 360) {
            for (int i = parts - 1; i > 0; i--) {
                if (angles[i] != 0) {
                    angles[i] += 360 - sum;
                    break;
                }
            }            
        }
        grphcs.setFont(new Font("Monospaced", 0, 11));
        grphcs.drawString("Суммарный расход за указанный период: " + Float.toString(summary), 10, 15);
        for (int i = 0; i < parts; i++) {
            grphcs.setColor(ColorList.getColor(i));
            grphcs.fillArc(xBeg, yBeg, width, height, sumAngles[i], angles[i]);
        }
        grphcs.setColor(Color.black);
        grphcs.drawOval(xBeg, yBeg, width, height);
    }
    private int getAngle(float sum, float seg) {
        if ((seg > 0) && (Math.round(seg / sum * 360) == 0)) return 1;
        return Math.round(seg / sum * 360);
    }
    public float[] getSegments() {
        return segments;
    }
    public void setSegments(float[] segments) {
        this.segments = segments;
    }
    
}

class ColorList {
    private static Color[] colors = {
        new Color(0, 0, 128),
        new Color(0, 255, 0),
        new Color(255, 0, 0),
        new Color(0, 255, 255),
        new Color(255, 128, 0),
        new Color(128, 0, 255),
        new Color(255, 255, 0),
        new Color(255, 0, 255),
        new Color(0, 128, 0),
        new Color(255, 128, 128),
        new Color(0, 0, 255),
        new Color(255, 0, 128),
        new Color(255, 255, 255),
    };
    public static Color getColor(int i) {
        return colors[i%colors.length];
    }
}