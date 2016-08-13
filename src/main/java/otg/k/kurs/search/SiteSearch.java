package otg.k.kurs.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import otg.k.kurs.domain.Site;

import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class SiteSearch {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Site> search(String text){
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory().
                buildQueryBuilder().forEntity(Site.class).get();

        Query query =
                queryBuilder
                    .keyword()
                    .onFields("siteName", "theme", "user.username",
                            "texts.markdownText", "comments.comment", "comments.user.username")
                    .matching(text)
                    .createQuery();

        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Site.class);

        @SuppressWarnings("unchecked")
        List<Site> results = jpaQuery.getResultList();

        return results;
    }

}
