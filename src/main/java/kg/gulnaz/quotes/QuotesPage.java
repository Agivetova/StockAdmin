package kg.gulnaz.quotes;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;

public class QuotesPage extends VerticalLayout implements View {
    public static final String NAME = "";

    public QuotesPage() {

        DCharts chart = new DCharts();

        chart.setDataSeries(
                new DataSeries()
                        .add(3, 7, 9, 1, 4, 6, 8, 2, 5)).show();

        Label title = new Label("Menu");

        addComponent(chart);
        setComponentAlignment(chart, Alignment.TOP_LEFT);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
