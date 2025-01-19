package foro.hub.api.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  private String titulo;
  private String mensaje;
  @Column(name = "fecha_de_creacion")
  private LocalDateTime fechaDeCreacion;
  @Column(name = "fecha_de_ultima_actualizacion")
  private LocalDateTime fechaDeUltimaActualizacion;
  private Boolean status;
  private String autor;
  private String curso;



  public Topico(DatosRegistroTopico datosRegistroTopico) {
    this.titulo=datosRegistroTopico.titulo();
    this.mensaje=datosRegistroTopico.mensaje();
    this.fechaDeCreacion = LocalDateTime.now();
    this.fechaDeUltimaActualizacion = LocalDateTime.now();
    this.status=true;
    this.autor=datosRegistroTopico.autor();
    this.curso= datosRegistroTopico.curso();
  }

  public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
    if(datosActualizarTopico.titulo()!=null) {
      this.titulo=datosActualizarTopico.titulo();
    }

    if(datosActualizarTopico.mensaje()!=null) {
      this.mensaje=datosActualizarTopico.mensaje();
    }

    if(datosActualizarTopico.autor()!=null) {
      this.autor=datosActualizarTopico.autor();
    }

    if(datosActualizarTopico.curso()!=null) {
      this.curso= datosActualizarTopico.curso();
    }
    this.fechaDeUltimaActualizacion=LocalDateTime.now();
  }
}
