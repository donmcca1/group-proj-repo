package com.ericsson.group.jaxrs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import com.ericsson.group.entities.BaseDataList;
import com.ericsson.group.services.BaseService;

@Path("/base")
public class BaseCRUDService {
	@Inject
	private BaseService service;
	
	//--- SELECT ALL ---//
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> getBaseData(){
		return service.getAllBaseData();
	}
	
	//--- SELECT BY IMSI ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imsi}")
	public BaseDataList getBaseDataByImsi(@PathParam("imsi") Long imsi){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByImsi(imsi));
		return list;
	}
	
	//--- SELECT BY DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date")
	public BaseDataList getBaseDataByDate(@QueryParam("start") Date startDate, @QueryParam("end") Date endDate){
		BaseDataList list = new BaseDataList();
		list.setBaseDataList(service.getBaseDataByDate(startDate, endDate));
		return list;
	}
	
	//--- COUNT BY MODEL AND DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/ue_type")
	public Long countByModelAndDate(@QueryParam("ue_type") Integer ue_type,
			@QueryParam("start") Date startDate, @QueryParam("end") Date endDate){
		return (Long)service.countByModelAndDate(ue_type, startDate, endDate);
	}

	//--- SELECT BY DATE NUM FAILURES AND DURATION---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/numfail")
	public List<Object[]> getBaseDataByDate2(@QueryParam("start2") Date startDate, @QueryParam("end2") Date endDate){
		List<Object[]> v = service.getNumFailuresAndDurationByDate(startDate, endDate);
		return v;
	}
	
	//--- SELECT BY IMSI, COUNT FAILURES BY DATE ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/date/imsi")
	public Long getFailuresByDate(@QueryParam("imsi") Long imsi, @QueryParam("start") Date startDate, 
			@QueryParam("end") Date endDate){
		System.out.println("CRUD");
		return (Long)service.getFailuresByDate(imsi, startDate, endDate);
	}
	
	//--- LILY COUNT OF EVENTID/CAUSECODE BY MODEL ---//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ue_type/count")
	public Collection<?> countByModelEventIdCauseCode(@QueryParam("ue_type") Integer ue_type){
		Collection<?> c = service.countByModelEventIdCauseCode(ue_type);
		return c;
	}

	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/file")
    public Response handleUpload(MultipartFormDataInput multipartFormDataInput) {
		String path = BaseCRUDService.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(".........");
		System.out.println(path);
		System.out.println(".........");
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
