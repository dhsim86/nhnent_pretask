package lesson.Lesson02.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Created by dongho on 12/25/16.
 */
@WebServlet("/lesson/lesson02/WebContent/FileUploadServlet")
public class FileUploadServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<HTML><HEAD><TITLE>Multipart Test</TITLE></HEAD><BODY>");

        try
        {
            String contextRootPath = this.getServletContext().getRealPath("/");

            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            diskFactory.setRepository(
                    new File(contextRootPath + "/WEB-INF")
            );
            ServletFileUpload upload = new ServletFileUpload(diskFactory);

            @SuppressWarnings("unchecked")
            List<FileItem> items = upload.parseRequest(request);

            FileItem item = null;
            for (int i = 0; i < items.size(); i++) {
                item = items.get(i);

                if (item.isFormField())
                {
                    processFormField(out, item);
                } else
                {
                    processUploadFile(out, item, contextRootPath);
                }
            }
        }
        catch (Exception e)
        {
            out.println("<PRE>");
            e.printStackTrace(out);
            out.println("</PRE>");
        }

        out.println("</BODY></HTML>");
    }

    private void processUploadFile(PrintWriter out, FileItem item, String contextRootPath)
        throws Exception
    {
        String name = item.getFieldName();
        String fileName = item.getName();
        String contentType = item.getContentType();
        long fileSize = item.getSize();

        String uploadedFileName = System.currentTimeMillis() +
                                fileName.substring(fileName.lastIndexOf("."));
        File uploadedFile = new File(
                contextRootPath + "/upload/" + uploadedFileName);
        item.write(uploadedFile);

        out.println("<P>");
        out.println("Parameter name: " + name + "<BR>");
        out.println("File name: " + fileName + "<BR>");
        out.println("Content Type: " + contentType + "<BR>");
        out.println("File Size: " + fileSize + "<BR>");
        System.out.println(uploadedFileName);
        out.println("<IMG SRC='upload/" + uploadedFileName + "' width='300'><BR>");
        out.println("</P>");
    }

    private void processFormField(PrintWriter out, FileItem item)
        throws Exception
    {
        String name = item.getFieldName();
        String value = item.getString("UTF-8");

        out.println(name + ":" + value + "<BR>");
    }
}
