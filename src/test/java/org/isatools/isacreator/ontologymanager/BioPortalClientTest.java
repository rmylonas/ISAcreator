/**
 ISAcreator is a component of the ISA software suite (http://www.isa-tools.org)

 License:
 ISAcreator is licensed under the Common Public Attribution License version 1.0 (CPAL)

 EXHIBIT A. CPAL version 1.0
 �The contents of this file are subject to the CPAL version 1.0 (the �License�);
 you may not use this file except in compliance with the License. You may obtain a
 copy of the License at http://isa-tools.org/licenses/ISAcreator-license.html.
 The License is based on the Mozilla Public License version 1.1 but Sections
 14 and 15 have been added to cover use of software over a computer network and
 provide for limited attribution for the Original Developer. In addition, Exhibit
 A has been modified to be consistent with Exhibit B.

 Software distributed under the License is distributed on an �AS IS� basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 the specific language governing rights and limitations under the License.

 The Original Code is ISAcreator.
 The Original Developer is the Initial Developer. The Initial Developer of the
 Original Code is the ISA Team (Eamonn Maguire, eamonnmag@gmail.com;
 Philippe Rocca-Serra, proccaserra@gmail.com; Susanna-Assunta Sansone, sa.sanson@gmail.com;
 http://www.isa-tools.org). All portions of the code written by the ISA Team are
 Copyright (c) 2007-2011 ISA Team. All Rights Reserved.

 EXHIBIT B. Attribution Information
 Attribution Copyright Notice: Copyright (c) 2008-2011 ISA Team
 Attribution Phrase: Developed by the ISA Team
 Attribution URL: http://www.isa-tools.org
 Graphic Image provided in the Covered Code as file: http://isa-tools.org/licenses/icons/poweredByISAtools.png
 Display of Attribution Information is required in Larger Works which are defined in the CPAL as a work which combines Covered Code or portions thereof with code not governed by the terms of the CPAL.

 Sponsors:
 The ISA Team and the ISA software suite have been funded by the EU Carcinogenomics project (http://www.carcinogenomics.eu), the UK BBSRC (http://www.bbsrc.ac.uk), the UK NERC-NEBC (http://nebc.nerc.ac.uk) and in part by the EU NuGO consortium (http://www.nugo.org/everyone).
 */

package org.isatools.isacreator.ontologymanager;

import org.isatools.isacreator.configuration.Ontology;
import org.isatools.isacreator.ontologymanager.common.OntologyTerm;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: Dec 15, 2010
 *         Time: 1:52:52 PM
 */
public class BioPortalClientTest {

    private static BioPortalClient client = new BioPortalClient();
    public static String testOntologyID = "1136";
    public static String testTermAccession = "421563008";
    public static String testOntologyVersion = "45155";
    public static String testSearchTerm = "dose";

    @Test
    public void getAllOntologies() {
        System.out.println("_____Testing getAllOntologies()____");

        List<Ontology> ontologies = client.getAllOntologies();

        assertTrue("Oh no! No returned ontologies (empty result)! ", ontologies.size() > 0);

        System.out.println("Found " + ontologies.size() + " ontologies \n");
        for (Ontology ontology : ontologies) {
            System.out.println(ontology.getOntologyAbbreviation() + " -> " + ontology.getOntologyDisplayLabel() + " -> " + ontology.getOntologyVersion());
        }
    }

    @Test
    public void getOntologyById() {
        System.out.println("_____Testing getOntologyById()____");

        Ontology ontology = client.getOntologyById(testOntologyID);

        assertTrue("No ontology found for " + testOntologyID, ontology != null);

        System.out.println("Ontology found -> " + ontology.toString() + "\n");
    }

    @Test
    public void getLatestOntologyVersion() {
        System.out.println("_____Testing getLatestOntologyVersion()____");

        String ontologyVersion = client.getLatestOntologyVersion(testOntologyID);

        assertTrue("Latest ontology version not found for " + testOntologyID, ontologyVersion != null);

        System.out.println("Latest ontology version found for " + testOntologyID + " -> " + ontologyVersion + "\n");
    }

    @Test
    public void getTermsByPartialNameFromSource() {
        System.out.println("_____Testing getTermsByPartialNameFromSource()____");

        Map<OntologySourceRefObject, List<OntologyTerm>> result = client.getTermsByPartialNameFromSource(testSearchTerm, testOntologyID, false);

        assertTrue("No results found for " + testSearchTerm + " in " + testOntologyID, result.size() > 0);

        for (OntologySourceRefObject source : result.keySet()) {
            System.out.println(source.getSourceName()  + " (" + source.getSourceVersion() + ")");

            for(OntologyTerm term : result.get(source)) {
                System.out.println("\t" + term.getOntologyTermName() + " (" + term.getOntologySourceAccession() + ")");
            }
        }

        System.out.println("Found " + result.values().size() + " matches...\n");
    }

    @Test
    public void getOntologyRoots() {
        Map<String, OntologyTerm> ontologyRoots = client.getOntologyRoots(testOntologyVersion);

        assertTrue("No ontology roots found for " + testOntologyID, ontologyRoots.size() > 0);

        System.out.println("Found " + ontologyRoots.size() + " roots for " + testOntologyID);
    }


}
