package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
    Long id,
    String titulo,
    LocalDateTime fecha,
    String autor,
    String curso
) {
  public DatosListadoTopico(Topico topico){
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getFecha(),
            topico.getAutor(),
            topico.getCurso());
  }
}
