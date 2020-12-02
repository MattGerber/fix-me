package com.fixme.market.markets;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.net.URL; 

public class CryptoMarket extends Market{

	public CryptoMarket() {
		super("Crypto");

        // This should be pulled from a file to add alternate markets.
        try {
            System.out.println("sdfaf" + getClass().getResource("crypto.txt"));
            // URL url = getClass().getResource("Inventories");
            File f = new File("C:\\Users\\Matthew Gerber\\Documents\\fix-meme\\market\\src\\main\\java\\com\\fixme\\market\\markets\\Inventories\\crypto.txt");
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                String[] item = data.split(",");
                instruments.add(new Instrument(item[0], item[1]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
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
