package loan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Paths;

import bean.UinBean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet implementation class account
 */
@WebServlet("/account")
public class account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=null;
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("acc.txt"));
		String line = reader.readLine();
		int matchFound=0;

		ArrayList<UinBean> records = new ArrayList<UinBean>();
		try {
		String acc = request.getParameter("account");
		out = response.getWriter();
		String checkAcc;
		while (line != null) {
			System.out.println("line:"+line);
			checkAcc=line.split(";")[0];
			System.out.println("acc "+acc);
			if(checkAcc.equals(acc))
			{
				UinBean accRec = new UinBean();
				accRec.setAccount(line.split(";")[0]);
				accRec.setUin(line.split(";")[1]);
				accRec.setName(line.split(";")[2]);
				accRec.setAge(line.split(";")[3]);
				accRec.setAddress(line.split(";")[4]);
				accRec.setOutstandingloan(line.split(";")[5]);
				accRec.setLastpaymentdate(line.split(";")[6]);
				accRec.setRepayperiodinmonths(line.split(";")[7]);
				records.add(accRec);
				matchFound=1;
			}
			// read next line
			line = reader.readLine();
		}
		if(matchFound==1) {
		request.setAttribute("records", records);
	request.getRequestDispatcher("account.jsp").forward(request, response);
	reader.close();
		}
		else
		{
			String someMessage = "Invalid values!";
        	out.println("<script type='text/javascript'>");
        	out.println("alert(" + "'" + someMessage + "'" + ");</script>");
        	out.println("</head><body></body></html>");
        	RequestDispatcher rd=request.getRequestDispatcher("enquiry.jsp");            
        	rd.include(request, response);	
		}
		}
		catch(Exception e) {
			out.println("Error"+e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
