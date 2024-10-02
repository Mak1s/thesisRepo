package mainClasses;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
//import org.apache.jena.sparql.engine.main.StageGenerator;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class ModelLoader {
    private OntModel model;

    public ModelLoader(String rdfsFilePath){
        this.model= ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
        FileManager.get().readModel(this.model,rdfsFilePath);
    }

    public Collection<String> listClasses(){
        ExtendedIterator<OntClass> classIter=this.model.listClasses();
        Set<String> classSet=new HashSet<>();
        while(classIter.hasNext())
            classSet.add(classIter.next().getURI());
        return classSet;
    }
    
    public Map<String,String> listClassesMap(){
        ExtendedIterator<OntClass> classIter=this.model.listClasses();
        Map<String,String> classMap=new TreeMap<>();
        while(classIter.hasNext()){
            OntClass nextClass=classIter.next();
            classMap.put(nextClass.getLocalName(), nextClass.getNameSpace());
        }
        return classMap;
    }

    public Collection<String> listProperties(){
        ExtendedIterator<OntProperty> propIter=this.model.listOntProperties();
        Set<String> propertySet=new HashSet<>();
        while(propIter.hasNext())
            propertySet.add(propIter.next().getURI());
        return propertySet;
    }
    
    public Map<String,String> listPropertiesMap(){
        ExtendedIterator<OntProperty> propIter=this.model.listOntProperties();
        Map<String,String> propertyMap=new TreeMap<>();
        while(propIter.hasNext()){
            OntProperty nextProperty=propIter.next();
            propertyMap.put(nextProperty.getLocalName(), nextProperty.getNameSpace());
        }
        return propertyMap;
    }

    public static void main(String []args){
        ModelLoader modelLoader=new ModelLoader("C:\\Users\\gerry\\Documents\\NetBeansProjects\\thesisProject\\src\\main\\java\\mainClasses\\CIDOC_CRM_v7.1.3.rdf");
//        ModelLoader modelLoader=new ModelLoader("foaf.rdf");
        
//        modelLoader.listClasses().forEach( c -> System.out.println(c));
//        modelLoader.listProperties().forEach( p -> System.out.println(p));

        Map<String,String> classesMap=modelLoader.listClassesMap();
        for(String className : classesMap.keySet()) {
            System.out.println(className+"\t"+classesMap.get(className));
        }
        Map<String,String> propertiesMap=modelLoader.listPropertiesMap();
        for(String propName : propertiesMap.keySet()) {
            System.out.println(propName+"\t"+propertiesMap.get(propName));
        }
    }
}
  