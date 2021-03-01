// Class to represent a collection of numbers

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Dataset {

  // private field to represent array of data
  ArrayList<Double> data = new ArrayList<>();

  public Dataset (String filename) throws IOException {
    Path path = Paths.get(filename);
    BufferedReader reader = null;
    try {
      reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
      String text = null;
      while ((text = reader.readLine()) != null) {
        data.add(Double.parseDouble(text));
      } 
    } finally {
      if (reader != null) reader.close();
    }
  }

  /**
   * @return Length of the data set
   */
  public int size() {
    return data.size();
  }

  /**
   * @return Average of data set
   */
  public double meanValue () {
    if (size() > 0) {
      Double sum = 0.0;
      if(!data.isEmpty()) {
        for (Double value : data) {
            sum += value;
        }
        return sum / data.size();
      }
      return sum;
    } else {
      throw new ArithmeticException("Error: there are no stored values.");
    }
  }
} // end of class Dataset