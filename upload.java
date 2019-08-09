package com.docto.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.io.FilenameUtils;
import java.util.Iterator;
import java.util.List;
public class upload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		doPost(req,res);

}
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		   String rootDirectory = "d:/images";
		   // Check that we have a file upload request
		   boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		   
		   if (isMultipart) 
		   {
		    // Create a factory for disk-based file items
		    FileItemFactory factory = new DiskFileItemFactory();
		    // Create a new file upload handler
		    ServletFileUpload upload = new ServletFileUpload(factory);
		   
		    // Process the uploaded items
		   
		    try {
		      // Parse the request
		      List items = upload.parseRequest(req);
		      // Process the uploaded items
		      Iterator iter = items.iterator();
		      while (iter.hasNext()) 
		      {
		       FileItem item = (FileItem) iter.next();
		     
		       if (item.isFormField()) 
		       {
		        String fieldName = item.getFieldName();
		        String fileName = item.getName();
		        String contentType = item.getContentType();
		        boolean isInMemory = item.isInMemory();
		        long sizeInBytes = item.getSize();
		       } 
		       else 
		       {
		        String fileName = item.getName();
		        if (fileName != null && !fileName.equals("")) 
		        {
		         fileName = FilenameUtils.getName(fileName);
		         File uploadedFile = new File(rootDirectory + fileName);
		       
		       
		         try {
		            item.write(uploadedFile);
		            //here set the root directory path
		            File newfile=new File("C://Users//dell//eclipse-workspace//docto//war//image//"+uploadedFile.getName()+".jpg");
		            uploadedFile.renameTo(newfile);
		            res.getWriter().write(newfile.getName());
		          
		          } 
		         catch (Exception e) 
		         {
		           e.printStackTrace();
		         }
		        }
		       }
		      }
		     } 
		    catch (FileUploadException e) 
		    {
		     e.printStackTrace();
		    }
		   }
		   }
		
	}