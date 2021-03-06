package com.fixme.commons.database;

import java.util.ArrayList;
import java.sql.*;

public class Database {

    public static void InitialiseDB() throws ClassNotFoundException {
        try {
            CreateMarketsTable();
            CreateTransactionsTable();
            InsertMarkets("Crypto", "ETH,Ethereum\nXRP,Ripple\nLTC,Litecoin\nUSDT,Tether\nBCH,Bitcoin Cash\nLIBRA,Libra\nXMR,Monero\nEOS,EOS\nBNB,Binance Coin\nETH,Ethereum");
            InsertMarkets("Stocks", "BPYU,BrookfieldProperty\nNRG,NRGEnergy\nARD,ArdaghGroup\nNLOK,NortonLifeLock\nEAF,GrafTechInternational\nBDN,BrandywineRealtyTrust\nROK,RockwellAutomation\nZM,ZoomVideoCommunications\nCRM,Salesforce.com\nAGCO,AGCOCorp");
            InsertMarkets("Jadon", "SFB,Surfboard\nWX,Wax\nWTS,Wetsuits\nFIN,Fins\nLSH,Leash\nTRAC,Traction\nVNSO,VansOriginals\nRIP,Ripcurl\nCAP,Cap\nBTS,Booties");
        } catch (Exception e) {
            System.out.println("InitialiseDB Exception: " + e);
        };
    }

    public static void CreateMarketsTable() throws ClassNotFoundException, SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS markets (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	market_name string NOT NULL,\n"
                + " items string NOT NULL,\n"
                + " UNIQUE(market_name, items)\n"
                + ");";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute(); 
            System.out.println("Markets table created successfully!");
        } catch (SQLException e) {
            System.out.println("Create Map Table Exception: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static void CreateTransactionsTable() throws ClassNotFoundException, SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS transactions (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	broker_id varchar NOT NULL,\n"
                + " market_name varchar  NOT NULL,\n"
                + " item varchar NOT NULL,\n"
                + " quantity integer NOT NULL,\n"
                + " quoted_price double NOT NULL,\n"
                + "	type varchar CHECK(type IN ('BUY','SELL')) NOT NULL,\n"
                + " created_date datetime NOT NULL"
                + ");";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute(); 
            System.out.println("Transactions table created successfully!");
        } catch (SQLException e) {
            System.out.println("Create Map Table Exception: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static void InsertMarkets(String market_name, String items) throws ClassNotFoundException, SQLException {
        String sql = "INSERT OR IGNORE INTO markets(market_name,items) VALUES (?,?);";
        Connection conn = SQLiteHelper.GetInstance();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, market_name);
            pstmt.setString(2, items);
            
            pstmt.executeUpdate();
            System.out.println("Market added Successfully");
        } catch (SQLException e) {
            System.out.println("Market adding error: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static void InsertTransactions(String brokerId,String marketName,String item,Integer quantity, Double quotedPrice, String type) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO transactions (broker_id,market_name,item,quantity,quoted_price,type,created_date) VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP);";
        Connection conn = SQLiteHelper.GetInstance();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, brokerId);
            pstmt.setString(2, marketName);
            pstmt.setString(3, item);
            pstmt.setInt(4, quantity);
            pstmt.setDouble(5, quotedPrice);
            pstmt.setString(6, type);
            
            pstmt.executeUpdate();
            System.out.println("Transaction added Successfully");
        } catch (SQLException e) {
            System.out.println("Transaction adding error: " + e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static MarketResults GetMarketByName(String marketName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM markets WHERE market_name = ?";
        Connection conn = SQLiteHelper.GetInstance();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, marketName);
            ResultSet rs = pstmt.executeQuery();
            MarketResults marketResults = new MarketResults(rs.getString("market_name"),rs.getString("items").split("\n"));
            return (marketResults);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
        return null;
    }

    public static void DisplayMarkets() throws SQLException, ClassNotFoundException {
        String sql = "SELECT market_name FROM markets";
        Connection conn = SQLiteHelper.GetInstance();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("market_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
    }

    public static ArrayList<String> GetMarketList() throws SQLException, ClassNotFoundException {
        String sql = "SELECT market_name FROM markets";
        Connection conn = SQLiteHelper.GetInstance();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> marketList = new ArrayList<>();
            while (rs.next()){
                marketList.add(rs.getString("market_name"));
            }
            return (marketList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
        return null;
    }

    public static ResultSet GetAllTransactions() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM transactions";
        Connection conn = SQLiteHelper.GetInstance();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            return (rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteHelper.CloseConnection(conn);
        }
        return null;
    }
}