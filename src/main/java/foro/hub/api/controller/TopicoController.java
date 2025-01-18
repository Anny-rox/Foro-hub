package foro.hub.api.controller;

import foro.hub.api.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

  @Autowired
  TopicoRepository topicoRepository;

  @PostMapping
  public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
    Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
    DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(
        topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFecha(), topico.getAutor(), topico.getCurso());
    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaTopico);

  }

  @GetMapping
  public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable paginacion) {
      return ResponseEntity.ok(topicoRepository.findAll(paginacion)
          .map(DatosListadoTopico::new));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity actualizarTopico(
      @PathVariable Long id,
      @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

    Optional<Topico> topicoOpcional = topicoRepository.findById(id);

    if (topicoOpcional.isPresent()) {
      Topico topico = topicoOpcional.get();
      topico.actualizarDatos(datosActualizarTopico);
      return ResponseEntity.ok(new DatosRespuestaTopico(
          topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFecha(), topico.getAutor(), topico.getCurso()));//200
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> eliminar(@PathVariable Long id){
      Optional<Topico> topico = topicoRepository.findById(id);

      if (topico.isPresent()) {
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();//204
      } else {
        return ResponseEntity.notFound().build();
      }
  }

  @GetMapping("/{id}")
  public ResponseEntity<DatosRespuestaTopico> retornaDatos(@PathVariable Long id) {
    Topico topico = topicoRepository.getReferenceById(id);
    var datosTopico = new DatosRespuestaTopico(
        topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFecha(), topico.getAutor(), topico.getCurso());
    return ResponseEntity.ok(datosTopico);
  }

}
