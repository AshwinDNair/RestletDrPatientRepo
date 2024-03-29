package doctor_patient;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlainResourceDoctor extends ServerResource { //to represent all doctors in plain text format

CopyOnWriteArrayList<Doctor> doctors;

    public PlainResourceDoctor() { }
    @Get
    public Representation toPlain() {
        String output="";
      doctors = Doctors.getList();
	for (Doctor a : doctors) {
        output=output+a.getId()+" : "+a.getName()+"==> Patient Count : "+a.getPatientCount()+"\n";
	}	
    
	setStatus(Status.SUCCESS_OK);
	return new StringRepresentation(output, MediaType.TEXT_PLAIN);
    }

   
}
