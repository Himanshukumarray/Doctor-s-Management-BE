package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.entity.DoctorSearch;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DoctorSolrService {

    @Autowired
    private SolrClient solrClient;

    public String addDoctor(DoctorSearch doctor) throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", doctor.getId());
        doc.addField("name", doctor.getName());
        doc.addField("specialization", doctor.getSpecialization());

        solrClient.add(doc);
        solrClient.commit();

        return "Indexed Successfully!";
    }

    public List<DoctorSearch> searchDoctors(String keyword) throws Exception {

        SolrQuery query = new SolrQuery();
        query.setQuery("name:*" + keyword + "* OR specialization:*" + keyword + "*");

        QueryResponse response = solrClient.query(query);

        return response.getResults().stream().map(doc ->
                new DoctorSearch(
                        getSingleValue(doc.getFieldValues("id")),
                        getSingleValue(doc.getFieldValues("name")),
                        getSingleValue(doc.getFieldValues("specialization"))
                )
        ).toList();
    }

    private String getSingleValue(Collection<Object> values) {
        if (values == null) return null;
        if (values.size() > 0) {
            return values.stream().findFirst().get().toString();
        }
        return null;
    }

}
