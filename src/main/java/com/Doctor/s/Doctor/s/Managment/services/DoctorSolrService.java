package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.entity.DoctorSearch;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorSolrService {

    @Autowired
    private SolrClient solrClient;

    private final String CORE_NAME = "doctors";

    public String addDoctor(DoctorSearch dto) throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", dto.getId());
        doc.addField("name", dto.getName());
        doc.addField("specialization", dto.getSpecialization());
        doc.addField("keywords", dto.getKeywords());

        solrClient.add(CORE_NAME, doc);
        solrClient.commit(CORE_NAME);
        return "Doctor Indexed Successfully!";
    }

    public List<DoctorSearch> searchDoctors(String keyword) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setRequestHandler("/select");
        query.set("defType", "edismax");
        query.set("q", keyword);
        query.set("qf", "specialization^4 keywords^3 name^2");
        query.set("mm", "1");

        QueryResponse response = solrClient.query(CORE_NAME, query);
        return response.getBeans(DoctorSearch.class);
    }
}
