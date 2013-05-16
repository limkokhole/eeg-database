package cz.zcu.kiv.eegdatabase.data.indexing;

import cz.zcu.kiv.eegdatabase.data.pojo.Article;
import cz.zcu.kiv.eegdatabase.logic.controller.searchsolr.FullTextSearchUtils;
import cz.zcu.kiv.eegdatabase.logic.controller.searchsolr.ResultCategory;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.social.linkedin.api.Post;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jan Koren
 * Date: 8.3.13
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
public class LinkedInIndexer extends Indexer<Post> {

    @Override
    public void unindex(Post post) throws IOException, SolrServerException {
        solrServer.deleteById(post.getId());
        solrServer.commit();
    }

    public void unindexAll() throws IOException, SolrServerException {
        solrServer.deleteByQuery("source:linkedin");
        solrServer.commit();
    }

    @Override
    public SolrInputDocument prepareForIndexing(Post post) throws IllegalAccessException, IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField(IndexField.UUID.getValue(), post.getId());
        document.addField(IndexField.ID.getValue(), 1);
        document.addField(IndexField.TITLE.getValue(), post.getTitle());
        document.addField(IndexField.TEXT.getValue(), post.getSummary());
        document.addField(IndexField.CLASS.getValue(), ResultCategory.ARTICLE.getValue());
        document.addField(IndexField.SOURCE.getValue(), IndexingUtils.SOURCE_LINKEDIN);
        return document;
    }

    /*
    public void indexAll(List<Post> posts) {

    }
    */
}
