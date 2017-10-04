package dmterkula.selfstudy.graphs.graphImpl.setup;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by David Terkula on 5/21/2017.
 */
public class TextFileReader {

    private String fileName;

    private ArrayList<String []> inputLines;


    public TextFileReader(String fileName){
        this.fileName = fileName;
        inputLines = new ArrayList<String[]>();
    }

    public void readFile(){
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {

            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            String currentLine;

            bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((currentLine = bufferedReader.readLine()) != null) {
                for(int i = 0; i < currentLine.length()-1; i ++){
                    if(currentLine.charAt(i)== ' '){
                        currentLine = currentLine.substring(0, i) + "+" + currentLine.substring(i+1);
                    }
                }
                String [] line = currentLine.split(":");
                inputLines.add(line);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

    }

    public ArrayList<String[]> getInputLines() {
        return inputLines;
    }

    public void setInputLines(ArrayList<String[]> inputLines) {
        this.inputLines = inputLines;
    }
}
