package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.WeeklyFuelPriceDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.CompanyRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.image.ImageDataFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Service
public class ReportService {
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    @Autowired
    public ReportService(CompanyRepository companyRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    private byte[] generateChart(String title, List<WeeklyFuelPriceDTO> prices) throws Exception {
        // Create dataset
        XYSeries series = new XYSeries("Price Trend");
        prices.forEach(price -> {
            // Use month number (1-12) with day as decimal for x-axis positioning
            double monthValue = price.getWeekStartDate().getMonthValue() + 
                              (price.getWeekStartDate().getDayOfMonth() / 31.0);
            series.add(monthValue, price.getAvgPrice());
        });
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Month",
                "Price ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Customize chart appearance
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(41, 128, 185)); // Blue line
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);

        // Customize x-axis to show month names
        org.jfree.chart.axis.NumberAxis xAxis = (org.jfree.chart.axis.NumberAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
        xAxis.setNumberFormatOverride(new java.text.NumberFormat() {
            private final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                                          "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            @Override
            public StringBuffer format(double number, StringBuffer toAppendTo, java.text.FieldPosition pos) {
                int monthIndex = (int)number - 1;
                if (monthIndex >= 0 && monthIndex < MONTHS.length) {
                    return toAppendTo.append(MONTHS[monthIndex]);
                }
                return toAppendTo.append("");
            }

            @Override
            public StringBuffer format(long number, StringBuffer toAppendTo, java.text.FieldPosition pos) {
                return format((double) number, toAppendTo, pos);
            }

            @Override
            public Number parse(String source, java.text.ParsePosition parsePosition) {
                return null;
            }
        });

        // Set x-axis range to show only months that have data
        double minMonth = prices.stream()
                .mapToDouble(p -> p.getWeekStartDate().getMonthValue())
                .min()
                .orElse(1);
        double maxMonth = prices.stream()
                .mapToDouble(p -> p.getWeekStartDate().getMonthValue())
                .max()
                .orElse(12);
        xAxis.setRange(minMonth - 0.5, maxMonth + 0.5);

        // Convert chart to image
        BufferedImage chartImage = chart.createBufferedImage(600, 400);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(chartImage, "PNG", baos);
        return baos.toByteArray();
    }

    public byte[] generateWeeklyFuelPriceReport(Long companyId) throws Exception {
        // Verify company exists
        companyService.getCompanyById(companyId);
        
        // Get current year
        String currentYear = String.valueOf(LocalDate.now().getYear());
        
        // Fetch data
        List<WeeklyFuelPriceDTO> prices = companyRepository.getGasStationYearStatistic(companyId, currentYear);
        
        if (prices.isEmpty()) {
            throw new RuntimeException("No data found for company ID: " + companyId);
        }

        // Group by fuel type
        Map<String, List<WeeklyFuelPriceDTO>> pricesByFuel = prices.stream()
                .collect(Collectors.groupingBy(WeeklyFuelPriceDTO::getFuelName));

        // Generate PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Add title
        String companyName = prices.get(0).getCompanyName();
        document.add(new Paragraph("Weekly Fuel Price Report")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold());
        document.add(new Paragraph(companyName)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16));
        document.add(new Paragraph("Year: " + currentYear)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(14));

        // Add tables and charts for each fuel type
        pricesByFuel.forEach((fuelName, fuelPrices) -> {
            try {
                document.add(new Paragraph("\n"));
                document.add(new Paragraph(fuelName)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setFontSize(14)
                        .setBold());

                // Create table
                Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
                
                // Add headers
                table.addHeaderCell(new Cell().add(new Paragraph("Week").setBold()));
                table.addHeaderCell(new Cell().add(new Paragraph("Average Price").setBold()));

                // Add data rows
                fuelPrices.forEach(price -> {
                    table.addCell(new Cell().add(new Paragraph(String.format("Week %s (%s - %s)", 
                        price.getWeek(),
                        price.getWeekStartDate().format(java.time.format.DateTimeFormatter.ofPattern("MMM d")),
                        price.getWeekEndDate().format(java.time.format.DateTimeFormatter.ofPattern("MMM d"))))));
                    table.addCell(new Cell().add(new Paragraph(String.format("$%.2f", price.getAvgPrice()))));
                });

                document.add(table);

                // Add price trend chart
                document.add(new Paragraph("\nPrice Trend")
                        .setTextAlignment(TextAlignment.LEFT)
                        .setFontSize(12)
                        .setBold());
                
                byte[] chartImageData = generateChart(fuelName + " Price Trend", fuelPrices);
                Image chartImage = new Image(ImageDataFactory.create(chartImageData));
                chartImage.setWidth(UnitValue.createPercentValue(100));
                document.add(chartImage);

            } catch (Exception e) {
                throw new RuntimeException("Error generating PDF", e);
            }
        });

        document.close();
        return baos.toByteArray();
    }
} 