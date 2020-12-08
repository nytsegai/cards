package jobsearch.projectutils.utils.sql;

import jobsearch.framework.database.ConnectSQLServer;
import jobsearch.framework.logger.Logger;
import jobsearch.framework.utils.Utils;
import jobsearch.projectutils.utils.appdata.ApplicationConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class contains methods for test data managing(creating and deleting);
 * <p>
 * Class methods are designed to prepare and execute sql scripts appropriate for each necessary data set;
 *
 * @author Iryna.Zhukava
 * Created  on 8/20/2018
 */
public class SQLTestDataCreator {
    public static final String DELETE_FEESCHEDULE_REQUEST = "sqlscripts/deleteFeeScheduleSQL";
    public static final String DELETE_DENIALS_MANAGEMENT_FILE = "sqlscripts/deleteDenialsManagementData.sql";
    protected static final Logger logger = Logger.getInstance();
    private static final String DELETE_INSURANCE_REQUEST = "sqlscripts/deleteInsuranceSQL";
    private static final String DELETE_SINGLE_VISIT_FILE = "sqlscripts/deleteSingleVisitSQL";
    private static final String DELETE_FACILITY_REQUEST = "sqlscripts/deleteFacilitySQL";
    private static final String DELETE_STATEMENT_REQUEST = "sqlscripts/deleteAutomatedStatementSQL";
    private static final String CREATE_PATIENTS_REQUEST = "sqlscripts/createPatientsSQL";
    private static final String DELETE_USER_REQUEST = "sqlscripts/deleteUserSQL";
    private static final String DELETE_USERS_REQUEST = "sqlscripts/deleteAllTempPatientsSQL";
    private static final String DELETE_CHARGE_REQUEST = "sqlscripts/deleteChargeEntrySQL";
    private static final String ALTERNATIVE_CREATE_PATIENTS_REQUEST = "sqlscripts/alternativePatientsCreateSQL";
    private static final String DELETE_PROTOCOL_REQUEST = "sqlscripts/deleteProtocolSQL";
    private static final String DELETE_PRACTICE_REQUEST = "sqlscripts/deletePracticeSQL";
    private static final String DELETE_COMPANY_REQUEST = "sqlscripts/deleteCompanySQL";
    private static final String CREATE_DENIALS_MANAGEMENT_FILE = "sqlscripts/createDenailsManagementData.sql";
    private static final String SELECT_DENIALS_WORKFLOW_STATUS = "sqlscripts/SelectDenialsManagementItemWorkflowStatus.sql";

    public synchronized static void deleteFeeSchedule(String procedureCode) {

//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            String sql = sqlConnectionManager.readRequestFromFile(DELETE_FEESCHEDULE_REQUEST);
//
//
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                //procedure code
//                stmt.setString(1, procedureCode);
//                stmt.setString(2, ApplicationConfig.PRACTICE);
//                stmt.execute();
//
//            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }
//

    }

    public synchronized static void deleteInsurance(String insuranceShortName) {
//
//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            String sql = sqlConnectionManager.readRequestFromFile(DELETE_INSURANCE_REQUEST);
//
//
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                //insurance name
//                stmt.setString(1, insuranceShortName.toUpperCase());
//                stmt.setString(2, ApplicationConfig.PRACTICE);
//                stmt.execute();
//
//            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }
//

    }

    public static synchronized void deleteSingleVisit(Long logNumber) {
//        if ((logNumber != null) && (logNumber != 0l)) {
//            ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//            try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//                logger.info("started deleting patient visit  in thread " + Thread.currentThread().getId() + " with id " + logNumber);
//                String sql = sqlConnectionManager.readRequestFromFile(DELETE_SINGLE_VISIT_FILE);
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setLong(1, logNumber);
//                stmt.execute();
//                logger.info("finished deleting patient visit in thread " + Thread.currentThread().getId() + " with id " + logNumber);
//
//            } catch (IOException | SQLException | ClassNotFoundException e) {
//                logger.info(e.getStackTrace());
//            }
//        }
    }


    public static synchronized void deleteAutomatedStatement(String batchTitle) {
//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            String sql = sqlConnectionManager.readRequestFromFile(DELETE_STATEMENT_REQUEST);
//
//
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setString(1, batchTitle);
//                stmt.setString(2, ApplicationConfig.PRACTICE);
//                stmt.execute();
//
//            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }
    }

