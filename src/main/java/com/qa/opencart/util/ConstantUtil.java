package com.qa.opencart.util;

import java.util.ArrayList;
import java.util.List;



public class ConstantUtil {
	
    public static final String login_page_title ="Account Login";
    public static final String Accounts_page_title ="My Account";
    public static final String Accounts_page_Header_title ="Your Store";
    public static final int Accounts_page_section_count =4;
    public static final String Account_sucess_message = "Your Account Has Been Created!";
    public static final String Exel_sheet_name ="register";
    
    public static List<String> getAccSectionsList() {
    	List<String> accList=new ArrayList<>();
    	accList.add("My Account");
    	accList.add("My Orders");
    	accList.add("My Affiliate Account");
    	accList.add("Newsletter");
    	
    	return accList;
    }
   
}
