package game.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CsvReader {
	
    public static String[][] ReadFile(String FilePath) {
        String line;
        String cvsSplitBy = ",";

        String[][] matrix = new String[38][22];

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {

            int row = 0;
            while ((line = br.readLine()) != null && row < matrix.length) {

                // utiliser la virgule comme séparateur
                String[] values = line.split(cvsSplitBy);

                // remplir la matrice avec les valeurs du fichier CSV
                for (int col = 0; col < matrix[0].length && col < values.length; col++) {
                    matrix[row][col] = values[col];
                }

                row++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
