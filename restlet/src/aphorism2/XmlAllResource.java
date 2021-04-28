package aphorism2;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.ext.xml.DomRepresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.*;
import java.io.IOException;

public class XmlAllResource extends ServerResource {
	private static Logger logger = Logger.getLogger(XmlAllResource.class.getName());

	public XmlAllResource() {
	}


	@Get
	public Representation toXml() throws Exception {

		CopyOnWriteArrayList<Patient> patients;
		patients = Patients.getList();

		DomRepresentation dom = null;
		try {
			dom = new DomRepresentation(MediaType.TEXT_XML);
			dom.setIndenting(true);
			Document doc = dom.getDocument();
			Element root = DoctorPatientUtil.getAllXml(doc,false);
			// root.appendChild(root1);
			doc.appendChild(root);
		} catch (Exception e) {
			throw e;
		}
		return dom;
	}


}
