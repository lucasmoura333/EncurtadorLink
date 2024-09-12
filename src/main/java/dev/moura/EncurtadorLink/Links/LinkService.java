package dev.moura.EncurtadorLink.Links;


//Lógica da aplicação

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkService {

private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    //gera a url aleatoria usando repository do maven, observar a versão.

    //TODO: REFATORAR PARA INCLUIR PARTE DA URL ORIGINAL NO NOSSO ALGORITIMO DE GERAÇÃO DE URL.
    public String gerarUrlAleatoria(){
        return RandomStringUtils.randomAlphabetic(5, 10);
    }

    public Link encurtarUrl(String urlOriginal){
        Link link = new Link();
        link.setUrlLong(urlOriginal);
        link.setUrlEncurtada(gerarUrlAleatoria());
        link.setUrlCriadaEm(LocalDateTime.now());
        link.setUrlQrCode("QR Indisponivel");

        return linkRepository.save(link);
    }

    public Link obterUrlOriginal(String urlEncurtada){

        try {
              //return linkRepository.findByurlLong(urlEncurtada);
                return linkRepository.findByUrlEncurtada(urlEncurtada);

            } catch (Exception erro){
              throw new RuntimeException("Url não existe no banco de dados", erro);
            }

    }
}
