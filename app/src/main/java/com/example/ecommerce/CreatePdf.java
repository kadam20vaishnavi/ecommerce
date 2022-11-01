package com.example.ecommerce;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;
//
//import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.property.HorizontalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CreatePdf {

    Context context;
    List<Orderlistresponce> finalorder=new ArrayList<>();
    public void createPdf(Context context,List<Orderlistresponce> policyResponceList) {

//        this.context=context;
//        this.finalorder=policyResponceList;
//
//        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//        File file = new File(pdfpath, finalorder.get(0).getFinalOrderPk()+"OrderReport.pdf");
//
//        String filename = finalorder.get(0).getFinalOrderPk() + "PolicyReport.pdf";
//
//        PdfWriter writer = null;
//        try {
//            writer = new PdfWriter(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//
//        Paragraph paragraph1 = new Paragraph("ALL Policy Report");
//        Paragraph paragraph2 = new Paragraph("Total Policy:" + policyResponceList.size());
//        paragraph1.setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//        float columnWidth[] = {80, 80, 80, 80, 80, 80, 80, 80, 80};
//        Table table1 = new Table(columnWidth);
//
//        table1.addCell(new Cell().add(new Paragraph("Order Status").setFontSize(14).setFontColor(ColorConstants.BLACK)));
//        table1.addCell(new Cell().add(new Paragraph("Grand Amount").setFontSize(14).setFontColor(ColorConstants.BLACK)));
//        table1.addCell(new Cell().add(new Paragraph("Date").setFontSize(14).setFontColor(ColorConstants.BLACK)));
//        table1.addCell(new Cell().add(new Paragraph("Number").setFontSize(14).setFontColor(ColorConstants.BLACK)));
//        table1.addCell(new Cell().add(new Paragraph("customername").setFontSize(14).setFontColor(ColorConstants.BLACK)));
//        table1.addCell(new Cell().add(new Paragraph("orderTime").setFontSize(14).setFontColor(ColorConstants.BLACK)));
//
//        for (int i = 0; i < finalorder.size(); i++) {
//
//            try {
//
//                table1.addCell(new Cell().add(new Paragraph(finalorder.get(i).getOrderStatus())));
//                table1.addCell(new Cell().add(new Paragraph(finalorder.get(i).getGrandAmount())));
//                table1.addCell(new Cell().add(new Paragraph(finalorder.get(i).getOrderDate())));
//                table1.addCell(new Cell().add(new Paragraph(finalorder.get(i).getOrderNumber())));
//                table1.addCell(new Cell().add(new Paragraph(finalorder.get(i).getCustomerName())));
//                table1.addCell(new Cell().add(new Paragraph(finalorder.get(i).getOrderTime())));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(context, "Somthing is wrong", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        document.add(paragraph1);
//        document.add(paragraph2);
//        document.add(table1);
//        document.close();
//
//        viewPdf(filename);
//
//        Log.e("Pdf create", "create");
//        Toast.makeText(context, "PDF Created Successfully", Toast.LENGTH_LONG).show();
//    }
//
//    public File getFile(Context context, String fileName) {
//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            return null;
//        }
//
//        File storageDir = context.getExternalFilesDir(null);
//        return new File(storageDir, fileName);
//    }
//
//    public Uri getFileUri(Context context, String fileName) {
//        File file = getFile(context, fileName);
//        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
//    }
//
//    private void viewPdf(String file) {
//
//        String pdfpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//        File file1 = new File(pdfpath, file);
//
//        Uri path = FileProvider.getUriForFile(context,context.getPackageName() + ".provider",file1);
//
//        // Setting the intent for pdf reader
//        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        pdfIntent.setDataAndType(path, "application/pdf");
//
//        try {
//            context.startActivity(pdfIntent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(context, "Can't read pdf file", Toast.LENGTH_SHORT).show();
//        }
//    }
    }
}
