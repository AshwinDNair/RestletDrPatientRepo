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
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class DoctorPatientUtil {
    private static CopyOnWriteArrayList<Doctor> doctors = Doctors.getList();
    private static CopyOnWriteArrayList<Patient> patients = Patients.getList();
    private static Element root1;

    public DoctorPatientUtil() {
    }

    public void setDocterPatientList() {

    }

    public static Element getOneXml(Doctor d, Document doc) {
        try {
            Element root = doc.createElement("doctor");
            Element rootDocName = doc.createElement("name");
            rootDocName.appendChild(doc.createTextNode(d.getName()));
            root.appendChild(rootDocName);
            Element rootPatients = doc.createElement("patients");
            for (Patient p : patients) {
                if (d.getId() == p.getDoctorId()) {
                    Element rootNewPatient = doc.createElement("patient");
                    Element rootPatientName = doc.createElement("name");
                    Element rootInsuranceNo = doc.createElement("insuranceNo");
                    rootPatientName.appendChild(doc.createTextNode(p.getName()));
                    rootInsuranceNo.appendChild(doc.createTextNode(p.getInsuranceNumber()));
                    rootNewPatient.appendChild(rootPatientName);
                    rootNewPatient.appendChild(rootInsuranceNo);
                    rootPatients.appendChild(rootNewPatient);

                }

            }
            root.appendChild(rootPatients);
            root1 = root;
            return root;

        } catch (Exception e) {
        }
        return root1;
    }

    public static Element getAllXml(Document doc) {
        try {
            Element doctorsRoot = doc.createElement("doctors");
            for (Doctor d : doctors) {

                doctorsRoot.appendChild(getOneXml(d, doc));
            }

            root1 = doctorsRoot;
            return doctorsRoot;

        } catch (Exception e) {
        }
        return root1;
    }

    public static void writeDoctorFile() {
        String output = null;
        File file = new File(
                "/Users/ashwinnair/Desktop/University Of Illinois Springfield/Web Services/TermProject/RestletDrPatientRepo/restlet/src/aphorism2/drs.db");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Doctor d : Doctors.getList()) {
                output = d.getId() + "!" + d.getName() + "!" + d.getPatientCount() + "\n";
                bw.write(output);
            }
            bw.close();
        } catch (Exception e) {

        }
    }
        public static void writePatientFile() {
            String output = null;
            File file = new File(
                    "/Users/ashwinnair/Desktop/University Of Illinois Springfield/Web Services/TermProject/RestletDrPatientRepo/restlet/src/aphorism2/patients.db");
            try {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                for (Patient p : Patients.getList()) {

                    output = p.getId() + "!" + p.getName() + "!" + p.getInsuranceNumber() + "!"+p.getDoctorId()+"\n";
                    bw.write(output);
                }
                bw.close();
            } catch (Exception e) {
    
            }
    }

}