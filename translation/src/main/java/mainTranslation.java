import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

public class mainTranslation {

    public class JsonData {
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
        Gson gson = new Gson();
        File inputFile1 = new File("changess.json");
        // Check if the file exists
        if (!inputFile.exists()) {
            System.out.println("File not found: " + inputFile.getAbsolutePath());
            return;
        }

        String specificClass = "<type>";
        String specificProperty ="<relationship>";
        String classes="";
        String properties="";
        String fileX3ML="";
        String fileJSON="";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
           
            while ((line = reader.readLine()) != null) {
                fileX3ML+=line;
                if (line.trim().startsWith(specificClass)) {
                    classes+=line;
                } else if (line.trim().startsWith(specificProperty)) {
                    properties+=line;
                }
            }
            System.out.println("classes = :"+classes);
            System.out.println("properties = :"+properties);
            System.out.println(fileX3ML);
            
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
        }
        
        try(BufferedReader reader2 = new BufferedReader(new FileReader(inputFile1))){
            JsonData[] dataArray = gson.fromJson(reader2, JsonData[].class);

            for (JsonData data : dataArray) {
                System.out.println(data);
            }
            System.out.println(dataArray[0].getClassBefore());
            
        } catch (IOException e){
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }
}
