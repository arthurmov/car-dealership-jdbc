package com.pluralsight;

import com.pluralsight.data.DealershipDAO;
import com.pluralsight.data.LeaseDAO;
import com.pluralsight.data.SalesDAO;
import com.pluralsight.ui.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    //will be responsible for starting the application via its main()
    //method and then creating the user interface and getting it started

    public static void main(String[] args) {

        if(args.length != 3){
            System.out.println("You must run this program with three arguments:" +
                    "<username> <password> <url>");
            System.exit(-1);
        }

        BasicDataSource basicDataSource = getBasicDataSourceFromArgs(args);
        DealershipDAO dealershipDAO = new DealershipDAO(basicDataSource);
        SalesDAO salesDAO = new SalesDAO(basicDataSource, dealershipDAO);
        LeaseDAO leaseContractDAO = new LeaseDAO(basicDataSource, dealershipDAO);


        UserInterface ui = new UserInterface(dealershipDAO, salesDAO, leaseDAO);


    }

    private static BasicDataSource getBasicDataSourceFromArgs(String[] args){
        String username = args[0];
        String password = args[1];
        String url = args[2];
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setUrl(url);
        return bds;
    }

    }
}