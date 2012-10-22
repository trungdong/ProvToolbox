package org.openprovenance.prov.notation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


import org.openprovenance.prov.xml.Document;
import org.openprovenance.prov.xml.Statement;
import org.openprovenance.prov.xml.UncheckedTestException;


public class RoundTripFromJavaTest extends org.openprovenance.prov.xml.RoundTripFromJavaTest {
    final Utility u=new Utility();

    public RoundTripFromJavaTest(String name) {
	super(name);
    }
    
    public String extension() {
	return ".provn";
    }  
    
    public boolean checkTest(String name) {
	return !(name.contains("member2") 
		|| name.contains("member3"));
    }
    
    public void writeDocument(Document doc, String file) {
        String s=u.convertBeanToASN(doc);
        writeTextToFile(s, file);
    }
    public Document readDocument(String file1) {

        try {
            return (Document) u.convertASNToJavaBean(file1);
        } catch (IOException e) {
           throw new UncheckedTestException(e);
        } catch (Throwable e) {
            throw new UncheckedTestException(e);
        }
    }

    
        

    /*
    public void makeDocAndTestOLD(Statement statement, String file, Statement [] opt, boolean check) {
	Document doc = pFactory.newDocument();
	doc.getEntityOrActivityOrWasGeneratedBy().add(statement);
	updateNamespaces(doc);
	
	check= check && checkTest(file);
	
	
	String file1=(opt==null) ? file : file+"-S";
	file1=file1+extension();
	
	String s=u.convertBeanToASN(doc);
	writeTextToFile(s, file1);
	
	Document doc2;
	try {
	    doc2 = (Document) u.convertASNToJavaBean(file1);
	    compareDocuments(doc, doc2, check && checkTest(file1));
	    
	    if (opt!=null) {
		doc.getEntityOrActivityOrWasGeneratedBy().addAll(Arrays.asList(opt));
		String file2=file+"-M";
		file2=file2+extension();
		
		String s2=u.convertBeanToASN(doc);
		writeTextToFile(s2, file2);
		
		Document doc3=(Document) u.convertASNToJavaBean(file2);
		compareDocuments(doc, doc3, check && checkTest(file2));
	    }

	    return;
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Throwable e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	assertTrue(false);
	

    }
    */

    public void writeTextToFile(String text,
                                String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(text);
        }
        catch (IOException e) {
        }
        finally {
            try {
                if (writer != null)
                    writer.close( );
            }
            catch (IOException e) {}
        }
    }
    

 }