import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.apache.commons.io.monitor.FileEntry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class EditExcel {
	private static String path;
	Workbook w = null;
	public EditExcel() {
	}
	
	public void setPath(String path) {
		this.path = path;
		deliminateFile();
	}
	
	public void deliminateFile() {
		try
	    {
	      BufferedWriter bw = null;
	      
	      File f = new File(path);
	      FileEntry fe = new FileEntry(f);
	      System.out.println(fe.refresh(f));
	      //Excel document to be imported
	      WorkbookSettings ws = new WorkbookSettings();
	      ws.setLocale(new Locale("en", "EN"));
	      w = Workbook.getWorkbook(new File(path));
	      
	      // Gets the sheets from workbook
	      for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
	      {
	        Sheet s = w.getSheet(sheet);
	        bw = openWriter(s.getName());
	 
	        Cell[] row = null;
	         
	        // Gets the cells from sheet
	        for (int i = 0 ; i < s.getRows() ; i++)
	        {
	          row = s.getRow(i);
	 
	          if (row.length > 0)
	          {
	            bw.write(row[0].getContents());
	            for (int j = 1; j < row.length; j++)
	            {
	              bw.write(';');
	              bw.write(row[j].getContents());
	            }
	          }
	          bw.newLine();
	        }
	        bw.flush();
		    bw.close();
	      }
	      UploadDAO uDao = new UploadDAO();
	      uDao.uploadTxtFiles();
	      w.close();
	    } catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(w != null) {
				w.close();
			} 
		} 
	}

	private BufferedWriter openWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
		  File file = createFile(name);
		  OutputStream os = (OutputStream)new FileOutputStream(file);
	      String encoding = "UTF8";
	      OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
	      BufferedWriter bw = new BufferedWriter(osw);
	      return bw;
	}

	private File createFile(String name) {
		File file = null;
		if(name.equals("Base Data")) {
			file = new File("upload/base_data.txt");
		} else if (name.equals("Event-Cause Table")) {
			file = new File("upload/event_cause.txt");
		} else if (name.equals("Failure Class Table")) {
			file = new File("upload/failure_class.txt");
		} else if (name.equals("UE Table")) {
			file = new File("upload/ue.txt");
		} else if (name.equals("MCC - MNC Table")) {
			file = new File("upload/mcc_mnc.txt");
		}
		return file;
	}
}
