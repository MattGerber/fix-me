package com.fixme.market.markets;

import com.fixme.commons.database.Database;
import com.fixme.commons.database.Items;
import com.fixme.commons.database.MarketResults;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.net.URL; 

public class CryptoMarket extends Market{

	public CryptoMarket(String marketName) {
		super(marketName);

        // This should be pulled from a file to add alternate markets.

        try {
            MarketResults marketResults = Database.GetMarketByName(marketName);
            for (Items i : marketResults.getItemList())
                instruments.add(new Instrument(i.getCode(), i.getProduct()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



		// instruments.add(new Instrument("ETH", "Ethereum"));
        // instruments.add(new Instrument("XRP", "Ripple"));
        // instruments.add(new Instrument("LTC", "Litecoin"));
        // instruments.add(new Instrument("USDT", "Tether"));
        // instruments.add(new Instrument("BCH", "Bitcoin Cash"));
        // instruments.add(new Instrument("LIBRA", "Libra"));
        // instruments.add(new Instrument("XMR", "Monero"));
        // instruments.add(new Instrument("EOS", "EOS"));
        // instruments.add(new Instrument("BNB", "Binance Coin"));
        // instruments.add(new Instrument("ETH", "Ethereum"));
	}
}

/*
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
  public static void main(String[] args) {
    try {
      File myObj = new File("filename.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
*/
