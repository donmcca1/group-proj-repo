package com.ericsson.group.jaxrs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.ericsson.group.services.BaseService;
import com.ericsson.group.services.UploadService;
import com.ericsson.group.utilities.EditExcel;

@Path("/upload")
public class UploadRestService {
	@Inject
	private UploadService service;
	
	
	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/file")
    public Response handleUpload(MultipartFormDataInput multipartFormDataInput) {
		// local variables
        MultivaluedMap<String, String> multivaluedMap = null;
        String fileName = null;
        InputStream inputStream = null;
        String uploadFilePath = null;
		
        try {
            Map<String, List<InputPart>> map = multipartFormDataInput.getFormDataMap();
            List<InputPart> lstInputPart = map.get("file");
 
            for(InputPart inputPart : lstInputPart){
 
                // get filename to be uploaded
                multivaluedMap = inputPart.getHeaders();
                fileName = getFileName(multivaluedMap);
                if(null != fileName && !"".equalsIgnoreCase(fileName)){
 
                    // write & upload file to UPLOAD_FILE_SERVER
                    inputStream = inputPart.getBody(InputStream.class,null);
                    uploadFilePath = writeToFileServer(inputStream, fileName);
 
                    // close the stream
                    inputStream.close();
                }
            }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally{
            // release resources, if any
        }
        
        service.uploadData(uploadFilePath);
        return Response.ok("File uploaded successfully at " + uploadFilePath).build();
    }
	
	private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
		 
        OutputStream outputStream = null;
        String path = BaseCRUDService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	path = path.substring(0, path.indexOf("classes")) + "upload/";
        String qualifiedUploadFilePath = path + fileName;
 
        try {
            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally{
            //release resource, if any
            outputStream.close();
        }
        return qualifiedUploadFilePath;
    }
 
	
	private String getFileName(MultivaluedMap<String, String> multivaluedMap) {
		
        String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
 
        for (String filename : contentDisposition) {
 
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String exactFileName = name[1].trim().replaceAll("\"", "");
                return exactFileName;
            }
        }
        return "UnknownFile";
    }
}
