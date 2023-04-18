package loan;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Servlet implementation class Enquiry
 */
@WebServlet("/Enquiry")
public class Enquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Enquiry() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = null;
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("acc.txt"));
		String line = reader.readLine();
		int matchFound = 0;

		ArrayList<UinBean> records = new ArrayList<UinBean>();

		try {
			out = response.getWriter();
			String item = request.getParameter("item"); // item = "UIN or Account"
			String num = request.getParameter("num"); // either uin number or account number
			System.out.println("item " + item);
			System.out.println("num " + num);
			request.setAttribute("num", num);
			request.setAttribute("item", item);
			// if user selects uin from dropdown, check for the uin number in the file and
			// bring the records
			if (item.equals("UIN")) {
				String checkUIN;
				while (line != null) {
					checkUIN = line.split(";")[1];
					if (checkUIN.equals(num)) {
						UinBean uinRec = new UinBean();
						uinRec.setAccount(line.split(";")[0]);
						uinRec.setUin(line.split(";")[1]);
						uinRec.setName(line.split(";")[2]);
						uinRec.setAge(line.split(";")[3]);
						uinRec.setAddress(line.split(";")[4]);
						uinRec.setOutstandingloan(line.split(";")[5]);
						uinRec.setLastpaymentdate(line.split(";")[6]);
						uinRec.setRepayperiodinmonths(line.split(";")[7]);
						records.add(uinRec);
						matchFound=1;
					}
					// read next line
					line = reader.readLine();
				}

				if(matchFound==1) {
				int page = 1;
				int recordsPerPage = 2;
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				ArrayList list = getRecordsPerPage(records, page, recordsPerPage);
				System.out.println("l" + list);

				int noOfRecords = records.size();

				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				System.out.println("noOfRecords" + noOfRecords);
				System.out.println("noOfPages" + noOfPages);
				request.setAttribute("records", list);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);

				request.getRequestDispatcher("uin.jsp").forward(request, response);
				reader.close();
			}
			}
			
			else if (item.equals("Account")) {
				String checkAcc;
				//List<String> pipes = new ArrayList();
				while (line != null) {
					System.out.println("line:" + line);
					checkAcc = line.split(";")[0];
					//System.out.println("account " + checkAcc);
					System.out.println("acc "+num);
					if (checkAcc.equals(num)) {
						UinBean uinRec = new UinBean();
						uinRec.setAccount(line.split(";")[0]);
						uinRec.setUin(line.split(";")[1]);
						uinRec.setName(line.split(";")[2]);
						uinRec.setAge(line.split(";")[3]);
						uinRec.setAddress(line.split(";")[4]);
						uinRec.setOutstandingloan(line.split(";")[5]);
						uinRec.setLastpaymentdate(line.split(";")[6]);
						uinRec.setRepayperiodinmonths(line.split(";")[7]);
						records.add(uinRec);
						//pipes.add(line);
						matchFound=1;
					}
					// read next line
					line = reader.readLine();
				}
//				System.out.println("size" + pipes.size());
//				System.out.println(pipes);
//				request.setAttribute("accs", pipes);
				if(matchFound==1) {
				request.setAttribute("records", records);
				request.getRequestDispatcher("account.jsp").forward(request, response);
				reader.close();
			}
			}
			
			if(matchFound==0)
			{
				String someMessage = "Invalid values!";
            	out.println("<script type='text/javascript'>");
            	out.println("alert(" + "'" + someMessage + "'" + ");</script>");
            	out.println("</head><body></body></html>");
            	RequestDispatcher rd=request.getRequestDispatcher("enquiry.jsp");            
            	rd.include(request, response);
			}

		} catch (Exception e) {
			out.println("Error" + e.getMessage());
		}
	}

	private ArrayList getRecordsPerPage(ArrayList<UinBean> records, int page, int recordsPerPage) {
		// TODO Auto-generated method stub
		System.out.println("page" + page);
		System.out.println(recordsPerPage);
		System.out.println(records);

		if (page == 0) {
			page = 1;
		}
		page = page - 1;

		Object[] objects = records.toArray();
		System.out.println(objects);
		List<Object> list1 = new ArrayList(Arrays.asList(objects));
		/*
		 * Change the value of chunkSize to get desired number of elements in the
		 * subLists
		 */
		int chunkSize = 2;
		AtomicInteger counter = new AtomicInteger();
		final Collection<List<Object>> partitionedList = list1.stream()
				.collect(Collectors.groupingBy(i -> counter.getAndIncrement() / chunkSize)).values();
		Object[] objects1 = partitionedList.toArray();
		System.out.println("selectedRec" + objects1[page]);
		return (ArrayList) objects1[page];
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
