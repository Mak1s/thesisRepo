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
    public static void runTranslationApp(String inputFilePath, String jsonFilePath) {
        File inputFile = new File(inputFilePath);
        File jsonFile = new File(jsonFilePath);
        File outputFile = new File("newX3ML_out.x3ml");

        Gson gson = new Gson();

        if (!inputFile.exists() || !jsonFile.exists()) {
            System.out.println("File not found: " + inputFile.getAbsolutePath() + " or " + jsonFile.getAbsolutePath());
            return;
        }
                try {
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }

            JsonData[] dataArray;
            try (BufferedReader reader2 = new BufferedReader(new FileReader(jsonFile))) {
                dataArray = gson.fromJson(reader2, JsonData[].class);
            }

            String fullFileContent = fileContent.toString();

            for (JsonData data : dataArray) {
                System.out.println("Processing classBefore: " + data.getClassBefore());
                System.out.println("Processing propertyBefore: " + data.getPropertyBefore());
                fullFileContent = fullFileContent.replace(data.getClassBefore(), data.getClassAfter());
                fullFileContent = fullFileContent.replace(data.getPropertyBefore(), data.getPropertyAfter());
                System.out.println("Changed to classAfter: "+ data.getClassAfter());
                System.out.println("Changed to propertyAfter: "+ data.getPropertyAfter());
                if (data.getAdditionalClass() != null && !data.getAdditionalClass().isEmpty()) {
                    String specificString = "<entity>\n" +
                            "                     <type>crm:" + data.getClassAfter() + "</type>\n" +
                            "                     <instance_generator name=\"LocalTermURI\">\n" +
                            "                        <arg name=\"hierarchy\" type=\"constant\">person</arg>\n" +
                            "                        <arg name=\"term\" type=\"xpath\">../creator_viaf/text()</arg>\n" +
                            "                     </instance_generator>\n" +
                            "                     <label_generator name=\"CompositeLabel\">\n" +
                            "                        <arg name=\"term1\" type=\"xpath\">text()</arg>\n" +
                            "                        <arg name=\"term2\" type=\"xpath\">../creator_lname/text()</arg>\n" +
                            "                     </label_generator>\n" +
                            "                  </entity>";

                    String replacementString = "<entity>\n" +
                            "                     <type>crm:" + data.getClassAfter() + "</type>\n" +
                            "                     <instance_generator name=\"LocalTermURI\">\n" +
                            "                        <arg name=\"hierarchy\" type=\"constant\">person</arg>\n" +
                            "                        <arg name=\"term\" type=\"xpath\">../creator_viaf/text()</arg>\n" +
                            "                     </instance_generator>\n" +
                            "                     <label_generator name=\"CompositeLabel\">\n" +
                            "                        <arg name=\"term1\" type=\"xpath\">text()</arg>\n" +
                            "                        <arg name=\"term2\" type=\"xpath\">../creator_lname/text()</arg>\n" +
                            "                     </label_generator>\n" +
                            "                  </entity>" +
                            "                   <additional>\n" +
                            "                        <entity>\n" +
                            "                           <type>crm:" + data.getAdditionalClass() + "</type>\n" +
                            "                        </entity>\n" +
                            "                     </additional>";
                    if (fullFileContent.contains(specificString)) {
                        fullFileContent = fullFileContent.replace(specificString, replacementString);
                        System.out.println("Multiline specific string replaced for class: " + data.getClassAfter());
                    }
                }
            }
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(fullFileContent);
                System.out.println("File updated successfully.");
            }
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    

    }
    public static void main(String[] args) {

        File inputFile = new File("reflection.txt");
        File jsonFile = new File("changess.json");
        File outputFile = new File("reflection_out.txt");
        Gson gson = new Gson();

        if (!inputFile.exists() || !jsonFile.exists()) {
            System.out.println("File not found: " + inputFile.getAbsolutePath() + " or " + jsonFile.getAbsolutePath());
            return;
        }

        try {
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }

            JsonData[] dataArray;
            try (BufferedReader reader2 = new BufferedReader(new FileReader(jsonFile))) {
                dataArray = gson.fromJson(reader2, JsonData[].class);
            }

            String fullFileContent = fileContent.toString();

            for (JsonData data : dataArray) {
                System.out.println("Processing classBefore: " + data.getClassBefore());
                System.out.println("Processing propertyBefore: " + data.getPropertyBefore());
                fullFileContent = fullFileContent.replace(data.getClassBefore(), data.getClassAfter());
                fullFileContent = fullFileContent.replace(data.getPropertyBefore(), data.getPropertyAfter());
                System.out.println("Changed to classAfter: "+ data.getClassAfter());
                System.out.println("Changed to propertyAfter: "+ data.getPropertyAfter());
                if (data.getAdditionalClass() != null && !data.getAdditionalClass().isEmpty()) {
                    String specificString = "<entity>\n" +
                            "                     <type>crm:" + data.getClassAfter() + "</type>\n" +
                            "                     <instance_generator name=\"LocalTermURI\">\n" +
                            "                        <arg name=\"hierarchy\" type=\"constant\">person</arg>\n" +
                            "                        <arg name=\"term\" type=\"xpath\">../creator_viaf/text()</arg>\n" +
                            "                     </instance_generator>\n" +
                            "                     <label_generator name=\"CompositeLabel\">\n" +
                            "                        <arg name=\"term1\" type=\"xpath\">text()</arg>\n" +
                            "                        <arg name=\"term2\" type=\"xpath\">../creator_lname/text()</arg>\n" +
                            "                     </label_generator>\n" +
                            "                  </entity>";

                    String replacementString = "<entity>\n" +
                            "                     <type>crm:" + data.getClassAfter() + "</type>\n" +
                            "                     <instance_generator name=\"LocalTermURI\">\n" +
                            "                        <arg name=\"hierarchy\" type=\"constant\">person</arg>\n" +
                            "                        <arg name=\"term\" type=\"xpath\">../creator_viaf/text()</arg>\n" +
                            "                     </instance_generator>\n" +
                            "                     <label_generator name=\"CompositeLabel\">\n" +
                            "                        <arg name=\"term1\" type=\"xpath\">text()</arg>\n" +
                            "                        <arg name=\"term2\" type=\"xpath\">../creator_lname/text()</arg>\n" +
                            "                     </label_generator>\n" +
                            "                  </entity>" +
                            "                   <additional>\n" +
                            "                        <entity>\n" +
                            "                           <type>crm:" + data.getAdditionalClass() + "</type>\n" +
                            "                        </entity>\n" +
                            "                     </additional>";
                    if (fullFileContent.contains(specificString)) {
                        fullFileContent = fullFileContent.replace(specificString, replacementString);
                        System.out.println("Multiline specific string replaced for class: " + data.getClassAfter());
                    }
                }
            }
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(fullFileContent);
                System.out.println("File updated successfully.");
            }
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }
}