package software.ulpgc.kata6.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata6.viewmodel.Histogram;

import javax.swing.*;

public class JFreeChartHistogramDisplay extends JFrame implements HistogramDisplay {
    private final Histogram<String> histogram;

    public JFreeChartHistogramDisplay(Histogram<String> histogram) {
        this.histogram = histogram;
        setTitle("Histogram Display");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(createPanel());
    }

    private JPanel createPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String key : histogram.keys()){
            dataset.addValue(histogram.get(key), "Frecuencia", key);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Histograma de tipos",
                "Categoria",
                "N. de titulos",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
        return new ChartPanel(chart);
    }

    @Override
    public void display(){
        this.setVisible(true);
    }
}
