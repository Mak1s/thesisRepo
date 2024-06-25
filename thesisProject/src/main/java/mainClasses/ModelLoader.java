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
import java.util.Set;

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

    public Collection<String> listProperties(){
        ExtendedIterator<OntProperty> propIter=this.model.listOntProperties();
        Set<String> propertySet=new HashSet<>();
        while(propIter.hasNext())
            propertySet.add(propIter.next().getURI());
        return propertySet;
    }

    public static void main(String []args){
        ModelLoader modelLoader=new ModelLoader("CIDOC_CRM_v7.1.1.rdfs");
        modelLoader.listClasses().forEach( c -> System.out.println(c));
        modelLoader.listProperties().forEach( p -> System.out.println(p));
    }
}