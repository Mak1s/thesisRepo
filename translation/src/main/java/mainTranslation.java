import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class mainTranslation {

    public static class JsonData {
        private String classBefore;
        private String classAfter;
        private String propertyBefore;
        private String propertyAfter;
        private String additionalClass;

        // Getters and setters
        public String getClassBefore() {
            return classBefore;
        }

        public void setClassBefore(String classBefore) {
            this.classBefore = classBefore;
        }

        public String getClassAfter() {
            return classAfter;
        }

        public void setClassAfter(String classAfter) {
            this.classAfter = classAfter;
        }

        public String getPropertyBefore() {
            return propertyBefore;
        }

        public void setPropertyBefore(String propertyBefore) {
            this.propertyBefore = propertyBefore;
        }

        public String getPropertyAfter() {
            return propertyAfter;
        }

        public void setPropertyAfter(String propertyAfter) {
            this.propertyAfter = propertyAfter;
        }

        public String getAdditionalClass() {
            return additionalClass;
        }

        public void setAdditionalClass(String additionalClass) {
            this.additionalClass = additionalClass;
        }

        @Override
        public String toString() {
            return "JsonData{" +
                    "classBefore='" + classBefore + '\'' +
                    ", classAfter='" + classAfter + '\'' +
                    ", propertyBefore='" + propertyBefore + '\'' +
                    ", propertyAfter='" + propertyAfter + '\'' +
                    ", additionalClass='" + additionalClass + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        File inputFile = new File("reflection.txt");
        File inputFile1 = new File("changess.json");
        File outputFile = new File("reflection_out.txt");

        Gson gson = new Gson();

        // Check if the input files exist
        if (!inputFile.exists() || !inputFile1.exists()) {
            System.out.println("File not found: " + inputFile.getAbsolutePath() + " or " + inputFile1.getAbsolutePath());
            return;
        }

        String fileX3ML = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            JsonData[] dataArray;
            try (BufferedReader reader2 = new BufferedReader(new FileReader(inputFile1))) {
                dataArray = gson.fromJson(reader2, JsonData[].class);
            }

           
            while ((line = reader.readLine()) != null) {
                fileX3ML += line + "\n";

                
                for (JsonData data : dataArray) {
                    if (line.contains(data.getClassBefore())) {
                      
                        line = line.replace(data.getClassBefore(), data.getClassAfter());
                    }else if(line.contains(data.getPropertyBefore())){
                        line = line.replace(data.getPropertyBefore(), data.getPropertyAfter());
                    }
                }

                fileContent.append(line).append("\n");
            }


            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(fileContent.toString());
                System.out.println("File updated successfully.");
            }

        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }
}
