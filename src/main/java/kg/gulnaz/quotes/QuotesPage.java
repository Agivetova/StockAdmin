package kg.gulnaz.quotes;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.Trendline;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.options.*;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;

public class QuotesPage extends VerticalLayout implements View {
    public static final String NAME = "";

    public QuotesPage() {

        DataSeries dataSeries = new DataSeries()
                .newSeries()
                .add("23-May-19", 1)
                .add("24-May-19", 4)
                .add("25-May-19", 2)
                .add("26-May-19", 6)
                .add("27-May-19", 1)
                .add("28-May-19", 4)
                .add("29-May-19", 2)
                .add("30-May-19", 6)
                .add("1-June-19", 1)
                .add("2-June-19", 4.5);

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setTrendline(
                        new Trendline()
                        .setShow(true));

        Axes axes = new Axes()
                .addAxis(
                        new XYaxis()
                        .setRenderer(AxisRenderers.DATE)
                        .setTickOptions(
                                new AxisTickRenderer()
                                .setFormatString("%#m/%#d/%y"))
                                .setNumberTicks(10))
                .addAxis(
                        new XYaxis(XYaxes.Y)
                        .setTickOptions(
                                new AxisTickRenderer()
                                .setFormatString("%.3f")));

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setSizeAdjust(20)
                .setTooltipLocation(TooltipLocations.NORTH)
                .setTooltipAxes(TooltipAxes.Y)
                .setTooltipFormatString("<b><i><span style = 'color:red;'>hello</span></i></b> %.2f")
                .setUseAxesFormatters(false);

        Cursor cursor = new Cursor()
                .setShow(true);

        Options options = new Options()
                .addOption(seriesDefaults)
                .addOption(axes)
                .addOption(highlighter)
                .addOption(cursor);

        DCharts chart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options)
                .show();

        addComponent(chart);
        setComponentAlignment(chart, Alignment.TOP_LEFT);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
