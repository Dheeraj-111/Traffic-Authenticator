package controller;

import dto.MedicalDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.MedicalRegisterAuthenticator;

@MultipartConfig(maxFileSize = 16177215)
public class MedicalReportServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
  {
    HttpSession session=request.getSession();
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        response.sendRedirect("userLogin.jsp");
    }
  }
  public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
  {
      HttpSession session=request.getSession();
      String userid=(String)session.getAttribute("userid");
      Part filepart=request.getPart("doc");
      String doc_title=request.getParameter("doc_title");
      
      MedicalDTO dto=new MedicalDTO();
      dto.setDocument(filepart);
      dto.setUserid(userid);
      dto.setDocument_title(doc_title);
      
      MedicalRegisterAuthenticator medical=new MedicalRegisterAuthenticator();
      boolean report=medical.setMedicalReport(dto);
      if(report)
      {
          response.sendRedirect("medicalReports.jsp");
      }
      else
      {
          System.out.println("add alert here");
      }
  }
}
