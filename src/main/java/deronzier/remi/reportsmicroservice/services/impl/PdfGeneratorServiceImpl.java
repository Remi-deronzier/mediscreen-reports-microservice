package deronzier.remi.reportsmicroservice.services.impl;

import java.net.MalformedURLException;

import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import deronzier.remi.reportsmicroservice.models.Patient;
import deronzier.remi.reportsmicroservice.models.RiskLevel;
import deronzier.remi.reportsmicroservice.services.PdfGeneratorService;

/**
 * This class is used to generate a PDF file for the patient report.
 * 
 * @author Rémi Deronzier
 */
@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    /**
     * @param patient
     * @param riskLevel
     * @return byte[]
     * @throws MalformedURLException
     */
    public byte[] generatePDF(Patient patient, RiskLevel riskLevel) throws MalformedURLException {
        // Create a document
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDocument);

        // Add header
        Table header = buildHeader();
        document.add(header);

        document.add(addSmallSpacing());

        // Add title
        Paragraph title = buildDocumentTitle();
        document.add(title);

        document.add(addSmallSpacing());

        // Add patient information section
        Paragraph patientInformationSection = buildSectionTitle("Informations patient");
        document.add(patientInformationSection);

        Paragraph patientInformationParagraph = buildParagraph("Tableau des informations du patient");
        document.add(patientInformationParagraph);

        Table patientInformationTable = buildTable(patient);
        document.add(patientInformationTable);

        document.add(addBigSpacing());

        // Add patient risk section
        Paragraph patientRiskSection = buildSectionTitle("Risque patient");
        document.add(patientRiskSection);

        Paragraph patientRiskParagraph = buildParagraph(riskLevel.getLabel());
        document.add(patientRiskParagraph);

        // Close the document
        document.close();
        return outputStream.toByteArray();
    }

    /**
     * @return Image
     * @throws MalformedURLException
     */
    private Image buildLogo() throws MalformedURLException {
        String imFile = this.getClass().getClassLoader().getResource("images/logo.png").toString();
        ImageData data = ImageDataFactory.create(imFile);
        Image image = new Image(data);
        float imageWidth = 30;
        float imageHeight = imageWidth;
        image.setWidth(imageWidth);
        image.setHeight(imageHeight);
        return image;
    }

    /**
     * @param patient
     * @return Table
     */
    private Table buildTable(Patient patient) {
        float[] pointColumnWidths = { 150F, 150F, 150F, 150F, 150F, 150F, 150F };
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add(new Paragraph("ID").setBold()));
        table.addCell(new Cell().add(new Paragraph("Prénom").setBold()));
        table.addCell(new Cell().add(new Paragraph("Nom").setBold()));
        table.addCell(new Cell().add(new Paragraph("Date de naissance").setBold()));
        table.addCell(new Cell().add(new Paragraph("Sexe").setBold()));
        table.addCell(new Cell().add(new Paragraph("Adresse").setBold()));
        table.addCell(new Cell().add(new Paragraph("Numéro de téléphone").setBold()));

        table.addCell(new Cell().add(new Paragraph(Long.toString(patient.getId()))));
        table.addCell(new Cell().add(new Paragraph(patient.getFirstName())));
        table.addCell(new Cell().add(new Paragraph(patient.getLastName())));
        table.addCell(new Cell().add(new Paragraph(patient.getDateOfBirth().toString())));
        table.addCell(new Cell().add(new Paragraph(patient.getSex().toString())));
        table.addCell(new Cell().add(new Paragraph(patient.getAddress())));
        table.addCell(new Cell().add(new Paragraph(patient.getPhoneNumber())));
        return table;
    }

    /**
     * @param content
     * @return Paragraph
     */
    private Paragraph buildParagraph(String content) {
        return new Paragraph(content);
    }

    /**
     * @param title
     * @return Paragraph
     */
    private Paragraph buildSectionTitle(String title) {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setFontSize(16);
        paragraph.setBold();
        return paragraph;
    }

    /**
     * @return Paragraph
     */
    private Paragraph buildDocumentTitle() {
        Paragraph paragraph = new Paragraph("Rapport patient");
        paragraph.setFontSize(20);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setBold();
        return paragraph;
    }

    /**
     * @return Paragraph
     */
    private Paragraph addBigSpacing() {
        Paragraph paragraph = new Paragraph("\n\n\n");
        return paragraph;
    }

    /**
     * @return Paragraph
     */
    private Paragraph addSmallSpacing() {
        Paragraph paragraph = new Paragraph("\n");
        return paragraph;
    }

    /**
     * @return Table
     * @throws MalformedURLException
     */
    private Table buildHeader() throws MalformedURLException {
        // Create a table with two columns
        Table headerTable = new Table(2);
        headerTable.setWidth(UnitValue.createPercentValue(100));

        // Add the logo to the first cell
        Cell logoCell = new Cell();
        logoCell.setBorder(Border.NO_BORDER);
        logoCell.setHorizontalAlignment(HorizontalAlignment.LEFT);
        Image logo = buildLogo();
        logoCell.add(logo);
        headerTable.addCell(logoCell);

        // Add the title of the company to the second cell
        Cell titleCell = new Cell();
        titleCell.setBorder(Border.NO_BORDER);
        titleCell.setTextAlignment(TextAlignment.RIGHT);
        Paragraph title = new Paragraph("Mediscreen");
        titleCell.add(title);
        headerTable.addCell(titleCell);

        return headerTable;
    }
}
