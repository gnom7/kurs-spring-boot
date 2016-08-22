package otg.k.kurs.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import otg.k.kurs.dto.SiteHolderDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString(exclude = "user")
@Entity
@Table(name = "site_holders")
public class SiteHolder {

    @Id
    @GeneratedValue
    private long id;

    @Field
    @Column(unique = true)
    private String siteHolderName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "siteHolder")
    @IndexedEmbedded
    private List<Site> sites;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public SiteHolder() {
        this.sites = new ArrayList<>();
    }

    public SiteHolder(SiteHolderDto siteHolderDto) {
        this.siteHolderName = siteHolderDto.getSiteHolderName();
        this.sites = new ArrayList<>(siteHolderDto.getSites().size());
        this.id = siteHolderDto.getId();
        this.sites = new ArrayList<>(siteHolderDto.getSites().size());
    }
}
