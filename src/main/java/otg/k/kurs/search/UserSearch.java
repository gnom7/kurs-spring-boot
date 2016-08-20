package otg.k.kurs.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserSearch {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> search(String text){
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory().
                        buildQueryBuilder().forEntity(User.class).get();

        Query query =
                queryBuilder
                        .keyword()
                        .onFields("username", "firstname", "lastname",
                                  "siteHolders.sites.siteName", "comments.comment")
                        .matching(text)
                        .createQuery();

        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, User.class);

        @SuppressWarnings("unchecked")
        List<User> results = jpaQuery.getResultList();

        return results;
    }

}
