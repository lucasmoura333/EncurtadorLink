package dev.moura.EncurtadorLink.Links;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LinkRepository extends JpaRepository <Link, Long>{



    //@Query("SELECT l FROM Link l WHERE l.urlLong = :UrlLong")
    //Link findByurlLong(String urlEncurtada);
    //Link findByUrlOriginal(@Param("UrlLong") String urlEncurtada);

    //Teve que ser feito essa adaptação abaixo por que JPA tava biruleibe das ideia

    @Query("SELECT l FROM Link l WHERE l.urlEncurtada = :urlEncurtada")
    Link findByUrlEncurtada(@Param("urlEncurtada") String urlEncurtada);


}
