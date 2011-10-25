
/*
 * Copyright 2002-2011 SCOOP Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.scoopgmbh.customerservice;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2011-10-12T09:59:16.212+02:00
 * Generated source version: 2.4.2
 * 
 */
 
public class CustomerService_CustomerServicePort_Server{

    protected CustomerService_CustomerServicePort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new CustomerServiceImpl();
        String address = "http://localhost:9091/CustomerServicePort";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new CustomerService_CustomerServicePort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
