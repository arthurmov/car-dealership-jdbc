package com.pluralsight.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

public class VehicleDAO implements isVehicle {

    private BasicDataSource data_Source;

    public VehicleDAO(BasicDataSource dataSource) {
        this.data_Source = dataSource;
    }


    @Override
    public List<Vehicle> getAllVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();

        try (

                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("SELECT * FROM vehicles");
                ResultSet resultSet = preparedStatement.executeQuery();

        ) {

            while (resultSet.next()) {

                int vin = resultSet.getInt("vin");
                int year = resultSet.getInt("year");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                String vehicle_Type = resultSet.getString("vehicleType");
                String color = resultSet.getString("color");
                int odometer = resultSet.getInt("odometer");
                double price = resultSet.getDouble("price");

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicle_Type, color, odometer, price);
                vehicles.add(vehicle);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return vehicles;
        }
    }


    @Override
    public List<Vehicle> getVehiclesByPrice(double min, double max) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (

                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")

        ) {

            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("odometer"),
                            resultSet.getDouble("price")
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (

                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE make = ? AND model = ?")

        ) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("odometer"),
                            resultSet.getDouble("price")
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }



    @Override
    public List<Vehicle> getVehiclesByYear(int min, int max) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (
                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?");

        ) {

            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {

                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("odometer"),
                            resultSet.getDouble("price")
                    );
                    vehicles.add(vehicle);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getVehiclesByColor(String color) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (

                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE color = ?"
                );

        ) {

            preparedStatement.setString(1, color);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {

                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("odometer"),
                            resultSet.getDouble("price")
                    );

                    vehicles.add(vehicle);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }


    @Override
    public List<Vehicle> getVehiclesByMileage(int min, int max) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (
                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?");

        ) {

            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {

                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("odometer"),
                            resultSet.getDouble("price")
                    );
                    vehicles.add(vehicle);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getVehiclesByType(String vehicleType) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (

                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE vehicle_type = ?"
                );

        ) {

            preparedStatement.setString(1, vehicleType);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {

                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vin"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("odometer"),
                            resultSet.getDouble("price")
                    );

                    vehicles.add(vehicle);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }


    @Override
    public int addVehicle(Vehicle vehicle) {

        int generatedId = -1;

        try (

                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO vehicles(vin,year, make, model,type, color, mileage, price) VALUES(?,?,?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS

                );

        ) {

            preparedStatement.setInt(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getVehicleType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setDouble(8, vehicle.getPrice());
            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    generatedId = keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }




    @Override
    public void removeVehicle(int vin) {

        try (
                Connection connection = data_Source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM vehicles WHERE vin = ?"
                );
        ) {
            preparedStatement.setInt(1, vin);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}