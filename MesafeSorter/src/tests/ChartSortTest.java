package tests;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.byrfb.main.NoktaMesafeSorter;
import com.byrfb.objects.Nokta;

/*
 * 
 * BURADA kendi belilediðim sorterin grafikesl olrak gösterimini kullanýyoruz
 * 
 */

public class ChartSortTest extends ApplicationFrame {

	
	public ChartSortTest(final String title) {

	    super(title);
	    
	    /*
	     * 
	     * bu alttaki grafiðin adý
	     * ve verilecek datasetin düzenlenmesi
	     */
	    final XYSeries series = new XYSeries("Random sayýlarýn uzaklýk göstergesi");
	    List<Nokta> unsortedList = new ArrayList<>();
	    int MAX_LIMIT = 1000;
		for(int i = 0 ; i < MAX_LIMIT ; i++) {
			double x = Math.random() * MAX_LIMIT;
			double y = Math.random() * MAX_LIMIT;
			x = i;
			unsortedList.add(new Nokta(x, y));
			series.add(x, y);
			
		}
		
		/*
		 * 
		 * bizim sortei çaðýrýyoruz ve çalýþtýrýyoruz
		 */
		NoktaMesafeSorter sorter = new NoktaMesafeSorter(unsortedList);
		sorter.sort();
		
//		unsortedList.sort(null);
		
		for (Nokta n : sorter.getSortedList()) {
//			System.out.println(n);
//			series.add(n.getX(), n.getY());
		}
		
		/*
		 * veri setini data collectona çevirme iþlem byapýlýyor
		 * 
		 */
	    final XYSeriesCollection data = new XYSeriesCollection(series);
	    final JFreeChart chart = ChartFactory.createScatterPlot("Distance Sorter",
	    		"X", "Y", data);
	    		//(
//	        "XY Series Demo",
//	        "X", 
//	        "Y", 
//	        data,
//	        PlotOrientation.VERTICAL,
//	        true,
//	        true,
//	        false
//	    );

	    /*
	     * 
	     * chart panle oluþturulup içine dataseti gönderiliyor
	     */
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(500, 500));
	    setContentPane(chartPanel);

	}

	public static void main(String[] args) {
		/*
		 * 
		 * bunun jframe i de buymuþ
		 */
		final ChartSortTest demo = new ChartSortTest("Distance Sorter");
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);

	}

}
