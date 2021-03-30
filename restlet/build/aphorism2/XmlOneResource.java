package aphorism2;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.List;

public class XmlOneResource extends ServerResource {
    public XmlOneResource() { }

    @Get
    public Representation toXml() {
	// Extract the friend's id.
	String sid = (String) getRequest().getAttributes().get("id");
	if (sid == null) return badRequest("No ID provided\n");

	int id;
	try {
	    id = Integer.parseInt(sid.trim());
	}
	catch(Exception e) { return badRequest("No such ID\n"); }

	// Search for the Friend.
	List<Doctor> list = Doctors.getList();
	Doctor doctor = Doctors.find(id);
	if (doctor == null) return badRequest("No doctor with ID " + id + "\n");

	// Generate the XML response.
	DomRepresentation dom = null;  
        try {  
            dom = new DomRepresentation(MediaType.TEXT_XML);  
	    dom.setIndenting(true);
            Document doc = dom.getDocument();  
  
            Element root = doc.createElement("doctor");  
	    root.appendChild(doc.createTextNode(doctor.toString()));
	    doc.appendChild(root);
	}
	catch(Exception e) { }
	return dom;
    }

    private StringRepresentation badRequest(String msg) {
	Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
	return new StringRepresentation(error.toString());
    }
}

