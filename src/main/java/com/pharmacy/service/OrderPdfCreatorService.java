package com.pharmacy.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderPdfCreatorService {
    public static void main(String[] args) {
        try {
            createOrderPdf("Ashay",String.valueOf(System.currentTimeMillis()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void createOrderPdf(String cashier, String orderNumber) throws IOException {
        PDDocument document = new PDDocument();
        PDDocumentInformation pdDocumentInformation = document.getDocumentInformation();
        pdDocumentInformation.setAuthor(cashier);
        pdDocumentInformation.setCreator("Rudhvi Pharmacy");
        pdDocumentInformation.setSubject("Order "+orderNumber);
        PDPage first = new PDPage();
        document.addPage(first);

        PDPageContentStream pdPageContentStream = new PDPageContentStream(document,first);
        pdPageContentStream.addRect(50.0f,50.0f,10.0f,10.0f);
        pdPageContentStream.close();
        document.save("C:\\delete\\"+orderNumber+".pdf");
        document.close();
    }
}
