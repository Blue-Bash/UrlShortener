package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import urlshortener.demo.controller.QrApi;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.utils.StringChecker;

import javax.servlet.http.HttpServletRequest;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
public class QrApiController implements QrApi {

    private static final Logger log = LoggerFactory.getLogger(QrApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final QRRepository qrRepository;
    
    private final URIRepository uriRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public QrApiController(ObjectMapper objectMapper, HttpServletRequest request, QRRepository qrRepository, URIRepository uriRepository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.qrRepository = qrRepository;
        this.uriRepository = uriRepository;
    }

    public ResponseEntity<QRItem> getQR(@ApiParam(value = "",required=true) @PathVariable("id") String id) {

        String accept = request.getHeader("Accept");
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        
        QRItem qr;

        // Check that width and heigth params are not null or negative
        // and return int
        int w = StringChecker.checkString2Int(width);
        int h = StringChecker.checkString2Int(height);
        
        // If uri is not in the URIRepository return 404
        if (!this.uriRepository.contains(id)){
            return new ResponseEntity<QRItem>(HttpStatus.NOT_FOUND);
        }

        // Default qr is required, so it's saved if the uri is shorthed
        if(w==500 && h==500){    
            URIItem uriItem = this.uriRepository.get(id);
            qr = this.qrRepository.get(uriItem.getRedirection());
        }else{
            URIItem uriItem = this.uriRepository.get(id);
            qr = new QRItem();
            qr.setUri(uriItem.getRedirection());
            qr.convertBase64(w, h);
        }

        return new ResponseEntity<QRItem>(qr, HttpStatus.OK);
    }

}
