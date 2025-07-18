package org.serratec.backend.gestao_competencias.entity.PK;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.serratec.backend.gestao_competencias.entity.Perfil;
import org.serratec.backend.gestao_competencias.entity.Usuario;

import java.util.Objects;

@Embeddable
public class UsuarioPerfilPK {

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfil, usuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioPerfilPK other = (UsuarioPerfilPK) obj;
        return Objects.equals(perfil, other.perfil) && Objects.equals(usuario, other.usuario);
    }

}
