package com.bkavca.getpdf.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bkavca.getpdf.handle.WebServiceHandle;
import com.bkavca.getpdf.model.WebServiceResponse;

/**
 * 
 * @author HungDMc
 *
 */
@Path("/getPdf")
public class GetPdfService {

	// URI: /contextPath/servletPath/getPdf/{pdfPath}
	@GET
	@Path("/{pdfPath}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public WebServiceResponse getResponse(@PathParam("pdfPath") String pdfPath) {
		
		return WebServiceHandle.getPdfFile(pdfPath);
	}
}
