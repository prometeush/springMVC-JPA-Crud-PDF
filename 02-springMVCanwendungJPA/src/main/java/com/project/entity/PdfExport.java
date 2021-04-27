package com.project.entity;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfExport {
	
	private List<Student> studentList;

	public PdfExport(List<Student> studentList) {
		super();
		this.studentList = studentList;
	}
	
	public void writeTableHeader(PdfPTable table1) {
		PdfPCell cell=new PdfPCell();
		
		cell.setBackgroundColor(Color.CYAN);
		cell.setPadding(4);
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		
		Font font=new Font();
		font.setColor(Color.blue);
		font.setFamily(FontFactory.HELVETICA);
		font.setSize(15);
		
		cell.setPhrase(new Phrase("Id", font));
		table1.addCell(cell);
		
		cell.setPhrase(new Phrase("Vorname", font));
		table1.addCell(cell);
		
		cell.setPhrase(new Phrase("Nachname", font));
		table1.addCell(cell);
		
		cell.setPhrase(new Phrase("Email", font));
		table1.addCell(cell);
		
		cell.setPhrase(new Phrase("Prog.Sprache", font));
		table1.addCell(cell);
		
		cell.setPhrase(new Phrase("GeburtsDatum", font));
		table1.addCell(cell);
	}
	
	public  void writeTableDate(PdfPTable table2) {
		
		for (Student student : studentList) {
			table2.addCell(String.valueOf(student.getId()));
			table2.addCell(student.getVorname());
			table2.addCell(student.getNachname());
			table2.addCell(student.getEmail());
			table2.addCell(student.getProgSprache());
			table2.addCell(student.getGeburtsDatum());
			
		}
		
	}
	
	public void exportData(HttpServletResponse response) throws DocumentException, IOException {
		Document document=new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font font=new Font();
		font.setColor(Color.blue);
		font.setFamily(FontFactory.HELVETICA);
		font.setSize(15);
		
		Paragraph title=new Paragraph("Student List", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		title.setSpacingAfter(50);
		
		PdfPTable table=new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setWidths(new int[] {2,2,2,2,2,2});
		
		writeTableHeader(table);
		writeTableDate(table);
		
		document.add(table);
		document.close();
		
		
	}

}
