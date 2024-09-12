package dev.moura.EncurtadorLink.Links;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class LinkController {

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    //Será trabalhado com o LinkResponse e não a Entidade/Model "Link.java"
    @PostMapping("/encurta-ai")
    public ResponseEntity<LinkResponse> gerarUrlEncurtada(@RequestBody Map<String, String> request){

        String urlOriginal = request.get("urlOriginal");
        Link link = linkService.encurtarUrl(urlOriginal);

        String gerarUrlDeRedicerionamentoDoUsuario = "http://localhost:8080/r/" + link.getUrlEncurtada();

        LinkResponse response = new LinkResponse(
          link.getId(),
          link.getUrlLong(),
          gerarUrlDeRedicerionamentoDoUsuario,
          link.getUrlQrCode(),
          link.getUrlCriadaEm()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/r/{urlEncurtada}")
    public void redirecionaLink(@PathVariable String urlEncurtada, HttpServletResponse response) throws IOException {

        Link link = linkService.obterUrlOriginal(urlEncurtada);

        if (link != null && link.getUrlLong() != null && !link.getUrlLong().isEmpty()
        ) {
            response.sendRedirect(link.getUrlLong());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Link não encontrado. @@@@@@ corno@@@@@");
        }
    }

}
