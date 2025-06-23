package com.pluralsight.ui;

import com.pluralsight.Contract;
import com.pluralsight.models.*;
import com.pluralsight.data.*;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //will be responsible for all output to the screen, reading of
    //user input, and "dispatching" of the commands to the Dealership as
    //needed. (ex: when the user selects "List all Vehicles", UserInterface would
    //call the appropriate Dealership method and then display the vehicles it
    //returns.)

    private Dealership dealership;
    private Console console = new Console();

    public UserInterface() {
        this.dealership = null;
    }


    public void displayMenu() {

        while (true) {


            //loop to keep the menu running
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nWelcome to " + dealership.getName());
            System.out.println("1. Find vehicles within a price range");
            System.out.println("2. Find vehicles by make and model");
            System.out.println("3. Find vehicles by year");
            System.out.println("4. Find vehicles by color");
            System.out.println("5. Find vehicles by mileage");
            System.out.println("6. Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7. List ALL vehicles");
            System.out.println("8. Add a vehicle");
            System.out.println("9. Remove a vehicle");
            System.out.println("10. Sell/Lease a vehicle");
            System.out.println("99. Quit");
            System.out.print("Enter your choice: ");

            //read user input
            int command = scanner.nextInt();

            //process the user's request based on their command
            switch (command) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellVehicle();
                case 11:
                    processLeaseVehicle();
                case 99:
                    System.out.println("Exiting...");
                    return; //exit the loop and end the program
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }

    }

    private void processLeaseVehicle() {
        int vin = console.promptForInt("Enter VIN of the vehicle: ");

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        String name = console.promptForString("Customer Name: ");
        String email = console.promptForString("Customer Email: ");
        String date = console.promptForString("Contract Date (YYYY-MM-DD): ");

            int currentYear = java.time.LocalDate.now().getYear();
            if ((currentYear - vehicle.getYear()) > 3) {
                System.out.println("Cannot lease a vehicle older than 3 years.");

            }

        LeaseContract newContract = new LeaseContract(date, name, email, vehicle, 0, 0, 0);

        if(newContract != null){
            LeaseDAO.createLease(newContract);
        } else {
            System.out.println("Could not complete a new sales contract");
        }
    }


    private void processSellVehicle() {

        int vin = console.promptForInt("Enter VIN of the vehicle: ");

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        String name = console.promptForString("Customer Name: ");
        String email = console.promptForString("Customer Email: ");
        String date = console.promptForString("Contract Date (YYYY-MM-DD): ");


            boolean finance = console.promptForString("Finance the vehicle? (yes/no): ").equalsIgnoreCase("yes");

        SalesContract newContract = new SalesContract(date, name, email, vehicle, 0, 0, 0, finance);

        if(newContract != null){
            SalesDAO.createSale(newContract);
        } else {
            System.out.println("Could not complete a new sales contract");
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v.toString());
            }
        }
    }


    public void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type (e.g., SUV, sedan): ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest() {
        System.out.println("Add a Vehicle to Dealership lot");
        System.out.println("Please enter the following details to add a vehicle to the lot: ");
        int vin = console.promptForInt("Vehicle VIN: ");
        int year = console.promptForInt("Vehicle Year: ");
        String make = console.promptForString("Vehicle Make: ");
        String model = console.promptForString("Vehicle Model: ");
        String type = console.promptForString("Vehicle Type: ");
        String color = console.promptForString("Vehicle Color: ");
        int mileage = console.promptForInt("Vehicle Mileage: ");
        double price = console.promptForDouble("Vehicle Price: ");

        try{
            Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
            dealership.addVehicle(newVehicle);

        } catch(Exception e){
            System.out.println("There was an error trying to add this vehicle to the lot.");
        }
    }

    public void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = scanner.nextInt();

        //search for the vehicle in the dealership's inventory
        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break; //exit the loop once the vehicle is found
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);  //remove the vehicle from the dealership
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}