    public synchronized static void createPatients() throws IOException {
//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        logger.info("Started bulk patients creation");
//        String sql = "";
//        Patient patient = new Patient();
//        if (Utils.isDateInIntervalOfDays(patient.getDOB(), 15)) {
//            sql = sqlConnectionManager.readRequestFromFile(ALTERNATIVE_CREATE_PATIENTS_REQUEST);
//
//        } else {
//            sql = sqlConnectionManager.readRequestFromFile(CREATE_PATIENTS_REQUEST);
//        }
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setString(1, ApplicationConfig.PRACTICE.toUpperCase());
//                stmt.setString(2, ApplicationConfig.CLINIC.toUpperCase());
//                stmt.execute();
//
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }
//        logger.info("Finished bulk patients creation");

    }

    public synchronized static void deleteUser(String userId) {
//        if (!userId.isEmpty()) {
//            ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//            try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//                String sql = sqlConnectionManager.readRequestFromFile(DELETE_USER_REQUEST);
//
//
//                if (connection != null) {
//                    PreparedStatement stmt = connection.prepareStatement(sql);
//                    stmt.setString(1, userId.toUpperCase());
//                    stmt.setString(2, ApplicationConfig.PRACTICE);
//                    stmt.execute();
//
//                }
//            } catch (IOException | SQLException | ClassNotFoundException e) {
//                logger.error(e.getMessage());
//            }
//        }
    }

    public synchronized static void deletePatients() throws IOException {
//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            String sql = sqlConnectionManager.readRequestFromFile(DELETE_USERS_REQUEST);
//
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setString(1, ApplicationConfig.PRACTICE);
//                stmt.execute();
//
//            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }


    }

    public synchronized static void deleteChargeEntries() throws IOException {
//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            String sql = sqlConnectionManager.readRequestFromFile(DELETE_CHARGE_REQUEST);
//
//            logger.info("Deleting charge entry data");
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setString(1, ApplicationConfig.PRACTICE);
//                stmt.setString(2, "AUTOMATON, %");
//
//                stmt.execute();
//                logger.info("Finished deleting charge entry data");
//
//            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }

    }

    public synchronized static void deleteProtocol(String protocolName) {
//        if (!protocolName.isEmpty()) {
//            ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//            try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//                String sql = sqlConnectionManager.readRequestFromFile(DELETE_PROTOCOL_REQUEST);
//
//
//                if (connection != null) {
//                    PreparedStatement stmt = connection.prepareStatement(sql);
//                    stmt.setString(1, protocolName.toUpperCase());
//                    stmt.setString(2, ApplicationConfig.PRACTICE);
//
//                    stmt.execute();
//
//                }
//            } catch (IOException | SQLException | ClassNotFoundException e) {
//                logger.error(e.getMessage());
//            }
//        }
    }

    public static void deletePractice(String practiceShortName) {
//String shortDatabaseName=ApplicationConfig.DB_NAME;
//        shortDatabaseName=shortDatabaseName.substring(1,shortDatabaseName.length()-1);
//        if (!practiceShortName.isEmpty()) {
//            ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//            try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//                String sql = sqlConnectionManager.readRequestFromFile(DELETE_PRACTICE_REQUEST);
//
//
//                if (connection != null) {
//                    PreparedStatement stmt = connection.prepareStatement(sql);
//                    stmt.setString(1, practiceShortName.toUpperCase());
//                    stmt.setString(2, shortDatabaseName);
//
//                    stmt.execute();
//
//                }
//            } catch (IOException | SQLException | ClassNotFoundException e) {
//                logger.error(e.getMessage());
//            }
//        }
    }

    public synchronized static void deleteCompany(String companyName) {
//
//        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
//        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
//            String sql = sqlConnectionManager.readRequestFromFile(DELETE_COMPANY_REQUEST);
//
//
//            if (connection != null) {
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                //procedure code
//                stmt.setString(1, companyName);
//                stmt.setString(2, ApplicationConfig.PRACTICE);
//                stmt.execute();
//
//            }
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            logger.error(e.getMessage());
//        }


    }

    /**
     * Creates test data for the Denials Management API testing.
     *
     * @return true if test data was created
     */
//    public static boolean createDenialsManagementData(String practice) {
////        boolean testDataCreated = false;
////        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
////        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
////            String findPatient = sqlConnectionManager.readRequestFromFile(CREATE_DENIALS_MANAGEMENT_FILE);
////            ResultSet results = null;
////
////            if (connection != null) {
////                PreparedStatement stmt = connection.prepareStatement(findPatient);
////                stmt.setString(1, practice);
////                testDataCreated = !stmt.execute();
////
////
////            }
////        } catch (IOException | SQLException | ClassNotFoundException e) {
////            logger.error(e.getMessage());
////        }
////        return testDataCreated;
//    }

    /**
     * Deletes test data for the Denials Management API testing.
     *
     * @return true if test data was deleted
     */
//    public static boolean deleteDenialsManagementData(String practice) {
////        boolean testDataCreated = false;
////        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
////        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
////            String findPatient = sqlConnectionManager.readRequestFromFile(DELETE_DENIALS_MANAGEMENT_FILE);
////            ResultSet results = null;
////
////            if (connection != null) {
////                PreparedStatement stmt = connection.prepareStatement(findPatient);
////                stmt.setString(1, practice);
////                testDataCreated = !stmt.execute();
////
////
////            }
////        } catch (IOException | SQLException | ClassNotFoundException e) {
////            logger.error(e.getMessage());
////        }
////        return testDataCreated;
//    }

    /**
     * Selects Workflow Status by eraClaimPk for the Denials Management API testing.
     *
     * @return true if test data was deleted
     */
//    public static int selectWorkFlowStatus(String eraClaimPk) {
////        boolean testDataCreated = false;
////        int status = 0;
////        ConnectSQLServer sqlConnectionManager = new ConnectSQLServer();
////        try (Connection connection = sqlConnectionManager.connectToDatabase()) {
////            String request = sqlConnectionManager.readRequestFromFile(SELECT_DENIALS_WORKFLOW_STATUS);
////            ResultSet results = null;
////
////            if (connection != null) {
////                PreparedStatement stmt = connection.prepareStatement(request);
////                stmt.setString(1, ApplicationConfig.PRACTICE);
////                stmt.setString(2, eraClaimPk);
////                results = stmt.executeQuery();
////                while (results.next()) {
////                    status = results.getInt(1);
////                }
////
////
////            }
////        } catch (IOException | SQLException | ClassNotFoundException e) {
////            logger.error(e.getMessage());
////        }
////        return status;
////    }
//    }
}
